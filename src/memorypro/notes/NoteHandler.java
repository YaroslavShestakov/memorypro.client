package memorypro.notes;
import java.util.*;
/**
 * Class for handling note objects.
 * @author Jani Liikkanen
 */
public class NoteHandler {
   private ArrayList<Note> notes = new ArrayList();
    
   /**
    * Constructor for the NoteHandler.
    */
    public NoteHandler() {
        notes.add(new Note("Wash dishes", "Carefully wash dishes"));
        notes.add(new Note("Make food", "Make some delicious food before guests arrive at 17.00"));
    }
    
    /**
     * Adds a new note into the note list.
     * @param newNote Note that is added into the list.
     */
    public void addNote(Note newNote) {
        notes.add(newNote);
    }
    
    public ArrayList getNotes(){
        return this.notes ;
    }
    
    /**
     * Returns a list of notes whose header matches the search string.
     * @param header Header that is searched from the list.
     * @return List with notes whose header matches the search string.
     */
    /*public static LinkedList<Note> getNotesByHeader(String header) {
        LinkedList<Note> notesToReturn = new LinkedList<>();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getHeader().toLowerCase().contains(header.toLowerCase())) {
//            if (notes.get(i).getHeader().equals(header)) {
                notesToReturn.add(notes.get(i));
            }
        }
        if (notesToReturn.size() > 0) {
            return notesToReturn;
        }
        return null;
    }
    
    /**
     * Returns a note from certain index.
     * @param index Index of the note wanted.
     * @return Note from a certain index.
     */
    /*public static Note get(int index) {
        if (index < notes.size()) {
            return notes.get(index);
        }
        else {
            return null;
        }
    }*/
    
    /**
     * Returns the size of the note list.
     * @return Size of the note list.
     */
    /*public static int size() {
        return notes.size();
    }*/
}
