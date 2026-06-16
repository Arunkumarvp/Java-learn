# Binary Tree Data Structure

A **Binary Tree** is a non-linear hierarchical data structure in which each node has at most two children, referred to as the **left child** and the **right child**.

## Tree Anatomy and Structure

```mermaid
graph TD
    Root(("Root: 1")) --> L1(("Left Child: 2"))
    Root --> R1(("Right Child: 3"))
    L1 --> L2_1(("Leaf: 4"))
    L1 --> L2_2(("Leaf: 5"))
    R1 --> L2_3(("Leaf: 6"))
    R1 --> L2_4(("Leaf: 7"))
    style Root fill:#8b5cf6,stroke:#333,stroke-width:2px,color:#fff
    style L2_1 fill:#1e293b,color:#fff
    style L2_2 fill:#1e293b,color:#fff
    style L2_3 fill:#1e293b,color:#fff
    style L2_4 fill:#1e293b,color:#fff
```

## Tree Traversals

Unlike linear data structures, trees can be traversed in several ways:

| Traversal | Logic / Order | Complexity (Time/Space) |
| :--- | :--- | :---: |
| **Preorder** | Node $\rightarrow$ Left $\rightarrow$ Right | $O(N)$ / $O(H)$ |
| **Inorder** | Left $\rightarrow$ Node $\rightarrow$ Right | $O(N)$ / $O(H)$ |
| **Postorder** | Left $\rightarrow$ Right $\rightarrow$ Node | $O(N)$ / $O(H)$ |
| **Level-Order (BFS)** | Level by level, Left to Right | $O(N)$ / $O(N)$ |

*Note: $N$ is the number of nodes, $H$ is the height of the tree.*

---

## Step-by-Step Traversal Diagrams

Given the tree structure above:

### 1. Inorder Traversal (Left, Node, Right)
Visits elements in bottom-up left-to-right projection order: `4 → 2 → 5 → 1 → 6 → 3 → 7`.

```mermaid
graph LR
    step1("Start at Root: 1") --> step2("Go Left: 2")
    step2 --> step3("Go Left: 4")
    step3 --> visit4("Visit Leaf 4")
    visit4 --> visit2("Up & Visit 2")
    visit2 --> visit5("Go Right & Visit Leaf 5")
    visit5 --> visit1("Up & Visit Root 1")
    visit1 --> visitNext("Proceed to Right Subtree...")
```

### 2. Level-Order Traversal (BFS)
Traverses level by level from top to bottom.

```mermaid
graph TD
    subgraph Level 0
        1((1))
    end
    subgraph Level 1
        2((2)) --> 3((3))
    end
    subgraph Level 2
        4((4)) --> 5((5)) --> 6((6)) --> 7((7))
    end
    style 1 fill:#8b5cf6,color:#fff
    style 2 fill:#06b6d4,color:#fff
    style 3 fill:#06b6d4,color:#fff
```

---

## Tree Metrics & Operations

Beyond traversal, several recursive operations are fundamental to understanding the tree's state.

### 1. Height of a Tree (`height()`)
The height is the number of edges on the longest path from the root to a leaf. It is calculated recursively: $1 + \max(\text{height}(\text{left}), \text{height}(\text{right}))$.

```mermaid
graph TD
    subgraph Height Calculation
        direction TB
        R1(("Root: height=2")) --> L1(("L: height=1"))
        R1 --> R2(("R: height=0"))
        L1 --> L3(("Leaf: height=0"))
        style R1 fill:#f43f5e,color:#fff
        style L1 fill:#8b5cf6,color:#fff
    end
```

### 2. Count Nodes & Leaves
- **`countNodes()`**: Returns the total number of nodes in the tree ($1 + \text{leftCount} + \text{rightCount}$).
- **`countLeaves()`**: Returns the number of nodes that have both left and right children as `null`.

```mermaid
graph LR
    subgraph Leaf Counting
        A(("Root")) --> B(("Left"))
        A --> C(("Right [Leaf]"))
        B --> D(("Left [Leaf]"))
        B --> E(("Right [Leaf]"))
        style C fill:#10b981,color:#fff
        style D fill:#10b981,color:#fff
        style E fill:#10b981,color:#fff
    end
```

---

## Java Implementation Examples

```java
public class BinaryTree {
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    private Node root;

    // Inorder Traversal Helper
    public void inorder() {
        inorderHelper(root);
    }

    private void inorderHelper(Node node) {
        if (node == null) return;
        inorderHelper(node.left);
        System.out.print(node.data + " ");
        inorderHelper(node.right);
    }
}
```
