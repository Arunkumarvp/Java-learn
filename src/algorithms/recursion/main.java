package Recursiveprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    public static void main(String[] args) {

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 1 — LINEAR RECURSION
        //  One recursive call per invocation.
        //  Call stack depth = n.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("  SECTION 1: LINEAR RECURSION");
        System.out.println("══════════════════════════════════════════════════");

        System.out.println("  sumN(10)            → " + Concepts.sumN(10));           // 55
        System.out.println("  factorial(10)       → " + Concepts.factorial(10));      // 3628800
        System.out.println("  power(2, 10)        → " + Concepts.power(2, 10));       // 1024
        System.out.println("  reverseString(hello)→ " + Concepts.reverseString("hello")); // olleh
        System.out.println("  isPalindrome(racecar)→ " + Concepts.isPalindrome("racecar")); // true
        System.out.println("  isPalindrome(hello) → " + Concepts.isPalindrome("hello"));    // false
        System.out.println("  digitSum(1234)      → " + Concepts.digitSum(1234));     // 10
        System.out.println("  countDigit(112211,1)→ " + Concepts.countDigit(112211, 1)); // 4

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 2 — TAIL RECURSION
        //  The recursive call is the LAST action (no work on unwind).
        //  Accumulator carries the intermediate state forward.
        //  JVM doesn't TCO natively, but the pattern is still important.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 2: TAIL RECURSION");
        System.out.println("══════════════════════════════════════════════════");

        System.out.println("  factorialTail(10,1) → " + Concepts.factorialTail(10, 1)); // 3628800
        System.out.println("  sumTail(10,0)       → " + Concepts.sumTail(10, 0));        // 55
        System.out.print("  countDown(5)        → ");
        Concepts.countDown(5);
        System.out.println();

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 3 — HEAD RECURSION vs TAIL RECURSION
        //  Head: recurse FIRST → process during UNWIND → prints in order
        //  Tail: process FIRST → recurse last        → prints in reverse
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 3: HEAD vs TAIL RECURSION (print order)");
        System.out.println("══════════════════════════════════════════════════");

        System.out.print("  printAscending(5)  [HEAD] → ");
        Concepts.printAscending(5);   // 1 2 3 4 5
        System.out.println();

        System.out.print("  printDescending(5) [TAIL] → ");
        Concepts.printDescending(5);  // 5 4 3 2 1
        System.out.println();

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 4 — MULTIPLE / TREE RECURSION
        //  Each call branches into 2+ recursive calls.
        //  Produces an exponential call tree.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 4: MULTIPLE (TREE) RECURSION");
        System.out.println("══════════════════════════════════════════════════");

        System.out.print("  Fibonacci (naive) fib(0..10): ");
        for (int i = 0; i <= 10; i++) System.out.print(Concepts.fibNaive(i) + " ");
        System.out.println();

        System.out.println("  nCr(6,2) = " + Concepts.nCr(6, 2));  // 15
        System.out.println("  nCr(5,3) = " + Concepts.nCr(5, 3));  // 10
        System.out.println("  Pascal's row 5 (nCr(5,k) for k=0..5):");
        System.out.print("    ");
        for (int k = 0; k <= 5; k++) System.out.print(Concepts.nCr(5, k) + " ");
        System.out.println();

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 5 — INDIRECT / MUTUAL RECURSION
        //  isEven ↔ isOdd — neither calls itself directly.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 5: INDIRECT / MUTUAL RECURSION");
        System.out.println("══════════════════════════════════════════════════");

        System.out.println("  isEven(4) → " + Concepts.isEven(4));  // true
        System.out.println("  isEven(7) → " + Concepts.isEven(7));  // false
        System.out.println("  isOdd(3)  → " + Concepts.isOdd(3));   // true
        System.out.println("  isOdd(8)  → " + Concepts.isOdd(8));   // false

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 6 — NESTED RECURSION
        //  The ARGUMENT to the recursive call is itself a recursive call.
        //  Ackermann grows absurdly fast — only safe for tiny inputs.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 6: NESTED RECURSION (Ackermann)");
        System.out.println("══════════════════════════════════════════════════");

        System.out.println("  A(0,0)="+Concepts.ackermann(0,0)+"  A(0,5)="+Concepts.ackermann(0,5));
        System.out.println("  A(1,1)="+Concepts.ackermann(1,1)+"  A(2,2)="+Concepts.ackermann(2,2));
        System.out.println("  A(3,3)="+Concepts.ackermann(3,3)+"  (A(4,x) is too large to compute)");

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 7 — DIVIDE & CONQUER
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 7: DIVIDE & CONQUER");
        System.out.println("══════════════════════════════════════════════════");

        // Binary Search
        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println("  Array: " + Arrays.toString(sorted));
        System.out.println("  binarySearch(23) → idx " + Concepts.binarySearch(sorted, 23, 0, sorted.length - 1));  // 5
        System.out.println("  binarySearch(99) → " + Concepts.binarySearch(sorted, 99, 0, sorted.length - 1));       // -1

        // Merge Sort
        int[] toSort = {64, 34, 25, 12, 22, 11, 90};
        System.out.print("  mergeSort before: " + Arrays.toString(toSort));
        Concepts.mergeSort(toSort, 0, toSort.length - 1);
        System.out.println("  →  after: " + Arrays.toString(toSort));

        // Max in array
        int[] nums = {3, 1, 9, 5, 7, 2};
        System.out.println("  maxInArray(" + Arrays.toString(nums) + ") → " + Concepts.maxInArray(nums, 0, nums.length - 1));

        // Fast power
        System.out.println("  fastPow(2,20)    → " + Concepts.fastPow(2, 20));    // 1048576
        System.out.println("  fastPow(3,10)    → " + Concepts.fastPow(3, 10));    // 59049

        // Tower of Hanoi
        System.out.println("\n  -- Tower of Hanoi (3 discs, A→C using B):");
        Concepts.hanoi(3, 'A', 'C', 'B');
        System.out.println("  Moves required for 3 discs = 2^3 - 1 = 7");

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 8 — BACKTRACKING
        //  Explore all possibilities; undo bad choices and try alternatives.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 8: BACKTRACKING");
        System.out.println("══════════════════════════════════════════════════");

        // All subsets (Power Set)
        int[] setArr = {1, 2, 3};
        List<List<Integer>> subsets = new ArrayList<>();
        Concepts.subsets(setArr, 0, new ArrayList<>(), subsets);
        System.out.println("  Power set of {1,2,3} — " + subsets.size() + " subsets:");
        System.out.println("    " + subsets);

        // All permutations
        List<String> perms = new ArrayList<>();
        Concepts.permutations("", "ABC", perms);
        System.out.println("\n  Permutations of \"ABC\" — " + perms.size() + " permutations:");
        System.out.println("    " + perms);

        // N-Queens
        System.out.println("\n  -- 4-Queens problem (all solutions on 4×4 board):");
        Concepts.nQueensSolutions = 0;
        int[] board4 = new int[4];
        Arrays.fill(board4, -1);
        Concepts.nQueens(0, 4, board4);
        System.out.println("  Total solutions for 4-Queens: " + Concepts.nQueensSolutions);

        System.out.println("\n  -- 8-Queens solution count:");
        Concepts.nQueensSolutions = 0;
        int[] board8 = new int[8];
        Arrays.fill(board8, -1);
        Concepts.nQueens(0, 8, board8);
        System.out.println("  Total solutions for 8-Queens: " + Concepts.nQueensSolutions); // 92

        // Grid paths
        System.out.println("\n  -- Grid paths (2×3 grid, R=right D=down):");
        List<String> paths = new ArrayList<>();
        Concepts.gridPaths(0, 0, 2, 3, "", paths);
        System.out.println("    " + paths + "  (" + paths.size() + " paths)");

        // Balanced parentheses
        System.out.println("\n  -- Balanced parentheses for n=3:");
        List<String> parens = new ArrayList<>();
        Concepts.genParentheses(3, 3, "", parens);
        System.out.println("    " + parens);

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 9 — MEMOIZATION (Top-Down DP)
        //  Cache subproblem results to avoid recomputation.
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 9: MEMOIZATION (Top-Down DP)");
        System.out.println("══════════════════════════════════════════════════");

        // Fibonacci — naive vs memoized
        System.out.println("  Fibonacci comparison:");
        Map<Integer, Long> fibCache = new HashMap<>();
        System.out.print("    fib(0..15) memoized: ");
        for (int i = 0; i <= 15; i++) System.out.print(Concepts.fibMemo(i, fibCache) + " ");
        System.out.println();
        System.out.println("    fib(50) memoized  → " + Concepts.fibMemo(50, new HashMap<>()));  // instant
        System.out.println("    fib(50) naive     → would take ~2^50 calls (not run)");

        // Coin change
        int[] coins = {1, 5, 10, 25};
        Map<Integer, Integer> coinMemo = new HashMap<>();
        System.out.println("\n  Coin change (coins={1,5,10,25}):");
        System.out.println("    coinChange(41) → " + Concepts.coinChange(coins, 41, coinMemo) + " coins");  // 4 (25+10+5+1)
        System.out.println("    coinChange(30) → " + Concepts.coinChange(coins, 30, new HashMap<>()) + " coins");  // 2 (25+5)

        // LCS
        String a = "ABCBDAB", b = "BDCAB";
        Map<String, Integer> lcsMemo = new HashMap<>();
        System.out.println("\n  LCS(\"ABCBDAB\", \"BDCAB\") → " +
                Concepts.lcs(a, b, a.length(), b.length(), lcsMemo) + "  (answer: 4, e.g. BCAB)");

        // ═══════════════════════════════════════════════════════════════════
        //  SECTION 10 — CLASSIC PROBLEMS (Mixed Patterns)
        // ═══════════════════════════════════════════════════════════════════
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SECTION 10: CLASSIC PROBLEMS");
        System.out.println("══════════════════════════════════════════════════");

        // GCD
        System.out.println("  gcd(48, 18)          → " + Concepts.gcd(48, 18));  // 6
        System.out.println("  gcd(100, 75)         → " + Concepts.gcd(100, 75)); // 25

        // Binary representation
        System.out.print("  printBinary(42)      → ");
        Concepts.printBinary(42);    // 101010
        System.out.println();

        // isSorted
        int[] s1 = {1, 2, 3, 4, 5};
        int[] s2 = {1, 3, 2, 4, 5};
        System.out.println("  isSorted({1,2,3,4,5})→ " + Concepts.isSorted(s1, 0)); // true
        System.out.println("  isSorted({1,3,2,4,5})→ " + Concepts.isSorted(s2, 0)); // false

        // First occurrence
        int[] arr = {4, 2, 7, 2, 9, 2};
        System.out.println("  firstOccurrence(arr,2)→ idx " + Concepts.firstOccurrence(arr, 2, 0)); // 1
        System.out.println("  firstOccurrence(arr,9)→ idx " + Concepts.firstOccurrence(arr, 9, 0)); // 4

        // Climb stairs
        System.out.println("  climbStairs(1..8):   ");
        System.out.print("    ways: ");
        for (int i = 1; i <= 8; i++) System.out.print(Concepts.climbStairs(i) + " ");
        System.out.println();  // 1 2 3 5 8 13 21 34

        // Josephus problem
        System.out.println("  josephus(7,3)        → pos " + Concepts.josephus(7, 3)); // 3 (0-indexed)
        System.out.println("  josephus(6,2)        → pos " + Concepts.josephus(6, 2)); // 4

        // Tilings (2×n board with 2×1 dominoes)
        System.out.print("  tilings(2×n), n=1..8: ");
        for (int i = 1; i <= 8; i++) System.out.print(Concepts.tilings(i) + " ");
        System.out.println();  // 1 2 3 5 8 13 21 34  (Fibonacci!)

        // Flatten nested array
        Object[] nested = {1, new Object[]{2, 3, new Object[]{4, 5}}, 6};
        List<Integer> flat = new ArrayList<>();
        Concepts.flatten(nested, flat);
        System.out.println("  flatten({1,{2,3,{4,5}},6}) → " + flat);

        // ─────────────────────────────────────────────────────────────────
        //  COMPLEXITY SUMMARY
        // ─────────────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  COMPLEXITY SUMMARY");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println(" Algorithm             │ Time        │ Space    │ Type");
        System.out.println(" ──────────────────────┼─────────────┼──────────┼──────────────");
        System.out.println(" sumN / factorial       │   O(n)      │  O(n)    │ Linear");
        System.out.println(" fastPow               │   O(log n)  │  O(log n)│ D&C");
        System.out.println(" binarySearch          │   O(log n)  │  O(log n)│ D&C");
        System.out.println(" mergeSort             │   O(n log n)│  O(n)    │ D&C");
        System.out.println(" fibNaive              │   O(2^n)    │  O(n)    │ Tree");
        System.out.println(" fibMemo               │   O(n)      │  O(n)    │ Memoized");
        System.out.println(" coinChange(memo)       │   O(n*coins)│  O(n)    │ Memoized");
        System.out.println(" LCS(memo)             │   O(m*n)    │  O(m*n)  │ Memoized");
        System.out.println(" subsets (power set)   │   O(2^n)    │  O(n)    │ Backtracking");
        System.out.println(" permutations          │   O(n!)     │  O(n)    │ Backtracking");
        System.out.println(" nQueens               │   O(n!)     │  O(n)    │ Backtracking");
        System.out.println(" Tower of Hanoi        │   O(2^n)    │  O(n)    │ D&C/Linear");
        System.out.println(" GCD (Euclidean)       │   O(log n)  │  O(log n)│ Tail");
        System.out.println(" Ackermann(m,n)        │ non-primitive│ O(A(m,n))│ Nested");
    }
}
