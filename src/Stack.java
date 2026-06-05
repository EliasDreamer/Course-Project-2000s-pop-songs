import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> implements LIFO<T> {
    private class Node {
        private T data;
        private Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    private int length;
    private Node top;

    /**
     *constructor method
     *
     *
     */
    public Stack(){
        this.length = 0;
        this.top = null;
    }



    /**
     *constructor method
     *@param array the array is being copy
     *
     */
    public Stack(T array[]){
        this();

        if (array == null) {
            return;
        }

        for (int i = array.length - 1; i >= 0; i--) {
            push(array[i]);
        }
    }


    /**
     *constructor method
     *@param stack the array is being copy
     *
     */
    public Stack(Stack<T> stack){
        this();
        if (stack != null && !stack.isEmpty()) {
            Node temp = stack.top;

            top = new Node(temp.data);
            length = 1;

            Node copyCurrent = top;
            temp = temp.next;

            while (temp != null) {
                copyCurrent.next = new Node(temp.data);
                copyCurrent = copyCurrent.next;
                temp = temp.next;
                length++;
            }
        }
    }

    /**
     * Determines whether the Stack is sorted from smallest to largest.
     * @return whether the Stack is sorted
     */
    public boolean isSorted() {
        return isSorted(top);
    }

    /**
     * Uses recursive linear search to locate an element.
     * @param element the value to search for
     * @return whether the element is present
     */
    public boolean linearSearch(T element) {
        return linearSearch(top, element);
    }

    /**
     * Uses recursive binary search to locate an element.
     * @param value the value to search for
     * @return whether the element is present
     * @throws IllegalStateException when the Stack is not sorted
     */
    public boolean binarySearch(T value) throws IllegalStateException {
        if (!isSorted()) {
            throw new IllegalStateException();
        }

        return binarySearch(0, length - 1, value);
    }

    /**
     * Creates a String of the Stack in reverse order.
     * @return the Stack values as a String in reverse order
     */
    public String reverseStack() {
        return reverseStack(top) + "\n";
    }


    /** RECURSIVE HELPER METHODS */

    /**
     * Recursively determines whether data is sorted from smallest to largest.
     * @param node the current node
     * @return whether the data is sorted in ascending order
     */
    private boolean isSorted(Node node) {
        if (node == null || node.next == null) {
            return true;
        }

        if (node.data.compareTo(node.next.data) > 0) {
            return false;
        }

        return isSorted(node.next);
    }

    /**
     * Searches for the specified value recursively.
     * @param node the current node
     * @param value the value to search for
     * @return whether the value exists in the Queue
     */
    private boolean linearSearch(Node node, T value) {
        if (node == null) {
            return false;
        }

        if (node.data.compareTo(value) == 0) {
            return true;
        }

        return linearSearch(node.next, value);
    }

    /**
     * Returns the data stored at the mid Node.
     * @param node the current node
     * @param mid the integer location in the Queue
     * @return the data stored at the mid Node
     */
    private T getMid(Node node, int mid) {
        if (mid == 0) {
            return node.data;
        }

        return getMid(node.next, mid - 1);
    }

    /**
     * Searches for the specified value by recursive binary search.
     * @param low the lowest bounds of the search
     * @param high the highest bounds of the search
     * @param value the value to search for
     * @return whether the value exists in the Queue
     */
    private boolean binarySearch(int low, int high, T value) {
        if (high < low) {
            return false;
        }

        int mid = low + (high - low) / 2;
        T midValue = getMid(top, mid);

        if (value.compareTo(midValue) == 0) {
            return true;
        } else if (value.compareTo(midValue) < 0) {
            return binarySearch(low, mid - 1, value);
        } else {
            return binarySearch(mid + 1, high, value);
        }
    }

    /**
     * Recursively creates a String where the data is in reverse order.
     * @param node the current node
     * @return the Stack values as a String in reverse order
     */
    private String reverseStack(Node node) {
        if (node == null) {
            return "";
        }

        return reverseStack(node.next) + node.data + " ";
    }


    /**
     * Returns the value stored at the front
     * of the Stack
     * @return the value at the front of the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public T peek() throws NoSuchElementException {
        if(length == 0){
            throw new NoSuchElementException("peek(): Stack is empty");
        }
        return top.data;
    }

    /**
     * Returns the size of the Stack
     * @return the size from 0 to n
     */
    public int getSize(){
        return length;
    }

    /**
     * Determines whether a Stack is empty
     * @return whether the Stack contains
     * no elements
     */
    public boolean isEmpty(){
        return length == 0;
    }

    /**
     * Inserts a new value in the Stack
     *
     * @param data the new data to insert
     * @postcondition a new node in the Stack
     */
    public void push(T data){
        Node newNode = new Node(data);
        if(isEmpty()){
            top = newNode;
        } else{
            newNode.next = top;
            top = newNode;
        }
        length++;
    }

    /**
     * Removes the front element in the Stack
     * @precondition !isEmpty()
     * @throws NoSuchElementException when
     * the precondition is violated
     * @postcondition the front element has
     * been removed
     */
    public void pop() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("pop(): Stack is empty");
        }
        top = top.next;
        length--;

    }


    /**
     * @return a string of all elements
     */
    @Override
    public String toString() {
        Node temp = top;
        String result = "";

        while (temp != null) {
            result += temp.data + " ";
            temp = temp.next;
        }

        result += "\n";
        return result;
    }

    /**
     * Determines whether two Stacks contain the same values
     * in the same order.
     * @param obj the Object to compare to this Stack
     * @return whether the two Stacks are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Stack)) {
            return false;
        }

        Stack other = (Stack) obj;

        if (length != other.length) {
            return false;
        }

        Node current = top;
        Stack.Node otherCurrent = other.top;

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
