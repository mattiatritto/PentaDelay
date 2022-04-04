package pentadelay;

/*
 * Questa classe descrive un JFrame di tipo JFPresentation
 * mostrato per pochi secondi quando l'applicativo viene lanciato.
 */



import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author Mattia Tritto
 */

public class JFPresentation extends javax.swing.JFrame {

    /**
     * Crea un nuovo form di tipo JFPresentazione.
     */
    
    public JFPresentation() {
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));
        
        ImageIcon img = new ImageIcon("..//..//..//img//iconaPentasuglia.jpg");
        this.setIconImage(img.getImage());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPanel = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        btnIconified = new javax.swing.JButton();
        lblLogoPentasuglia = new javax.swing.JLabel();
        lblLogoRepubblicaItaliana = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        lblPentaDelay = new javax.swing.JLabel();
        lblProgressBar = new javax.swing.JLabel();
        bottomPanel = new javax.swing.JPanel();
        lblCaricamento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PentaDelay");
        setBackground(new java.awt.Color(166, 55, 58));
        setUndecorated(true);
        setSize(new java.awt.Dimension(500, 250));

        btnPanel.setBackground(new java.awt.Color(166, 55, 58));

        btnClose.setBackground(new java.awt.Color(166, 55, 58));
        btnClose.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
        btnClose.setForeground(new java.awt.Color(255, 255, 255));
        btnClose.setText("x");
        btnClose.setBorder(null);
        btnClose.setContentAreaFilled(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnIconified.setBackground(new java.awt.Color(166, 55, 58));
        btnIconified.setFont(new java.awt.Font("Berlin Sans FB", 0, 16)); // NOI18N
        btnIconified.setForeground(new java.awt.Color(255, 255, 255));
        btnIconified.setText("-");
        btnIconified.setBorder(null);
        btnIconified.setBorderPainted(false);
        btnIconified.setContentAreaFilled(false);
        btnIconified.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIconifiedActionPerformed(evt);
            }
        });

        lblLogoPentasuglia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/logoPentasugliaMatera.jpg"))); // NOI18N

        lblLogoRepubblicaItaliana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/logoRepubblicaItaliana.png"))); // NOI18N

        javax.swing.GroupLayout btnPanelLayout = new javax.swing.GroupLayout(btnPanel);
        btnPanel.setLayout(btnPanelLayout);
        btnPanelLayout.setHorizontalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblLogoRepubblicaItaliana)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLogoPentasuglia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIconified, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        btnPanelLayout.setVerticalGroup(
            btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblLogoPentasuglia)
                    .addComponent(lblLogoRepubblicaItaliana)
                    .addGroup(btnPanelLayout.createSequentialGroup()
                        .addGroup(btnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIconified, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        centerPanel.setBackground(new java.awt.Color(166, 55, 58));

        lblPentaDelay.setFont(new java.awt.Font("Arial", 0, 50)); // NOI18N
        lblPentaDelay.setForeground(new java.awt.Color(255, 255, 255));
        lblPentaDelay.setText("PentaDelay");

        lblProgressBar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pentadelay/img/progressBar.gif"))); // NOI18N
        lblProgressBar.setToolTipText("");

        javax.swing.GroupLayout centerPanelLayout = new javax.swing.GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, centerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPentaDelay)
                .addGap(114, 114, 114))
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        centerPanelLayout.setVerticalGroup(
            centerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(centerPanelLayout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addComponent(lblPentaDelay)
                .addGap(32, 32, 32)
                .addComponent(lblProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bottomPanel.setBackground(new java.awt.Color(166, 55, 58));

        lblCaricamento.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        lblCaricamento.setForeground(new java.awt.Color(255, 255, 255));
        lblCaricamento.setText("Avvio in corso...");

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCaricamento)
                .addContainerGap(411, Short.MAX_VALUE))
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addComponent(lblCaricamento)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(centerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(centerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Chiude l'applicativo.
     * 
     * @param evt 
     */
    
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed
    
    /**
     * Riduce ad icona l'applicativo.
     * 
     * @param evt 
     */
    
    private void btnIconifiedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIconifiedActionPerformed
        this.setExtendedState(this.ICONIFIED);
    }//GEN-LAST:event_btnIconifiedActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnIconified;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel lblCaricamento;
    private javax.swing.JLabel lblLogoPentasuglia;
    private javax.swing.JLabel lblLogoRepubblicaItaliana;
    private javax.swing.JLabel lblPentaDelay;
    private javax.swing.JLabel lblProgressBar;
    // End of variables declaration//GEN-END:variables
}