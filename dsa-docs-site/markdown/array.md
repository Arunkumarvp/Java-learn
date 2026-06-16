# Arrays

An **Array** is a linear data structure that collects elements of the same data type and stores them in contiguous and adjacent memory locations. Arrays work on an index system starting from 0.

## Types of Arrays
1. **One-Dimensional Arrays**: Elements are stored in a single continuous row.
2. **Multi-Dimensional Arrays**: Data is stored in tabular form, like a 2D matrix.

## Key Operations

### 1. Array Rotation
Rotating an array involves shifting its elements to the left or right by a certain number of positions.

```mermaid
graph TD
    subgraph Step 1: Store First Element
        direction LR
        A1[1] --> A2[2] --> A3[3] --> A4[4] --> A5[5]
        temp[temp = 1]
        style A1 fill:#f43f5e,color:#fff
        style temp fill:#8b5cf6,color:#fff
    end
    subgraph Step 2: Shift Left
        direction LR
        S1[2] --> S2[3] --> S3[4] --> S4[5] --> S5[5]
    end
    subgraph Step 3: Insert Temp at End
        direction LR
        F1[2] --> F2[3] --> F3[4] --> F4[5] --> F5[1]
        style F5 fill:#06b6d4,color:#fff
    end
```

```java
// Example: Left Rotation by 1
int temp = arr[0];
for (int j = 0; j < arr.length - 1; ++j) {
    arr[j] = arr[j + 1];
}
arr[arr.length - 1] = temp;
```

### 2. Finding Duplicates
Checking if an array contains duplicate values. This can be visualized as comparing every element with all subsequent elements.

```mermaid
graph LR
    subgraph Outer Loop i=0
        direction LR
        Pivot[1] -.-> cmp1[3]
        Pivot -.-> cmp2[1]
        style cmp2 fill:#f43f5e,color:#fff
        style Pivot fill:#8b5cf6,color:#fff
    end
```

```java
for(int i = 0; i < size; i++) {
    for(int j = i + 1; j < arr.length; j++) {
        if(arr[i] == arr[j]) {
            System.out.println("duplicate: " + arr[j]);
        }
    }
}
```

### 3. Frequency of Elements
Counting how many times each element appears in the array. This is often solved by keeping a frequency array or using a HashMap.

```mermaid
graph TD
    A[Array: 1, 2, 2, 3] --> count1(1 occurs 1 time)
    A --> count2(2 occurs 2 times)
    A --> count3(3 occurs 1 time)
```

## Array Characteristics
| Characteristic | Description |
| :--- | :--- |
| **Fixed Size** | Once initialized, the size of a standard array cannot be changed. |
| **Fast Lookup** | Accessing an element by index takes $O(1)$ time. |
| **Slow Insertion** | Inserting or deleting an element in the middle takes $O(N)$ time as elements must be shifted. |
