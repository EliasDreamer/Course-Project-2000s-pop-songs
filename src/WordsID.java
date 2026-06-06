public class WordsID {
    private String word;
    private int ID;

    public String getWord(){
        return word;
    }
    public int getID(){
        return ID;
    }

//    /*Constructor*/
//    // I am not sure if the ID should be generated  when Initializing the WordsID object
//    // and the hashCode is not unique if we do like this
//    // maybe there is another way
//    public WordsID(String word){
//        this.word = word;
//        this.ID = word.hashCode();
//    }
}
