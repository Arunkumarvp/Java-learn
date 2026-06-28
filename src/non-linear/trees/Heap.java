package Trees;

// ═══════════════════════════════════════════════════════════════════════
//  HEAP (Binary Heap)
//  A complete binary tree stored in an array.
//  The array index relationships:
//    parent(i)     = (i - 1) / 2
//    leftChild(i)  = 2*i + 1
//    rightChild(i) = 2*i + 2
//
//  ┌──────────────────────────────────────────────────────────┐
//  │  MIN-HEAP: parent ≤ children (root = smallest element)   │
//  │  MAX-HEAP: parent ≥ children (root = largest  element)   │
//  │                                                          │
//  │  Operations:                                             │
//  │    insert      → add at end, heapify-up     → O(log n)  │
//  │    extractMin  → remove root, heapify-down  → O(log n)  │
//  │    peek        → root element               → O(1)      │
//  │    heapify     → build from array           → O(n)      │
//  │    sort        → HeapSort                   → O(n log n) │
//  │                                                          │
//  │  Use cases: Priority queues, Dijkstra, Prim's MST,      │
//  │             K-th largest/smallest element, scheduling.   │
//  └──────────────────────────────────────────────────────────┘
// ═══════════════════════════════════════════════════════════════════════

class MinHeap {
    private final int[] data;
    private final int capacity;
    private int size;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    // ── index helpers ─────────────────────────────────────────────────
    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    // ── insert (+ heapify-up / sift-up) ──────────────────────────────
    // Add at the last position, then bubble up until heap property holds.
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("  [FULL] Cannot insert " + value);
            return;
        }
        data[size] = value;
        heapifyUp(size);
        size++;
        System.out.println("  insert(" + value + ") → min=" + peek());
    }

    private void heapifyUp(int i) {
        while (i > 0 && data[parent(i)] > data[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // ── extractMin (+ heapify-down / sift-down) ───────────────────────
    // Remove root (min), put last element at root, sift it down.
    public int extractMin() {
        if (isEmpty()) {
            System.out.println("  [EMPTY] Cannot extract.");
            return Integer.MIN_VALUE;
        }
        int min = data[0];
        data[0] = data[--size]; // last element replaces root
        heapifyDown(0);
        System.out
                .println("  extractMin() → " + min + (isEmpty() ? "  [heap now empty]" : "  (new min=" + peek() + ")"));
        return min;
    }

    private void heapifyDown(int i) {
        int smallest = i;
        int left = leftChild(i);
        int right = rightChild(i);

        if (left < size && data[left] < data[smallest])
            smallest = left;
        if (right < size && data[right] < data[smallest])
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // ── peek ─────────────────────────────────────────────────────────
    public int peek() {
        if (isEmpty())
            return Integer.MIN_VALUE;
        return data[0];
    }

    // ── buildHeap (Heapify from array) ────────────────────────────────
    // O(n) — more efficient than inserting one-by-one O(n log n).
    // Start heapifyDown from the last non-leaf node = (n/2 - 1).
    public void buildHeap(int[] arr) {
        if (arr.length > capacity) {
            System.out.println("  [ERROR] Array larger than heap capacity.");
            return;
        }
        System.arraycopy(arr, 0, data, 0, arr.length);
        size = arr.length;
        for (int i = (size / 2) - 1; i >= 0; i--)
            heapifyDown(i);
        System.out.println("  buildHeap() → min=" + peek());
    }

    // ── delete ───────────────────────────────────────────────────────
    // Replace target with the last element, shrink size by 1,
    // then restore heap property by trying both heapifyUp and heapifyDown.
    // This avoids the Integer.MIN_VALUE sentinel trick.
    public void delete(int index) {
        if (index < 0 || index >= size) {
            System.out.println("  [INVALID] Index " + index + " out of range.");
            return;
        }
        int removed = data[index];
        data[index] = data[--size]; // replace with last element
        heapifyUp(index); // fix upward if needed
        heapifyDown(index); // fix downward if needed
        System.out.println("  delete(index=" + index + ", value=" + removed + ") done."
                + (isEmpty() ? " [heap now empty]" : " (new min=" + peek() + ")"));
    }

    // ── isEmpty / isFull / size ───────────────────────────────────────
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        if (isEmpty()) {
            System.out.println("  [empty]");
            return;
        }
        System.out.print("  MinHeap array: [");
        for (int i = 0; i < size; i++)
            System.out.print(data[i] + (i < size - 1 ? ", " : ""));
        System.out.println("]  size=" + size);

        // Show level-by-level layout
        int level = 0, levelCount = 1, count = 0;
        System.out.print("  Tree levels:  L0: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i] + " ");
            count++;
            if (count == levelCount && i < size - 1) {
                level++;
                levelCount = (int) Math.pow(2, level);
                count = 0;
                System.out.print("\n               L" + level + ": ");
            }
        }
        System.out.println();
    }
}

// ═══════════════════════════════════════════════════════════════════════
// MAX-HEAP
// Mirror of MinHeap — parent ≥ children, root = maximum.
// ═══════════════════════════════════════════════════════════════════════
class MaxHeap {
    private final int[] data;
    private final int capacity;
    private int size;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        int t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    // ── insert ───────────────────────────────────────────────────────
    public void insert(int value) {
        if (size == capacity) {
            System.out.println("  [FULL] Cannot insert " + value);
            return;
        }
        data[size] = value;
        heapifyUp(size);
        size++;
        System.out.println("  insert(" + value + ") → max=" + peek());
    }

    private void heapifyUp(int i) {
        while (i > 0 && data[parent(i)] < data[i]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // ── extractMax ───────────────────────────────────────────────────
    public int extractMax() {
        if (isEmpty()) {
            System.out.println("  [EMPTY] Cannot extract.");
            return Integer.MIN_VALUE;
        }
        int max = data[0];
        data[0] = data[--size];
        heapifyDown(0);
        System.out
                .println("  extractMax() → " + max + (isEmpty() ? "  [heap now empty]" : "  (new max=" + peek() + ")"));
        return max;
    }

    private void heapifyDown(int i) {
        int largest = i;
        int left = leftChild(i);
        int right = rightChild(i);
        if (left < size && data[left] > data[largest])
            largest = left;
        if (right < size && data[right] > data[largest])
            largest = right;
        if (largest != i) {
            swap(i, largest);
            heapifyDown(largest);
        }
    }

    // ── buildHeap ────────────────────────────────────────────────────
    public void buildHeap(int[] arr) {
        System.arraycopy(arr, 0, data, 0, arr.length);
        size = arr.length;
        for (int i = (size / 2) - 1; i >= 0; i--)
            heapifyDown(i);
        System.out.println("  buildHeap() → max=" + peek());
    }

    // ── heapSort ─────────────────────────────────────────────────────
    // Builds a max-heap then extracts elements in descending order.
    // Result: ascending sorted array in-place.
    public int[] heapSort(int[] arr) {
        buildHeap(arr);
        for (int i = size - 1; i > 0; i--) {
            swap(0, i); // move current max to end
            size--;
            heapifyDown(0); // restore heap property on reduced heap
        }
        size = arr.length; // reset
        return data;
    }

    // ── peek / isEmpty / size ─────────────────────────────────────────
    public int peek() {
        return (isEmpty()) ? Integer.MIN_VALUE : data[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        if (isEmpty()) {
            System.out.println("  [empty]");
            return;
        }
        System.out.print("  MaxHeap array: [");
        for (int i = 0; i < size; i++)
            System.out.print(data[i] + (i < size - 1 ? ", " : ""));
        System.out.println("]  size=" + size);
    }
}
