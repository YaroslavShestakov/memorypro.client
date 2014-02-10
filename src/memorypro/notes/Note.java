package memorypro.notes;
import java.util.Date;
/**
 *
 * @author Jani Liikkanen
 */
public class Note {
    private String header;
    private String message;
    private Date date;
    
    public Note(String header, String message, Date date) {
        this.header = header;
        this.message = message;
        this.date = date;
    }
    
    public Note(String header, String message) {
        this.header = header;
        this.message = message;
        date = new Date();
    }
    
    public String getHeader() {
        return header;
    }
    public String getMessage() {
        return message;
    }
    public void setHeader(String newHeader) {
        header = newHeader;
    }
    public void setMessage(String newMsg) {
        message = newMsg;
    }
    public Date getDate() {
        return date;
    }
    public String toString() {
        String strToReturn = header;
        if (date.toString() != null){
            strToReturn += " " +  date.toString();
        }
        return strToReturn;
    }
}
