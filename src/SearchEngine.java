public class SearchEngine<T> {
    private ArrayList<BST<T>> arrayOfTree;
    private HashTable<WordsID> wordsIDHashTable;

    //Maybe use this as the wordsID is better
    //Because we have 0,1,2,3,.... for "time", "love", "life"
    private int numOfValidWords;
    /*Constructor*/

    public SearchEngine(int size) throws IllegalArgumentException{
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0.");
        }
        this.arrayOfTree = new ArrayList<BST<T>>();
        for(int i = 0; i < size; i++){
            arrayOfTree.add(new BST<T>());
        }
        this.wordsIDHashTable = new HashTable<>(size);
    }

    public SearchEngine(ArrayList<BST<T>> arrayOfTree,HashTable<WordsID> wordsIDHashTable){

    }

    /*Accessors*/

    /*
     *Using the given keyWord("time","love") to find the corresponding tree
     *which contains all the songs that have the keyWord
     *(It can be private)
     * because I think in outside class we may just want to add the songs into the engine
     */
    public BST<T> findTree(String keyWord){
        return null;
    }

    /*
     *With given keyWord find the ID of the word in the wordsIDHashTable
     *(use hash value of the keyWord to implement this method maybe)
     * (It can be a private method)
     */
    public int findWordsID(String keyWord){
        return -1;
    }


    /*Mutators*/

    /*
     * When this method is being called,
     * firstly get rid of all the invalid words,(I think we can write more private method to make the code looks better)
     * store all the valid words into the wordsIDHashTable
     * the add the songs to the correct trees
     */
    public void addSongs(T songs){

    }

    /*
     * When adding songs to the SearchEngine, automatically invoke this function,
     * so that all the valid keywords can be stored in the wordsIDHashTable
     * Using the given lyrics add all those valid words into the wordsIDHashTable
     * Can't add the same word to the table
     */
    private void addWordID(String lyrics){

    }

    /*Display method*/

    /*
     * output the names of the songs in order
     * 1.Attention
     * 2.Baby
     * 3....
     * Those names of the songs are all stored in one tree
     * So we may use BST inOrderString()
     */


    public String getTheSongs(String keyWords){
        return "";
    }

}
