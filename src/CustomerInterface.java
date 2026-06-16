import java.io.IOException;
import java.util.Scanner;

/**
 * CustomerInterface.java
 * CIS 22C Course Project
 * @author
 */
public class CustomerInterface {
    private static final String DEFAULT_FILE = "songs.txt";
    private static final int SONG_TABLE_SIZE = 31;
    private static final int WORD_TABLE_SIZE = 101;

    private static Scanner keyboard = new Scanner(System.in);
    private static SongLibrary library =
            new SongLibrary(SONG_TABLE_SIZE, WORD_TABLE_SIZE);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   2000s Pop Songs Search System");
        System.out.println("=========================================");

        loadStartingData();

        boolean running = true;
        while (running) {
            printMenu();
            String choice = keyboard.nextLine().trim();

            switch (choice) {
                case "1":
                    uploadRecord();
                    break;
                case "2":
                    deleteRecord();
                    break;
                case "3":
                    searchMenu();
                    break;
                case "4":
                    updateRecord();
                    break;
                case "5":
                    showStatistics();
                    break;
                case "6":
                    quit();
                    running = false;
                    break;
                default:
                    System.out.println("Please enter a number from 1 to 6.");
                    continue;
            }

            if (running) {
                System.out.print("\nPress Enter to continue...");
                keyboard.nextLine();
            }
        }
    }

    /**
     * Loads starting songs from the default file.
     */
    private static void loadStartingData() {
        try {
            int loaded = library.loadFromFile(DEFAULT_FILE);
            System.out.println("Loaded " + loaded + " songs from "
                    + DEFAULT_FILE + ".");
        } catch (IOException e) {
            System.out.println("Could not read " + DEFAULT_FILE
                    + ". Starting with an empty library.");
        }
    }

    /**
     * Prints the main menu.
     */
    private static void printMenu() {
        System.out.println();
        System.out.println("----------- Main Menu -----------");
        System.out.println("1. Upload a new song");
        System.out.println("2. Delete a song");
        System.out.println("3. Search for a song");
        System.out.println("4. Modify a song");
        System.out.println("5. Statistics");
        System.out.println("6. Quit");
        System.out.print("Choose an option: ");
    }

    /**
     * Menu option 1: add a new song.
     */
    private static void uploadRecord() {
        System.out.println("\n--- Upload a New Song ---");
        String title = prompt("Title: ");
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty.");
            return;
        }
        if (library.contains(title)) {
            System.out.println("A song with that title already exists.");
            return;
        }
        String artist = prompt("Artist: ");
        int year = promptYear();
        String genre = prompt("Genre: ");
        String text = prompt("Text or keywords: ");
        if (library.addSong(new Song(title, artist, year, genre, text))) {
            if (saveDefaultFile()) {
                System.out.println("Added \"" + title + "\" and saved it to "
                        + DEFAULT_FILE + ".");
            }
        } else {
            System.out.println("Could not add \"" + title + "\".");
        }
    }

    /**
     * Saves the current library to the default song file.
     */
    private static boolean saveDefaultFile() {
        try {
            library.saveToFile(DEFAULT_FILE);
            return true;
        } catch (IOException e) {
            System.out.println("Changed the library in memory, but could not write to "
                    + DEFAULT_FILE + ".");
            return false;
        }
    }


    /**
     * Menu option 2: remove a song by title.
     */
    private static void deleteRecord() {
        System.out.println("\n--- Delete a Song ---");
        Song song = chooseSongFromSearch();
        if (song == null) {
            return;
        }

        System.out.println();
        System.out.println(song.toDetailString());
        String confirm = prompt("Delete this song? (y/n): ");
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("Delete canceled.");
            return;
        }

        if (library.deleteSong(song.getTitle())) {
            saveDefaultFile();
            System.out.println("Deleted the song.");
        } else {
            System.out.println("Could not delete that song.");
        }

    }

    /**
     * Menu option 3: search sub-menu.
     */
    private static void searchMenu() {
        System.out.println("\n--- Search ---");
        System.out.println("1. Find one song by title (primary key)");
        System.out.println("2. Find songs by keyword (search engine)");
        String choice = prompt("Choose an option: ");

        if (choice.equals("1")) {
            searchByTitle();
        } else if (choice.equals("2")) {
            searchByKeyword();
        } else {
            System.out.println("Please enter 1 or 2.");
        }
    }

    /**
     * Finds and displays a single song by title.
     */
    private static void searchByTitle() {
        String title = prompt("Title: ");
        Song song = library.findByTitle(title);
        if (song == null) {
            System.out.println("No song found with that title.");
        } else {
            System.out.println();
            System.out.println(song.toDetailString());
        }
    }

    /**
     * Runs a keyword search and shows matching titles.
     */
    private static void searchByKeyword() {
        String word = prompt("Keyword: ").toLowerCase(); 
        ArrayList<Song> results = library.search(word);

        if (results.getLength() == 0) {
            System.out.println("No songs contain the word \"" + word + "\".");
            return;
        }

        System.out.println("\nThe following songs contain the word \""
                + word + "\":");
        for (int i = 0; i < results.getLength(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getTitle());
        }

        System.out.print("\nEnter a number to view that song, "
                + "or press Enter to skip: ");
        String pick = keyboard.nextLine().trim();
        if (pick.isEmpty()) {
            return;
        }
        try {
            int number = Integer.parseInt(pick);
            if (number >= 1 && number <= results.getLength()) {
                System.out.println();
                System.out.println(results.get(number - 1).toDetailString());
            } else {
                System.out.println("That number is not on the list.");
            }
        } catch (NumberFormatException e) {
            System.out.println("That was not a number.");
        }
    }

    /**
     * Menu option 4: update an existing song.
     */
    private static void updateRecord() {
        // TODO
        System.out.println("\n--- Modify a Song ---");
        Song song = chooseSongFromSearch();
        if (song == null) {
            return;
        }

        System.out.println();
        System.out.println(song.toDetailString());
        String field = prompt("Choose what to modify: (Title/Artist/Year/Genre/Text): ");
        if (field.equalsIgnoreCase("Title")) {
            String newTitle = prompt("new Title: ");
            if (library.changeTitle(song, newTitle)) {
                saveDefaultFile();
                System.out.println("Title updated.");
            } else {
                System.out.println("Title was empty or already exists.");
            }
        } else if (field.equalsIgnoreCase("Artist")) {
            song.setArtist(prompt("new Artist: "));
            saveDefaultFile();
            System.out.println("Artist updated.");
        } else if (field.equalsIgnoreCase("Year")) {
            song.setYear(promptYear("new Year: "));
            saveDefaultFile();
            System.out.println("Year updated.");
        } else if (field.equalsIgnoreCase("Genre")) {
            song.setGenre(prompt("new Genre: "));
            saveDefaultFile();
            System.out.println("Genre updated.");
        } else if (field.equalsIgnoreCase("Text")) {
            library.updateText(song, prompt("new Text: "));
            saveDefaultFile();
            System.out.println("Text updated.");
        } else {
            System.out.println("Please choose Title, Artist, Year, Genre, or Text.");
        }
    }

    /**
     * Menu option 5: display at least 3 statistics.
     */
    private static void showStatistics() {
        System.out.println("\n--- Statistics ---");
        ArrayList<Song> songs = library.getAllSongs();
        int total = songs.getLength();
        System.out.println("Total songs: " + total);
        if (total == 0) {
            return;
        }

        System.out.println("Oldest song: " + library.getOldestSong());
        System.out.println("Newest song: " + library.getNewestSong());
        printYearPercentages(songs, total);
        printGenrePercentages(songs, total);
    }

    /**
     * Lets the user find and choose one song by title or keyword.
     * @return the selected song, or null if none was selected
     */
    private static Song chooseSongFromSearch() {
        System.out.println("1. Find one song by title (primary key)");
        System.out.println("2. Find songs by keyword (search engine)");
        String choice = prompt("Choose an option: ");

        if (choice.equals("1")) {
            String title = prompt("Title: ");
            Song song = library.findByTitle(title);
            if (song == null) {
                System.out.println("No song found with that title.");
            }
            return song;
        } else if (choice.equals("2")) {
            String word = prompt("Keyword: ").toLowerCase();
            ArrayList<Song> results = library.search(word);
            if (results.getLength() == 0) {
                System.out.println("No songs contain the word \"" + word + "\".");
                return null;
            }
            for (int i = 0; i < results.getLength(); i++) {
                System.out.println((i + 1) + ". " + results.get(i));
            }
            String pick = prompt("Choose a song number: ");
            try {
                int number = Integer.parseInt(pick);
                if (number >= 1 && number <= results.getLength()) {
                    return results.get(number - 1);
                }
                System.out.println("That number is not on the list.");
            } catch (NumberFormatException e) {
                System.out.println("That was not a number.");
            }
        } else {
            System.out.println("Please enter 1 or 2.");
        }
        return null;
    }

    /**
     * Prints the percentage of songs for each release year.
     */
    private static void printYearPercentages(ArrayList<Song> songs, int total) {
        System.out.println("\nSongs by year:");
        for (int i = 0; i < songs.getLength(); i++) {
            int year = songs.get(i).getYear();
            boolean alreadyPrinted = false;
            for (int j = 0; j < i; j++) {
                if (songs.get(j).getYear() == year) {
                    alreadyPrinted = true;
                }
            }
            if (!alreadyPrinted) {
                int count = 0;
                for (int j = 0; j < songs.getLength(); j++) {
                    if (songs.get(j).getYear() == year) {
                        count++;
                    }
                }
                printPercent(String.valueOf(year), count, total);
            }
        }
    }

    /**
     * Prints the percentage of songs for each genre.
     */
    private static void printGenrePercentages(ArrayList<Song> songs, int total) {
        System.out.println("\nSongs by genre:");
        for (int i = 0; i < songs.getLength(); i++) {
            String genre = normalizeGenre(songs.get(i).getGenre());
            boolean alreadyPrinted = false;
            for (int j = 0; j < i; j++) {
                if (normalizeGenre(songs.get(j).getGenre()).equalsIgnoreCase(genre)) {
                    alreadyPrinted = true;
                }
            }
            if (!alreadyPrinted) {
                int count = 0;
                for (int j = 0; j < songs.getLength(); j++) {
                    if (normalizeGenre(songs.get(j).getGenre()).equalsIgnoreCase(genre)) {
                        count++;
                    }
                }
                printPercent(genre, count, total);
            }
        }
    }

    private static String normalizeGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            return "Unknown";
        }
        return genre.trim();
    }

    private static void printPercent(String label, int count, int total) {
        double percent = (double) count * 100 / total;
        System.out.printf("%s: %d/%d (%.2f%%)%n", label, count, total, percent);
    }

    /**
     * Menu option 6: save and quit.
     */
    private static void quit() {
        System.out.println("\n--- Quit ---");
        while (true) {
            String fileName = prompt("File name to save to ["
                    + DEFAULT_FILE + "]: ");
            if (fileName.isEmpty()) {
                fileName = DEFAULT_FILE;
                String confirm = prompt("Overwrite " + DEFAULT_FILE
                        + "? (y/n): ");
                if (!confirm.equalsIgnoreCase("y")) {
                    System.out.println("Please enter a different file name.");
                    continue;
                }
            }
            try {
                library.saveToFile(fileName);
                System.out.println("Saved " + library.size()
                        + " songs to " + fileName + ".");
                break;
            } catch (IOException e) {
                System.out.println("Could not write to " + fileName + ".");
                System.out.println("Please try another file name.");
            }
        }
        System.out.println("Goodbye.");
    }

    /**
     * Prints a prompt and reads one trimmed line.
     */
    private static String prompt(String message) {
        System.out.print(message);
        return keyboard.nextLine().trim();
    }

    /**
     * Prompts for a year and keeps asking until it gets a number.
     * @return the year
     */
    private static int promptYear() {
        return promptYear("Year: ");
    }

    /**
     * Prompts for a year with a custom message.
     * @param message the prompt message
     * @return the year
     */
    private static int promptYear(String message) {
        while (true) {
            String input = prompt(message);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please type a year as a number.");
            }
        }
    }
}
