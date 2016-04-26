package sys.ctrl;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
Have to do this to prevent any highlighted duplications on goddamn IntelliJ
As well as maintainable
 */
public class ExecuteCBox {
    private DBConnection db;
    private String query;
    private JComboBox<String> comboBox;

    public ExecuteCBox(DBConnection db, String query, JComboBox<String> comboBox){
        this.db = db;
        this.query = query;
        this.comboBox = comboBox;

        executeCBox();
    }

    private void executeCBox(){
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                comboBox.addItem(rs.getString(1));
            }
            comboBox.setSelectedIndex(-1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeCon();
        }
    }
}
