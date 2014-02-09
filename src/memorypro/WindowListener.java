package memorypro;

import javax.swing.SwingUtilities;

/**
 *
 * @author Jani Liikkanen
 */
public class WindowListener {
    public static WindowListener listener;
    MemoryPro frame;
    
    public WindowListener(MemoryPro mp) {
        frame = mp;
    }
    
    public void login() {
        System.out.println("WindowListener: login()");
//            MemoryPro.frameLogin.setVisible(false);
//            MemoryPro.frameMain.setVisible(true);
        
//            MemoryPro.frameLogin.getContentPane().removeAll();
//            JPanelMain newPanel = new JPanelMain();
//            MemoryPro.frameLogin.getContentPane().add(newPanel);
//            MemoryPro.frameLogin.repaint();
        
            frame.changeView("main");
        
//            MemoryPro.frameLogin.getContentPane().removeAll();
//            MemoryPro.frameLogin.add(newPanel);
//            MemoryPro.frameLogin.repaint();
    }
    
}
