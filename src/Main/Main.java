package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


import StudentDashBoard.StudentPanel;
import TeacherDashBoard.TeacherModule;
import Admin.AdminPanel;
public class Main {

    public static void main(String[] args) {
        createAndShowGUI();
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Welcome To LMS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.darkGray);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(40, 70, 100, 20);
        JTextField usernameField = new JTextField();
        usernameField.setBounds(150, 70, 150, 20);
        usernameLabel.setForeground(Color.white);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(40, 100, 100, 20);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 20);
        passwordLabel.setForeground(Color.white);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 120, 30);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Tahoma",Font.BOLD,16));

//        JButton registerButton = new JButton("Forget Password");
//        registerButton.setBounds(180, 140, 120, 30);
//        registerButton.setBackground(Color.BLACK);
//        registerButton.setForeground(Color.WHITE);
//        registerButton.setFont(new Font("Tahoma",Font.BOLD,16));


        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/login.jpg"));
        Image i2=i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(350,20,200,200);


        frame.add(image);
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
//        frame.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root", "2000");
                    String query = "SELECT * FROM user WHERE user_name = ? AND password = ?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    ResultSet resultSet = preparedStatement.executeQuery();


                    if (resultSet.next()) {


                            if (resultSet.getString("Role").equals("Admin")) {
                                new AdminPanel();
                                frame.dispose();
                            }
                            else if (resultSet.getString("Role").equals("Student")) {

                                StudentPanel S=new StudentPanel();
                                  S.SLable.setText("Welcome " + username+" ");
                                  S.SetStudentId(password);
                                  S.Stdname=username;

                            }
                            else if (resultSet.getString("Role").equals("Teacher")) {
                                TeacherModule T=new TeacherModule();
                                T.TLable.setText("Welcome " + username +".");
//                                T.SetTpass=Integer.parseInt(password);
                                T.SetTpass=password;
                            }

                        connection.close();
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Invalid credentials. Try again.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: Unable to connect to the database.");
                }
            }
        });


        frame.setVisible(true);
    }
}
