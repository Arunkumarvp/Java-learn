# Queue Data Structure

A **Queue** is a linear data structure that follows the **FIFO (First In, First Out)** principle. The first element added to the queue will be the first one to be removed, similar to a line of people waiting for a ticket.

## Structure and Visual Representation

A Queue has two pointers:
- **Front**: Points to the first element (where elements are removed/dequeued).
- **Rear**: Points to the last element (where elements are inserted/enqueued).

```mermaid
graph LR
    subgraph Queue representation
        direction LR
        Front[Front Pointer] --> Head[Element 1]
        Head --> B[Element 2]
        B --> C[Element 3]
        C --> Tail[Element 4]
        Rear[Rear Pointer] --> Tail
        style Front fill:#8b5cf6,color:#fff
        style Rear fill:#06b6d4,color:#fff
        style Head fill:#1e293b,color:#fff
        style Tail fill:#1e293b,color:#fff
    end
```

## Standard Queue Operations

| Operation | Description | Time Complexity | Space Complexity |
| :--- | :--- | :---: | :---: |
| **Enqueue** | Inserts an element at the rear of the queue | $O(1)$ | $O(1)$ |
| **Dequeue** | Removes and returns the element at the front | $O(1)$ | $O(1)$ |
| **Peek / Front**| Returns the front element without removing it | $O(1)$ | $O(1)$ |
| **isEmpty** | Checks if the queue is empty | $O(1)$ | $O(1)$ |

---

## Step-by-Step Operation Diagrams

### 1. Enqueue Operation
Adding `Element 5` to the queue. The `Rear` pointer moves to point to the new element.

```mermaid
graph TD
    subgraph Before Enqueue
        direction LR
        F1[Front] --> d1[Element 1]
        d1 --> d2[Element 2]
        R1[Rear] --> d2
    end
    subgraph After Enqueue
        direction LR
        F2[Front] --> d1b[Element 1]
        d1b --> d2b[Element 2]
        d2b --> d3[New Element 5]
        R2[Rear] --> d3
        style d3 fill:#06b6d4,color:#fff
    end
```

### 2. Dequeue Operation
Removing the front element. The `Front` pointer moves to the next element.

```mermaid
graph TD
    subgraph Before Dequeue
        direction LR
        F1[Front] --> d1[Element 1]
        d1 --> d2[Element 2]
        R1[Rear] --> d2
        style d1 fill:#f43f5e,color:#fff
    end
    subgraph After Dequeue
        direction LR
        F2[Front] --> d2b[Element 2]
        R2[Rear] --> d2b
        style d2b fill:#8b5cf6,color:#fff
    end
```

---

## Advanced Queue Variations

### 3. Deque (Double Ended Queue)
A Deque allows insertion and deletion from **both** the Front and the Rear.

```mermaid
graph LR
    subgraph Deque Operations
        direction LR
        InFront(("Insert Front")) --> d1[Element 1]
        d1 --> OutFront(("Delete Front"))
        
        InRear(("Insert Rear")) --> dN[Element N]
        dN --> OutRear(("Delete Rear"))
        
        d1 -.-> d2["[...]"]
        d2 -.-> dN
        
        style InFront fill:#10b981,color:#fff
        style OutFront fill:#f43f5e,color:#fff
        style InRear fill:#10b981,color:#fff
        style OutRear fill:#f43f5e,color:#fff
    end
```
- **insertFront()**: Adds an element to the front of the deque.
- **insertRear()**: Adds an element to the rear of the deque.
- **deleteFront()**: Removes the front element.
- **deleteRear()**: Removes the rear element.

### 4. Priority Queue
Elements are enqueued with a **priority value**. Dequeue operations always remove the element with the highest priority first, regardless of when it was enqueued.

```mermaid
graph TD
    subgraph Enqueue Phase
        A["New Task: Prio 5"] --> Q1
        B["New Task: Prio 1"] --> Q1
        C["New Task: Prio 9"] --> Q1
    end
    
    subgraph Priority Queue Internal State
        Q1[Queue] --> Sorted[Sorted by Priority]
        Sorted --> T1["Task: Prio 9"]
        Sorted --> T2["Task: Prio 5"]
        Sorted --> T3["Task: Prio 1"]
        style T1 fill:#8b5cf6,color:#fff
    end
    
    subgraph Dequeue Phase
        T1 --> Out[Highest Priority dequeued first]
    end
```
- **Insert / Enqueue**: Adds the item based on its priority.
- **Delete / Dequeue**: Extracts the maximum (or minimum) priority element. Typically implemented using a Heap data structure for $O(\log N)$ efficiency.

---

## Java Implementation Example

### Circular Queue (Array-Based)
```java
public class CircularQueue {
    private int[] data;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.data = new int[capacity];
        this.front = this.size = 0;
        this.rear = capacity - 1;
    }

    public void enqueue(int item) {
        if (size == capacity) {
            throw new RuntimeException("Queue is Full");
        }
        rear = (rear + 1) % capacity;
        data[rear] = item;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new RuntimeException("Queue is Empty");
        }
        int item = data[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }
}
```
