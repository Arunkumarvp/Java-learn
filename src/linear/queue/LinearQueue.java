package Queue_DSA;

// ══════════════════════════════════════════════════════════
//  TYPE 1: LINEAR QUEUE (Array-based)
//
//  FIFO — First In, First Out.
//  front points to first element, rear to last.
//  Limitation: once front moves right, left slots are wasted.
//
//  front                rear
//   ↓                    ↓
//  [ ][ ][10][20][30][40][ ]
//         ← used space →
// ══════════════════════════════════════════════════════════
public class LinearQueue {

    private int[] data;
    private int capacity;
    private int front;
    private int rear;

    // ── Constructor ───────────────────────────────────────
    public LinearQueue(int capacity) {
        this.capacity = capacity;
        this.data     = new int[capacity];
        this.front    = -1;
        this.rear     = -1;
    }

    // ── Core Operations ───────────────────────────────────

    // isFull — O(1)
    // rear has reached the last index (even if front moved forward)
    public boolean isFull() {
        return rear == capacity - 1;
    }

    // isEmpty — O(1)
    public boolean isEmpty() {
        return front == -1;
    }

    // enqueue — add to rear — O(1)
    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("[LinearQueue] Queue is full! Cannot enqueue " + value);
            return;
        }
        if (isEmpty()) front = 0;   // first element: set front
        data[++rear] = value;
        System.out.println("[LinearQueue] Enqueued: " + value);
    }

    // dequeue — remove from front — O(1)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("[LinearQueue] Queue is empty! Cannot dequeue.");
            return -1;
        }
        int value = data[front];
        if (front == rear) {        // last element removed
            front = rear = -1;
        } else {
            front++;
        }
        System.out.println("[LinearQueue] Dequeued: " + value);
        return value;
    }

    // peek — view front without removing — O(1)
    public int peek() {
        if (isEmpty()) {
            System.out.println("[LinearQueue] Queue is empty!");
            return -1;
        }
        return data[front];
    }

    // rear peek — O(1)
    public int peekRear() {
        if (isEmpty()) return -1;
        return data[rear];
    }

    // size — O(1)
    public int size() {
        if (isEmpty()) return 0;
        return rear - front + 1;
    }

    // search — O(n)
    public int search(int value) {
        for (int i = front; i <= rear; i++) {
            if (data[i] == value) return i - front; // distance from front
        }
        return -1; // not found
    }

    // clear — O(1)
    public void clear() {
        front = rear = -1;
        System.out.println("[LinearQueue] Cleared.");
    }

    // display — front → rear — O(n)
    public void display() {
        if (isEmpty()) { System.out.println("[LinearQueue] (empty)"); return; }
        System.out.print("[LinearQueue] front -> ");
        for (int i = front; i <= rear; i++) System.out.print(data[i] + " ");
        System.out.println("<- rear");
    }
}
