package Linkdin_List;

// ─────────────────────────────────────────────────────────
//  DOUBLY LINKED LIST
//  Each node has BOTH a next AND a prev pointer.
//  null ← [10] ⇄ [20] ⇄ [30] → null
//         head                  tail
// ─────────────────────────────────────────────────────────
public class DoublyLinkedList {

    // ── Inner Node class ──────────────────────
    static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // ─────────────────────────────────────────
    //  INSERT OPERATIONS
    // ─────────────────────────────────────────

    // Insert at head — O(1)
    public void insertAtFirst(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Insert at tail — O(1)  ← advantage over singly list
    public void insertAtLast(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
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
        for (int i = 0; i < index; i++) {
            temp = temp.next;   // temp is now the node currently AT index
        }
        // Insert newNode BEFORE temp
        Node prevNode = temp.prev;
        newNode.next = temp;
        newNode.prev = prevNode;
        prevNode.next = newNode;
        temp.prev = newNode;
        size++;
    }

    // ─────────────────────────────────────────
    //  DELETE OPERATIONS
    // ─────────────────────────────────────────

    // Delete head — O(1)
    public void deleteAtFirst() {
        if (head == null) { System.out.println("List is empty"); return; }
        if (head == tail) {         // only one node
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
    }

    // Delete tail — O(1)  ← advantage over singly list
    public void deleteAtLast() {
        if (tail == null) { System.out.println("List is empty"); return; }
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
    }

    // Delete at 0-based index — O(n)
    public void deleteAtPosition(int index) {
        if (head == null) { System.out.println("List is empty"); return; }
        if (index < 0 || index >= size) { System.out.println("Invalid index: " + index); return; }
        if (index == 0)       { deleteAtFirst(); return; }
        if (index == size - 1){ deleteAtLast();  return; }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;   // temp is the node to delete
        }
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size--;
    }

    // Delete by value (first occurrence) — O(n)
    public void deleteByValue(int data) {
        if (head == null) { System.out.println("List is empty"); return; }
        Node temp = head;
        while (temp != null && temp.data != data) {
            temp = temp.next;
        }
        if (temp == null) { System.out.println("Value " + data + " not found"); return; }

        if (temp == head) { deleteAtFirst(); return; }
        if (temp == tail) { deleteAtLast();  return; }

        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        size--;
    }

    // ─────────────────────────────────────────
    //  SEARCH / ACCESS
    // ─────────────────────────────────────────

    // Returns 0-based index of first occurrence, or -1 — O(n)
    public int search(int data) {
        Node temp = head;
        int index = 0;
        while (temp != null) {
            if (temp.data == data) return index;
            temp = temp.next;
            index++;
        }
        return -1;
    }

    // ─────────────────────────────────────────
    //  REVERSE
    // ─────────────────────────────────────────

    // Reverse in-place by swapping prev/next pointers — O(n)
    public void reverse() {
        Node curr = head;
        Node temp = null;
        while (curr != null) {
            temp = curr.prev;       // swap prev and next
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev;       // move to what was next
        }
        if (temp != null) {
            head = temp.prev;       // temp.prev is the new head
        }
        // swap head and tail references
        temp = head;
        head = tail;
        tail = temp;
    }

    // ─────────────────────────────────────────
    //  UTILITY
    // ─────────────────────────────────────────

    public int length() { return size; }
    public boolean isEmpty() { return head == null; }

    // Forward:  10 <-> 20 <-> 30 <-> null
    public void displayForward() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Backward: 30 <-> 20 <-> 10 <-> null  (no extra space needed)
    public void displayBackward() {
        Node temp = tail;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }
}
