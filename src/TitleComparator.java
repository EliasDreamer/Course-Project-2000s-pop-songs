import java.util.Comparator;

/**
 * TitleComparator.java
 * CIS 22C Course Project
 * @author CIS 22C Team
 */
public class TitleComparator implements Comparator<Song> {

    /**
     * Compares two songs by title, case insensitive.
     * @param a the first song
     * @param b the second song
     * @return negative if a comes first, zero if equal,
     *         positive if b comes first
     */
    @Override
    public int compare(Song a, Song b) {
        if (a == b) {
            return 0;
        }
        if (a == null) {
            return -1;
        }
        if (b == null) {
            return 1;
        }

        String aTitle = a.getTitle();
        String bTitle = b.getTitle();
        if (aTitle == bTitle) {
            return 0;
        }
        if (aTitle == null) {
            return -1;
        }
        if (bTitle == null) {
            return 1;
        }
        return aTitle.compareToIgnoreCase(bTitle);
    }
}
