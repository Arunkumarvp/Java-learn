package Sorting;

import java.util.Arrays;

// ═══════════════════════════════════════════════════════════════════════
//  SORTING ALGORITHMS — IMPLEMENTATION LIBRARY
//
//  This library contains standard implementations of key comparison-based
//  and non-comparison-based sorting algorithms.
// ═══════════════════════════════════════════════════════════════════════

public class Algorithms {

    // ── 1. BUBBLE SORT (Optimized) ──────────────────────────────────
    // Compare adjacent elements and swap them if they are in wrong order.
    // Optimization: If no elements were swapped in a pass, the array is sorted.
    // Time Complexity: Best: O(N) | Avg/Worst: O(N^2)
    // Space Complexity: O(1)
    // Stability: Stable
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped by inner loop, then break
            if (!swapped) {
                break;
            }
        }
    }

    // ── 2. SELECTION SORT ───────────────────────────────────────────
    // Repeatedly find the minimum element from the unsorted part and put it at start.
    // Time Complexity: Best/Avg/Worst: O(N^2)
    // Space Complexity: O(1)
    // Stability: Unstable (swaps can disrupt relative order of equal keys)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            // Swap the found minimum element with the first element of unsorted part
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // ── 3. INSERTION SORT ───────────────────────────────────────────
    // Build the sorted array one item at a time by inserting elements
    // into their correct position in the sorted part.
    // Time Complexity: Best: O(N) | Avg/Worst: O(N^2)
    // Space Complexity: O(1)
    // Stability: Stable
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    // ── 4. MERGE SORT (Divide & Conquer) ───────────────────────────
    // Divide array into halves, sort them, and merge the sorted halves.
    // Time Complexity: Best/Avg/Worst: O(N log N)
    // Space Complexity: O(N) (requires auxiliary array for merging)
    // Stability: Stable
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) return;
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSortHelper(arr, l, m);
            mergeSortHelper(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    // ── 5. QUICK SORT ───────────────────────────────────────────────
    // Pick a pivot element, partition array around pivot, recurse on partitions.
    // This implementation uses the Hoare Partitioning with middle pivot
    // which resists pre-sorted array worst cases better than Lomuto.
    // Time Complexity: Best/Avg: O(N log N) | Worst: O(N^2)
    // Space Complexity: O(log N) (call stack)
    // Stability: Unstable
    public static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSortHelper(arr, low, p);
            quickSortHelper(arr, p + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low + (high - low) / 2];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;
            }

            // swap
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // ── 6. HEAP SORT ────────────────────────────────────────────────
    // Build a max heap, then repeatedly swap root with last item and heapify down.
    // Time Complexity: Best/Avg/Worst: O(N log N)
    // Space Complexity: O(1) in-place
    // Stability: Unstable
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left
        int r = 2 * i + 2; // right

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    // ── 7. COUNTING SORT (Stable Integer Sort) ──────────────────────
    // Non-comparison sort. Counts frequency of each value, maps to output array.
    // Works only for a bounded range of integers.
    // Time Complexity: O(N + K) where K is the range of values (max - min + 1)
    // Space Complexity: O(N + K)
    // Stability: Stable
    public static void countingSort(int[] arr) {
        if (arr.length == 0) return;

        // Find min and max to support negative integers
        int max = arr[0];
        int min = arr[0];
        for (int val : arr) {
            if (val > max) max = val;
            if (val < min) min = val;
        }

        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];

        // Store count of occurrences
        for (int val : arr) {
            count[val - min]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this element in output array
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Build the output character array (traverse backwards for stability)
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy the output array to arr, so that arr now contains sorted values
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    // ── 8. RADIX SORT ───────────────────────────────────────────────
    // Sorts integers digit by digit starting from least significant digit (LSD) to MSD.
    // Uses Counting Sort as a stable sorting sub-routine.
    // Works only for non-negative integers.
    // Time Complexity: O(d * (N + B)) where d is digits, B is base (10)
    // Space Complexity: O(N + B)
    // Stability: Stable
    public static void radixSort(int[] arr) {
        if (arr.length == 0) return;

        // Find maximum number to know number of digits
        int max = arr[0];
        for (int val : arr) {
            if (val < 0) {
                throw new IllegalArgumentException("Radix sort works only for non-negative integers in this implementation.");
            }
            if (val > max) max = val;
        }

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSortForRadix(arr, exp);
        }
    }

    private static void countSortForRadix(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Store count of occurrences in count[]
        for (int val : arr) {
            count[(val / exp) % 10]++;
        }

        // Change count[i] so that count[i] now contains actual
        // position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array (traverse backwards for stability)
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        System.arraycopy(output, 0, arr, 0, n);
    }
}
