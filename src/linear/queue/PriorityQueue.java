package Queue_DSA;

// ══════════════════════════════════════════════════════════
//  TYPE 5: PRIORITY QUEUE (Min-Heap based, manual impl.)
//
//  Each element has a priority. Lower number = higher priority.
//  Dequeue always returns the element with LOWEST priority value.
//
//  Built manually using a min-heap (array representation).
//  Java's java.util.PriorityQueue does this for you — shown at bottom.
// ══════════════════════════════════════════════════════════
public class PriorityQueue {

    // ── Entry: value + priority ───────────────────────────
    private static class Entry {
        int value;
        int priority;   // lower number = dequeued first

        Entry(int value, int priority) {
            this.value    = value;
            this.priority = priority;
        }
        public String toString() {
            return "(" + value + ", p=" + priority + ")";
        }
    }

    private Entry[] heap;
    private int     capacity;
    private int     count;

    // ── Constructor ───────────────────────────────────────
    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.heap     = new Entry[capacity];
        this.count    = 0;
    }

    // ── Heap Helper Indices ───────────────────────────────
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i)   { return 2 * i + 1;   }
    private int right(int i)  { return 2 * i + 2;   }

    // ── Swap ──────────────────────────────────────────────
    private void swap(int i, int j) {
        Entry tmp = heap[i]; heap[i] = heap[j]; heap[j] = tmp;
    }

    // ── Heapify Up (after insert) ─────────────────────────
    private void heapifyUp(int i) {
        while (i > 0 && heap[i].priority < heap[parent(i)].priority) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    // ── Heapify Down (after remove) ───────────────────────
    private void heapifyDown(int i) {
        int smallest = i;
        int l = left(i), r = right(i);
        if (l < count && heap[l].priority < heap[smallest].priority) smallest = l;
        if (r < count && heap[r].priority < heap[smallest].priority) smallest = r;
        if (smallest != i) {
            swap(i, smallest);
            heapifyDown(smallest);
        }
    }

    // ── Core Operations ───────────────────────────────────

    // isEmpty — O(1)
    public boolean isEmpty() { return count == 0; }

    // isFull — O(1)
    public boolean isFull()  { return count == capacity; }

    // size — O(1)
    public int size() { return count; }

    // enqueue — insert with priority — O(log n)
    public void enqueue(int value, int priority) {
        if (isFull()) { System.out.println("[PriorityQueue] Full!"); return; }
        heap[count] = new Entry(value, priority);
        heapifyUp(count);
        count++;
        System.out.println("[PriorityQueue] Enqueued: " + value + " (priority=" + priority + ")");
    }

    // dequeue — removes element with LOWEST priority number — O(log n)
    public int dequeue() {
        if (isEmpty()) { System.out.println("[PriorityQueue] Empty!"); return -1; }
        int value = heap[0].value;
        System.out.println("[PriorityQueue] Dequeued: " + value + " (priority=" + heap[0].priority + ")");
        heap[0] = heap[--count];    // move last to root
        heapifyDown(0);             // restore heap
        return value;
    }

    // peek — view highest-priority element without removing — O(1)
    public int peek() {
        if (isEmpty()) { System.out.println("[PriorityQueue] Empty!"); return -1; }
        return heap[0].value;
    }

    // display — O(n)  (heap order, not sorted)
    public void display() {
        if (isEmpty()) { System.out.println("[PriorityQueue] (empty)"); return; }
        System.out.print("[PriorityQueue] heap -> ");
        for (int i = 0; i < count; i++) System.out.print(heap[i] + " ");
        System.out.println();
    }
}
