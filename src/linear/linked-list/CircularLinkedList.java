package Linkdin_List;

// ─────────────────────────────────────────────────────────
//  CIRCULAR LINKED LIST (Singly)
//  The last node's next pointer points BACK to head,
//  forming a circle. No node ever points to null.
//
//  head → [10] → [20] → [30] → (back to head)
//          ↑___________________________|
// ─────────────────────────────────────────────────────────
public class CircularLinkedList {

    // ── Inner Node class ──────────────────────
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;  // We only keep head; tail is always head.prev via traversal
    private int size;

    public CircularLinkedList() {
        head = null;
        size = 0;
    }

    // ─────────────────────────────────────────
    //  INSERT OPERATIONS
    // ─────────────────────────────────────────

    // Insert at head — O(n) because we must update tail.next → new head
    public void insertAtFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;       // point to itself
        } else {
            Node tail = getTail();
            newNode.next = head;    // new node points to old head
            tail.next = newNode;    // tail now points to new head
            head = newNode;         // update head
        }
        size++;
    }

    // Insert at tail — O(n) to reach tail
    public void insertAtLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node tail = getTail();
            tail.next = newNode;    // old tail points to new node
            newNode.next = head;    // new tail points back to head
        }
        size++;
    }

    // Insert at 0-based index — O(n)
    public void insertAtPosition(int index, int data) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index: " + index);
            return;
        }
        if (index == 0) { insertAtFirst(data); return; }
        if (index == size) { insertAtLast(data); return; }

        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {  // stop at node BEFORE target
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // ─────────────────────────────────────────
    //  DELETE OPERATIONS
    // ─────────────────────────────────────────

    // Delete head — O(n) to find and update tail
    public void deleteAtFirst() {
        if (head == null) { System.out.println("List is empty"); return; }
        if (size == 1) {
            head = null;
            size--;
            return;
        }
        Node tail = getTail();
        head = head.next;
        tail.next = head;   // tail must now point to new head
        size--;
    }

    // Delete tail — O(n)
    public void deleteAtLast() {
        if (head == null) { System.out.println("List is empty"); return; }
        if (size == 1) {
            head = null;
            size--;
            return;
        }
        Node temp = head;
        while (temp.next.next != head) {    // stop at second-to-last
            temp = temp.next;
        }
        temp.next = head;   // second-to-last now points back to head
        size--;
    }

    // Delete at 0-based index — O(n)
    public void deleteAtPosition(int index) {
        if (head == null) { System.out.println("List is empty"); return; }
        if (index < 0 || index >= size) { System.out.println("Invalid index: " + index); return; }
        if (index == 0)       { deleteAtFirst(); return; }
        if (index == size - 1){ deleteAtLast();  return; }

        Node temp = head;
        for (int i = 0; i < index - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--;
    }

    // Delete by value (first occurrence) — O(n)
    public void deleteByValue(int data) {
        if (head == null) { System.out.println("List is empty"); return; }

        // Check head
        if (head.data == data) { deleteAtFirst(); return; }

        Node temp = head;
        while (temp.next != head && temp.next.data != data) {
            temp = temp.next;
        }
        if (temp.next == head) { System.out.println("Value " + data + " not found"); return; }
        temp.next = temp.next.next;
        size--;
    }

    // ─────────────────────────────────────────
    //  SEARCH / ACCESS
    // ─────────────────────────────────────────

    // Returns 0-based index of first occurrence, or -1 — O(n)
    public int search(int data) {
        if (head == null) return -1;
        Node temp = head;
        int index = 0;
        do {
            if (temp.data == data) return index;
            temp = temp.next;
            index++;
        } while (temp != head);
        return -1;
    }

    // ─────────────────────────────────────────
    //  UTILITY
    // ─────────────────────────────────────────

    // Helper: returns tail node (the node whose next == head) — O(n)
    private Node getTail() {
        Node temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        return temp;
    }

    public int length() { return size; }
    public boolean isEmpty() { return head == null; }

    // Print: 10 -> 20 -> 30 -> (head)
    public void display() {
        if (head == null) { System.out.println("List is empty"); return; }
        Node temp = head;
        do {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(head)");
    }
}
