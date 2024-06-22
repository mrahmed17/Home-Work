package dbcrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;

public class DBCrud {

    static PreparedStatement ps;
    static ResultSet rs;

    public static void main(String[] args) {
        Util db = new Util();

        String insertSql = "insert into student (name, email, address, contact)" + "values(?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(insertSql);

            ps.setString(1, "Raju");
            ps.setString(2, "Raju@gmail.com");
            ps.setString(3, "Azimpur, Dhaka");
            ps.setString(4, "6546542");

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

        String selectSql = "select * from student";

        try {
            ps = db.getCon().prepareStatement(selectSql);
            
            rs = ps.executeQuery();
            
            while(rs.next()){
            int id= rs.getInt("id");
            String name= rs.getString("Name");
            String email= rs.getString("email");
            String address= rs.getString("address");
            String contact= rs.getString("contact");
                        
            System.out.println("I'd: " +id+"\nName: "+name+"\nEmail: "+email+"\nAddress: "+address+"\nContact: "+contact);
            }
            
            ps.close();
            rs.close();
            db.getCon().close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
