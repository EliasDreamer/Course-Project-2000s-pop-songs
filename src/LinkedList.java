import java.util.NoSuchElementException;

public class LinkedList<T> {
    private class Node{
        private T data;
        private Node next;
        private Node prev;

        /**
         *Constructor of Class Node
         *@param data the data to insert
         */
        public Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    private Node first;
    private Node last;
    private int length;
    private Node iterator;

    /**
     *Constructor of Class LinkedList
     */
    public LinkedList(){
        this.first = null;
        this.last = null;
        length = 0;
        iterator = null;
    }

    /**
     * Instantiates a new LinkedList by copying the elements from an array
     * @param array the array to copy
     * @postcondition a new LinkedList object, which contains the same elements
     * as array in the same order
     */
    public LinkedList(T[] array) {
        this();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                addLast(array[i]);
            }
        }
    }

    /**
     * Instantiates a new LinkedList by copying another LinkedList
     * @param original the LinkedList to copy
     * @postcondition a new List object, which is an identical,
     * but separate, copy of the LinkedList original
     */
    public LinkedList(LinkedList<T> original) {
        this();
        if(original != null && original.length != 0) {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }
    }


    /**
     * Instantiates a new LinkedList by copying another LinkedList
     * @param obj that is to be compared
     * @return whether the two list is equal
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj){
        if (obj == this) {
            return true;
        } else if (!(obj instanceof LinkedList)) {
            return false;
        } else {
            LinkedList<T> list = (LinkedList<T>) obj;

            if (this.length != list.length) {
                return false;
            }

            Node temp1 = this.first;
            Node temp2 = list.first;

            while (temp1 != null) {
                if (temp1.data == null) {
                    if (temp2.data != null) {
                        return false;
                    }
                } else if (!temp1.data.equals(temp2.data)) {
                    return false;
                }

                temp1 = temp1.next;
                temp2 = temp2.next;
            }

            return true;
        }
    }

    /**
     *clear all the elements in the list
     */
    public void clear(){

        while(length > 0){
            removeFirst();
        }

    }

    /**
     *assign first to iterator
     *
     *
     */
    public void positionIterator(){
        iterator = first;
    }




    /**
     *return the data in the object that iterator refers to
     *@precondition iterator cann't be null
     *@return the data in the object that iterator refers to
     *@throws NullPointerException when iterator is null
     */
    public T getIterator() throws NullPointerException {
        if(iterator == null){
            throw new NullPointerException("getIterator(): cannot get a null iterator.");
        }
        else return iterator.data;
    }




    /**
     *
     *return if the iterator is offEnd
     *@return whether the iterator is offEnd
     */
    public boolean offEnd(){
        if(iterator == null) return true;
        else return false;
    }



    /**
     *Moves the iterator to the next node.
     *@precondition iterator cann't be null
     *@throws NullPointerException when iterator is null
     */
    public void advanceIterator() throws NullPointerException{
        if(iterator == null){
            throw new NullPointerException("advanceIterator(): cannot advance a null iterator.");
        }
        iterator = iterator.next;
    }





    /**
     *Moves the iterator to the previous node.
     *@precondition iterator cann't be null
     *@throws NullPointerException when iterator is null
     */
    public void reverseIterator() throws NullPointerException{
        if(iterator == null){
            throw new NullPointerException("reverseIterator(): cannot reverse a null iterator.");
        }
        iterator = iterator.prev;
    }





    /**
     *add a new Node on the position of iterator
     *@precondition iterator cann't be null
     *@postcondition a new node containing data is inserted after the iterator,
    and length is increased by 1
     *@param data the data to insert
     *@throws NullPointerException when iterator is null
     */
    public void addIterator(T data) throws NullPointerException{
        if(iterator == null){
            throw new NullPointerException("addIterator(): cannot insert after a null iterator.");
        }
        else if(iterator == last) {addLast(data);}
        else{
            Node newNode = new Node(data);
            newNode.prev = iterator;
            newNode.next = iterator.next;
            iterator.next.prev = newNode;
            iterator.next = newNode;
            length ++;
        }

    }




    /**
     *remove the Node on the position of iterator
     *@precondition iterator cann't be null
     *@postcondition the node at iterator is removed, length is decreased by 1,
    and iterator is set to null
     *@throws NullPointerException when iterator is null
     */
    public void removeIterator()throws NullPointerException{
        if(iterator == null){
            throw new NullPointerException("removeIterator(): cannot remove a null iterator.");
        } else if(iterator == first){
            removeFirst();
        }
        else if(iterator == last){
            removeLast();
        }
        else{
            iterator.next.prev = iterator.prev;
            iterator.prev.next = iterator.next;
            iterator = null;
            length --;
        }

    }



    /**
     * Removes the element at the back of the LinkedList
     * @precondition LinkedList must not be empty
     * @postcondition Last element of list is removed
     * @throws NoSuchElementException when LinkedList is empty
     */
    public void removeLast() throws NoSuchElementException {
        if(length == 0) {
            throw new NoSuchElementException("removeLast(): LinkedList cannot be empty.");
        } else if(length == 1) {
            if(iterator == last) {iterator = null; }
            first = last = null;
        } else {
            if(iterator == last) {iterator = null; }
            last = last.prev;
            last.next = null;

        }
        length--;
    }


    /**
     * Removes the element at the front of the LinkedList
     * @precondition LinkedList must not be empty
     * @postcondition First element of list is removed
     * @throws NoSuchElementException when LinkedList is empty
     */
    public void removeFirst() throws NoSuchElementException {
        if(length == 0) {
            throw new NoSuchElementException("removeFirst(): LinkedList cannot be empty.");
        } else if(length == 1) {
            if(iterator == first) {iterator = null; }
            first = last = null;
        } else {
            if(iterator == first) {iterator = null; }
            first = first.next;
            first.prev = null;
        }

        length--;
    }


    /**
     * Returns the value stored in the last node
     * @precondition The LinkedList is not empty.
     * @return the value stored at node last
     * @throws NoSuchElementException LinkedList cannot be empty.
     */
    public T getLast() throws NoSuchElementException {
        if(length == 0) {
            throw new NoSuchElementException("getLast(): LinkedList cannot be empty.");
        }
        return last.data;
    }



    /**
     * Returns the value stored in the first node
     * @precondition The LinkedList is not empty.
     * @return the value stored at node first
     * @throws NoSuchElementException LinkedList cannot be empty.
     */
    public T getFirst() throws NoSuchElementException {
        if(length == 0) {
            throw new NoSuchElementException("getFirst(): LinkedList cannot be empty.");
        }
        return first.data;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the back of the LinkedList
     * @postcondition A new node containing data is added to the back of the list,
     * last references this new node, and length is increased by 1.
     */
    public void addLast(T data) {
        Node dataNode = new Node(data);
        if(length == 0) {
            first = last = dataNode;
        } else {
            last.next = dataNode;
            dataNode.prev = last;
            last = dataNode;
        }
        length++;
    }



    /**
     * Creates a new first element
     * @param data the data to insert at the front of the LinkedList
     * @postcondition A new node containing data is added to the front of the list,
     * first references this new node, and length is increased by 1.
     */
    public void addFirst(T data) {
        Node dataNode = new Node(data);
        if(length == 0) {
            first = last = dataNode;
        }  else {
            first.prev = dataNode;
            dataNode.next = first;
            first = dataNode;
        }
        length++;
    }

    /**
     * Converts the LinkedList to a String, with each value separated by a
     * blank space. At the end of the String, place a new line character.
     * @return the LinkedList as a String
     */
    @Override
    public String toString() {
        String result = "";
        Node temp = first;
        while(temp != null) {
            result += temp.data + " ";
            temp = temp.next;
        }
        return result + "\n";
    }

    /**
     * return a boolean value if the List is empty
     * @return return a boolean value shows that if the List is empty
     */
    public boolean isEmpty(){
        if(length == 0) return true;
        else return false;
    }

    /**
     * returns the current length of the List
     * @return return the length of the List from 0 to n
     */
    public int getLength(){
        return  this.length;
    }

    /**** ADDITIONAL OPERATIONS ****/

    /**
     * Returns a String containing each element in the LinkedList
     * numbered from 1 to n and followed by a newline.
     *
     * @return the numbered LinkedList elements as a String
     */
    public String numberedListString() {
        StringBuilder result = new StringBuilder();
        Node temp = first;
        int number = 1;

        while (temp != null) {
            result.append(number).append(". ").append(temp.data).append("\n");
            temp = temp.next;
            number++;
        }

        result.append("\n");
        return result.toString();
    }

    /** MORE METHODS */

    /**
     * Searches the LinkedList for the index of the given data.
     *
     * @param data the data whose index to locate
     * @return the index of data or -1 if data is not in the LinkedList
     */
    public int findIndex(T data) {
        Node temp = first;
        int index = 0;

        while (temp != null) {
            if (temp.data.equals(data)) {
                return index;
            }

            temp = temp.next;
            index++;
        }

        return -1;
    }

    /**
     * Advances the iterator to the node at the given index.
     *
     * @param index the index at which to place the iterator
     * @throws IndexOutOfBoundsException when index is out of bounds
     */
    public void advanceIteratorToIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException(
                    "advanceIteratorToIndex(): index out of bounds."
            );
        }

        iterator = first;

        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
        }
    }
}

