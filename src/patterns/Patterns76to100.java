package pattern;

public class Patterns76to100 {

    static int N = 5;

    // P76: Star inside number border
    static void p76() {
        System.out.println("P76: Star inside Number Border");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 || i == N) System.out.print(j + " ");
                else if (j == 1 || j == N) System.out.print(i + " ");
                else System.out.print("* ");
            }
            System.out.println();
        }
    }

    // P77: Inverted alphabet triangle
    // A B C D E
    // A B C D
    // A B C
    static void p77() {
        System.out.println("\nP77: Inverted Alphabet Triangle");
        for (int i = N; i >= 1; i--) {
            for (int j = 0; j < i; j++) System.out.print((char)('A' + j) + " ");
            System.out.println();
        }
    }

    // P78: Border diamond (outer diamond)
    static void p78() {
        System.out.println("\nP78: Diamond Border");
        for (int i = 1; i <= N; i++) {
            for (int s = N-i; s > 0; s--) System.out.print(" ");
            System.out.print("*");
            if (i > 1) {
                for (int s = 0; s < 2*(i-1)-1; s++) System.out.print(" ");
                System.out.print("*");
            }
            System.out.println();
        }
        for (int i = N-1; i >= 1; i--) {
            for (int s = N-i; s > 0; s--) System.out.print(" ");
            System.out.print("*");
            if (i > 1) {
                for (int s = 0; s < 2*(i-1)-1; s++) System.out.print(" ");
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // P79: Alternate star and space rows
    static void p79() {
        System.out.println("\nP79: Alternating Star/Space Rows");
        for (int i = 1; i <= N; i++) {
            if (i % 2 == 1)
                for (int j = 1; j <= N; j++) System.out.print("* ");
            else
                for (int j = 1; j <= N; j++) System.out.print("  ");
            System.out.println();
        }
    }

    // P80: Number border increasing per side
    static void p80() {
        System.out.println("\nP80: Layered Distance Square");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int layer = Math.min(Math.min(i, j), Math.min(N-1-i, N-1-j)) + 1;
                System.out.print(layer + " ");
            }
            System.out.println();
        }
    }

    // P81: Factorial triangle
    static void p81() {
        System.out.println("\nP81: Factorial Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.printf("%5d", factorial(j));
            System.out.println();
        }
    }
    static long factorial(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }

    // P82: Reverse number rows
    // 5 4 3 2 1
    // 4 3 2 1
    // 3 2 1
    static void p82() {
        System.out.println("\nP82: Reverse Number Rows");
        for (int i = N; i >= 1; i--) {
            for (int j = i; j >= 1; j--) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P83: Square of same digit
    // 1 1 1 1 1
    // 2 2 2 2 2
    // 3 3 3 3 3
    static void p83() {
        System.out.println("\nP83: Same Digit Rows");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) System.out.print(i + " ");
            System.out.println();
        }
    }

    // P84: Roman numeral triangle (simple i, ii, iii using count)
    static void p84() {
        System.out.println("\nP84: Count Repetition Triangle");
        String[] rep = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 1; i <= Math.min(N, 10); i++) {
            for (int j = 1; j <= i; j++) System.out.print(rep[j-1] + " ");
            System.out.println();
        }
    }

    // P85: Alphabet diamond
    static void p85() {
        System.out.println("\nP85: Alphabet Diamond");
        for (int i = 0; i < N; i++) {
            for (int s = N-1-i; s > 0; s--) System.out.print(" ");
            for (int j = 0; j <= i; j++) System.out.print((char)('A'+j));
            for (int j = i-1; j >= 0; j--) System.out.print((char)('A'+j));
            System.out.println();
        }
        for (int i = N-2; i >= 0; i--) {
            for (int s = N-1-i; s > 0; s--) System.out.print(" ");
            for (int j = 0; j <= i; j++) System.out.print((char)('A'+j));
            for (int j = i-1; j >= 0; j--) System.out.print((char)('A'+j));
            System.out.println();
        }
    }

    // P86: Staircase with numbers
    static void p86() {
        System.out.println("\nP86: Number Staircase");
        for (int i = 1; i <= N; i++) {
            for (int s = N-i; s > 0; s--) System.out.print("  ");
            for (int j = 1; j <= i; j++) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P87: Square brackets pattern
    static void p87() {
        System.out.println("\nP87: Corner Brackets");
        int side = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean top  = i < side;
                boolean bot  = i >= N - side;
                boolean left = j < side;
                boolean right= j >= N - side;
                if ((top || bot) && (left || right)) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P88: Inverted Pascal star
    // * * * * *
    // * * * *
    // * * *
    // * *
    // *
    static void p88() {
        System.out.println("\nP88: Inverted Pascal");
        for (int i = N; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P89: Sum of rows triangle
    // Row sums: 1, 1+2=3, ...
    static void p89() {
        System.out.println("\nP89: Row Sum Triangle");
        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= i; j++) sum += j;
            for (int j = 1; j <= i; j++) System.out.print(j + " ");
            System.out.print("= " + sum);
            System.out.println();
        }
    }

    // P90: Zigzag number column
    static void p90() {
        System.out.println("\nP90: Zigzag Column");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (j % 2 == 1) System.out.print(i + " ");
                else System.out.print((N - i + 1) + " ");
            }
            System.out.println();
        }
    }

    // P91: Solid rectangle
    static void p91() {
        System.out.println("\nP91: Solid Rectangle (3x7)");
        int rows = 3, cols = 7;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P92: Triangle of squares of numbers
    // 1
    // 4 9
    // 16 25 36
    static void p92() {
        System.out.println("\nP92: Squares Triangle");
        int n = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.printf("%4d", n++ * n);
            System.out.println();
        }
    }

    // P93: Letter reversal per row
    // A B C D E
    // E D C B A
    // A B C D E
    static void p93() {
        System.out.println("\nP93: Alternating Letter Rows");
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0)
                for (int j = 0; j < N; j++) System.out.print((char)('A'+j) + " ");
            else
                for (int j = N-1; j >= 0; j--) System.out.print((char)('A'+j) + " ");
            System.out.println();
        }
    }

    // P94: Right hollow triangle
    static void p94() {
        System.out.println("\nP94: Right Hollow Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i || i == N) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P95: Counting triangle (row label)
    // Row 1: 1
    // Row 2: 1 2
    static void p95() {
        System.out.println("\nP95: Labeled Row Triangle");
        for (int i = 1; i <= N; i++) {
            System.out.print("Row " + i + ": ");
            for (int j = 1; j <= i; j++) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P96: Symmetric star hour glass
    static void p96() {
        System.out.println("\nP96: Symmetric Hourglass");
        for (int i = N; i >= 1; i--) {
            for (int s = 0; s < N-i; s++) System.out.print(" ");
            for (int j = 0; j < i; j++) System.out.print("* ");
            System.out.println();
        }
        for (int i = 2; i <= N; i++) {
            for (int s = 0; s < N-i; s++) System.out.print(" ");
            for (int j = 0; j < i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P97: Number maze (snake pattern)
    static void p97() {
        System.out.println("\nP97: Snake Number Matrix");
        int num = 1;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0)
                for (int j = 1; j <= N; j++) System.out.printf("%3d", num++);
            else
                for (int j = N; j >= 1; j--) System.out.printf("%3d", num++);
            System.out.println();
        }
    }

    // P98: Triangle of alternating stars and dots
    // *
    // . *
    // * . *
    static void p98() {
        System.out.println("\nP98: Alternating Star-Dot Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++)
                System.out.print(((i + j) % 2 == 0 ? "*" : ".") + " ");
            System.out.println();
        }
    }

    // P99: Hashing staircase
    // #
    // ##
    // ###
    static void p99() {
        System.out.println("\nP99: Hash Staircase");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print("#");
            System.out.println();
        }
    }

    // P100: Full diamond with border numbers
    static void p100() {
        System.out.println("\nP100: Full Diamond with Row Numbers");
        for (int i = 1; i <= N; i++) {
            System.out.printf("%-3d", i);
            for (int s = N-i; s > 0; s--) System.out.print(" ");
            for (int j = 1; j <= (2*i-1); j++) System.out.print("*");
            System.out.println();
        }
        for (int i = N-1; i >= 1; i--) {
            System.out.printf("%-3d", i);
            for (int s = N-i; s > 0; s--) System.out.print(" ");
            for (int j = 1; j <= (2*i-1); j++) System.out.print("*");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        p76();  p77();  p78();  p79();  p80();
        p81();  p82();  p83();  p84();  p85();
        p86();  p87();  p88();  p89();  p90();
        p91();  p92();  p93();  p94();  p95();
        p96();  p97();  p98();  p99();  p100();
    }
}
