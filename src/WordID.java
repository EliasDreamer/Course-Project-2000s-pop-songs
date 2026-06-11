/**
 * WordID.java
 * CIS 22C Course Project
 * @author
 */
public class WordID {
    private String word;
    private int id;

    /***CONSTRUCTORS***/

    /**
     * Full constructor.
     * @param word the unique word
     * @param id the array index assigned to this word
     */
    public WordID(String word, int id) {
        this.word = word;
        this.id = id;
    }

    /**
     * Search-key constructor.
     * Makes a probe that only carries a word, used to look the id up.
     * @param word the word to search for
     */
    public WordID(String word) {
        this(word, -1);
    }

    /***ACCESSORS***/

    /**
     * Returns the word.
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * Returns the id which is the array index of this word's tree.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /***ADDITIONAL OPERATIONS***/

    /**
     * Two WordIDs match when they hold the same word, ignoring case.
     * @param obj the object to compare
     * @return whether they share a word
     */
    @Override
    public boolean equals(Object obj) {
        // TODO: compare word, ignore case
        return false;
    }

    /**
     * Hash on the lowercased word so it lines up with equals.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        // TODO: base on word
        return 0;
    }

    /**
     * Returns word and id as text.
     * @return the word and its id
     */
    @Override
    public String toString() {
        return word + " -> " + id;
    }
}
