package memorypro;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Ярослав, Jani Liikkanen
 */
public class MemoryPro extends JFrame {
//    public JFrame frame;
//    public WindowListener listener;
    JPanelLogin loginScreen;
    JPanelMain mainScreen;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MemoryPro frame = new MemoryPro();
    }
    
    public MemoryPro() {
    
        WindowListener listener = new WindowListener(this);
        loginScreen = new JPanelLogin(listener);
        
        getContentPane().add(loginScreen);
           
        setVisible(true);
        setSize(800, 600);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void changeView(String view) {
        if (view.equals("main")) {
            getContentPane().remove(loginScreen);
            if (mainScreen == null) {
                mainScreen = new JPanelMain();
                System.out.println("new main screen");
            }
            getContentPane().add(mainScreen);
            repaint();
            printAll(getGraphics());
        }
    }
}
