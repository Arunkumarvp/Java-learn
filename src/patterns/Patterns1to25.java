package pattern;

public class Patterns1to25 {

    static int N = 5; // change this to resize all patterns

    // P1: Square of Stars
    // * * * * *
    // * * * * *
    static void p1() {
        System.out.println("P1: Square of Stars");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P2: Right-angled triangle (stars)
    // *
    // * *
    // * * *
    static void p2() {
        System.out.println("\nP2: Right-Angled Triangle (Stars)");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P3: Inverted right-angled triangle (stars)
    // * * * * *
    // * * * *
    // * * *
    static void p3() {
        System.out.println("\nP3: Inverted Right-Angled Triangle");
        for (int i = N; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P4: Right-angled triangle (numbers)
    // 1
    // 1 2
    // 1 2 3
    static void p4() {
        System.out.println("\nP4: Right-Angled Triangle (Numbers)");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P5: Right-angled triangle (same number per row)
    // 1
    // 2 2
    // 3 3 3
    static void p5() {
        System.out.println("\nP5: Right-Angled Triangle (Row Number)");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print(i + " ");
            System.out.println();
        }
    }

    // P6: Pyramid (centered star)
    //     *
    //    * * *
    //   * * * * *
    static void p6() {
        System.out.println("\nP6: Centered Star Pyramid");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
    }

    // P7: Inverted pyramid
    static void p7() {
        System.out.println("\nP7: Inverted Centered Pyramid");
        for (int i = N; i >= 1; i--) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
    }

    // P8: Diamond
    static void p8() {
        System.out.println("\nP8: Diamond");
        // upper half
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
        // lower half
        for (int i = N - 1; i >= 1; i--) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
    }

    // P9: Right triangle (alphabets)
    // A
    // A B
    // A B C
    static void p9() {
        System.out.println("\nP9: Right-Angled Triangle (Alphabets)");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print((char)('A' + j - 1) + " ");
            System.out.println();
        }
    }

    // P10: Same letter per row
    // A
    // B B
    // C C C
    static void p10() {
        System.out.println("\nP10: Same Letter Per Row");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print((char)('A' + i - 1) + " ");
            System.out.println();
        }
    }

    // P11: Hollow Square
    // * * * * *
    // *       *
    // * * * * *
    static void p11() {
        System.out.println("\nP11: Hollow Square");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == 1 || i == N || j == 1 || j == N) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P12: Hollow triangle
    static void p12() {
        System.out.println("\nP12: Hollow Right Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1 || j == i || i == N) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P13: Number pyramid (centered)
    //     1
    //    1 2 1
    //   1 2 3 2 1
    static void p13() {
        System.out.println("\nP13: Number Pyramid (Centered)");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print(j);
            for (int j = i - 1; j >= 1; j--) System.out.print(j);
            System.out.println();
        }
    }

    // P14: Pascal's triangle
    static void p14() {
        System.out.println("\nP14: Pascal's Triangle");
        for (int i = 0; i < N; i++) {
            for (int s = 0; s < N - i - 1; s++) System.out.print(" ");
            int val = 1;
            for (int j = 0; j <= i; j++) {
                System.out.print(val + " ");
                val = val * (i - j) / (j + 1);
            }
            System.out.println();
        }
    }

    // P15: Floyd's triangle (consecutive numbers)
    // 1
    // 2 3
    // 4 5 6
    static void p15() {
        System.out.println("\nP15: Floyd's Triangle");
        int num = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print(num++ + " ");
            System.out.println();
        }
    }

    // P16: Binary triangle (alternating 0 1)
    // 1
    // 0 1
    // 1 0 1
    static void p16() {
        System.out.println("\nP16: Binary Triangle");
        for (int i = 1; i <= N; i++) {
            int start = (i % 2 == 0) ? 0 : 1;
            for (int j = 1; j <= i; j++) {
                System.out.print(start + " ");
                start = 1 - start;
            }
            System.out.println();
        }
    }

    // P17: Right-angled triangle (descending numbers)
    // 5
    // 5 4
    // 5 4 3
    static void p17() {
        System.out.println("\nP17: Descending Numbers Triangle");
        for (int i = 1; i <= N; i++) {
            for (int j = N; j >= N - i + 1; j--) System.out.print(j + " ");
            System.out.println();
        }
    }

    // P18: Left-aligned inverted star triangle
    // * * * * *
    //   * * * *
    //     * * *
    static void p18() {
        System.out.println("\nP18: Left-Aligned Inverted Triangle");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s < i; s++) System.out.print("  ");
            for (int j = i; j <= N; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P19: Mirror right triangle
    //         *
    //       * *
    //     * * *
    static void p19() {
        System.out.println("\nP19: Mirror Right Triangle");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print("  ");
            for (int j = 1; j <= i; j++) System.out.print("* ");
            System.out.println();
        }
    }

    // P20: Hollow pyramid
    static void p20() {
        System.out.println("\nP20: Hollow Pyramid");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) {
                if (j == 1 || j == (2 * i - 1) || i == N) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    // P21: Hollow diamond
    static void p21() {
        System.out.println("\nP21: Hollow Diamond");
        for (int i = 1; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) {
                if (j == 1 || j == (2 * i - 1)) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
        for (int i = N - 1; i >= 1; i--) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) {
                if (j == 1 || j == (2 * i - 1)) System.out.print("*");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    // P22: Butterfly pattern
    static void p22() {
        System.out.println("\nP22: Butterfly");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            for (int s = 1; s <= 2 * (N - i); s++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
        for (int i = N; i >= 1; i--) {
            for (int j = 1; j <= i; j++) System.out.print("*");
            for (int s = 1; s <= 2 * (N - i); s++) System.out.print(" ");
            for (int j = 1; j <= i; j++) System.out.print("*");
            System.out.println();
        }
    }

    // P23: Hourglass
    static void p23() {
        System.out.println("\nP23: Hourglass");
        for (int i = N; i >= 1; i--) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
        for (int i = 2; i <= N; i++) {
            for (int s = 1; s <= N - i; s++) System.out.print(" ");
            for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
            System.out.println();
        }
    }

    // P24: Cross / Plus sign
    static void p24() {
        System.out.println("\nP24: Cross / Plus");
        int mid = N / 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == mid || j == mid) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    // P25: X pattern
    static void p25() {
        System.out.println("\nP25: X Pattern");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || i + j == N - 1) System.out.print("* ");
                else System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        p1();  p2();  p3();  p4();  p5();
        p6();  p7();  p8();  p9();  p10();
        p11(); p12(); p13(); p14(); p15();
        p16(); p17(); p18(); p19(); p20();
        p21(); p22(); p23(); p24(); p25();
    }
}
