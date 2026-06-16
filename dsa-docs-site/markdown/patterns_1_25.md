# Patterns 1 to 25

Here are the implementations and expected outputs for this pattern set.

### P1: Square of Stars

**Output shape:**
```text
* * * * *
* * * * *
```

**Java Code:**
```java
static void p1() {
    System.out.println("P1: Square of Stars");
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) System.out.print("* ");
        System.out.println();
    }
}
```

---

### P2: Right-angled triangle (stars)

**Output shape:**
```text
*
* *
* * *
```

**Java Code:**
```java
static void p2() {
    System.out.println("\nP2: Right-Angled Triangle (Stars)");
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= i; j++) System.out.print("* ");
        System.out.println();
    }
}
```

---

### P3: Inverted right-angled triangle (stars)

**Output shape:**
```text
* * * * *
* * * *
* * *
```

**Java Code:**
```java
static void p3() {
    System.out.println("\nP3: Inverted Right-Angled Triangle");
    for (int i = N; i >= 1; i--) {
        for (int j = 1; j <= i; j++) System.out.print("* ");
        System.out.println();
    }
}
```

---

### P4: Right-angled triangle (numbers)

**Output shape:**
```text
1
1 2
1 2 3
```

**Java Code:**
```java
static void p4() {
    System.out.println("\nP4: Right-Angled Triangle (Numbers)");
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= i; j++) System.out.print(j + " ");
        System.out.println();
    }
}
```

---

### P5: Right-angled triangle (same number per row)

**Output shape:**
```text
1
2 2
3 3 3
```

**Java Code:**
```java
static void p5() {
    System.out.println("\nP5: Right-Angled Triangle (Row Number)");
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= i; j++) System.out.print(i + " ");
        System.out.println();
    }
}
```

---

### P6: Pyramid (centered star)

**Output shape:**
```text
    *
   * * *
  * * * * *
```

**Java Code:**
```java
static void p6() {
    System.out.println("\nP6: Centered Star Pyramid");
    for (int i = 1; i <= N; i++) {
        for (int s = 1; s <= N - i; s++) System.out.print(" ");
        for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
        System.out.println();
    }
}
```

---

### P7: Inverted pyramid

**Output shape:**
```text
```

**Java Code:**
```java
static void p7() {
    System.out.println("\nP7: Inverted Centered Pyramid");
    for (int i = N; i >= 1; i--) {
        for (int s = 1; s <= N - i; s++) System.out.print(" ");
        for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
        System.out.println();
    }
}
```

---

### P8: Diamond

**Output shape:**
```text
upper half
lower half
```

**Java Code:**
```java
static void p8() {
    System.out.println("\nP8: Diamond");
    for (int i = 1; i <= N; i++) {
        for (int s = 1; s <= N - i; s++) System.out.print(" ");
        for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
        System.out.println();
    }
    for (int i = N - 1; i >= 1; i--) {
        for (int s = 1; s <= N - i; s++) System.out.print(" ");
        for (int j = 1; j <= (2 * i - 1); j++) System.out.print("*");
        System.out.println();
    }
}
```

---

### P9: Right triangle (alphabets)

**Output shape:**
```text
A
A B
A B C
```

**Java Code:**
```java
static void p9() {
    System.out.println("\nP9: Right-Angled Triangle (Alphabets)");
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= i; j++) System.out.print((char)('A' + j - 1) + " ");
        System.out.println();
    }
}
```

---

### P10: Same letter per row

**Output shape:**
```text
A
B B
C C C
```

**Java Code:**
```java
static void p10() {
    System.out.println("\nP10: Same Letter Per Row");
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= i; j++) System.out.print((char)('A' + i - 1) + " ");
        System.out.println();
    }
}
```

---

### P11: Hollow Square

**Output shape:**
```text
* * * * *
*       *
* * * * *
```

**Java Code:**
```java
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
```

---

### P12: Hollow triangle

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

### P13: Number pyramid (centered)

**Output shape:**
```text
    1
   1 2 1
  1 2 3 2 1
```

**Java Code:**
```java
static void p13() {
    System.out.println("\nP13: Number Pyramid (Centered)");
    for (int i = 1; i <= N; i++) {
        for (int s = 1; s <= N - i; s++) System.out.print(" ");
        for (int j = 1; j <= i; j++) System.out.print(j);
        for (int j = i - 1; j >= 1; j--) System.out.print(j);
        System.out.println();
    }
}
```

---

### P14: Pascal's triangle

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

### P15: Floyd's triangle (consecutive numbers)

**Output shape:**
```text
1
2 3
4 5 6
```

**Java Code:**
```java
static void p15() {
    System.out.println("\nP15: Floyd's Triangle");
    int num = 1;
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= i; j++) System.out.print(num++ + " ");
        System.out.println();
    }
}
```

---

### P16: Binary triangle (alternating 0 1)

**Output shape:**
```text
1
0 1
1 0 1
```

**Java Code:**
```java
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
```

---

### P17: Right-angled triangle (descending numbers)

**Output shape:**
```text
5
5 4
5 4 3
```

**Java Code:**
```java
static void p17() {
    System.out.println("\nP17: Descending Numbers Triangle");
    for (int i = 1; i <= N; i++) {
        for (int j = N; j >= N - i + 1; j--) System.out.print(j + " ");
        System.out.println();
    }
}
```

---

### P18: Left-aligned inverted star triangle

**Output shape:**
```text
* * * * *
  * * * *
    * * *
```

**Java Code:**
```java
static void p18() {
    System.out.println("\nP18: Left-Aligned Inverted Triangle");
    for (int i = 1; i <= N; i++) {
        for (int s = 1; s < i; s++) System.out.print("  ");
        for (int j = i; j <= N; j++) System.out.print("* ");
        System.out.println();
    }
}
```

---

### P19: Mirror right triangle

**Output shape:**
```text
        *
      * *
    * * *
```

**Java Code:**
```java
static void p19() {
    System.out.println("\nP19: Mirror Right Triangle");
    for (int i = 1; i <= N; i++) {
        for (int s = 1; s <= N - i; s++) System.out.print("  ");
        for (int j = 1; j <= i; j++) System.out.print("* ");
        System.out.println();
    }
}
```

---

### P20: Hollow pyramid

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

### P21: Hollow diamond

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

### P22: Butterfly pattern

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

### P23: Hourglass

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

### P24: Cross / Plus sign

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

### P25: X pattern

**Output shape:**
```text
```

**Java Code:**
```java
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
```

---

