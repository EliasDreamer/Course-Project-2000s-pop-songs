/**
 * Song.java
 * CIS 22C Course Project
 * @author
 */
public class Song {
    private String title;
    private String artist;
    private int year;
    private String genre;
    private String text;

    /***CONSTRUCTORS***/

    /**
     * Full constructor.
     * @param title the song title (primary key)
     * @param artist the performing artist
     * @param year the year the song came out
     * @param text the searchable text for this song
     */
    public Song(String title, String artist, int year, String text) {
        this(title, artist, year, "", text);
    }

    /**
     * Full constructor including genre.
     * @param title the song title (primary key)
     * @param artist the performing artist
     * @param year the year the song came out
     * @param genre the song genre or type
     * @param text the searchable text for this song
     */
    public Song(String title, String artist, int year, String genre, String text) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.genre = genre;
        this.text = text;
    }

    /**
     * Search-key constructor.
     * Creates a "probe" song that only carries a title. Use this
     * when you want to look a song up in the HashTable by name,
     * since the table only compares titles.
     * @param title the title to search for
     */
    public Song(String title) {
        this(title, "", 0, "");
    }

    /***ACCESSORS***/

    /**
     * Returns the title of this song.
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the artist.
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Returns the year this song was released.
     * @return the release year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns the searchable text for this song.
     * @return the text
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the searchable text for this song.
     * @return the text
     */
    public String getText() {
        return text;
    }

    /***MUTATORS***/

    /**
     * Changes the title.
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Changes the artist.
     * @param artist the new artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Changes the year.
     * @param year the new year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Changes the text.
     * @param genre the new genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Changes the text.
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /***ADDITIONAL OPERATIONS***/

    /**
     * Two songs are equal when they share a title, ignoring case.
     * The title is the primary key so that is all we compare.
     * @param obj the object to compare
     * @return whether the two songs have the same title
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Song)) {
            return false;
        }
        Song other = (Song) obj;
        if (title == null) {
            return other.title == null;
        }
        return title.equalsIgnoreCase(other.title);
    }

    /**
     * Hash code based on the lowercased title so it matches equals.
     * @return the hash code
     */
    @Override
    public int hashCode() {
        if (title == null) {
            return 0;
        }
        return title.toLowerCase().hashCode();
    }

    /**
     * One line summary for lists.
     * @return the title and artist
     */
    @Override
    public String toString() {
        return title + " by " + artist + " (" + year + ")";
    }

    /**
     * Full record for when the user views one song.
     * @return every field on its own line
     */
    public String toDetailString() {
        return "Title: " + title + "\n"
                + "Artist: " + artist + "\n"
                + "Year: " + year + "\n"
                + "Genre: " + genre + "\n"
                + "Text: " + text;
    }
}
