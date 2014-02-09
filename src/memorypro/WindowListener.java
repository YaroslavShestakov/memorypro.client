package memorypro;

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
        MemoryPro.frame.getContentPane().removeAll();
        JPanelMain newPanel = new JPanelMain();
        MemoryPro.frame.add(newPanel);
        MemoryPro.frame.repaint();
    }
    
    
}
