package memorypro.notes;
import java.text.SimpleDateFormat;
import java.util.Date;
import memorypro.Server;
/**
 * A note class that contains info about a note object.
 * @author Jani Liikkanen
 */
public class Note {
    private Integer id ;
    private String title;
    private String description;
    private Date alertdate;
    private boolean enabled ;
    private Integer userId ;
    
    /**
     * Constructor for Note.
     * @param header Topic of the note.
     * @param message Message of the note.
     * @param date Date for note deadline.
     */
    public Note(String title, String description, Date date) {
        this.title = title ;
        this.description = description ;
        this.alertdate = date;
        this.enabled = true ;
    }
    
    public Note(){
        
    }
    
    /**
     * Constructor for Note.
     * @param header Topic of the note.
     * @param message Message of the note.
     */
    public Note(String title, String description) {
        this(title, description, new Date());
    }
    
    /**
     * Returns the header of the note.
     * @return Header of the note.
     */
    public String getTitle() {
        return title ;
    }
    
    /**
     * Returns the message of the note.
     * @return Message of the note.
     */
    public String getDescription() {
        return description ;
    }
    
    /**
     * returns the note alert date.
     * @return Deadline of the note.
     */
    public Date getDate() {
        return alertdate;
    }
    
    /**
     * Returns the boolean value of the note's activity state.
     * @return 
     */
    public boolean isEnabled(){
        return this.enabled ;
    }
    
    /**
     * Returns the activity state of the note.
     * @return the activity state.
     */
    public boolean isActive(){
        return (this.alertdate.after(new Date())) ;
    }
    
    /**
     * Sets the header of a note.
     * @param newHeader New header for the note.
     */
    public void setTitle(String title){
        this.title = title ;
    }
    
    public void setEnabled(boolean status){
        this.enabled = status ;
    }
    
    /**
     * Sets the message of the note.
     * @param newMsg New message for the note.
     */
    public void setDescription(String description){
        this.description = description ;
    }
    
    public void setAlertDate(Date date){
        this.alertdate = date ;
    }
    
    public int getId(){
        return this.id ;
    }
    
    public void setId(int id){
        this.id = id ;
    }

    
    /**
     * Returns a string containing data about the note.
     * @return String with info about the note.
     */
    @Override
    public String toString() {
        String strToReturn = "";
        if (alertdate != null){
//            strToReturn += "                        " +  (new SimpleDateFormat("dd-mm-yy HH:mm")).format(alertdate) ;
            strToReturn += (new SimpleDateFormat("dd.MM.yyyy")).format(alertdate) ;
            strToReturn += " - ";
        }
        strToReturn += title;
        return strToReturn;
    }

    /**
     * Returns the date the note is supposed to alert.
     * @return Alert date of the note.
     */
    public Date getAlertDate() {
        return this.alertdate ;
    }
}
