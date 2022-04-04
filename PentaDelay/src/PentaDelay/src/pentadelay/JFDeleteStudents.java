package pentadelay;

/*
 * Questa classe descrive un JFrame di tipo JFDeleteStudents
 * dove con il proprio codice fiscale oppure con nome, cognome, classe
 * e sezione è possibile eliminare un alunno dal database scolastico.
 */



import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Mattia Tritto
 */

public class JFDeleteStudents extends javax.swing.JFrame {

    /**
     * Crea un nuovo form JFDeleteStudents.
     */
    
    public JFDeleteStudents() {
        
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setLocation((screenSize.width / 2) - (this.getWidth() / 2), (screenSize.height / 2) - (this.getHeight() / 2));
        
        ImageIcon img = new ImageIcon("..//..//..//img//iconaPentasuglia.jpg");
        this.setIconImage(img.getImage());
        
        txtSurname.requestFocus();
    }
    
    
    
    /**
     * Metodo che cancella il contenuto di tutti i form
     * presenti nel JFrame.
     */
    
    private void eraseAllForms() {
        txtSurname.setText("");
        txtClass.setText("");
        txtSection.setText("");
        txtCF.setText("");
        txtSurname.requestFocus();
        txtName.setText("");
    }
    
    /**
     * Metodo che controlla l'integrità dei dati inseriti.
     * 
     * @throws WrongDataException
     * @throws EmptyFormsException 
     */
    
    private void checkStudent() throws WrongDataException, EmptyFormsException{
        
        if (txtName.getText().equals("") || txtSurname.getText().equals("") || txtClass.getText().equals("") || txtSection.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Riempire tutti i form richiesti.", "Alunno non inserito", JOptionPane.WARNING_MESSAGE);
            throw new EmptyFormsException();
        }
        
        int classroom = 0;
        try{
            classroom = Integer.parseInt(txtClass.getText());
        } catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Classe non valida. Riprovare.", "Classe non valida", JOptionPane.WARNING_MESSAGE);
            throw new WrongDataException();
        }
        
        if (classroom < 1 || classroom > 5){
            JOptionPane.showMessageDialog(null, "Classe non valida. Riprovare.", "Classe non valida", JOptionPane.WARNING_MESSAGE);
            throw new WrongDataException();
        }
    }
    
    /**
     * Elimina uno studente dal database scolastico dai suoi dati anagrafici.
     */
    
    private void deleteStudentFromDataRegistry(){
        
        try {
            checkStudent();
        } catch (PentaDelayException ex) {return;}
        
        String name = txtName.getText();
        String surname = txtSurname.getText();
        String classroom = txtClass.getText();
        String section = txtSection.getText();
        
        ConnessioneDB conDB = null;
        
        String query = "SELECT * "
                     + "FROM AlunniPenta "
                     + "WHERE NOME = '" + name + "'" + " AND "
                     + "COGNOME = '" + surname + "'" + " AND "
                     + "CL = '" + classroom + "'" + " AND "
                     + "SEZ = '" + section + "'";
        
        String ris[] = new String[10];

            try {
                conDB = new ConnessioneDB("..//..//..//database//db_pentadelay.mdb");

                ris = conDB.insertQueryDB(query);
                    
                if (ris[0] == null)
                    JOptionPane.showMessageDialog(null, "L'alunno inserito non esiste. Riprovare.", "Alunno non trovato", JOptionPane.ERROR_MESSAGE);
                else
                    if (JOptionPane.showConfirmDialog(null, "Rimuovere l'alunno " + ris[2] + " " + ris[3] + " nato il " + ris[4] + "\nresidente a " + ris[6] + "?") == 0){
                        
                        query = "DELETE FROM AlunniPenta WHERE COD_FISC = '" + ris[5] + "'";
                        
                        if (conDB.updateQueryDB(query) == 1)
                            JOptionPane.showMessageDialog(null, "Alunno eliminato correttamente.");
                        else
                            JOptionPane.showMessageDialog(null, "Ops! Qualcosa è andato storto! Riprovare.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
            } catch (ClassNotFoundException | SQLException ex) {}
    }
    
    private void deleteStudentFromCF(){
        
        String cf = txtCF.getText();
        
        if (cf.length() == 16){

            ConnessioneDB conDB = null;
            String query = "SELECT * "
                         + "FROM AlunniPenta "
                         + "WHERE COD_FISC = '" + cf + "'";
            
            String ris[] = new String[11];
                
            try {
                conDB = new ConnessioneDB("..//..//..//database//db_pentadelay.mdb");

                ris = conDB.insertQueryDB(query);
                
                if (ris[0] == null)
                    JOptionPane.showMessageDialog(null, "L'alunno con codice fiscale " + cf + " non esiste. Riprovare.", "Alunno non trovato", JOptionPane.ERROR_MESSAGE);
                else
                    if (JOptionPane.showConfirmDialog(null, "Rimuovere l'alunno " + ris[2] + " " + ris[3] + " nato il " + ris[4] + "\nresidente a " + ris[6] + "?") == 0){
                        
                        query = "DELETE FROM AlunniPenta WHERE COD_FISC = '" + txtCF.getText() + "'";
                        
                        if (conDB.updateQueryDB(query) == 1)
                            JOptionPane.showMessageDialog(null, "Alunno eliminato correttamente.", "Success!", JOptionPane.INFORMATION_MESSAGE);
                        else
                            JOptionPane.showMessageDialog(null, "Ops! Qualcosa è andato storto! Riprovare.", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
            }   catch (ClassNotFoundException | SQLException ex) {}
        }
        else
            JOptionPane.showMessageDialog(null, "Codice fiscale errato. Riprovare.", "Codice fiscale errato", JOptionPane.ERROR_MESSAGE);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registryPanel = new javax.swing.JPanel();
        lblDeleteStudent = new javax.swing.JLabel();
        lblCF = new javax.swing.JLabel();
        txtCF = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblSurname = new javax.swing.JLabel();
        txtSurname = new javax.swing.JTextField();
        lblClass = new javax.swing.JLabel();
        txtClass = new javax.swing.JTextField();
        lblSection = new javax.swing.JLabel();
        txtSection = new javax.swing.JTextField();
        btnDeleteFromDataRegistry = new javax.swing.JButton();
        btnDeleteFromCF = new javax.swing.JButton();
        buttonsPanel1 = new javax.swing.JPanel();
        lblPentadelay1 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        lblLogoPentasuglia = new javax.swing.JLabel();
        btnHomePage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rimuovi alunni");
        setResizable(false);

        registryPanel.setBackground(new java.awt.Color(241, 241, 241));
        registryPanel.setEnabled(false);
        registryPanel.setFocusable(false);

        lblDeleteStudent.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblDeleteStudent.setForeground(new java.awt.Color(68, 68, 68));
        lblDeleteStudent.setText("Rimuovi Alunno");

        lblCF.setForeground(new java.awt.Color(102, 102, 102));
        lblCF.setText("CODICE FISCALE");

        txtCF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCF.setForeground(new java.awt.Color(102, 102, 102));
        txtCF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCF.setFocusCycleRoot(true);
        txtCF.setName(""); // NOI18N

        lblName.setForeground(new java.awt.Color(102, 102, 102));
        lblName.setText("NOME");

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(102, 102, 102));
        txtName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtName.setName(""); // NOI18N
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        lblSurname.setForeground(new java.awt.Color(102, 102, 102));
        lblSurname.setText("COGNOME");

        txtSurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSurname.setForeground(new java.awt.Color(102, 102, 102));
        txtSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSurname.setName(""); // NOI18N
        txtSurname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSurnameKeyPressed(evt);
            }
        });

        lblClass.setForeground(new java.awt.Color(102, 102, 102));
        lblClass.setText("CLASSE");

        txtClass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtClass.setForeground(new java.awt.Color(102, 102, 102));
        txtClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtClass.setName(""); // NOI18N
        txtClass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClassKeyPressed(evt);
            }
        });

        lblSection.setForeground(new java.awt.Color(102, 102, 102));
        lblSection.setText("SEZIONE");

        txtSection.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSection.setForeground(new java.awt.Color(102, 102, 102));
        txtSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSection.setName(""); // NOI18N
        txtSection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSectionKeyPressed(evt);
            }
        });

        btnDeleteFromDataRegistry.setBackground(new java.awt.Color(164, 55, 58));
        btnDeleteFromDataRegistry.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDeleteFromDataRegistry.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteFromDataRegistry.setText("Elimina da dati anagrafici");
        btnDeleteFromDataRegistry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFromDataRegistryActionPerformed(evt);
            }
        });

        btnDeleteFromCF.setBackground(new java.awt.Color(164, 55, 58));
        btnDeleteFromCF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnDeleteFromCF.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteFromCF.setText("Elimina da codice fiscale");
        btnDeleteFromCF.setPreferredSize(new java.awt.Dimension(191, 25));
        btnDeleteFromCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteFromCFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout registryPanelLayout = new javax.swing.GroupLayout(registryPanel);
        registryPanel.setLayout(registryPanelLayout);
        registryPanelLayout.setHorizontalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteFromCF, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDeleteStudent)
                            .addGroup(registryPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCF)
                                    .addComponent(txtCF, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSurname, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                        .addComponent(lblSurname)
                        .addComponent(txtSection, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblClass, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSection, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeleteFromDataRegistry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
        );
        registryPanelLayout.setVerticalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblDeleteStudent)
                .addGap(51, 51, 51)
                .addComponent(lblSurname)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClass)
                    .addComponent(lblCF))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblSection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteFromDataRegistry, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteFromCF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        buttonsPanel1.setBackground(new java.awt.Color(164, 55, 58));
        buttonsPanel1.setPreferredSize(new java.awt.Dimension(534, 160));

        lblPentadelay1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblPentadelay1.setForeground(new java.awt.Color(255, 255, 255));
        lblPentadelay1.setText("PentaDelay");

        btnNew.setBackground(new java.awt.Color(164, 55, 58));
        btnNew.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnNew.setForeground(new java.awt.Color(255, 255, 255));
        btnNew.setText("Nuovo");
        btnNew.setBorder(null);
        btnNew.setBorderPainted(false);
        btnNew.setContentAreaFilled(false);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        lblLogoPentasuglia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/logoPentasugliaMatera.jpg"))); // NOI18N

        btnHomePage.setBackground(new java.awt.Color(164, 55, 58));
        btnHomePage.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnHomePage.setForeground(new java.awt.Color(255, 255, 255));
        btnHomePage.setText("Home Page");
        btnHomePage.setBorder(null);
        btnHomePage.setBorderPainted(false);
        btnHomePage.setContentAreaFilled(false);
        btnHomePage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomePageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanel1Layout = new javax.swing.GroupLayout(buttonsPanel1);
        buttonsPanel1.setLayout(buttonsPanel1Layout);
        buttonsPanel1Layout.setHorizontalGroup(
            buttonsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblLogoPentasuglia)
                .addGap(18, 18, 18)
                .addGroup(buttonsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNew)
                    .addComponent(btnHomePage)
                    .addComponent(lblPentadelay1))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        buttonsPanel1Layout.setVerticalGroup(
            buttonsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanel1Layout.createSequentialGroup()
                .addGroup(buttonsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonsPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblLogoPentasuglia))
                    .addGroup(buttonsPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblPentadelay1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(btnHomePage)
                .addGap(18, 18, 18)
                .addComponent(btnNew)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(buttonsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(registryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(registryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(buttonsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo che elimina uno studente dal database scolastico inserendo
     * nome, cognome, classe e sezione.
     * 
     * @param evt 
     */
    
    private void btnDeleteFromDataRegistryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFromDataRegistryActionPerformed
        deleteStudentFromDataRegistry();
    }//GEN-LAST:event_btnDeleteFromDataRegistryActionPerformed

    /**
     * Metodo che elimina uno studente dal database scolastico
     * a partire dal codice fiscale.
     * 
     * @param evt 
     */
    
    private void btnDeleteFromCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteFromCFActionPerformed
        deleteStudentFromCF();
    }//GEN-LAST:event_btnDeleteFromCFActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        eraseAllForms();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnHomePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomePageActionPerformed
        setVisible(false);
        JFChoise homePage = new JFChoise();
        homePage.setVisible(true);
    }//GEN-LAST:event_btnHomePageActionPerformed

    private void txtSurnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurnameKeyPressed
        if(evt.getKeyCode() == 10)
            txtName.requestFocus();
    }//GEN-LAST:event_txtSurnameKeyPressed

    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if(evt.getKeyCode() == 10)
            txtClass.requestFocus();
    }//GEN-LAST:event_txtNameKeyPressed

    private void txtClassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClassKeyPressed
        if(evt.getKeyCode() == 10)
            txtSection.requestFocus();
    }//GEN-LAST:event_txtClassKeyPressed

    private void txtSectionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSectionKeyPressed
        if(evt.getKeyCode() == 10)
            deleteStudentFromDataRegistry();
    }//GEN-LAST:event_txtSectionKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteFromCF;
    private javax.swing.JButton btnDeleteFromDataRegistry;
    private javax.swing.JButton btnHomePage;
    private javax.swing.JButton btnNew;
    private javax.swing.JPanel buttonsPanel1;
    private javax.swing.JLabel lblCF;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDeleteStudent;
    private javax.swing.JLabel lblLogoPentasuglia;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPentadelay1;
    private javax.swing.JLabel lblSection;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JPanel registryPanel;
    private javax.swing.JTextField txtCF;
    private javax.swing.JTextField txtClass;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSection;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables
}
