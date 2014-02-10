package memorypro;

import java.awt.Dimension;
import javax.swing.JFrame;
import memorypro.windows.*;
import memorypro.notes.*;

/**
 *
 * @author Ярослав, Jani Liikkanen
 */
public class MemoryPro extends JFrame {
//    public JFrame frame;
//    public WindowListener listener;
    JPanelLogin loginScreen;
    JPanelMain mainScreen;
    JPanelBrowse browseScreen;
    JPanelAccount accountScreen;
    WindowListener listener;
    NoteHandler noteHandler;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MemoryPro frame = new MemoryPro();
    }
    
    public MemoryPro() {
    
        noteHandler = new NoteHandler();
        listener = new WindowListener(this);
        loginScreen = new JPanelLogin(listener);
        
        getContentPane().add(loginScreen);
           
        setVisible(true);
        setSize(800, 600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void changeView(String view) {
        switch(view) {
            case "main":
                gotoMain();
                break;
            case "browse":
                gotoBrowse();
                break;
            case "account":
                gotoAccount();
                break;
            default:
                System.out.println("Cannot find view " + view);
                break;
        }
    }
    
    public void gotoMain() {
        getContentPane().removeAll();
        if (mainScreen == null) {
            mainScreen = new JPanelMain(listener, noteHandler);
        }
        getContentPane().add(mainScreen);
        repaint();
        printAll(getGraphics());
    }
    
    public void gotoBrowse() {
        getContentPane().removeAll();
        if (browseScreen == null) {
            browseScreen = new JPanelBrowse(listener);
        }
        getContentPane().add(browseScreen);
        repaint();
        printAll(getGraphics());
    }
    
    public void gotoAccount() {
        getContentPane().removeAll();
        if (accountScreen == null) {
            accountScreen = new JPanelAccount(listener);
        }
        getContentPane().add(accountScreen);
        repaint();
        printAll(getGraphics());
    }
}
