package pentadelay;

/*
 * Questa classe descrive un JFrame di tipo JFDelaysManagement
 * dove un lettore di codici a barre leggerà il codice fiscale di
 * un alunno e stamperà i suoi dati nei form del frame (si può
 * anche inserire manualmente o il codice fiscale oppure nome, cognome
 * classe e sezione dell'alunno).
 */



import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Mattia Tritto
 */



public class JFDelaysManagement extends javax.swing.JFrame {

    /*Attributi*/
    
    private int delayHours;
    private String cf;
        

    
    /**
     * Crea un nuovo form JFGestioneRitardi
     */
    
    public JFDelaysManagement() 
    {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        ImageIcon img = new ImageIcon("..//..//..//img//iconaPentasuglia.jpg");
        this.setIconImage(img.getImage());
        
        setLocation((screenSize.width / 2) - (getWidth() / 2), (screenSize.height / 2) - (getHeight() / 2));
        txtCF.requestFocus();
    }

    
    
    /**
     * Metodo che cerca uno studente a partire dal suo nome, cognome
     * e classe e stampa il resto dei suoi dati nei form rimanenti.
     */
    
    private void searchStudentFromDataRegistry(){
        
        try {
            checkStudent();
        } catch(WrongDataException | EmptyFormsException ex){
            return;
        }
        
        String name = txtName.getText();
        String surname = txtSurname.getText();
        String classroom = txtClass.getText();
        String section = txtSection.getText();

        String query = "SELECT * "
                     + "FROM AlunniPenta "
                     + "WHERE NOME = '" + name + "'" + " AND "
                     + "COGNOME = '" + surname + "'" + " AND "
                     + "CL = '" + classroom + "'" + " AND "
                     + "SEZ = '" + section + "'";

        String ris[] = new String[12];

        try {
            ConnessioneDB conDB = new ConnessioneDB("..//..//..//database//db_pentadelay.mdb");

            ris = conDB.insertQueryDB(query);
            conDB.closeDB();

            if (ris[0] == null)
                JOptionPane.showMessageDialog(null, "L'alunno " + name + " " + surname + " non esiste. Riprovare.", "Alunno non trovato", JOptionPane.ERROR_MESSAGE);
            else{
                txtBirthday.setText(ris[4]);
                txtCity.setText(ris[6]);
                txtAddress.setText(ris[7]);
                txtGender.setText(ris[10]);
                txtDadTelephone.setText(ris[8]);
                txtMumTelephone.setText(ris[9]);
                this.cf = ris[5];
                
                delayHours = Integer.parseInt(ris[11]);
                delayHours += calculateDelays();

                if (delayHours > 5){
                    PlayWavSound alarm = new PlayWavSound("..\\..\\..\\wav\\sirena.wav");
                    alarm.start();
                    txtDelayNumber.setText("" + delayHours);
                    JOptionPane.showMessageDialog(null, "L'alunno ha superato i 5 ritardi!", "Numero ritardi superato", JOptionPane.WARNING_MESSAGE);
                    alarm.stop();
                }
                else{
                    printDelay(delayHours, ris[5]);
                    eraseAllForms();
                }
            }
        }   catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Errore connessione al DB.", "Errore", JOptionPane.ERROR_MESSAGE);}
    }
    
    /**
     * Metodo che calcola il numero ore di ritardo.
     * 
     * @return delayHours
     */
    
    private int calculateDelays(){
        
        /*LocalTime startSchool = LocalTime.of(8, 5), now = LocalTime.now();
        long delay = now.toSecondOfDay() - startSchool.toSecondOfDay();
        
        return (int) (delay/3600);*/
        
        return 1;
    }
    
    /**
     * Metodo che cerca uno studente nel database scolastico a partire
     * dal codice fiscale.
     */
    
    private void searchStudentFromCF(){
        
        String cf = txtCF.getText();
        
        if (cf.length() == 16){

            String query = "SELECT * "
                         + "FROM AlunniPenta "
                         + "WHERE COD_FISC = '" + cf + "'";
            
            String ris[] = new String[12];
                
            try {
                ConnessioneDB conDB = new ConnessioneDB("..//..//..//database//db_pentadelay.mdb");

                ris = conDB.insertQueryDB(query);
                conDB.closeDB();
                
                if (ris[0] == null)
                    JOptionPane.showMessageDialog(null, "L'alunno con codice fiscale " + cf + " non esiste. Riprovare.", "Alunno non trovato", JOptionPane.ERROR_MESSAGE);
                else{
                    this.cf = ris[5];
                    txtName.setText(ris[3]);
                    txtSurname.setText(ris[2]);
                    txtBirthday.setText(ris[4]);
                    txtCity.setText(ris[6]);
                    txtAddress.setText(ris[7]);
                    txtGender.setText(ris[10]);
                    txtClass.setText(ris[0]);
                    txtSection.setText(ris[1]);
                    txtDadTelephone.setText(ris[8]);
                    txtMumTelephone.setText(ris[9]);

                    delayHours = Integer.parseInt(ris[11]);
                    delayHours += calculateDelays();

                    if (delayHours > 5){
                        PlayWavSound alarm = new PlayWavSound("..\\..\\..\\wav\\sirena.wav");
                        alarm.start();
                        txtDelayNumber.setText("" + delayHours);
                        JOptionPane.showMessageDialog(null, "L'alunno ha superato i 5 ritardi!", "Numero ritardi superato", JOptionPane.WARNING_MESSAGE);
                        alarm.stop();
                    }
                    else{
                        printDelay(delayHours, this.cf);
                        eraseAllForms();
                    }
                }
            }   catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Errore connessione al DB.", "Errore", JOptionPane.ERROR_MESSAGE);}
        }
        else
            JOptionPane.showMessageDialog(null, "Codice fiscale errato. Riprovare.", "Codice fiscale errato", JOptionPane.ERROR_MESSAGE);
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
     * Stampa il foglietto dei ritardi.
     * 
     * @param delays
     */
    
    private void printDelay(int delays, String cf) {
        
        String query = "UPDATE AlunniPenta "
                     + "SET RITARDI = '" + delays + "' "
                     + "WHERE COD_FISC = '" + cf + "'";
        
        txtDelayNumber.setText("");
        txtDelayNumber.setText("" + delays);
        
        ConnessioneDB conDB = null;
        
        try {
            conDB = new ConnessioneDB("..//..//..//database//db_pentadelay.mdb");
            conDB.updateQueryDB(query);
            conDB.closeDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore connessione al DB.", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date today = Calendar.getInstance().getTime();
        
        String time = Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes();
        
        query = "INSERT INTO StoricoRitardatari VALUES('" + txtClass.getText() + "', '"
             + txtSection.getText().toUpperCase() + "', '"
             + txtSurname.getText().toUpperCase() + "', '"
             + txtName.getText().toUpperCase() + "', '"
             + cf.toUpperCase() + "', '"
             + df.format(today) + "', '"
             + time + "')";
        
        try {
            conDB = new ConnessioneDB("..//..//..//database//db_pentadelay.mdb");
            conDB.updateQueryDB(query);
            conDB.closeDB();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Errore connessione al DB.", "Errore", JOptionPane.ERROR_MESSAGE);
        }

        Report report = new Report(new Alunno(txtName.getText(), txtSurname.getText(), txtClass.getText(), txtSection.getText(), df.format(today), delays + "", time));

        report.generateXMLFiles();
        report.generateReport(); 
        
        try {
            Desktop desk = java.awt.Desktop.getDesktop();
            desk.print(new File("..//..//..//delay.odt"));
        }catch (NullPointerException | IllegalArgumentException | UnsupportedOperationException | IOException | SecurityException ex) {}
    }
    
    /**
     * Cancella il contenuto di tutti i form presenti nel frame.
     */
    
    private void eraseAllForms() {
        txtName.setText("");
        txtSurname.setText("");
        txtBirthday.setText("");
        txtCity.setText("");
        txtAddress.setText("");
        txtGender.setText("");
        txtClass.setText("");
        txtSection.setText("");
        txtDelayNumber.setText("");
        txtDadTelephone.setText("");
        txtMumTelephone.setText("");
        txtCF.requestFocus();        
        txtCF.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonsPanel = new javax.swing.JPanel();
        lblPentaDelay = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnPrintDelay = new javax.swing.JButton();
        lblLogoPentasuglia = new javax.swing.JLabel();
        btnHomePage = new javax.swing.JButton();
        registryPanel = new javax.swing.JPanel();
        lblGender = new javax.swing.JLabel();
        txtGender = new javax.swing.JTextField();
        lblDelayNumber = new javax.swing.JLabel();
        txtDelayNumber = new javax.swing.JTextField();
        txtCF = new javax.swing.JTextField();
        searchFromCF = new javax.swing.JButton();
        lblCF = new javax.swing.JLabel();
        lblSurname = new javax.swing.JLabel();
        txtSurname = new javax.swing.JTextField();
        lblDataRegistry = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblClass = new javax.swing.JLabel();
        txtSection = new javax.swing.JTextField();
        lblSection = new javax.swing.JLabel();
        txtClass = new javax.swing.JTextField();
        btnSearchFromRegistryData = new javax.swing.JButton();
        lblBirthday = new javax.swing.JLabel();
        txtBirthday = new javax.swing.JTextField();
        lblCity = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtDadTelephone = new javax.swing.JTextField();
        lblDadTelephone = new javax.swing.JLabel();
        lblMumTelephone = new javax.swing.JLabel();
        txtMumTelephone = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestione ritardi");
        setFocusable(false);
        setResizable(false);

        buttonsPanel.setBackground(new java.awt.Color(164, 55, 58));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(534, 160));

        lblPentaDelay.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblPentaDelay.setForeground(new java.awt.Color(255, 255, 255));
        lblPentaDelay.setText("PentaDelay");

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

        btnPrintDelay.setBackground(new java.awt.Color(255, 255, 255));
        btnPrintDelay.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnPrintDelay.setForeground(new java.awt.Color(153, 0, 0));
        btnPrintDelay.setText("Stampa");
        btnPrintDelay.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnPrintDelay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintDelayActionPerformed(evt);
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

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblLogoPentasuglia)
                .addGap(30, 30, 30)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNew)
                    .addComponent(btnHomePage)
                    .addComponent(lblPentaDelay)
                    .addComponent(btnPrintDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lblLogoPentasuglia))
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblPentaDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(btnHomePage)
                .addGap(18, 18, 18)
                .addComponent(btnNew)
                .addGap(18, 18, 18)
                .addComponent(btnPrintDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        registryPanel.setBackground(new java.awt.Color(241, 241, 241));
        registryPanel.setEnabled(false);
        registryPanel.setFocusable(false);

        lblGender.setForeground(new java.awt.Color(102, 102, 102));
        lblGender.setText("SESSO");

        txtGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGender.setForeground(new java.awt.Color(102, 102, 102));
        txtGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtGender.setFocusable(false);
        txtGender.setName(""); // NOI18N

        lblDelayNumber.setForeground(new java.awt.Color(102, 102, 102));
        lblDelayNumber.setText("NUMERO RITARDI");

        txtDelayNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDelayNumber.setForeground(new java.awt.Color(102, 102, 102));
        txtDelayNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDelayNumber.setFocusable(false);
        txtDelayNumber.setName(""); // NOI18N

        txtCF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCF.setForeground(new java.awt.Color(102, 102, 102));
        txtCF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCF.setName(""); // NOI18N
        txtCF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCFKeyTyped(evt);
            }
        });

        searchFromCF.setBackground(new java.awt.Color(164, 55, 58));
        searchFromCF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        searchFromCF.setForeground(new java.awt.Color(255, 255, 255));
        searchFromCF.setText("Stampa da codice fiscale");
        searchFromCF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFromCFActionPerformed(evt);
            }
        });

        lblCF.setForeground(new java.awt.Color(102, 102, 102));
        lblCF.setText("CODICE FISCALE");

        lblSurname.setForeground(new java.awt.Color(102, 102, 102));
        lblSurname.setText("COGNOME");

        txtSurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSurname.setForeground(new java.awt.Color(102, 102, 102));
        txtSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSurname.setFocusCycleRoot(true);
        txtSurname.setName(""); // NOI18N
        txtSurname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSurnameKeyPressed(evt);
            }
        });

        lblDataRegistry.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblDataRegistry.setForeground(new java.awt.Color(68, 68, 68));
        lblDataRegistry.setText("Dati anagrafici alunno");

        txtName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtName.setForeground(new java.awt.Color(102, 102, 102));
        txtName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtName.setName(""); // NOI18N
        txtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNameKeyPressed(evt);
            }
        });

        lblName.setForeground(new java.awt.Color(102, 102, 102));
        lblName.setText("NOME");

        lblClass.setForeground(new java.awt.Color(102, 102, 102));
        lblClass.setText("CLASSE");

        txtSection.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSection.setForeground(new java.awt.Color(102, 102, 102));
        txtSection.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSection.setName(""); // NOI18N
        txtSection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSectionKeyPressed(evt);
            }
        });

        lblSection.setForeground(new java.awt.Color(102, 102, 102));
        lblSection.setText("SEZIONE");

        txtClass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtClass.setForeground(new java.awt.Color(102, 102, 102));
        txtClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtClass.setFocusCycleRoot(true);
        txtClass.setName(""); // NOI18N
        txtClass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClassKeyPressed(evt);
            }
        });

        btnSearchFromRegistryData.setBackground(new java.awt.Color(164, 55, 58));
        btnSearchFromRegistryData.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSearchFromRegistryData.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchFromRegistryData.setText("Stampa da dati anagrafici");
        btnSearchFromRegistryData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchFromRegistryDataActionPerformed(evt);
            }
        });

        lblBirthday.setForeground(new java.awt.Color(102, 102, 102));
        lblBirthday.setText("DATA NASCITA");

        txtBirthday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBirthday.setForeground(new java.awt.Color(102, 102, 102));
        txtBirthday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBirthday.setFocusable(false);
        txtBirthday.setName(""); // NOI18N

        lblCity.setForeground(new java.awt.Color(102, 102, 102));
        lblCity.setText("COMUNE RESIDENZA");

        txtCity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCity.setForeground(new java.awt.Color(102, 102, 102));
        txtCity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCity.setFocusable(false);
        txtCity.setName(""); // NOI18N

        lblAddress.setForeground(new java.awt.Color(102, 102, 102));
        lblAddress.setText("INDIRIZZO RESIDENZA");

        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(102, 102, 102));
        txtAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAddress.setFocusable(false);
        txtAddress.setName(""); // NOI18N

        txtDadTelephone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDadTelephone.setForeground(new java.awt.Color(102, 102, 102));
        txtDadTelephone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDadTelephone.setFocusCycleRoot(true);
        txtDadTelephone.setFocusable(false);
        txtDadTelephone.setName(""); // NOI18N

        lblDadTelephone.setForeground(new java.awt.Color(102, 102, 102));
        lblDadTelephone.setText("TELEFONO PADRE");

        lblMumTelephone.setForeground(new java.awt.Color(102, 102, 102));
        lblMumTelephone.setText("TELEFONO MADRE");

        txtMumTelephone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMumTelephone.setForeground(new java.awt.Color(102, 102, 102));
        txtMumTelephone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMumTelephone.setFocusCycleRoot(true);
        txtMumTelephone.setFocusable(false);
        txtMumTelephone.setName(""); // NOI18N

        javax.swing.GroupLayout registryPanelLayout = new javax.swing.GroupLayout(registryPanel);
        registryPanel.setLayout(registryPanelLayout);
        registryPanelLayout.setHorizontalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtMumTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMumTelephone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblDataRegistry)
                    .addComponent(lblClass, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSection, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtClass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSection, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblGender, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCity, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDelayNumber, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGender, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCity, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSearchFromRegistryData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDelayNumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblAddress)
                                .addComponent(lblBirthday)
                                .addComponent(searchFromCF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBirthday)
                                .addComponent(txtAddress))
                            .addComponent(txtDadTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDadTelephone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSurname)
                            .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCF, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCF, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        registryPanelLayout.setVerticalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblDataRegistry)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblCF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSurname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblClass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblSection)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSearchFromRegistryData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchFromCF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                        .addComponent(lblGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                        .addComponent(lblBirthday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCity)
                    .addComponent(lblAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addComponent(lblDelayNumber)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDelayNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addComponent(lblDadTelephone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDadTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lblMumTelephone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMumTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
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
            .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
            .addComponent(registryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo che cancella tutti i form presenti nel frame.
     *
     * @param evt
     */

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        eraseAllForms();
    }//GEN-LAST:event_btnNewActionPerformed

    /**
     * Stampa il foglietto dei ritardi.
     *
     * @param evt
     */

    private void btnPrintDelayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintDelayActionPerformed
        printDelay(delayHours, cf);
        eraseAllForms();
    }//GEN-LAST:event_btnPrintDelayActionPerformed

    /**
     * Metodo che consente all'operatore di tornare alla home page.
     * 
     * @param evt 
     */
    
    private void btnHomePageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomePageActionPerformed
        setVisible(false);
        JFChoise homePage = new JFChoise();
        homePage.setVisible(true);
    }//GEN-LAST:event_btnHomePageActionPerformed

    /**
     * Metodo che cerca un alunno a partire dai suoi dati anagrafici.
     * 
     * @param evt 
     */
    
    private void btnSearchFromRegistryDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchFromRegistryDataActionPerformed
        searchStudentFromDataRegistry();
    }//GEN-LAST:event_btnSearchFromRegistryDataActionPerformed

    /**
     * Metodo che cerca un alunno a partire dal suo codice fiscale.
     * 
     * @param evt 
     */
    
    private void searchFromCFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFromCFActionPerformed
        searchStudentFromCF();
    }//GEN-LAST:event_searchFromCFActionPerformed

    /**
     * Metodo che invoca il metodo di stampa del ritardo se
     * il codice fiscale ha una lunghezza idonea.
     * 
     * @param evt 
     */
    
    private void txtCFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCFKeyTyped
        if (txtCF.getText().length() == 16){
                searchStudentFromCF();
        }
    }//GEN-LAST:event_txtCFKeyTyped

    /**
     * Metodi per passare da un form all'altro mediante l'uso dell'invio.
     * 
     * @param evt 
     */
    
    private void txtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNameKeyPressed
        if(evt.getKeyCode() == 10)
            txtClass.requestFocus();
    }//GEN-LAST:event_txtNameKeyPressed

    private void txtSurnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurnameKeyPressed
        if(evt.getKeyCode() == 10)
            txtName.requestFocus();
    }//GEN-LAST:event_txtSurnameKeyPressed

    private void txtClassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClassKeyPressed
        if(evt.getKeyCode() == 10)
            txtSection.requestFocus();
    }//GEN-LAST:event_txtClassKeyPressed

    private void txtSectionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSectionKeyPressed
        if(evt.getKeyCode() == 10)
            searchStudentFromDataRegistry();
    }//GEN-LAST:event_txtSectionKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHomePage;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnPrintDelay;
    private javax.swing.JButton btnSearchFromRegistryData;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBirthday;
    private javax.swing.JLabel lblCF;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDadTelephone;
    private javax.swing.JLabel lblDataRegistry;
    private javax.swing.JLabel lblDelayNumber;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblLogoPentasuglia;
    private javax.swing.JLabel lblMumTelephone;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPentaDelay;
    private javax.swing.JLabel lblSection;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JPanel registryPanel;
    private javax.swing.JButton searchFromCF;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBirthday;
    private javax.swing.JTextField txtCF;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtClass;
    private javax.swing.JTextField txtDadTelephone;
    private javax.swing.JTextField txtDelayNumber;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtMumTelephone;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSection;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables
}