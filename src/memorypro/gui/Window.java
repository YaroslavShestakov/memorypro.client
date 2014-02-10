/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorypro.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author c2yshest
 */
public class Window extends JFrame {
    public Integer type ;
    
    public Window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public Window(Integer type){
        this();
        setType(type);
    }
    
    public void setType(Integer type){
        this.type = type ;
    }
    
    
    public static final Integer
        LOGIN       = 0,
        MAIN        = 1,
        NOTES       = 3,
        ADD_NOTE    = 4
    ;
}
