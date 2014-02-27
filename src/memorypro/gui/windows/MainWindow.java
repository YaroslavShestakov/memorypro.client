/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorypro.gui.windows;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;
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
                if (ev.getValueIsAdjusting()) {
                  last = (Note) note_list.getSelectedValue();
                  MainWindow.this.displayNote(last);
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
                    } else 
                        return ;
                }
            });
    }
    
    /**
     * Update the list of notes, and show only selected items.
     */
    public void rebuildList(){
        model.clear();
        ArrayList<Note> notes = app.notehandler.getNotes();
        boolean displayEnabled  = this.filter_enabled_cb.isSelected();
        boolean displayDisabled = this.filter_disabled_cb.isSelected();
        boolean displayActive   = this.filter_active_cb.isSelected();
        boolean displayExpired  = this.filter_expired_cb.isSelected();
        
        int index = 0 ;
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
        
        resetNoteData();
        last = (Note) note_list.getSelectedValue();
        displayNote(last);

    }

    /**
     * Updates the shown list with items containing the searched string.
     * @param searchStr String searched from notes headers.
     */
    public void searchList(String searchStr){
        model.clear();
        ArrayList<Note> notes = app.notehandler.getNotesByHeader(searchStr);
        boolean displayEnabled  = this.filter_enabled_cb.isSelected();
        boolean displayDisabled = this.filter_disabled_cb.isSelected();
        boolean displayActive   = this.filter_active_cb.isSelected();
        boolean displayExpired  = this.filter_expired_cb.isSelected();
        
        int index = 0 ;
        if (notes != null)
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
        jPanel3 = new javax.swing.JPanel();
        filter_enabled_cb = new javax.swing.JCheckBox();
        filter_disabled_cb = new javax.swing.JCheckBox();
        filter_expired_cb = new javax.swing.JCheckBox();
        filter_active_cb = new javax.swing.JCheckBox();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        note_panel = new javax.swing.JPanel();
        note_scroll = new javax.swing.JScrollPane();
        note_list = new javax.swing.JList();
        title_panel = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        description_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        description = new javax.swing.JTextArea();
        date_panel = new javax.swing.JPanel();
        alertdate = new javax.swing.JLabel();
        properties_panel = new javax.swing.JPanel();
        editNote_btn = new javax.swing.JButton();
        sort_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dateAsc_radio = new javax.swing.JRadioButton();
        dateDesc_radio = new javax.swing.JRadioButton();
        newNote_btn = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        main_menu = new javax.swing.JMenu();
        account_menu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        logout_item = new javax.swing.JMenuItem();
        exit_item = new javax.swing.JMenuItem();
        tools_item = new javax.swing.JMenu();
        help_menu = new javax.swing.JMenu();
        helpContents_item = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        about_item = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filter_enabled_cb)
                    .addComponent(filter_disabled_cb)
                    .addComponent(filter_expired_cb)
                    .addComponent(filter_active_cb)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filter_enabled_cb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filter_disabled_cb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filter_active_cb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filter_expired_cb)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setText("Notes");

        note_panel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        note_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        note_scroll.setViewportView(note_list);

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

        javax.swing.GroupLayout properties_panelLayout = new javax.swing.GroupLayout(properties_panel);
        properties_panel.setLayout(properties_panelLayout);
        properties_panelLayout.setHorizontalGroup(
            properties_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        properties_panelLayout.setVerticalGroup(
            properties_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 34, Short.MAX_VALUE)
        );

        editNote_btn.setText("Edit...");
        editNote_btn.setFocusable(false);
        editNote_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNote_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout note_panelLayout = new javax.swing.GroupLayout(note_panel);
        note_panel.setLayout(note_panelLayout);
        note_panelLayout.setHorizontalGroup(
            note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(note_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(description_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(properties_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(note_panelLayout.createSequentialGroup()
                        .addGroup(note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(note_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(note_panelLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addComponent(editNote_btn)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        note_panelLayout.setVerticalGroup(
            note_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(note_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(note_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(description_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(properties_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editNote_btn))
        );

        sort_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sort"));

        jLabel2.setText("Date");

        sort_date_bgroup.add(dateAsc_radio);
        dateAsc_radio.setText("Ascending");
        dateAsc_radio.setFocusable(false);
        dateAsc_radio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateAsc_radioActionPerformed(evt);
            }
        });

        sort_date_bgroup.add(dateDesc_radio);
        dateDesc_radio.setText("Descending");
        dateDesc_radio.setFocusable(false);

        javax.swing.GroupLayout sort_panelLayout = new javax.swing.GroupLayout(sort_panel);
        sort_panel.setLayout(sort_panelLayout);
        sort_panelLayout.setHorizontalGroup(
            sort_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sort_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sort_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateDesc_radio)
                    .addComponent(dateAsc_radio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sort_panelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        sort_panelLayout.setVerticalGroup(
            sort_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sort_panelLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateAsc_radio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dateDesc_radio))
        );

        newNote_btn.setText("New Note");
        newNote_btn.setFocusable(false);
        newNote_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newNote_btnActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        main_menu.setText("MemoryPro");

        account_menu.setText("Account");

        jMenuItem1.setText("View");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        account_menu.add(jMenuItem1);

        main_menu.add(account_menu);
        main_menu.add(jSeparator2);

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

        tools_item.setText("Tools");
        jMenuBar1.add(tools_item);

        help_menu.setText("Help");

        helpContents_item.setText("Help Contents");
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
                .addGap(284, 284, 284)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sort_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(note_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newNote_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchField)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(newNote_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sort_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(btnSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(note_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
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

    /**
     * Logs the user out of the program.
     * @param evt ActionEvent from the menu item.
     */
    private void logout_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_itemActionPerformed
        app.logout();
        app.gui.closeWindow(this);
        app.gui.openWindow(Window.LOGIN);
    }//GEN-LAST:event_logout_itemActionPerformed

    /**
     * Exits the program.
     * @param evt ActionEvent from the menu item.
     */
    private void exit_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_itemActionPerformed
        app.exit();
    }//GEN-LAST:event_exit_itemActionPerformed

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

    private void dateAsc_radioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateAsc_radioActionPerformed
       
    }//GEN-LAST:event_dateAsc_radioActionPerformed

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

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        app.gui.openWindow(Window.ACCOUNT);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Applies a searchstring to the notelist, and refreshes the list
     * to display only notes containgin the searched string.
     * @param evt ActionEvent from the button.
     */
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchList(searchField.getText());
    }//GEN-LAST:event_btnSearchActionPerformed

    /**
     * Clears the search parameter and shows all notes in the list.
     * @param evt 
     */
    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        rebuildList();
    }//GEN-LAST:event_btnClearActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about_item;
    private javax.swing.JMenu account_menu;
    private javax.swing.JLabel alertdate;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSearch;
    private javax.swing.JRadioButton dateAsc_radio;
    private javax.swing.JRadioButton dateDesc_radio;
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
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenuItem logout_item;
    private javax.swing.JMenu main_menu;
    private javax.swing.JButton newNote_btn;
    private javax.swing.JList note_list;
    private javax.swing.JPanel note_panel;
    private javax.swing.JScrollPane note_scroll;
    private javax.swing.JPanel properties_panel;
    private javax.swing.JTextField searchField;
    private javax.swing.ButtonGroup sort_date_bgroup;
    private javax.swing.JPanel sort_panel;
    private javax.swing.JLabel title;
    private javax.swing.JPanel title_panel;
    private javax.swing.JMenu tools_item;
    // End of variables declaration//GEN-END:variables
}
