package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManageTeacher {
    private JTextField idField, TName, phoneField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JFormattedTextField dobField;

    private JComboBox<String> CourseDropdown;


    private JButton addButton,deletebtn,updatebtn,backbtn;

    private JFrame frame;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable teacherTable;




    public ManageTeacher() {

        frame = new JFrame("Manage Teacher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150, 700); // Set your desired size here
        frame.setLayout(null);

        initComponents();
        fillTable();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void  initComponents(){

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 20, 80, 20);
        idField = new JTextField();
        idField.setBounds(150, 20, 150, 20);
        frame.add(idLabel);
        frame.add(idField);

        JLabel Name = new JLabel(" Name:");
        Name.setBounds(50, 60, 80, 20);
        TName = new JTextField();
        TName.setBounds(150, 60, 150, 20);
        frame.add(Name);
        frame.add(TName);


        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 140, 80, 20);
        phoneField = new JTextField();
        phoneField.setBounds(150, 140, 150, 20);
        frame.add(phoneLabel);
        frame.add(phoneField);



        JLabel sub=new JLabel("Subject");
        sub.setBounds(50,100,80,20);

        CourseDropdown = new JComboBox<>();
        CourseDropdown.setBounds(150, 100, 150, 20);

        frame.add(CourseDropdown);
        frame.add(sub);

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

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 220, 80, 20);
        frame.add(genderLabel);
        frame.add(maleRadioButton);
        frame.add(femaleRadioButton);

        model = new DefaultTableModel();
        teacherTable = new JTable(model);
        scrollPane = new JScrollPane(teacherTable);
        scrollPane.setBounds(400, 60, 600, 500);
        frame.add(scrollPane);


        addButton = new JButton("Add");
        addButton.setBounds(260, 400, 80, 30);
        frame.add(addButton);

        updatebtn = new JButton("Update");
        updatebtn.setBounds(60, 400, 80, 30);
        frame.add(updatebtn);

        deletebtn = new JButton("Delete");
        deletebtn.setBounds(160, 400, 80, 30);
        frame.add(deletebtn);


        backbtn=new JButton("Go back");
        backbtn.setBounds(60,360,80,30);
        frame.add(backbtn);



        updatebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String id=idLabel.getText();
                    String Name=TName.getText();
                    String Phone=phoneField.getText();
                    String sub=(String) CourseDropdown.getSelectedItem();
                    String gender = maleRadioButton.isSelected() ? "Male" : "Female";

                    TeacherDB db=new TeacherDB();
                    db.insertupdateDeleteTeacher('u',id,Name,sub,gender,Phone);
                    frame.dispose();
                   new ManageTeacher();

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });
        deletebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String id = idField.getText();
                    TeacherDB db=new TeacherDB();
                    db.insertupdateDeleteTeacher('d',id,null,null,null,null);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }



            }
        });



        backbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AdminPanel();
                frame.dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String id=idField.getText();
                    String name=TName.getText();
                    String sub=(String) CourseDropdown.getSelectedItem();
                    String phn=phoneField.getText();
                    String g = maleRadioButton.isSelected() ? "Male" : "Female";

                    TeacherDB db=new TeacherDB();
                    db.insertupdateDeleteTeacher('i',id,name,sub,g,phn);

                }
                catch (Exception ex){
                    ex.printStackTrace();
                }




            }
        });



    }
    public  List<String>fetchCourseFromDatabase(){
        List<String> Course = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT  * FROM course");

            while (resultSet.next()){
                String CourseName=resultSet.getString("Course_Name");
                Course.add(CourseName);
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return Course;
    }


    public void fillTable(){
        model.addColumn("Teacher_id");
        model.addColumn("Teacher_Name");
        model.addColumn("Subject allot");
        model.addColumn("Gender");
        model.addColumn("Phone");

       try {
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root", "2000");
           Statement statement = con.createStatement();
           ResultSet resultSet = statement.executeQuery("select * from teacher");

           while (resultSet.next()){

               Object[] rowData = new Object[5];
               rowData[0] = resultSet.getString("Teacher_id");
               rowData[1] = resultSet.getString("Name");
               rowData[2] = resultSet.getString("Subject");
               rowData[3] = resultSet.getString("Gender");
               rowData[4] = resultSet.getString("Phone");

               model.addRow(rowData);

           }

       }
       catch (Exception ex){
           ex.printStackTrace();
       }

    }





    public  static void  main(String[] args){
        new ManageTeacher();
    }
}
