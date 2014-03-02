package memorypro;

import memorypro.utils.Display;
import memorypro.gui.GUI;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import memorypro.gui.windows.Window;
import memorypro.notes.Note;
import memorypro.notes.NoteHandler;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author Ярослав, Jani Liikkanen
 */
public class MemoryPro {
    public GUI gui ;
    public ErrorHandler errhandler = new ErrorHandler();
    public NoteHandler notehandler = new NoteHandler();
    
    protected User user ;
    /**
     * Session id, used for authentication.
     */
    protected String sid = null ;
    protected Display display = new Display();
    protected Timer timer = new Timer();
        
    /**
     * Creates a MemoryPro object.
     * @param useGUI Determines whether Graphics User Interface should be used.
     */
    public MemoryPro(boolean useGUI) {
        NoteHandler nh = new NoteHandler();
        if (useGUI){
            gui = new GUI(this);
            gui.openWindow(Window.LOGIN);
        }
    }
    
    /**
     * Returns User object container
     * @return User object
     */
    public User getUser(){
        return this.user ;
    }
    
    /**
     * Login to the program.
     * @param email User's email address.
     * @param password User's password.
     * @return Returns true if login succeeds.
     */
    public boolean login(String email, String password){
        String request = "?action=login" ;
        request += "&data[email]="    + Server.encode(email) ;
        request += "&data[password]=" + Server.encode(password) ;
        
        URLConnection connection = Server.getConnection(request);
        if (connection != null){
            String json = Server.getResponse(connection);
            if (json != null){
                
                @SuppressWarnings("null")
                JSONObject response = new JSONObject(json);

                Boolean status = response.getBoolean("status");

                if (status){
                    JSONObject data = response.getJSONObject("data");
                    JSONObject user = data.getJSONObject("user");
                    String sid  = data.getString("sid") ;

                    this.user = new User();

                    this.user.id        = user.getInt("id");
                    this.user.firstname = user.getString("firstname");
                    this.user.lastname  = user.getString("lastname");

                    this.user.email     = email;
                    this.user.password  = password;

                    this.sid = sid ;
                    return true ;
                } else {
                    errhandler.setLastError("login", Error.WRONG_CREDENTIALS);
                    return false ;
                }         
            } else {
                errhandler.setLastError("login", Error.UNKNOWN);
            }
        } else {   
            errhandler.setLastError("login", Error.NO_CONNECTION);
        }
        return false;
    }

    /**
     * Loads all notes of the user from the database.
     * @return Return true if loading succeeds.
     */
    public boolean loadNotes(){
        if (this.sid != null){
            String request = "?action=get_notes&sid="+Server.encode(this.sid) ;

            URLConnection connection = Server.getConnection(request);
            if (connection != null){
                String json = Server.getResponse(connection);
                if (json != null){
          
                    JSONObject response = new JSONObject(json);
                    Boolean status   = response.getBoolean("status") ;
                    
                    if (status){
                        notehandler.clear();
                        JSONArray notes = null ;
                        try {
                            notes = response.getJSONArray("data");
                        } catch (Exception e){
                            return false ;
                        }
                        
                                                    
                        // Timestamp from MySQL is in format of yyyy-MM-dd HH:mm:ss
                        DateFormat ts_format = new SimpleDateFormat(Server.MySQL_TS_FORMAT);
                        
                        for (int i = 0 ; i < notes.length() ; i++){
                            JSONObject obj = notes.getJSONObject(i);
                            
                            Integer id    = obj.getInt("id");
                            String title = obj.getString("title");
                            String descr = obj.getString("description");
                            Boolean enabled =  obj.getBoolean("enabled");
                            String timestamp = obj.getString("alertdate");
                            
                            Date alertdate = null ;
                            try {
                                alertdate = ts_format.parse(timestamp);
                            } catch (Exception e){
                                alertdate = new Date();
                            }
                            
                            Note note = new Note();
                            note.setTitle       (title);
                            note.setDescription (descr);
                            note.setEnabled     (enabled);
                            note.setAlertDate   (alertdate);
                            note.setId          (id);

                            notehandler.addNote(note);                          
                        }
                        scheduleTimer();
                    }
                }
            }
        }
        return false ;
    }
    
    /**
     * Add a new note into database.
     * @param note The note added to database.
     * @return Returns a boolean value if adding succeeded.
     */
    public boolean addNote(Note note){
        if (this.sid != null){
            String request = "?action=add_note&sid="+this.sid ;
            request += "&data[title]="          + Server.encode(note.getTitle()); 
            request += "&data[description]="    + Server.encode(note.getDescription());
            request += "&data[enabled]="        + Server.encode(String.valueOf(note.isEnabled()));
            request += "&data[alertdate]="      + Server.encode(
                (new SimpleDateFormat(Server.MySQL_TS_FORMAT)).
                    format(note.getAlertDate()));
            
            URLConnection connection = Server.getConnection(request);
            if (connection != null){
                String json = Server.getResponse(connection);
                if (json != null){
                    
                    JSONObject response = new JSONObject(json);
                    Boolean status   = response.getBoolean("status") ;
                    
                    if (status){
                        scheduleTimer();
                        return true ;
                    }
                }
            }
        }
        return false ;
    }
    
    /**
     * Deletes a note from database
     * @param note a Note object to delete
     * @return true on success and false on failure
     */
    public boolean deleteNote(Note note){
        if (this.sid != null){
            String request = "?action=delete_note" ;
            request += "&sid="      + Server.encode(this.sid) ;
            request += "&data[id]=" + Server.encode(String.valueOf(note.getId()));
            
            URLConnection connection = Server.getConnection(request);
            if (connection != null){
                String json = Server.getResponse(connection);
                if (json != null){
                    
                    JSONObject response = new JSONObject(json);
                    Boolean status   = response.getBoolean("status") ;
                    
                    if (status){
                        scheduleTimer();
                        return true ;
                    }
                }
            }
        }
        return false ;
    }
    
    /**
     * Edits the note data
     * @param note Note object with new information
     * @return true on success and false on failure
     */
    public boolean editNote(Note note){
        if (this.sid != null){
            String request = "?action=edit_note&sid=" + Server.encode(this.sid) ;
            request += "&data[id]="             + Server.encode(String.valueOf(note.getId())); 
            request += "&data[title]="          + Server.encode(note.getTitle()); 
            request += "&data[description]="    + Server.encode(note.getDescription());
            request += "&data[enabled]="        + Server.encode(String.valueOf(note.isEnabled()));
            request += "&data[alertdate]="      + Server.encode(
                    (new SimpleDateFormat(Server.MySQL_TS_FORMAT))
                    .format(note.getAlertDate()));
            
            URLConnection connection = Server.getConnection(request);
            if (connection != null){
                String json = Server.getResponse(connection);
                if (json != null){
                    
                    JSONObject response = new JSONObject(json);
                    Boolean status   = response.getBoolean("status") ;
                    
                    if (status){
                        scheduleTimer();
                        return true ;
                    }
                }
            }
        }
        return false ;
    }
    
    

    /**
     * Closes the program.
     */
    public void exit(){
        System.exit(0);
    }
    
    /**
     * Prints contents of an object.
     * @param o Any object
     */
    public void print(Object o){
        System.out.println(o);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        MemoryPro app = new MemoryPro(true);
    }

    /**
     * Logs out the current user.
     */
    public void logout() {
        notehandler.clear();
        this.user = new User();
        this.sid = null ;
    }

    /**
     * Registers a new user, add data to the database.
     * @param email Email/username
     * @param pass  Password
     * @param firstname First name
     * @param lastname Last name
     * @param phone Phone
     * @return true on success and false on failure
     */
    public boolean register(String email, String pass, String firstname, String lastname, String phone) {
        String request = "?action=register";
        request += "&data[email]=" + Server.encode(email);
        request += "&data[password]=" + Server.encode(pass);
        request += "&data[firstname]=" + Server.encode(firstname);
        request += "&data[lastname]=" + Server.encode(lastname);
        request += "&data[phone]=" + Server.encode(phone);
        
        URLConnection connection = Server.getConnection(request);
        if (connection != null) {
            String json = Server.getResponse(connection);

            if (json != null) {
                JSONObject response = new JSONObject(json);
                Boolean status = response.getBoolean("status");

                return status;
            }
        }
        
        return false ;
    }
    
        
    /**
     * Schedules alerts for notes of the current user.
     */
    public void scheduleTimer(){
        timer.cancel();
        timer.purge();
        timer = new Timer();
        
        Date now = new Date();
        for (final Note note : notehandler.getNotes()){
            
            final Date alertdate = note.getAlertDate() ;
            if (note.isEnabled() && alertdate.after(now)){
                long delay = alertdate.getTime() - now.getTime();
                timer.schedule(new TimerTask(){

                    @Override
                    public void run() {
                        JOptionPane.showMessageDialog(null,
                                note.getDescription() + "\n"+
                                (new SimpleDateFormat("dd-mm-yy HH:mm")).format(alertdate),
                                "Notification: " + note.getTitle(),
                        JOptionPane.WARNING_MESSAGE);
                    }

                }, delay);
            }
        }
    }
}
