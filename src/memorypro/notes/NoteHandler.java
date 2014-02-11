package memorypro.notes;
import java.util.LinkedList;
/**
 *
 * @author Jani Liikkanen
 */
public class NoteHandler {
   private static LinkedList<Note> notes;
    
    public NoteHandler() {
        notes = new LinkedList<>();
        notes.add(new Note("asd", "asdasdasda"));
        notes.add(new Note("asd2", "asdasdasda2"));
    }
    
    public static void addNote(Note newNote) {
        notes.add(newNote);
    }
    
    public static LinkedList<Note> getNotesByHeader(String header) {
        LinkedList<Note> notesToReturn = new LinkedList<>();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getHeader().equals(header)) {
                notesToReturn.add(notes.get(i));
            }
        }
        if (notesToReturn.size() > 0) {
            return notesToReturn;
        }
        return null;
    }
    
    public static Note get(int index) {
        if (index < notes.size()) {
            return notes.get(index);
        }
        else {
            return null;
        }
    }
    
    public static int size() {
        return notes.size();
    }
}
