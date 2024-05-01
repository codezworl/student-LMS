package Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Random;

class AddStudent extends JFrame {

    private JTextField idField, firstNameField, lastNameField, phoneField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JTextArea addressArea;
    private JFormattedTextField dobField;

    private JButton addButton,cancelButton;

    public AddStudent() {
        setTitle("Add Student");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(550, 450);
        setLocationRelativeTo(null);

        initComponents();
        addComponentsToFrame();

        setVisible(true);
    }

    public void setTextclear(){
        idField.setText("");
        firstNameField.setText(" ");
        lastNameField.setText(" ");
        phoneField.setText(" ");
        dobField.setText(" ");
        addressArea.setText(" ");

    }

    private void initComponents() {

        idField = new JTextField();
        idField.setBounds(150, 20, 150, 20);

        firstNameField = new JTextField();
        firstNameField.setBounds(150, 60, 150, 20);

        lastNameField = new JTextField();
        lastNameField.setBounds(150, 100, 150, 20);

        phoneField = new JTextField();
        phoneField.setBounds(150, 140, 150, 20);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dobField = new JFormattedTextField(dateFormat);
        dobField.setBounds(150, 180, 150, 20);

        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBounds(150, 220, 80, 20);

        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBounds(240, 220, 80, 20);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        addressArea = new JTextArea();
        addressArea.setBounds(150, 260, 200, 80);

        addButton = new JButton("Add");
        addButton.setBounds(250, 360, 80, 30);

        cancelButton=new JButton("Go back");
        cancelButton.setBounds(150,360,80,30);
    }

    private void addComponentsToFrame() {
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(50, 20, 80, 20);
        String id;
        Random random=new Random();
        id= String.valueOf(random.nextInt(1000));
        idField.setText(id);
        add(idLabel);
        add(idField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 60, 80, 20);
        add(firstNameLabel);
        add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 100, 80, 20);
        add(lastNameLabel);
        add(lastNameField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 140, 80, 20);
        add(phoneLabel);
        add(phoneField);

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(50, 180, 100, 20);
        dobField.setToolTipText("dd/mm/yyyy");
        add(dobLabel);
        add(dobField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 220, 80, 20);
        add(genderLabel);
        add(maleRadioButton);
        add(femaleRadioButton);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 260, 80, 20);
        add(addressLabel);
        add(addressArea);

        add(addButton);
        add(cancelButton);


        cancelButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                new AdminPanel();
                dispose();

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
                    String gender = maleRadioButton.isSelected() ? "Male" : "Female";

                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    String dobString = dobField.getText();
                    java.util.Date dobUtilDate = inputDateFormat.parse(dobString);
                    String bdate = outputDateFormat.format(dobUtilDate);

                    StudentDB std = new StudentDB();
                    std.insertupdateDeleteStudent('i', id, fname, lname, gender, bdate, phone, address);

                    UserDB db=new UserDB();
                    db.InsertUser(id,fname,"Student");


                    JOptionPane.showMessageDialog(null, "Student added successfully!");
                    setTextclear();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid ID.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please enter the date in dd/MM/yyyy format.");
                }


            }
        });




    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddStudent::new);
    }
}
