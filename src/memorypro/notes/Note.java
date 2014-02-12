package memorypro.notes;
import java.util.Date;
/**
 * A note class that contains info about a note object.
 * @author Jani Liikkanen
 */
public class Note {
    private String header;
    private String message;
    private Date date;
    
    /**
     * Constructor for Note.
     * @param header Topic of the note.
     * @param message Message of the note.
     * @param date Date for note's deadline.
     */
    public Note(String header, String message, Date date) {
        this.header = header;
        this.message = message;
        this.date = date;
    }
    
    /**
     * Constructor for Note.
     * @param header Topic of the note.
     * @param message Message of the note.
     */
    public Note(String header, String message) {
        this.header = header;
        this.message = message;
        date = new Date();
    }
    
    /**
     * Returns the header of the note.
     * @return Header of the note.
     */
    public String getHeader() {
        return header;
    }
    
    /**
     * Returns the message of the note.
     * @return Message of the note.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Sets the header of a note.
     * @param newHeader New header for the note.
     */
    public void setHeader(String newHeader) {
        header = newHeader;
    }
    
    /**
     * Sets the message of the note.
     * @param newMsg New message for the note.
     */
    public void setMessage(String newMsg) {
        message = newMsg;
    }
    
    /**
     * returns the note's deadline.
     * @return Deadline of the note.
     */
    public Date getDate() {
        return date;
    }
    
    /**
     * Returns a string containing data about the note.
     * @return String with info about the note.
     */
    public String toString() {
        String strToReturn = header;
        strToReturn += " - " + message;
        if (date.toString() != null){
            strToReturn += " - " +  date.toString();
        }
        return strToReturn;
    }
}
