package Admin;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CourseDB {


      public void insertUpdateDeleteCourse(char operation ,int id, String CName,int Crhrs){

          try {
              Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");

             if (operation=='i'){
                 String Qurrey="INSERT INTO course (Course_id, Course_name, Credit_hrs) " +
                         "VALUES (?, ?, ?)";
                 PreparedStatement preparedStatement=connection.prepareStatement(Qurrey);

                 preparedStatement.setInt(1,id);
                 preparedStatement.setString(2,CName);
                 preparedStatement.setInt(3,Crhrs);

                 int rowsInserted = preparedStatement.executeUpdate();
                 if (rowsInserted > 0) {
                     System.out.println("A new Course was inserted successfully!");
                 }
             }
             if (operation=='d'){
                   String deleteQurrey="DELETE FROM course WHERE Course_id=?";

                   PreparedStatement deleteStatment=connection.prepareStatement(deleteQurrey);
                 deleteStatment.setInt(1,id);

                 int rowsDeleted = deleteStatment.executeUpdate();

                 if (rowsDeleted > 0) {
                     JOptionPane.showMessageDialog(null,"Course with ID: " + id + " was deleted successfully!");
                 }
             }
             if (operation=='u'){
                 String updateQuery = "UPDATE course SET  Course_name = ? , Credit_hrs = ? WHERE Course_id = ?";
                 PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                 updateStatement.setString(1,CName);
                 updateStatement.setInt(2,Crhrs);
                 updateStatement.setInt(3,id);

                 int rowsUpdated = updateStatement.executeUpdate();
                 if (rowsUpdated > 0) {
                     JOptionPane.showMessageDialog(null, "Course with ID: " + id + " was updated successfully!");
                 } else {
                     JOptionPane.showMessageDialog(null, "No student found with ID: " + id, "Error", JOptionPane.ERROR_MESSAGE);
                 }

             }
          }
          catch (Exception ex){
              ex.printStackTrace();
          }

      }
}
