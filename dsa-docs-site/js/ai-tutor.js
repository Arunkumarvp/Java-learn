/**
 * ai-tutor.js — Dual-Backend Offline Java AI Tutor Engine
 *
 * Backend Priority:
 *  1. Ollama (auto-detected URL from tutor-config.js) — Qwen2.5-Coder, fast CPU
 *  2. WebLLM (browser WASM) — Qwen2.5-Coder-1.5B-Instruct fallback
 *
 * Config is dynamically injected by start.sh / start.ps1 into js/tutor-config.js
 * Usage:
 *   window.AITutor.onProgress(cb).onReady(cb).onError(cb).load();
 *   window.AITutor.chat(message, onChunk, onDone);
 */

// Read dynamic config written by start.sh / start.ps1, fall back to defaults
const _dyn = (typeof window !== 'undefined' && window.TUTOR_CONFIG) || {};

const CONFIG = {
  ollama: {
    baseUrl:  _dyn.ollamaUrl    || 'http://localhost:11434',
    model:    _dyn.preferredModel || 'qwen2.5-coder:1.5b',
    fallback: 'tinyllama',
    timeout:  3000,
  },
  webllm: {
    model:    'Qwen2.5-Coder-1.5B-Instruct-q4f16_1-MLC',
    fallback: 'TinyLlama-1.1B-Chat-v1.0-q4f32_1-MLC',
  },
  sysInfo: _dyn.systemInfo || {},
};

const SYSTEM_PROMPT = `You are JavaBot, an expert Java and Data Structures & Algorithms tutor specializing in code-focused teaching.

Your responses must:
1. Always use proper Java syntax with complete, runnable code examples
2. Include Time Complexity (Big O) and Space Complexity for algorithm questions
3. Break down complex concepts step-by-step before showing code
4. Use comments in code to explain key lines
5. For DSA questions, explain: concept → approach → code → complexity
6. Keep explanations clear and beginner-friendly

Topics you cover: Java OOP, Arrays, LinkedList (Singly/Doubly/Circular), Stack, Queue, Binary Tree, BST, AVL Tree, Heap, Graph, Sorting (Bubble/Selection/Insertion/Merge/Quick), Searching (Linear/Binary), Recursion, Dynamic Programming, and coding interview patterns.

When answering, structure your response as:
- Brief concept explanation
- Java code example (always in a code block)
- Complexity analysis (when applicable)`;

class AITutorEngine {
  constructor() {
    this.backend       = null;  // 'ollama' | 'webllm' | null
    this.webllmEngine  = null;
    this.isLoaded      = false;
    this.isLoading     = false;
    this.activeModel   = null;
    this.ollamaModels  = [];
    this.messageHistory = [{ role: 'system', content: SYSTEM_PROMPT }];

    // Callbacks
    this._onProgress = null;
    this._onReady    = null;
    this._onError    = null;
    this._onStatus   = null;
  }

  onProgress(cb) { this._onProgress = cb; return this; }
  onReady(cb)    { this._onReady    = cb; return this; }
  onError(cb)    { this._onError    = cb; return this; }
  onStatus(cb)   { this._onStatus   = cb; return this; }

  _progress(pct, text, phase) {
    if (this._onProgress) this._onProgress({ pct, text, phase });
  }
  _status(text) {
    if (this._onStatus) this._onStatus(text);
  }

  // ─── Public: load ───────────────────────────────────────────────────
  async load() {
    if (this.isLoaded || this.isLoading) return;
    this.isLoading = true;

    try {
      // Try Ollama first
      const ollamaOk = await this._checkOllama();

      if (ollamaOk) {
        await this._initOllama();
      } else {
        // Fall back to WebLLM
        await this._initWebLLM();
      }
    } catch (err) {
      this.isLoading = false;
      console.error('[AITutor] Load failed:', err);
      if (this._onError) this._onError(err);
    }
  }

  // ─── Ollama: health check ────────────────────────────────────────────
  async _checkOllama() {
    this._status('Checking for local Ollama service...');
    try {
      const ctrl = new AbortController();
      const tid   = setTimeout(() => ctrl.abort(), CONFIG.ollama.timeout);
      const resp  = await fetch(`${CONFIG.ollama.baseUrl}/api/tags`, {
        signal: ctrl.signal
      });
      clearTimeout(tid);
      if (!resp.ok) return false;

      const data = await resp.json();
      this.ollamaModels = (data.models || []).map(m => m.name);
      return true;
    } catch {
      return false;
    }
  }

  // ─── Ollama: init ────────────────────────────────────────────────────
  async _initOllama() {
    this._progress(30, 'Ollama detected! Connecting...', 'ollama');

    // Choose best available model
    const preferred = CONFIG.ollama.model;
    const fallback  = CONFIG.ollama.fallback;

    const hasPreferred = this.ollamaModels.some(m => m.startsWith(preferred.split(':')[0]));
    const hasFallback  = this.ollamaModels.some(m => m.startsWith(fallback));

    if (hasPreferred) {
      this.activeModel = preferred;
    } else if (hasFallback) {
      this.activeModel = fallback;
      console.warn(`[AITutor] Preferred model "${preferred}" not found, using "${fallback}"`);
    } else {
      // Model not pulled yet — try to pull it
      this._progress(40, `Pulling ${preferred} (first-time setup)...`, 'pulling');
      const pulled = await this._pullOllamaModel(preferred);
      if (!pulled) {
        throw new Error(
          `Model "${preferred}" not found in Ollama. Run:\n  ollama pull ${preferred}\nthen reload this page.`
        );
      }
      this.activeModel = preferred;
    }

    this._progress(100, `Ready · ${this.activeModel}`, 'ready');
    this.backend   = 'ollama';
    this.isLoaded  = true;
    this.isLoading = false;
    if (this._onReady) this._onReady({ backend: 'ollama', model: this.activeModel, models: this.ollamaModels });
  }

  // ─── Ollama: pull model ──────────────────────────────────────────────
  async _pullOllamaModel(model) {
    try {
      const resp = await fetch(`${CONFIG.ollama.baseUrl}/api/pull`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: model, stream: true })
      });
      if (!resp.ok) return false;

      const reader = resp.body.getReader();
      const dec    = new TextDecoder();
      let pulled   = false;

      while (true) {
        const { done, value } = await reader.read();
        if (done) break;
        const lines = dec.decode(value).split('\n').filter(Boolean);
        for (const line of lines) {
          try {
            const obj = JSON.parse(line);
            if (obj.status === 'success') pulled = true;
            if (obj.completed && obj.total) {
              const pct = Math.round((obj.completed / obj.total) * 60) + 40;
              this._progress(pct, `Downloading ${model}... ${Math.round(obj.completed/1e6)}MB`, 'pulling');
            }
          } catch { /* skip non-JSON */ }
        }
      }
      return pulled;
    } catch {
      return false;
    }
  }

  // ─── WebLLM: init ───────────────────────────────────────────────────
  async _initWebLLM() {
    this._progress(5, 'Ollama not found — loading browser-based model...', 'webllm');

    try {
      const webllm = await import('https://esm.run/@mlc-ai/web-llm');
      this.webllmEngine = new webllm.MLCEngine();

      this.webllmEngine.setInitProgressCallback((report) => {
        const pct  = Math.round(report.progress * 95);
        const text = report.text || `Loading model... ${pct}%`;
        this._progress(pct, text, 'webllm');
      });

      // Try Qwen2.5-Coder first, fall back to TinyLlama
      let loaded = false;
      for (const model of [CONFIG.webllm.model, CONFIG.webllm.fallback]) {
        try {
          this._progress(5, `Loading ${model}...`, 'webllm');
          await this.webllmEngine.reload(model);
          this.activeModel = model;
          loaded = true;
          break;
        } catch (err) {
          console.warn(`[AITutor] WebLLM model ${model} failed:`, err.message);
        }
      }

      if (!loaded) throw new Error('All WebLLM models failed to load.');

      this._progress(100, `Ready · ${this.activeModel} (browser)`, 'ready');
      this.backend   = 'webllm';
      this.isLoaded  = true;
      this.isLoading = false;
      if (this._onReady) this._onReady({ backend: 'webllm', model: this.activeModel });

    } catch (err) {
      this.isLoading = false;
      if (this._onError) this._onError(err);
    }
  }

  // ─── Public: chat ────────────────────────────────────────────────────
  async chat(userMessage, onChunk, onDone) {
    if (!this.isLoaded) throw new Error('Model not loaded. Call load() first.');

    this.messageHistory.push({ role: 'user', content: userMessage });
    let fullResponse = '';

    try {
      if (this.backend === 'ollama') {
        fullResponse = await this._chatOllama(onChunk);
      } else {
        fullResponse = await this._chatWebLLM(onChunk);
      }

      this.messageHistory.push({ role: 'assistant', content: fullResponse });

      // Keep history: system prompt + last 20 messages
      if (this.messageHistory.length > 21) {
        this.messageHistory = [this.messageHistory[0], ...this.messageHistory.slice(-20)];
      }

      if (onDone) onDone(fullResponse);
      return fullResponse;

    } catch (err) {
      // Remove the failed user message from history
      this.messageHistory.pop();
      console.error('[AITutor] Chat error:', err);
      if (this._onError) this._onError(err);
      throw err;
    }
  }

  // ─── Ollama: streaming chat ──────────────────────────────────────────
  async _chatOllama(onChunk) {
    const resp = await fetch(`${CONFIG.ollama.baseUrl}/api/chat`, {
      method:  'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        model:    this.activeModel,
        messages: this.messageHistory,
        stream:   true,
        options:  { temperature: 0.7, num_predict: 1024 }
      })
    });

    if (!resp.ok) throw new Error(`Ollama error: ${resp.status} ${resp.statusText}`);

    const reader = resp.body.getReader();
    const dec    = new TextDecoder();
    let full     = '';

    while (true) {
      const { done, value } = await reader.read();
      if (done) break;
      const lines = dec.decode(value).split('\n').filter(Boolean);
      for (const line of lines) {
        try {
          const obj   = JSON.parse(line);
          const delta = obj?.message?.content || '';
          if (delta) {
            full += delta;
            if (onChunk) onChunk(delta, full);
          }
        } catch { /* skip */ }
      }
    }
    return full;
  }

  // ─── WebLLM: streaming chat ──────────────────────────────────────────
  async _chatWebLLM(onChunk) {
    const stream = await this.webllmEngine.chat.completions.create({
      messages:       this.messageHistory,
      stream:         true,
      temperature:    0.7,
      max_tokens:     1024,
      stream_options: { include_usage: false }
    });

    let full = '';
    for await (const chunk of stream) {
      const delta = chunk.choices[0]?.delta?.content || '';
      if (delta) {
        full += delta;
        if (onChunk) onChunk(delta, full);
      }
    }
    return full;
  }

  // ─── Public: switch Ollama model ─────────────────────────────────────
  async switchModel(modelName) {
    if (this.backend !== 'ollama') return false;
    this.activeModel = modelName;
    this.resetChat();
    return true;
  }

  // ─── Public: re-check Ollama (used by "Check Again" button) ──────────
  async recheckOllama() {
    const ok = await this._checkOllama();
    if (ok) {
      this.isLoaded  = false;
      this.isLoading = false;
      await this._initOllama();
      return true;
    }
    return false;
  }

  // ─── Public: reset chat ───────────────────────────────────────────────
  resetChat() {
    this.messageHistory = [{ role: 'system', content: SYSTEM_PROMPT }];
    if (this.backend === 'webllm' && this.webllmEngine) {
      try { this.webllmEngine.resetChat(); } catch {}
    }
  }

  // ─── Public: info ─────────────────────────────────────────────────────
  getInfo() {
    return {
      backend:       this.backend,
      model:         this.activeModel,
      isLoaded:      this.isLoaded,
      ollamaModels:  this.ollamaModels,
      historyLength: this.messageHistory.length - 1,
    };
  }
}

window.AITutor = new AITutorEngine();
