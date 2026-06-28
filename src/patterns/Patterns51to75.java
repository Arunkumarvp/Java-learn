package pattern;

public class Patterns51to75 {

    static int N = 5;

    // P51: Star border with number fill
    static void p51() {
        System.out.println("P51: Star Border with Number Fill");
        int num = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 || i == N || j == 1 || j == N) System.out.print("*  ");
                else System.out.printf("%-3d", num++);
            }
            System.out.println();
        }
    }

    // P52: Triangle count per row
    // 1
    // 2 2
    // 3 3 3
    static void p52() {
        System.out.println("\nP52: Count Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print(i + " ");
            System.out.println();
        }
    }

    // P53: Odd number triangle
    // 1
    // 3 5
    // 7 9 11
    static void p53() {
        System.out.println("\nP53: Odd Numbers Triangle");
        int odd = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(odd + " ");
                odd += 2;
            }
            System.out.println();
        }
    }

    // P54: Even number triangle
    // 2
    // 4 6
    // 8 10 12
    static void p54() {
        System.out.println("\nP54: Even Numbers Triangle");
        int even = 2;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(even + " ");
                even += 2;
            }
            System.out.println();
        }
    }

    // P55: Right triangle of character '@'
    static void p55() {
        System.out.println("\nP55: @ Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print("@ ");
            System.out.println();
        }
    }

    // P56: Square of row*col
    static void p56() {
        System.out.println("\nP56: Row*Col Grid");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) System.out.printf("%4d", i * j);
            System.out.println();
        }
    }

    // P57: Border of letters
    static void p57() {
        System.out.println("\nP57: Letter Border");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == 0 || i == N-1 || j == 0 || j == N-1)
                    System.out.print((char)('A' + (i + j) % 26) + " ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P58: Diamond with numbers
    //   1
    //  2 2
    // 3 3 3
    static void p58() {
        System.out.println("\nP58: Number Diamond");
        for (int i = 1; i <= N; i++) {
            for (int s = N; s > i; s--) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print(i + " ");
            System.out.println();
        }
        for (int i = N-1; i >= 1; i--) {
            for (int s = N; s > i; s--) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print(i + " ");
            System.out.println();
        }
    }

    // P59: Column-wise number increment square
    // 1 2 3 4 5
    // 2 3 4 5 6
    // 3 4 5 6 7
    static void p59() {
        System.out.println("\nP59: Diagonal Number Square");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) System.out.printf("%3d", i + j - 1);
            System.out.println();
        }
    }

    // P60: Identity matrix
    static void p60() {
        System.out.println("\nP60: Identity Matrix");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print((i == j ? "1 " : "0 "));
            System.out.println();
        }
    }

    // P61: Right triangle with repeating letter
    // A
    // B B
    // C C C
    static void p61() {
        System.out.println("\nP61: Repeating Letter Triangle");
        for (int i = 0; i < N; i++) {
            char ch = (char)('A' + i);
            for (int j = 0; j <= i; j++) System.out.print(ch + " ");
            System.out.println();
        }
    }

    // P62: Number pyramid with spaces
    //         1
    //       2 1 2
    //     3 2 1 2 3
    static void p62() {
        System.out.println("\nP62: Symmetric Number Pyramid");
        for (int i = 1; i <= N; i++) {
            for (int s = N-i; s > 0; s--) System.out.print("  ");
            for (int j = i; j >= 1; j--) System.out.print(j + " ");
            for (int j = 2; j <= i; j++) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P63: Star pattern – right angle with hypotenuse
    static void p63() {
        System.out.println("\nP63: Triangle with Hypotenuse");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1 || i == N || i == j) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P64: Square spiral flag (using 0/1)
    static void p64() {
        System.out.println("\nP64: Diagonal Stripe (0/1)");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print((i + j) % 2 == 0 ? "1 " : "0 ");
            System.out.println();
        }
    }

    // P65: Number increases left to right, top to bottom in triangle
    // 1
    // 2 3
    // 4 5 6
    static void p65() {
        System.out.println("\nP65: Sequential Triangle");
        int num = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print(num++ + " ");
            System.out.println();
        }
    }

    // P66: Alphabet A-Z wrap triangle
    static void p66() {
        System.out.println("\nP66: Alphabet Wrap Triangle");
        int ch = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print((char)('A' + ch++ % 26) + " ");
            System.out.println();
        }
    }

    // P67: Mirrored number pyramid
    //         1
    //       2 1 2
    //     3 2 1 2 3
    static void p67() {
        System.out.println("\nP67: Mirrored Number Pyramid");
        for (int i = 1; i <= N; i++) {
            for (int s = N-i; s > 0; s--) System.out.print(" ");
            for (int j = i; j >= 1; j--) System.out.print(j);
            for (int j = 2; j <= i; j++) System.out.print(j);
            System.out.println();
        }
    }

    // P68: Concentric square
    static void p68() {
        System.out.println("\nP68: Concentric Square (Min Distance)");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int val = Math.min(Math.min(i, j), Math.min(N-1-i, N-1-j));
                System.out.print((N/2 - val) + " ");
            }
            System.out.println();
        }
    }

    // P69: Star columns
    // *   *   *
    // * * * * *
    // * * * * *
    static void p69() {
        System.out.println("\nP69: Star Columns (Col Boundary)");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 && j % 2 == 1) System.out.print("* ");
                else if (i > 1) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P70: Inverted pyramid of letters
    // E D C B A
    //  D C B A
    //    C B A
    static void p70() {
        System.out.println("\nP70: Inverted Letter Pyramid");
        for (int i = 0; i < N; i++) {
            for (int s = 0; s < i; s++) System.out.print("  ");
            for (int j = N-1; j >= i; j--) System.out.print((char)('A'+j) + " ");
            System.out.println();
        }
    }

    // P71: Triangle of 1s (ones)
    static void p71() {
        System.out.println("\nP71: Triangle of 1s");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print("1 ");
            System.out.println();
        }
    }

    // P72: Reverse Floyd's triangle
    static void p72() {
        System.out.println("\nP72: Reverse Floyd's Triangle");
        int total = N * (N + 1) / 2;
        for (int i = N; i >= 1; i--) {
            for (int j = total; j > total - i; j--) System.out.print(j + " ");
            total -= i;
            System.out.println();
        }
    }

    // P73: Row-wise power triangle
    // 1^1
    // 2^1 2^2
    // 3^1 3^2 3^3
    static void p73() {
        System.out.println("\nP73: Power Triangle");
        for (int i = 1; i <= N; i++) {
            int val = 1;
            for (int j = 1; j <= i; j++) {
                val *= i;
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
    }

    // P74: String pattern – word staircase
    static void p74() {
        System.out.println("\nP74: Word Staircase");
        String word = "JAVA";
        for (int i = 1; i <= word.length(); i++) {
            System.out.println(word.substring(0, i));
        }
    }

    // P75: Number clock pattern (circular-ish square)
    static void p75() {
        System.out.println("\nP75: Min Distance from Corner");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int d = Math.min(Math.min(i, j), Math.min(N-1-i, N-1-j));
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        p51(); p52(); p53(); p54(); p55();
        p56(); p57(); p58(); p59(); p60();
        p61(); p62(); p63(); p64(); p65();
        p66(); p67(); p68(); p69(); p70();
        p71(); p72(); p73(); p74(); p75();
    }
}
