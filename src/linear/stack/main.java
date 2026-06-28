
import java.util.ArrayDeque;

public class main {

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════════════════════════
        // TYPE 1 — ARRAY-BASED STACK
        // LIFO. Fixed-size int array. top starts at -1.
        // Best for: bounded collections, predictable memory usage.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("  TYPE 1: ARRAY-BASED STACK");
        System.out.println("══════════════════════════════════════════════════");

        ArrayStack as = new ArrayStack(5);

        // push — normal fills
        as.push(10);
        as.push(20);
        as.push(30);
        as.push(40);
        as.push(50);
        as.display();

        // push — overflow guard
        as.push(99); // stack is full

        // peek — view top without removing
        System.out.println("  peek()    → " + as.peek());

        // size, isEmpty, isFull
        System.out.println("  size()    → " + as.size());
        System.out.println("  isFull()  → " + as.isFull());
        System.out.println("  isEmpty() → " + as.isEmpty());

        // search — 1-based distance from top (-1 = not found)
        System.out.println("  search(30)→ " + as.search(30) + "  (1-based from top)");
        System.out.println("  search(99)→ " + as.search(99) + "  (-1 = not found)");

        // pop — LIFO order
        as.pop();
        as.pop();
        as.display();

        // pop — underflow guard (pop until empty, then one more)
        as.pop();
        as.pop();
        as.pop();
        as.pop(); // underflow

        // clear — reset without reallocating
        as.push(5);
        as.push(6);
        as.clear();
        as.display();

        // ═══════════════════════════════════════════════════════════════════
        // TYPE 2 — LINKED-LIST STACK (Dynamic)
        // No size limit. Each push allocates a Node.
        // Best for: unknown/unbounded input sizes.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  TYPE 2: LINKED-LIST STACK (Dynamic)");
        System.out.println("══════════════════════════════════════════════════");

        LinkedStack ls = new LinkedStack();

        ls.push(100);
        ls.push(200);
        ls.push(300);
        ls.push(400);
        ls.display();

        System.out.println("  peek()     → " + ls.peek());
        System.out.println("  size()     → " + ls.size());
        System.out.println("  search(200)→ " + ls.search(200) + "  (1-based from top)");
        System.out.println("  search(999)→ " + ls.search(999) + "  (-1 = not found)");

        ls.pop();
        ls.pop();
        ls.display();

        // underflow guard
        ls.pop();
        ls.pop();
        ls.pop(); // stack is empty now

        ls.push(77);
        ls.clear();
        ls.display();

        // ═══════════════════════════════════════════════════════════════════
        // TYPE 3 — TWO STACKS IN ONE ARRAY
        // Stack1 grows → from index 0.
        // Stack2 grows ← from index n-1.
        // Overflow only when both tops collide in the middle.
        // Best for: paired undo/redo buffers, symbol table scopes.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  TYPE 3: TWO STACKS IN ONE ARRAY");
        System.out.println("══════════════════════════════════════════════════");

        TwoStack ts = new TwoStack(8);

        ts.push1(1);
        ts.push1(2);
        ts.push1(3);
        ts.push2(90);
        ts.push2(80);
        ts.push2(70);
        ts.display();

        System.out.println("  peek1() → " + ts.peek1());
        System.out.println("  peek2() → " + ts.peek2());
        System.out.println("  size1() → " + ts.size1());
        System.out.println("  size2() → " + ts.size2());

        ts.pop1();
        ts.pop2();
        ts.display();

        // Both stacks fill completely (8 slots, 3+3=6 pushed, 2 popped → 4 used → 4
        // free)
        ts.push1(4);
        ts.push1(5);
        ts.push2(60);
        ts.push2(50);
        ts.display();

        // Overflow — 8 slots are now all occupied
        ts.push1(99); // should overflow

        // ═══════════════════════════════════════════════════════════════════
        // TYPE 4 — MIN STACK (O(1) getMin)
        // Tracks the running minimum alongside normal push/pop operations.
        // getMin() is O(1) — no scanning needed.
        // Best for: stock price tracking, monotonic problems, LeetCode #155.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  TYPE 4: MIN STACK  (O(1) getMin)");
        System.out.println("══════════════════════════════════════════════════");

        MinStack ms = new MinStack();

        ms.push(5);
        ms.push(3); // new min
        ms.push(7);
        ms.push(2); // new min
        ms.push(2); // duplicate min — still tracked
        ms.push(8);
        ms.display();

        System.out.println("  peek()   → " + ms.peek());
        System.out.println("  getMin() → " + ms.getMin());
        System.out.println("  size()   → " + ms.size());

        // pop and verify min updates correctly
        ms.pop(); // removes 8 — min stays 2
        ms.pop(); // removes 2 — min stays 2 (duplicate)
        ms.pop(); // removes 2 — min reverts to 3
        ms.display();

        ms.pop(); // removes 7 — min stays 3
        ms.pop(); // removes 3 — min reverts to 5
        ms.display();

        ms.pop(); // removes 5 — stack now empty
        ms.display();

        // underflow guard
        ms.pop();

        // ═══════════════════════════════════════════════════════════════════
        // JAVA COLLECTIONS — BUILT-IN STACK TYPES
        // java.util.Stack — legacy, synchronized, extends Vector
        // java.util.ArrayDeque — preferred modern stack (Deque interface)
        // Both use the same LIFO contract.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  JAVA COLLECTIONS — BUILT-IN STACK TYPES");
        System.out.println("══════════════════════════════════════════════════");

        // ── java.util.Stack (legacy) ─────────────────────────────────────
        System.out.println("\n  ── java.util.Stack (legacy, avoid in new code) ──");
        java.util.Stack<Integer> jStack = new java.util.Stack<>();

        jStack.push(10);
        jStack.push(20);
        jStack.push(30);
        System.out.println("  push 10, 20, 30  → " + jStack);

        System.out.println("  peek()   → " + jStack.peek()); // view top
        System.out.println("  pop()    → " + jStack.pop()); // remove top
        System.out.println("  search(10)→ " + jStack.search(10));// 1-based from top
        System.out.println("  empty()  → " + jStack.empty());
        System.out.println("  size()   → " + jStack.size());
        System.out.println("  after pop: " + jStack);

        // ── java.util.ArrayDeque as Stack (recommended) ──────────────────
        System.out.println("\n  ── java.util.ArrayDeque as Stack (recommended) ──");
        ArrayDeque<Integer> jDequeStack = new ArrayDeque<>();

        // Deque-as-stack: use push/pop/peek (operates on the FRONT)
        jDequeStack.push(10); // same as addFirst
        jDequeStack.push(20);
        jDequeStack.push(30);
        System.out.println("  push 10, 20, 30  → " + jDequeStack); // [30, 20, 10]

        System.out.println("  peek()   → " + jDequeStack.peek()); // 30
        System.out.println("  pop()    → " + jDequeStack.pop()); // 30
        System.out.println("  isEmpty()→ " + jDequeStack.isEmpty());
        System.out.println("  size()   → " + jDequeStack.size());
        System.out.println("  after pop: " + jDequeStack); // [20, 10]

        // Iterating in LIFO order
        System.out.print("  LIFO iteration: ");
        for (int val : jDequeStack)
            System.out.print(val + " ");
        System.out.println();

        // ─────────────────────────────────────────────────────────────────
        // COMPLEXITY SUMMARY
        // ─────────────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  COMPLEXITY SUMMARY");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println(" Operation  │ Array  │ Linked │ TwoStack │ MinStack │ Deque  ");
        System.out.println(" ───────────┼────────┼────────┼──────────┼──────────┼────────");
        System.out.println(" push       │  O(1)  │  O(1)  │   O(1)   │   O(1)   │  O(1)  ");
        System.out.println(" pop        │  O(1)  │  O(1)  │   O(1)   │   O(1)   │  O(1)  ");
        System.out.println(" peek       │  O(1)  │  O(1)  │   O(1)   │   O(1)   │  O(1)  ");
        System.out.println(" getMin     │  O(n)  │  O(n)  │   O(n)   │   O(1)   │  O(n)  ");
        System.out.println(" search     │  O(n)  │  O(n)  │   O(n)   │   O(n)   │  O(n)  ");
        System.out.println(" Space      │  O(n)  │  O(n)  │   O(n)   │   O(n)   │  O(n)  ");
        System.out.println(" Dynamic    │   No   │  Yes   │    No    │   Yes    │  Yes   ");
        System.out.println(" Thread-safe│   No   │   No   │    No    │    No    │   No   ");
    }
}