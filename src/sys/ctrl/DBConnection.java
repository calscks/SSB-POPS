package sys.ctrl;

import java.sql.*;

/**
 * @author CK Seong
 */

public class DBConnection {
    private String dbName;
    private Connection c = null;
    private Statement statement = null;

    
    //a simple DBConnection that can be used easily
    public DBConnection(String dbName) {
        this.dbName = dbName;

        try {
            setConnection();
        } catch (SQLException | ClassNotFoundException |InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setConnection() throws SQLException, ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        //language=SQLite
        String[] create = new String[]{
                "CREATE TABLE IF NOT EXISTS Employee\n" +
                        "(\n" +
                        "    EmpID INTEGER PRIMARY KEY,\n" +
                        "    UName TEXT,\n" +
                        "    Pwd TEXT,\n" +
                        "    FName TEXT,\n" +
                        "    LName TEXT,\n" +
                        "    Phone TEXT,\n" +
                        "    Role TEXT\n" +
                        ");" ,
                "CREATE TABLE IF NOT EXISTS Item\n" +
                        "(\n" +
                        "    ItemCode TEXT PRIMARY KEY,\n" +
                        "    ItemName TEXT,\n" +
                        "    UnitPrice REAL,\n" +
                        "    SupplierID TEXT\n" +
                        ");" ,
                "CREATE TABLE IF NOT EXISTS PurchaseOrder\n" +
                        "(\n" +
                        "    PONo INTEGER PRIMARY KEY,\n" +
                        "    PRNo INTEGER,\n" +
                        "    PODate TEXT,\n" +
                        "    Total REAL\n" +
                        ");" ,
                "CREATE TABLE IF NOT EXISTS PurchaseReq\n" +
                        "(\n" +
                        "    PRNo INTEGER,\n" +
                        "    EmpID INTEGER,\n" +
                        "    ItemCode TEXT,\n" +
                        "    ReqDate TEXT,\n" +
                        "    Quantity INTEGER,\n" +
                        "    Amount REAL,\n" +
                        "    ORNo INTEGER\n" +
                        ");" ,
                "CREATE TABLE IF NOT EXISTS Supplier\n" +
                        "(\n" +
                        "    SupplierID TEXT PRIMARY KEY,\n" +
                        "    SFName TEXT,\n" +
                        "    SLName TEXT,\n" +
                        "    CompanyName TEXT,\n" +
                        "    Address TEXT,\n" +
                        "    Postcode TEXT,\n" +
                        "    City TEXT,\n" +
                        "    State TEXT,\n" +
                        "    Contact INTEGER\n" +
                        ");"
        };

        Class.forName("org.sqlite.JDBC").newInstance();
        c = DriverManager.getConnection("jdbc:sqlite:" + dbName);

        DatabaseMetaData meta = c.getMetaData();
        ResultSet chkTable = meta.getTables(null, null, "Employee", new String[]{"TABLE"});
        if (chkTable.next()) {
            System.out.println("Db exists");
        } else {
            System.out.println("Does not exists, creating a new db..");
            System.out.print("Does not exists, creating a new db");
            for (String aCreate : create) { //loop the string[] create array
                PreparedStatement tableQuery = c.prepareStatement(aCreate);
                tableQuery.executeUpdate();
            }
            String query = "INSERT INTO Employee VALUES (10001, 'admin', 'admin', 'Admin', 'Admin', NULL, " +
                    "'Admin')";
            Statement another = c.createStatement();
            another.executeUpdate(query);
            another.close();
        }

        try {
            statement = c.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    //close the connection, after queries/instructions execution this method is highly recommended to be called
    public void closeCon() {
        if (statement != null) try {
            statement.close();
            System.out.println("Statement closed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (c != null) {
            try {
                c.close();
                System.out.println("con closed.\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //execute mainly/only for SELECT clause
    public ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    //execute for CREATE, INSERT, UPDATE, DELETE clause, for DDL, and 3 DMLs that affect data in db
    public void executeUpdate(String query) throws SQLException {
        statement.executeUpdate(query);
    }
}
