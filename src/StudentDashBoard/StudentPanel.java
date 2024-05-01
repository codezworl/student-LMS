package StudentDashBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentPanel {

    private JFrame frame;
    private JPanel topPanel, sidePanel;
    public JLabel SLable;

    public String StudentId;
    public String Stdname;

    public StudentPanel() {
        frame = new JFrame();
        frame.setTitle("Student Dashboard");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        initComponents();

        frame.setVisible(true);

    }

    private void initComponents() {
        Font labelFont = new Font("Arial", Font.BOLD, 24);

        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 200, 50);
        topPanel.setBackground(Color.BLUE);
        frame.add(topPanel);

        JLabel label = new JLabel("Student View Board");
        label.setFont(labelFont);
        label.setBounds(450, 0, 600, 100);
        label.setForeground(Color.BLACK);
        frame.add(label);

        sidePanel = new JPanel();
        sidePanel.setBounds(0, 50, 200, 550);
        sidePanel.setBackground(Color.LIGHT_GRAY);
        frame.add(sidePanel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/Studenpanalpic.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(200, 50, frame.getWidth() - 200, frame.getHeight() - 50);
        frame.add(image);

        sidePanel.setLayout(new GridLayout(0, 1, 50, 4));

        SLable = new JLabel("");
        SLable.setFont(new java.awt.Font("Courier New", 2, 17));
        SLable.setBounds(50, 120, 100, 30);
        frame.add(SLable);
        sidePanel.add(SLable);

        JButton enrollCourseButton = new JButton("Enroll in Course");
        sidePanel.add(enrollCourseButton);

        enrollCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              EnrolledCourse en= new EnrolledCourse();
              en.Studentid=StudentId;
              en.Studentname=Stdname;

            }
        });

        JButton viewCourse=new JButton("View Course");
        sidePanel.add(viewCourse);

        viewCourse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                ViewCourse v=new ViewCourse();
                v.FetchdataFromEnrolledCourse(StudentId);



            }
        });



        JButton viewAssingmentbtn = new JButton("View Assignment");
        sidePanel.add(viewAssingmentbtn);

        viewAssingmentbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new ViewAssignment();
            }
        });

        JButton viewGradesButton = new JButton("View Grades");
        sidePanel.add(viewGradesButton);

        viewGradesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewScore V=new ViewScore();
                V.retrieveScore(StudentId);
            }
        });

        JButton Taketest = new JButton("Take Test Online");
        sidePanel.add(Taketest);

        Taketest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new test("test Online");
            }
        });
    }
    public void SetStudentId(String id){
        this.StudentId=id;
    }


    public static void main(String[] args) {
        new StudentPanel();
    }
}
