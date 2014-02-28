/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorypro.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author c2yshest
 */
public class Display {
    
    static {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width  = dim.width ;
        height = dim.height ;
    }
    public static final Integer width ;
    public static final Integer height ;
}
