package sys.ui.staff;


import sys.ctrl.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author CK Seong
 */
public class PurchaseReq extends JFrame implements MainInterface {

    //language=SQLite
    private String query = "";

    /**
     * Creates new form PurchaseReq
     */
    public PurchaseReq() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setTitle("Create Purchase Requisition");
        mainFunctions();
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jScrollPane1 = new JScrollPane();
        table_CPR = new JTable();
        jPanel1 = new JPanel();
        lbl_iCode = new JLabel();
        cbox_iCode = new JComboBox<>();
        lbl_iName = new JLabel();
        tf_iSupplier = new JTextField();
        lbl_iSupplier = new JLabel();
        tf_iName = new JTextField();
        lbl_iPrice = new JLabel();
        tf_iQuantity = new JTextField();
        lbl_iQuantity = new JLabel();
        tf_iTotal = new JTextField();
        tf_iPrice = new JTextField();
        lbl_iTotal = new JLabel();
        jPanel2 = new JPanel();
        btn_iGenerate = new JButton();
        btn_iDel = new JButton();
        jPanel3 = new JPanel();
        btn_iAdd = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        table_CPR.setAutoCreateRowSorter(true);
        jScrollPane1.setViewportView(table_CPR);

        jPanel1.setLayout(new GridBagLayout());

        lbl_iCode.setFont(new Font("Arial", 0, 14));
        lbl_iCode.setText("Item Code :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 13, 15);
        jPanel1.add(lbl_iCode, gridBagConstraints);

        cbox_iCode.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 13, 0);
        jPanel1.add(cbox_iCode, gridBagConstraints);

        lbl_iName.setFont(new Font("Arial", 0, 14));
        lbl_iName.setText("Item Name :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 13, 15);
        jPanel1.add(lbl_iName, gridBagConstraints);

        tf_iSupplier.setEditable(false);
        tf_iSupplier.setFont(new Font("Arial", 0, 14));
        tf_iSupplier.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 13, 0);
        jPanel1.add(tf_iSupplier, gridBagConstraints);

        lbl_iSupplier.setFont(new Font("Arial", 0, 14));
        lbl_iSupplier.setText("Supplier :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 13, 15);
        jPanel1.add(lbl_iSupplier, gridBagConstraints);

        tf_iName.setEditable(false);
        tf_iName.setFont(new Font("Arial", 0, 14));
        tf_iName.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 13, 0);
        jPanel1.add(tf_iName, gridBagConstraints);

        lbl_iPrice.setFont(new Font("Arial", 0, 14));
        lbl_iPrice.setText("Unit Price :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 13, 15);
        jPanel1.add(lbl_iPrice, gridBagConstraints);

        tf_iQuantity.setFont(new Font("Arial", 0, 14));
        tf_iQuantity.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 13, 0);
        jPanel1.add(tf_iQuantity, gridBagConstraints);

        lbl_iQuantity.setFont(new Font("Arial", 0, 14));
        lbl_iQuantity.setText("Quantity :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 13, 15);
        jPanel1.add(lbl_iQuantity, gridBagConstraints);

        tf_iTotal.setEditable(false);
        tf_iTotal.setFont(new Font("Arial", 0, 14));
        tf_iTotal.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        jPanel1.add(tf_iTotal, gridBagConstraints);

        tf_iPrice.setEditable(false);
        tf_iPrice.setFont(new Font("Arial", 0, 14));
        tf_iPrice.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 13, 0);
        jPanel1.add(tf_iPrice, gridBagConstraints);

        lbl_iTotal.setFont(new Font("Arial", 0, 14));
        lbl_iTotal.setText("Total :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 15);
        jPanel1.add(lbl_iTotal, gridBagConstraints);

        jPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));

        btn_iGenerate.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_iGenerate.setText("Generate PR");
        btn_iGenerate.setPreferredSize(new Dimension(110, 35));
        jPanel2.add(btn_iGenerate);

        btn_iDel.setText("Delete Item");
        btn_iDel.setPreferredSize(new Dimension(100, 35));
        jPanel2.add(btn_iDel);

        btn_iAdd.setText("Add Item");
        btn_iAdd.setPreferredSize(new Dimension(100, 35));
        jPanel3.add(btn_iAdd);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        pack();
    }

    public void mainFunctions() {
        table_CPR.setModel(new FillTable().modelPR());
        initItemCBox();
        btn_iAddProperty();

        cbox_iCode.addItemListener(this::itemCBoxSelection);

        PlainDocument doc = (PlainDocument) tf_iQuantity.getDocument();
        doc.setDocumentFilter(new Filter(RegexType.DIGIT.getRegexExp()));

        tf_iQuantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                tf_iQuantityChanged(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                tf_iQuantityChanged(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                tf_iQuantityChanged(e);
            }
        });

        btn_iAdd.addActionListener(this::btn_iAddClicked);

        btn_iDel.addActionListener(this::btn_iDelClicked);

        btn_iGenerate.addActionListener(this::btn_iGenClicked);
    }

    //applied to the item code cBox item listener
    private void itemCBoxSelection(ItemEvent e) {
        JComboBox comboBox = (JComboBox) e.getSource();
        reset();

        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selected = comboBox.getSelectedItem().toString();
            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    query = "SELECT * FROM Item WHERE ItemCode = '" + selected +
                            "'";
                    DBConnection db = new DBConnection("DAT.ssb");
                    try {
                        ResultSet rs = db.executeQuery(query);
                        if (rs.next()) {
                            tf_iName.setText(rs.getString("ItemName"));
                            tf_iSupplier.setText(rs.getString("SupplierID"));
                            tf_iPrice.setText(String.format(Locale.UK, "%.2f", rs.getFloat("UnitPrice")));
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
    }

    //applied to the document listener of quantity textfield
    private void tf_iQuantityChanged(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(0, e.getDocument().getLength());

            if (Objects.equals(text, "") || text == null) {
                tf_iTotal.setText("");
                return;
            }

            Integer quantity = Integer.valueOf(text);

            if (!Objects.equals(tf_iPrice.getText(), "") && tf_iPrice.getText() != null) {

                Float total = Float.parseFloat(tf_iPrice.getText()) * quantity;
                tf_iTotal.setText(String.format(Locale.UK, "%.2f", total));

            }

        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    private void btn_iAddClicked(ActionEvent e) {
        int choice = JOptionPane.showConfirmDialog(this, "Add item to Purchase Requisition List?",
                "Add Item", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        String iCode = cbox_iCode.getSelectedItem().toString();
        String iName = tf_iName.getText();
        String iQuantity = tf_iQuantity.getText();
        String iAmount = tf_iTotal.getText();

        Object[] row = {iCode, iName, iQuantity, iAmount};
        DefaultTableModel dt = (DefaultTableModel) table_CPR.getModel();

        dt.addRow(row);

        reset();
        cbox_iCode.setSelectedIndex(-1);
    }

    private void btn_iDelClicked(ActionEvent e) {
        if (table_CPR.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Select one of the items in the purchase requisition table to remove.",
                    "Remove Items", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selected = table_CPR.getSelectedRow();
        String iCode = (String) table_CPR.getModel().getValueAt(selected, 0);

        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete" +
                " Item ID: " + iCode + " from the Purchase Requisition Table?", "Delete Item", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        DefaultTableModel model = (DefaultTableModel) table_CPR.getModel();
        model.removeRow(selected);
    }

    private void btn_iGenClicked(ActionEvent e) {

        int choice = JOptionPane.showConfirmDialog(this, "Create new Purchase Requisition?",
                "Generate PR", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        int rows = table_CPR.getRowCount();
        Integer prNo;
        DBConnection db = new DBConnection("DAT.ssb");
        query = "SELECT DISTINCT PRNo FROM PurchaseReq ORDER BY PRNo DESC LIMIT 1";

        try {
            ResultSet rs = db.executeQuery(query);
            if (!rs.next()) {
                prNo = 10000;
            } else {
                prNo = rs.getInt(1);
                prNo += 1;
            }


            for (int i = 0; i < rows; i++) {
                String iCode = (String) table_CPR.getModel().getValueAt(i, 0);
                String iQuantity = (String) table_CPR.getModel().getValueAt(i, 2);
                String iAmount = (String) table_CPR.getModel().getValueAt(i, 3);

                query = "INSERT INTO PurchaseReq VALUES ( " + prNo +
                        " , " + Long.parseLong(LoginData.getLogin_EMPID()) +
                        " ,'" + iCode + "',date('now', 'localtime')" +
                        ", " + Integer.parseInt(iQuantity) +
                        " , " + Float.parseFloat(iAmount) +
                        " , NULL)";

                db.executeUpdate(query);
            }

            JOptionPane.showMessageDialog(this, "Purchase Requisition has been created! " +
                            "The PR number you have created is No: " + String.valueOf(prNo),
                    "PR Generated Successfully", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            SwingUtilities.invokeLater(() -> table_CPR.setModel(new FillTable().modelPR()));
            db.closeCon();
        }
    }

    private void btn_iAddProperty() {
        ButtonModel model = btn_iAdd.getModel();
        Document doc1 = tf_iName.getDocument();
        Document doc2 = tf_iSupplier.getDocument();
        Document doc3 = tf_iPrice.getDocument();
        Document doc4 = tf_iQuantity.getDocument();
        Document doc5 = tf_iTotal.getDocument();

        BtnDisable disable = new BtnDisable(model);
        disable.addDoc(doc1);
        disable.addDoc(doc2);
        disable.addDoc(doc3);
        disable.addDoc(doc4);
        disable.addDoc(doc5);
    }

    private void initItemCBox() {
        cbox_iCode.removeAllItems();
        query = "SELECT ItemCode FROM Item";
        DBConnection db = new DBConnection("DAT.ssb");
        new ExecuteCBox(db, query, cbox_iCode);
    }

    private void reset() {
        tf_iName.setText("");
        tf_iSupplier.setText("");
        tf_iPrice.setText("");
        tf_iQuantity.setText("");
        tf_iTotal.setText("");
    }

    private JButton btn_iAdd;
    private JButton btn_iDel;
    private JButton btn_iGenerate;
    private JComboBox<String> cbox_iCode;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTable table_CPR;
    private JLabel lbl_iCode;
    private JLabel lbl_iName;
    private JLabel lbl_iPrice;
    private JLabel lbl_iQuantity;
    private JLabel lbl_iSupplier;
    private JLabel lbl_iTotal;
    private JTextField tf_iName;
    private JTextField tf_iPrice;
    private JTextField tf_iQuantity;
    private JTextField tf_iSupplier;
    private JTextField tf_iTotal;
}
