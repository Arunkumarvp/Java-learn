package pattern;

public class Patterns26to50 {

    static int N = 5;

    // P26: Number border square
    static void p26() {
        System.out.println("P26: Number Border Square");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 || i == N || j == 1 || j == N) System.out.print(i + "" + j + " ");
                else System.out.print("   ");
            }
            System.out.println();
        }
    }

    // P27: Staircase right-aligned
    //         *
    //       * *
    //     * * *
    static void p27() {
        System.out.println("\nP27: Right-Aligned Staircase");
        for (int i = 1; i <= N; i++) {
            for (int s = 0; s < N - i; s++) System.out.print("  ");
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P28: Zigzag pattern (3-row zigzag)
    static void p28() {
        System.out.println("\nP28: Zigzag (3 rows)");
        int cols = 2 * N;
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= cols; j++) {
                if ((i == 1 && j % 4 == 1) || (i == 2 && j % 4 == 2) ||
                    (i == 2 && j % 4 == 0) || (i == 3 && j % 4 == 3))
                    System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    // P29: Checkerboard
    static void p29() {
        System.out.println("\nP29: Checkerboard");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if ((i + j) % 2 == 0) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P30: Spiral (outer boundary only)
    static void p30() {
        System.out.println("\nP30: Spiral Numbers");
        int[][] mat = new int[N][N];
        int top = 0, bot = N - 1, left = 0, right = N - 1, num = 1;
        while (top <= bot && left <= right) {
            for (int i = left; i <= right; i++)   mat[top][i] = num++;
            top++;
            for (int i = top; i <= bot; i++)       mat[i][right] = num++;
            right--;
            if (top <= bot) { for (int i = right; i >= left; i--) mat[bot][i] = num++; bot--; }
            if (left <= right) { for (int i = bot; i >= top; i--) mat[i][left] = num++; left++; }
        }
        for (int[] row : mat) {
            for (int v : row) System.out.printf("%3d", v);
            System.out.println();
        }
    }

    // P31: Alphabet pyramid centered
    //     A
    //    A B A
    //   A B C B A
    static void p31() {
        System.out.println("\nP31: Alphabet Pyramid (Centered)");
        for (int i = 0; i < N; i++) {
            for (int s = 0; s < N - i - 1; s++) System.out.print(" ");
            for (int j = 0; j <= i; j++) System.out.print((char)('A' + j));
            for (int j = i - 1; j >= 0; j--) System.out.print((char)('A' + j));
            System.out.println();
        }
    }

    // P32: Inverted number triangle
    // 1 2 3 4 5
    // 1 2 3 4
    // 1 2 3
    static void p32() {
        System.out.println("\nP32: Inverted Number Triangle");
        for (int i = N; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P33: Parallelogram
    static void p33() {
        System.out.println("\nP33: Parallelogram");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= N; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P34: Rhombus
    static void p34() {
        System.out.println("\nP34: Rhombus");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= i - 1; s++) System.out.print(" ");
            for (int j = 1; j <= N; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P35: Number rhombus
    static void p35() {
        System.out.println("\nP35: Number Rhombus");
        for (int i = 1; i <= N; i++) {
            for (int s = N; s > i; s--) System.out.print("  ");
            for (int j = 1; j <= i; j++) System.out.print(j + " ");
            for (int j = i - 1; j >= 1; j--) System.out.print(j + " ");
            System.out.println();
        }
        for (int i = N - 1; i >= 1; i--) {
            for (int s = N; s > i; s--) System.out.print("  ");
            for (int j = 1; j <= i; j++) System.out.print(j + " ");
            for (int j = i - 1; j >= 1; j--) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P36: Heart shape (large N recommended, use 6)
    static void p36() {
        System.out.println("\nP36: Heart Shape");
        int n = 6;
        for (int i = n / 2; i <= n; i += 2) {
            for (int j = 1; j < n - i; j += 2) System.out.print(" ");
            System.out.print("***");
            for (int j = 1; j < i - 1; j++) System.out.print(" ");
            System.out.print("***");
            System.out.println();
        }
        for (int i = n; i >= 1; i--) {
            for (int j = i; j < n; j++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
    }

    // P37: Arrow pointing right
    static void p37() {
        System.out.println("\nP37: Right Arrow");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
        for (int i = N - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
    }

    // P38: Sandglass of numbers
    // 1 2 3 4 5
    //  1 2 3 4
    //   1 2 3
    static void p38() {
        System.out.println("\nP38: Sandglass Numbers");
        for (int i = 0; i < N; i++) {
            for (int s = 0; s < i; s++) System.out.print(" ");
            for (int j = 1; j <= N - i; j++) System.out.print(j + " ");
            System.out.println();
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int s = 0; s < i; s++) System.out.print(" ");
            for (int j = 1; j <= N - i; j++) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P39: Left Pascal triangle
    // *
    // * *
    // * * *
    // * *
    // *
    static void p39() {
        System.out.println("\nP39: Left Pascal Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
        for (int i = N - 1; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P40: Right Pascal triangle (mirror)
    static void p40() {
        System.out.println("\nP40: Right Pascal Triangle");
        for (int i = 1; i <= N; i++) {
            for (int s = N; s > i; s--) System.out.print("  ");
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
        for (int i = N - 1; i >= 1; i--) {
            for (int s = N; s > i; s--) System.out.print("  ");
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P41: Fibonacci triangle
    static void p41() {
        System.out.println("\nP41: Fibonacci Triangle");
        int a = 0, b = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(a + " ");
                int c = a + b; a = b; b = c;
            }
            System.out.println();
        }
    }

    // P42: Prime number triangle
    static void p42() {
        System.out.println("\nP42: Prime Number Triangle");
        int candidate = 2;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                while (!isPrime(candidate)) candidate++;
                System.out.print(candidate++ + " ");
            }
            System.out.println();
        }
    }
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) if (n % i == 0) return false;
        return true;
    }

    // P43: Square number pattern
    // 1  2  3  4  5
    // 4  5  6  7  8
    // 9 10 11 12 13
    static void p43() {
        System.out.println("\nP43: Square Number Pattern");
        int num = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) System.out.printf("%3d", num++);
            num += (N - 1);
            System.out.println();
        }
    }

    // P44: Multiplication table grid
    static void p44() {
        System.out.println("\nP44: Multiplication Table Grid");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) System.out.printf("%4d", i * j);
            System.out.println();
        }
    }

    // P45: Wave pattern
    static void p45() {
        System.out.println("\nP45: Wave Pattern");
        for (int j = 1; j <= N; j++) {
            for (int i = 1; i <= N; i++) {
                if (i == j || i == N - j + 1) System.out.print("* ");
                else if (j % 2 == 1 && i < j) System.out.print("* ");
                else if (j % 2 == 0 && i > N - j + 1) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P46: Square with diagonals marked
    static void p46() {
        System.out.println("\nP46: Square with Diagonals");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || i + j == N - 1 || i == 0 || i == N - 1 || j == 0 || j == N - 1)
                    System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P47: Number increasing per column
    // 1 2 3 4 5
    // 1 2 3 4 5
    static void p47() {
        System.out.println("\nP47: Column Numbers");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P48: Descending right triangle with letters
    // E
    // E D
    // E D C
    static void p48() {
        System.out.println("\nP48: Descending Letter Triangle");
        for (int i = N; i >= 1; i--) {
            for (int j = N; j >= i; j--) System.out.print((char)('A' + j - 1) + " ");
            System.out.println();
        }
    }

    // P49: Palindrome number triangle
    //     1
    //    121
    //   12321
    static void p49() {
        System.out.println("\nP49: Palindrome Number Triangle");
        for (int i = 1; i <= N; i++) {
            for (int s = N; s > i; s--) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print(j);
            for (int j = i - 1; j >= 1; j--) System.out.print(j);
            System.out.println();
        }
    }

    // P50: Power of 2 triangle
    // 1
    // 1 2
    // 1 2 4
    // 1 2 4 8
    static void p50() {
        System.out.println("\nP50: Powers of 2 Triangle");
        for (int i = 1; i <= N; i++) {
            int val = 1;
            for (int j = 1; j <= i; j++) {
                System.out.print(val + " ");
                val *= 2;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        p26(); p27(); p28(); p29(); p30();
        p31(); p32(); p33(); p34(); p35();
        p36(); p37(); p38(); p39(); p40();
        p41(); p42(); p43(); p44(); p45();
        p46(); p47(); p48(); p49(); p50();
    }
}
