public class PopSong {
    private String title;
    private String artist;
    private String key;
    private int years;
    private int length;
    private String album;
    private String lyrics;

    // Constructors
    public PopSong(){
        this.key = "";
        this.title = "" ;
        this.artist = "" ;
        this.years = -1;
        this.length = -1 ;
        this.album = "" ;
        this.lyrics = "" ;
    }
    public PopSong(String title,
                   String artist, String key,
                   int years,
                   int length,
                   String album,
                   String lyrics) {
        this.title = title;
        this.artist = artist;
        this.key = key;
        this.years = years;
        this.length = length;
        this.album = album;
        this.lyrics = lyrics;
    }

    // Accessors
    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public String getKey(){
        return key;
    }

    public int getYears(){
        return years;
    }

    public int getLength(){
        return length;
    }

    public String getAlbum(){
        return album;
    }

    public String getLyrics(){
        return lyrics;
    }

    // Mutators

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYears(int years) {
        this.years = years;
    }

}
