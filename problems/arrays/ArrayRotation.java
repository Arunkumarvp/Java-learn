import java.util.*;

// array left rotations
public class ArrayRotation {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int n = scn.nextInt();
		int[] arr = new int[5];
		for (int k = 0; k < arr.length; k++) {
			arr[k] = scn.nextInt();
		}

		for (int i = 0; i < arr.length; ++i) {
			int temp;
			temp = arr[0];
			for (int j = 0; j < arr.length - 1; ++j) {
				arr[j] = arr[j + 1];

			}
			arr[i] = temp;

		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

}
