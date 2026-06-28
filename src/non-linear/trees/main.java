package Trees;

import java.util.ArrayDeque;
import java.util.Queue;

public class main {

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════════════════════════
        // SECTION 1 — BINARY TREE (BT)
        // Unordered tree. Level-order insertion keeps it complete.
        // Visual example built:
        // 1
        // / \
        // 2 3
        // / \ / \
        // 4 5 6 7
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("  SECTION 1: BINARY TREE (BT)");
        System.out.println("══════════════════════════════════════════════════");

        BinaryTree bt = new BinaryTree();

        // insert — fills level by level (BFS order)
        System.out.println("-- insert 1,2,3,4,5,6,7:");
        bt.insert(1);
        bt.insert(2);
        bt.insert(3);
        bt.insert(4);
        bt.insert(5);
        bt.insert(6);
        bt.insert(7);
        bt.display();

        // traversals
        bt.inorder(); // Left → Node → Right : 4 2 5 1 6 3 7
        bt.preorder(); // Node → Left → Right : 1 2 4 5 3 6 7
        bt.postorder(); // Left → Right → Node : 4 5 2 6 7 3 1
        bt.levelOrder(); // BFS level by level

        // properties
        System.out.println("  height()      → " + bt.height());
        System.out.println("  countNodes()  → " + bt.countNodes());
        System.out.println("  countLeaves() → " + bt.countLeaves());

        // search
        System.out.println("  search(5)     → " + bt.search(5));
        System.out.println("  search(99)    → " + bt.search(99));

        // ═══════════════════════════════════════════════════════════════════
        // SECTION 2 — BINARY SEARCH TREE (BST)
        // Invariant: left < node < right at every node.
        // Inorder traversal always gives sorted ascending output.
        //
        // Insertions: 50, 30, 70, 20, 40, 60, 80
        // Visual:
        // 50
        // / \
        // 30 70
        // / \ / \
        // 20 40 60 80
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 2: BINARY SEARCH TREE (BST)");
        System.out.println("══════════════════════════════════════════════════");

        BST bst = new BST();

        // insert
        System.out.println("-- insert 50,30,70,20,40,60,80:");
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.display();

        // traversals
        bst.inorder(); // sorted: 20 30 40 50 60 70 80
        bst.preorder();
        bst.postorder();

        // properties
        System.out.println("  min()              → " + bst.min());
        System.out.println("  max()              → " + bst.max());
        System.out.println("  height()           → " + bst.height());
        System.out.println("  countNodes()       → " + bst.countNodes());
        System.out.println("  countLeaves()      → " + bst.countLeaves());

        // search
        System.out.println("  search(40)         → " + bst.search(40));
        System.out.println("  search(99)         → " + bst.search(99));

        // inorder successor
        System.out.println("  inorderSucc(40)    → " + bst.inorderSuccessor(40)); // 50
        System.out.println("  inorderSucc(70)    → " + bst.inorderSuccessor(70)); // 80

        // delete — CASE 1: leaf node (20)
        System.out.println("\n-- delete(20) [leaf node]:");
        bst.delete(20);
        bst.inorder();
        bst.display();

        // delete — CASE 2: one child (30 after 20 was removed)
        System.out.println("-- delete(30) [one child]:");
        bst.delete(30);
        bst.inorder();
        bst.display();

        // delete — CASE 3: two children (50 — root)
        System.out.println("-- delete(50) [two children → replaced by inorder successor 60]:");
        bst.delete(50);
        bst.inorder();
        bst.display();

        // delete non-existent
        System.out.println("-- delete(999) [not found]:");
        bst.delete(999);

        // ═══════════════════════════════════════════════════════════════════
        // SECTION 3 — AVL TREE
        // Self-balancing BST. Triggers rotations on imbalance.
        // Inserting 10,20,30 in a plain BST creates a right-skewed chain
        // (degenerate / worst case). AVL detects BF=-2 and left-rotates.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 3: AVL TREE (Self-Balancing BST)");
        System.out.println("══════════════════════════════════════════════════");

        AVLTree avl = new AVLTree();

        // RR Case — triggers left rotation
        System.out.println("-- RR case: insert 10, 20, 30:");
        avl.insert(10);
        avl.insert(20);
        avl.insert(30); // imbalance at 10 (BF=-2, right child BF=0) → left rotate
        avl.display();
        avl.inorder();

        // LL Case — triggers right rotation
        System.out.println("\n-- LL case: insert 15, 25 (then insert 5, 8):");
        avl.insert(15);
        avl.insert(5);
        avl.insert(8); // BF check triggers LR on some ancestor
        avl.display();
        avl.inorder();

        // More inserts
        System.out.println("\n-- insert 40, 50, 60:");
        avl.insert(40);
        avl.insert(50);
        avl.insert(60);
        avl.display();
        avl.inorder();

        // search
        System.out.println("  search(20)   → " + avl.search(20));
        System.out.println("  search(99)   → " + avl.search(99));
        System.out.println("  height()     → " + avl.height());
        System.out.println("  balanceFactor(root) → " + avl.getBalanceFactor());

        // delete
        System.out.println("\n-- delete(10):");
        avl.delete(10);
        avl.display();
        avl.inorder();

        // ═══════════════════════════════════════════════════════════════════
        // SECTION 4A — MIN-HEAP
        // Parent ≤ children. Root = minimum element always.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 4A: MIN-HEAP");
        System.out.println("══════════════════════════════════════════════════");

        MinHeap minH = new MinHeap(15);

        // insert one by one
        System.out.println("-- individual inserts:");
        minH.insert(20);
        minH.insert(15);
        minH.insert(30);
        minH.insert(5);
        minH.insert(10);
        minH.insert(25);
        minH.display();

        System.out.println("  peek()  → " + minH.peek()); // 5
        System.out.println("  size()  → " + minH.size());

        // extractMin — O(log n)
        System.out.println("\n-- extractMin sequence:");
        while (!minH.isEmpty())
            minH.extractMin();

        // buildHeap from array — O(n)
        System.out.println("\n-- buildHeap([40,30,50,10,20,60,5]):");
        MinHeap minH2 = new MinHeap(10);
        minH2.buildHeap(new int[] { 40, 30, 50, 10, 20, 60, 5 });
        minH2.display();

        // delete by index — after buildHeap the array is [5,10,40,30,20,60,50]
        // so index 2 holds the value 40 (not the original 50 from input)
        System.out.println("\n-- delete index=2 (which holds value=40 after heapify):");
        minH2.delete(2);
        minH2.display();

        // ═══════════════════════════════════════════════════════════════════
        // SECTION 4B — MAX-HEAP
        // Parent ≥ children. Root = maximum element always.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 4B: MAX-HEAP + HEAP SORT");
        System.out.println("══════════════════════════════════════════════════");

        MaxHeap maxH = new MaxHeap(15);

        System.out.println("-- individual inserts:");
        maxH.insert(20);
        maxH.insert(15);
        maxH.insert(30);
        maxH.insert(5);
        maxH.insert(10);
        maxH.insert(25);
        maxH.display();

        System.out.println("  peek()  → " + maxH.peek()); // 30

        System.out.println("\n-- extractMax sequence:");
        while (!maxH.isEmpty())
            maxH.extractMax();

        // Heap Sort — builds max-heap in O(n), sorts in O(n log n)
        System.out.println("\n-- heapSort([64, 25, 12, 22, 11]):");
        MaxHeap sorter = new MaxHeap(10);
        int[] sortArr = { 64, 25, 12, 22, 11 };
        int[] sorted = sorter.heapSort(sortArr);
        System.out.print("  Sorted (ascending): ");
        for (int i = 0; i < sortArr.length; i++)
            System.out.print(sorted[i] + (i < sortArr.length - 1 ? ", " : "\n"));

        // ─────────────────────────────────────────────────────────────────
        // COMPLEXITY SUMMARY
        // ─────────────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  TREE COMPLEXITY SUMMARY");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println(" Operation   │  BT   │  BST (avg) │ BST (worst) │  AVL   │  Heap   ");
        System.out.println(" ────────────┼───────┼────────────┼─────────────┼────────┼─────────");
        System.out.println(" insert      │ O(n)  │  O(log n)  │    O(n)     │O(log n)│ O(log n)");
        System.out.println(" search      │ O(n)  │  O(log n)  │    O(n)     │O(log n)│  O(n)   ");
        System.out.println(" delete      │ O(n)  │  O(log n)  │    O(n)     │O(log n)│ O(log n)");
        System.out.println(" min/max     │ O(n)  │  O(log n)  │    O(n)     │O(log n)│  O(1)   ");
        System.out.println(" traversal   │ O(n)  │    O(n)    │    O(n)     │  O(n)  │  O(n)   ");
        System.out.println(" buildHeap   │  N/A  │    N/A     │    N/A      │  N/A   │  O(n)   ");
        System.out.println(" heapSort    │  N/A  │    N/A     │    N/A      │  N/A   │O(nlogn) ");
        System.out.println(" Space       │ O(n)  │    O(n)    │    O(n)     │  O(n)  │  O(n)   ");
        System.out.println(" Balanced?   │  No   │    No      │    No       │  Yes   │  Yes    ");
    }
}
