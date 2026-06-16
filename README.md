# Java Data Structures & Algorithms Learning Hub 🚀

Welcome to the **Java DSA Learning Hub**! This repository is a comprehensive collection of Data Structures, Algorithms, and 100 logical programming patterns implemented in Java. 

It also includes a **fully interactive, locally-hosted documentation website** with beautiful Mermaid visualizations to help you visually trace the logic behind every operation!

---

## 📁 Repository Structure

- `Array problems/`: Array manipulations, rotations, and frequency logic.
- `Linkdin_List/`: Singly, Doubly, and Circular Linked Lists.
- `stack/`: Array-based stacks, MinStacks, and Two-Stacks in an array.
- `Queue_DSA/`: Linear Queues, Circular Queues, Deques, and Priority Queues.
- `non-linear-data/`: Trees (Binary, BST, AVL, Heaps) and Graphs (BFS, DFS, Pathfinding).
- `Recursiveprogramming/`: Classic recursion (Fibonacci, Factorial, Tower of Hanoi, N-Queens Backtracking).
- `pattern/`: 100 logical shape printing patterns to master loops and coordinates.
- `dsa-docs-site/`: The interactive documentation frontend.

---

## 📖 Viewing the Interactive Documentation

To view the step-by-step visualizations and operational flowcharts of the code, you can run the local documentation server.

### Prerequisites
- **Python 3.x** must be installed on your system (used to run a simple local web server).

### For Linux / macOS Users
1. Open your terminal.
2. Navigate to the documentation directory:
   ```bash
   cd dsa-docs-site
   ```
3. Run the provided bash script:
   ```bash
   ./run.sh
   ```
   *(Alternatively, you can run: `python3 -m http.server 8000`)*
4. Open your web browser and go to: **http://localhost:8000**

### For Windows Users
1. Open Command Prompt (`cmd`) or PowerShell.
2. Navigate to the documentation directory:
   ```cmd
   cd dsa-docs-site
   ```
3. Start the Python HTTP server:
   ```cmd
   python -m http.server 8000
   ```
4. Open your web browser and go to: **http://localhost:8000**

---

## ⚙️ Compiling and Running the Java Code

To run any of the specific algorithms or data structures, use the standard `javac` and `java` commands.

1. Compile the Java file:
   ```bash
   javac path/to/FileName.java
   ```
2. Run the compiled class:
   ```bash
   java path.to.FileName
   ```

## 🛠 Automation Scripts
- `generate_docs.py`: Automatically parses the Java codebase to build out an updated `operations.md` index.
- `generate_patterns.py`: Automatically scans the `pattern/` folder and generates beautifully formatted Markdown files containing all 100 logic shapes and their respective code.
