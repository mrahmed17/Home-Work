package databaseconnection;

import dbutil.DbUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    static PreparedStatement ps;
    static ResultSet rs;

    public static void main(String[] args) {
        DbUtil db = new DbUtil();

        String insertSql = "insert into employee (name,email,address,cell)" + "value(?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(insertSql);

            ps.setString(1, "Raju");
            ps.setString(2, "Raju@gmail.com");
            ps.setString(3, "Azimpur, Dhaka");
            ps.setString(4, "561464565");

            ps.executeUpdate();
            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        String selectSql = "select * from employee";

        try {
            ps = db.getCon().prepareStatement(selectSql);

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cell = rs.getString("cell");

                System.out.println("ID is: " + id + " Name is: " + name + " Email is: " + email + " Address is: " + address + " Cell No is: " + cell);

            }

            ps.close();
            rs.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
