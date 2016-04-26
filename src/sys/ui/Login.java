/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.ui;

import sys.ctrl.BtnDisable;
import sys.ctrl.LoginData;
import sys.ctrl.VerInput;
import sys.ctrl.DBConnection;
import sys.ui.admin.MainUI;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CK Seong
 */
    public class Login extends JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);//me trying to center the frame
        this.getRootPane().setDefaultButton(btn_login); //me trying to make login button
        //to default button that accepts keyboard enter

        //very first db initialisation
        DBConnection db = new DBConnection("DAT.ssb");
        db.closeCon();
    }

    //all ui components will be initialized
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel3 = new JPanel();

        JLabel lbl_SSB = new JLabel();
        JLabel lbl_POPS = new JLabel();
        JLabel lbl_uname = new JLabel();
        JLabel lbl_pwd = new JLabel();

        tf_pwd = new JPasswordField();
        btn_login = new JButton();
        tf_uname = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new GridBagLayout());

        lbl_SSB.setFont(new Font("Arial", 0, 18)); // NOI18N
        lbl_SSB.setText("SHOPMART SDN BHD");
        lbl_SSB.setName(""); // NOI18N
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(7, 0, 7, 0);
        jPanel1.add(lbl_SSB, gridBagConstraints);

        lbl_POPS.setFont(new Font("Times New Roman", Font.ITALIC, 14)); // NOI18N
        lbl_POPS.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_POPS.setText("Purchase Order Processing System ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(7, 0, 7, 0);
        jPanel1.add(lbl_POPS, gridBagConstraints);

        jPanel2.setMinimumSize(new Dimension(100, 20));
        jPanel2.setLayout(new GridBagLayout());

        lbl_uname.setFont(new Font("Arial", 0, 14)); // NOI18N
        lbl_uname.setText("Username :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(18, 0, 18, 13);
        jPanel2.add(lbl_uname, gridBagConstraints);

        tf_uname.setFont(new Font("Arial", 0, 14)); // NOI18N
        tf_uname.setName("tf_uname"); // NOI18N
        tf_uname.setPreferredSize(new Dimension(150, 30));

        //i want only keyTyped event instead of all 3 keyTyped, keyPressed and keyReleased
        tf_uname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                new VerInput().keyTyped(e, 20);
            }
        });

        tf_uname.setTransferHandler(null);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(18, 0, 18, 15);
        jPanel2.add(tf_uname, gridBagConstraints);

        lbl_pwd.setFont(new Font("Arial", 0, 14)); // NOI18N
        lbl_pwd.setText("Password :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(18, 0, 18, 13);
        jPanel2.add(lbl_pwd, gridBagConstraints);

        tf_pwd.setFont(new Font("Arial", Font.BOLD, 16)); // NOI18N
        tf_pwd.setMinimumSize(new Dimension(150, 23));
        tf_pwd.setName("tf_pwd"); // NOI18N
        tf_pwd.setPreferredSize(new Dimension(150, 30));
        tf_pwd.setEchoChar('•');
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(18, 0, 18, 16);
        jPanel2.add(tf_pwd, gridBagConstraints);

        btn_login.setText("Login");
        btn_login.setName("btn_login"); // NOI18N
        btn_login.setPreferredSize(new Dimension(70, 30));

        //method referencing
        btn_login.addActionListener(this::btn_loginActionPerformed);
        jPanel3.add(btn_login);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        btnProperty();
        pack();
    }


    private void btn_loginActionPerformed(ActionEvent evt) {

        //i tried creating a multi-threading environment. because whenever i press login i feel the lag
        new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws Exception{
                loginVerify();
                return null;
            }
        }.execute();
    }

    private void loginVerify(){
        //language=SQLite
        String match;

        //check for existence of username, only then check for matches
        match = "SELECT * FROM Employee WHERE UName = '" + tf_uname.getText() +
                "'";

        DBConnection db = new DBConnection("DAT.ssb");

        try {
            ResultSet rs = db.executeQuery(match);

            if (rs.next()){
                match = "SELECT * FROM Employee WHERE UName = '" + tf_uname.getText() +
                        "' AND Pwd = '" + String.valueOf(tf_pwd.getPassword()) + "'";
                rs = db.executeQuery(match);

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful as " + rs.getString("Role"),
                            "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    LoginData.setLogin_FNAME(rs.getString("FName"));
                    LoginData.setLogin_UNAME(rs.getString("UName"));
                    LoginData.setLogin_ROLE(rs.getString("Role"));
                    LoginData.setLogin_EMPID(rs.getString("EmpID"));

                    this.dispose();
                    EventQueue.invokeLater(() -> new MainUI().setVisible(true));

                } else {
                    JOptionPane.showMessageDialog(this, "Username and password do not match. Please try again.",
                            "Login Unsuccessful", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Username does not exist. Please contact the administrator to " +
                                "add your account to grant your access to this system.",
                        "Login Unsuccessful", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //i need to close db connection after all these stuffs above still.
            db.closeCon();
        }
    }

    private void btnProperty(){
        ButtonModel model = btn_login.getModel();
        Document doc1 = tf_uname.getDocument();
        Document doc2 = tf_pwd.getDocument();

        BtnDisable property = new BtnDisable(model);
        property.addDoc(doc1);
        property.addDoc(doc2);
    }

    private JButton btn_login;
    private JPasswordField tf_pwd;
    private JTextField tf_uname;

}
