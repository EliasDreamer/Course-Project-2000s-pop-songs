public class HashTable<T> {
    private int numElements;
    private ArrayList<LinkedList<T>> table;

    /**** Constructors ****/

    /**
     * Constructor for the HashTable class.
     * Initializes the table to the given size.
     * @param size the size of the table
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(int size) throws IllegalArgumentException {
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }


        table = new ArrayList<LinkedList<T>>();

        for (int i = 0; i < size; i++) {
            table.add(new LinkedList<T>());
        }
    }

    /**
     * Constructor for the HashTable class.
     * Inserts the contents of the given array into the table at the appropriate indices.
     * @param array an array of elements to insert
     * @param size the size of the Table
     * @throws IllegalArgumentException when size <= 0
     */
    public HashTable(T[] array, int size) throws IllegalArgumentException {
        this(size);

        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    add(array[i]);
                }
            }
        }
    }

    /**** Accessors ****/



    /**
     * Searches for the specified element in the HashTable.
     * @param elmt the element to search for
     * @return the bucket where the element is located, or -1 if not found
     * @throws NullPointerException when elmt is null
     */
    public int find(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        int bucket = hash(elmt);
        LinkedList<T> list = table.get(bucket);

        list.positionIterator();

        while (!list.offEnd()) {
            if (list.getIterator().equals(elmt)) {
                return bucket;
            }

            list.advanceIterator();
        }

        return -1;
    }

    /**
     * Converts a bucket to a String.
     * @param index the index in the table
     * @return a String of the elements at this bucket
     * @throws IndexOutOfBoundsException when index is out of bounds
     */
    public String bucketToString(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= table.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }

        return table.get(index).toString();
    }

    /**
     * Returns the load factor of the HashTable.
     * @return the load factor
     */
    public double getLoadFactor() {
        return (double) numElements / table.size();
    }

    /**
     * Returns the hash value in the table for a given Object.
     * @param obj the Object
     * @return the index in the table
     */
    private int hash(T obj) {
        return Math.abs(obj.hashCode()) % table.getLength();
    }


    /**
     * Counts the number of elements at this index.
     * @param index the index in the table
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException when index < 0 or index >= table.size()
     */
    public int countBucket(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= table.size()) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }

        return table.get(index).getLength();
    }

    /**
     * Determines whether a specified element is in the table.
     * @param elmt the element to locate
     * @return whether the element is in the table
     * @throws NullPointerException when elmt is null
     */
    public boolean contains(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        int bucket = hash(elmt);
        LinkedList<T> list = table.get(bucket);

        list.positionIterator();

        while (!list.offEnd()) {
            if (list.getIterator().equals(elmt)) {
                return true;
            }

            list.advanceIterator();
        }

        return false;
    }

    /**
     * Accesses a specified key in the Table.
     * @param elmt the key to search for
     * @return the value to which the specified key is mapped,
     * or null if this table contains no mapping for the key
     * @precondition elmt != null
     * @throws NullPointerException when the precondition is violated
     */
    public T get(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("get(): element cannot be null.");
        }

        int bucket = hash(elmt);
        LinkedList<T> list = table.get(bucket);
        int index = list.findIndex(elmt);

        if (index == -1) {
            return null;
        }

        list.advanceIteratorToIndex(index);
        return list.getIterator();
    }


    /**
     * Returns the number of elements in the HashTable.
     * @return the number of elements
     */
    public int getNumElements() {
        return numElements;
    }

    /**** Mutators ****/

    /**
     * Inserts a new element into the table.
     * @param elmt the element to insert
     * @throws NullPointerException when elmt is null
     */
    public void add(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        int bucket = hash(elmt);
        table.get(bucket).addLast(elmt);
        numElements++;
    }

    /**
     * Removes the given element from the table.
     * @param elmt the element to remove
     * @return whether elmt exists and was removed from the table
     * @throws NullPointerException when elmt is null
     */
    public boolean delete(T elmt) throws NullPointerException {
        if (elmt == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        int bucket = hash(elmt);
        LinkedList<T> list = table.get(bucket);

        list.positionIterator();

        while (!list.offEnd()) {
            if (list.getIterator().equals(elmt)) {
                list.removeIterator();
                numElements--;
                return true;
            }

            list.advanceIterator();
        }

        return false;
    }

    /**
     * Resets the hash table back to the empty state.
     */
    public void clear() {
        for (int i = 0; i < table.size(); i++) {
            while (!table.get(i).isEmpty()) {
                table.get(i).removeFirst();
            }
        }

        numElements = 0;
    }

    /**** Additional Methods ****/

    // added so we can grab all songs for saving to file and stats
    /**
     * Returns all elements in the table as an ArrayList.
     * @return an ArrayList of every element
     */
    public ArrayList<T> getAllElements() {
        ArrayList<T> all = new ArrayList<>();
        for (int i = 0; i < table.getLength(); i++) {
            LinkedList<T> list = table.get(i);
            list.positionIterator();
            while (!list.offEnd()) {
                all.add(list.getIterator());
                list.advanceIterator();
            }
        }
        return all;
    }

    /**
     * Creates a String of the bucket number followed by a colon followed by
     * the first element at each bucket followed by a new line.
     * For empty buckets, add the bucket number followed by a colon followed by empty.
     * @return a String of all first elements at each bucket
     */
    public String rowToString() {
        String result = "";

        for (int i = 0; i < table.getLength(); i++) {
            result += "Bucket " + i + ": ";

            if (table.get(i).isEmpty()) {
                result += "empty";
            } else {
                result += table.get(i).getFirst();
            }

            result += "\n";
        }

        return result;
    }

    /**
     * Starting at the 0th bucket, and continuing in order until the last bucket,
     * concatenates all elements at all buckets into one String.
     * @return a String of all elements in this HashTable.
     */
    @Override
    public String toString() {
        String result = "";

        for (int i = 0; i < table.getLength(); i++) {
            if (!table.get(i).isEmpty()) {
                result += table.get(i).toString();
            }
        }

        return result + "\n";
    }
}
