
import java.util.NoSuchElementException;

// ═══════════════════════════════════════════════════════════════════════
//  TYPE 1 — ARRAY-BASED STACK
//  LIFO. Fixed capacity. top pointer starts at -1.
//  push  : stackData[++top] = value          → O(1)
//  pop   : return stackData[top--]           → O(1)
//  peek  : return stackData[top]             → O(1)
//  search: scan from top to 0                → O(n)
//  Use when: size is known upfront, need predictable memory.
// ═══════════════════════════════════════════════════════════════════════
class ArrayStack {
    private final int maxSize;
    private final int[] data;
    private int top;

    public ArrayStack(int size) {
        this.maxSize = size;
        this.data = new int[maxSize];
        this.top = -1;
    }

    // ── push ─────────────────────────────────────────────────────────
    public void push(int value) {
        if (isFull()) {
            System.out.println("  [OVERFLOW]  Cannot push " + value + " — stack is full.");
            return;
        }
        data[++top] = value;
        System.out.println("  push(" + value + ")  → top=" + top);
    }

    // ── pop ──────────────────────────────────────────────────────────
    public int pop() {
        if (isEmpty()) {
            System.out.println("  [UNDERFLOW] Cannot pop — stack is empty.");
            return Integer.MIN_VALUE;
        }
        int val = data[top--];
        System.out.println("  pop()  → " + val + "  (top now=" + top + ")");
        return val;
    }

    // ── peek ─────────────────────────────────────────────────────────
    public int peek() {
        if (isEmpty()) {
            System.out.println("  [EMPTY] Nothing to peek.");
            return Integer.MIN_VALUE;
        }
        return data[top];
    }

    // ── isEmpty / isFull / size ───────────────────────────────────────
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top >= maxSize - 1;
    }

    public int size() {
        return top + 1;
    }

    // ── search ───────────────────────────────────────────────────────
    // Returns 1-based position from top (same contract as java.util.Stack).
    // Returns -1 if not found.
    public int search(int value) {
        for (int i = top; i >= 0; i--) {
            if (data[i] == value)
                return top - i + 1;
        }
        return -1;
    }

    // ── clear ────────────────────────────────────────────────────────
    public void clear() {
        top = -1;
        System.out.println("  clear() done — stack reset.");
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        if (isEmpty()) {
            System.out.println("  [empty]");
            return;
        }
        System.out.print("  Stack (top→bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(data[i]);
            if (i > 0)
                System.out.print(" → ");
        }
        System.out.println();
    }
}

// ═══════════════════════════════════════════════════════════════════════
// TYPE 2 — LINKED-LIST STACK (Dynamic)
// No fixed capacity. Each push allocates a Node on the heap.
// The 'top' reference is the head of the linked list.
// push : new Node → points to old top → O(1)
// pop : top = top.next → O(1)
// Use when: size is unpredictable, unlimited growth needed.
// ═══════════════════════════════════════════════════════════════════════
class LinkedStack {

    // Inner node class
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node top; // points to the top element
    private int size;

    public LinkedStack() {
        top = null;
        size = 0;
    }

    // ── push ─────────────────────────────────────────────────────────
    public void push(int value) {
        Node node = new Node(value);
        node.next = top; // new node points to old top
        top = node; // new node becomes the new top
        size++;
        System.out.println("  push(" + value + ")  → top=" + top.data);
    }

    // ── pop ──────────────────────────────────────────────────────────
    public int pop() {
        if (isEmpty()) {
            System.out.println("  [UNDERFLOW] Cannot pop — stack is empty.");
            return Integer.MIN_VALUE;
        }
        int val = top.data;
        top = top.next; // move top pointer down
        size--;
        System.out.println("  pop()  → " + val);
        return val;
    }

    // ── peek ─────────────────────────────────────────────────────────
    public int peek() {
        if (isEmpty()) {
            System.out.println("  [EMPTY] Nothing to peek.");
            return Integer.MIN_VALUE;
        }
        return top.data;
    }

    // ── isEmpty / size ────────────────────────────────────────────────
    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    // ── search ───────────────────────────────────────────────────────
    // 1-based distance from top. Returns -1 if not found.
    public int search(int value) {
        int dist = 1;
        Node cur = top;
        while (cur != null) {
            if (cur.data == value)
                return dist;
            cur = cur.next;
            dist++;
        }
        return -1;
    }

    // ── clear ────────────────────────────────────────────────────────
    public void clear() {
        top = null;
        size = 0;
        System.out.println("  clear() done — all nodes released.");
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        if (isEmpty()) {
            System.out.println("  [empty]");
            return;
        }
        System.out.print("  Stack (top→bottom): ");
        Node cur = top;
        while (cur != null) {
            System.out.print(cur.data);
            if (cur.next != null)
                System.out.print(" → ");
            cur = cur.next;
        }
        System.out.println();
    }
}

// ═══════════════════════════════════════════════════════════════════════
// TYPE 3 — TWO STACKS IN ONE ARRAY
// Split a single array between two stacks:
// Stack-1 grows from index 0 → right
// Stack-2 grows from index n-1→ left
// Overflow only when both tops meet in the middle.
// Space-efficient: neither half wastes unused slots of the other.
// Use when: two stacks needed with complementary usage patterns.
// ═══════════════════════════════════════════════════════════════════════
class TwoStack {
    private final int[] data;
    private final int n;
    private int top1; // grows right → starts at -1
    private int top2; // grows left ← starts at n

    public TwoStack(int capacity) {
        n = capacity;
        data = new int[n];
        top1 = -1;
        top2 = n;
    }

    // ── push1 / push2 ────────────────────────────────────────────────
    public void push1(int value) {
        if (top1 + 1 == top2) {
            System.out.println("  [OVERFLOW] Stack1 push(" + value + ") — array full.");
            return;
        }
        data[++top1] = value;
        System.out.println("  Stack1 push(" + value + ")  top1=" + top1);
    }

    public void push2(int value) {
        if (top2 - 1 == top1) {
            System.out.println("  [OVERFLOW] Stack2 push(" + value + ") — array full.");
            return;
        }
        data[--top2] = value;
        System.out.println("  Stack2 push(" + value + ")  top2=" + top2);
    }

    // ── pop1 / pop2 ──────────────────────────────────────────────────
    public int pop1() {
        if (top1 == -1) {
            System.out.println("  [UNDERFLOW] Stack1 is empty.");
            return Integer.MIN_VALUE;
        }
        int val = data[top1--];
        System.out.println("  Stack1 pop()  → " + val);
        return val;
    }

    public int pop2() {
        if (top2 == n) {
            System.out.println("  [UNDERFLOW] Stack2 is empty.");
            return Integer.MIN_VALUE;
        }
        int val = data[top2++];
        System.out.println("  Stack2 pop()  → " + val);
        return val;
    }

    // ── peek1 / peek2 ────────────────────────────────────────────────
    public int peek1() {
        return (top1 == -1) ? Integer.MIN_VALUE : data[top1];
    }

    public int peek2() {
        return (top2 == n) ? Integer.MIN_VALUE : data[top2];
    }

    // ── isEmpty ──────────────────────────────────────────────────────
    public boolean isEmpty1() {
        return top1 == -1;
    }

    public boolean isEmpty2() {
        return top2 == n;
    }

    // ── size ─────────────────────────────────────────────────────────
    public int size1() {
        return top1 + 1;
    }

    public int size2() {
        return n - top2;
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        System.out.print("  Stack1 (top→bottom): ");
        if (top1 == -1)
            System.out.print("[empty]");
        else
            for (int i = top1; i >= 0; i--)
                System.out.print(data[i] + (i > 0 ? " → " : ""));
        System.out.println();

        System.out.print("  Stack2 (top→bottom): ");
        if (top2 == n)
            System.out.print("[empty]");
        else
            for (int i = top2; i < n; i++)
                System.out.print(data[i] + (i < n - 1 ? " → " : ""));
        System.out.println();

        System.out.println("  Raw array: " + java.util.Arrays.toString(data));
    }
}

// ═══════════════════════════════════════════════════════════════════════
// TYPE 4 — MIN STACK (O(1) getMin)
// Two internal stacks:
// mainStack — holds all pushed values
// minStack — tracks the running minimum at each level
// Rule: push to minStack only when value ≤ current minimum.
// getMin() → minStack.peek() → O(1)
// push / pop / peek / size → O(1)
// Use when: need constant-time access to minimum element.
// Classic interview problem (LeetCode #155).
// ═══════════════════════════════════════════════════════════════════════
class MinStack {
    // Using java.util.ArrayDeque as the backing structure for performance
    private final java.util.ArrayDeque<Integer> mainStack = new java.util.ArrayDeque<>();
    private final java.util.ArrayDeque<Integer> minStack = new java.util.ArrayDeque<>();

    // ── push ─────────────────────────────────────────────────────────
    public void push(int value) {
        mainStack.push(value);
        // Push to minStack if it's empty or value is ≤ current minimum
        if (minStack.isEmpty() || value <= minStack.peek()) {
            minStack.push(value);
        }
        System.out.println("  push(" + value + ")  → min=" + getMin());
    }

    // ── pop ──────────────────────────────────────────────────────────
    public int pop() {
        if (isEmpty()) {
            System.out.println("  [UNDERFLOW] Cannot pop — stack is empty.");
            return Integer.MIN_VALUE;
        }
        int val = mainStack.pop();
        // Only pop minStack if the popped value was the minimum
        if (val == minStack.peek())
            minStack.pop();
        System.out.println("  pop()  → " + val + "  (min now=" + (isEmpty() ? "N/A" : getMin()) + ")");
        return val;
    }

    // ── peek ─────────────────────────────────────────────────────────
    public int peek() {
        if (isEmpty()) {
            System.out.println("  [EMPTY] Nothing to peek.");
            return Integer.MIN_VALUE;
        }
        return mainStack.peek();
    }

    // ── getMin ───────────────────────────────────────────────────────
    // KEY OPERATION — O(1) because minStack always has current min at top
    public int getMin() {
        if (minStack.isEmpty())
            throw new NoSuchElementException("Stack is empty");
        return minStack.peek();
    }

    // ── isEmpty / size ────────────────────────────────────────────────
    public boolean isEmpty() {
        return mainStack.isEmpty();
    }

    public int size() {
        return mainStack.size();
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        if (isEmpty()) {
            System.out.println("  [empty]");
            return;
        }
        System.out.println("  Main  stack: " + mainStack);
        System.out.println("  Min   stack: " + minStack);
        System.out.println("  Current min: " + getMin());
    }
}