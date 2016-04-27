package sys.ui.manager;

import sys.ctrl.DBConnection;
import sys.ctrl.ExecuteCBox;
import sys.ctrl.FillTable;
import sys.ctrl.MainInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 *
 * @author CK Seong
 */
public class PurchaseOrder extends JFrame implements MainInterface {

    //language=SQLite
    private String query = "";

    /**
     * Creates new form PurchaseOrder
     */
    public PurchaseOrder() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setTitle("Create Purchase Order");
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        jScrollPane1 = new JScrollPane();
        table_PR = new JTable();
        panel_control = new JPanel();
        lbl_pr = new JLabel();
        cbox_prID = new JComboBox<>();
        btn_prView = new JButton();
        panel_eButton = new JPanel();
        btn_prGen = new JButton();
        btn_prDec = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        table_PR.setAutoCreateRowSorter(true);
        jScrollPane1.setViewportView(table_PR);

        panel_control.setLayout(new GridBagLayout());

        lbl_pr.setFont(new Font("Arial", 0, 14));
        lbl_pr.setText("Select Ongoing PR to be viewed :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 16);
        panel_control.add(lbl_pr, gridBagConstraints);

        cbox_prID.setPreferredSize(new Dimension(200, 30));
        panel_control.add(cbox_prID, new GridBagConstraints());

        btn_prView.setText("View");
        btn_prView.setMaximumSize(new Dimension(77, 30));
        btn_prView.setMinimumSize(new Dimension(77, 30));
        btn_prView.setPreferredSize(new Dimension(100, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        panel_control.add(btn_prView, gridBagConstraints);

        panel_eButton.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        btn_prGen.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_prGen.setText("Generate PO");
        btn_prGen.setMaximumSize(new Dimension(120, 35));
        btn_prGen.setPreferredSize(new Dimension(120, 35));
        panel_eButton.add(btn_prGen);

        btn_prDec.setText("Decline PR");
        btn_prDec.setMaximumSize(new Dimension(100, 35));
        btn_prDec.setPreferredSize(new Dimension(90, 35));
        panel_eButton.add(btn_prDec);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(panel_control, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                    .addComponent(panel_eButton, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_control, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_eButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();

        mainFunctions();
    }

    @Override
    public void mainFunctions() {
        table_PR.setModel(new FillTable().modelPOGenerate());
        table_PR.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        populatePRBox();

        btn_prView.addActionListener(this::viewClicked);

        btn_prGen.addActionListener(this::genClicked);

        btn_prDec.addActionListener(this::decline);
    }

    private void populatePRBox(){
        cbox_prID.removeAllItems();
        DBConnection db = new DBConnection("DAT.ssb");
        query = "SELECT DISTINCT PRNo FROM PurchaseReq WHERE ORNo ISNULL ORDER BY PRNo ASC";
        new ExecuteCBox(db, query, cbox_prID);
    }

    private void viewClicked(ActionEvent e){
        if (cbox_prID.getSelectedIndex() == -1) return;

        String selected = cbox_prID.getSelectedItem().toString();

        SwingUtilities.invokeLater(()->table_PR.setModel(new FillTable().modelPOGenerate()));

        new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws Exception {
                query = "SELECT * FROM PurchaseReq pr INNER JOIN Item i ON pr.ItemCode = " +
                        "i.ItemCode WHERE pr.PRNo = '" + selected + "'";
                DBConnection db = new DBConnection("DAT.ssb");
                try {

                    ResultSet rs = db.executeQuery(query);
                    while (rs.next()){
                        Long prNo = rs.getLong("PRNo");
                        Long empId = rs.getLong("EmpID");
                        String date = rs.getString("ReqDate");
                        String iCode = rs.getString("ItemCode");
                        String iName = rs.getString("ItemName");
                        String supplier = rs.getString("SupplierID");
                        String price = String.format(Locale.UK, "%.2f", rs.getFloat("UnitPrice"));
                        Integer quantity = rs.getInt("Quantity");
                        String amount = String.format(Locale.UK, "%.2f", rs.getFloat("Amount"));

                        Object[] row = {prNo, empId, date, iCode, iName, supplier,
                                price, quantity, amount};
                        DefaultTableModel dt = (DefaultTableModel) table_PR.getModel();
                        dt.addRow(row);
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

    private void genClicked(ActionEvent e){
        if (table_PR.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "No PR is selected/currently pending.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selected = cbox_prID.getSelectedItem().toString();
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to generate " +
                "purchase order for PR No: " + cbox_prID.getSelectedItem().toString() + "?",
                "Generate Purchase Order", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;



        Integer orNo;
        query = "SELECT DISTINCT PONo FROM PurchaseOrder ORDER BY PONo DESC LIMIT 1";
        DBConnection db = new DBConnection("DAT.ssb");

        try {
            ResultSet rs = db.executeQuery(query);
            if (!rs.next()) {
                orNo = 10000;
            } else {
                orNo = rs.getInt(1);
                orNo += 1;
            }

            query = "UPDATE PurchaseReq SET ORNo = " + orNo + " WHERE PRNo = " + selected + "";
            db.executeUpdate(query);

            Float total = 0.0f;
            Integer rows = table_PR.getRowCount();
            for (int i = 0; i < rows; i++) {
                total += Float.valueOf(String.valueOf(table_PR.getModel().getValueAt(i, 8)));
                System.out.println(total);
            }

            query = "INSERT INTO PurchaseOrder VALUES ( " + orNo +
                    " , " + selected + " , date('now', 'localtime') , " + total +
                    " )";

            db.executeUpdate(query);

            JOptionPane.showMessageDialog(this, "Purchase Order No: " + orNo + " has been generated. " +
                    "Report is ready to be generated from list of POs.",
                    "Successful", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            SwingUtilities.invokeLater(()->table_PR.setModel(new FillTable().modelPOGenerate()));
            populatePRBox();
            db.closeCon();
        }
    }

    private void decline(ActionEvent e){
        if (table_PR.getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "No PR is selected/currently pending.",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to decline generation of " +
                        "purchase order for PR No: " + cbox_prID.getSelectedItem().toString() + "?",
                "Decline Purchase Requisition", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        String selected = cbox_prID.getSelectedItem().toString();

        query = "UPDATE PurchaseReq SET ORNo = 0 WHERE PRNo = " + selected;
        DBConnection db = new DBConnection("DAT.ssb");

        try {
            db.executeUpdate(query);
            JOptionPane.showMessageDialog(this, "PR No: " + selected +
                    " has been declined.", "Declination Successful",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            SwingUtilities.invokeLater(()->table_PR.setModel(new FillTable().modelPOGenerate()));
            populatePRBox();
            db.closeCon();
        }
    }

    private JButton btn_prDec;
    private JButton btn_prGen;
    private JButton btn_prView;
    private JComboBox<String> cbox_prID;
    private JScrollPane jScrollPane1;
    private JLabel lbl_pr;
    private JPanel panel_control;
    private JPanel panel_eButton;
    private JTable table_PR;
}
