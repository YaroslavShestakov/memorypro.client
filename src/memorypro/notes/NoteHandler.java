package memorypro.notes;
import java.util.*;
import memorypro.gui.windows.MainWindow;
import memorypro.gui.windows.NewNoteWindow;
/**
 * Class for handling note objects.
 * @author Jani Liikkanen
 */
public class NoteHandler {
   private ArrayList<Note> notes = new ArrayList();
   private MainWindow mainWin = null;
   private NewNoteWindow newNoteWin = null;
    
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
        if (mainWin != null) {
            mainWin.rebuildList();
        }
    }
    
    /*
    public void editNote(int index, Note newNote) {
        Note tmpNote = notes.get(index);
        tmpNote.setTitle(newNote.getTitle());
        tmpNote.setDescription(newNote.getDescription());
        tmpNote.setAlertDate(newNote.getDate());
        mainWin.rebuildList();
    }
    */
    
    /**
     * Sends the selected note's data into the NewNoteWindow frame.
     * @param note The selected note.
     */
    public void editNote(Note note) {
        if (note == null) {
            return;
        }
        if (newNoteWin != null)
            newNoteWin.editNote(note);
    }
    
    /**
     * Returns all saved notes.
     * @return Saved notes.
     */
    public ArrayList getNotes(){
        return this.notes ;
    }
    
    /**
     * Saves the main window for accessibility.
     * @param mw MainWindow object.
     */
    public void addMainWindow(MainWindow mw) {
        mainWin = mw;
    }
    
    /**
     * Saves the new note window for accessibility.
     * @param nnw NewNoteWindow object.
     */
    public void addNewNoteWindow(NewNoteWindow nnw) {
        System.out.println("Added new note window");
        newNoteWin = nnw;
    }
    /**
     * Returns a list of notes whose header matches the search string.
     * @param header Header that is searched from the list.
     * @return List with notes whose header matches the search string.
     */
    public ArrayList<Note> getNotesByHeader(String header) {
        ArrayList<Note> notesToReturn = new ArrayList<Note>();
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getTitle().toLowerCase().contains(header.toLowerCase())) {
                notesToReturn.add(notes.get(i));
            }
        }
        if (notesToReturn.size() > 0) {
            return notesToReturn;
        }
        return null;
    }
}
