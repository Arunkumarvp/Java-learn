package Queue_DSA;

// ══════════════════════════════════════════════════════════
//  TYPE 4: LINKED-LIST BASED QUEUE (Dynamic, no size limit)
//
//  Uses Node objects. No fixed capacity.
//  head = front, tail = rear.
//
//  head(front)                  tail(rear)
//      ↓                            ↓
//    [10] → [20] → [30] → [40] → null
// ══════════════════════════════════════════════════════════
public class LinkedQueue {

    // ── Inner Node ────────────────────────────────────────
    private static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; this.next = null; }
    }

    private Node front; // dequeue from here
    private Node rear;  // enqueue to here
    private int  count;

    // ── Constructor ───────────────────────────────────────
    public LinkedQueue() {
        front = null;
        rear  = null;
        count = 0;
    }

    // ── Core Operations ───────────────────────────────────

    // isEmpty — O(1)
    public boolean isEmpty() { return front == null; }

    // size — O(1)
    public int size() { return count; }

    // enqueue — add to rear — O(1)
    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (rear == null) {         // empty queue
            front = rear = newNode;
        } else {
            rear.next = newNode;    // link new node at rear
            rear = newNode;         // advance rear
        }
        count++;
        System.out.println("[LinkedQueue] Enqueued: " + value);
    }

    // dequeue — remove from front — O(1)
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("[LinkedQueue] Empty! Cannot dequeue.");
            return -1;
        }
        int value = front.data;
        front = front.next;         // advance front
        if (front == null) rear = null; // queue became empty
        count--;
        System.out.println("[LinkedQueue] Dequeued: " + value);
        return value;
    }

    // peek front — O(1)
    public int peek() {
        if (isEmpty()) { System.out.println("[LinkedQueue] Empty!"); return -1; }
        return front.data;
    }

    // peek rear — O(1)
    public int peekRear() {
        if (isEmpty()) return -1;
        return rear.data;
    }

    // search — O(n) — returns distance from front
    public int search(int value) {
        Node temp = front;
        int index = 0;
        while (temp != null) {
            if (temp.data == value) return index;
            temp = temp.next;
            index++;
        }
        return -1;
    }

    // clear — O(1)  (GC handles node cleanup)
    public void clear() {
        front = null;
        rear  = null;
        count = 0;
        System.out.println("[LinkedQueue] Cleared.");
    }

    // display — O(n)
    public void display() {
        if (isEmpty()) { System.out.println("[LinkedQueue] (empty)"); return; }
        System.out.print("[LinkedQueue] front -> ");
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " -> " : ""));
            temp = temp.next;
        }
        System.out.println(" <- rear");
    }
}
