document.addEventListener('DOMContentLoaded', () => {
    const navLinks = document.querySelectorAll('.nav-list a');
    const markdownBody = document.getElementById('markdown-body');
    const loadingEl = document.getElementById('loading');
    const errorEl = document.getElementById('error');

    // Configure marked options
    marked.setOptions({
        gfm: true,
        breaks: true,
        headerIds: true
    });

    async function loadMarkdown(docPath) {
        // Show loading state
        markdownBody.innerHTML = '';
        loadingEl.classList.remove('hidden');
        errorEl.classList.add('hidden');

        try {
            const response = await fetch(docPath);
            if (!response.ok) throw new Error(`HTTP error! status: ${response.status}`);
            
            const markdownText = await response.text();
            
            // Pre-process markdown to handle mermaid blocks
            // marked.js wraps code blocks in <pre><code>, we need to extract mermaid blocks
            const renderer = new marked.Renderer();
            const originalCode = renderer.code.bind(renderer);
            
            renderer.code = function(token) {
                // In latest marked.js, token is an object: { text, lang, ... }
                let lang = typeof token === 'object' ? token.lang : arguments[1];
                let code = typeof token === 'object' ? token.text : arguments[0];

                if (lang === 'mermaid') {
                    // Unescape HTML entities that marked might have escaped
                    const unescaped = code.replace(/&amp;/g, '&')
                                          .replace(/&lt;/g, '<')
                                          .replace(/&gt;/g, '>')
                                          .replace(/&quot;/g, '"')
                                          .replace(/&#39;/g, "'");
                    return `<div class="mermaid">${unescaped}</div>`;
                }
                
                // Call original code block renderer
                if (typeof token === 'object') {
                    return originalCode.call(this, token);
                } else {
                    return originalCode.apply(this, arguments);
                }
            };

            const html = marked.parse(markdownText, { renderer });
            
            // Render to DOM
            markdownBody.innerHTML = html;

            // Initialize Mermaid diagrams
            if (window.mermaid) {
                try {
                    await window.mermaid.run({
                        querySelector: '.mermaid'
                    });
                } catch (e) {
                    console.error("Mermaid parsing error:", e);
                }
            }

            // Scroll to top
            document.querySelector('.content-area').scrollTop = 0;

        } catch (error) {
            console.error('Error loading markdown:', error);
            errorEl.classList.remove('hidden');
        } finally {
            loadingEl.classList.add('hidden');
        }
    }

    // Handle Navigation
    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            
            // Update active state
            navLinks.forEach(l => l.classList.remove('active'));
            link.classList.add('active');

            const docPath = link.getAttribute('data-doc');
            loadMarkdown(docPath);
        });
    });

    // Load initial document
    const initialDoc = document.querySelector('.nav-list a.active').getAttribute('data-doc');
    loadMarkdown(initialDoc);
});
