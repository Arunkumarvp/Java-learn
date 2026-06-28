package Queue_DSA;

import java.util.ArrayDeque;

public class main {

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════════════
        //  TYPE 1 — LINEAR QUEUE
        //  FIFO. Fixed capacity. front/rear pointers.
        //  Limitation: wasted space after dequeues.
        //  Use when: size is known and queue never wraps.
        // ═══════════════════════════════════════════════════════
        System.out.println("══════════════════════════════════════");
        System.out.println("  TYPE 1: LINEAR QUEUE");
        System.out.println("══════════════════════════════════════");

        LinearQueue lq = new LinearQueue(5);

        lq.enqueue(10);
        lq.enqueue(20);
        lq.enqueue(30);
        lq.display();

        System.out.println("peek front : " + lq.peek());
        System.out.println("peek rear  : " + lq.peekRear());
        System.out.println("size       : " + lq.size());
        System.out.println("search(20) : index " + lq.search(20));

        lq.dequeue();
        lq.dequeue();
        lq.display();

        lq.enqueue(40);
        lq.enqueue(50);
        lq.enqueue(60); // fills up
        lq.enqueue(70); // should show FULL
        lq.display();

        lq.clear();
        lq.display();

        // ═══════════════════════════════════════════════════════
        //  TYPE 2 — CIRCULAR QUEUE
        //  Fixes wasted space: rear wraps to index 0 after end.
        //  front = (front+1) % capacity
        //  rear  = (rear+1)  % capacity
        //  Use when: fixed buffer that recycles (e.g. OS scheduler).
        // ═══════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  TYPE 2: CIRCULAR QUEUE");
        System.out.println("══════════════════════════════════════");

        CircularQueue cq = new CircularQueue(4);

        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.enqueue(40); // full
        cq.display();

        cq.dequeue();   // free slot at index 0
        cq.dequeue();   // free slot at index 1
        cq.display();

        cq.enqueue(50); // wraps to index 0
        cq.enqueue(60); // wraps to index 1
        cq.display();   // shows reuse of freed slots

        System.out.println("peek front : " + cq.peek());
        System.out.println("peek rear  : " + cq.peekRear());
        System.out.println("size       : " + cq.size());

        cq.clear();
        cq.display();

        // ═══════════════════════════════════════════════════════
        //  TYPE 3 — DEQUE (Double-Ended Queue)
        //  Insert AND delete from both front and rear.
        //  Sub-types:
        //    Input-restricted  : insert only at rear
        //    Output-restricted : delete only from front
        //  Use when: need both stack (LIFO) and queue (FIFO) behaviour.
        //  Example: sliding window maximum problem.
        // ═══════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  TYPE 3: DEQUE (Double-Ended Queue)");
        System.out.println("══════════════════════════════════════");

        Deque dq = new Deque(8);

        dq.insertRear(20);
        dq.insertRear(30);
        dq.insertFront(10); // inserts at front
        dq.insertFront(5);  // inserts before 10
        dq.display();       // 5 10 20 30

        System.out.println("peekFront : " + dq.peekFront());
        System.out.println("peekRear  : " + dq.peekRear());
        System.out.println("size      : " + dq.size());

        dq.deleteFront();   // removes 5
        dq.deleteRear();    // removes 30
        dq.display();       // 10 20

        dq.insertFront(1);
        dq.insertRear(99);
        dq.display();       // 1 10 20 99

        dq.clear();

        // ═══════════════════════════════════════════════════════
        //  TYPE 4 — LINKED-LIST QUEUE (Dynamic)
        //  No capacity limit. Each enqueue allocates a Node.
        //  Use when: size is unpredictable and memory isn't tight.
        // ═══════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  TYPE 4: LINKED-LIST QUEUE");
        System.out.println("══════════════════════════════════════");

        LinkedQueue llq = new LinkedQueue();

        llq.enqueue(100);
        llq.enqueue(200);
        llq.enqueue(300);
        llq.enqueue(400);
        llq.display();

        System.out.println("peek front  : " + llq.peek());
        System.out.println("peek rear   : " + llq.peekRear());
        System.out.println("search(300) : index " + llq.search(300));
        System.out.println("size        : " + llq.size());

        llq.dequeue();
        llq.dequeue();
        llq.display();

        llq.clear();
        llq.display();

        // ═══════════════════════════════════════════════════════
        //  TYPE 5 — PRIORITY QUEUE (Min-Heap)
        //  Elements dequeued in priority order (lowest number first).
        //  enqueue: O(log n), dequeue: O(log n), peek: O(1)
        //  Use when: task scheduling, Dijkstra, A*, Huffman coding.
        // ═══════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  TYPE 5: PRIORITY QUEUE (min-heap)");
        System.out.println("══════════════════════════════════════");

        PriorityQueue pq = new PriorityQueue(10);

        pq.enqueue(300, 3);  // value=300, priority=3
        pq.enqueue(100, 1);  // highest priority → dequeued first
        pq.enqueue(500, 5);
        pq.enqueue(200, 2);
        pq.enqueue(400, 4);
        pq.display();

        System.out.println("peek (highest priority): " + pq.peek());

        System.out.println("-- Dequeue all in priority order:");
        while (!pq.isEmpty()) pq.dequeue();

        // ═══════════════════════════════════════════════════════
        //  JAVA COLLECTIONS — BUILT-IN QUEUE TYPES
        //  java.util provides production-ready implementations.
        // ═══════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  JAVA COLLECTIONS QUEUE EXAMPLES");
        System.out.println("══════════════════════════════════════");

        // --- java.util.LinkedList as Queue ---
        System.out.println("\n-- java.util.LinkedList as Queue:");
        java.util.Queue<Integer> javaQueue = new java.util.LinkedList<>();
        javaQueue.offer(10);    // enqueue (returns false if full)
        javaQueue.offer(20);
        javaQueue.offer(30);
        System.out.println("peek    : " + javaQueue.peek());    // view front
        System.out.println("poll    : " + javaQueue.poll());    // dequeue
        System.out.println("element : " + javaQueue.element()); // peek, throws if empty
        System.out.println("size    : " + javaQueue.size());

        // --- java.util.ArrayDeque as Deque ---
        System.out.println("\n-- java.util.ArrayDeque as Deque:");
        ArrayDeque<Integer> javaDeque = new ArrayDeque<>();
        javaDeque.offerFirst(20);
        javaDeque.offerFirst(10);   // front
        javaDeque.offerLast(30);
        javaDeque.offerLast(40);    // rear
        System.out.println("peekFirst : " + javaDeque.peekFirst());
        System.out.println("peekLast  : " + javaDeque.peekLast());
        javaDeque.pollFirst();
        javaDeque.pollLast();
        System.out.println("after pollFirst + pollLast: " + javaDeque);

        // --- java.util.PriorityQueue ---
        System.out.println("\n-- java.util.PriorityQueue (min-heap):");
        java.util.PriorityQueue<Integer> javaPQ = new java.util.PriorityQueue<>();
        javaPQ.offer(40);
        javaPQ.offer(10);
        javaPQ.offer(30);
        javaPQ.offer(20);
        System.out.print("Poll order (ascending): ");
        while (!javaPQ.isEmpty()) System.out.print(javaPQ.poll() + " ");
        System.out.println();

        // Max-heap using reverseOrder comparator
        java.util.PriorityQueue<Integer> maxPQ =
                new java.util.PriorityQueue<>(java.util.Collections.reverseOrder());
        maxPQ.offer(40); maxPQ.offer(10); maxPQ.offer(30); maxPQ.offer(20);
        System.out.print("Poll order (descending / max-heap): ");
        while (!maxPQ.isEmpty()) System.out.print(maxPQ.poll() + " ");
        System.out.println();

        // ═══════════════════════════════════════════════════════
        //  QUICK COMPLEXITY SUMMARY (printed for reference)
        // ═══════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════");
        System.out.println("  COMPLEXITY SUMMARY");
        System.out.println("══════════════════════════════════════");
        System.out.println(" Operation       | Linear | Circular | Deque | Linked | Priority");
        System.out.println(" enqueue         |  O(1)  |   O(1)   |  O(1) |  O(1)  |  O(log n)");
        System.out.println(" dequeue         |  O(1)  |   O(1)   |  O(1) |  O(1)  |  O(log n)");
        System.out.println(" peek            |  O(1)  |   O(1)   |  O(1) |  O(1)  |  O(1)");
        System.out.println(" search          |  O(n)  |   O(n)   |  O(n) |  O(n)  |  O(n)");
        System.out.println(" Space           |  O(n)  |   O(n)   |  O(n) |  O(n)  |  O(n)");
        System.out.println(" Dynamic size    |   No   |    No    |   No  |  Yes   |  No");
        System.out.println(" Wraps around    |   No   |   Yes    |  Yes  |  N/A   |  N/A");
        System.out.println(" Priority order  |   No   |    No    |   No  |   No   |  Yes");
    }
}
