package dbcrud2;

import entity.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBUtil;

public class DBCrud2 {

    static DBUtil db = new DBUtil();
    static PreparedStatement ps;
    static ResultSet rs;
    static String sql = "";

    public static void main(String[] args) {
        Student student = new Student();

        List<Student> studentList = getById(1);

        //   For Data Edit
        student.setName("Raju");
        student.setEmail("raju@gmail.com");
        student.setAddress("Azimpur");
        student.setCell("0123456789");
        student.setId(studentList.get(0).getId()); //Id will be auto increment 

//        saveData(); // for add new data
//        System.out.println("\n After Insert Data \n");
        showData();

//        editData(student); // for edit data
//        System.out.println("\n After Edit Data \n");
        showData();

//        deleteData(2);
//        System.out.println("\n After Delete Data \n");
        showData(); // show data in console

        //      Data Editable Sample Format
//        student.setName(studentList.get(0).getName());
//        student.setEmail(studentList.get(0).getEmail());
//        student.setAddress(studentList.get(0).getAddress());
//        student.setCell(studentList.get(0).getCell());
//        student.setId(studentList.get(0).getId());
    }

    public static void saveData() {
        sql = " insert into student (name, email, address, cell) values(?,?,?,?)";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, "Any Name");
            ps.setString(2, "Any Email");
            ps.setString(3, "Any Address");
            ps.setString(4, "Any Cell");

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            System.out.println("Data Insert Successful");

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void showData() {
        sql = "select * from student";

        try {
            ps = db.getCon().prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String cell = rs.getString("cell");

                System.out.println("ID No: " + id + "\tName: " + name + "\tEmail: " + email + "\tAddress: " + address + "\tCell: " + cell);
            }

            ps.close();
            rs.close();
            db.getCon().close();

            System.out.println("Update Data Successful");

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void editData(Student s) {
        sql = "update student set name=?, email=?, address=?, cell=? where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getAddress());
            ps.setString(4, s.getCell());
            ps.setInt(5, s.getId());

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            System.out.println("Edit Data Successful");

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void deleteData(int id) {
        sql = "delete from student where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            ps.close();
            db.getCon().close();

            System.out.println("Delete Data Successful");

        } catch (SQLException ex) {
            Logger.getLogger(DBCrud2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<Student> getById(int id) {
        List<Student> studentList = new ArrayList<>();

        sql = "select * from student where id=?";

        try {
            ps = db.getCon().prepareStatement(sql);

            ps.setInt(1, id);

            rs = ps.executeQuery();

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("cell")
                );
                studentList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBCrud2.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentList;

    }

}
