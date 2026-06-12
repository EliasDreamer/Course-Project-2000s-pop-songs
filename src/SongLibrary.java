import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SongLibrary.java
 * CIS 22C Course Project
 * @author
 */
public class SongLibrary {
    private HashTable<Song> songTable;
    private SearchEngine engine;

    /***CONSTRUCTORS***/

    /**
     * Builds an empty library.
     * @param songTableSize buckets for the song hash table
     * @param wordTableSize buckets for the search engine's word table
     */
    public SongLibrary(int songTableSize, int wordTableSize) {
        songTable = new HashTable<>(songTableSize);
        engine = new SearchEngine(wordTableSize);
    }

    /***ACCESSORS***/

    /**
     * keyword search
     * @param word the word to search for
     * @return matching songs
     */
    public ArrayList<Song> search(String word) {
        return engine.search(word);
    }

    /**
     * @return how many words currently match at least one song
     */
    public int getIndexedWordCount() {
        return engine.getIndexedWordCount();
    }

    /**
     * @return how many songs are stored
     */
    public int size() {
        return songTable.getNumElements();
    }

    /**
     * Checks whether a song with this title exists.
     * @param title the title to check
     * @return whether the song is present
     */
    public boolean contains(String title) {
        return findByTitle(title) != null;
    }

    /**
     * finds one song by title
     * @param title the title to look up
     * @return the song or null
     */
    public Song findByTitle(String title) {
        return songTable.get(new Song(title));
    }

    /**
     * @return every song in the library
     */
    public ArrayList<Song> getAllSongs() {
        return songTable.getAllElements();
    }

    /***MUTATORS***/

    /**
     * adds a new song to both the table and index
     * @param song the song to add
     * @return true if added, false if duplicate title
     */
    public boolean addSong(Song song) {
        if (contains(song.getTitle())) {
            return false;
        }
        songTable.add(song);
        engine.addSong(song);
        return true;
    }

    /**
     * deletes a song from both stores
     * @param title the title to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteSong(String title) {
        Song song = findByTitle(title);
        if (song == null) {
            return false;
        }
        engine.removeSong(song);
        songTable.delete(song);
        return true;
    }

    /**
     * Updates the text of an existing song.
     * @param song the song to update
     * @param newText the new searchable text
     */
    public void updateText(Song song, String newText) {
        // TODO
    }

    /**
     * Changes the title of an existing song.
     * @param song the song to rename
     * @param newTitle the new title
     * @return true if renamed, false if another song has that title
     */
    public boolean changeTitle(Song song, String newTitle) {
        // TODO
        return false;
    }

    /***FILE I/O***/

    /**
     * Reads songs from a file and adds them to the library.
     * @param fileName the file to read
     * @return the number of songs loaded
     * @throws IOException if the file cannot be read
     */
    public int loadFromFile(String fileName) throws IOException {
        int count = 0;
        String title = "";
        String artist = "";
        int year = 0;
        String text = "";

        try (BufferedReader reader =
                new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();

            while (line != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty()) {
                    if (!title.isEmpty()
                            && addSong(new Song(title, artist, year, text))) {
                        count++;
                    }
                    title = "";
                    artist = "";
                    year = 0;
                    text = "";
                } else if (hasLabel(trimmed, "title:")) {
                    title = valueOf(trimmed);
                } else if (hasLabel(trimmed, "artist:")) {
                    artist = valueOf(trimmed);
                } else if (hasLabel(trimmed, "year:")) {
                    year = parseYear(valueOf(trimmed));
                } else if (hasLabel(trimmed, "text:")) {
                    text = valueOf(trimmed);
                }
                line = reader.readLine();
            }
            // save the last song if file didn't end with blank line
            if (!title.isEmpty()
                    && addSong(new Song(title, artist, year, text))) {
                count++;
            }
        }
        return count;
    }

    /**
     * Writes every song to a file using the same labeled format.
     * @param fileName the file to write
     * @throws IOException if the file cannot be written
     */
    public void saveToFile(String fileName) throws IOException {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fileName))) {
            ArrayList<Song> songs = getAllSongs();
            for (int i = 0; i < songs.getLength(); i++) {
                Song song = songs.get(i);
                writer.write("title:  " + valueOrEmpty(song.getTitle()));
                writer.newLine();
                writer.write("artist: " + valueOrEmpty(song.getArtist()));
                writer.newLine();
                writer.write("year:   " + song.getYear());
                writer.newLine();
                writer.write("text:   " + valueOrEmpty(song.getText()));
                writer.newLine();

                if (i < songs.getLength() - 1) {
                    writer.newLine();
                }
            }
        }
    }

    /***PRIVATE HELPERS***/

    /**
     * Converts null field values to empty strings before writing records.
     */
    private String valueOrEmpty(String value) {
        if (value == null) {
            return "";
        }
        return value;
    }



    /**
     * Checks whether a line starts with the given label.
     */
    private boolean hasLabel(String line, String label) {
        return line.toLowerCase().startsWith(label);
    }

    /**
     * Returns the part after the first colon, trimmed.
     */
    private String valueOf(String line) {
        int colon = line.indexOf(':');
        return line.substring(colon + 1).trim();
    }

    /**
     * Parses a year string, defaults to 0 if not a number.
     */
    private int parseYear(String text) {
        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
