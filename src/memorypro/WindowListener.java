package memorypro;

import java.awt.Component;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import memorypro.windows.NewNoteWindow;

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
        frame.changeView("main");
    }
    
    public void register() {
        System.out.println("Register not yet implemented");
    }
    
    public void gotoMain() {
        frame.changeView("main");
    }
    
    public void gotoBrowse() {
        frame.changeView("browse");
    }
    
    public void gotoAccount() {
        frame.changeView("account");
    }
    
    public void gotoSettings() {
        System.out.println("Settings not yet implemented");
    }
    
    public void showHelp(String location) {
        switch(location) {
            case "login":
                System.out.println("Help not yet implemented");
                JOptionPane.showMessageDialog(frame,
                        "You can register or log in here.",
                        "Help", JOptionPane.PLAIN_MESSAGE);
                break;
            default:
                System.out.println("Help at " + location + " not yet implemented");
        }
    }
    
    public void gotoAdmin() {
        System.out.println("Admin not yer implemented");
    }
    
    public void removeNote() {
        System.out.println("Remove note not implemented");
    }
    
    public void addNote() {
        Frame[] frames = frame.getFrames();
        for (int i = 0; i < frames.length; i++){
            if (frames[i].getName().equals("newNote")) {
                return;
            }
        }
        NewNoteWindow noteWin = new NewNoteWindow(this);
        JFrame noteFrame = new JFrame();
        noteFrame.setName("newNote");
        noteFrame.setSize(800,600);
        noteFrame.getContentPane().add(noteWin);
        noteFrame.setVisible(true);
//        noteFrame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                Component myComp = (Component) e.getSource();
//                JFrame frame = (JFrame) SwingUtilities.getRoot(myComp);
//            }
//        });
            
        
    }
    
    public void saveNote() {
        System.out.println("Note saving not yet implemented");
    }
    
    public void editNote() {
        System.out.println("Edit not yet implemented");
    }
    
    public void searchNote() {
        System.out.println("Search not yet implemented");
    }
}
