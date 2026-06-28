package Sorting;

import java.util.Arrays;

public class main {

    public static void main(String[] args) {
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("  SORTING ALGORITHMS DEMONSTRATION");
        System.out.println("══════════════════════════════════════════════════");

        // Source unsorted arrays
        int[] originalArray = { 64, 34, 25, 12, 22, -11, 90, 5, 0, 22 };
        int[] nonNegativeOriginal = { 170, 45, 75, 90, 802, 24, 2, 66 };

        System.out.println("  Original Input Array   : " + Arrays.toString(originalArray));
        System.out.println("  Non-negative Input     : " + Arrays.toString(nonNegativeOriginal));
        System.out.println();

        // 1. Bubble Sort
        int[] arr1 = originalArray.clone();
        Algorithms.bubbleSort(arr1);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "1. Bubble Sort", 
            Arrays.toString(arr1), isSorted(arr1));

        // 2. Selection Sort
        int[] arr2 = originalArray.clone();
        Algorithms.selectionSort(arr2);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "2. Selection Sort", 
            Arrays.toString(arr2), isSorted(arr2));

        // 3. Insertion Sort
        int[] arr3 = originalArray.clone();
        Algorithms.insertionSort(arr3);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "3. Insertion Sort", 
            Arrays.toString(arr3), isSorted(arr3));

        // 4. Merge Sort
        int[] arr4 = originalArray.clone();
        Algorithms.mergeSort(arr4);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "4. Merge Sort", 
            Arrays.toString(arr4), isSorted(arr4));

        // 5. Quick Sort
        int[] arr5 = originalArray.clone();
        Algorithms.quickSort(arr5);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "5. Quick Sort", 
            Arrays.toString(arr5), isSorted(arr5));

        // 6. Heap Sort
        int[] arr6 = originalArray.clone();
        Algorithms.heapSort(arr6);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "6. Heap Sort", 
            Arrays.toString(arr6), isSorted(arr6));

        // 7. Counting Sort
        int[] arr7 = originalArray.clone();
        Algorithms.countingSort(arr7);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "7. Counting Sort", 
            Arrays.toString(arr7), isSorted(arr7));

        // 8. Radix Sort
        int[] arr8 = nonNegativeOriginal.clone();
        Algorithms.radixSort(arr8);
        System.out.printf("  %-22s : %s (Sorted: %b)%n", "8. Radix Sort", 
            Arrays.toString(arr8), isSorted(arr8));

        // ─────────────────────────────────────────────────────────────────
        //  COMPLEXITY & PROPERTIES SUMMARY
        // ─────────────────────────────────────────────────────────────────
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("  SORTING ALGORITHMS COMPLEXITY SUMMARY");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println(" Algorithm      │ Best       │ Average    │ Worst      │ Space    │ Stable ");
        System.out.println(" ───────────────┼────────────┼────────────┼────────────┼──────────┼────────");
        System.out.println(" Bubble Sort    │ O(N)       │ O(N²)      │ O(N²)      │ O(1)     │ Yes    ");
        System.out.println(" Selection Sort │ O(N²)      │ O(N²)      │ O(N²)      │ O(1)     │ No     ");
        System.out.println(" Insertion Sort │ O(N)       │ O(N²)      │ O(N²)      │ O(1)     │ Yes    ");
        System.out.println(" Merge Sort     │ O(N log N) │ O(N log N) │ O(N log N) │ O(N)     │ Yes    ");
        System.out.println(" Quick Sort     │ O(N log N) │ O(N log N) │ O(N²)      │ O(log N) │ No     ");
        System.out.println(" Heap Sort      │ O(N log N) │ O(N log N) │ O(N log N) │ O(1)     │ No     ");
        System.out.println(" Counting Sort  │ O(N + K)   │ O(N + K)   │ O(N + K)   │ O(N + K) │ Yes    ");
        System.out.println(" Radix Sort     │ O(d*(N+B)) │ O(d*(N+B)) │ O(d*(N+B)) │ O(N + B) │ Yes    ");
        System.out.println("\n  N = Array size, K = Integer value range, B = Radix base (10), d = Max digits");
    }

    private static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
