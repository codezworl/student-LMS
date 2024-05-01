package TeacherDashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAlocatedCourse extends JFrame {

    private JLabel idField, courseField,nameField;
    private JButton BackButton;





    public ViewAlocatedCourse() {
        setTitle("Teacher Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 200);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel(null);

        JLabel idLabel = new JLabel("Your  ID:");
        idField = new JLabel();
        idLabel.setBounds(10, 10, 100, 25);
        idField.setBounds(120, 10, 150, 25);

        JLabel nameLabel = new JLabel("Your Login Name:");
        nameField = new JLabel();
        nameLabel.setBounds(10, 40, 100, 25);
        nameField.setBounds(120, 40, 150, 25);

        JLabel courseLabel = new JLabel("Allocated Course To you:");
        courseField = new JLabel();
        courseLabel.setBounds(10, 70, 100, 25);
        courseField.setBounds(120, 70, 150, 25);

        BackButton = new JButton("Go Back");
        BackButton.setBounds(100, 110, 100, 30);

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(courseLabel);
        inputPanel.add(courseField);
        inputPanel.add(BackButton);

        add(inputPanel);

        BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


    }


    public void retrieveTeacherData(String id) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM teacher WHERE Teacher_id = ?")) {
            statement.setString(1, String.valueOf(id));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int teacherID = resultSet.getInt("Teacher_id");
                    String teacherName = resultSet.getString("Name");
                    String subject = resultSet.getString("Subject");

                    idField.setText(String.valueOf(teacherID));
                    nameField.setText(teacherName);
                    courseField.setText(subject);

                }
            }
        }
        catch (Exception ex) {
           JOptionPane.showMessageDialog(null,"No data Present");
        }
    }






    public static void main(String[] args) {
        new ViewAlocatedCourse();
    }
}
