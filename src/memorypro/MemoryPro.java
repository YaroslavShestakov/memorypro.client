package memorypro;

import memorypro.gui.GUI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    protected String sid = null ;
    protected Display display = new Display();
        
    public MemoryPro(boolean useGUI) {
        NoteHandler nh = new NoteHandler();
        if (useGUI){
            gui = new GUI(this);
            gui.openWindow(Window.LOGIN);
        }
    }
    
    public boolean login(String email, String password){
        String request = "?action=login&data[email]="+email+"&data[password]="+password ;
        
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

    public boolean loadNotes(){
        if (this.sid != null){
            String request = "?action=get_notes&sid="+this.sid ;

            URLConnection connection = Server.getConnection(request);
            if (connection != null){
                String json = Server.getResponse(connection);
                if (json != null){
          
                    JSONObject response = new JSONObject(json);
                    Boolean status   = response.getBoolean("status") ;
                    
                    if (status){
                        notehandler.clear();
                        
                        JSONArray notes = response.getJSONArray("data");
                        
                                                    
                        // Timestamp from MySQL is in format of yyyy-MM-dd HH:mm:ss
                        DateFormat ts_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        
                        for (int i = 0 ; i < notes.length() ; i++){
                            JSONObject obj = notes.getJSONObject(i);
                            
                            String title = obj.getString("title");
                            String descr = obj.getString("description");
                            Boolean enabled = "1".equals(obj.getString("enabled"));
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

                            notehandler.addNote(note);                          
                        }
                        
                    }
                }
            }
        }
        return false ;
    }
    
    public boolean addNote(Note note){
        if (this.sid != null){
            String request = "?action=add_note&sid="+this.sid ;
            request += "&data[title]="          + note.getTitle(); 
            request += "&data[description]="    + note.getDescription();
            request += "&data[enabled]="        + note.isEnabled();
            request += "&data[alertdate]="      + note.getAlertDate();
            
            URLConnection connection = Server.getConnection(request);
            if (connection != null){
                String json = Server.getResponse(connection);
                if (json != null){
                    
                    JSONObject response = new JSONObject(json);
                    Boolean status   = response.getBoolean("status") ;
                    
                    if (status){
                        return true ;
                    }
                }
            }
        }
        return false ;
    }
    
    

    public void exit(){
        System.exit(0);
    }
    
    public void print(Object o){
        System.out.println(o);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        MemoryPro app = new MemoryPro(true);
    }

    public void logout() {
        this.user = new User();
        this.sid = null ;
    }
}
