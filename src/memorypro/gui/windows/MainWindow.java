/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorypro.gui.windows;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import memorypro.MemoryPro;
import memorypro.notes.Note;

/**
 *
 * @author Ярослав
 */
public class MainWindow extends Window {
    private DefaultListModel model = new DefaultListModel();
    private Note last ;

    /**
     * Creates new form Main
     */
    
    public MainWindow(MemoryPro app) {
        super(app);
        app.notehandler.addMainWindow(this);
        initComponents();
        //setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
             
        note_list.setModel(this.model);
        rebuildList();
        note_list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent ev) {
                if (true) { //ev.getValueIsAdjusting()
                  last = (Note) note_list.getSelectedValue();
                  if (last != null){
                      MainWindow.this.displayNote(last);
                  }
                }
            }
        });
        
        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int i;
                    i = JOptionPane.showConfirmDialog(
                            MainWindow.this,
                            "Are you sure you want to close MemoryPro?\nYou will not be able to receive notifications",
                            "Exit?",
                            JOptionPane.YES_NO_OPTION);
                    
                    if (i == 0) {
                        MainWindow.this.app.gui.closeWindow(MainWindow.this);
                        MainWindow.this.app.exit();
                    } else 
                        return ;
                }
            });
        
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                rebuildList();
            }
            public void removeUpdate(DocumentEvent e) {
                rebuildList();
            }
            public void insertUpdate(DocumentEvent e) {
                rebuildList();
            }
        });
    }
        
    public void selectLastNote(){
        note_list.setSelectedIndex(note_list.getModel().getSize()- 1);
        displayNote((Note) note_list.getSelectedValue());
    }
    
    public void selectFirstNote(){
        note_list.setSelectedIndex(0);
        displayNote((Note) note_list.getSelectedValue());
    }
    
    /**
     * Update the list of notes, and show only selected items.
     */
    public void rebuildList(){
        model.clear();
        String search = searchField.getText();
        ArrayList<Note> notes ;

        if (search != "")
            notes = app.notehandler.getNotesByHeader(search);
        else
            notes = app.notehandler.getNotes();
        
        boolean displayEnabled  = this.filter_enabled_cb.isSelected();
        boolean displayDisabled = this.filter_disabled_cb.isSelected();
        boolean displayActive   = this.filter_active_cb.isSelected();
        boolean displayExpired  = this.filter_expired_cb.isSelected();
        
        int index = 0 ;
        if (notes != null){
            for (Note note : notes){
                if (
                    (
                    (displayEnabled && note.isEnabled()) 
                        || 
                    (displayDisabled && !note.isEnabled())
                    ) && (    
                    (displayActive && note.isActive())
                        ||
                    (displayExpired && !note.isActive())
                    )
                ){

                    model.add(index, note);
                    index++ ;
                }
            }
        }
        
        resetNoteData();
        last = (Note) note_list.getSelectedValue();
        displayNote(last);

    }
    
    /**
     * Displays the info of the selected note.
     * @param note Note selected from the list.
     */
    public void displayNote(Note note){
        if (note != null){
            title.setText(note.getTitle());
            description.setText(note.getDescription());
            alertdate.setText(note.getAlertDate().toString());
            properties.setText((note.isEnabled() ? "Alert is enabled" : "Alert is disabled"));
        } else 
            resetNoteData();
    }
    
    /**
     * Empties the displayed info of note.
     */
    public void resetNoteData(){
        title.setText("");
        description.setText("");
        alertdate.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jComboBox1 = new javax.swing.JComboBox();
        sort_date_bgroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        note_panel = new javax.swing.JPanel();
        title_panel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        description_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        date_panel = new javax.swing.JPanel();
        alertdate = new javax.swing.JLabel();
        properties_panel = new javax.swing.JPanel();
        properties = new javax.swing.JLabel();
        editNote_btn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        newNote_btn = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        filter_enabled_cb = new javax.swing.JCheckBox();
        filter_disabled_cb = new javax.swing.JCheckBox();
        filter_expired_cb = new javax.swing.JCheckBox();
        filter_active_cb = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        note_scroll = new javax.swing.JScrollPane();
        note_list = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        main_menu = new javax.swing.JMenu();
        logout_item = new javax.swing.JMenuItem();
        exit_item = new javax.swing.JMenuItem();
        help_menu = new javax.swing.JMenu();
        helpContents_item = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        about_item = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Notes");

        note_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        title_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Title"));

        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout title_panelLayout = new javax.swing.GroupLayout(title_panel);
        title_panel.setLayout(title_panelLayout);
        title_panelLayout.setHorizontalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        title_panelLayout.setVerticalGroup(
            title_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        description_panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 0, 51)));
        description_panel.setToolTipText("");
        description_panel.setName("Description"); // NOI18N

        description.setBackground(new java.awt.Color(240, 240, 240));
        description.setColumns(15);
        description.setEditable(false);
        description.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        description.setLineWrap(true);
        description.setRows(4);
        description.setWrapStyleWord(true);
        description.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        description.setFocusable(false);
        jScrollPane1.setViewportView(description);

        javax.swing.GroupLayout description_panelLayout = new javax.swing.GroupLayout(description_panel);
        description_panel.setLayout(description_panelLayout);
        description_panelLayout.setHorizontalGroup(
            description_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        description_panelLayout.setVerticalGroup(
            description_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
        );

        date_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Alert date"));

        alertdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout date_panelLayout = new javax.swing.GroupLayout(date_panel);
        date_panel.setLayout(date_panelLayout);
        date_panelLayout.setHorizontalGroup(
            date_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(alertdate, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        date_panelLayout.setVerticalGroup(
            date_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(alertdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        properties_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Properties"));

        properties.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout properties_panelLayout = new javax.swing.GroupLayout(properties_panel);
        properties_panel.setLayout(properties_panelLayout);
        properties_panelLayout.setHorizontalGroup(
            properties_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(properties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        properties_panelLayout.setVerticalGroup(
            properties_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(properties, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        editNote_btn.setText("Edit");
        editNote_btn.setFocusable(false);
        editNote_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNote_btnActionPerformed(evt);
            }
        });

        jButton1.setText("Delete");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        newNote_btn.setText("New Note");
        newNote_btn.setFocusable(false);
        newNote_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newNote_btnActionPerformed(evt);
            }
        });

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.setFocusable(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));

        filter_enabled_cb.setSelected(true);
        filter_enabled_cb.setText("Enabled");
        filter_enabled_cb.setFocusable(false);
        filter_enabled_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_enabled_cbActionPerformed(evt);
            }
        });

        filter_disabled_cb.setSelected(true);
        filter_disabled_cb.setText("Disabled");
        filter_disabled_cb.setFocusable(false);
        filter_disabled_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_disabled_cbActionPerformed(evt);
            }
        });

        filter_expired_cb.setSelected(true);
        filter_expired_cb.setText("Expired");
        filter_expired_cb.setFocusable(false);
        filter_expired_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_expired_cbActionPerformed(evt);
            }
        });

        filter_active_cb.setSelected(true);
        filter_active_cb.setText("Active");
        filter_active_cb.setFocusable(false);
        filter_active_cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filter_active_cbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filter_enabled_cb)
                    .addComponent(filter_disabled_cb))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filter_active_cb)
                    .addComponent(filter_expired_cb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filter_enabled_cb)
                    .addComponent(filter_active_cb))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filter_disabled_cb)
                    .addComponent(filter_expired_cb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Search");

        javax.swing.GroupLayout note_panelLayout = new javax.swing.GroupLayout(note_panel);
        note_panel.setLayout(note_panelLayout);
        note_panelLayout.setHorizontalGroup(
            note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(note_panelLayout.createSequentialGroup()
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, note_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(description_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(properties_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, note_panelLayout.createSequentialGroup()
                                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(editNote_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                                    .addComponent(newNote_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(note_panelLayout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(searchField)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        note_panelLayout.setVerticalGroup(
            note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(note_panelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(newNote_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editNote_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(properties_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        note_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        note_scroll.setViewportView(note_list);

        main_menu.setText("MemoryPro");

        logout_item.setText("Logout");
        logout_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_itemActionPerformed(evt);
            }
        });
        main_menu.add(logout_item);

        exit_item.setText("Exit");
        exit_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_itemActionPerformed(evt);
            }
        });
        main_menu.add(exit_item);

        jMenuBar1.add(main_menu);

        help_menu.setText("Help");

        helpContents_item.setText("Help Contents");
        helpContents_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpContents_itemActionPerformed(evt);
            }
        });
        help_menu.add(helpContents_item);
        help_menu.add(jSeparator1);

        about_item.setText("About");
        about_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                about_itemActionPerformed(evt);
            }
        });
        help_menu.add(about_item);

        jMenuBar1.add(help_menu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(note_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(note_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(note_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(note_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void about_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_about_itemActionPerformed
        String help = "Authors: \nLaukkanen Mika\nLiikkanen Jani\n"
                + "Mattila Klaus\nShestakov Yaroslav\n\n"
                + "MemoryPro is a free program developed as a school project.\n"
                + "The program is used for saving and editing notes that help\n"
                + "you remember important events.";
        JOptionPane.showMessageDialog(null, help, "MemoryPro",
                JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_about_itemActionPerformed

    private void filter_enabled_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_enabled_cbActionPerformed
        rebuildList();
    }//GEN-LAST:event_filter_enabled_cbActionPerformed

    /**
     * Opens a window where you can edit the selected note.
     * @param evt ActionEvent from the button.
     */
    private void editNote_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNote_btnActionPerformed
        Note tmp = null;
        tmp = (Note) note_list.getSelectedValue();
        if (tmp != null) {
            app.gui.openWindow(Window.NEW_NOTE);
            app.notehandler.editNote(tmp);
        }
    }//GEN-LAST:event_editNote_btnActionPerformed

    /**
     * Opens a window for adding new note.
     * @param evt ActionEvent from the button.
     */
    private void newNote_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newNote_btnActionPerformed
        app.gui.openWindow(Window.NEW_NOTE);
    }//GEN-LAST:event_newNote_btnActionPerformed

    private void filter_disabled_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_disabled_cbActionPerformed
       rebuildList();
    }//GEN-LAST:event_filter_disabled_cbActionPerformed

    private void filter_active_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_active_cbActionPerformed
        rebuildList();
    }//GEN-LAST:event_filter_active_cbActionPerformed

    private void filter_expired_cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filter_expired_cbActionPerformed
        rebuildList();
    }//GEN-LAST:event_filter_expired_cbActionPerformed

    /**
     * Clears the search parameter and shows all notes in the list.
     * @param evt 
     */
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        searchField.setText("");
        rebuildList();
    }//GEN-LAST:event_btnClearActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Note selected = (Note) note_list.getSelectedValue();
        if (selected != null){
            int i = JOptionPane.showConfirmDialog(this, "Delete this note?", "Delete note", JOptionPane.OK_CANCEL_OPTION);
            if (i == 0){
                if (app.deleteNote(selected)){
                    app.loadNotes();
                    rebuildList();
                    selectFirstNote();
                } else {
                    JOptionPane.showMessageDialog(this, "Could not delete note");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void helpContents_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpContents_itemActionPerformed
        String help = "Create new notes with the New Note button, or edit existing\n"
                + "ones with the Edit button. Delete button let's you delete\n"
                + "old notes.\n\n"
                + "You can search notes by inputing a search string and clicking\n"
                + "Search. Clear the search wth Clear button.\n\n"
                + "You choose to view expired or active notes by selecting\n"
                + "different filters."
                + "";
        JOptionPane.showMessageDialog(null, help, "MemoryPro",
                JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_helpContents_itemActionPerformed

    /**
     * Exits the program.
     * @param evt ActionEvent from the menu item.
     */
    private void exit_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_itemActionPerformed
        app.exit();
    }//GEN-LAST:event_exit_itemActionPerformed

    /**
     * Logs the user out of the program.
     * @param evt ActionEvent from the menu item.
     */
    private void logout_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_itemActionPerformed
        app.logout();
        app.gui.openWindow(Window.LOGIN);
        app.gui.closeWindow(this);
    }//GEN-LAST:event_logout_itemActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_item;
    private javax.swing.JLabel alertdate;
    private javax.swing.JButton btnClear;
    private javax.swing.JPanel date_panel;
    private javax.swing.JTextArea description;
    private javax.swing.JPanel description_panel;
    private javax.swing.JButton editNote_btn;
    private javax.swing.JMenuItem exit_item;
    private javax.swing.JCheckBox filter_active_cb;
    private javax.swing.JCheckBox filter_disabled_cb;
    private javax.swing.JCheckBox filter_enabled_cb;
    private javax.swing.JCheckBox filter_expired_cb;
    private javax.swing.JMenuItem helpContents_item;
    private javax.swing.JMenu help_menu;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenuItem logout_item;
    private javax.swing.JMenu main_menu;
    private javax.swing.JButton newNote_btn;
    private javax.swing.JList note_list;
    private javax.swing.JPanel note_panel;
    private javax.swing.JScrollPane note_scroll;
    private javax.swing.JLabel properties;
    private javax.swing.JPanel properties_panel;
    private javax.swing.JTextField searchField;
    private javax.swing.ButtonGroup sort_date_bgroup;
    private javax.swing.JLabel status;
    private javax.swing.JLabel title;
    private javax.swing.JPanel title_panel;
    // End of variables declaration//GEN-END:variables
}
