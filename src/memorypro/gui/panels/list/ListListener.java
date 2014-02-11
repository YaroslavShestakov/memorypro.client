/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package memorypro.gui.panels.list;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import memorypro.gui.panels.JPanelBrowse;

/**
 *
 * @author Jani
 */
public class ListListener implements ListSelectionListener {

    private JList list;
    private JPanel parent;
    
    public ListListener(JList list, JPanel parent) {
        this.parent = parent;
        this.list = list;
        if (list.getSelectedIndex() == -1) {
            ((JPanelBrowse) parent).setEditBtn(false);
        }
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (list.getSelectedIndex() == -1) {
            ((JPanelBrowse) parent).setEditBtn(false);
        }
        else {
            ((JPanelBrowse) parent).setEditBtn(true);
        }
    }
    
}
