package Recursiveprogramming;

// ═══════════════════════════════════════════════════════════════════════
//  RECURSION — COMPLETE CONCEPT LIBRARY
//
//  Recursion = a method that calls itself to solve a smaller subproblem.
//
//  Anatomy of every recursive method:
//    1. BASE CASE   — stops recursion (prevents infinite loop)
//    2. RECURSIVE CASE — breaks the problem smaller and calls itself
//    3. RETURN / COMBINE — merges results on the way back up (unwind)
//
//  Call Stack mechanics:
//    Each call pushes a stack frame.
//    Base case pops back, results flow up (unwind phase).
//    StackOverflowError = missing or unreachable base case.
//
//  Recursion types covered:
//    1.  Linear Recursion       — one recursive call per invocation
//    2.  Tail Recursion         — recursive call is the LAST statement
//    3.  Head Recursion         — recursive call is the FIRST statement
//    4.  Multiple Recursion     — more than one recursive call (Tree recursion)
//    5.  Indirect Recursion     — A calls B, B calls A
//    6.  Nested Recursion       — argument to recursive call is itself recursive
//    7.  Divide & Conquer       — split problem in half each time
//    8.  Backtracking           — explore + undo on failure
//    9.  Memoization            — cache results to avoid recomputation
//   10.  Mutual Recursion       — two functions alternately call each other
// ═══════════════════════════════════════════════════════════════════════

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Concepts {

    // ═══════════════════════════════════════════════════════════════════
    // 1. LINEAR RECURSION
    // Each call makes exactly ONE recursive call.
    // Stack depth = n. Time: O(n).
    // ═══════════════════════════════════════════════════════════════════

    // Sum of first n natural numbers 1+2+…+n
    // f(n) = n + f(n-1), f(0) = 0
    public static int sumN(int n) {
        if (n == 0)
            return 0; // base case
        return n + sumN(n - 1); // linear: one call
    }

    // Factorial n! = n × (n-1)!, 0! = 1
    public static long factorial(int n) {
        if (n <= 1)
            return 1;
        return n * factorial(n - 1);
    }

    // Power base^exp (positive exponents only)
    public static long power(long base, int exp) {
        if (exp == 0)
            return 1;
        return base * power(base, exp - 1);
    }

    // Reverse a string by peeling off the first character
    public static String reverseString(String s) {
        if (s.isEmpty())
            return s;
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    // Check palindrome recursively
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1)
            return true;
        if (s.charAt(0) != s.charAt(s.length() - 1))
            return false;
        return isPalindrome(s.substring(1, s.length() - 1));
    }

    // Sum of digits e.g. 1234 → 10
    public static int digitSum(int n) {
        if (n == 0)
            return 0;
        return (n % 10) + digitSum(n / 10);
    }

    // Count occurrences of a digit in a number
    public static int countDigit(int n, int digit) {
        if (n == 0)
            return 0;
        return (n % 10 == digit ? 1 : 0) + countDigit(n / 10, digit);
    }

    // ═══════════════════════════════════════════════════════════════════
    // 2. TAIL RECURSION
    // The recursive call is the very last action — no work on unwind.
    // Many compilers optimise tail calls into loops (TCO).
    // Accumulator carries the intermediate result.
    // ═══════════════════════════════════════════════════════════════════

    // Tail-recursive factorial using an accumulator
    public static long factorialTail(int n, long acc) {
        if (n <= 1)
            return acc; // base case: return acc
        return factorialTail(n - 1, n * acc); // tail: last action is the call
    }

    // Tail-recursive sum
    public static int sumTail(int n, int acc) {
        if (n == 0)
            return acc;
        return sumTail(n - 1, acc + n);
    }

    // Tail-recursive countdown print
    public static void countDown(int n) {
        if (n < 0)
            return;
        System.out.print(n + " ");
        countDown(n - 1); // tail call — no operation after it
    }

    // ═══════════════════════════════════════════════════════════════════
    // 3. HEAD RECURSION
    // The recursive call happens FIRST before any processing.
    // Processing happens during the UNWIND (return) phase.
    // ═══════════════════════════════════════════════════════════════════

    // Print 1 to n using head recursion
    // Call descends to base first, then prints on the way back up
    public static void printAscending(int n) {
        if (n == 0)
            return;
        printAscending(n - 1); // HEAD: recurse first
        System.out.print(n + " "); // then process (prints in order 1..n)
    }

    // Print n to 1 using tail recursion (contrast with above)
    public static void printDescending(int n) {
        if (n == 0)
            return;
        System.out.print(n + " "); // process first
        printDescending(n - 1); // then recurse (TAIL)
    }

    // ═══════════════════════════════════════════════════════════════════
    // 4. MULTIPLE (TREE) RECURSION
    // Each call spawns MORE than one recursive call.
    // Call tree branches like a binary tree.
    // Time: often O(2^n) without memoization.
    // ═══════════════════════════════════════════════════════════════════

    // Fibonacci (naive, exponential) — classic tree recursion
    // fib(n) = fib(n-1) + fib(n-2), fib(0)=0, fib(1)=1
    public static long fibNaive(int n) {
        if (n <= 1)
            return n;
        return fibNaive(n - 1) + fibNaive(n - 2); // TWO calls → tree
    }

    // Count subsets of size k from n items (nCr)
    // nCr = (n-1)Cr + (n-1)C(r-1)
    public static int nCr(int n, int r) {
        if (r == 0 || r == n)
            return 1; // base
        return nCr(n - 1, r) + nCr(n - 1, r - 1);
    }

    // ═══════════════════════════════════════════════════════════════════
    // 5. INDIRECT / MUTUAL RECURSION
    // isEven calls isOdd, isOdd calls isEven.
    // Neither calls itself directly.
    // ═══════════════════════════════════════════════════════════════════

    public static boolean isEven(int n) {
        if (n == 0)
            return true;
        return isOdd(n - 1); // delegates to isOdd
    }

    public static boolean isOdd(int n) {
        if (n == 0)
            return false;
        return isEven(n - 1); // delegates back to isEven
    }

    // ═══════════════════════════════════════════════════════════════════
    // 6. NESTED RECURSION
    // The ARGUMENT to the recursive call is itself a recursive call.
    // Ackermann function — grows astronomically fast.
    // A(0,n)=n+1, A(m,0)=A(m-1,1), A(m,n)=A(m-1,A(m,n-1))
    // ═══════════════════════════════════════════════════════════════════

    public static int ackermann(int m, int n) {
        if (m == 0)
            return n + 1;
        if (n == 0)
            return ackermann(m - 1, 1);
        return ackermann(m - 1, ackermann(m, n - 1)); // NESTED: inner call is arg to outer
    }

    // ═══════════════════════════════════════════════════════════════════
    // 7. DIVIDE & CONQUER
    // Split input in half → solve each half → merge results.
    // Classic pattern: Binary Search, Merge Sort, Quick Sort, Max Subarray.
    // ═══════════════════════════════════════════════════════════════════

    // Binary Search — divide array in half each time O(log n)
    public static int binarySearch(int[] arr, int target, int lo, int hi) {
        if (lo > hi)
            return -1; // base: not found
        int mid = lo + (hi - lo) / 2;
        if (arr[mid] == target)
            return mid;
        if (target < arr[mid])
            return binarySearch(arr, target, lo, mid - 1); // left half
        return binarySearch(arr, target, mid + 1, hi); // right half
    }

    // Merge Sort O(n log n)
    public static void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi)
            return; // base: single element
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid); // sort left half
        mergeSort(arr, mid + 1, hi); // sort right half
        merge(arr, lo, mid, hi); // merge both halves
    }

    private static void merge(int[] arr, int lo, int mid, int hi) {
        int[] tmp = Arrays.copyOfRange(arr, lo, hi + 1);
        int i = 0, j = mid - lo + 1, k = lo;
        while (i <= mid - lo && j <= hi - lo)
            arr[k++] = (tmp[i] <= tmp[j]) ? tmp[i++] : tmp[j++];
        while (i <= mid - lo)
            arr[k++] = tmp[i++];
        while (j <= hi - lo)
            arr[k++] = tmp[j++];
    }

    // Maximum element in array using D&C
    public static int maxInArray(int[] arr, int lo, int hi) {
        if (lo == hi)
            return arr[lo]; // base: one element
        int mid = lo + (hi - lo) / 2;
        int leftMax = maxInArray(arr, lo, mid);
        int rightMax = maxInArray(arr, mid + 1, hi);
        return Math.max(leftMax, rightMax);
    }

    // Power using fast exponentiation O(log n)
    // base^n = (base^(n/2))^2 if n is even
    // = base * base^(n-1) if n is odd
    public static long fastPow(long base, int n) {
        if (n == 0)
            return 1;
        if (n % 2 == 0) {
            long half = fastPow(base, n / 2);
            return half * half;
        }
        return base * fastPow(base, n - 1);
    }

    // Tower of Hanoi — move n discs from src to dest using aux
    // Move n-1 discs to aux, move largest to dest, move n-1 from aux to dest
    public static void hanoi(int n, char src, char dest, char aux) {
        if (n == 0)
            return;
        hanoi(n - 1, src, aux, dest); // move n-1 to auxiliary
        System.out.printf("    Move disc %d: %c → %c%n", n, src, dest);
        hanoi(n - 1, aux, dest, src); // move n-1 from aux to dest
    }

    // ═══════════════════════════════════════════════════════════════════
    // 8. BACKTRACKING
    // Try a choice → recurse → if dead end, UNDO the choice and try next.
    // Pattern:
    // choose → explore → unchoose (backtrack)
    //
    // Problems covered:
    // a) All subsets (Power Set)
    // b) All permutations
    // c) N-Queens
    // ═══════════════════════════════════════════════════════════════════

    // a) All subsets (Power Set) of an integer array
    // At each index: include the element OR skip it → 2^n subsets
    public static void subsets(int[] arr, int index, List<Integer> current,
            List<List<Integer>> result) {
        if (index == arr.length) { // base: processed all elements
            result.add(new ArrayList<>(current));
            return;
        }
        // Choice 1: INCLUDE arr[index]
        current.add(arr[index]);
        subsets(arr, index + 1, current, result);

        // Choice 2: SKIP arr[index] (backtrack: undo the add)
        current.remove(current.size() - 1);
        subsets(arr, index + 1, current, result);
    }

    // b) All permutations of a string
    // Fix prefix, permute the rest recursively
    public static void permutations(String prefix, String remaining,
            List<String> result) {
        if (remaining.isEmpty()) { // base: nothing left to add
            result.add(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            char chosen = remaining.charAt(i);
            String next = remaining.substring(0, i) + remaining.substring(i + 1);
            permutations(prefix + chosen, next, result); // choose + recurse
            // backtrack happens implicitly — string is immutable, no undo needed
        }
    }

    // c) N-Queens — place N queens on N×N board, none attacking each other
    // board[row] = column where queen is placed in that row
    public static int nQueensSolutions = 0;

    public static void nQueens(int row, int n, int[] board) {
        if (row == n) { // base: all queens placed
            nQueensSolutions++;
            printBoard(board, n);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col; // CHOOSE: place queen
                nQueens(row + 1, n, board); // EXPLORE
                board[row] = -1; // UNCHOOSE: backtrack
            }
        }
    }

    private static boolean isSafe(int[] board, int row, int col) {
        for (int r = 0; r < row; r++) {
            if (board[r] == col)
                return false; // same column
            if (Math.abs(board[r] - col) == Math.abs(r - row))
                return false; // diagonal
        }
        return true;
    }

    private static void printBoard(int[] board, int n) {
        System.out.print("    [");
        for (int i = 0; i < n; i++)
            System.out.print("Q" + (i + 1) + "=col" + board[i] + (i < n - 1 ? ", " : ""));
        System.out.println("]");
    }

    // ═══════════════════════════════════════════════════════════════════
    // 9. MEMOIZATION (Top-Down Dynamic Programming)
    // Cache the result of each subproblem so it is computed only once.
    // Converts exponential O(2^n) tree recursion → O(n) with memo.
    // ═══════════════════════════════════════════════════════════════════

    // Fibonacci with memoization O(n) time, O(n) space
    public static long fibMemo(int n, Map<Integer, Long> memo) {
        if (n <= 1)
            return n;
        if (memo.containsKey(n))
            return memo.get(n); // CACHE HIT
        long result = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
        memo.put(n, result); // CACHE the result
        return result;
    }

    // Coin change — minimum coins to make amount (top-down)
    // coinChange(amount) = 1 + min over each coin of coinChange(amount - coin)
    public static int coinChange(int[] coins, int amount, Map<Integer, Integer> memo) {
        if (amount == 0)
            return 0;
        if (amount < 0)
            return Integer.MAX_VALUE / 2; // impossible
        if (memo.containsKey(amount))
            return memo.get(amount);

        int best = Integer.MAX_VALUE / 2;
        for (int coin : coins) {
            int sub = coinChange(coins, amount - coin, memo);
            best = Math.min(best, 1 + sub);
        }
        memo.put(amount, best);
        return best;
    }

    // Longest Common Subsequence (LCS) — top-down memo
    public static int lcs(String a, String b, int i, int j,
            Map<String, Integer> memo) {
        if (i == 0 || j == 0)
            return 0;
        String key = i + "," + j;
        if (memo.containsKey(key))
            return memo.get(key);

        int result;
        if (a.charAt(i - 1) == b.charAt(j - 1))
            result = 1 + lcs(a, b, i - 1, j - 1, memo);
        else
            result = Math.max(lcs(a, b, i - 1, j, memo),
                    lcs(a, b, i, j - 1, memo));
        memo.put(key, result);
        return result;
    }

    // ═══════════════════════════════════════════════════════════════════
    // 10. CLASSIC PROBLEMS (assorted patterns)
    // ═══════════════════════════════════════════════════════════════════

    // GCD using Euclidean algorithm (tail-recursive)
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Flatten a nested list to a flat list recursively
    // Demonstrates recursion over variable-depth structures
    public static void flatten(Object[] nested, List<Integer> flat) {
        for (Object item : nested) {
            if (item instanceof Integer)
                flat.add((Integer) item);
            else if (item instanceof Object[])
                flatten((Object[]) item, flat);
        }
    }

    // Print all paths in a grid from (0,0) to (m-1,n-1)
    // Only allowed moves: right (R) or down (D)
    public static void gridPaths(int row, int col, int m, int n, String path,
            List<String> paths) {
        if (row == m - 1 && col == n - 1) {
            paths.add(path);
            return;
        }
        if (row < m - 1)
            gridPaths(row + 1, col, m, n, path + "D", paths); // move Down
        if (col < n - 1)
            gridPaths(row, col + 1, m, n, path + "R", paths); // move Right
    }

    // Josephus problem — find safe position in circle of n people, step k
    public static int josephus(int n, int k) {
        if (n == 1)
            return 0;
        return (josephus(n - 1, k) + k) % n;
    }

    // Count ways to climb stairs (1 or 2 steps at a time)
    // Same recurrence as Fibonacci: ways(n) = ways(n-1) + ways(n-2)
    public static int climbStairs(int n) {
        if (n <= 1)
            return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    // Generate all balanced parentheses for n pairs
    public static void genParentheses(int open, int close, String cur,
            List<String> result) {
        if (open == 0 && close == 0) {
            result.add(cur);
            return;
        }
        if (open > 0)
            genParentheses(open - 1, close, cur + "(", result);
        if (close > open)
            genParentheses(open, close - 1, cur + ")", result);
    }

    // Check if array is sorted (recursively)
    public static boolean isSorted(int[] arr, int i) {
        if (i == arr.length - 1)
            return true;
        if (arr[i] > arr[i + 1])
            return false;
        return isSorted(arr, i + 1);
    }

    // Find index of first occurrence in array
    public static int firstOccurrence(int[] arr, int target, int i) {
        if (i == arr.length)
            return -1;
        if (arr[i] == target)
            return i;
        return firstOccurrence(arr, target, i + 1);
    }

    // Binary representation of a number (recursive print)
    public static void printBinary(int n) {
        if (n == 0)
            return;
        printBinary(n / 2); // head recursion: recurse first
        System.out.print(n % 2); // print bit on unwind
    }

    // Number of ways to tile a 2×n board with 2×1 dominoes
    // tilings(n) = tilings(n-1) + tilings(n-2)
    public static int tilings(int n) {
        if (n <= 1)
            return 1;
        return tilings(n - 1) + tilings(n - 2);
    }
}
