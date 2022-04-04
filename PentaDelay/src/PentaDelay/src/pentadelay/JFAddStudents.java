package pentadelay;

/*
 * Questa classe descrive un JFrame di tipo JFAddStudents
 * che scrive nel database un nuovo record con i dati anagrafici
 * e scolastici del nuovo alunno.
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

public class JFAddStudents extends javax.swing.JFrame {
    
    /**
     * Crea un nuovo form di tipo JFAggiungiAlunno.
     */
    
    public JFAddStudents() {
        
        initComponents();
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((screenSize.width / 2) - (this.getWidth() / 2), (screenSize.height / 2) - (this.getHeight() / 2));
        
        txtSurname.requestFocus();
        
        ImageIcon img = new ImageIcon("..//..//..//img//iconaPentasuglia.jpg");
        this.setIconImage(img.getImage());
    }
    
    
    
    /**
     * Metodo che controlla l'integrità dei dati inseriti.
     * 
     * @throws EmptyFormsException
     * @throws WrongDataException
     */
    
    private void checkNewStudent() throws EmptyFormsException, WrongDataException{
        
        if (txtName.getText().equals("")|| txtSurname.getText().equals("") || txtGender.getText().equals("") || txtBirthday.getText().equals("") ||
            txtCity.getText().equals("") || txtAddress.getText().equals("") || txtClass.getText().equals("") || txtSection.getText().equals("") ||
            txtCF.getText().equals("") || txtMumTelephone.getText().equals("") || txtDadTelephone.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Riempire tutti i form necessari.", "Errore inserimento campi", JOptionPane.WARNING_MESSAGE);
            throw new EmptyFormsException();
        }
        
        if (!(txtGender.getText().equals("m") || txtGender.getText().equals("M") || txtGender.getText().equals("f") || txtGender.getText().equals("F"))){
            JOptionPane.showMessageDialog(null, "Inserire sesso corretto.", "Sesso errato", JOptionPane.ERROR_MESSAGE);
            throw new WrongDataException();
        }
        
        int classroom;
        try{
            classroom = Integer.parseInt(txtClass.getText());
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Inserire classe corretta.", "Classe errata", JOptionPane.ERROR_MESSAGE);
            throw new WrongDataException();
        }
        
        if (classroom < 1 || classroom > 5){
            JOptionPane.showMessageDialog(null, "Inserire classe corretta.", "Classe errata", JOptionPane.ERROR_MESSAGE);
            throw new WrongDataException();
        }
        
        if (txtCF.getText().length() != 16){
            JOptionPane.showMessageDialog(null, "Inserire codice fiscale corretto.", "Codice fiscale errato", JOptionPane.ERROR_MESSAGE);
            throw new WrongDataException();
        }
    }
    
    /**
     * Metodo che aggiunge uno studente nel database scolastico.
     */
    
    private void addStudent(){
        try {
            checkNewStudent();
            
            String query = "INSERT INTO AlunniPenta VALUES('" + txtClass.getText() + "', '"
             + txtSection.getText().toUpperCase() + "', '"
             + txtSurname.getText().toUpperCase() + "', '"
             + txtName.getText().toUpperCase() + "', '"
             + txtBirthday.getText() + "', '"
             + txtCF.getText().toUpperCase() + "', '"
             + txtCity.getText().toUpperCase() + "', '"
             + txtAddress.getText().toUpperCase() + "', '"
             + txtDadTelephone.getText() + "', '"
             + txtMumTelephone.getText() + "', '"
             + txtGender.getText().toUpperCase() + "', '"
             + "0" + "')";
        
            ConnessioneDB conDB = new ConnessioneDB("..//..//..//database//db_pentadelay.mdb");

            if (conDB.updateQueryDB(query) == 1)
                JOptionPane.showMessageDialog(null, "Alunno inserito correttamente.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Ops! Qualcosa è andato storto! Riprova.", "Errore inserimento alunno", JOptionPane.WARNING_MESSAGE);
            
            eraseAllForms();
        }
        catch (ClassNotFoundException | SQLException | PentaDelayException ex) {}
    }
    
    /**
     * Cancella il contenuto di tutti i form presenti nel frame.
     */
    
    private void eraseAllForms() {
        txtSurname.setText("");
        txtBirthday.setText("");
        txtCity.setText("");
        txtAddress.setText("");
        txtGender.setText("");
        txtClass.setText("");
        txtSection.setText("");
        txtCF.setText("");
        txtMumTelephone.setText("");
        txtDadTelephone.setText("");
        txtName.setText("");
        txtSurname.requestFocus();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registryPanel = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        lblSurname = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        lblBirthday = new javax.swing.JLabel();
        lblCity = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblCF = new javax.swing.JLabel();
        txtCF = new javax.swing.JTextField();
        lblClass = new javax.swing.JLabel();
        txtBirthday = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        txtClass = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblSection = new javax.swing.JLabel();
        txtSection = new javax.swing.JTextField();
        btnAddStudent = new javax.swing.JButton();
        lblMumTelephone = new javax.swing.JLabel();
        txtMumTelephone = new javax.swing.JTextField();
        lblShowStudentsStatus = new javax.swing.JLabel();
        lblDadTelephone = new javax.swing.JLabel();
        txtDadTelephone = new javax.swing.JTextField();
        buttonsPanel = new javax.swing.JPanel();
        lblPentadelay = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        lblLogoPentasuglia = new javax.swing.JLabel();
        btnHomePage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aggiungi studenti");
        setResizable(false);

        registryPanel.setBackground(new java.awt.Color(241, 241, 241));
        registryPanel.setEnabled(false);
        registryPanel.setFocusable(false);

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

        txtCity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCity.setForeground(new java.awt.Color(102, 102, 102));
        txtCity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCity.setName(""); // NOI18N
        txtCity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCityKeyPressed(evt);
            }
        });

        lblBirthday.setForeground(new java.awt.Color(102, 102, 102));
        lblBirthday.setText("DATA NASCITA");

        lblCity.setForeground(new java.awt.Color(102, 102, 102));
        lblCity.setText("COMUNE RESIDENZA");

        lblGender.setForeground(new java.awt.Color(102, 102, 102));
        lblGender.setText("SESSO");

        lblCF.setForeground(new java.awt.Color(102, 102, 102));
        lblCF.setText("CODICE FISCALE");

        txtCF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCF.setForeground(new java.awt.Color(102, 102, 102));
        txtCF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCF.setFocusCycleRoot(true);
        txtCF.setName(""); // NOI18N
        txtCF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCFKeyPressed(evt);
            }
        });

        lblClass.setForeground(new java.awt.Color(102, 102, 102));
        lblClass.setText("CLASSE");

        txtBirthday.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtBirthday.setForeground(new java.awt.Color(102, 102, 102));
        txtBirthday.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBirthday.setName(""); // NOI18N
        txtBirthday.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBirthdayKeyPressed(evt);
            }
        });

        txtGender.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGender.setForeground(new java.awt.Color(102, 102, 102));
        txtGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtGender.setName(""); // NOI18N
        txtGender.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGenderKeyPressed(evt);
            }
        });

        txtClass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtClass.setForeground(new java.awt.Color(102, 102, 102));
        txtClass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtClass.setName(""); // NOI18N
        txtClass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtClassKeyPressed(evt);
            }
        });

        txtSurname.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtSurname.setForeground(new java.awt.Color(102, 102, 102));
        txtSurname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtSurname.setName(""); // NOI18N
        txtSurname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSurnameKeyPressed(evt);
            }
        });

        lblAddress.setForeground(new java.awt.Color(102, 102, 102));
        lblAddress.setText("INDIRIZZO RESIDENZA");

        txtAddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtAddress.setForeground(new java.awt.Color(102, 102, 102));
        txtAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAddress.setName(""); // NOI18N
        txtAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAddressKeyPressed(evt);
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

        btnAddStudent.setBackground(new java.awt.Color(164, 55, 58));
        btnAddStudent.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnAddStudent.setForeground(new java.awt.Color(255, 255, 255));
        btnAddStudent.setText("Aggiungi");
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        lblMumTelephone.setForeground(new java.awt.Color(102, 102, 102));
        lblMumTelephone.setText("TELEFONO MADRE");

        txtMumTelephone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMumTelephone.setForeground(new java.awt.Color(102, 102, 102));
        txtMumTelephone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMumTelephone.setFocusCycleRoot(true);
        txtMumTelephone.setName(""); // NOI18N
        txtMumTelephone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMumTelephoneKeyPressed(evt);
            }
        });

        lblShowStudentsStatus.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        lblShowStudentsStatus.setForeground(new java.awt.Color(68, 68, 68));
        lblShowStudentsStatus.setText("Aggiungi alunno");

        lblDadTelephone.setForeground(new java.awt.Color(102, 102, 102));
        lblDadTelephone.setText("TELEFONO PADRE");

        txtDadTelephone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDadTelephone.setForeground(new java.awt.Color(102, 102, 102));
        txtDadTelephone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtDadTelephone.setFocusCycleRoot(true);
        txtDadTelephone.setName(""); // NOI18N
        txtDadTelephone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDadTelephoneKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout registryPanelLayout = new javax.swing.GroupLayout(registryPanel);
        registryPanel.setLayout(registryPanelLayout);
        registryPanelLayout.setHorizontalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, registryPanelLayout.createSequentialGroup()
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDadTelephone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtBirthday, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtClass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCF, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCity)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, registryPanelLayout.createSequentialGroup()
                                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblCity, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblClass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblBirthday, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 74, Short.MAX_VALUE)))
                        .addGap(98, 98, 98))
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDadTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblShowStudentsStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSurname)
                            .addComponent(txtSurname))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblAddress)
                        .addComponent(txtSection)
                        .addComponent(lblSection)
                        .addComponent(txtAddress)
                        .addComponent(lblGender)
                        .addComponent(txtGender)
                        .addComponent(lblMumTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMumTelephone)
                        .addComponent(btnAddStudent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                .addGap(50, 50, 50))
        );
        registryPanelLayout.setVerticalGroup(
            registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registryPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblShowStudentsStatus)
                .addGap(42, 42, 42)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addComponent(lblSurname)
                        .addGap(23, 23, 23)))
                .addGap(18, 18, 18)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(registryPanelLayout.createSequentialGroup()
                                .addComponent(lblCity)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(registryPanelLayout.createSequentialGroup()
                                .addComponent(lblAddress)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addComponent(lblClass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addComponent(lblSection)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBirthday)
                    .addComponent(lblGender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBirthday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCF)
                    .addComponent(lblMumTelephone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMumTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(registryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(registryPanelLayout.createSequentialGroup()
                        .addComponent(lblDadTelephone)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDadTelephone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        buttonsPanel.setBackground(new java.awt.Color(164, 55, 58));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(534, 160));

        lblPentadelay.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblPentadelay.setForeground(new java.awt.Color(255, 255, 255));
        lblPentadelay.setText("PentaDelay");

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

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblLogoPentasuglia)
                .addGap(18, 18, 18)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNew)
                    .addComponent(btnHomePage)
                    .addComponent(lblPentadelay))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblLogoPentasuglia))
                    .addGroup(buttonsPanelLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(lblPentadelay, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(registryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(registryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(buttonsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo che aggiunge un alunno nel database scolastico.
     * 
     * @param evt 
     */
    
    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentActionPerformed
        addStudent();
    }//GEN-LAST:event_btnAddStudentActionPerformed

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
            txtCity.requestFocus();
    }//GEN-LAST:event_txtNameKeyPressed

    private void txtCityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCityKeyPressed
        if(evt.getKeyCode() == 10)
            txtAddress.requestFocus();
    }//GEN-LAST:event_txtCityKeyPressed

    private void txtAddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAddressKeyPressed
        if(evt.getKeyCode() == 10)
            txtClass.requestFocus();
    }//GEN-LAST:event_txtAddressKeyPressed

    private void txtClassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClassKeyPressed
        if(evt.getKeyCode() == 10)
            txtSection.requestFocus();
    }//GEN-LAST:event_txtClassKeyPressed

    private void txtSectionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSectionKeyPressed
        if(evt.getKeyCode() == 10)
            txtBirthday.requestFocus();
    }//GEN-LAST:event_txtSectionKeyPressed

    private void txtBirthdayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBirthdayKeyPressed
        if(evt.getKeyCode() == 10)
            txtGender.requestFocus();
    }//GEN-LAST:event_txtBirthdayKeyPressed

    private void txtGenderKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGenderKeyPressed
        if(evt.getKeyCode() == 10)
            txtCF.requestFocus();
    }//GEN-LAST:event_txtGenderKeyPressed

    private void txtCFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCFKeyPressed
        if(evt.getKeyCode() == 10)
            txtMumTelephone.requestFocus();
    }//GEN-LAST:event_txtCFKeyPressed

    private void txtDadTelephoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDadTelephoneKeyPressed
        if(evt.getKeyCode() == 10)
            addStudent();
    }//GEN-LAST:event_txtDadTelephoneKeyPressed

    private void txtMumTelephoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMumTelephoneKeyPressed
        if(evt.getKeyCode() == 10)
            txtDadTelephone.requestFocus();
    }//GEN-LAST:event_txtMumTelephoneKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnHomePage;
    private javax.swing.JButton btnNew;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBirthday;
    private javax.swing.JLabel lblCF;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblClass;
    private javax.swing.JLabel lblDadTelephone;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblLogoPentasuglia;
    private javax.swing.JLabel lblMumTelephone;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPentadelay;
    private javax.swing.JLabel lblSection;
    private javax.swing.JLabel lblShowStudentsStatus;
    private javax.swing.JLabel lblSurname;
    private javax.swing.JPanel registryPanel;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBirthday;
    private javax.swing.JTextField txtCF;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtClass;
    private javax.swing.JTextField txtDadTelephone;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtMumTelephone;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSection;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables
}
