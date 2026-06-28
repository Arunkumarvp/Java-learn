package Linkdin_List;

public class main {

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════
        //  1. SINGLY LINKED LIST DEMO
        // ═══════════════════════════════════════════════
        System.out.println("════════════════════════════════");
        System.out.println("  SINGLY LINKED LIST");
        System.out.println("════════════════════════════════");

        Linkdin_list sll = new Linkdin_list();

        System.out.println("\n-- Insert at first: 30, 20, 10");
        sll.insertAtFirst(30);
        sll.insertAtFirst(20);
        sll.insertAtFirst(10);
        sll.display();                              // 10 -> 20 -> 30 -> null

        System.out.println("-- Insert at last: 40, 50");
        sll.insertAtLast(40);
        sll.insertAtLast(50);
        sll.display();                              // 10 -> 20 -> 30 -> 40 -> 50 -> null

        System.out.println("-- Insert at position 2: 99");
        sll.insertAtPosition(2, 99);
        sll.display();                              // 10 -> 20 -> 99 -> 30 -> 40 -> 50 -> null

        System.out.println("-- Insert at middle");
        sll.insertAtMiddle(55);
        sll.display();

        System.out.println("-- Middle value: " + sll.getMiddle());
        System.out.println("-- Search 99: index " + sll.search(99));
        System.out.println("-- Get index 0: " + sll.get(0));
        System.out.println("-- Length: " + sll.length());

        System.out.println("\n-- Delete at first");
        sll.deleteAtFirst();
        sll.display();

        System.out.println("-- Delete at last");
        sll.deleteAtLast();
        sll.display();

        System.out.println("-- Delete at position 1");
        sll.deleteAtPosition(1);
        sll.display();

        System.out.println("-- Delete by value 40");
        sll.deleteByValue(40);
        sll.display();

        System.out.println("-- Delete at middle");
        sll.deleteAtMiddle();
        sll.display();

        System.out.println("-- Reverse");
        sll.reverse();
        sll.display();

        // ═══════════════════════════════════════════════
        //  2. DOUBLY LINKED LIST DEMO
        // ═══════════════════════════════════════════════
        System.out.println("\n════════════════════════════════");
        System.out.println("  DOUBLY LINKED LIST");
        System.out.println("════════════════════════════════");

        DoublyLinkedList dll = new DoublyLinkedList();

        System.out.println("\n-- Insert at first: 30, 20, 10");
        dll.insertAtFirst(30);
        dll.insertAtFirst(20);
        dll.insertAtFirst(10);
        dll.displayForward();                       // 10 <-> 20 <-> 30 <-> null

        System.out.println("-- Insert at last: 40, 50");
        dll.insertAtLast(40);
        dll.insertAtLast(50);
        dll.displayForward();                       // 10 <-> 20 <-> 30 <-> 40 <-> 50 <-> null

        System.out.println("-- Backward:");
        dll.displayBackward();                      // 50 <-> 40 <-> 30 <-> 20 <-> 10 <-> null

        System.out.println("-- Insert at position 2: 99");
        dll.insertAtPosition(2, 99);
        dll.displayForward();

        System.out.println("-- Search 99: index " + dll.search(99));
        System.out.println("-- Length: " + dll.length());

        System.out.println("\n-- Delete at first");
        dll.deleteAtFirst();
        dll.displayForward();

        System.out.println("-- Delete at last");
        dll.deleteAtLast();
        dll.displayForward();

        System.out.println("-- Delete at position 1");
        dll.deleteAtPosition(1);
        dll.displayForward();

        System.out.println("-- Delete by value 40");
        dll.deleteByValue(40);
        dll.displayForward();

        System.out.println("-- Reverse");
        dll.reverse();
        dll.displayForward();

        // ═══════════════════════════════════════════════
        //  3. CIRCULAR LINKED LIST DEMO
        // ═══════════════════════════════════════════════
        System.out.println("\n════════════════════════════════");
        System.out.println("  CIRCULAR LINKED LIST");
        System.out.println("════════════════════════════════");

        CircularLinkedList cll = new CircularLinkedList();

        System.out.println("\n-- Insert at last: 10, 20, 30");
        cll.insertAtLast(10);
        cll.insertAtLast(20);
        cll.insertAtLast(30);
        cll.display();                              // 10 -> 20 -> 30 -> (head)

        System.out.println("-- Insert at first: 5");
        cll.insertAtFirst(5);
        cll.display();                              // 5 -> 10 -> 20 -> 30 -> (head)

        System.out.println("-- Insert at position 2: 99");
        cll.insertAtPosition(2, 99);
        cll.display();                              // 5 -> 10 -> 99 -> 20 -> 30 -> (head)

        System.out.println("-- Search 99: index " + cll.search(99));
        System.out.println("-- Length: " + cll.length());

        System.out.println("\n-- Delete at first");
        cll.deleteAtFirst();
        cll.display();

        System.out.println("-- Delete at last");
        cll.deleteAtLast();
        cll.display();

        System.out.println("-- Delete at position 1");
        cll.deleteAtPosition(1);
        cll.display();

        System.out.println("-- Delete by value 20");
        cll.deleteByValue(20);
        cll.display();
    }
}
