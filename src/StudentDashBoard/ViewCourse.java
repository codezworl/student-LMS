package StudentDashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewCourse extends JFrame {

    JLabel nameLabel;
    JLabel courseLabel;
    JLabel teacherLabel;
    JLabel creditLabel;



    ViewCourse() {
        setTitle("View Course");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);

        init();

        setVisible(true);
    }

    private void init() {
        nameLabel = new JLabel("Your id : ");
        courseLabel = new JLabel("Course Name: ");
        teacherLabel = new JLabel("Teacher Name: ");
        creditLabel = new JLabel("Credit Hours: ");

//        nameLabel.setBounds(20, 10, 300, 25);
//        courseLabel.setBounds(20, 35, 300, 25);
//        teacherLabel.setBounds(20, 60, 300, 25);
//        creditLabel.setBounds(20, 90, 300, 25);



        // Add labels to the frame
//        add(nameLabel);
//        add(courseLabel);
//        add(teacherLabel);
//        add(creditLabel);
        JButton backbtn=new JButton("Go Back");
        backbtn.setBounds(250,500,100,25);
        add(backbtn);
        backbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void FetchdataFromEnrolledCourse(String studentId) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM enrolled_courses WHERE Student_id = ?");
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();

            int index = 0;

            while (resultSet.next()) {
                JPanel panel = new JPanel();

                panel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                panel.setLayout(new GridLayout(4, 1));

                JLabel idLabel = new JLabel("Your id: " + resultSet.getInt("Student_id"));
                JLabel courseLabel = new JLabel("Course Name: " + resultSet.getString("Course_name"));
                JLabel teacherLabel = new JLabel("Teacher Name: " + resultSet.getString("Teacher_name"));
                JLabel creditLabel = new JLabel("Credit Hours: " + resultSet.getInt("Credit_hr"));

                panel.add(idLabel);
                panel.add(courseLabel);
                panel.add(teacherLabel);
                panel.add(creditLabel);

                index++;
                panel.setBounds(50, index * 120, 500, 100);
                add(panel);
            }


        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new ViewCourse();
    }
}
