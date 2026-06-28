package Searching_Algo;

import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("  SEARCHING ALGORITHMS DEMONSTRATION");
        System.out.println("══════════════════════════════════════════════════");

        // Uniformly distributed sorted array for testing search algorithms
        int[] sortedArray = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        int targetExist = 70;
        int targetNotExist = 45;

        System.out.println("  Input Sorted Array : " + Arrays.toString(sortedArray));
        System.out.println("  Target to Find     : " + targetExist);
        System.out.println("  Target not in Array: " + targetNotExist);
        System.out.println();

        // 1. Linear Search
        System.out.printf("  %-25s : Index of %d → %d | Index of %d → %d%n", 
            "1. Linear Search", 
            targetExist, Algorithms.linearSearch(sortedArray, targetExist),
            targetNotExist, Algorithms.linearSearch(sortedArray, targetNotExist));

        // 2. Binary Search (Iterative)
        System.out.printf("  %-25s : Index of %d → %d | Index of %d → %d%n", 
            "2. Binary Search (Iterative)", 
            targetExist, Algorithms.binarySearchIterative(sortedArray, targetExist),
            targetNotExist, Algorithms.binarySearchIterative(sortedArray, targetNotExist));

        // 3. Binary Search (Recursive)
        System.out.printf("  %-25s : Index of %d → %d | Index of %d → %d%n", 
            "3. Binary Search (Recursive)", 
            targetExist, Algorithms.binarySearchRecursive(sortedArray, targetExist),
            targetNotExist, Algorithms.binarySearchRecursive(sortedArray, targetNotExist));

        // 4. Ternary Search
        System.out.printf("  %-25s : Index of %d → %d | Index of %d → %d%n", 
            "4. Ternary Search", 
            targetExist, Algorithms.ternarySearch(sortedArray, targetExist),
            targetNotExist, Algorithms.ternarySearch(sortedArray, targetNotExist));

        // 5. Jump Search
        System.out.printf("  %-25s : Index of %d → %d | Index of %d → %d%n", 
            "5. Jump Search", 
            targetExist, Algorithms.jumpSearch(sortedArray, targetExist),
            targetNotExist, Algorithms.jumpSearch(sortedArray, targetNotExist));

        // 6. Interpolation Search
        System.out.printf("  %-25s : Index of %d → %d | Index of %d → %d%n", 
            "6. Interpolation Search", 
            targetExist, Algorithms.interpolationSearch(sortedArray, targetExist),
            targetNotExist, Algorithms.interpolationSearch(sortedArray, targetNotExist));

        // 7. Exponential Search
        System.out.printf("  %-25s : Index of %d → %d | Index of %d → %d%n", 
            "7. Exponential Search", 
            targetExist, Algorithms.exponentialSearch(sortedArray, targetExist),
            targetNotExist, Algorithms.exponentialSearch(sortedArray, targetNotExist));

        // ─────────────────────────────────────────────────────────────────
        //  COMPLEXITY & PROPERTIES SUMMARY
        // ─────────────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SEARCHING ALGORITHMS COMPLEXITY SUMMARY");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println(" Algorithm            │ Best   │ Average     │ Worst   │ Space   │ Sorted Required ");
        System.out.println(" ─────────────────────┼────────┼─────────────┼─────────┼─────────┼─────────────────");
        System.out.println(" Linear Search        │ O(1)   │ O(N)        │ O(N)    │ O(1)    │ No              ");
        System.out.println(" Binary Search (Iter) │ O(1)   │ O(log N)    │ O(log N)│ O(1)    │ Yes             ");
        System.out.println(" Binary Search (Rec)  │ O(1)   │ O(log N)    │ O(log N)│ O(log N)│ Yes             ");
        System.out.println(" Ternary Search       │ O(1)   │ O(log3 N)   │ O(log3 N)│ O(log3 N)│ Yes            ");
        System.out.println(" Jump Search          │ O(1)   │ O(√N)       │ O(√N)   │ O(1)    │ Yes             ");
        System.out.println(" Interpolation Search │ O(1)   │ O(log log N)│ O(N)    │ O(1)    │ Yes (Uniform)   ");
        System.out.println(" Exponential Search   │ O(1)   │ O(log N)    │ O(log N)│ O(log N)│ Yes             ");
        System.out.println("\n  N = Array size. Space complexity for recursive methods represents call stack depth.");
    }
}
