# 2000s Pop Songs Project

Great job everyone, the code side is basically done. Everyone's parts are in, it compiles clean, and I ran it top to bottom and through a bunch of edge cases and it holds up. Take one last good look at your own part so you understand it for the presentation, then we can move on to the slides and the demo.

The program loads a set of 2000s pop songs from a text file into a hash table (keyed by title) and an inverted index for keyword search. From the menu you can add, delete, search, and modify songs, see some statistics, and save everything back to a file.

## The menu

```
1. Upload a new song
2. Delete a song
3. Search for a song
     1. By title (primary key)
     2. By keyword (search engine)
4. Modify a song
5. Statistics
6. Quit and save
```

## Testing

I went through the program and tested all of the features along with a lot of edge cases. Everything below was run on the current code and worked as expected. There are 37 tests grouped by feature. Running the main path for the recording takes about 10 to 14 minutes.

To save time during testing, here is a sample song you can copy and paste in for the add, modify, and delete tests:

- Title: Viva la Vida
- Artist: Coldplay
- Year: 2008
- Genre: Alt Rock
- Text: revolution castle ruled the world

For the modify test, change its text to `aurora midnight sky` and its title to `Viva la Vida (Live)`. Useful keywords to try: love, dance, the, lov, 99, talk, revolution, aurora.

### Loading

| #   | What we tested         | Steps                           | What we expected and saw                         |
| --- | ---------------------- | ------------------------------- | ------------------------------------------------ |
| 1   | Loads from the file    | Start the program               | `Loaded 16 songs from songs.txt.`                |
| 2   | Handles a missing file | Start with no songs.txt present | Warns and starts with an empty library, no crash |

### Statistics

| #   | What we tested            | Steps                                   | What we expected and saw                                                                    |
| --- | ------------------------- | --------------------------------------- | ------------------------------------------------------------------------------------------- |
| 3   | Stats on the full data    | Choose Statistics                       | Total 16, oldest 2000, newest 2016, average year 2005.2, plus the year and genre breakdowns |
| 4   | Stats on an empty library | With no songs loaded, choose Statistics | Shows total 0 and returns, no crash                                                         |

### Search by title

| #   | What we tested            | Steps                                              | What we expected and saw                                             |
| --- | ------------------------- | -------------------------------------------------- | -------------------------------------------------------------------- |
| 5   | Find a song by title      | Search, By title, enter `Toxic` (also try `toxic`) | Finds the Toxic record either way, so capitalization does not matter |
| 6   | Title that does not exist | Search, By title, enter `No Such Song`             | `No song found with that title.`                                     |

### Search by keyword

| #   | What we tested        | Steps                                                          | What we expected and saw                                        |
| --- | --------------------- | -------------------------------------------------------------- | --------------------------------------------------------------- |
| 7   | Keyword match, sorted | Search, By keyword, enter `love` (also try `LOVE`)             | Crazy in Love, Poker Face, Toxic in order, case does not matter |
| 8   | Many matches          | Search, By keyword, enter `dance`                              | Seven songs, alphabetical                                       |
| 9   | Stop word removed     | Search, By keyword, enter `the`                                | No matches, which proves common words were stripped out         |
| 10  | Whole word only       | Search, By keyword, enter `lov`                                | No matches, it does not match part of a word                    |
| 11  | View a result         | After searching `love`, type the number of one result          | Shows that song's full record                                   |
| 12  | Bad selection         | After a search, type a number out of range or some letters     | Friendly message, no crash                                      |
| 13  | Blank keyword         | Search, By keyword, press Enter                                | No matches, no crash                                            |
| 14  | Number as a keyword   | Add a song with `club 99 banger` in the text, then search `99` | Finds that song                                                 |
| 15  | Word from real lyrics | Search, By keyword, enter `talk`                               | Finds We don't talk anymore                                     |

### Adding a song

| #   | What we tested                      | Steps                                                                                          | What we expected and saw                                |
| --- | ----------------------------------- | ---------------------------------------------------------------------------------------------- | ------------------------------------------------------- |
| 16  | Add and confirm it shows everywhere | Upload the sample song, then search it by title, by keyword `revolution`, and check Statistics | Found by title and keyword, and the count goes up to 17 |
| 17  | Reject a duplicate title            | Upload with title `Toxic` (also try `toxic`)                                                   | Rejected, even with different capitalization            |
| 18  | Reject an empty title               | Upload and leave the title blank or just spaces                                                | `Title cannot be empty.`                                |
| 19  | Trim extra spaces                   | Upload with title `  Spaced Title  `                                                           | Saved and shown as `Spaced Title`                       |
| 20  | Reject a bad year                   | Upload and type letters at the Year prompt                                                     | Asks again for a number                                 |

### Modifying a song

| #   | What we tested          | Steps                                                                                 | What we expected and saw                                              |
| --- | ----------------------- | ------------------------------------------------------------------------------------- | --------------------------------------------------------------------- |
| 21  | Change the text         | Modify the sample song, choose Text, enter new text                                   | The old keyword stops matching and the new one matches                |
| 22  | Change the title        | Modify the sample song, choose Title, enter a new title                               | The old title is gone and the new title is found                      |
| 23  | Reject a clashing title | Modify and set the title to one that exists, like `Umbrella` (also with extra spaces) | Rejected                                                              |
| 24  | Change other fields     | Modify a song, change the artist, year, or genre                                      | The field updates, confirmed by searching again                       |
| 25  | Clear the text          | Modify a song and set its text to blank                                               | That song stops showing in keyword search but is still found by title |
| 26  | Reject a bad field      | Modify a song and type a field name like `Color`                                      | Friendly message listing the valid fields                             |

### Deleting a song

| #   | What we tested               | Steps                                                                       | What we expected and saw                                                                  |
| --- | ---------------------------- | --------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------- |
| 27  | Delete and confirm           | Delete a song and answer yes                                                | Deleted, and it is gone from title search, keyword search, statistics, and the saved file |
| 28  | Cancel a delete              | Delete a song and answer no                                                 | Canceled, the song stays                                                                  |
| 29  | Delete a missing song        | Delete a title that does not exist                                          | `No song found with that title.`                                                          |
| 30  | Shared keyword stays correct | Delete one song that shares a keyword with others, then search that keyword | Only that one is gone, the rest still match                                               |
| 31  | Delete then re-add           | Delete a song, then add it back                                             | Comes back and is searchable again                                                        |

### Saving and quitting

| #   | What we tested               | Steps                                                  | What we expected and saw                            |
| --- | ---------------------------- | ------------------------------------------------------ | --------------------------------------------------- |
| 32  | Save to a file               | Quit and type a file name                              | Saves all songs to that file, in alphabetical order |
| 33  | Do not overwrite by accident | Quit, press Enter for the default, then answer no      | Asks for a different file name                      |
| 34  | File name with a space       | Quit and save as `my songs.txt`                        | Saves with that exact name                          |
| 35  | Data persists                | Reload a file you saved, then look up a song you added | Still there, so it carried over between runs        |

### Bad input

| #   | What we tested       | Steps                                                | What we expected and saw             |
| --- | -------------------- | ---------------------------------------------------- | ------------------------------------ |
| 36  | Bad main menu choice | Type `9` or some letters at the main menu            | `Please enter a number from 1 to 6.` |
| 37  | Bad search choice    | In the search menu, type something other than 1 or 2 | `Please enter 1 or 2.`               |
