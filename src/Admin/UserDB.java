package Admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDB {

    public void InsertUser(String id, String Name,String Role){

        try {

            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");
            String query = "INSERT INTO user (user_name, password, Role) " +
                    "VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1,Name);
            preparedStatement.setString(2,id);
            preparedStatement.setString(3,Role);
            preparedStatement.executeUpdate();

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
