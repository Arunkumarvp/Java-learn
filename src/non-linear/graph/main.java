public class main {

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 1 — ADJACENCY MATRIX
        //  Build this graph (undirected, unweighted):
        //
        //    0 ── 1 ── 3
        //    |    |
        //    2 ── 4
        //
        //  V=5, edges: (0,1),(0,2),(1,3),(1,4),(2,4)
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("  SECTION 1: ADJACENCY MATRIX");
        System.out.println("══════════════════════════════════════════════════");

        AdjacencyMatrix am = new AdjacencyMatrix(5);

        am.addEdge(0, 1);
        am.addEdge(0, 2);
        am.addEdge(1, 3);
        am.addEdge(1, 4);
        am.addEdge(2, 4);
        am.display();

        // edge checks
        System.out.println("  hasEdge(0,1)  → " + am.hasEdge(0, 1));  // true
        System.out.println("  hasEdge(0,3)  → " + am.hasEdge(0, 3));  // false
        System.out.println("  degree(1)     → " + am.degree(1));       // 3

        // traversals
        am.bfs(0);  // BFS from 0
        am.dfs(0);  // DFS from 0

        // properties
        System.out.println("  isConnected() → " + am.isConnected());  // true
        System.out.println("  hasCycle()    → " + am.hasCycle());     // true (0-1-4-2-0)

        // removeEdge
        System.out.println("\n-- removeEdge(0,2) and removeEdge(2,4):");
        am.removeEdge(0, 2);
        am.removeEdge(2, 4);
        am.display();
        System.out.println("  isConnected() → " + am.isConnected());  // false (2 isolated)
        am.bfs(0);

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 2 — ADJACENCY LIST
        //  Build same graph:
        //    0 ── 1 ── 2
        //    |         |
        //    3 ── 4 ── 5
        //
        //  V=6, edges: (0,1),(1,2),(0,3),(3,4),(4,5),(2,5)
        //  This forms a cycle: 0-1-2-5-4-3-0
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 2: ADJACENCY LIST");
        System.out.println("══════════════════════════════════════════════════");

        AdjacencyList al = new AdjacencyList(6);

        al.addEdge(0, 1);
        al.addEdge(1, 2);
        al.addEdge(0, 3);
        al.addEdge(3, 4);
        al.addEdge(4, 5);
        al.addEdge(2, 5);
        al.display();

        // edge checks
        System.out.println("  hasEdge(1,2)  → " + al.hasEdge(1, 2));  // true
        System.out.println("  hasEdge(0,5)  → " + al.hasEdge(0, 5));  // false
        System.out.println("  degree(0)     → " + al.degree(0));       // 2

        // traversals — BFS vs DFS
        al.bfs(0);           // level by level: 0 1 3 2 4 5
        al.dfs(0);           // deep dive:      0 1 2 5 4 3
        al.dfsIterative(0);  // iterative DFS using explicit stack

        // properties
        System.out.println("  isConnected() → " + al.isConnected());  // true
        System.out.println("  hasCycle()    → " + al.hasCycle());     // true

        // shortest path (BFS-based, unweighted)
        al.shortestPath(0, 5);  // 0 → 3 → 4 → 5 or 0 → 1 → 2 → 5 (2 edges? no, 3)
        al.shortestPath(0, 2);  // 0 → 1 → 2
        al.shortestPath(3, 2);  // 3 → 0 → 1 → 2 or 3 → 4 → 5 → 2

        // remove edge and check disconnected
        System.out.println("\n-- After removeEdge(0,3) and removeEdge(4,5):");
        al.removeEdge(0, 3);
        al.removeEdge(4, 5);
        al.display();
        System.out.println("  isConnected()          → " + al.isConnected());

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 3 — DISCONNECTED GRAPH + CONNECTED COMPONENTS
        //  Graph with 3 separate components:
        //    Component 0: 0-1-2
        //    Component 1: 3-4
        //    Component 2: 5
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 3: DISCONNECTED GRAPH + CONNECTED COMPONENTS");
        System.out.println("══════════════════════════════════════════════════");

        AdjacencyList disconnected = new AdjacencyList(6);
        disconnected.addEdge(0, 1);
        disconnected.addEdge(1, 2);
        disconnected.addEdge(3, 4);
        // vertex 5 is isolated
        disconnected.display();

        System.out.println("  isConnected()       → " + disconnected.isConnected());  // false
        System.out.println("  hasCycle()          → " + disconnected.hasCycle());     // false
        System.out.print("  connectedComponents (DFS from each unvisited vertex):\n  ");
        int components = disconnected.connectedComponents();
        System.out.println("  Total components    → " + components);  // 3

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 4 — CYCLE DETECTION DEMO
        //  1. Simple acyclic (tree-like): 0-1, 0-2, 1-3  → no cycle
        //  2. Add back edge 3-0                           → cycle exists
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 4: CYCLE DETECTION");
        System.out.println("══════════════════════════════════════════════════");

        AdjacencyList acyclic = new AdjacencyList(4);
        acyclic.addEdge(0, 1);
        acyclic.addEdge(0, 2);
        acyclic.addEdge(1, 3);
        acyclic.display();
        System.out.println("  hasCycle() [acyclic] → " + acyclic.hasCycle());  // false

        AdjacencyList cyclic = new AdjacencyList(4);
        cyclic.addEdge(0, 1);
        cyclic.addEdge(0, 2);
        cyclic.addEdge(1, 3);
        cyclic.addEdge(3, 0); // back edge → creates cycle 0-1-3-0
        cyclic.display();
        System.out.println("  hasCycle() [cyclic]  → " + cyclic.hasCycle());   // true

        // ─────────────────────────────────────────────────────────────────
        //  COMPLEXITY SUMMARY
        // ─────────────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  GRAPH COMPLEXITY SUMMARY");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println(" Operation          │ Adj Matrix  │  Adj List   ");
        System.out.println(" ───────────────────┼─────────────┼─────────────");
        System.out.println(" Space              │   O(V²)     │   O(V+E)    ");
        System.out.println(" addEdge            │   O(1)      │   O(1)      ");
        System.out.println(" removeEdge         │   O(1)      │   O(E)      ");
        System.out.println(" hasEdge            │   O(1)      │   O(degree) ");
        System.out.println(" BFS / DFS          │   O(V²)     │   O(V+E)    ");
        System.out.println(" isConnected        │   O(V²)     │   O(V+E)    ");
        System.out.println(" hasCycle           │   O(V²)     │   O(V+E)    ");
        System.out.println(" shortestPath (BFS) │   O(V²)     │   O(V+E)    ");
        System.out.println(" Best for           │ dense graphs│ sparse graphs");
        System.out.println("\n  V = vertices, E = edges");

        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  NON-LINEAR DATA STRUCTURES — FULL SUMMARY");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println(" Structure   │  Type          │  Key property                  ");
        System.out.println(" ────────────┼────────────────┼────────────────────────────────");
        System.out.println(" Binary Tree │  Hierarchical  │  max 2 children, no ordering   ");
        System.out.println(" BST         │  Hierarchical  │  left<node<right               ");
        System.out.println(" AVL Tree    │  Hierarchical  │  self-balancing BST            ");
        System.out.println(" Min-Heap    │  Complete Tree │  parent≤children, O(1) min     ");
        System.out.println(" Max-Heap    │  Complete Tree │  parent≥children, O(1) max     ");
        System.out.println(" Graph (AM)  │  Network       │  O(1) edge check, O(V²) space  ");
        System.out.println(" Graph (AL)  │  Network       │  O(V+E) space, best traversal  ");
    }
}
