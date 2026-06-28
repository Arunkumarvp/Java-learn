
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// ═══════════════════════════════════════════════════════════════════════
//  GRAPH REPRESENTATIONS
//
//  A Graph G = (V, E): V = vertices (nodes), E = edges (connections).
//
//  ┌──────────────────────────────────────────────────────────────────┐
//  │  Types of Graphs:                                                │
//  │  • Undirected  : edges have no direction (A—B means B—A)        │
//  │  • Directed    : edges have direction (A→B does NOT mean B→A)   │
//  │  • Weighted    : edges carry a cost/weight                       │
//  │  • Unweighted  : all edges treated equally                       │
//  │  • Cyclic      : contains at least one cycle                     │
//  │  • Acyclic     : no cycles (DAG = Directed Acyclic Graph)        │
//  │  • Connected   : every vertex reachable from every other         │
//  │  • Disconnected: some vertices unreachable from others           │
//  └──────────────────────────────────────────────────────────────────┘
//
//  Two standard representations:
//
//  1. ADJACENCY MATRIX  — 2D array [V][V]
//     matrix[i][j] = 1 (edge exists) or 0 (no edge)
//     Space: O(V²)  | Edge check: O(1) | Add edge: O(1)
//     Best for: dense graphs, quick edge-existence queries.
//
//  2. ADJACENCY LIST   — array of lists
//     list[i] = list of all neighbours of vertex i
//     Space: O(V+E) | Edge check: O(degree) | Add edge: O(1)
//     Best for: sparse graphs, traversal-heavy algorithms.
// ═══════════════════════════════════════════════════════════════════════

// ────────────────────────────────────────────────────────────────────────
//  TYPE 1 — ADJACENCY MATRIX (Undirected, Unweighted)
// ────────────────────────────────────────────────────────────────────────
class AdjacencyMatrix {
    private final int V; // number of vertices
    private final int[][] matrix;

    public AdjacencyMatrix(int vertices) {
        this.V = vertices;
        this.matrix = new int[V][V];
    }

    // ── addEdge ──────────────────────────────────────────────────────
    // Undirected: set both matrix[u][v] and matrix[v][u] = 1
    public void addEdge(int u, int v) {
        if (!valid(u) || !valid(v)) {
            System.out.println("  [INVALID] vertex out of range.");
            return;
        }
        matrix[u][v] = 1;
        matrix[v][u] = 1; // remove this line for directed graph
        System.out.println("  addEdge(" + u + ", " + v + ")");
    }

    // ── removeEdge ───────────────────────────────────────────────────
    public void removeEdge(int u, int v) {
        if (!valid(u) || !valid(v))
            return;
        matrix[u][v] = 0;
        matrix[v][u] = 0;
        System.out.println("  removeEdge(" + u + ", " + v + ")");
    }

    // ── hasEdge ──────────────────────────────────────────────────────
    public boolean hasEdge(int u, int v) {
        return valid(u) && valid(v) && matrix[u][v] == 1;
    }

    // ── degree ───────────────────────────────────────────────────────
    // Number of edges connected to vertex v
    public int degree(int v) {
        if (!valid(v))
            return -1;
        int deg = 0;
        for (int j = 0; j < V; j++)
            deg += matrix[v][j];
        return deg;
    }

    // ── BFS (Breadth-First Search) ────────────────────────────────────
    // Explores level by level (closest neighbours first).
    // Uses a queue — FIFO order ensures breadth-first expansion.
    // Time: O(V²) for matrix | Space: O(V)
    public void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);
        System.out.print("  BFS from " + start + ": ");
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int j = 0; j < V; j++) {
                if (matrix[node][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    q.offer(j);
                }
            }
        }
        System.out.println();
    }

    // ── DFS (Depth-First Search) ──────────────────────────────────────
    // Explores as deep as possible along each branch before backtracking.
    // Uses recursion (implicit call stack) — LIFO order.
    // Time: O(V²) for matrix | Space: O(V)
    public void dfs(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("  DFS from " + start + ": ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int j = 0; j < V; j++) {
            if (matrix[node][j] == 1 && !visited[j])
                dfsHelper(j, visited);
        }
    }

    // ── isConnected ──────────────────────────────────────────────────
    // Run BFS/DFS from vertex 0. If all vertices visited → connected.
    public boolean isConnected() {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        visited[0] = true;
        q.offer(0);
        int count = 1;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int j = 0; j < V; j++) {
                if (matrix[node][j] == 1 && !visited[j]) {
                    visited[j] = true;
                    q.offer(j);
                    count++;
                }
            }
        }
        return count == V;
    }

    // ── hasCycle (Undirected) ────────────────────────────────────────
    // Use BFS. If we visit a neighbour that is already visited and is
    // NOT the parent we came from → cycle detected.
    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (bfsCycleCheck(i, visited))
                    return true;
            }
        }
        return false;
    }

    private boolean bfsCycleCheck(int start, boolean[] visited) {
        int[] parent = new int[V];
        java.util.Arrays.fill(parent, -1);
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int j = 0; j < V; j++) {
                if (matrix[node][j] == 1) {
                    if (!visited[j]) {
                        visited[j] = true;
                        parent[j] = node;
                        q.offer(j);
                    } else if (j != parent[node]) {
                        return true; // back edge found → cycle!
                    }
                }
            }
        }
        return false;
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        System.out.println("  Adjacency Matrix (" + V + "×" + V + "):");
        System.out.print("      ");
        for (int i = 0; i < V; i++)
            System.out.printf("%3d", i);
        System.out.println();
        System.out.print("      ");
        for (int i = 0; i < V; i++)
            System.out.print("---");
        System.out.println();
        for (int i = 0; i < V; i++) {
            System.out.printf("  %2d |", i);
            for (int j = 0; j < V; j++)
                System.out.printf("%3d", matrix[i][j]);
            System.out.println();
        }
    }

    private boolean valid(int v) {
        return v >= 0 && v < V;
    }

    public int vertices() {
        return V;
    }
}

// ────────────────────────────────────────────────────────────────────────
// TYPE 2 — ADJACENCY LIST (Undirected, Unweighted)
// ────────────────────────────────────────────────────────────────────────
class AdjacencyList {
    private final int V;
    private final List<List<Integer>> adj;

    public AdjacencyList(int vertices) {
        this.V = vertices;
        this.adj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            adj.add(new ArrayList<>());
    }

    // ── addEdge ──────────────────────────────────────────────────────
    public void addEdge(int u, int v) {
        if (!valid(u) || !valid(v)) {
            System.out.println("  [INVALID] vertex out of range.");
            return;
        }
        adj.get(u).add(v);
        adj.get(v).add(u); // remove for directed graph
        System.out.println("  addEdge(" + u + ", " + v + ")");
    }

    // ── removeEdge ───────────────────────────────────────────────────
    public void removeEdge(int u, int v) {
        adj.get(u).remove(Integer.valueOf(v));
        adj.get(v).remove(Integer.valueOf(u));
        System.out.println("  removeEdge(" + u + ", " + v + ")");
    }

    // ── hasEdge ──────────────────────────────────────────────────────
    public boolean hasEdge(int u, int v) {
        return valid(u) && adj.get(u).contains(v);
    }

    // ── degree ───────────────────────────────────────────────────────
    public int degree(int v) {
        return valid(v) ? adj.get(v).size() : -1;
    }

    // ── BFS ──────────────────────────────────────────────────────────
    // Time: O(V+E) for list | Space: O(V)
    public void bfs(int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.offer(start);
        System.out.print("  BFS from " + start + ": ");
        while (!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            for (int neighbour : adj.get(node)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    q.offer(neighbour);
                }
            }
        }
        System.out.println();
    }

    // ── DFS (recursive) ──────────────────────────────────────────────
    // Time: O(V+E) for list | Space: O(V)
    public void dfs(int start) {
        boolean[] visited = new boolean[V];
        System.out.print("  DFS from " + start + ": ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(int node, boolean[] visited) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbour : adj.get(node)) {
            if (!visited[neighbour])
                dfsHelper(neighbour, visited);
        }
    }

    // ── DFS (iterative, using explicit stack) ─────────────────────────
    public void dfsIterative(int start) {
        boolean[] visited = new boolean[V];
        java.util.Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        System.out.print("  DFS iterative from " + start + ": ");
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                System.out.print(node + " ");
                for (int neighbour : adj.get(node))
                    if (!visited[neighbour])
                        stack.push(neighbour);
            }
        }
        System.out.println();
    }

    // ── isConnected ──────────────────────────────────────────────────
    public boolean isConnected() {
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        visited[0] = true;
        q.offer(0);
        int count = 1;
        while (!q.isEmpty()) {
            int node = q.poll();
            for (int nb : adj.get(node)) {
                if (!visited[nb]) {
                    visited[nb] = true;
                    q.offer(nb);
                    count++;
                }
            }
        }
        return count == V;
    }

    // ── hasCycle (Undirected, DFS-based) ─────────────────────────────
    public boolean hasCycle() {
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            if (!visited[i] && dfsCycleCheck(i, -1, visited))
                return true;
        return false;
    }

    private boolean dfsCycleCheck(int node, int parent, boolean[] visited) {
        visited[node] = true;
        for (int nb : adj.get(node)) {
            if (!visited[nb]) {
                if (dfsCycleCheck(nb, node, visited))
                    return true;
            } else if (nb != parent) {
                return true; // back edge → cycle
            }
        }
        return false;
    }

    // ── connectedComponents ──────────────────────────────────────────
    // Count how many disconnected subgraphs exist.
    public int connectedComponents() {
        boolean[] visited = new boolean[V];
        int components = 0;
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsHelper(i, visited);
                components++;
                System.out.println(" ← component " + components);
            }
        }
        return components;
    }

    // ── shortestPath (BFS-based, unweighted) ─────────────────────────
    // BFS guarantees shortest path in terms of number of edges.
    public void shortestPath(int src, int dest) {
        int[] dist = new int[V];
        int[] prev = new int[V];
        java.util.Arrays.fill(dist, -1);
        java.util.Arrays.fill(prev, -1);
        boolean[] visited = new boolean[V];
        Queue<Integer> q = new ArrayDeque<>();
        dist[src] = 0;
        visited[src] = true;
        q.offer(src);

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int nb : adj.get(node)) {
                if (!visited[nb]) {
                    visited[nb] = true;
                    dist[nb] = dist[node] + 1;
                    prev[nb] = node;
                    q.offer(nb);
                }
            }
        }

        if (dist[dest] == -1) {
            System.out.println("  shortestPath(" + src + "→" + dest + "): No path.");
            return;
        }

        // Reconstruct path
        java.util.Deque<Integer> path = new ArrayDeque<>();
        for (int at = dest; at != -1; at = prev[at])
            path.push(at);
        System.out.print("  shortestPath(" + src + "→" + dest + ") [" + dist[dest] + " edges]: ");
        System.out.println(path);
    }

    // ── display ──────────────────────────────────────────────────────
    public void display() {
        System.out.println("  Adjacency List:");
        for (int i = 0; i < V; i++)
            System.out.println("    " + i + " → " + adj.get(i));
    }

    private boolean valid(int v) {
        return v >= 0 && v < V;
    }

    public int vertices() {
        return V;
    }
}
