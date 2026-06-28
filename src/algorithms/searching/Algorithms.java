package Searching_Algo;

// ═══════════════════════════════════════════════════════════════════════
//  SEARCHING ALGORITHMS — IMPLEMENTATION LIBRARY
//
//  This library contains standard implementations of key searching strategies
//  used in linear collections.
// ═══════════════════════════════════════════════════════════════════════

public class Algorithms {

    // ── 1. LINEAR SEARCH ────────────────────────────────────────────
    // Check every element of the list sequentially until a match is found.
    // Works on both sorted and unsorted arrays.
    // Time Complexity: Best: O(1) | Avg/Worst: O(N)
    // Space Complexity: O(1)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // index found
            }
        }
        return -1; // not found
    }

    // ── 2. BINARY SEARCH (Iterative) ────────────────────────────────
    // Divide-and-conquer strategy. Compares target to mid element, halves search space.
    // REQUIRES: Sorted Array.
    // Time Complexity: Best: O(1) | Avg/Worst: O(log N)
    // Space Complexity: O(1)
    public static int binarySearchIterative(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids potential integer overflow

            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // ── 3. BINARY SEARCH (Recursive) ────────────────────────────────
    // Recursive implementation of Binary Search.
    // REQUIRES: Sorted Array.
    // Time Complexity: Best: O(1) | Avg/Worst: O(log N)
    // Space Complexity: O(log N) (call stack depth)
    public static int binarySearchRecursive(int[] arr, int target) {
        return binarySearchRecursiveHelper(arr, target, 0, arr.length - 1);
    }

    private static int binarySearchRecursiveHelper(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] < target) {
            return binarySearchRecursiveHelper(arr, target, mid + 1, high);
        } else {
            return binarySearchRecursiveHelper(arr, target, low, mid - 1);
        }
    }

    // ── 4. TERNARY SEARCH ───────────────────────────────────────────
    // Divide-and-conquer strategy, but splits array into three parts using two midpoints.
    // REQUIRES: Sorted Array.
    // Time Complexity: Best: O(1) | Avg/Worst: O(log3 N)
    // Space Complexity: O(log3 N) (due to recursion stack)
    // Note: Performs more comparisons than binary search in practice, but reduces steps.
    public static int ternarySearch(int[] arr, int target) {
        return ternarySearchHelper(arr, target, 0, arr.length - 1);
    }

    private static int ternarySearchHelper(int[] arr, int target, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid1 = low + (high - low) / 3;
        int mid2 = high - (high - low) / 3;

        if (arr[mid1] == target) {
            return mid1;
        }
        if (arr[mid2] == target) {
            return mid2;
        }

        if (target < arr[mid1]) {
            return ternarySearchHelper(arr, target, low, mid1 - 1);
        } else if (target > arr[mid2]) {
            return ternarySearchHelper(arr, target, mid2 + 1, high);
        } else {
            return ternarySearchHelper(arr, target, mid1 + 1, mid2 - 1);
        }
    }

    // ── 5. JUMP SEARCH ──────────────────────────────────────────────
    // Jump ahead by fixed steps (optimal step size = sqrt(N)) then linear search backward.
    // REQUIRES: Sorted Array.
    // Time Complexity: Best: O(1) | Avg/Worst: O(sqrt(N))
    // Space Complexity: O(1)
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));

        int prev = 0;
        // Finding the block where element is present (if it is present)
        int limit = Math.min(step, n);
        while (arr[limit - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                return -1;
            }
            limit = Math.min(step, n);
        }

        // Doing a linear search for target in block beginning with prev
        while (arr[prev] < target) {
            prev++;
            // If we reached next block or end of array, element is not present
            if (prev == limit) {
                return -1;
            }
        }

        // If element is found
        if (arr[prev] == target) {
            return prev;
        }

        return -1;
    }

    // ── 6. INTERPOLATION SEARCH ─────────────────────────────────────
    // Improvement over Binary Search for sorted arrays where elements are uniformly distributed.
    // Calculates probe position based on value scale:
    // pos = low + [ (target - arr[low]) * (high - low) / (arr[high] - arr[low]) ]
    // REQUIRES: Sorted and Uniformly Distributed Array.
    // Time Complexity: Best: O(1) | Avg: O(log log N) | Worst: O(N) (if skewed distribution)
    // Space Complexity: O(1)
    public static int interpolationSearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high && target >= arr[low] && target <= arr[high]) {
            if (low == high) {
                if (arr[low] == target) return low;
                return -1;
            }

            // Estimate position formula
            int pos = low + (int) (((double)(high - low) / (arr[high] - arr[low])) * (target - arr[low]));

            if (arr[pos] == target) {
                return pos;
            }

            if (arr[pos] < target) {
                low = pos + 1;
            } else {
                high = pos - 1;
            }
        }
        return -1;
    }

    // ── 7. EXPONENTIAL SEARCH ───────────────────────────────────────
    // Useful for unbounded or infinite search spaces. Find range where target lies
    // by growing index exponentially (1, 2, 4, 8, ...), then run binary search in that range.
    // REQUIRES: Sorted Array.
    // Time Complexity: Best: O(1) | Avg/Worst: O(log N)
    // Space Complexity: O(log N) (due to recursive binary search range helper)
    public static int exponentialSearch(int[] arr, int target) {
        int n = arr.length;
        if (n == 0) return -1;
        if (arr[0] == target) {
            return 0;
        }

        // Find range for binary search by repeated doubling
        int i = 1;
        while (i < n && arr[i] <= target) {
            i = i * 2;
        }

        // Call binary search for the found range
        return binarySearchRangeHelper(arr, target, i / 2, Math.min(i, n - 1));
    }

    private static int binarySearchRangeHelper(int[] arr, int target, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
