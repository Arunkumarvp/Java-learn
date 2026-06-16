# Graph Data Structure

A **Graph** is a non-linear data structure consisting of two components:
1. **Vertices (or Nodes)**: The entities.
2. **Edges**: The links connecting pairs of vertices. Edges can be directed or undirected, and weighted or unweighted.

## Structure and Visual Representation

```mermaid
graph LR
    subgraph Undirected Graph
        A((A)) --- B((B))
        A --- C((C))
        B --- D((D))
        C --- D
    end
    subgraph Directed Graph
        direction LR
        E((E)) --> F((F))
        F --> G((G))
        G --> E
        G --> H((H))
    end
    style A fill:#8b5cf6,color:#fff
    style E fill:#06b6d4,color:#fff
```

---

## Graph Representations

### 1. Adjacency Matrix
A 2D array where cell `[i][j] = 1` indicates an edge between vertex `i` and `j`. Excellent for dense graphs.

### 2. Adjacency List
An array of lists, where list at index `i` contains neighbors of vertex `i`. Memory efficient for sparse graphs.

```mermaid
graph LR
    subgraph Adjacency List for Vertex A
        nodeA[Vertex A] --> neighborB[Neighbor B]
        neighborB --> neighborC[Neighbor C]
        style nodeA fill:#8b5cf6,color:#fff
    end
```

---

## Graph Traversals

| Traversal | Logic / Strategy | Data Structure Used | Time / Space Complexity |
| :--- | :--- | :---: | :---: |
| **Breadth-First Search (BFS)** | Explore level-by-level (nearest neighbors first) | **Queue** | $O(V + E)$ / $O(V)$ |
| **Depth-First Search (DFS)** | Explore deep along branches before backtracking | **Stack / Recursion** | $O(V + E)$ / $O(V)$ |

---

## Step-by-Step Traversals

### 1. BFS Traversal
Exploring neighbors level-by-level starting from `A`:
1. Visit `A` $\rightarrow$ Queue neighbors: `[B, C]`.
2. Dequeue `B` $\rightarrow$ Visit `B` $\rightarrow$ Queue neighbors: `[D]`. Queue is now `[C, D]`.
3. Dequeue `C` $\rightarrow$ Visit `C` $\rightarrow$ Queue neighbors: (all visited).
4. Dequeue `D` $\rightarrow$ Visit `D`.
*Result order: `A → B → C → D`*

```mermaid
graph TD
    step1("Start A: Queue=[B,C]") --> step2("Visit B: Queue=[C,D]")
    step2 --> step3("Visit C: Queue=[D]")
    step3 --> step4("Visit D: Queue=[]")
```

### 2. DFS Traversal (Deep Dive)
Going down a single path completely before backtracking. Starting from `A`:
`A → B → D → C` (then backtracks to `A`).

```mermaid
graph LR
    A((A)) -->|1| B((B))
    B -->|2| D((D))
    D -->|3| C((C))
    style A fill:#8b5cf6,color:#fff
    style C fill:#06b6d4,color:#fff
```

---

## Advanced Graph Algorithms

Based on your implementation, here are diagrams explaining the logic behind your advanced graph operations.

### 3. Shortest Path (Unweighted Graph)
To find the shortest path from a source node to all other nodes in an unweighted graph, we use **BFS**. As we visit each neighbor, we record its distance as `distance[parent] + 1` and track its predecessor.

```mermaid
graph LR
    subgraph Distance Calculation
        S(("Start (Dist: 0)")) -->|Dist: 1| A(("A (Dist: 1)"))
        S -->|Dist: 1| B(("B (Dist: 1)"))
        A -->|Dist: 2| C(("C (Dist: 2)"))
        B -->|Dist: 2| C
        style S fill:#10b981,color:#fff
        style C fill:#f43f5e,color:#fff
    end
```

### 4. Cycle Detection (`hasCycle`)
A cycle exists if we visit a node that is already marked as `visited`, and it is **not** the parent of the current node (in undirected graphs).

```mermaid
graph TD
    subgraph Cycle Exists!
        A(("A")) --> B(("B"))
        B --> C(("C"))
        C -.->|Back-edge| A
        style C fill:#f43f5e,color:#fff
    end
```

### 5. Connected Components (`connectedComponents`)
If a graph is disconnected, a single BFS/DFS will not visit all nodes. We loop through all vertices, and for every unvisited vertex, we launch a new traversal, counting it as a new component.

```mermaid
graph LR
    subgraph Component 1
        1(("1")) --- 2(("2"))
    end
    subgraph Component 2
        3(("3")) --- 4(("4"))
        4 --- 5(("5"))
    end
    subgraph Component 3
        6(("6"))
    end
```

---

## Java Implementation Example (Adjacency List)

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
    private int numVertices;
    private ArrayList<ArrayList<Integer>> adjList;

    public Graph(int vertices) {
        this.numVertices = vertices;
        this.adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest) {
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // for undirected graph
    }

    public void bfs(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.print(curr + " ");

            for (int neighbor : adjList.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
```
