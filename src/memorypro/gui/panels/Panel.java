/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorypro.gui.panels;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;
import memorypro.MemoryPro;

/**
 *
 * @author c2yshest
 */
public class Panel extends JPanel {
    public MemoryPro app ;
    
    public Panel(MemoryPro app){
       this.app = app ;
    }
    
    public void clearFields(){
         for (Component c : this.getComponents()){
            if (c instanceof JTextField){
                JTextField tf = (JTextField) c ;
                tf.setText("");
            }
        }
    }
}
