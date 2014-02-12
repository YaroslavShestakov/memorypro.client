package memorypro;

import memorypro.gui.panels.JPanelMain;
import memorypro.gui.panels.JPanelLogin;
import memorypro.gui.panels.JPanelBrowse;
import memorypro.gui.panels.JPanelAccount;
import memorypro.gui.GUI;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JFrame;
import memorypro.gui.Window;
import memorypro.notes.NoteHandler;
import org.json.JSONObject;
/**
 *
 * @author Ярослав, Jani Liikkanen
 */
public class MemoryPro {
    public GUI gui ;
    public ErrorHandler errhandler = new ErrorHandler();
    
    protected User user ;
    protected String sid = null ;
    protected Display display = new Display();
    
    private String server = "http://koti.tamk.fi/~c2yshest/mp/api/" ;
    
        
    public MemoryPro(boolean useGUI) {
        NoteHandler nh = new NoteHandler();
        if (useGUI){
            gui = new GUI(this);
            gui.openWindow(Window.LOGIN);
        }
    }
    
    public boolean login(String email, String password){
        try {
            String query = "?action=login&email="+email+"&password="+password ;
            URL url = new URL(server + query);
            URLConnection connection = url.openConnection();
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String json = "";
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                json += inputLine ;
            }

            in.close();
            
            @SuppressWarnings("null")
            JSONObject response = new JSONObject(json);
            
            String sid = response.getString("sid");
            Boolean status = response.getBoolean("status");

            
            if (status){
                this.user = new User();
                JSONObject data = response.getJSONObject("data");
                
                this.user.id = data.getInt("id");
                this.user.firstname = data.getString("firstname");
                this.user.lastname = data.getString("lastname");
                
                this.user.email = email;
                this.user.password = password;
                
                this.sid = sid ;
                return true ;
            } else {
                errhandler.setLastError("login", Error.WRONG_CREDENTIALS);
                return false ;
            }
        } catch (Exception e) {
            e.printStackTrace();
            
            errhandler.setLastError("login", Error.NO_CONNECTION);
            //return false ;
        }
        
        //errhandler.setLastError("login", Error.UNKNOWN);
        return false;
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
