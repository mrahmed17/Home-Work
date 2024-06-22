package jdbcinoneclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCInOneClass {

    private static Connection con = null;
    private static String url = "jdbc:mysql://localhost:3306/testjdbc";
    private static String user = "root";
    private static String password = "1234";
    private static String driver = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {

        PreparedStatement ps;
        ResultSet rs;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);

            String selectSql = "select * from employee";

            ps = con.prepareStatement(selectSql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String contact = rs.getString("contact");

                System.out.println("I'd: " + id + "\nName: " + name + "\nEmail: " + email + "\nAddress: " + address + "\nContact: " + contact);
            }

        } catch (ClassNotFoundException | SQLException ex) {

            System.out.println("Driver not found");
            System.out.println("SQL Exception" + ex.getMessage());

            Logger.getLogger(JDBCInOneClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
