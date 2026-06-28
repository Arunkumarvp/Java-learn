package Queue_DSA;

// ══════════════════════════════════════════════════════════
//  TYPE 2: CIRCULAR QUEUE (Array-based)
//
//  Fixes the wasted-space problem of LinearQueue.
//  rear and front wrap around using modulo arithmetic.
//
//  Capacity = 5, after 2 dequeues + 2 enqueues:
//
//  index: [0] [1] [2] [3] [4]
//  data:  [40][50][ ][10][20]
//          ↑rear   ↑front
//
//  front = 3, rear = 1  (wrapped around!)
// ══════════════════════════════════════════════════════════
public class CircularQueue {

    private int[] data;
    private int capacity;
    private int front;
    private int rear;
    private int count;  // tracks actual number of elements

    // ── Constructor ───────────────────────────────────────
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.data     = new int[capacity];
        this.front    = 0;
        this.rear     = -1;
        this.count    = 0;
    }

    // ── Core Operations ───────────────────────────────────

    // isFull — O(1)
    public boolean isFull() {
        return count == capacity;
    }

    // isEmpty — O(1)
    public boolean isEmpty() {
        return count == 0;
    }

    // enqueue — rear advances with wrap-around — O(1)
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("[CircularQueue] Full! Cannot enqueue " + value);
            return;
        }
        rear = (rear + 1) % capacity;  // wrap rear
        data[rear] = value;
        count++;
        System.out.println("[CircularQueue] Enqueued: " + value
                + "  (front=" + front + ", rear=" + rear + ")");
    }

    // dequeue — front advances with wrap-around — O(1)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("[CircularQueue] Empty! Cannot dequeue.");
            return -1;
        }
        int value = data[front];
        front = (front + 1) % capacity; // wrap front
        count--;
        System.out.println("[CircularQueue] Dequeued: " + value
                + "  (front=" + front + ", rear=" + rear + ")");
        return value;
    }

    // peek — O(1)
    public int peek() {
        if (isEmpty()) { System.out.println("[CircularQueue] Empty!"); return -1; }
        return data[front];
    }

    // peekRear — O(1)
    public int peekRear() {
        if (isEmpty()) return -1;
        return data[rear];
    }

    // size — O(1)
    public int size() { return count; }

    // clear — O(1)
    public void clear() {
        front = 0; rear = -1; count = 0;
        System.out.println("[CircularQueue] Cleared.");
    }

    // display — O(n)
    public void display() {
        if (isEmpty()) { System.out.println("[CircularQueue] (empty)"); return; }
        System.out.print("[CircularQueue] front -> ");
        for (int i = 0; i < count; i++) {
            System.out.print(data[(front + i) % capacity] + " ");
        }
        System.out.println("<- rear");
    }
}
