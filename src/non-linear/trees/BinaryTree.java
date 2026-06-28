
package Trees;

import java.util.ArrayDeque;
import java.util.Queue;

// ═══════════════════════════════════════════════════════════════════════
//  BINARY TREE (BT)
//  Each node has at most 2 children (left and right).
//  No ordering constraint — any value can go anywhere.
//
//  ┌──────────────────────────────────────────────────────┐
//  │  Key Properties:                                     │
//  │  • Height h → max nodes = 2^(h+1) - 1               │
//  │  • Height h → max leaves = 2^h                       │
//  │  • Full BT  : every node has 0 or 2 children         │
//  │  • Complete : all levels full except last (left-fill) │
//  │  • Perfect  : all leaves at same level               │
//  └──────────────────────────────────────────────────────┘
//
//  Operations & Complexity:
//    insert (level-order)  → O(n)
//    search                → O(n)
//    height                → O(n)
//    countNodes            → O(n)
//    traversals            → O(n) each
// ═══════════════════════════════════════════════════════════════════════

public class BinaryTree {

    // ── Node ─────────────────────────────────────────────────────────
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    private Node root;

    // ── insert (level-order / BFS fill) ──────────────────────────────
    // Inserts at the first available position using level-order scan.
    // This keeps the tree complete (dense from top-left).
    public void insert(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            root = newNode;
            System.out.println("  insert(" + value + ") → root");
            return;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.left == null) {
                cur.left = newNode;
                System.out.println("  insert(" + value + ") → left  of " + cur.data);
                return;
            } else {
                q.offer(cur.left);
            }
            if (cur.right == null) {
                cur.right = newNode;
                System.out.println("  insert(" + value + ") → right of " + cur.data);
                return;
            } else {
                q.offer(cur.right);
            }
        }
    }

    // ── search ───────────────────────────────────────────────────────
    // Linear scan via level-order — no ordering guarantee in plain BT.
    public boolean search(int value) {
        if (root == null)
            return false;
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.data == value)
                return true;
            if (cur.left != null)
                q.offer(cur.left);
            if (cur.right != null)
                q.offer(cur.right);
        }
        return false;
    }

    // ── height ───────────────────────────────────────────────────────
    // Height = number of edges on the longest root-to-leaf path.
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null)
            return -1; // empty tree height = -1
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // ── countNodes ───────────────────────────────────────────────────
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null)
            return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    // ── countLeaves ──────────────────────────────────────────────────
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        return countLeaves(node.left) + countLeaves(node.right);
    }

    // ── TRAVERSALS ────────────────────────────────────────────────────
    // Inorder (Left → Root → Right) → sorted output for BST
    // Preorder (Root → Left → Right) → used for tree cloning
    // Postorder (Left → Right → Root) → used for tree deletion
    // Level-order (BFS) → breadth-first, level by level

    public void inorder() {
        System.out.print("  Inorder    (L→N→R): ");
        inorder(root);
        System.out.println();
    }

    public void preorder() {
        System.out.print("  Preorder   (N→L→R): ");
        preorder(root);
        System.out.println();
    }

    public void postorder() {
        System.out.print("  Postorder  (L→R→N): ");
        postorder(root);
        System.out.println();
    }

    private void inorder(Node node) {
        if (node == null)
            return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    private void preorder(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    private void postorder(Node node) {
        if (node == null)
            return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    // Level-order (BFS) — prints level by level with labels
    public void levelOrder() {
        if (root == null) {
            System.out.println("  [empty]");
            return;
        }
        System.out.println("  Level-order (BFS):");
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            System.out.print("    Level " + level + ": ");
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                System.out.print(cur.data + " ");
                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
            System.out.println();
            level++;
        }
    }

    // ── display (visual tree) ─────────────────────────────────────────
    public void display() {
        System.out.println("  Tree structure:");
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

    // ── isEmpty ──────────────────────────────────────────────────────
    public boolean isEmpty() {
        return root == null;
    }
}
