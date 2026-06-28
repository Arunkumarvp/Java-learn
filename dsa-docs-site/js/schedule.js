// =========================================
// 24-Day Java + DSA Learning Console
// XplorePro.ai
// =========================================

const SCHEDULE = [
  { day:1,  topic:"Java Fundamentals",              subtitle:"Core language basics to kick off your journey",          category:"Java Basics",    dsa:["Variables","Data Types","Operators","Input/Output"],           problems:["Hello World & I/O","Type Conversion","Arithmetic Ops","Comparison Logic","User Input Calc"] },
  { day:2,  topic:"Control Statements",             subtitle:"Master decision-making and loop constructs",             category:"Java Basics",    dsa:["If-Else","Switch","for Loop","while Loop","do-while"],         problems:["Even/Odd Checker","FizzBuzz","Factorial","Number Patterns","Grade Calculator"] },
  { day:3,  topic:"Methods & Arrays",               subtitle:"Functions and one-dimensional data structures",          category:"Java Basics",    dsa:["Functions","1D Arrays","Array Traversal","Pass by Value"],    problems:["Max in Array","Reverse Array","Sum of Elements","Linear Search","Rotate Array"] },
  { day:4,  topic:"Strings",                        subtitle:"String manipulation and StringBuilder tricks",           category:"Java Basics",    dsa:["String Operations","StringBuilder","String Methods","Immutability"], problems:["Palindrome Check","Anagram Check","Count Vowels","Reverse Words","String Compression"] },
  { day:5,  topic:"2D Arrays",                      subtitle:"Matrix operations and grid-based problems",              category:"Java Basics",    dsa:["Matrix Traversal","Row/Col Sum","Transpose","Spiral Order"], problems:["Matrix Addition","Diagonal Sum","Row Reverse","Spiral Print","Saddle Point"] },
  { day:6,  topic:"Object-Oriented Programming",    subtitle:"Classes, objects, and constructor concepts",             category:"OOP",            dsa:["Classes","Objects","Constructors","this keyword","Access Modifiers"], problems:["Bank Account OOP","Student Class","Rectangle Area","Person Builder","Car Simulation"] },
  { day:7,  topic:"OOP Advanced",                   subtitle:"Inheritance, polymorphism, abstraction, encapsulation",  category:"OOP",            dsa:["Inheritance","Polymorphism","Abstraction","Encapsulation","Interfaces"], problems:["Shape Hierarchy","Animal Sounds","Abstract Vehicle","Interface Demo","Override Methods"] },
  { day:8,  topic:"Exception Handling & Collections",subtitle:"Robust error handling and dynamic data collections",   category:"Java Basics",    dsa:["try-catch-finally","Custom Exceptions","ArrayList","LinkedList","Iterator"], problems:["Division Guard","Custom Exception","ArrayList Sort","Frequency Map","Remove Duplicates"] },
  { day:9,  topic:"Time & Space Complexity",        subtitle:"Big-O analysis to write efficient algorithms",           category:"Algorithms",     dsa:["Big-O Notation","O(1) O(n) O(n²)","Space Complexity","Best/Worst Case","Amortized Analysis"], problems:["Identify Complexity","Optimize Loop","Compare Algorithms","Space Trade-off","Complexity Quiz"] },
  { day:10, topic:"Searching Techniques",           subtitle:"Linear and Binary Search implementations",               category:"Algorithms",     dsa:["Linear Search","Binary Search","Recursive Binary Search","Search in Rotated Array"], problems:["Find Element","First/Last Occurrence","Search Insert Position","Count Target","Peak Element"] },
  { day:11, topic:"Sorting Techniques – I",         subtitle:"Bubble, Selection, and Insertion Sort",                  category:"Algorithms",     dsa:["Bubble Sort","Selection Sort","Insertion Sort","Stability","In-place Sort"], problems:["Sort Ascending","Sort Descending","Count Swaps","Nearly Sorted","Insertion Sort Impl"] },
  { day:12, topic:"Sorting Techniques – II",        subtitle:"Efficient divide-and-conquer sorting algorithms",        category:"Algorithms",     dsa:["Merge Sort","Quick Sort","Pivot Selection","Merge Step","Divide & Conquer"], problems:["Merge Sort Impl","Quick Sort Impl","Sort Linked List","Kth Largest","Count Inversions"] },
  { day:13, topic:"Recursion",                      subtitle:"Recursive thinking and backtracking fundamentals",        category:"Algorithms",     dsa:["Base Case","Recursive Case","Call Stack","Backtracking","Memoization Intro"], problems:["Fibonacci Recursive","Power Function","Tower of Hanoi","Permutations","Subset Sum"] },
  { day:14, topic:"Linked Lists",                   subtitle:"Singly Linked List — all core operations",               category:"Linear DSA",     dsa:["Node Structure","Insert Head/Tail","Delete Node","Traversal","Reverse List"], problems:["Detect Cycle","Find Middle","Merge Two Lists","Remove Nth Node","Palindrome List"] },
  { day:15, topic:"Doubly & Circular Linked Lists", subtitle:"Advanced list types with bidirectional links",            category:"Linear DSA",     dsa:["Doubly LL","Circular LL","Insert/Delete DLL","Forward & Backward Traversal"], problems:["DLL Insert","DLL Delete","Circular LL Operations","Josephus Problem","LRU Cache Concept"] },
  { day:16, topic:"Stack",                          subtitle:"LIFO structure with real-world applications",             category:"Linear DSA",     dsa:["Stack Array Impl","Stack LinkedList Impl","Push/Pop/Peek","Applications"], problems:["Valid Parentheses","Next Greater Element","Min Stack","Evaluate Expression","Sort Stack"] },
  { day:17, topic:"Queue",                          subtitle:"FIFO structures — Queue, Circular, and Deque",           category:"Linear DSA",     dsa:["Queue Array Impl","Circular Queue","Deque","Priority Queue Intro","BFS Queue Use"], problems:["Circular Queue Impl","Sliding Window Max","Generate Binary Numbers","First Non-Repeating","Queue via Stack"] },
  { day:18, topic:"Hashing",                        subtitle:"HashMap and HashSet for O(1) lookups",                  category:"Linear DSA",     dsa:["HashMap","HashSet","Hash Function","Collision Handling","Load Factor"], problems:["Two Sum","Frequency Count","Group Anagrams","Longest Subarray","Subarray Sum Zero"] },
  { day:19, topic:"Trees – I",                      subtitle:"Binary Trees and all traversal strategies",               category:"Non-Linear DSA", dsa:["Binary Tree","Inorder","Preorder","Postorder","Level Order (BFS)"], problems:["Tree Height","Count Nodes","Leaf Nodes","Mirror Tree","Sum of Nodes"] },
  { day:20, topic:"Trees – II",                     subtitle:"Binary Search Tree properties and operations",            category:"Non-Linear DSA", dsa:["BST Insert","BST Search","BST Delete","Inorder is Sorted","Validate BST"], problems:["BST Insert","BST Delete","Kth Smallest","LCA in BST","Convert Sorted to BST"] },
  { day:21, topic:"Heap & Priority Queue",          subtitle:"Min/Max Heap for priority-based processing",             category:"Non-Linear DSA", dsa:["Min Heap","Max Heap","Heapify","Insert & Extract","PriorityQueue Java"], problems:["Kth Largest in Array","Merge K Lists","Top K Frequent","Running Median","Task Scheduler"] },
  { day:22, topic:"Graphs",                         subtitle:"BFS, DFS, and graph representation techniques",           category:"Non-Linear DSA", dsa:["Adjacency List","Adjacency Matrix","BFS","DFS","Cycle Detection"], problems:["BFS Traversal","DFS Traversal","Detect Cycle","Number of Islands","Bipartite Check"] },
  { day:23, topic:"Greedy & Dynamic Programming",   subtitle:"Optimal substructure and greedy choice strategies",       category:"Algorithms",     dsa:["Greedy Algorithms","Activity Selection","DP Table","Memoization","Tabulation"], problems:["Coin Change","0-1 Knapsack","Longest Common Subsequence","Fibonacci DP","Jump Game"] },
  { day:24, topic:"Mock Assessment & Interview Prep",subtitle:"Revision, coding strategies, and interview simulation", category:"Assessment",     dsa:["Revision Strategy","Time Management","Pattern Recognition","Edge Cases","Code Optimization"], problems:["Mixed Coding Test","Array Challenge","String Challenge","Tree Challenge","Graph Challenge"] },
];

const CATEGORIES = {
  "Java Basics":    { color:"#8b5cf6", days:[1,2,3,4,5,6,8] },
  "OOP":            { color:"#22d3ee", days:[6,7] },
  "Algorithms":     { color:"#f59e0b", days:[9,10,11,12,13,23] },
  "Linear DSA":     { color:"#10b981", days:[14,15,16,17,18] },
  "Non-Linear DSA": { color:"#f43f5e", days:[19,20,21,22] },
  "Assessment":     { color:"#a78bfa", days:[24] },
};

// Checklist items per day (generated from DSA + problems)
function buildChecklist(day) {
  return [
    `Read & understand: ${day.dsa[0]}`,
    `Code at least one ${day.dsa[1] || day.dsa[0]} example`,
    `Solve: ${day.problems[0]}`,
    `Solve: ${day.problems[1]}`,
    `Solve: ${day.problems[2]}`,
    `Review and write notes`,
  ];
}

// ── State ──
let currentDay = 1;
let currentFilter = 'all';

function getState() {
  const raw = localStorage.getItem('java24_state');
  if (raw) return JSON.parse(raw);
  return { completed: {}, checklist: {}, notes: {} };
}
function saveState(state) {
  localStorage.setItem('java24_state', JSON.stringify(state));
}

// ── Build Day Sidebar ──
function buildDayNav() {
  const state = getState();
  const nav = document.getElementById('dayNav');
  nav.innerHTML = '';
  SCHEDULE.forEach(d => {
    if (currentFilter === 'done' && !state.completed[d.day]) return;
    if (currentFilter === 'pending' && state.completed[d.day]) return;

    const btn = document.createElement('button');
    btn.className = 'day-nav-btn' + (state.completed[d.day] ? ' completed' : '') + (d.day === currentDay ? ' active' : '');
    btn.dataset.day = d.day;
    btn.innerHTML = `
      <div class="day-num">${d.day}</div>
      <div class="day-nav-label">
        <div>${d.topic}</div>
        <div class="day-nav-sublabel">${d.category}</div>
      </div>
      <div class="day-check">${state.completed[d.day] ? '✓' : ''}</div>`;
    btn.addEventListener('click', () => loadDay(d.day));
    nav.appendChild(btn);
  });
}

// ── Build Timeline ──
function buildTimeline() {
  const state = getState();
  const tl = document.getElementById('timeline');
  tl.innerHTML = '';
  SCHEDULE.forEach(d => {
    const item = document.createElement('div');
    const done = state.completed[d.day];
    item.className = 'tl-item' + (done ? ' tl-done' : '') + (d.day === currentDay ? ' tl-active' : '');
    item.innerHTML = `
      <div class="tl-dot"></div>
      <span class="tl-day-num">D${d.day}</span>
      <span class="tl-day-name">${d.topic}</span>
      ${done ? '<span class="tl-check">✓</span>' : ''}`;
    item.addEventListener('click', () => loadDay(d.day));
    tl.appendChild(item);
  });
}

// ── Build Category Bars ──
function buildCategoryBars() {
  const state = getState();
  const container = document.getElementById('categoryBars');
  container.innerHTML = '';
  Object.entries(CATEGORIES).forEach(([name, cat]) => {
    const total = cat.days.length;
    const done = cat.days.filter(d => state.completed[d]).length;
    const pct = total > 0 ? Math.round((done / total) * 100) : 0;
    const row = document.createElement('div');
    row.className = 'cat-row';
    row.innerHTML = `
      <div class="cat-label-row">
        <span class="cat-name">${name}</span>
        <span class="cat-count">${done}/${total}</span>
      </div>
      <div class="cat-bar-track">
        <div class="cat-bar-fill" style="width:${pct}%;background:${cat.color}"></div>
      </div>`;
    container.appendChild(row);
  });
}

// ── Update Stats ──
function updateStats() {
  const state = getState();
  const completed = Object.values(state.completed).filter(Boolean).length;
  const remaining = 24 - completed;
  const pct = Math.round((completed / 24) * 100);

  document.getElementById('statCompleted').textContent = completed;
  document.getElementById('statRemaining').textContent = remaining;
  document.getElementById('progressDays').textContent = `${completed} / 24 days`;
  document.getElementById('progressPct').textContent = `${pct}%`;

  // Streak: consecutive days from day 1
  let streak = 0;
  for (let i = 1; i <= 24; i++) {
    if (state.completed[i]) streak++;
    else break;
  }
  document.getElementById('statStreak').textContent = streak;

  // Circular progress
  const circle = document.getElementById('progressCircle');
  circle.setAttribute('stroke-dasharray', `${pct}, 100`);
}

// ── Build Day Dots ──
function buildDayDots() {
  const state = getState();
  const dots = document.getElementById('dayDots');
  dots.innerHTML = '';
  SCHEDULE.forEach(d => {
    const dot = document.createElement('div');
    dot.className = 'day-dot' + (state.completed[d.day] ? ' completed' : '') + (d.day === currentDay ? ' active' : '');
    dot.title = `Day ${d.day}: ${d.topic}`;
    dot.addEventListener('click', () => loadDay(d.day));
    dots.appendChild(dot);
  });
}

// ── Load Day ──
function loadDay(dayNum) {
  currentDay = dayNum;
  const state = getState();
  const day = SCHEDULE[dayNum - 1];
  const done = state.completed[dayNum];

  // Hero
  document.getElementById('dayNumberBadge').textContent = `Day ${dayNum}`;
  document.getElementById('dayTitle').textContent = day.topic;
  document.getElementById('daySubtitle').textContent = day.subtitle;

  const hero = document.getElementById('dayHero');
  hero.className = 'day-hero' + (done ? ' completed-hero' : '');

  const btnComplete = document.getElementById('btnComplete');
  if (done) {
    btnComplete.innerHTML = `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="20 6 9 17 4 12"/></svg> Completed!`;
    btnComplete.className = 'btn-complete is-completed';
  } else {
    btnComplete.innerHTML = `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="20 6 9 17 4 12"/></svg> Mark Complete`;
    btnComplete.className = 'btn-complete';
  }

  // DSA Tags
  const dsaTags = document.getElementById('dsaTags');
  dsaTags.innerHTML = day.dsa.map(t => `<span class="tag tag-dsa">${t}</span>`).join('');

  // Problem Tags
  const probTags = document.getElementById('problemTags');
  probTags.innerHTML = day.problems.map(p => `<span class="tag tag-problem">${p}</span>`).join('');

  // Checklist
  const items = buildChecklist(day);
  const savedChecks = state.checklist[dayNum] || {};
  const checklistEl = document.getElementById('checklist');
  checklistEl.innerHTML = '';
  items.forEach((item, idx) => {
    const checked = savedChecks[idx];
    const li = document.createElement('li');
    li.className = 'checklist-item' + (checked ? ' checked' : '');
    li.dataset.idx = idx;
    li.innerHTML = `
      <div class="chk-box">${checked ? '✓' : ''}</div>
      <span class="chk-label">${item}</span>`;
    li.addEventListener('click', () => toggleCheck(dayNum, idx));
    checklistEl.appendChild(li);
  });

  // Notes
  const notesArea = document.getElementById('notesArea');
  notesArea.value = state.notes[dayNum] || '';

  // Nav arrows
  document.getElementById('prevDay').disabled = dayNum <= 1;
  document.getElementById('nextDay').disabled = dayNum >= 24;

  // Rebuild sidebar/timeline
  buildDayNav();
  buildTimeline();
  buildDayDots();
  buildCategoryBars();
  updateStats();

  // Scroll detail panel to top
  document.getElementById('detailPanel').scrollTop = 0;
}

// ── Toggle Checklist ──
function toggleCheck(dayNum, idx) {
  const state = getState();
  if (!state.checklist[dayNum]) state.checklist[dayNum] = {};
  state.checklist[dayNum][idx] = !state.checklist[dayNum][idx];
  saveState(state);
  loadDay(dayNum);
}

// ── Mark Complete ──
document.getElementById('btnComplete').addEventListener('click', () => {
  const state = getState();
  state.completed[currentDay] = !state.completed[currentDay];
  saveState(state);
  loadDay(currentDay);

  // Confetti on completion
  if (state.completed[currentDay]) {
    spawnConfetti();
    const totalDone = Object.values(state.completed).filter(Boolean).length;
    if (totalDone === 24) {
      setTimeout(() => {
        document.getElementById('modalBody').textContent =
          "You've crushed all 24 days of the Java + DSA challenge! You're now interview-ready. 🚀";
        document.getElementById('modalOverlay').classList.add('show');
      }, 600);
    }
  }
});

// ── Notes auto-save ──
let noteTimer;
document.getElementById('notesArea').addEventListener('input', () => {
  clearTimeout(noteTimer);
  noteTimer = setTimeout(() => {
    const state = getState();
    state.notes[currentDay] = document.getElementById('notesArea').value;
    saveState(state);
    const autosave = document.getElementById('noteAutosave');
    autosave.classList.add('show');
    setTimeout(() => autosave.classList.remove('show'), 2000);
  }, 600);
});

// ── Navigation Arrows ──
document.getElementById('prevDay').addEventListener('click', () => {
  if (currentDay > 1) loadDay(currentDay - 1);
});
document.getElementById('nextDay').addEventListener('click', () => {
  if (currentDay < 24) loadDay(currentDay + 1);
});

// ── Filter Buttons ──
document.querySelectorAll('.filter-btn').forEach(btn => {
  btn.addEventListener('click', () => {
    document.querySelectorAll('.filter-btn').forEach(b => b.classList.remove('active'));
    btn.classList.add('active');
    currentFilter = btn.dataset.filter;
    buildDayNav();
  });
});

// ── Reset ──
document.getElementById('btnReset').addEventListener('click', () => {
  if (confirm('Reset all progress, notes, and checklists? This cannot be undone.')) {
    localStorage.removeItem('java24_state');
    currentDay = 1;
    loadDay(1);
  }
});

// ── Modal Close ──
document.getElementById('modalClose').addEventListener('click', () => {
  document.getElementById('modalOverlay').classList.remove('show');
});

// ── Confetti ──
function spawnConfetti() {
  const colors = ['#8b5cf6','#22d3ee','#10b981','#f59e0b','#f43f5e','#a78bfa'];
  for (let i = 0; i < 60; i++) {
    const el = document.createElement('div');
    el.style.cssText = `
      position:fixed;top:-10px;left:${Math.random()*100}vw;
      width:${6+Math.random()*6}px;height:${6+Math.random()*6}px;
      background:${colors[Math.floor(Math.random()*colors.length)]};
      border-radius:${Math.random()>0.5?'50%':'2px'};
      opacity:1;pointer-events:none;z-index:9999;
      animation:confettiFall ${1.5+Math.random()*2}s ease forwards;
      animation-delay:${Math.random()*0.5}s;`;
    document.body.appendChild(el);
    setTimeout(() => el.remove(), 3500);
  }
}

// Inject confetti keyframes once
const confettiStyle = document.createElement('style');
confettiStyle.textContent = `
  @keyframes confettiFall {
    to { transform: translateY(105vh) rotate(${Math.random()*720}deg); opacity:0; }
  }`;
document.head.appendChild(confettiStyle);

// ── Keyboard Navigation ──
document.addEventListener('keydown', e => {
  if (e.target.tagName === 'TEXTAREA') return;
  if (e.key === 'ArrowLeft' || e.key === 'ArrowUp') {
    if (currentDay > 1) loadDay(currentDay - 1);
  } else if (e.key === 'ArrowRight' || e.key === 'ArrowDown') {
    if (currentDay < 24) loadDay(currentDay + 1);
  } else if (e.key === 'Enter' || e.key === ' ') {
    document.getElementById('btnComplete').click();
  }
});

// ── Init ──
loadDay(1);
