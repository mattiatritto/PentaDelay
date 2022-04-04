package pentadelay;

/*
 * Questa classe definisce un JFrame di tipo JFChoise aperto
 * dopo il JFrame di presentazione che permette all'operatore
 * di scegliere cosa fare.
 */



import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author Mattia Tritto
 */

public class JFChoise extends javax.swing.JFrame {

    /**
     * Crea un nuovo form JFScelta.
     */
    
    public JFChoise() {
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width / 2) - (this.getWidth() / 2), (screenSize.height / 2) - (this.getHeight() / 2));
        
        ImageIcon img = new ImageIcon("..//..//..//img//iconaPentasuglia.jpg");
        this.setIconImage(img.getImage());
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel = new javax.swing.JPanel();
        lblPentadelay = new javax.swing.JLabel();
        lblLogoPentasuglia = new javax.swing.JLabel();
        registryPanel = new javax.swing.JPanel();
        btnFindStudents = new javax.swing.JButton();
        btnAddStudent = new javax.swing.JButton();
        btnRegisterDelays = new javax.swing.JButton();
        btnRemoveStudents = new javax.swing.JButton();
        lblRemoveStudents = new javax.swing.JLabel();
        lblFindStudents = new javax.swing.JLabel();
        lblAddStudents = new javax.swing.JLabel();
        lblRegisterDelays = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menù di scelta");
        setResizable(false);

        buttonsPanel.setBackground(new java.awt.Color(164, 55, 58));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(534, 160));

        lblPentadelay.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblPentadelay.setForeground(new java.awt.Color(255, 255, 255));
        lblPentadelay.setText("PentaDelay");

        lblLogoPentasuglia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/logoPentasugliaMatera.jpg"))); // NOI18N

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblLogoPentasuglia)
                .addGap(18, 18, 18)
                .addComponent(lblPentadelay)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPentadelay, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblLogoPentasuglia)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnFindStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/lente.png"))); // NOI18N
        btnFindStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindStudentsActionPerformed(evt);
            }
        });

        btnAddStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/add.png"))); // NOI18N
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        btnRegisterDelays.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/questoPC.png"))); // NOI18N
        btnRegisterDelays.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterDelaysActionPerformed(evt);
            }
        });

        btnRemoveStudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/remove.png"))); // NOI18N
        btnRemoveStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveStudentsActionPerformed(evt);
            }
        });

        lblRemoveStudents.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblRemoveStudents.setText("Rimuovi alunno");

        lblFindStudents.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblFindStudents.setText("Ricerca alunno");

        lblAddStudents.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAddStudents.setText("Aggiungi alunno");

        lblRegisterDelays.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblRegisterDelays.setText("Registra ritardi");

        javax.swing.GroupLayout registryPanelLayout = new javax.swing.GroupLayout(registryPanel);
        registryPanel.setLayout(registryPanelLayout);
        registryPanelLayout.setHorizontalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnRegisterDelays, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(lblRegisterDelays)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                            .addComponent(btnFindStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(61, 61, 61))
                        .addGroup(registryPanelLayout.createSequentialGroup()
                            .addComponent(btnRemoveStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                        .addComponent(lblFindStudents)
                        .addGap(115, 115, 115))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(lblAddStudents)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRemoveStudents)
                .addGap(112, 112, 112))
        );
        registryPanelLayout.setVerticalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnFindStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegisterDelays, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFindStudents)
                    .addComponent(lblRegisterDelays))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveStudents, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddStudents)
                    .addComponent(lblRemoveStudents))
                .addGap(67, 67, 67))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(registryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
            .addComponent(registryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Apre un nuovo JFrame che registra i ritardatari.
     * 
     * @param evt 
     */
    
    private void btnRegisterDelaysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterDelaysActionPerformed
        JFDelaysManagement registraRitardi = new JFDelaysManagement();
        registraRitardi.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnRegisterDelaysActionPerformed

    /**
     * Apre un nuovo JFrame che cerca studenti nel database scolastico.
     * 
     * @param evt 
     */
    
    private void btnFindStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindStudentsActionPerformed
        JFStudentsStatus statusAlunni = new JFStudentsStatus();
        statusAlunni.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnFindStudentsActionPerformed

    /**
     * Apre un nuovo JFrame che rimuove studenti nel database scolastico.
     * 
     * @param evt 
     */
    
    private void btnRemoveStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveStudentsActionPerformed
        JFDeleteStudents deleteStudents = new JFDeleteStudents();
        deleteStudents.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnRemoveStudentsActionPerformed

    /**
     * Apre un nuovo JFrame che aggiunge studenti nel database scolastico.
     * 
     * @param evt 
     */
    
    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentActionPerformed
        JFAddStudents aggiungiAlunni = new JFAddStudents();
        aggiungiAlunni.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_btnAddStudentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnFindStudents;
    private javax.swing.JButton btnRegisterDelays;
    private javax.swing.JButton btnRemoveStudents;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JLabel lblAddStudents;
    private javax.swing.JLabel lblFindStudents;
    private javax.swing.JLabel lblLogoPentasuglia;
    private javax.swing.JLabel lblPentadelay;
    private javax.swing.JLabel lblRegisterDelays;
    private javax.swing.JLabel lblRemoveStudents;
    private javax.swing.JPanel registryPanel;
    // End of variables declaration//GEN-END:variables
}