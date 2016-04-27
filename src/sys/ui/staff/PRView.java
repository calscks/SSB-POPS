package sys.ui.staff;

import sys.ctrl.DBConnection;
import sys.ctrl.ExecuteCBox;
import sys.ctrl.FillTable;
import sys.ctrl.MainInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CK Seong
 */
public class PRView extends JFrame implements MainInterface {

    //language=SQLite
    private String query = "";

    /**
     * Creates new form PRView
     */
    public PRView() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setTitle("View Purchase Requisition");
    }
    
    private void initComponents() {
        GridBagConstraints gridBagConstraints;

        panel_control = new JPanel();
        lbl_iCode = new JLabel();
        cbox_prID = new JComboBox<>();
        btn_prView = new JButton();
        jScrollPane1 = new JScrollPane();
        table_prView = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        panel_control.setLayout(new GridBagLayout());

        lbl_iCode.setFont(new Font("Arial", 0, 14)); // NOI18N
        lbl_iCode.setText("Select an ongoing PR to be viewed :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 16);
        panel_control.add(lbl_iCode, gridBagConstraints);

        cbox_prID.setPreferredSize(new Dimension(200, 30));
        panel_control.add(cbox_prID, new GridBagConstraints());

        btn_prView.setText("View");
        btn_prView.setMaximumSize(new Dimension(77, 30));
        btn_prView.setMinimumSize(new Dimension(77, 30));
        btn_prView.setPreferredSize(new Dimension(100, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        panel_control.add(btn_prView, gridBagConstraints);

        table_prView.setAutoCreateRowSorter(true);
        jScrollPane1.setViewportView(table_prView);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panel_control, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_control, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();

        mainFunctions();
    }

    @Override
    public void mainFunctions() {

        populatePRBox();
        table_prView.setModel(new FillTable().modelPRView());

        btn_prView.addActionListener(this::btn_prViewClick);

    }

    private void populatePRBox(){
        cbox_prID.removeAllItems();
        DBConnection db = new DBConnection("DAT.ssb");
        query = "SELECT DISTINCT PRNo FROM PurchaseReq WHERE ORNo ISNULL ORDER BY PRNo ASC";
        new ExecuteCBox(db, query, cbox_prID);
    }

    private void btn_prViewClick(ActionEvent e) {
        String selected = cbox_prID.getSelectedItem().toString();

        SwingUtilities.invokeLater(()->table_prView.setModel(new FillTable().modelPRView()));

        new SwingWorker<Void, Void>(){
            @Override
            protected Void doInBackground() throws Exception {
                query = "SELECT * FROM PurchaseReq WHERE PRNo = '" + selected + "'";
                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    ResultSet rs = db.executeQuery(query);
                    while (rs.next()){
                        Long prNo = rs.getLong(1);
                        Long empId = rs.getLong("EmpID");
                        String reqDate = rs.getString("ReqDate");
                        String iCode = rs.getString("ItemCode");
                        Integer quantity = rs.getInt("Quantity");
                        Float amount = rs.getFloat("Amount");

                        Object[] row = {prNo, empId, reqDate, iCode, quantity, amount};
                        DefaultTableModel dt = (DefaultTableModel) table_prView.getModel();
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

    private JButton btn_prView;
    private JComboBox<String> cbox_prID;
    private JScrollPane jScrollPane1;
    private JLabel lbl_iCode;
    private JPanel panel_control;
    private JTable table_prView;
}
