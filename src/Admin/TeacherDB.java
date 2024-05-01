package Admin;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherDB {
    public void insertupdateDeleteTeacher(char operation, String id, String name, String subject, String gender,String phone) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");

            if (operation == 'i') {
                String query = "INSERT INTO teacher (Teacher_id, Name, Subject, Gender, Phone) " +
                        "VALUES (?, ?, ?, ?, ?)";


                PreparedStatement preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, subject);
                preparedStatement.setString(4, gender);
                preparedStatement.setString(5, phone);

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new Teacher was inserted successfully!");
                }
            }

            if (operation=='d'){
                String deleteQury="DELETE FROM teacher WHERE Teacher_id = ?";
                PreparedStatement deleteStatement = con.prepareStatement(deleteQury);
                deleteStatement.setString(1, id);
                int rowsDeleted = deleteStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null,"Teacher with ID: " + id + " was deleted successfully!");
                }


            }
            if(operation=='u'){
                String updateQuery = "UPDATE teacher SET Name = ?, Subject = ?, Gender = ?,  Phone = ? WHERE Teacher_id = ?";
                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
                updateStatement.setString(1,name);
                updateStatement.setString(2,subject);
                updateStatement.setString(3,gender);
                updateStatement.setString(4,phone);
                updateStatement.setString(5,id);

                int rowsUpdated = updateStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(null, "Teacher with ID: " + id + " was updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "No Teacher found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
