package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E> {
    private Object[] itemArray;
    private int capacity;
    private int rear = -1;
    private static final int DEFAULT_Queue_SIZE = 10;

    public QueueImplementation() throws QueueAllocationException {
        this(DEFAULT_Queue_SIZE);
    }

    public QueueImplementation(int capacity) throws QueueAllocationException {
       // TODO: Implement this

        if (capacity < 2) {
            throw new QueueAllocationException("Size should be at least 2");
        }
        this.capacity = capacity;
        itemArray = new Object[capacity];
        rear = -1;
    }

    @Override
    public int capacity() {
        // TODO: Implement this
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (rear >= capacity - 1) {
            int newCapacity = capacity * 2; // Increase capacity, you can use any appropriate logic
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(itemArray, 0, newArray, 0, capacity);
            itemArray = newArray;
            capacity = newCapacity;
        }
        itemArray[++rear] = element;
    }

    @Override
    public E dequeue() throws QueueIsEmptyException {
        // TODO: Implement this
        if (rear >= 0) {
            E dequeuedElement = (E) itemArray[0]; // element at the front of the queue
            // Shift the elements to the front
            for (int i = 0; i < rear; i++) {
                itemArray[i] = itemArray[i + 1];
            }
            itemArray[rear] = null; // Set the last element to null
            rear--; // Decrement the rear pointer
            return dequeuedElement;
        } else {
            throw new QueueIsEmptyException("Queue is empty");
        }
    }

    @Override
    public E element() throws QueueIsEmptyException {
        // TODO: Implement this
        if (rear >= 0) {
            return (E)itemArray[0]; // Return the element at the front of the queue
        } else {
            throw new QueueIsEmptyException("Queue is empty");
        }
    }

    @Override
    public int size() {
        // TODO: Implement this
       return rear+1;
    }

    @Override
    public boolean isEmpty() {
        // TODO: Implement this
        return rear == -1;
    }

    @Override
    public void clear() {
        // TODO: Implement this
        rear = -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (var index = 0; index <= rear; index++) {
            builder.append(itemArray[index].toString());
            if (index < rear) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
