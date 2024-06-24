package dbcrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBUtil;

public class DBCrud {

    static DBUtil db = new DBUtil();
    static PreparedStatement ps;
    static ResultSet rs;
    static String sql = "";

    public static void main(String[] args) {

//        insertStd("Raju", "raju@gmail.com", "Azimpur", "123456789");
//        System.out.println("\n After Insert Data \n");
//        showStd();
//        deleteStd();
//        System.out.println("\n After Delete Data \n");
//        showStd();
        editStd("Raju", "raju@gmail.com", "Azimpur", "9875650654", 2);
        editStd("Mostafa", "mostafa@gmail.com", "Azimpur", "25575650654", 3);
        editStd("Nusrat", "nusrat@gmail.com", "Mirpur", "4654650654", 4);
        editStd("Shabab", "shabab@gmail.com", "Dhanmondi", "5465450654", 5);
        System.out.println("\n After Edit Data \n");
        showStd();

    }

    public static void insertStd(String name, String email, String address, String cell) {
        sql = "insert into student (name, email, address, cell) values(?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setString(4, cell);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void showStd() {
        sql = "select * from student";

        try {
            ps = db.getCon().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cell = rs.getString("cell");
                String id = rs.getString("id");

                System.out.println("ID No: " + id + "\tName: " + name + "\tEmail: " + email + "\tAddress: " + address + "\tCell No: " + cell);
            }

            ps.close();
            rs.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteStd(int id) {
        sql = "delete from student where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void editStd(String name, String email, String address, String cell, int id) {
        sql = "update student set name=?, email=?, address=?, cell=? where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setString(4, cell);
            ps.setInt(5, id);

            ps.executeUpdate();
            ps.close();
            db.getCon().close();

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

//Output
//run:
//
// After Insert Data 
//
//ID No: 1	Name: Raju	Email: raju@gmail.com	Address: Azimpur	Cell No: 123456789
//Jun 24, 2024 12:33:12 PM dbcrud.DBCrud showStd
//SEVERE: null
//java.sql.SQLException: Can not issue executeUpdate() or executeLargeUpdate() with statements that produce result sets
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:130)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:98)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:90)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:64)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1038)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1009)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeLargeUpdate(ClientPreparedStatement.java:1320)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdate(ClientPreparedStatement.java:994)
//	at dbcrud.DBCrud.showStd(DBCrud.java:68)
//	at dbcrud.DBCrud.main(DBCrud.java:20)
//
//
// After Delete Data 
//
//ID No: 2	Name: Raju	Email: raju@gmail.com	Address: Azimpur	Cell No: 123456789
//Jun 24, 2024 12:33:12 PM dbcrud.DBCrud showStd
//SEVERE: null
//java.sql.SQLException: Can not issue executeUpdate() or executeLargeUpdate() with statements that produce result sets
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:130)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:98)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:90)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:64)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1038)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1009)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeLargeUpdate(ClientPreparedStatement.java:1320)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdate(ClientPreparedStatement.java:994)
//	at dbcrud.DBCrud.showStd(DBCrud.java:68)
//	at dbcrud.DBCrud.main(DBCrud.java:23)
//
//
// After Edit Data 
//
//ID No: 2	Name: Raju Ahmed	Email: rajuahmed@gmail.com	Address: Azimpur	Cell No: 9875650654
//Jun 24, 2024 12:33:12 PM dbcrud.DBCrud showStd
//SEVERE: null
//java.sql.SQLException: Can not issue executeUpdate() or executeLargeUpdate() with statements that produce result sets
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:130)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:98)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:90)
//	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:64)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1038)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1009)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeLargeUpdate(ClientPreparedStatement.java:1320)
//	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdate(ClientPreparedStatement.java:994)
//	at dbcrud.DBCrud.showStd(DBCrud.java:68)
//	at dbcrud.DBCrud.main(DBCrud.java:26)
