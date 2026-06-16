# Recursion

**Recursion** is a method where the solution to a problem depends on solutions to smaller instances of the same problem. A recursive function calls itself directly or indirectly.

## Core Concepts

Every recursive function must have two main components:
1. **Base Case**: The condition under which the function stops calling itself to prevent infinite loops.
2. **Recursive Step**: The part where the function calls itself with modified parameters, moving closer to the base case.

## Classic Recursive Problems

### 1. Factorial
Calculating $N! = N \times (N-1)!$
```java
public int factorial(int n) {
    if (n == 0 || n == 1) return 1; // Base case
    return n * factorial(n - 1);    // Recursive step
}
```

### 2. Tower of Hanoi
A mathematical puzzle where the objective is to move a stack of disks from one rod to another, following specific rules.
```java
public void hanoi(int n, char source, char auxiliary, char target) {
    if (n == 1) {
        System.out.println("Move disk 1 from " + source + " to " + target);
        return;
    }
    hanoi(n - 1, source, target, auxiliary);
    System.out.println("Move disk " + n + " from " + source + " to " + target);
    hanoi(n - 1, auxiliary, source, target);
}
```

### 3. N-Queens Problem (Backtracking)
Placing $N$ chess queens on an $N \times N$ chessboard so that no two queens threaten each other. Recursion is used to explore paths and backtrack when a path fails.

```mermaid
graph TD
    subgraph Backtracking Flow
        direction TB
        Q0["Start (Empty Board)"] --> Q1["Place Q1 at (0,0)"]
        Q1 --> Q2_1["Try Q2 at (1,0) - INVALID"]
        Q1 --> Q2_2["Try Q2 at (1,1) - INVALID"]
        Q1 --> Q2_3["Try Q2 at (1,2) - VALID"]
        
        Q2_3 --> Q3_1["Try Q3... (Hits Dead End)"]
        
        Q3_1 -.->|"Backtrack!"| Q2_3
        Q2_3 -.->|"Backtrack!"| Q1
        
        Q1 --> Q1_Alt["Move Q1 to (0,1)"]
        style Q2_3 fill:#10b981,color:#fff
        style Q2_1 fill:#f43f5e,color:#fff
        style Q2_2 fill:#f43f5e,color:#fff
    end
```

## Recursion Tree Example (Fibonacci)
```mermaid
graph TD
    Fib4["fib(4)"] --> Fib3["fib(3)"]
    Fib4 --> Fib2["fib(2)"]
    Fib3 --> Fib2_2["fib(2)"]
    Fib3 --> Fib1["fib(1)"]
    Fib2 --> Fib1_2["fib(1)"]
    Fib2 --> Fib0["fib(0)"]
```

## Complexity
Recursion can be elegant but often comes with a higher Space Complexity $O(N)$ due to the implicit use of the Call Stack. Techniques like **Memoization** and **Tail Recursion** are used to optimize recursive calls.
