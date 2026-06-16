# Programming Patterns

Pattern programming is a great way to understand the flow of nested loops, condition logic, and coordinates on a 2D plane. It builds strong logical thinking, especially for technical interviews.

## Common Pattern Formats

### 1. Square / Rectangular Patterns
These use two loops ($i$ for rows, $j$ for columns) running independently.
```text
* * * * *
* * * * *
* * * * *
* * * * *
```

### 2. Triangular Patterns
The inner loop bounds depend on the outer loop variable ($i$).
```text
* 
* * 
* * * 
* * * * 
* * * * * 
```
```java
for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= i; j++) {
        System.out.print("* ");
    }
    System.out.println();
}
```

### 3. Number Patterns
Instead of printing stars, you print numbers. This introduces variables that increment inside or outside the loops.
```text
1
1 2
1 2 3
1 2 3 4
```

### 4. Pyramid & Diamond Patterns
These require managing spaces before printing the characters to align them centrally.
```text
    *
   ***
  *****
 *******
```

### Logic Matrix Approach
When solving any pattern problem, map out the Rows ($i$) vs Columns ($j$) in a table to find the mathematical relationship.

| Row ($i$) | Stars | Spaces | Formula |
| :---: | :---: | :---: | :--- |
| 1 | 1 | 4 | $N - i$ spaces, $2i - 1$ stars |
| 2 | 3 | 3 | $N - i$ spaces, $2i - 1$ stars |
| 3 | 5 | 2 | $N - i$ spaces, $2i - 1$ stars |
