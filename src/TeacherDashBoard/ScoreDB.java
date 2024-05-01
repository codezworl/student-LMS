package TeacherDashBoard;

import java.sql.*;

public class ScoreDB {

    public  void InstertUpdateScore(char operation,int id,String CourseName,Float Score,String Description){

        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");

            if(operation=='i'){
                PreparedStatement statement=connection.prepareStatement("INSERT into score (Student_id,Course_name,Student_Score,description) VALUES (?,?,?,?)");

                statement.setInt(1,id);
                statement.setString(2,CourseName);
                statement.setFloat(3,Score);
                statement.setString(4,Description);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Score Added successfully!");
                }

            }

        }
        catch (Exception ex){
//            ex.printStackTrace();
            System.out.println("SQL error");
        }
    }

}
