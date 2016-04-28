package sys.ui.admin;

import sys.ctrl.*;
import sys.ui.Login;
import sys.ui.manager.POList;
import sys.ui.manager.PurchaseOrder;
import sys.ui.staff.PRView;
import sys.ui.staff.PurchaseReq;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author CK Seong
 */
public class MainUI extends JFrame implements MainInterface{

    //language=SQLite
    private String query = "";

    /**
     * Creates new form MainUI
     */
    public MainUI() {
        try {
            initComponents();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.setLocationRelativeTo(null);
        this.setTitle("SSB Purchase Order Process System");
    }

    //initialize components
    private void initComponents() throws ParseException {
        GridBagConstraints gridBagConstraints;

        jTabPane = new JTabbedPane();
        panel_IE = new JPanel();
        jScrollPane1 = new JScrollPane();
        table_item = new JTable();
        panel_iFill = new JPanel();
        lbl_iCode = new JLabel();
        tf_iCode = new JTextField();
        lbl_iSupplier = new JLabel();
        lbl_iName1 = new JLabel();
        tf_iPrice = new JTextField();
        cbox_iSupplier = new JComboBox<>();
        lbl_iPrice = new JLabel();
        tf_iName = new JTextField();
        panel_iButton = new JPanel();
        btn_iAdd = new JButton();
        btn_iEdit = new JButton();
        btn_iDelete = new JButton();
        panel_iButton1 = new JPanel();
        btn_iSwitchable = new JButton();
        btn_iCancel = new JButton();
        panel_SE = new JPanel();
        panel_sFill = new JPanel();
        lbl_sid = new JLabel();
        tf_sid = new JTextField();
        lbl_address = new JLabel();
        lbl_sfname = new JLabel();
        tf_address = new JTextField();
        lbl_slname = new JLabel();
        tf_sfname = new JTextField();
        lbl_city = new JLabel();
        lbl_postcode = new JLabel();
        lbl_company = new JLabel();
        lbl_state = new JLabel();
        lbl_contact = new JLabel();
        tf_postcode = new JTextField();
        tf_city = new JTextField();
        tf_state = new JTextField();
        tf_contact = new JTextField();
        tf_company = new JTextField();
        tf_slname = new JTextField();
        panel_sButton = new JPanel();
        btn_sAdd = new JButton();
        btn_sEdit = new JButton();
        btn_sDel = new JButton();
        //set always visible horizontal scrollbar
        jScrollPane3 = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table_supplier = new JTable();
        panel_sButton1 = new JPanel();
        btn_sSwitch = new JButton();
        btn_sCancel = new JButton();
        lbl_welcome = new JLabel();
        lbl_role = new JLabel();
        lbl_id = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        menuBar = new JMenuBar();
        menu_create = new JMenu();
        menu_PR = new JMenuItem();
        menu_PO = new JMenuItem();
        menu_viewPR = new JMenuItem();
        menu_LPO = new JMenuItem();
        menu_add = new JMenu();
        menu_logout = new JMenu();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(910, 540));
        setResizable(false);

        jTabPane.setMinimumSize(new Dimension(1280, 535));
        jTabPane.setPreferredSize(new Dimension(1280, 535));
        jScrollPane1.setViewportView(table_item);

        panel_iFill.setLayout(new GridBagLayout());

        lbl_iCode.setFont(new Font("Arial", 0, 14));
        lbl_iCode.setText("Item Code :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 22, 14);
        panel_iFill.add(lbl_iCode, gridBagConstraints);

        tf_iCode.setFont(new Font("Arial", 0, 14));
        tf_iCode.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 22, 0);
        panel_iFill.add(tf_iCode, gridBagConstraints);

        lbl_iSupplier.setFont(new Font("Arial", 0, 14));
        lbl_iSupplier.setText("Item Supplier :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 14);
        panel_iFill.add(lbl_iSupplier, gridBagConstraints);

        lbl_iName1.setFont(new Font("Arial", 0, 14));
        lbl_iName1.setText("Item Name :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 22, 14);
        panel_iFill.add(lbl_iName1, gridBagConstraints);

        tf_iPrice.setFont(new Font("Arial", 0, 14));
        tf_iPrice.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 22, 0);
        panel_iFill.add(tf_iPrice, gridBagConstraints);

        cbox_iSupplier.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        panel_iFill.add(cbox_iSupplier, gridBagConstraints);

        lbl_iPrice.setFont(new Font("Arial", 0, 14));
        lbl_iPrice.setText("Unit Price :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 22, 14);
        panel_iFill.add(lbl_iPrice, gridBagConstraints);

        tf_iName.setFont(new Font("Arial", 0, 14));
        tf_iName.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 22, 0);
        panel_iFill.add(tf_iName, gridBagConstraints);

        panel_iButton.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        btn_iAdd.setText("Add");
        btn_iAdd.setPreferredSize(new Dimension(80, 30));
        panel_iButton.add(btn_iAdd);

        btn_iEdit.setText("Edit");
        btn_iEdit.setPreferredSize(new Dimension(80, 30));
        panel_iButton.add(btn_iEdit);

        btn_iDelete.setText("Delete");
        btn_iDelete.setPreferredSize(new Dimension(80, 30));
        panel_iButton.add(btn_iDelete);

        panel_iButton1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        btn_iSwitchable.setText("Switch");
        btn_iSwitchable.setPreferredSize(new Dimension(80, 30));
        panel_iButton1.add(btn_iSwitchable);

        btn_iCancel.setText("Cancel");
        btn_iCancel.setPreferredSize(new Dimension(80, 30));
        panel_iButton1.add(btn_iCancel);

        GroupLayout panel_IELayout = new GroupLayout(panel_IE);
        panel_IE.setLayout(panel_IELayout);
        panel_IELayout.setHorizontalGroup(
                panel_IELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel_IELayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_IELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panel_iFill, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel_iButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_IELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panel_iButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panel_IELayout.setVerticalGroup(
                panel_IELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel_IELayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_IELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(panel_IELayout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(panel_iButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_IELayout.createSequentialGroup()
                                                .addComponent(panel_iFill, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(panel_iButton1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabPane.addTab("Item Entry", panel_IE);

        panel_sFill.setLayout(new GridBagLayout());

        lbl_sid.setFont(new Font("Arial", 0, 14));
        lbl_sid.setText("Supplier ID :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_sid, gridBagConstraints);

        tf_sid.setFont(new Font("Arial", 0, 14));
        tf_sid.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_sid, gridBagConstraints);

        lbl_address.setFont(new Font("Arial", 0, 14));
        lbl_address.setText("Adress :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_address, gridBagConstraints);

        lbl_sfname.setFont(new Font("Arial", 0, 14));
        lbl_sfname.setText("First Name :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_sfname, gridBagConstraints);

        tf_address.setFont(new Font("Arial", 0, 14));
        tf_address.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_address, gridBagConstraints);

        lbl_slname.setFont(new Font("Arial", 0, 14));
        lbl_slname.setText("Last Name :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_slname, gridBagConstraints);

        tf_sfname.setFont(new Font("Arial", 0, 14));
        tf_sfname.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_sfname, gridBagConstraints);

        lbl_city.setFont(new Font("Arial", 0, 14));
        lbl_city.setText("City :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_city, gridBagConstraints);

        lbl_postcode.setFont(new Font("Arial", 0, 14));
        lbl_postcode.setText("Postcode :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_postcode, gridBagConstraints);

        lbl_company.setFont(new Font("Arial", 0, 14));
        lbl_company.setText("Company Name :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_company, gridBagConstraints);

        lbl_state.setFont(new Font("Arial", 0, 14));
        lbl_state.setText("State :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 2, 16);
        panel_sFill.add(lbl_state, gridBagConstraints);

        lbl_contact.setFont(new Font("Arial", 0, 14));
        lbl_contact.setText("Contact :");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0, 0, 0, 16);
        panel_sFill.add(lbl_contact, gridBagConstraints);

        tf_postcode.setFont(new Font("Arial", 0, 14));
        tf_postcode.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_postcode, gridBagConstraints);

        tf_city.setFont(new Font("Arial", 0, 14));
        tf_city.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_city, gridBagConstraints);

        tf_state.setFont(new Font("Arial", 0, 14));
        tf_state.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_state, gridBagConstraints);

        tf_contact.setFont(new Font("Arial", 0, 14));
        tf_contact.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        panel_sFill.add(tf_contact, gridBagConstraints);

        tf_company.setFont(new Font("Arial", 0, 14));
        tf_company.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_company, gridBagConstraints);

        tf_slname.setFont(new Font("Arial", 0, 14));
        tf_slname.setPreferredSize(new Dimension(200, 30));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(0, 0, 2, 0);
        panel_sFill.add(tf_slname, gridBagConstraints);

        panel_sButton.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        btn_sAdd.setText("Add");
        btn_sAdd.setPreferredSize(new Dimension(80, 30));
        panel_sButton.add(btn_sAdd);

        btn_sEdit.setText("Edit");
        btn_sEdit.setPreferredSize(new Dimension(80, 30));
        panel_sButton.add(btn_sEdit);

        btn_sDel.setText("Delete");
        btn_sDel.setPreferredSize(new Dimension(80, 30));
        panel_sButton.add(btn_sDel);

        table_supplier.setDefaultEditor(Object.class, null);
        table_supplier.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(table_supplier);

        panel_sButton1.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

        btn_sSwitch.setText("Switch");
        btn_sSwitch.setPreferredSize(new Dimension(80, 30));
        panel_sButton1.add(btn_sSwitch);

        btn_sCancel.setText("Cancel");
        btn_sCancel.setPreferredSize(new Dimension(80, 30));
        panel_sButton1.add(btn_sCancel);

        GroupLayout panel_SELayout = new GroupLayout(panel_SE);
        panel_SE.setLayout(panel_SELayout);
        panel_SELayout.setHorizontalGroup(
                panel_SELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel_SELayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_SELayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(panel_sFill, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                                        .addComponent(panel_sButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel_SELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panel_sButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                                .addContainerGap())
        );
        panel_SELayout.setVerticalGroup(
                panel_SELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel_SELayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_SELayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(panel_sFill, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_SELayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(panel_sButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(panel_sButton1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabPane.addTab("Supplier Entry", panel_SE);

        lbl_welcome.setFont(new Font("Arial", Font.BOLD, 18));
        lbl_welcome.setText("Welcome, FName!");

        lbl_role.setFont(new Font("Arial", Font.BOLD, 14));
        lbl_role.setText("Staff");

        lbl_id.setFont(new Font("Arial", Font.BOLD, 14));
        lbl_id.setText("12345");

        jLabel4.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel4.setText("Role :");

        jLabel5.setFont(new Font("Arial", Font.BOLD, 14));
        jLabel5.setText("ID :");

        jLabel1.setFont(new Font("Arial", Font.BOLD, 36));
        jLabel1.setForeground(new Color(0, 102, 102));
        jLabel1.setText("SHOPMART SDN BHD ®");
        jPanel1.add(jLabel1);

        menu_create.setText("Create...");

        menu_PR.setText("Purchase Requisition");
        menu_create.add(menu_PR);

        menu_PO.setText("Purchase Order");
        menu_create.add(menu_PO);

        menu_viewPR.setText("View Generated PR");
        menu_create.add(menu_viewPR);

        menu_LPO.setText("List of Purchase Orders");
        menu_create.add(menu_LPO);

        menuBar.add(menu_create);

        menu_add.setText("Add New User");
        menuBar.add(menu_add);

        menuBar.add(Box.createHorizontalGlue());

        menu_logout.setText("Logout");
        menuBar.add(menu_logout);

        setJMenuBar(menuBar);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTabPane, GroupLayout.PREFERRED_SIZE, 883, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lbl_welcome, GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbl_role, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(lbl_id, GroupLayout.Alignment.TRAILING))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(lbl_welcome)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbl_role)
                                                        .addComponent(jLabel4))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbl_id)
                                                        .addComponent(jLabel5))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(jTabPane, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE))
        );

        pack();

        iEnablement(false);

        sEnablement(false);

        mainFunctions();
    }

    //functions separated from initComponents, inclusive of listeners and other ui properties
    public void mainFunctions() {
        populateItems();
        populateSuppliers();
        populateCBox();

        lbl_welcome.setText("Welcome, " + LoginData.getLogin_FNAME() +
                "!");
        lbl_role.setText(LoginData.getLogin_ROLE());

        lbl_id.setText(LoginData.getLogin_EMPID());

        if (Objects.equals(lbl_role.getText(), "Manager")){
            menuBar.remove(menu_add);
        } else if (Objects.equals(lbl_role.getText(), "Staff")){
            menu_create.remove(menu_PO);
            menuBar.remove(menu_add);
        }

        //disable editing cells
        table_item.setDefaultEditor(Object.class, null);

        table_item.setAutoCreateRowSorter(true);
        table_supplier.setAutoCreateRowSorter(true);

        PlainDocument doc = (PlainDocument) tf_iPrice.getDocument();

        //uses Filter class created by me to verify inputs, then uses RegexType enums to get the regex strings
        doc.setDocumentFilter(new Filter(RegexType.PRICE.getRegexExp()));

        PlainDocument doc1 = (PlainDocument) tf_iCode.getDocument();
        PlainDocument doc2 = (PlainDocument) tf_iName.getDocument();
        PlainDocument doc3 = (PlainDocument) tf_sid.getDocument();
        PlainDocument doc4 = (PlainDocument) tf_sfname.getDocument();
        PlainDocument doc5 = (PlainDocument) tf_slname.getDocument();
        PlainDocument doc6 = (PlainDocument) tf_postcode.getDocument();

        doc1.setDocumentFilter(new Filter(RegexType.ALPHANUMERIC.getRegexExp()));
        doc2.setDocumentFilter(new Filter(RegexType.ALPHANUMSPACE.getRegexExp()));
        doc3.setDocumentFilter(new Filter(RegexType.ALPHANUMERIC.getRegexExp()));
        doc4.setDocumentFilter(new Filter(RegexType.ALPHANUMSPACE.getRegexExp()));
        doc5.setDocumentFilter(new Filter(RegexType.ALPHANUMSPACE.getRegexExp()));
        doc6.setDocumentFilter(new Filter(RegexType.ALPHANUMERIC.getRegexExp()));



        tf_iCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                new VerInput().keyTyped(e);
            }
        });


        btn_iSwitchable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                btn_iSwitchMouseClicked(me);
            }
        });

        btn_iAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_iAddMouseClicked(e);
            }
        });

        btn_iEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_iEditMouseClicked(e);
            }
        });

        btn_iCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_iCancelMouseClicked(e);
            }
        });

        btn_iDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_iDeleteMouseClicked(e);
            }
        });

        btn_sSwitch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_sSwitchMouseClicked(e);
            }
        });

        btn_sAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_sAddMouseClicked(e);
            }
        });

        btn_sEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_sEditMouseClicked(e);
            }
        });

        btn_sDel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_sDelMouseClicked(e);
            }
        });

        btn_sCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btn_sCancelMouseClicked(e);
            }
        });

        menu_add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu_addAction(e);
            }
        });

        //method referencing
        menu_PR.addActionListener(this::menu_PRAction);

        menu_viewPR.addActionListener(this::menu_viewPRAction);

        menu_PO.addActionListener(this::menu_POAction);

        menu_LPO.addActionListener(this::menu_LPOAction);

        menu_logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menu_logoutClick(e);
            }
        });
    }

    private void menu_logoutClick(MouseEvent e){
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        this.dispose();
        EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    private void menu_PRAction(ActionEvent e) {
        //lambda expression
        SwingUtilities.invokeLater(() -> new PurchaseReq().setVisible(true));
    }

    private void menu_POAction(ActionEvent e) {
        SwingUtilities.invokeLater(() -> new PurchaseOrder().setVisible(true));
    }

    private void menu_LPOAction(ActionEvent e) {
        SwingUtilities.invokeLater(() -> new POList().setVisible(true));
    }

    private void menu_viewPRAction(ActionEvent e) {
        SwingUtilities.invokeLater(() -> new PRView().setVisible(true));
    }

    private void menu_addAction(MouseEvent e) {
        SwingUtilities.invokeLater(() -> new AddUser().setVisible(true));
    }

    //for switchable button
    private void btn_iSwitchMouseClicked(MouseEvent e) {
        if (Objects.equals(btn_iSwitchable.getText(), "Add")) {

            int choice = JOptionPane.showConfirmDialog(this, "Add new item?", "Add Item", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION)
                return;
            else if (choice == JOptionPane.CLOSED_OPTION)
                return;

            query = "SELECT ItemCode FROM Item";
            DBConnection db = new DBConnection("DAT.ssb");

            try {
                ResultSet rs = db.executeQuery(query);

                while (rs.next()) {
                    if (Objects.equals(tf_iCode.getText(), rs.getString(1))) {
                        JOptionPane.showMessageDialog(this, "This ID is used by other items.",
                                "Add Unsuccessful", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                db.closeCon();
            }

            if (!Objects.equals(tf_iName.getText(), "") && !Objects.equals(tf_iPrice.getText(), "") &&
                    !Objects.equals(tf_iCode.getText(), "") && cbox_iSupplier.getSelectedIndex() != -1) {

                //may use SwingUtilities.invokeLater but i'll just stick with this?
                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        query = "INSERT INTO Item VALUES ('" + tf_iCode.getText() +
                                "','" + tf_iName.getText() +
                                "', " + Float.parseFloat(tf_iPrice.getText()) +
                                " ,'" + cbox_iSupplier.getSelectedItem().toString() + "');";

                        DBConnection db = new DBConnection("DAT.ssb");
                        try {
                            db.executeUpdate(query);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            itemsReInit(db);
                        }
                        return null;
                    }
                }.execute();

            } else {
                JOptionPane.showMessageDialog(this, "All fields must be entered, and a supplier must be chosen.",
                        "Add Item Unsuccessful", JOptionPane.WARNING_MESSAGE);
            }

        } else if (Objects.equals(btn_iSwitchable.getText(), "Edit")) {

            int choice = JOptionPane.showConfirmDialog(this, "Confirm edit?", "Edit Item", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION)
                return;
            else if (choice == JOptionPane.CLOSED_OPTION)
                return;

            if (!Objects.equals(tf_iName.getText(), "") && !Objects.equals(tf_iPrice.getText(), "") &&
                    !Objects.equals(tf_iCode.getText(), "") && cbox_iSupplier.getSelectedIndex() != -1) {

                new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() throws Exception {

                        DBConnection db = new DBConnection("DAT.ssb");
                        try {
                            query = "UPDATE Item SET ItemName = '" + tf_iName.getText() +
                                    "', UnitPrice = '" + Float.parseFloat(tf_iPrice.getText()) +
                                    "', SupplierID = '" + cbox_iSupplier.getSelectedItem().toString() +
                                    "' WHERE ItemCode = '" + tf_iCode.getText() + "'";

                            db.executeUpdate(query);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            itemsReInit(db);
                        }
                        return null;
                    }
                }.execute();

            } else {
                JOptionPane.showMessageDialog(this, "All fields must be entered, and a supplier must be chosen.",
                        "Edit Item Unsuccessful", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //for add button on the right
    private void btn_iAddMouseClicked(MouseEvent e) {
        iEnablement(true);
        iReset();
        btn_iSwitchable.setText("Add");
    }

    //for edit button on the right
    private void btn_iEditMouseClicked(MouseEvent e) {
        if (table_item.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Select one of the items in the item table to edit.",
                    "Edit", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table_item.getSelectedRow();
        String itemCode = (String) table_item.getModel().getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(this, "Edit Item ID: " + itemCode +
                "? If you want to change the Item Code, you are required to delete the item and " +
                "add the item again.", "Edit Item", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        iReset();
        iEnablement(true);
        tf_iCode.setEnabled(false);
        btn_iSwitchable.setText("Edit");

        query = "SELECT DISTINCT * FROM Item WHERE ItemCode = '" + itemCode + "'";

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    ResultSet rs = db.executeQuery(query);
                    if (rs.next()) {
                        tf_iCode.setText(rs.getString(1));
                        tf_iName.setText(rs.getString(2));
                        tf_iPrice.setText(String.format(Locale.UK, "%.2f", rs.getFloat(3)));
                        cbox_iSupplier.setSelectedItem(rs.getString(4));
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

    //for delete button on the right
    private void btn_iDeleteMouseClicked(MouseEvent e) {
        if (table_item.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Select one of the items in the item table to delete.",
                    "Delete", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table_item.getSelectedRow();
        String itemCode = (String) table_item.getModel().getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete" +
                " Item ID: " + itemCode + "? This action cannot be undone.", "Delete Item", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        if (Objects.equals(itemCode, tf_iCode.getText())) {
            JOptionPane.showMessageDialog(this, "This item is under editing! Please cancel editing to delete the " +
                    "selected item.", "Delete", JOptionPane.WARNING_MESSAGE);
            return;
        }

        query = "DELETE FROM Item WHERE ItemCode = '" + itemCode + "'";

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    db.executeUpdate(query);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } finally {
                    itemsReInit(db);
                }
                return null;
            }
        }.execute();

    }

    //for cancel button on the left
    private void btn_iCancelMouseClicked(MouseEvent e) {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        iReset();
        iEnablement(false);
    }

    //for switchable button on supplier's tab
    private void btn_sSwitchMouseClicked(MouseEvent e) {

        if (Objects.equals(btn_sSwitch.getText(), "Add")) {
            int choice = JOptionPane.showConfirmDialog(this, "Add new supplier?", "Add Supplier",
                    JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION)
                return;
            else if (choice == JOptionPane.CLOSED_OPTION)
                return;

            query = "SELECT SupplierID FROM Supplier";
            //create a new instance of DBConnection
            DBConnection db = new DBConnection("DAT.ssb");

            try {
                ResultSet rs = db.executeQuery(query);

                while (rs.next()) {
                    if (Objects.equals(tf_sid.getText(), rs.getString(1))) {
                        JOptionPane.showMessageDialog(this, "This ID is used by other supplier.",
                                "Add Unsuccessful", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } finally {
                db.closeCon(); //called closeCon method from DBConnection to close the connection
            }


            query = "INSERT INTO Supplier VALUES ('" + tf_sid.getText() +
                    "','" + tf_sfname.getText() + "','" + tf_slname.getText() +
                    "','" + tf_company.getText() + "','" + tf_address.getText() +
                    "','" + tf_postcode.getText() + "','" + tf_city.getText() +
                    "','" + tf_state.getText() + "', " + Long.parseLong(tf_contact.getText()) + ")";

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    DBConnection db = new DBConnection("DAT.ssb");
                    try {
                        db.executeUpdate(query);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } finally {
                        sReInit(db);
                    }
                    return null;
                }
            }.execute();

        } else if (Objects.equals(btn_sSwitch.getText(), "Edit")) {
            int choice = JOptionPane.showConfirmDialog(this, "Confirm edit?", "Edit Supplier", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION)
                return;
            else if (choice == JOptionPane.CLOSED_OPTION)
                return;

            new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    DBConnection db = new DBConnection("DAT.ssb");
                    query = "UPDATE Supplier SET SFName = '" + tf_sfname.getText() +
                            "', SLName = '" + tf_slname.getText() + "', CompanyName = '" + tf_company.getText() +
                            "', Address = '" + tf_address.getText() + "'," + "Postcode = '" + tf_postcode.getText() +
                            "', City = '" + tf_city.getText() + "', State = '" + tf_state.getText() +
                            "', Contact = '" + Long.parseLong(tf_contact.getText()) +
                            "' WHERE SupplierID = '" + tf_sid.getText() + "'";
                    try {
                        db.executeUpdate(query);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } finally {
                        sReInit(db);
                    }
                    return null;
                }
            }.execute();

        }
    }

    //for cancel button on supplier's tab
    private void btn_sCancelMouseClicked(MouseEvent e) {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        sReset();
        sEnablement(false);
    }

    //for add button on supplier's tab
    private void btn_sAddMouseClicked(MouseEvent e) {
        sEnablement(true);
        btn_sSwitchProperty();
        sReset();
        btn_sSwitch.setText("Add");
    }

    //for edit button on supplier's tab
    private void btn_sEditMouseClicked(MouseEvent e) {
        if (table_supplier.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Select one of the suppliers in the supplier table to edit.",
                    "Edit Supplier", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table_supplier.getSelectedRow();
        String supplierID = (String) table_supplier.getModel().getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(this, "Edit Supplier ID: " + supplierID +
                "? If you want to change the Supplier ID, you are required to delete the item and " +
                "add the item again.", "Edit Supplier", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        sReset();
        sEnablement(true);
        tf_sid.setEnabled(false);
        btn_sSwitch.setText("Edit");
        btn_sSwitchProperty();

        query = "SELECT DISTINCT * FROM Supplier WHERE SupplierID = '" + supplierID +
                "'";
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    ResultSet rs = db.executeQuery(query);
                    if (rs.next()) {
                        tf_sid.setText(rs.getString(1));
                        tf_sfname.setText(rs.getString(2));
                        tf_slname.setText(rs.getString(3));
                        tf_company.setText(rs.getString(4));
                        tf_address.setText(rs.getString(5));
                        tf_postcode.setText(rs.getString(6));
                        tf_city.setText(rs.getString(7));
                        tf_state.setText(rs.getString(8));
                        tf_contact.setText(String.valueOf(rs.getLong(9)));
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

    //for delete button on supplier's tab
    private void btn_sDelMouseClicked(MouseEvent e) {
        if (table_supplier.getSelectionModel().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Select one of the suppliers in the supplier table to delete.",
                    "Delete Supplier", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int row = table_supplier.getSelectedRow();
        String supplierID = (String) table_supplier.getModel().getValueAt(row, 0);

        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete" +
                        " Supplier ID: " + supplierID + "? This action cannot be undone.", "Delete Supplier",
                JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.NO_OPTION)
            return;
        else if (choice == JOptionPane.CLOSED_OPTION)
            return;

        if (Objects.equals(supplierID, tf_iCode.getText())) {
            JOptionPane.showMessageDialog(this, "This supplier is under editing! Please cancel editing to delete the " +
                    "selected supplier.", "Delete Supplier", JOptionPane.WARNING_MESSAGE);
            return;
        }

        query = "DELETE FROM Supplier WHERE SupplierID = '" + supplierID + "'";

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DBConnection db = new DBConnection("DAT.ssb");
                try {
                    db.executeUpdate(query);
                } finally {
                    sReInit(db);
                }
                return null;
            }
        }.execute();
    }

    private void btn_sSwitchProperty() {
        ButtonModel model = btn_sSwitch.getModel();
        Document doc1 = tf_sid.getDocument();
        Document doc2 = tf_sfname.getDocument();
        Document doc3 = tf_slname.getDocument();
        Document doc4 = tf_company.getDocument();
        Document doc5 = tf_address.getDocument();
        Document doc6 = tf_postcode.getDocument();
        Document doc7 = tf_city.getDocument();
        Document doc8 = tf_state.getDocument();
        Document doc9 = tf_contact.getDocument();

        BtnDisable disable = new BtnDisable(model);
        disable.addDoc(doc1);
        disable.addDoc(doc2);
        disable.addDoc(doc3);
        disable.addDoc(doc4);
        disable.addDoc(doc5);
        disable.addDoc(doc6);
        disable.addDoc(doc7);
        disable.addDoc(doc8);
        disable.addDoc(doc9);
    }

    //supplier cbox population
    private void populateCBox() {
        cbox_iSupplier.removeAllItems();
        DBConnection db = new DBConnection("DAT.ssb");
        query = "SELECT SupplierID FROM Supplier";
        new ExecuteCBox(db, query, cbox_iSupplier);
    }

    //how i apply my table model to table
    private void populateItems() {
        SwingUtilities.invokeLater(() -> {
            query = "SELECT * FROM Item";
            try {
                table_item.setModel(new FillTable().tbModel(query));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    //same goes to this table
    private void populateSuppliers() {
        SwingUtilities.invokeLater(() -> {
            query = "SELECT * FROM Supplier";
            try {
                table_supplier.setModel(new FillTable().tbModel(query));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    //re-initialise the ui (not really)
    private void itemsReInit(DBConnection db) {
        populateItems();
        iReset();
        iEnablement(false);
        db.closeCon();
    }

    private void sReInit(DBConnection db) {
        populateCBox();
        populateSuppliers();
        sReset();
        sEnablement(false);
        db.closeCon();
    }

    //iReset the values inside the iFill panel
    private void iReset() {
        tf_iCode.setText("");
        tf_iName.setText("");
        tf_iPrice.setText("");
        cbox_iSupplier.setSelectedIndex(-1);
    }

    private void sReset() {
        tf_sid.setText("");
        tf_sfname.setText("");
        tf_slname.setText("");
        tf_company.setText("");
        tf_address.setText("");
        tf_postcode.setText("");
        tf_city.setText("");
        tf_state.setText("");
        tf_contact.setText("");
    }

    //set manipulation components either to true or false
    private void iEnablement(Boolean b) {
        tf_iCode.setEnabled(b);
        tf_iName.setEnabled(b);
        tf_iPrice.setEnabled(b);
        cbox_iSupplier.setEnabled(b);

        btn_iSwitchable.setEnabled(b);
        btn_iCancel.setEnabled(b);
    }

    private void sEnablement(Boolean b) {
        tf_sid.setEnabled(b);
        tf_sfname.setEnabled(b);
        tf_slname.setEnabled(b);
        tf_address.setEnabled(b);
        tf_company.setEnabled(b);
        tf_postcode.setEnabled(b);
        tf_city.setEnabled(b);
        tf_state.setEnabled(b);
        tf_contact.setEnabled(b);

        btn_sSwitch.setEnabled(b);
        btn_sCancel.setEnabled(b);
    }


    private JButton btn_iAdd;
    private JButton btn_iCancel;
    private JButton btn_iDelete;
    private JButton btn_iEdit;
    private JButton btn_iSwitchable;
    private JButton btn_sAdd;
    private JButton btn_sCancel;
    private JButton btn_sDel;
    private JButton btn_sEdit;
    private JButton btn_sSwitch;
    private JComboBox<String> cbox_iSupplier;
    private JLabel jLabel1;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane3;
    private JTabbedPane jTabPane;
    private JLabel lbl_address;
    private JLabel lbl_city;
    private JLabel lbl_company;
    private JLabel lbl_contact;
    private JLabel lbl_iCode;
    private JLabel lbl_iName1;
    private JLabel lbl_iPrice;
    private JLabel lbl_iSupplier;
    private JLabel lbl_id;
    private JLabel lbl_postcode;
    private JLabel lbl_role;
    private JLabel lbl_sfname;
    private JLabel lbl_sid;
    private JLabel lbl_slname;
    private JLabel lbl_state;
    private JLabel lbl_welcome;
    private JMenuBar menuBar;
    private JMenuItem menu_LPO;
    private JMenuItem menu_PO;
    private JMenuItem menu_PR;
    private JMenuItem menu_viewPR;
    private JMenu menu_add;
    private JMenu menu_create;
    private JMenu menu_logout;
    private JPanel panel_IE;
    private JPanel panel_SE;
    private JPanel panel_iButton;
    private JPanel panel_iButton1;
    private JPanel panel_iFill;
    private JPanel panel_sButton;
    private JPanel panel_sButton1;
    private JPanel panel_sFill;
    private JTable table_item;
    private JTable table_supplier;
    private JTextField tf_address;
    private JTextField tf_city;
    private JTextField tf_company;
    private JTextField tf_contact;
    private JTextField tf_iCode;
    private JTextField tf_iName;
    private JTextField tf_iPrice;
    private JTextField tf_postcode;
    private JTextField tf_sfname;
    private JTextField tf_sid;
    private JTextField tf_slname;
    private JTextField tf_state;
    // End of variables declaration//GEN-END:variables
}
