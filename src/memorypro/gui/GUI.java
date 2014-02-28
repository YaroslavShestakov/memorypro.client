package memorypro.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import memorypro.gui.windows.* ;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import memorypro.utils.Display;
import memorypro.MemoryPro;

/**
 *
 * @author c2yshest
 */
public class GUI {
    private HashMap<Integer, Window> windows = new HashMap();
    protected MemoryPro app ;
    
    public GUI(MemoryPro app){
        this.app = app ;

    }
    
    public Window getWindow(Integer type){
        if (windows.containsKey(type)){
            return windows.get(type);
        }
        return null ;
    }
    
    public Window openWindow(Integer type){
        if (windows.containsKey(type)){
            final Window existing = windows.get(type);
            System.out.println("Opening existing");
            //Move the window to front
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    existing.toFront();
                    existing.repaint();
                    existing.setVisible(true);
                }
            });
            
            return existing ;
        } else {
            Window window = null ;
            
            if (type == Window.LOGIN){
                window = new LoginWindow(app);
                window.setResizable(false);
                window.setTitle("MemoryPro [login]");
                window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else if (type == Window.REGISTER){
                window = new RegistrationWindow(app);
                window.setResizable(false);
                window.setTitle("MemoryPro [registration]");
                window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else if (type == Window.MAIN){
                window = new MainWindow(app);
                window.setTitle("Welcome to MemoryPro");
                window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                
            } else if (type == Window.NEW_NOTE){
                window = new NewNoteWindow(app);
                window.setTitle("MemoryPro [new note]");
                window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            } else if (type == Window.ACCOUNT){
                window = new AccountWindow(app);
                window.setTitle("MemoryPro");
                window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
            
            if (window != null)
                this.setupNewWindow(window, type);
                
            return window ;
        }
    }
    
    public void closeWindow(Integer type){ System.out.println("Close");
        if (windows.containsKey(type)){
            Window window = windows.get(type);
                 
            System.out.println("Window found");
           
            //window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            window.setVisible(false);
            window.dispose();
            
            windows.remove(type);
            
            
            if (windows.size() == 0){
                System.out.println("All windows closed, terminating.");
                app.exit();
            }
        }
    }
    
    public void closeWindow(Window window){
        this.closeWindow(window.type);
    }
    
    private void setupNewWindow(Window window, Integer type){
        window.setApp(this.app);
        window.setType(type);

        window.pack(); //AUTORESIZING
        window.setLocation(
            Display.width/2-window.getWidth() / 2, 
            Display.height/2-window.getHeight() /2
         );
        window.repaint();
        window.printAll(window.getGraphics());  
        window.setVisible(true);                       
        
       windows.put(type, window);
    }
}