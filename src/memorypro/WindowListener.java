package memorypro;

import javax.swing.SwingUtilities;

/**
 *
 * @author Jani Liikkanen
 */
public class WindowListener {
    public static WindowListener listener;
    
    public WindowListener() {
    }
    
    public static void login() {
        System.out.println("WindowListener: login()");
            MemoryPro.frameLogin.setVisible(false);
            MemoryPro.frameMain.setVisible(true);
//            MemoryPro.frameLogin.getContentPane().removeAll();
//            JPanelMain newPanel = new JPanelMain();
//            MemoryPro.frameLogin.add(newPanel);
//            MemoryPro.frameLogin.repaint();
    }
    
}
