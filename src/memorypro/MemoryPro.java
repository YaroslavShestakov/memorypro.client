/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorypro;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Ярослав
 */
public class MemoryPro extends JFrame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        JFrame frame = new MemoryPro();
        
        System.out.println("Kay");
        
        NewJPanel j = new NewJPanel();
        //j.setSize(new Dimension(600, 800));
        
        frame.getContentPane().add(j);
           
        frame.setVisible(true);
        frame.setSize(800, 600);
       
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
