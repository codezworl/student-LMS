package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class ManageStudent {
    private JFrame frame;
    private JTextField idField;
    private JTextField firstNameField,phoneField,dobField;
    private JTextField lastNameField;
    private JTextArea addressArea;
    private JButton updateButton;
    private JButton deleteButton,addButton,cancelButton;
    private JTable studentTable;
    private JScrollPane scrollPane;
    private JRadioButton maleRadioButton, femaleRadioButton;

    private DefaultTableModel model;

    public ManageStudent() {
        frame = new JFrame("Student Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1150, 700); // Set your desired size here
        frame.setLayout(null);

        initComponents();
        fillTable();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initComponents() {
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 20, 80, 20);
        idField = new JTextField();
        idField.setBounds(150, 20, 150, 20);
        frame.add(idField);
        frame.add(idLabel);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 60, 80, 20);
        firstNameField = new JTextField();
        firstNameField.setBounds(150, 60, 150, 20);
        frame.add(firstNameField);
        frame.add(firstNameLabel);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 100, 80, 20);
        lastNameField = new JTextField();
        lastNameField.setBounds(150, 100, 150, 20);
        frame.add(lastNameField);
        frame.add(lastNameLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 140, 150, 20);
        frame.add(phoneField);
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 140, 80, 20);
        frame.add(phoneLabel);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dobField = new JFormattedTextField(dateFormat);
        dobField.setBounds(150, 180, 150, 20);
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 180, 100, 20);
        frame.add(dobLabel);
        frame.add(dobField);


        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 260, 80, 20);
        addressArea = new JTextArea();
        addressArea.setBounds(150, 260, 200, 80);
        frame.add(addressArea);
        frame.add(addressLabel);

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
        studentTable = new JTable(model);
        scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(400, 60, 600, 500);
        frame.add(scrollPane);



        addButton = new JButton("Add");
        addButton.setBounds(260, 400, 80, 30);
        frame.add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(60, 400, 80, 30);
        frame.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(160, 400, 80, 30);
        frame.add(deleteButton);



        cancelButton=new JButton("Go back");
        cancelButton.setBounds(60,360,80,30);
        frame.add(cancelButton);




       cancelButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               new AdminPanel();
               frame.dispose();
           }
       });

       addButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               try {
                   String id = idField.getText();
                   String fname = firstNameField.getText();
                   String lname = lastNameField.getText();
                   String phone = phoneField.getText();
                   String address = addressArea.getText();
                   String dobString = dobField.getText();
                   String gender = maleRadioButton.isSelected() ? "Male" : "Female";


                   SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                   SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                   java.util.Date dobUtilDate = inputDateFormat.parse(dobString);
                   String bdate = outputDateFormat.format(dobUtilDate);

                   StudentDB db=new StudentDB();
                   db.insertupdateDeleteStudent('i', id, fname, lname, gender, bdate, phone, address);
                   JOptionPane.showMessageDialog(null, "Student added successfully!");

                   fillTable();

               }
               catch (Exception ex){
                   JOptionPane.showMessageDialog(null, "Please enter a valid ID.");
               }
           }
       });
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              try {
                  String id = idField.getText();
                  String fname = firstNameField.getText();
                  String lname = lastNameField.getText();
                  String phone = phoneField.getText();
                  String address = addressArea.getText();
                  String dobString = dobField.getText();
                  String gender = maleRadioButton.isSelected() ? "Male" : "Female";


                  SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                  SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                  java.util.Date dobUtilDate = inputDateFormat.parse(dobString);
                  String bdate = outputDateFormat.format(dobUtilDate);

                  StudentDB db=new StudentDB();
                  db.insertupdateDeleteStudent('u', id, fname, lname, gender, bdate, phone, address);
              }
              catch (Exception ex){
                  ex.printStackTrace();
              }


            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    String id = idField.getText();
                    StudentDB db=new StudentDB();
                    db.insertupdateDeleteStudent('d', id, null, null, null, null, null, null);
                    JOptionPane.showMessageDialog(null,"Student with ID: " + id + " was deleted successfully!");
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });




    }

    private void fillTable() {
        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");
        model.addColumn("Date-of-birth");
        model.addColumn("Phone");
        model.addColumn("Address");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms","root", "2000");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");

            while (resultSet.next()){

                Object[] rowData = new Object[7];
                rowData[0] = resultSet.getString("Student_id");
                rowData[1] = resultSet.getString("First_Name");
                rowData[2] = resultSet.getString("Last_Name");
                rowData[3] = resultSet.getString("Gender");
                rowData[4] = resultSet.getString("DOB");
                rowData[5] = resultSet.getString("Phone");
                rowData[6] = resultSet.getString("Address");

                model.addRow(rowData);

            }
        }

        catch (Exception ex){
            ex.printStackTrace();
        }



    }

    public static void main(String[] args) {
       new ManageStudent();
    }
}
