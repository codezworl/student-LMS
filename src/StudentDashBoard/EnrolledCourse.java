package StudentDashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EnrolledCourse extends JFrame {

    public String Studentid;
    public String Studentname;

    public  EnrolledCourse() {
        setTitle("Enrolled Course");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);
        setLocationRelativeTo(null);

        retrieveCourseData();

        setVisible(true);
    }

    public void addCourseBox(int courseId, String courseName, int creditHrs, String teacherName, int index) {

        JPanel coursePanel = new JPanel(null);
        coursePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        JLabel nameLabel = new JLabel("Teacher Name: " + teacherName);
        JLabel courseid = new JLabel("course id: " + courseId);
        JLabel courseLabel = new JLabel("Course Name :"+courseName);
        JLabel creditLabel = new JLabel("Credit Hours: " + creditHrs);
        JButton enrollButton = new JButton("Enroll");
        JButton backbtn=new JButton("Go back");

        nameLabel.setBounds(20, 10, 300, 25);
        courseLabel.setBounds(20, 35, 300, 25);
        courseid.setBounds(20,80,300,25);
        creditLabel.setBounds(20, 60, 300, 25);
        enrollButton.setBounds(350, 25, 100, 30);
//          backbtn.setLayout(S);
        backbtn.setBounds(250, 500, 100, 30);

        enrollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(EnrolledCourse.this, "Enroll in " + courseName + " Button Clicked!");

                EnrolledCourseDB en=new EnrolledCourseDB();
                en.InsterIntoEnrolledCourse(Studentid,Studentname,courseName,teacherName,creditHrs);
            }
        });
//        backbtn.addActionListener(new);
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        coursePanel.add(nameLabel);
        coursePanel.add(courseLabel);
        coursePanel.add(creditLabel);
        coursePanel.add(enrollButton);
        coursePanel.add(courseid);
//        add();

        coursePanel.setBounds(50,  index*120, 500, 120);
        add(coursePanel);
        add(backbtn);
    }

    public void retrieveCourseData() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");

            String query = "SELECT c.Course_id, c.Course_name, c.Credit_hrs, t.Name, t.Subject " +
                    "FROM course c " +
                    "JOIN teacher t ON c.Course_name = t.Subject";

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            int index = 0;
            while (resultSet.next()) {
                int courseId = resultSet.getInt("Course_id");
                String courseName = resultSet.getString("Course_name");
                int creditHours = resultSet.getInt("Credit_hrs");
                String teacherName = resultSet.getString("Name");

                addCourseBox(courseId, courseName, creditHours, teacherName, index++);
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
         new EnrolledCourse();
    }
}
