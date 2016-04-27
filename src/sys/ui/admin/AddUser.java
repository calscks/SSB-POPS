package sys.ui.admin;


import sys.ctrl.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author User
 */
public class AddUser extends JFrame implements MainInterface {

    //language=SQLite
    private String query = "";

    /**
     * Creates new form AddUser
     */
    public AddUser() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setTitle("Modify User");
    }
    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jScrollPane1 = new JScrollPane();
        table_Emp = new JTable();
        panel_eButton = new JPanel();
        btn_eAdd = new JButton();
        btn_eEdit = new JButton();
        btn_eDelete = new JButton();
        panel_control = new JPanel();
        lbl_empID = new JLabel();
        cbox_eRole = new JComboBox<>();
        lbl_eUName = new JLabel();
        tf_ePwd = new JTextField();
        lbl_ePwd = new JLabel();
        tf_eUName = new JTextField();
        lbl_eFName = new JLabel();
        tf_eLName = new JTextField();
        lbl_eLName = new JLabel();
        tf_empID = new JTextField();
        tf_eFName = new JTextField();
        lbl_eCon = new JLabel();
        lbl_eRole = new JLabel();
        tf_eCon = new JTextField();
        jPanel1 = new JPanel();
        btn_eSwitch = new JButton();
        btn_eCancel = new JButton();
        panel_eFilter = new JPanel();
        lbl_eUType = new JLabel();
        cbox_eType = new JComboBox<>();
        btn_eFilter = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        jScrollPane1.setViewportView(table_Emp);

        panel_eButton.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        btn_eAdd.setText("Add");
        btn_eAdd.setPreferredSize(new Dimension(80, 30));
        panel_eButton.add(btn_eAdd);

        btn_eEdit.setText("Edit");
        btn_eEdit.setPreferredSize(new Dimension(80, 30));
        panel_eButton.add(btn_eEdit);

        btn_eDelete.setText("Delete");
        btn_eDelete.setPreferredSize(new Dimension(80, 30));
        panel_eButton.add(btn_eDelete);

        panel_control.setLayout(new GridBagLayout());

        lbl_empID.setFont(new Font("Arial", 0, 14));
        lbl_empID.setText("Employee ID :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        panel_control.add(lbl_empID, gridBagConstraints);

        cbox_eRole.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        panel_control.add(cbox_eRole, gridBagConstraints);

        lbl_eUName.setFont(new Font("Arial", 0, 14));
        lbl_eUName.setText("Username :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        panel_control.add(lbl_eUName, gridBagConstraints);

        tf_ePwd.setFont(new Font("Arial", 0, 14));
        tf_ePwd.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        panel_control.add(tf_ePwd, gridBagConstraints);

        lbl_ePwd.setFont(new Font("Arial", 0, 14));
        lbl_ePwd.setText("Password :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        panel_control.add(lbl_ePwd, gridBagConstraints);

        tf_eUName.setFont(new Font("Arial", 0, 14));
        tf_eUName.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        panel_control.add(tf_eUName, gridBagConstraints);

        lbl_eFName.setFont(new Font("Arial", 0, 14));
        lbl_eFName.setText("First Name :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        panel_control.add(lbl_eFName, gridBagConstraints);

        tf_eLName.setFont(new Font("Arial", 0, 14));
        tf_eLName.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        panel_control.add(tf_eLName, gridBagConstraints);

        lbl_eLName.setFont(new Font("Arial", 0, 14));
        lbl_eLName.setText("Last Name :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        panel_control.add(lbl_eLName, gridBagConstraints);

        tf_empID.setFont(new Font("Arial", 0, 14));
        tf_empID.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        panel_control.add(tf_empID, gridBagConstraints);

        tf_eFName.setFont(new Font("Arial", 0, 14));
        tf_eFName.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        panel_control.add(tf_eFName, gridBagConstraints);

        lbl_eCon.setFont(new Font("Arial", 0, 14));
        lbl_eCon.setText("Contact :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 10, 15);
        panel_control.add(lbl_eCon, gridBagConstraints);

        lbl_eRole.setFont(new Font("Arial", 0, 14));
        lbl_eRole.setText("Role :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 15);
        panel_control.add(lbl_eRole, gridBagConstraints);

        tf_eCon.setFont(new Font("Arial", 0, 14));
        tf_eCon.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        panel_control.add(tf_eCon, gridBagConstraints);

        btn_eSwitch.setText("Switch");
        btn_eSwitch.setPreferredSize(new Dimension(100, 35));
        jPanel1.add(btn_eSwitch);

        btn_eCancel.setText("Cancel");
        btn_eCancel.setPreferredSize(new Dimension(100, 35));
        jPanel1.add(btn_eCancel);

        panel_eFilter.setLayout(new GridBagLayout());

        lbl_eUType.setFont(new Font("Arial", 0, 14));
        lbl_eUType.setText("User Type :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 15);
        panel_eFilter.add(lbl_eUType, gridBagConstraints);

        cbox_eType.setFont(new Font("Tahoma", 0, 12));
        cbox_eType.setPreferredSize(new Dimension(130, 30));
        panel_eFilter.add(cbox_eType, new GridBagConstraints());

        btn_eFilter.setText("Filter");
        btn_eFilter.setPreferredSize(new Dimension(80, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        panel_eFilter.add(btn_eFilter, gridBagConstraints);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panel_control, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panel_eButton, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panel_eFilter, GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_eFilter, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_eButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panel_control, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        mainFunctions();
    }

    public void mainFunctions() {

        initUType();
        initTable();
        eEnablement(false);

        table_Emp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        cbox_eRole.addItem("Admin");
        cbox_eRole.addItem("Manager");
        cbox_eRole.addItem("Staff");
        cbox_eRole.setSelectedIndex(-1);

        btn_eFilter.setEnabled(false);

        btn_eFilter.addActionListener(this::btn_filterClick);

        cbox_eType.addItemListener(this::selectionChanged);

        btn_eSwitch.addActionListener(this::btn_switchClicked);

        btn_eCancel.addActionListener(this::btn_cancelClicked);

        btn_eAdd.addActionListener(this::btn_addClicked);

        btn_eEdit.addActionListener(this::btn_editClicked);

        btn_eDelete.addActionListener(this::btn_delClicked);
    }

    private void btn_filterClick(ActionEvent e) {
        String selected = cbox_eType.getSelectedItem().toString();
        RowFilter<DefaultTableModel, Object> filter;
        try {
            DefaultTableModel model = new FillTable().tbModel("SELECT * FROM Employee");

            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);

            filter = RowFilter.regexFilter(selected,
                    table_Emp.getColumnModel().getColumnIndex("Role"));
            sorter.setRowFilter(filter);

            table_Emp.setRowSorter(sorter);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    private void selectionChanged(ItemEvent e){
        JComboBox comboBox = (JComboBox) e.getSource();

        if (comboBox.getSelectedIndex() != -1){
            btn_eFilter.setEnabled(true);
        } else {
            btn_eFilter.setEnabled(false);
        }
    }

    private void btn_addClicked(ActionEvent e){
        eReset();
        eEnablement(true);
        btnSwitchProperty();

        btn_eSwitch.setText("Add");
    }

    private void btn_editClicked(ActionEvent e){
        if (table_Emp.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Select an employee from the table to edit.",
                    "Edit Employee", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table_Emp.getSelectedRow();
        String empId = String.valueOf(table_Emp.getModel().getValueAt(
                table_Emp.convertRowIndexToModel(row), 0));

        int choice = JOptionPane.showConfirmDialog(this, "Edit this employee with ID: " +
                empId + "?", "Edit Employee", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        eReset();
        eEnablement(true);
        tf_empID.setEnabled(false);
        btn_eSwitch.setText("Edit");
        btnSwitchProperty();

        query = "SELECT * FROM Employee WHERE EmpID = " + empId;

        new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws Exception {
                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    ResultSet rs = db.executeQuery(query);
                    if (rs.next()){
                        tf_empID.setText(String.valueOf(rs.getLong(1)));
                        tf_eUName.setText(rs.getString(2));
                        tf_ePwd.setText(rs.getString(3));
                        tf_eFName.setText(rs.getString(4));
                        tf_eLName.setText(rs.getString(5));
                        tf_eCon.setText(rs.getString(6));
                        cbox_eRole.setSelectedItem(rs.getString(7));
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    db.closeCon();
                }
                return null;
            }
        }.execute();
    }

    private void btn_delClicked(ActionEvent e){
        if (table_Emp.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Select an employee from the table to delete.",
                    "Delete", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table_Emp.getSelectedRow();
        String empId = String.valueOf(table_Emp.getModel().
                getValueAt(table_Emp.convertRowIndexToModel(row), 0));

        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete employee with" +
                " ID: " + empId + "? This action cannot be undone.", "Delete Employee", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        if (Objects.equals(empId, tf_empID.getText())){
            JOptionPane.showMessageDialog(this, "This employee is under editing! Please cancel editing to delete the " +
                    "selected employee.", "Delete", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Objects.equals(LoginData.getLogin_EMPID(), empId)){
            JOptionPane.showMessageDialog(this, "You cannot delete your own account!",
                    "Delete", JOptionPane.WARNING_MESSAGE);
            return;
        }

        query = "DELETE FROM Employee WHERE EmpID = '" + empId + "'";
        customWork(query);

    }

    private void btn_switchClicked(ActionEvent e){
        if (Objects.equals(btn_eSwitch.getText(), "Add")){

            int choice = JOptionPane.showConfirmDialog(this, "Add new employee?", "Add Employee",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION)
                return;
            else if (choice == JOptionPane.CLOSED_OPTION)
                return;

            query = "SELECT EmpID, UName FROM Employee";
            DBConnection db = new DBConnection("DAT.ssb");
            try {
                ResultSet rs = db.executeQuery(query);
                while (rs.next()){
                    if (Long.valueOf(tf_empID.getText()) == rs.getLong(1)){
                        JOptionPane.showMessageDialog(this, "This ID is used by other employee.",
                                "Add Unsuccessful", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (Objects.equals(tf_eUName.getText(), rs.getString(2))){
                        JOptionPane.showMessageDialog(this, "This username is used by other employee.",
                                "Add Unsuccessful", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                db.closeCon();
            }

            query = "INSERT INTO Employee VALUES ( " + Long.parseLong(tf_empID.getText()) +
                    " ,'" + tf_eUName.getText() + "','" + tf_ePwd.getText() +
                    "','" + tf_eFName.getText() + "','" + tf_eLName.getText() +
                    "','" + tf_eCon.getText() + "','" +
                    cbox_eRole.getSelectedItem().toString() + "')";

            customWork(query);

        } else if (Objects.equals(btn_eSwitch.getText(), "Edit")){
            int choice = JOptionPane.showConfirmDialog(this, "Confirm edit?", "Edit Employee",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION)
                return;
            else if (choice == JOptionPane.CLOSED_OPTION)
                return;

            query = "UPDATE Employee SET UName = '" + tf_eUName.getText() +
                    "', Pwd = '" + tf_ePwd.getText() + "', FName = '" + tf_eFName.getText() +
                    "', LName = '" + tf_eLName.getText() + "', Phone = '" + tf_eCon.getText() +
                    "', Role = '" + cbox_eRole.getSelectedItem().toString() +
                    "' WHERE EmpID = '" + tf_empID.getText() + "'";

            customWork(query);
        }
    }

    private void btn_cancelClicked(ActionEvent e){
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        eReset();
        eEnablement(false);
    }

    private void customWork(String ins){
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    db.executeUpdate(ins);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    eReInit(db);
                }
                return null;
            }
        }.execute();
    }

    private void initUType(){
        cbox_eType.removeAllItems();
        query = "SELECT DISTINCT Role FROM Employee ORDER BY Role";
        DBConnection db = new DBConnection("DAT.ssb");
        new ExecuteCBox(db, query, cbox_eType);
    }

    private void initTable(){
        SwingUtilities.invokeLater(() -> {
            query = "SELECT * FROM Employee";
            try {
                table_Emp.setModel(new FillTable().tbModel(query));
                table_Emp.setRowSorter(null);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void eReInit(DBConnection db){
        initTable();
        initUType();
        eEnablement(false);
        eReset();

        db.closeCon();
    }

    private void eEnablement(Boolean b){
        tf_eCon.setEnabled(b);
        tf_eFName.setEnabled(b);
        tf_eLName.setEnabled(b);
        tf_empID.setEnabled(b);
        tf_ePwd.setEnabled(b);
        tf_eUName.setEnabled(b);
        cbox_eRole.setEnabled(b);
        btn_eSwitch.setEnabled(b);
        btn_eCancel.setEnabled(b);
    }

    private void eReset(){
        tf_eUName.setText("");
        tf_eCon.setText("");
        tf_ePwd.setText("");
        tf_empID.setText("");
        tf_eLName.setText("");
        tf_eFName.setText("");
        cbox_eRole.setSelectedIndex(-1);
        cbox_eType.setSelectedIndex(-1);
    }

    private void btnSwitchProperty(){
        ButtonModel model = btn_eSwitch.getModel();
        Document doc1 = tf_eCon.getDocument();
        Document doc2 = tf_empID.getDocument();
        Document doc3 = tf_ePwd.getDocument();
        Document doc4 = tf_eFName.getDocument();
        Document doc5 = tf_eLName.getDocument();
        Document doc6 = tf_eUName.getDocument();

        BtnDisable disable = new BtnDisable(model);
        disable.addDoc(doc1);
        disable.addDoc(doc2);
        disable.addDoc(doc3);
        disable.addDoc(doc4);
        disable.addDoc(doc5);
        disable.addDoc(doc6);
    }


    private JButton btn_eAdd;
    private JButton btn_eCancel;
    private JButton btn_eDelete;
    private JButton btn_eEdit;
    private JButton btn_eFilter;
    private JButton btn_eSwitch;
    private JComboBox<String> cbox_eRole;
    private JComboBox<String> cbox_eType;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JLabel lbl_eCon;
    private JLabel lbl_eFName;
    private JLabel lbl_eLName;
    private JLabel lbl_ePwd;
    private JLabel lbl_eRole;
    private JLabel lbl_eUName;
    private JLabel lbl_eUType;
    private JLabel lbl_empID;
    private JPanel panel_control;
    private JPanel panel_eButton;
    private JPanel panel_eFilter;
    private JTable table_Emp;
    private JTextField tf_eCon;
    private JTextField tf_eFName;
    private JTextField tf_eLName;
    private JTextField tf_ePwd;
    private JTextField tf_eUName;
    private JTextField tf_empID;
}
