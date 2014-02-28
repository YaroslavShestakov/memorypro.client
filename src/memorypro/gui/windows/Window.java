/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorypro.gui.windows;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import memorypro.MemoryPro;

/**
 *
 * @author c2yshest
 */
public class Window extends JFrame {
    public Integer type ;
    public MemoryPro app ;
    
    public Window(MemoryPro app){
        setApp(app);
        this.setDefaultCloseOperation(Window.DO_NOTHING_ON_CLOSE);
    }
    
    /**
     * Saves the main program for accessibility.
     * @param app The main program.
     */
    public void setApp(MemoryPro app){
        this.app = app ;
    }
    
    /**
     * Sets the type of a window.
     * @param type Integer number connected with window type.
     */
    public void setType(Integer type){
        this.type = type ;
    }
    
    public static final Integer
        LOGIN       = 0,
        MAIN        = 1,
        NOTES       = 3,
        NEW_NOTE    = 4,
        ACCOUNT     = 5,
        BROWSE      = 6,
        EDIT_NOTE   = 7,
        REGISTER    = 8
    ;
}
