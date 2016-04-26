package sys.ui.manager;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import sys.ctrl.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author CK Seong
 */
public class POList extends JFrame implements MainInterface {
    //language=SQLite
    private String query = "";

    /**
     * Creates new form POList
     */
    public POList() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        panel_control = new JPanel();
        lbl_po = new JLabel();
        tf_search = new JTextField();
        btn_search = new JButton();
        jScrollPane1 = new JScrollPane();
        table_PO = new JTable();
        panel_control1 = new JPanel();
        btn_genAll = new JButton();
        lbl_pr2 = new JLabel();
        cbox_supplier = new JComboBox<>();
        btn_gen = new JButton();
        panel_control2 = new JPanel();
        lbl_pr1 = new JLabel();
        lbl_amount = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        panel_control.setLayout(new GridBagLayout());

        lbl_po.setFont(new Font("Arial", 0, 14));
        lbl_po.setText("Search for Purchase Order :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 18);
        panel_control.add(lbl_po, gridBagConstraints);

        tf_search.setFont(new Font("Arial", 0, 14));
        tf_search.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        panel_control.add(tf_search, gridBagConstraints);

        btn_search.setText("Search");
        btn_search.setMaximumSize(new Dimension(77, 30));
        btn_search.setMinimumSize(new Dimension(77, 30));
        btn_search.setPreferredSize(new Dimension(100, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        panel_control.add(btn_search, gridBagConstraints);

        table_PO.setAutoCreateRowSorter(true);
        jScrollPane1.setViewportView(table_PO);

        panel_control1.setLayout(new GridBagLayout());

        btn_genAll.setFont(new Font("Tahoma", Font.BOLD, 12));
        btn_genAll.setForeground(new Color(0, 102, 102));
        btn_genAll.setText("Generate All");
        btn_genAll.setMaximumSize(new Dimension(150, 35));
        btn_genAll.setMinimumSize(new Dimension(77, 30));
        btn_genAll.setPreferredSize(new Dimension(150, 35));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        panel_control1.add(btn_genAll, gridBagConstraints);

        lbl_pr2.setFont(new Font("Arial", 0, 14));
        lbl_pr2.setText("Generate Report for :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(1, 0, 0, 9);
        panel_control1.add(lbl_pr2, gridBagConstraints);

        cbox_supplier.setPreferredSize(new Dimension(180, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(1, 0, 0, 0);
        panel_control1.add(cbox_supplier, gridBagConstraints);

        btn_gen.setFont(new Font("Tahoma", 0, 12));
        btn_gen.setText("Generate");
        btn_gen.setMaximumSize(new Dimension(150, 35));
        btn_gen.setMinimumSize(new Dimension(77, 30));
        btn_gen.setPreferredSize(new Dimension(120, 32));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(1, 10, 0, 0);
        panel_control1.add(btn_gen, gridBagConstraints);

        panel_control2.setLayout(new GridBagLayout());

        lbl_pr1.setFont(new Font("Arial", Font.ITALIC, 14));
        lbl_pr1.setText("Total Amount :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 18);
        panel_control2.add(lbl_pr1, gridBagConstraints);

        lbl_amount.setFont(new Font("Arial", Font.BOLD, 14));
        lbl_amount.setText("Amount");
        panel_control2.add(lbl_amount, new GridBagConstraints());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panel_control2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_control, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_control1, GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_control, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 284, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_control2, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_control1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        mainFunctions();
    }

    @Override
    public void mainFunctions() {
        table_PO.setModel(new FillTable().modelPOList());
        table_PO.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        lbl_amount.setText("0.00");

        btn_searchProperty();

        btn_search.addActionListener(this::searchClicked);

        btn_genAll.addActionListener(this::genAllClick);

        btn_gen.addActionListener(this::genClick);
    }

    private void searchClicked(ActionEvent e){
        populateTable();
    }

    private void populateTable(){
        SwingUtilities.invokeLater(() -> {
            table_PO.setModel(new FillTable().modelPOList());
            DBConnection db = new DBConnection("DAT.ssb");
            query = "SELECT * FROM PurchaseOrder po INNER JOIN " +
                    "PurchaseReq pr ON pr.ORNo = po.PONo INNER JOIN Item i ON pr.ItemCode" +
                    "= i.ItemCode WHERE po.PONo = " + tf_search.getText() +
                    "";
            try {
                ResultSet rs = db.executeQuery(query);
                while (rs.next()){
                    Long poNo = rs.getLong("PONo");
                    String date = rs.getString("PODate");
                    String iCode = rs.getString("ItemCode");
                    String iName = rs.getString("ItemName");
                    String supplier = rs.getString("SupplierID");
                    String price = String.format(Locale.UK, "%.2f", rs.getFloat("UnitPrice"));
                    Integer quantity = rs.getInt("Quantity");
                    String amount = String.format(Locale.UK, "%.2f", rs.getFloat("Amount"));
                    String total = String.format(Locale.UK, "%.2f", rs.getFloat("Total"));

                    Object[] row = {poNo, date, iCode, iName, supplier,
                            price, quantity, amount};
                    DefaultTableModel dt = (DefaultTableModel) table_PO.getModel();
                    dt.addRow(row);

                    lbl_amount.setText(total);
                }

                query = "SELECT DISTINCT SupplierID FROM Item i INNER JOIN PurchaseReq pr " +
                        "ON pr.ItemCode = i.ItemCode WHERE ORNo = " + tf_search.getText();
                rs = db.executeQuery(query);
                cbox_supplier.removeAllItems();
                while (rs.next()){
                    cbox_supplier.addItem(rs.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.closeCon();
            }
        });
    }

    private void genClick(ActionEvent e){
        if (table_PO.getModel().getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "No purchase order can be printed.",
                    "Warning", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String poNo = table_PO.getModel().getValueAt(0,0).toString();
        String supplier = cbox_supplier.getSelectedItem().toString();

        query = "SELECT po.PONo, po.PODate, i.ItemCode, i.ItemName, i.UnitPrice, " +
                "pr.Quantity, pr.Amount, i.SupplierID FROM PurchaseOrder po " +
                "INNER JOIN PurchaseReq pr ON po.PONo = pr.ORNo INNER JOIN Item i ON " +
                "pr.ItemCode = i.ItemCode WHERE po.PONo = " + poNo + " AND i.SupplierID = '" + supplier +
                "'";
        genReport(query);

    }

    private void genAllClick(ActionEvent e){
        if (table_PO.getModel().getRowCount() == 0){
            JOptionPane.showMessageDialog(this, "No purchase order can be printed.",
                    "Warning", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String poNo = table_PO.getModel().getValueAt(0,0).toString();
        query = "SELECT po.PONo, po.PODate, i.ItemCode, i.ItemName, i.UnitPrice, " +
                "pr.Quantity, pr.Amount, i.SupplierID FROM PurchaseOrder po INNER JOIN " +
                "PurchaseReq pr ON pr.ORNo = po.PONo " +
                "INNER JOIN Item i ON i.ItemCode = pr.ItemCode WHERE po.PONo = " + poNo;
        genReport(query);

    }

    private void genReport(String query){
        new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws Exception {

                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    publish();
                    ResultSet rs = db.executeQuery(query);
                    JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
                    JasperReport jasperReport;
                    jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("" +
                            "/sys/ui/report/Coffee.jrxml"));
                    JasperPrint jasperPrint;
                    Map empID = new HashMap();
                    empID.put("EmpID", LoginData.getLogin_EMPID());
                    jasperPrint = JasperFillManager.fillReport(jasperReport, empID, resultSetDataSource);
                    JasperViewer.viewReport(jasperPrint, false);
                } catch (SQLException | JRException e1) {
                    e1.printStackTrace();
                } finally {
                    db.closeCon();
                }

                return null;
            }
        }.execute();
    }

    private void btn_searchProperty(){
        ButtonModel model = btn_search.getModel();
        Document doc = tf_search.getDocument();
        BtnDisable disable = new BtnDisable(model);
        disable.addDoc(doc);
    }

    private JButton btn_gen;
    private JButton btn_genAll;
    private JButton btn_search;
    private JComboBox<String> cbox_supplier;
    private JScrollPane jScrollPane1;
    private JLabel lbl_amount;
    private JLabel lbl_po;
    private JLabel lbl_pr1;
    private JLabel lbl_pr2;
    private JPanel panel_control;
    private JPanel panel_control1;
    private JPanel panel_control2;
    private JTable table_PO;
    private JTextField tf_search;
}
