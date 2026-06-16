# Binary Search Tree (BST)

A **Binary Search Tree (BST)** is an ordered binary tree that maintains a strict binary search invariant:
* The left subtree of a node contains only nodes with keys **less than** the node's key.
* The right subtree of a node contains only nodes with keys **greater than** the node's key.
* The left and right subtrees must also be binary search trees.

## Structure and Visual Representation

```mermaid
graph TD
    Root((50)) --> L1((30))
    Root --> R1((70))
    L1 --> L2_1((20))
    L1 --> L2_2((40))
    R1 --> L2_3((60))
    R1 --> L2_4((80))
    style Root fill:#8b5cf6,stroke:#333,stroke-width:2px,color:#fff
    style L2_1 fill:#1e293b,color:#fff
    style L2_4 fill:#1e293b,color:#fff
```

## BST Operations & Complexity

| Operation | Best Case | Average Case | Worst Case (Skewed Tree) | Space Complexity |
| :--- | :---: | :---: | :---: | :---: |
| **Search** | $O(1)$ | $O(\log N)$ | $O(N)$ | $O(H)$ (recursion stack) |
| **Insert** | $O(1)$ | $O(\log N)$ | $O(N)$ | $O(H)$ (recursion stack) |
| **Delete** | $O(1)$ | $O(\log N)$ | $O(N)$ | $O(H)$ (recursion stack) |

---

## Step-by-Step Operations

### 1. Insertion Operation
Inserting `35` into the BST:
1. Start at `50` $\rightarrow$ $35 < 50$, go left to `30`.
2. Compare with `30` $\rightarrow$ $35 > 30$, go right to `40`.
3. Compare with `40` $\rightarrow$ $35 < 40$, go left (position is null, insert).

```mermaid
graph TD
    subgraph Insert Path
        n50((50)) -->|35 < 50| n30((30))
        n50 --> n70((70))
        n30 --> n20((20))
        n30 -->|35 > 30| n40((40))
        n40 -->|35 < 40| n35(("New: 35"))
        style n35 fill:#06b6d4,color:#fff
    end
```

### 2. Deletion Operation (Node with 2 Children)
Deleting `30` from the tree. It is replaced by its **inorder successor** (the smallest value in its right subtree, which is `40`).

```mermaid
graph LR
    subgraph Before Deletion
        direction TB
        d50((50)) --> d30((30))
        d30 --> d20((20))
        d30 --> d40((40))
        style d30 fill:#f43f5e,color:#fff
        style d40 fill:#06b6d4,color:#fff
    end
    subgraph After Deletion
        direction TB
        a50((50)) --> a40((40))
        a40 --> a20((20))
    end
```

### 3. Finding Min and Max Values
Because of the BST invariant, finding the minimum and maximum values does not require searching the whole tree.
- **`min()`**: Simply traverse down the **left** children until you reach a node with no left child.
- **`max()`**: Simply traverse down the **right** children until you reach a node with no right child.

```mermaid
graph TD
    subgraph Min and Max Tracking
        R(("Root: 50")) --> L1(("L: 30"))
        R --> R1(("R: 70"))
        L1 --> L2(("Min: 20"))
        R1 --> R2(("Max: 80"))
        style L2 fill:#10b981,color:#fff
        style R2 fill:#f43f5e,color:#fff
    end
```

---

## Java Implementation Example

```java
public class BST {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    private Node root;

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private Node insertRec(Node root, int value) {
        if (root == null) {
            return new Node(value);
        }
        if (value < root.data) {
            root.left = insertRec(root.left, value);
        } else if (value > root.data) {
            root.right = insertRec(root.right, value);
        }
        return root;
    }
}
```
