package Trees;

// ═══════════════════════════════════════════════════════════════════════
//  AVL TREE (Adelson-Velsky and Landis)
//  A self-balancing BST. After every insert/delete the tree
//  rebalances itself via rotations to ensure:
//    |height(left) - height(right)| ≤ 1  at every node
//
//  Balance Factor (BF) = height(left) - height(right)
//    BF ∈ {-1, 0, +1} → balanced
//    BF =  2           → left-heavy  → right rotation needed
//    BF = -2           → right-heavy → left  rotation needed
//
//  ┌──────────────────────────────────────────────────────┐
//  │  4 Rotation Cases:                                   │
//  │  LL  (BF=+2, left child BF≥0)  → right-rotate root  │
//  │  RR  (BF=-2, right child BF≤0) → left-rotate  root  │
//  │  LR  (BF=+2, left child BF<0)  → left-rotate  left, │
//  │                                   then right-rotate  │
//  │  RL  (BF=-2, right child BF>0) → right-rotate right, │
//  │                                   then left-rotate   │
//  └──────────────────────────────────────────────────────┘
//
//  All operations: O(log n) guaranteed (balanced height).
// ═══════════════════════════════════════════════════════════════════════

public class AVLTree {

    // ── Node ─────────────────────────────────────────────────────────
    static class Node {
        int data, height;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.height = 0; // leaf height = 0
        }
    }

    private Node root;

    // ── height helper ────────────────────────────────────────────────
    private int height(Node n) {
        return (n == null) ? -1 : n.height;
    }

    // ── balance factor ───────────────────────────────────────────────
    private int bf(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    // ── update height ────────────────────────────────────────────────
    private void updateHeight(Node n) {
        if (n != null)
            n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    // ── ROTATIONS ────────────────────────────────────────────────────

    // y x
    // / \ Right-Rotate / \
    // x T3 ──────────→ T1 y
    // / \ / \
    // T1 T2 T2 T3
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        System.out.println("    [RR rotation] pivot=" + y.data + " → new root=" + x.data);
        return x;
    }

    // x y
    // / \ Left-Rotate / \
    // T1 y ──────────→ x T3
    // / \ / \
    // T2 T3 T1 T2
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        System.out.println("    [LR rotation] pivot=" + x.data + " → new root=" + y.data);
        return y;
    }

    // ── rebalance ────────────────────────────────────────────────────
    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = bf(node);

        // LL Case — right rotation
        if (balance > 1 && bf(node.left) >= 0) {
            System.out.println("    [LL case] at node=" + node.data);
            return rotateRight(node);
        }
        // LR Case — left-rotate left child, then right-rotate
        if (balance > 1 && bf(node.left) < 0) {
            System.out.println("    [LR case] at node=" + node.data);
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        // RR Case — left rotation
        if (balance < -1 && bf(node.right) <= 0) {
            System.out.println("    [RR case] at node=" + node.data);
            return rotateLeft(node);
        }
        // RL Case — right-rotate right child, then left-rotate
        if (balance < -1 && bf(node.right) > 0) {
            System.out.println("    [RL case] at node=" + node.data);
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node; // already balanced
    }

    // ── insert ───────────────────────────────────────────────────────
    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node node, int value) {
        if (node == null)
            return new Node(value);

        if (value < node.data)
            node.left = insert(node.left, value);
        else if (value > node.data)
            node.right = insert(node.right, value);
        else
            return node; // duplicate

        return rebalance(node);
    }

    // ── delete ───────────────────────────────────────────────────────
    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {
        if (node == null) {
            System.out.println("  [NOT FOUND] " + value);
            return null;
        }

        if (value < node.data)
            node.left = delete(node.left, value);
        else if (value > node.data)
            node.right = delete(node.right, value);
        else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            Node successor = minNode(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return rebalance(node);
    }

    private Node minNode(Node n) {
        return (n.left == null) ? n : minNode(n.left);
    }

    // ── search ───────────────────────────────────────────────────────
    public boolean search(int value) {
        Node cur = root;
        while (cur != null) {
            if (value == cur.data)
                return true;
            else if (value < cur.data)
                cur = cur.left;
            else
                cur = cur.right;
        }
        return false;
    }

    // ── getBalanceFactor ─────────────────────────────────────────────
    public int getBalanceFactor() {
        return bf(root);
    }

    // ── height ───────────────────────────────────────────────────────
    public int height() {
        return height(root);
    }

    // ── traversals ───────────────────────────────────────────────────
    public void inorder() {
        System.out.print("  Inorder  (sorted): ");
        inorder(root);
        System.out.println();
    }

    public void preorder() {
        System.out.print("  Preorder          : ");
        preorder(root);
        System.out.println();
    }

    private void inorder(Node n) {
        if (n == null)
            return;
        inorder(n.left);
        System.out.print(n.data + "(BF=" + bf(n) + ") ");
        inorder(n.right);
    }

    private void preorder(Node n) {
        if (n == null)
            return;
        System.out.print(n.data + " ");
        preorder(n.left);
        preorder(n.right);
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        System.out.println("  AVL structure (BF shown):");
        display(root, "", false);
    }

    private void display(Node node, String prefix, boolean isLeft) {
        if (node == null)
            return;
        System.out.println(prefix + (isLeft ? "├── " : "└── ")
                + node.data + " [h=" + node.height + " bf=" + bf(node) + "]");
        if (node.left != null || node.right != null) {
            display(node.left, prefix + (isLeft ? "│   " : "    "), true);
            display(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}
