/**
 * SearchEngine.java
 * CIS 22C Course Project
 * @author CIS 22C Team
 */
public class SearchEngine {
    private ArrayList<BST<Song>> index;
    private HashTable<WordID> wordTable;
    private TitleComparator cmp;
    private int nextId;

    // stop words
    private static final String[] STOP_WORDS = {
        "the", "a", "an", "and", "or", "but", "if", "of", "to", "in",
        "on", "at", "for", "with", "is", "are", "was", "were", "be",
        "been", "am", "it", "its", "this", "that", "these", "those",
        "i", "you", "he", "she", "we", "they", "me", "my", "your",
        "so", "as", "by", "up", "out", "no", "not", "do", "all"
    };

    /***CONSTRUCTORS***/

    /**
     * Builds an empty search engine.
     * @param wordTableSize the number of buckets for the word hash table
     */
    public SearchEngine(int wordTableSize) {
        index = new ArrayList<>();
        wordTable = new HashTable<>(wordTableSize);
        cmp = new TitleComparator();
        nextId = 0;
    }

    /***MUTATORS***/

    /**
     * adds a song to the inverted index
     * @param song the song to index
     */
    public void addSong(Song song) {
        String[] words = tokenize(song.getText());
        for (String w : words) {
            if (isStopWord(w)) {
                continue;
            }
            int id = getOrCreateId(w);
            BST<Song> tree = index.get(id);

            // only file the song once per word even if word repeats
            if (tree.search(song, cmp) == null) {
                tree.insert(song, cmp);
            }
        }
    }

    /**
     * removes a song from all word trees
     * @param song the song to remove
     */
    public void removeSong(Song song) {
        if (song == null || song.getText() == null) {
            return;
        }

        String[] words = tokenize(song.getText());

        for (String w : words) {
            if (isStopWord(w)) {
                continue;
            }

            WordID found = wordTable.get(new WordID(w));

            if (found != null) {
                int id = found.getId();
                BST<Song> tree = index.get(id);
                if (tree.search(song, cmp) != null) {
                    tree.remove(song, cmp);
                }
            }
        }
    }

    /***ACCESSORS***/

    /**
     * keyword search that returns a sorted list of songs
     * @param word the word to search for
     * @return list of matching songs
     */
    public ArrayList<Song> search(String word) {
        WordID found = wordTable.get(new WordID(word.toLowerCase()));
        if (found == null) {
            return new ArrayList<>();
        }
        return index.get(found.getId()).toArrayListInOrder();
    }

    /**
     * counts how many words are actually indexed right now
     * @return the number of valid words
     */
    public int getIndexedWordCount() {
        int count = 0;
        for (int i = 0; i < index.getLength(); i++) {
            if (!index.get(i).isEmpty()) {
                count++;
            }
        }
        return count;
    }

    /***PRIVATE HELPERS***/

    /**
     * Looks up the id for a word, creating a new id and empty tree
     * the first time we see that word.
     * @param word the word to look up
     * @return the id (ArrayList slot) for the word
     */
    private int getOrCreateId(String word) {
        WordID found = wordTable.get(new WordID(word));
        if (found != null) {
            return found.getId();
        }
        // new word: give it the next id and add an empty tree
        int id = nextId++;
        wordTable.add(new WordID(word, id));
        index.add(new BST<Song>());
        return id;
    }

    /**
     * Splits text into lowercase words. Anything that is not a
     * letter or digit counts as a separator.
     * @param text the text to split
     * @return an array of words
     */
    private String[] tokenize(String text) {
        if (text == null) {
            return new String[0];
        }
        String cleaned = text.toLowerCase().trim();
        if (cleaned.isEmpty()) {
            return new String[0];
        }
        return cleaned.split("[^a-z0-9]+");
    }

    /**
     * Checks if a word is on the stop word list.
     * @param word the word to check
     * @return whether it should be skipped
     */
    private boolean isStopWord(String word) {
        if (word.isEmpty()) {
            return true;
        }
        for (int i = 0; i < STOP_WORDS.length; i++) {
            if (STOP_WORDS[i].equals(word)) {
                return true;
            }
        }
        return false;
    }
}
