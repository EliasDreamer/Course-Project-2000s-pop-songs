/**
 * ArrayList.java
 * CIS 22C
 */
public class ArrayList<E> {
    private E[] array;
    private int numElements;
    public int capacity;

    /**
     * Default constructor for ArrayList
     * Creates an empty array of length 5
     */
    @SuppressWarnings("unchecked")
    public ArrayList() {
        final int INITIAL_CAP = 5;
        array = (E[]) new Object[INITIAL_CAP];
        numElements = 0;
        capacity = INITIAL_CAP;
    }

    /**
     * Overloaded constructor for ArrayList
     * Creates an empty array of length initCap
     * @param initCap the initial capacity of the ArrayList
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int initCap) {
        array = (E[]) new Object[initCap];
        numElements = 0;
        capacity = initCap;
    }

    /**
     * Copy constructor for ArrayList
     * Creates a new ArrayList with identical data as parameter
     * @param original the ArrayList to copy
     */
    @SuppressWarnings("unchecked")
    public ArrayList(ArrayList<E> original) {
        this.numElements = original.numElements;
        this.capacity = original.capacity;
        this.array = (E[]) new Object[original.capacity];

        for (int i = 0; i < numElements; i++) {
            this.array[i] = original.array[i];
        }
    }

    /**
     * Returns whether the parameter is an ArrayList and
     * contains the same data in the same order as this ArrayList
     * @param obj the Object to compare for equality
     * @return whether obj is an ArrayList containing the same data
     * in the same order as this ArrayList
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ArrayList)) {
            return false;
        }

        ArrayList<E> other = (ArrayList<E>) obj;

        if (this.numElements != other.numElements) {
            return false;
        }

        for (int i = 0; i < numElements; i++) {
            if (this.array[i] == null && other.array[i] != null) {
                return false;
            }

            if (this.array[i] != null && !this.array[i].equals(other.array[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns whether the ArrayList is currently empty
     * @return whether the ArrayList is empty
     */
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Returns the current number of elements stored in the ArrayList
     * @return the number of elements
     */
    public int getLength() {
        return numElements;
    }

    /**
     * Returns the current number of elements stored in the ArrayList
     * @return the number of elements
     */
    public int size() {
        return numElements;
    }

    /**
     * Determines whether the ArrayList is currently full
     * @return whether the ArrayList is at maximum capacity
     */
    public boolean atCapacity() {
        return numElements == capacity;
    }

    /**
     * Resizes the ArrayList by doubling its capacity
     */
    @SuppressWarnings("unchecked")
    private void reSize() {
        E[] newArray = (E[]) new Object[capacity * 2];

        for (int i = 0; i < numElements; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
        capacity = capacity * 2;
    }

    /**
     * Inserts a new element to the end of the ArrayList
     * @param element the element to insert
     */
    public void add(E element) {
        if (atCapacity()) {
            reSize();
        }

        array[numElements] = element;
        numElements++;
    }

    /**
     * Inserts a new element at the specified index in the ArrayList
     * @param index the index at which to insert
     * @param element the element to insert
     * @throws IndexOutOfBoundsException for invalid indices
     */
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > numElements) {
            throw new IndexOutOfBoundsException(
                    "Error: Cannot add at index " + index + ".\n"
                            + "Index is outside the bounds of the ArrayList.\n"
                            + "Index: " + index + ", Size: " + numElements + "\n"
            );
        }

        if (atCapacity()) {
            reSize();
        }

        for (int i = numElements; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = element;
        numElements++;
    }

    /**
     * Returns the element at the specified index
     * @param index the index of element to access
     * @return the element at index
     * @throws IndexOutOfBoundsException if the index is outside the bounds of the array
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException(
                    "Error: Cannot get element at index " + index + ".\n"
                            + "Outside bounds of the ArrayList.\n"
                            + "Index: " + index + ", Size: " + numElements + "\n"
            );
        }

        return array[index];
    }

    /**
     * Assigns a new value to the ArrayList at a specified index
     * @param index the index at which to update
     * @param element the new element
     * @throws IndexOutOfBoundsException when index is outside bounds of array
     */
    public void set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException(
                    "Error: Cannot set at index outside bounds of ArrayList.\n"
                            + "Index: " + index + " , Size: " + numElements + "\n"
            );
        }

        array[index] = element;
    }

    /**
     * Uses the linearSearch algorithm to locate an element
     * @param element the element to locate
     * @return the location of the element or -1 if the element is not in the ArrayList
     */
    public int indexOf(E element) {
        for (int i = 0; i < numElements; i++) {
            if (element == null && array[i] == null) {
                return i;
            }

            if (element != null && element.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Uses the linearSearch algorithm to locate an element in the ArrayList
     * @param element the element to locate
     * @return whether or not the element is in the ArrayList
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    /**
     * Removes an element at a specified index in the ArrayList
     * @param index the index at which to remove
     * @return the element that was removed
     * @throws IndexOutOfBoundsException when index is outside bounds of array
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException(
                    "Error: Cannot remove element at index " + index + ".\n"
                            + "Outside bounds of the ArrayList.\n"
                            + "Index: " + index + ", Size: " + numElements + "\n"
            );
        }

        E element = array[index];

        for (int i = index; i < numElements - 1; i++) {
            array[i] = array[i + 1];
        }

        array[numElements - 1] = null;
        numElements--;

        return element;
    }

    /**
     * Removes the first instance of the specified element in the ArrayList
     * @param element the element to remove
     * @return whether the element was successfully removed
     */
    public boolean remove(E element) {
        int index = indexOf(element);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    /**
     * Creates a String of all elements, with [] around the elements
     * @return a String representation of the ArrayList
     */
    @Override
    public String toString() {
        String result = "[";

        for (int i = 0; i < numElements - 1; i++) {
            result += array[i] + ", ";
        }

        if (!isEmpty()) {
            result += array[numElements - 1];
        }

        result += "]";
        return result;
    }
}
