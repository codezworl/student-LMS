package Admin;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class AddCourseModule {

    JFrame frame = new JFrame();
    private JTextField CourseNametext, idText;
    private JSpinner jSpinner_courseHours;
    private JButton Addbtn;
    private JButton button_Cancel;


    public AddCourseModule() {
        initComponents();
    }

    private void initComponents() {

        frame.setSize(500, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel jLabel1 = new JLabel("Add Courses");
        jLabel1.setFont(new java.awt.Font("Courier New", 1, 30));
        jLabel1.setBounds(140, 40, 200, 40);
        frame.add(jLabel1);

        JLabel CourseName = new JLabel("Course id");
        CourseName.setFont(new java.awt.Font("Courier New", 3, 14));
        CourseName.setBounds(50, 120, 100, 30);
        frame.add(CourseName);

        JLabel Name = new JLabel("Course Name:");
        Name.setFont(new java.awt.Font("Courier New", 3, 14));
        Name.setBounds(50, 190, 100, 30);
        frame.add(Name);

        JLabel Hours = new JLabel("Credit Hrs:");
        Hours.setFont(new java.awt.Font("Courier New", 1, 15));
        Hours.setBounds(50, 260, 100, 30);
        frame.add(Hours);


        idText = new JTextField();
        idText.setFont(new java.awt.Font("Comic Sans MS", 2, 18));
        idText.setBounds(150, 120, 200, 30);
        frame.add(idText);

        CourseNametext = new JTextField();
        CourseNametext.setFont(new java.awt.Font("Comic Sans MS", 2, 18));
        CourseNametext.setBounds(150, 190, 200, 30);
        frame.add(CourseNametext);

        String id;
        Random random=new Random();
        id= String.valueOf(random.nextInt(5000));
        idText.setText(id);

        jSpinner_courseHours = new JSpinner();
        jSpinner_courseHours.setFont(new java.awt.Font("Tahoma", 1, 14));
        jSpinner_courseHours.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        jSpinner_courseHours.setBounds(150, 260, 100, 30);
        frame.add(jSpinner_courseHours);

        Addbtn = new JButton("Add Course");
        Addbtn.setBackground(new java.awt.Color(102, 102, 102));
        Addbtn.setFont(new java.awt.Font("Calibri", 1, 16));
        Addbtn.setForeground(new java.awt.Color(255, 255, 255));
        Addbtn.setBounds(110, 450, 150, 36);
        frame.add(Addbtn);

        button_Cancel = new JButton("Cancel");
        button_Cancel.setBackground(new java.awt.Color(102, 102, 102));
        button_Cancel.setFont(new java.awt.Font("Calibri", 1, 16));
        button_Cancel.setForeground(new java.awt.Color(255, 255, 255));
        button_Cancel.setBounds(280, 450, 100, 36);
        button_Cancel.addActionListener(this::button_CancelActionPerformed);
        frame.add(button_Cancel);

        frame.setLocationRelativeTo(null);


        Addbtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int id=Integer.parseInt(idText.getText());
                String Cname=CourseNametext.getText();
                int Crhr=(int)jSpinner_courseHours.getValue();
                CourseDB db=new CourseDB();
                db.insertUpdateDeleteCourse('i',id,Cname,Crhr);
                JOptionPane.showMessageDialog(null,"Course Added Successfuly");
                ClearText();

            }
        });
    }
    void ClearText(){
        idText.setText(" ");
        CourseNametext.setText(" ");
        jSpinner_courseHours.setValue(0);
    }




    private void button_CancelActionPerformed(ActionEvent evt) {
        new AdminPanel();
        frame.dispose();

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new AddCourseModule();
        });
    }
}
