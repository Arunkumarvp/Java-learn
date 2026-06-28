# Java DSA Learning Repository 🚀

A structured, comprehensive Java learning repository covering **Data Structures**, **Algorithms**, and **Problem Solving** with an interactive web-based documentation portal and an **offline AI tutor** powered by Qwen2.5-Coder.

---

## 📁 Repository Structure

```
Java-learn/
│
├── src/                        ← Java source code
│   ├── linear/
│   │   ├── linked-list/        Singly, Doubly, Circular Linked Lists
│   │   ├── stack/              Array Stack, Linked Stack, Min Stack
│   │   └── queue/              Linear, Circular, Deque, Priority Queue
│   │
│   ├── non-linear/
│   │   ├── trees/              Binary Tree, BST, AVL Tree, Heap
│   │   └── graph/              Adjacency List & Matrix
│   │
│   ├── algorithms/
│   │   ├── sorting/            Bubble, Selection, Insertion, Merge, Quick
│   │   ├── searching/          Linear, Binary Search
│   │   └── recursion/          Recursive patterns & examples
│   │
│   └── patterns/               100 Java pattern programs (1–100)
│
├── problems/
│   └── arrays/                 Array problem-solving exercises
│
├── dsa-docs-site/              ← Interactive web learning portal
│   ├── index.html              DSA Hub (documentation)
│   ├── schedule.html           24-Day Java + DSA Console
│   ├── tutor.html              🤖 Offline AI Java Tutor
│   ├── start.sh                Smart launcher (Linux/Mac)
│   ├── start.ps1               Smart launcher (Windows)
│   └── start.bat               Windows double-click launcher
│
├── scripts/                    ← Utility scripts
│   ├── generate_docs.py        Auto-generate markdown docs
│   └── generate_patterns.py    Auto-generate pattern exercises
│
├── operations.md               Quick reference for all DSA operations
└── README.md
```

---

## 🚀 Quick Start

### Launch the Learning Portal + AI Tutor

**Linux / macOS:**
```bash
cd dsa-docs-site
bash start.sh
```

**Windows:**
```
Double-click: dsa-docs-site/start.bat
```

The smart launcher will:
1. 🔍 Detect your system specs (RAM, CPU, GPU)
2. 🤖 Select the best AI model for your hardware
3. ⚙️ Install Ollama + pull the model (first run only)
4. 🌐 Find a free port and start the web server
5. 🚀 Open your browser automatically

---

## 📚 Topics Covered

| Category | Topics |
|---|---|
| **Linear DS** | Singly/Doubly/Circular Linked List, Stack, Queue, Deque, Priority Queue |
| **Non-Linear DS** | Binary Tree, BST, AVL Tree, Min/Max Heap, Graph (Adj. List/Matrix) |
| **Algorithms** | Bubble/Selection/Insertion/Merge/Quick Sort, Linear/Binary Search |
| **Recursion** | Factorial, Fibonacci, Tower of Hanoi, Backtracking patterns |
| **Patterns** | 100 Java pattern programs |
| **Problems** | Array rotation, duplicates, frequency, Fibonacci arrays |

---

## 🤖 Offline AI Tutor

The built-in AI tutor runs **100% on your machine** — no cloud, no internet required after setup.

| System RAM | Auto-selected Model | Quality |
|---|---|---|
| < 3.5 GB | TinyLlama-1.1B | ⭐⭐ |
| 3.5–6 GB | Qwen2.5-Coder-1.5B | ⭐⭐⭐⭐ |
| > 6 GB | Qwen2.5-Coder-7B | ⭐⭐⭐⭐⭐ |

---

## 📖 24-Day Learning Schedule

The `schedule.html` page tracks your progress through a structured 24-day Java + DSA curriculum with:
- ✅ Daily topic checklists
- 📝 Personal notes (auto-saved)
- 📊 Progress tracking with streak counter
- 🗓️ Day-by-day navigation

---

## 🛠️ Running Individual Java Programs

```bash
# Compile
javac src/linear/linked-list/Linkdin_list.java

# Run
java -cp src/linear/linked-list main
```

---

## 📄 License

MIT — Free to use for learning and education.
