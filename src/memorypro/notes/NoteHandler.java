package memorypro.notes;
import java.util.LinkedList;
/**
 *
 * @author Jani Liikkanen
 */
public class NoteHandler {
    LinkedList<Note> notes;
    
    public NoteHandler() {
        notes = new LinkedList<Note>();
        notes.add(new Note("asd", "asdasdasda"));
    }
    
    public void addNote(Note newNote) {
        notes.add(newNote);
    }
    
    public LinkedList<Note> getNotesByHeader(String header) {
        LinkedList<Note> notesToReturn = new LinkedList<Note>();
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
    
    public Note get(int index) {
        if (index < notes.size()) {
            return notes.get(index);
        }
        else {
            return null;
        }
    }
    
    public int size() {
        return notes.size();
    }
}
