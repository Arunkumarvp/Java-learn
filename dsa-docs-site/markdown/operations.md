# Java Operations Documentation

@[/home/dev/Projects/java/Java-learn]

### Linkdin_List/Linkdin_list.java

```mermaid
classDiagram
    class Linkdin_list {
        +insertAtPosition()
        +isEmpty()
        +insertAtMiddle()
        +insertAtFirst()
        +deleteAtLast()
        +deleteAtPosition()
        +deleteAtFirst()
        +deleteAtMiddle()
        +search()
        +get()
        +getMiddle()
        +reverse()
        +deleteByValue()
        +insertAtLast()
        +display()
        +length()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Linkdin_List/DoublyLinkedList.java

```mermaid
classDiagram
    class DoublyLinkedList {
        +insertAtPosition()
        +displayBackward()
        +isEmpty()
        +deleteAtPosition()
        +insertAtFirst()
        +deleteAtLast()
        +deleteAtFirst()
        +search()
        +reverse()
        +deleteByValue()
        +insertAtLast()
        +displayForward()
        +length()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Linkdin_List/CircularLinkedList.java

```mermaid
classDiagram
    class CircularLinkedList {
        +insertAtPosition()
        +isEmpty()
        +getTail()
        +deleteAtPosition()
        +insertAtFirst()
        +deleteAtLast()
        +deleteAtFirst()
        +search()
        +deleteByValue()
        +insertAtLast()
        +display()
        +length()
    }
```

@[/home/dev/Projects/java/Java-learn]

### non-linear-data/ graph/Graph.java

```mermaid
classDiagram
    class Graph {
        +degree()
        +bfs()
        +dfsIterative()
        +shortestPath()
        +dfsHelper()
        +connectedComponents()
        +isConnected()
        +addEdge()
        +hasEdge()
        +dfs()
        +vertices()
        +dfsCycleCheck()
        +hasCycle()
        +bfsCycleCheck()
        +removeEdge()
        +valid()
        +display()
    }
```

@[/home/dev/Projects/java/Java-learn]

### non-linear-data/Trees/Heap.java

```mermaid
classDiagram
    class Heap {
        +peek()
        +isEmpty()
        +parent()
        +rightChild()
        +extractMax()
        +buildHeap()
        +heapSort()
        +insert()
        +isFull()
        +heapifyUp()
        +swap()
        +heapifyDown()
        +size()
        +display()
        +leftChild()
        +delete()
        +extractMin()
    }
```

@[/home/dev/Projects/java/Java-learn]

### non-linear-data/Trees/AVLTree.java

```mermaid
classDiagram
    class AVLTree {
        +updateHeight()
        +height()
        +search()
        +getBalanceFactor()
        +preorder()
        +bf()
        +rotateLeft()
        +insert()
        +inorder()
        +rotateRight()
        +display()
        +minNode()
        +delete()
        +rebalance()
    }
```

@[/home/dev/Projects/java/Java-learn]

### non-linear-data/Trees/BST.java

```mermaid
classDiagram
    class BST {
        +isEmpty()
        +countNodes()
        +min()
        +countLeaves()
        +height()
        +search()
        +leaves()
        +preorder()
        +insert()
        +max()
        +count()
        +display()
        +postorder()
        +delete()
        +inorderSuccessor()
        +inorder()
    }
```

@[/home/dev/Projects/java/Java-learn]

### non-linear-data/Trees/BinaryTree.java

```mermaid
classDiagram
    class BinaryTree {
        +isEmpty()
        +countNodes()
        +countLeaves()
        +height()
        +search()
        +preorder()
        +insert()
        +levelOrder()
        +display()
        +postorder()
        +inorder()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Queue_DSA/LinkedQueue.java

```mermaid
classDiagram
    class LinkedQueue {
        +peek()
        +dequeue()
        +isEmpty()
        +peekRear()
        +clear()
        +search()
        +size()
        +enqueue()
        +display()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Queue_DSA/CircularQueue.java

```mermaid
classDiagram
    class CircularQueue {
        +dequeue()
        +peek()
        +isEmpty()
        +peekRear()
        +clear()
        +isFull()
        +size()
        +enqueue()
        +display()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Queue_DSA/PriorityQueue.java

```mermaid
classDiagram
    class PriorityQueue {
        +dequeue()
        +isEmpty()
        +peek()
        +parent()
        +left()
        +toString()
        +isFull()
        +heapifyUp()
        +swap()
        +heapifyDown()
        +size()
        +enqueue()
        +display()
        +right()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Queue_DSA/LinearQueue.java

```mermaid
classDiagram
    class LinearQueue {
        +dequeue()
        +peek()
        +isEmpty()
        +peekRear()
        +clear()
        +search()
        +isFull()
        +size()
        +enqueue()
        +display()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Queue_DSA/Queue.java

```mermaid
classDiagram
    class Queue {
        +dequeue()
        +peek()
        +isEmpty()
        +clear()
        +search()
        +isFull()
        +size()
        +enqueue()
        +display()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Queue_DSA/Deque.java

```mermaid
classDiagram
    class Deque {
        +isEmpty()
        +deleteFront()
        +peekRear()
        +insertRear()
        +clear()
        +deleteRear()
        +peekFront()
        +isFull()
        +size()
        +insertFront()
        +display()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Recursiveprogramming/Concepts.java

```mermaid
classDiagram
    class Concepts {
        +printBoard()
        +sumN()
        +power()
        +binarySearch()
        +countDown()
        +printAscending()
        +climbStairs()
        +nCr()
        +subsets()
        +countDigit()
        +tilings()
        +digitSum()
        +isSafe()
        +sumTail()
        +isSorted()
        +gcd()
        +fibMemo()
        +flatten()
        +firstOccurrence()
        +factorial()
        +fastPow()
        +ackermann()
        +genParentheses()
        +printDescending()
        +josephus()
        +isPalindrome()
        +factorialTail()
        +isEven()
        +nQueens()
        +gridPaths()
        +merge()
        +reverseString()
        +maxInArray()
        +hanoi()
        +printBinary()
        +lcs()
        +fibNaive()
        +coinChange()
        +isOdd()
        +mergeSort()
        +permutations()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Searching_Algo/Algorithms.java

```mermaid
classDiagram
    class Algorithms {
        +binarySearchRangeHelper()
        +binarySearchRecursive()
        +exponentialSearch()
        +binarySearchRecursiveHelper()
        +binarySearchIterative()
        +jumpSearch()
        +ternarySearch()
        +linearSearch()
        +interpolationSearch()
        +ternarySearchHelper()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Sorting/Algorithms.java

```mermaid
classDiagram
    class Algorithms {
        +bubbleSort()
        +partition()
        +merge()
        +quickSort()
        +insertionSort()
        +heapSort()
        +quickSortHelper()
        +heapify()
        +countSortForRadix()
        +mergeSortHelper()
        +selectionSort()
        +radixSort()
        +mergeSort()
        +countingSort()
    }
```

@[/home/dev/Projects/java/Java-learn]

### Sorting/main.java

```mermaid
classDiagram
    class main {
        +isSorted()
    }
```

@[/home/dev/Projects/java/Java-learn]

### stack/stack.java

```mermaid
classDiagram
    class stack {
        +isEmpty2()
        +clear()
        +isEmpty1()
        +pop1()
        +display()
        +peek1()
        +push1()
        +isEmpty()
        +push2()
        +pop()
        +getMin()
        +pop2()
        +search()
        +size()
        +push()
        +peek()
        +size1()
        +peek2()
        +isFull()
        +size2()
    }
```

