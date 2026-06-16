# AVL Tree Data Structure

An **AVL Tree** (named after inventors **A**delson-**V**elsky and **L**andis) is a self-balancing Binary Search Tree (BST) where the difference in heights of left and right subtrees (the **Balance Factor**) cannot be more than 1 for all nodes.

$$\text{Balance Factor (BF)} = \text{Height(Left Subtree)} - \text{Height(Right Subtree)}$$
$$\text{AVL Invariant: } \text{BF} \in \{-1, 0, +1\}$$

If the balance factor of any node becomes $\ge 2$ or $\le -2$, the tree is rebalanced using **rotations**.

## Structure and Visual Representation

```mermaid
graph TD
    Root(("30 [bf=0]")) --> L1(("20 [bf=0]"))
    Root --> R1(("40 [bf=-1]"))
    R1 --> R2_2(("50 [bf=0]"))
    style Root fill:#8b5cf6,stroke:#333,stroke-width:2px,color:#fff
    style L1 fill:#1e293b,color:#fff
    style R2_2 fill:#1e293b,color:#fff
```

## Rotations and Rebalancing Cases

| Case | Triggering Condition | Balancing Action |
| :--- | :--- | :--- |
| **Left-Left (LL)** | Node BF > 1 & Left Child BF $\ge$ 0 | Single **Right Rotation** |
| **Right-Right (RR)** | Node BF < -1 & Right Child BF $\le$ 0 | Single **Left Rotation** |
| **Left-Right (LR)** | Node BF > 1 & Left Child BF < 0 | **Left Rotation** on left child, then **Right Rotation** on node |
| **Right-Left (RL)** | Node BF < -1 & Right Child BF > 0 | **Right Rotation** on right child, then **Left Rotation** on node |

---

## Step-by-Step Rotations

### 1. Left-Left (LL Case) - Right Rotation
A node is inserted in the left subtree of the left child, causing an imbalance.

```mermaid
graph TD
    subgraph Imbalanced (LL)
        direction TB
        z((3)) -->|BF = 2| y((2))
        y --> x((1))
        style z fill:#f43f5e,color:#fff
    end
    subgraph Balanced (Right Rotation)
        direction TB
        yb((2)) --> xb((1))
        yb --> zb((3))
        style yb fill:#06b6d4,color:#fff
    end
```

### 2. Left-Right (LR Case) - Double Rotation
A node is inserted in the right subtree of the left child.

```mermaid
graph TD
    subgraph Imbalanced (LR)
        z(("3 [BF=2]")) --> y(("1 [BF=-1]"))
        y --> x(("2"))
        style z fill:#f43f5e,color:#fff
    end
    subgraph Step 1: Left Rotate Child y
        z1((3)) --> y1((2))
        y1 --> x1((1))
    end
    subgraph Step 2: Right Rotate Parent z
        y2((2)) --> x2((1))
        y2 --> z2((3))
        style y2 fill:#06b6d4,color:#fff
    end
```

---

## Java Implementation example (Rotations)

```java
public class AVLTree {
    static class Node {
        int data, height;
        Node left, right;
        Node(int data) {
            this.data = data;
            this.height = 0;
        }
    }

    private int height(Node n) {
        return (n == null) ? -1 : n.height;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }
}
```
