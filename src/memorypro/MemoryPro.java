package memorypro;

import memorypro.gui.panels.JPanelMain;
import memorypro.gui.panels.JPanelLogin;
import memorypro.gui.panels.JPanelBrowse;
import memorypro.gui.panels.JPanelAccount;
import memorypro.gui.GUI;
import java.awt.Dimension;
import javax.swing.JFrame;
import memorypro.gui.Window;

/**
 *
 * @author Ярослав, Jani Liikkanen
 */
public class MemoryPro {
    protected GUI gui ;
    protected User user ;
    protected String sid ;
    protected Display display = new Display();
    
        
    public MemoryPro(boolean useGUI) {
        if (useGUI){
            gui = new GUI(this);
            gui.openWindow(Window.LOGIN);
        }
    }
    
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {    
        MemoryPro app = new MemoryPro(true);
    }

}
