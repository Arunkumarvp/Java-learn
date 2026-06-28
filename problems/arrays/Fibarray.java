import java.util.*;

public class Fibarray {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int[] arr = new int[101];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        for (int j = 0; j < arr.length; j++) {
            int a = 0, b = 1, n;
            n = a + b;
            System.out.println(n);
            a = b;
            b = n;
        }

    }
}
