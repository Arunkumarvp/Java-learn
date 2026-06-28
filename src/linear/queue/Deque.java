package Queue_DSA;

// ══════════════════════════════════════════════════════════
//  TYPE 3: DEQUE — Double-Ended Queue (Array-based)
//
//  Elements can be inserted AND removed from BOTH ends.
//  front ← [ ][ ][10][20][30] → rear
//
//  Two sub-types:
//  - Input-restricted Deque  : insert only at rear
//  - Output-restricted Deque : remove only from front
//  This implementation is a fully general Deque (no restriction).
// ══════════════════════════════════════════════════════════
public class Deque {

    private int[] data;
    private int capacity;
    private int front;
    private int rear;
    private int count;

    // ── Constructor ───────────────────────────────────────
    public Deque(int capacity) {
        this.capacity = capacity;
        this.data     = new int[capacity];
        this.front    = capacity / 2; // start in middle so both ends have room
        this.rear     = capacity / 2 - 1;
        this.count    = 0;
    }

    // ── State Checks ──────────────────────────────────────

    public boolean isFull()  { return count == capacity; }
    public boolean isEmpty() { return count == 0; }
    public int size()        { return count; }

    // ── Front Operations ──────────────────────────────────

    // insertFront — O(1)
    public void insertFront(int value) {
        if (isFull()) { System.out.println("[Deque] Full! Cannot insertFront."); return; }
        front = (front - 1 + capacity) % capacity;
        data[front] = value;
        count++;
        System.out.println("[Deque] insertFront: " + value);
    }

    // deleteFront — O(1)
    public int deleteFront() {
        if (isEmpty()) { System.out.println("[Deque] Empty! Cannot deleteFront."); return -1; }
        int value = data[front];
        front = (front + 1) % capacity;
        count--;
        System.out.println("[Deque] deleteFront: " + value);
        return value;
    }

    // peekFront — O(1)
    public int peekFront() {
        if (isEmpty()) { System.out.println("[Deque] Empty!"); return -1; }
        return data[front];
    }

    // ── Rear Operations ───────────────────────────────────

    // insertRear — O(1)
    public void insertRear(int value) {
        if (isFull()) { System.out.println("[Deque] Full! Cannot insertRear."); return; }
        rear = (rear + 1) % capacity;
        data[rear] = value;
        count++;
        System.out.println("[Deque] insertRear:  " + value);
    }

    // deleteRear — O(1)
    public int deleteRear() {
        if (isEmpty()) { System.out.println("[Deque] Empty! Cannot deleteRear."); return -1; }
        int value = data[rear];
        rear = (rear - 1 + capacity) % capacity;
        count--;
        System.out.println("[Deque] deleteRear:  " + value);
        return value;
    }

    // peekRear — O(1)
    public int peekRear() {
        if (isEmpty()) { System.out.println("[Deque] Empty!"); return -1; }
        return data[rear];
    }

    // ── Utility ───────────────────────────────────────────

    // clear — O(1)
    public void clear() {
        front = capacity / 2;
        rear  = capacity / 2 - 1;
        count = 0;
        System.out.println("[Deque] Cleared.");
    }

    // display — O(n)
    public void display() {
        if (isEmpty()) { System.out.println("[Deque] (empty)"); return; }
        System.out.print("[Deque] front -> ");
        for (int i = 0; i < count; i++) {
            System.out.print(data[(front + i) % capacity] + " ");
        }
        System.out.println("<- rear");
    }
}
