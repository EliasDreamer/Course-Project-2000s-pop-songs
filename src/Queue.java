import java.util.NoSuchElementException;
public class Queue<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private int size;
    private Node front;
    private Node end;

    /**
     * Default constructor for Queue.
     * Creates an empty Queue.
     */
    public Queue() {
        size = 0;
        front = null;
        end = null;
    }

    /**
     * Converts an array into a Queue.
     * @param array the array to copy into this Queue
     */
    public Queue(T[] array) {
        this();

        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                enqueue(array[i]);
            }
        }
    }

    /**
     * Copy constructor for Queue.
     * Makes a deep copy of the nodes in the original Queue.
     * @param original the Queue to copy
     */
    public Queue(Queue<T> original) {
        this();

        if (original != null) {
            Node current = original.front;

            while (current != null) {
                enqueue(current.data);
                current = current.next;
            }
        }
    }

    /**
     * Returns the value stored at the front of the Queue.
     * @return the value at the front of the Queue
     * @throws NoSuchElementException when the Queue is empty
     */
    public T getFront() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("getFront(): Queue is empty.");
        }

        return front.data;
    }

    /**
     * Returns the size of the Queue.
     * @return the size of the Queue
     */
    public int getSize() {
        return size;
    }

    /**
     * Determines whether the Queue is empty.
     * @return whether the Queue contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Inserts a new value at the end of the Queue.
     * @param data the new data to insert
     */
    public void enqueue(T data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            front = newNode;
            end = newNode;
        } else {
            end.next = newNode;
            end = newNode;
        }

        size++;
    }

    /**
     * Removes the front element in the Queue.
     * @throws NoSuchElementException when the Queue is empty
     */
    public void dequeue() throws NoSuchElementException {

        if (isEmpty()) {
            throw new NoSuchElementException("dequeue(): Queue is empty.");
        }

        front = front.next;
        size--;

        if (isEmpty()) {
            end = null;
        }
    }

    /**
     * Returns the values stored in the Queue as a String.
     * @return the Queue as a String
     */
    @Override
    public String toString() {
        String result = "";
        Node current = front;

        while (current != null) {
            result += current.data + " ";
            current = current.next;
        }

        result += "\n";
        return result;
    }

    /**
     * Determines whether two Queues contain the same values
     * in the same order.
     * @param obj the Object to compare to this Queue
     * @return whether the two Queues are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Queue<?>)) {
            return false;
        }

        Queue<?> other = (Queue<?>) obj;

        if (size != other.size) {
            return false;
        }

        Node current = front;
        Queue<?>.Node otherCurrent = other.front;

        while (current != null) {
            if (current.data == null) {
                if (otherCurrent.data != null) {
                    return false;
                }
            } else if (!current.data.equals(otherCurrent.data)) {
                return false;
            }

            current = current.next;
            otherCurrent = otherCurrent.next;
        }

        return true;
    }
}
