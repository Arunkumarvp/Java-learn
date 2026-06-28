package Linkdin_List;

// ─────────────────────────────────────────────
//  SINGLY LINKED LIST
//  Each node holds data + a pointer to next node.
//  Head → [10] → [20] → [30] → null
// ─────────────────────────────────────────────
public class Linkdin_list {

    // ── Inner Node class ──────────────────────
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public Linkdin_list() {
        head = null;
        size = 0;
    }

    // ─────────────────────────────────────────
    //  INSERT OPERATIONS
    // ─────────────────────────────────────────

    // Insert at head — O(1)
    public void insertAtFirst(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Insert at tail — O(n)
    public void insertAtLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    // Insert at 0-based index position — O(n)
    // index 0  → same as insertAtFirst
    // index n  → same as insertAtLast (appends)
    public void insertAtPosition(int index, int data) {
        if (index < 0 || index > size) {
            System.out.println("Invalid index: " + index);
            return;
        }
        if (index == 0) {
            insertAtFirst(data);
            return;
        }
        Node newNode = new Node(data);
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {  // stop at node BEFORE target position
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // Insert after middle node (using slow/fast pointer) — O(n)
    public void insertAtMiddle(int data) {
        if (head == null) {
            insertAtFirst(data);
            return;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;       // moves 1 step
            fast = fast.next.next;  // moves 2 steps
        }
        // slow is now at the middle node
        Node newNode = new Node(data);
        newNode.next = slow.next;
        slow.next = newNode;
        size++;
    }

    // ─────────────────────────────────────────
    //  DELETE OPERATIONS
    // ─────────────────────────────────────────

    // Delete head node — O(1)
    public void deleteAtFirst() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        head = head.next;
        size--;
    }

    // Delete tail node — O(n)
    // BUG FIX: must stop at the SECOND-TO-LAST node, not the last
    public void deleteAtLast() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.next == null) {    // only one node
            head = null;
            size--;
            return;
        }
        Node temp = head;
        while (temp.next.next != null) {  // stop at second-to-last
            temp = temp.next;
        }
        temp.next = null;   // unlink last node
        size--;
    }

    // Delete node at 0-based index — O(n)
    // BUG FIX: correctly uses position parameter
    public void deleteAtPosition(int index) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (index < 0 || index >= size) {
            System.out.println("Invalid index: " + index);
            return;
        }
        if (index == 0) {
            deleteAtFirst();
            return;
        }
        Node temp = head;
        for (int i = 0; i < index - 1; i++) {  // stop at node BEFORE target
            temp = temp.next;
        }
        temp.next = temp.next.next;  // skip (unlink) the target node
        size--;
    }

    // Delete the node just after the middle — O(n)
    public void deleteAtMiddle() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.next == null) {
            head = null;
            size--;
            return;
        }
        Node slow = head;
        Node fast = head;
        Node prevSlow = null;
        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow is now at middle; unlink it
        if (prevSlow != null) {
            prevSlow.next = slow.next;
            size--;
        }
    }

    // Delete node by value (first occurrence) — O(n)
    public void deleteByValue(int data) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head.data == data) {
            deleteAtFirst();
            return;
        }
        Node temp = head;
        while (temp.next != null && temp.next.data != data) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Value " + data + " not found");
            return;
        }
        temp.next = temp.next.next;
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

    // Get value at 0-based index — O(n)
    public int get(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index: " + index);
            return -1;
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    // Return the middle node's value (slow/fast pointer) — O(n)
    public int getMiddle() {
        if (head == null) return -1;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    // ─────────────────────────────────────────
    //  REVERSE
    // ─────────────────────────────────────────

    // Reverse list in-place (iterative) — O(n), O(1) space
    public void reverse() {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;  // save next
            curr.next = prev;            // flip pointer
            prev = curr;                 // move prev forward
            curr = nextTemp;             // move curr forward
        }
        head = prev;
    }

    // ─────────────────────────────────────────
    //  UTILITY
    // ─────────────────────────────────────────

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // Print: 10 -> 20 -> 30 -> null
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
}
