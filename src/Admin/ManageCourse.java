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

public class ManageCourse {
    private JTextField idField, CName;
    private JSpinner jSpinner_courseHours;
    private JButton addButton,deletebtn,updatebtn,backbtn;


    private JFrame frame;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable CourseTable;

   public ManageCourse(){

       frame = new JFrame("Manage Course");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(1150, 700); // Set your desired size here
       frame.setLayout(null);

       initComponents();
       fillTable();

       frame.setLocationRelativeTo(null);
       frame.setVisible(true);

   }
   public  void initComponents(){

       JLabel jLabel1 = new JLabel("Manage Course");
       jLabel1.setFont(new java.awt.Font("Courier New", 2, 20));
       jLabel1.setBounds(140, 40, 200, 40);
       frame.add(jLabel1);

       JLabel CourseName = new JLabel("Course id");
       CourseName.setFont(new java.awt.Font("Courier New", 1, 15));
       CourseName.setBounds(50, 120, 100, 30);
       frame.add(CourseName);

       JLabel Name = new JLabel("Course Name:");
       Name.setBounds(50, 190, 100, 30);
       frame.add(Name);

       JLabel Hours = new JLabel("Hours:");
       Hours.setFont(new java.awt.Font("Courier New", 1, 15));
       Hours.setBounds(50, 320, 100, 30);
       frame.add(Hours);

//       JLabel instructorLabel = new JLabel("Instructor:");
//       instructorLabel.setBounds(50, 250, 100, 30);
//       frame.add(instructorLabel);

       idField = new JTextField();
       idField.setFont(new java.awt.Font("Comic Sans MS", 2, 18));
       idField.setBounds(150, 120, 200, 30);
       frame.add(idField);

       CName = new JTextField();
       CName.setFont(new java.awt.Font("Comic Sans MS", 2, 18));
       CName.setBounds(150, 190, 200, 30);
       frame.add(CName);

       jSpinner_courseHours = new JSpinner();
       jSpinner_courseHours.setFont(new java.awt.Font("Tahoma", 1, 14));
       jSpinner_courseHours.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
       jSpinner_courseHours.setBounds(150, 320, 100, 30);
       frame.add(jSpinner_courseHours);


       model = new DefaultTableModel();
       CourseTable = new JTable(model);
       scrollPane = new JScrollPane(CourseTable);
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


       backbtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               new AdminPanel();
               frame.dispose();
           }
       });

       addButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   int id=Integer.parseInt(idField.getText());
                   String Cname=CName.getText();
//                   String InstName= (String) instructorDropdown.getSelectedItem();
                   int Crhr=(int)jSpinner_courseHours.getValue();
                   CourseDB db=new CourseDB();
                   db.insertUpdateDeleteCourse('i',id,Cname,Crhr);
                   JOptionPane.showMessageDialog(null,"New Course Added SucessFult");
                   frame.dispose();
                   new ManageCourse();
               }
               catch (Exception ex){
                   ex.printStackTrace();
               }
           }
       });

       deletebtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   int id=Integer.parseInt(idField.getText());
                   CourseDB db=new CourseDB();
                   db.insertUpdateDeleteCourse('d',id,null,0);
                   frame.dispose();
                   new ManageCourse();

               }
               catch (Exception ex){
                   ex.printStackTrace();
               }
           }
       });

       updatebtn.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               int id=Integer.parseInt(idField.getText());
               String Cname=CName.getText();
//               String InstName= (String) instructorDropdown.getSelectedItem();
               int Crhr=(int)jSpinner_courseHours.getValue();
               CourseDB db=new CourseDB();
               db.insertUpdateDeleteCourse('u',id,Cname,Crhr);

               frame.dispose();
               new ManageCourse();
           }
       });



   }

   public void fillTable(){

       model.addColumn("Course id");
       model.addColumn("Course Name");
       model.addColumn("Course Credit Hours");
       try {
           Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root", "2000");
           Statement statement=connection.createStatement();
           ResultSet resultSet=statement.executeQuery("SELECT * FROM course");

           while (resultSet.next()){
               Object [] obj=new Object[4];
               obj[0]=resultSet.getInt("Course_id");
               obj[1]=resultSet.getString("Course_Name");
               obj[2]=resultSet.getInt("Credit_hrs");
               model.addRow(obj);
           }


       }
       catch (Exception ex){
           ex.printStackTrace();
       }
   }

   public static void main(String[] args){
       new ManageCourse();
   }

}
