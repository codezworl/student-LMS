package Admin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDB {
    public void insertupdateDeleteStudent(char operation, String id, String fname, String lname, String gender, String dob,
                                          String phone, String address) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");

            if (operation == 'i') {
                String query = "INSERT INTO student (Student_id, First_name, Last_name, Gender, DOB, Phone, Address) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, fname);
                preparedStatement.setString(3, lname);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, dob);
                preparedStatement.setString(6, phone);
                preparedStatement.setString(7, address);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new student was inserted successfully!");
                }
            }

            if (operation=='d'){
                String deleteQury="DELETE FROM student WHERE Student_id = ?";

                PreparedStatement deleteStatement = con.prepareStatement(deleteQury);
                deleteStatement.setString(1, id);
                int rowsDeleted = deleteStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null,"Student with ID: " + id + " was deleted successfully!");
                }

            }
            if (operation == 'u') {
                String updateQuery = "UPDATE student SET First_name = ?, Last_name = ?, Gender = ?, DOB = ?, Phone = ?, Address = ? WHERE Student_id = ?";

                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setString(1, fname);
                updateStatement.setString(2, lname);
                updateStatement.setString(3, gender);
                updateStatement.setString(4, dob);
                updateStatement.setString(5, phone);
                updateStatement.setString(6, address);
                updateStatement.setString(7, id);

                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Student with ID: " + id + " was updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No student found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }


            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
