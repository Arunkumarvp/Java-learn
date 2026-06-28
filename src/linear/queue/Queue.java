package Queue_DSA;

class Queue {
    private int[] data;
    private int size;
    private int front;
    private int rear;

    public Queue(int size) {
        this.size = size;
        this.data = new int[size];
        this.front = -1;
        this.rear = -1;
    }

    public boolean isFull() {
        return (rear == size - 1);
    }

    public boolean isEmpty() {
        return (front == -1 && rear == -1);
    }

    public void enqueue(int value) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        if (isEmpty()) {
            front = 0;
        }
        rear++;
        data[rear] = value;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        int value = data[front];
        front++;
        if (front > rear) {
            front = -1;
            rear = -1;
        }
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        return data[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = front; i <= rear; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public int size() {
        return rear - front + 1;
    }

    public void clear() {
        front = -1;
        rear = -1;
    }

    public int search(int value) {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        for (int i = front; i <= rear; i++) {
            if (data[i] == value) {
                return i - front;
            }
        }
        return -1;
    }

}
