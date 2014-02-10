package memorypro.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import memorypro.Display;
import memorypro.MemoryPro;
import memorypro.gui.panels.* ;

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
    
    public Window openWindow(Integer type){
        if (windows.containsKey(type)){
            final Window existing = windows.get(type);
            //Move the window to front
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    existing.toFront();
                    existing.repaint();    
                }
            });
            
            return existing ;
        } else {
            Window window = new Window();
           
            
            boolean found = false ;
            int width  = 800 ;
            int height = 600 ;
            
            if (type == Window.LOGIN){
                found = true ;
                width  = 800 ;
                height = 600 ;
                window.setVisible(true);
                         
                JPanel j = new JPanelLogin(app);
                window.getContentPane().add(j);
            }
            
            if (found){ 
                window.setType(type);
                
                window.setSize(width, height);          
                window.setLocation(
                        Display.width/2-width/2, 
                        Display.height/2-height/2
                );
                window.repaint();
                window.printAll(window.getGraphics());  
                                
                windows.put(type, window);
                
                return window ;
            }
        }
        return null ;
    }
    
    public void closeWindow(Integer type){
        if (windows.containsKey(type)){
            Window window = windows.get(type);
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
            window.setVisible(false);
            window.dispose();
            
            windows.remove(window);
        }
    }
    
    public void closeWindow(Window window){
            this.closeWindow(window.type);
    }
}