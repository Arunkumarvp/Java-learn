/**
 * tutor-widget.js — Floating AI Tutor button for all pages
 * Injects a pulsing chat bubble in the bottom-right corner.
 */
(function () {
  const style = document.createElement('style');
  style.textContent = `
    .ai-fab {
      position: fixed; bottom: 28px; right: 28px; z-index: 9999;
      width: 58px; height: 58px; border-radius: 18px;
      background: linear-gradient(135deg, #6366f1, #8b5cf6);
      border: none; cursor: pointer;
      display: flex; align-items: center; justify-content: center;
      box-shadow: 0 8px 28px rgba(99,102,241,0.55);
      transition: transform 0.2s, box-shadow 0.2s;
      text-decoration: none;
    }
    .ai-fab:hover { transform: scale(1.12) translateY(-3px); box-shadow: 0 14px 38px rgba(99,102,241,0.7); }
    .ai-fab:active { transform: scale(0.97); }
    .ai-fab span { font-size: 1.6rem; line-height: 1; }
    .ai-fab-ring {
      position: fixed; bottom: 24px; right: 24px; z-index: 9998;
      width: 66px; height: 66px; border-radius: 20px;
      border: 2px solid rgba(99,102,241,0.5);
      animation: fab-pulse 2.5s ease-in-out infinite;
      pointer-events: none;
    }
    .ai-fab-tooltip {
      position: fixed; bottom: 95px; right: 22px; z-index: 9997;
      background: rgba(17,21,32,0.95); border: 1px solid rgba(99,102,241,0.3);
      border-radius: 10px; padding: 8px 14px;
      font-family: 'Inter', system-ui, sans-serif;
      font-size: 0.78rem; color: #f1f5f9; white-space: nowrap;
      box-shadow: 0 4px 20px rgba(0,0,0,0.4);
      opacity: 0; transform: translateY(6px);
      transition: opacity 0.2s, transform 0.2s;
      pointer-events: none;
    }
    .ai-fab-tooltip::after {
      content: ''; position: absolute; bottom: -6px; right: 22px;
      border: 6px solid transparent; border-top-color: rgba(99,102,241,0.3);
    }
    .ai-fab:hover ~ .ai-fab-tooltip,
    .ai-fab-wrap:hover .ai-fab-tooltip { opacity: 1; transform: translateY(0); }
    .ai-fab-wrap { position: fixed; bottom: 0; right: 0; z-index: 9999; }
    @keyframes fab-pulse {
      0%   { transform: scale(1); opacity: 0.7; }
      50%  { transform: scale(1.18); opacity: 0.2; }
      100% { transform: scale(1); opacity: 0.7; }
    }
  `;
  document.head.appendChild(style);

  // Wrapper for hover tooltip
  const wrap = document.createElement('div');
  wrap.className = 'ai-fab-wrap';

  const ring = document.createElement('div');
  ring.className = 'ai-fab-ring';

  const fab = document.createElement('a');
  fab.className = 'ai-fab';
  fab.href = 'tutor.html';
  fab.title = 'Open AI Java Tutor';
  fab.setAttribute('aria-label', 'Open offline AI Java tutor');
  fab.innerHTML = '<span>🤖</span>';

  const tooltip = document.createElement('div');
  tooltip.className = 'ai-fab-tooltip';
  tooltip.innerHTML = '🤖 <strong>JavaBot</strong> · Offline AI Tutor';

  wrap.appendChild(ring);
  wrap.appendChild(fab);
  wrap.appendChild(tooltip);
  document.body.appendChild(wrap);
})();
