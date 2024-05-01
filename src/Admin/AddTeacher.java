package Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AddTeacher extends JFrame {
    private JTextField idField, TName, phoneField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JFormattedTextField dobField;

    private JButton addButton,cancelButton;
    private JComboBox<String> CourseDropdown;

    public  AddTeacher(){
        setTitle("Add Teacher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(550, 450);
        setLocationRelativeTo(null);

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    private void initComponents() {

        idField = new JTextField();
        idField.setBounds(150, 20, 150, 20);

        TName = new JTextField();
        TName.setBounds(150, 60, 150, 20);


        phoneField = new JTextField();
        phoneField.setBounds(150, 140, 150, 20);

//        Subject=new JTextField();
//        Subject.setBounds(150, 100, 150, 20);

        CourseDropdown = new JComboBox<>();
        CourseDropdown.setBounds(150, 100, 150, 20);

      List<String> course = fetchCourseFromDatabase();
       for (String Courses : course) {
           CourseDropdown.addItem(Courses);
       }

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(150, 220, 80, 20);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(240, 220, 80, 20);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);


        addButton = new JButton("Add");
        addButton.setBounds(250, 290, 80, 30);

        cancelButton=new JButton("Go back");
        cancelButton.setBounds(150,290,80,30);


    }

    public    List<String>fetchCourseFromDatabase(){
        List<String> Course = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT  * FROM course");

            while (resultSet.next()){
                String CourseName=resultSet.getString("Course_name");
                Course.add(CourseName);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return Course;
    }

    private void addComponentsToFrame(){
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 20, 80, 20);
        String id;
        Random random=new Random();
        id= String.valueOf(random.nextInt(1000));
        idField.setText(id);
        add(idLabel);
        add(idField);

        JLabel name=new JLabel("Name");
        name.setBounds(50,60,80,20);
        add(name);
        add(TName);

        JLabel Addsub = new JLabel("Subject:");
        Addsub.setBounds(50, 100, 80, 20);
        add(Addsub);
        add(CourseDropdown);



        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 140, 80, 20);
        add(phoneLabel);
        add(phoneField);


        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 220, 80, 20);
        add(genderLabel);
        add(maleRadioButton);
        add(femaleRadioButton);


        add(addButton);
        add(cancelButton);

      cancelButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              new AdminPanel();
              dispose();
          }
      });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String id=idField.getText();
                    String name =TName.getText();
                    String sub= (String) CourseDropdown.getSelectedItem();
                    String gender = maleRadioButton.isSelected() ? "Male" : "Female";
                    String phone=phoneField.getText();

                    TeacherDB db=new TeacherDB();
                    db.insertupdateDeleteTeacher('i',id,name,sub,gender,phone);

                    UserDB std=new UserDB();
                    std.InsertUser(id,name,"Teacher");

                    JOptionPane.showMessageDialog(null, "Teacher added successfully!");

                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID.");
                }
            }
        });
    }



 public  static  void  main(String[] args){
       new AddTeacher();
 }

}
