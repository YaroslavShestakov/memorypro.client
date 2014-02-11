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

/**
 *
 * @author Ярослав, Jani Liikkanen
 */
public class MemoryPro {
    public GUI gui ;
    protected User user ;
    protected String sid ;
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
            String query = "?email="+email+"&password="+password ;
            URL url = new URL(server + query);
            URLConnection connection = url.openConnection();
            connection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                result += inputLine ;
            }

            in.close();
            
            return true ;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return false;
    }
    
    public void exit(){
        System.exit(0);
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        MemoryPro app = new MemoryPro(true);
    }

}
