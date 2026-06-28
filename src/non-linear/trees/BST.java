package Trees;

// ═══════════════════════════════════════════════════════════════════════
//  BINARY SEARCH TREE (BST)
//  An ordered binary tree with the invariant:
//    left.data  <  node.data  <  right.data
//
//  ┌──────────────────────────────────────────────────────┐
//  │  Operations (average / worst-case when unbalanced):  │
//  │    insert   → O(log n) / O(n)                        │
//  │    search   → O(log n) / O(n)                        │
//  │    delete   → O(log n) / O(n)                        │
//  │    min/max  → O(log n) / O(n)                        │
//  │    inorder  → O(n) — always gives sorted output      │
//  │                                                      │
//  │  Delete has 3 cases:                                 │
//  │    1. Leaf node            → just remove             │
//  │    2. Node with 1 child    → bypass (link parent)    │
//  │    3. Node with 2 children → replace with inorder    │
//  │                              successor (min of right) │
//  └──────────────────────────────────────────────────────┘
// ═══════════════════════════════════════════════════════════════════════

class BST {

    // ── Node ─────────────────────────────────────────────────────────
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    private Node root;

    // ── insert ───────────────────────────────────────────────────────
    // Recursively finds the correct leaf position. Duplicate = ignored.
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
        // Duplicate: do nothing
        return node;
    }

    // ── search ───────────────────────────────────────────────────────
    public boolean search(int value) {
        return search(root, value);
    }

    private boolean search(Node node, int value) {
        if (node == null)
            return false;
        if (value == node.data)
            return true;
        return (value < node.data)
                ? search(node.left, value)
                : search(node.right, value);
    }

    // ── min / max ────────────────────────────────────────────────────
    // min → leftmost leaf; max → rightmost leaf
    public int min() {
        if (root == null)
            throw new RuntimeException("BST is empty");
        return min(root).data;
    }

    private Node min(Node node) {
        return (node.left == null) ? node : min(node.left);
    }

    public int max() {
        if (root == null)
            throw new RuntimeException("BST is empty");
        Node cur = root;
        while (cur.right != null)
            cur = cur.right;
        return cur.data;
    }

    // ── height ───────────────────────────────────────────────────────
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null)
            return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // ── delete ───────────────────────────────────────────────────────
    // Three cases handled:
    // 1. Leaf → return null
    // 2. One child → return the non-null child
    // 3. Two children → find inorder successor (min of right subtree),
    // copy its value, delete the successor
    public void delete(int value) {
        root = delete(root, value);
    }

    private Node delete(Node node, int value) {
        if (node == null) {
            System.out.println("  [NOT FOUND] " + value + " not in BST.");
            return null;
        }
        if (value < node.data) {
            node.left = delete(node.left, value);
        } else if (value > node.data) {
            node.right = delete(node.right, value);
        } else {
            // FOUND — apply the three cases
            if (node.left == null)
                return node.right; // case 1 & 2
            if (node.right == null)
                return node.left; // case 2
            // Case 3: two children — replace with inorder successor
            Node successor = min(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return node;
    }

    // ── inorder successor ────────────────────────────────────────────
    // The next greater value after a given node (used internally in delete).
    public int inorderSuccessor(int value) {
        Node result = null;
        Node cur = root;
        while (cur != null) {
            if (value < cur.data) {
                result = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return (result == null) ? -1 : result.data;
    }

    // ── count / leaves ───────────────────────────────────────────────
    public int countNodes() {
        return count(root);
    }

    private int count(Node n) {
        return (n == null) ? 0 : 1 + count(n.left) + count(n.right);
    }

    public int countLeaves() {
        return leaves(root);
    }

    private int leaves(Node n) {
        if (n == null)
            return 0;
        if (n.left == null && n.right == null)
            return 1;
        return leaves(n.left) + leaves(n.right);
    }

    // ── TRAVERSALS ────────────────────────────────────────────────────
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

    public void postorder() {
        System.out.print("  Postorder         : ");
        postorder(root);
        System.out.println();
    }

    private void inorder(Node n) {
        if (n == null)
            return;
        inorder(n.left);
        System.out.print(n.data + " ");
        inorder(n.right);
    }

    private void preorder(Node n) {
        if (n == null)
            return;
        System.out.print(n.data + " ");
        preorder(n.left);
        preorder(n.right);
    }

    private void postorder(Node n) {
        if (n == null)
            return;
        postorder(n.left);
        postorder(n.right);
        System.out.print(n.data + " ");
    }

    // ── isEmpty / display ─────────────────────────────────────────────
    public boolean isEmpty() {
        return root == null;
    }

    public void display() {
        System.out.println("  BST structure:");
        display(root, "", false);
    }

    private void display(Node node, String prefix, boolean isLeft) {
        if (node == null)
            return;
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.data);
        if (node.left != null || node.right != null) {
            display(node.left, prefix + (isLeft ? "│   " : "    "), true);
            display(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }
}
