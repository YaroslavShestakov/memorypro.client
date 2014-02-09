package memorypro;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Ярослав, Jani Liikkanen
 */
public class MemoryPro extends JFrame {
    public static JFrame frameLogin;
    public static JFrame frameMain;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        frameLogin = new MemoryPro();
        frameMain = new MemoryPro();
        
        JPanelLogin loginScreen = new JPanelLogin();
        JPanelMain mainScreen = new JPanelMain();
        
        frameLogin.getContentPane().add(loginScreen);
        frameMain.getContentPane().add(mainScreen);
           
        frameLogin.setVisible(true);
        frameLogin.setSize(800, 600);
        frameMain.setSize(800,600);
       
        
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
