package Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminPanel implements ActionListener {
    public JButton addCourseBtn, addStudentBtn, addTeacherBtn, ManageBtn, ManageBtn2, ManageBtn3;
    JFrame frame;

    public AdminPanel() {
        frame = new JFrame("Admin Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 300);
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel(new BorderLayout());
        frame.add(panel);
        placeButtons(panel);
        frame.setVisible(true);
    }

    private void placeButtons(JPanel panel) {
        JPanel teacherPanel = new JPanel();
        JPanel studentPanel = new JPanel();
        JPanel coursePanel = new JPanel();

        teacherPanel.setBorder(BorderFactory.createTitledBorder("Teacher Sections"));
        studentPanel.setBorder(BorderFactory.createTitledBorder("Student Sections"));
        coursePanel.setBorder(BorderFactory.createTitledBorder("Course Sections"));

        addCourseBtn = new JButton("Add New Course");
        addStudentBtn = new JButton("Add Student");
        addTeacherBtn = new JButton("Add New Teacher");
        ManageBtn = new JButton("Manage Student");
        ManageBtn2 = new JButton("Manage Teacher");
        ManageBtn3 = new JButton("Manage Course");

        addCourseBtn.setBackground(new Color(0, 123, 255));
        addStudentBtn.setBackground(new Color(0, 123, 255));
        addTeacherBtn.setBackground(new Color(0, 123, 255));
        ManageBtn.setBackground(new Color(0, 123, 255));
        ManageBtn2.setBackground(new Color(0, 123, 255));
        ManageBtn3.setBackground(new Color(0, 123, 255));


        teacherPanel.add(addTeacherBtn);
        teacherPanel.add(ManageBtn2);

        studentPanel.add(addStudentBtn);
        studentPanel.add(ManageBtn);

        coursePanel.add(addCourseBtn);
        coursePanel.add(ManageBtn3);

        panel.add(teacherPanel, BorderLayout.EAST);
        panel.add(studentPanel,BorderLayout.CENTER);
        panel.add(coursePanel,BorderLayout.WEST);

        addCourseBtn.addActionListener(this);
        addStudentBtn.addActionListener(this);
        addTeacherBtn.addActionListener(this);
        ManageBtn.addActionListener(this);
        ManageBtn2.addActionListener(this);
        ManageBtn3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseBtn) {
            new AddCourseModule();
            frame.dispose();
        } else if (e.getSource() == addStudentBtn) {
            new AddStudent();
            frame.dispose();
        } else if (e.getSource() == addTeacherBtn) {
            new AddTeacher();
            frame.dispose();
        } else if (e.getSource() == ManageBtn) {
            new ManageStudent();
            frame.dispose();
        } else if (e.getSource() == ManageBtn2) {
            new ManageTeacher();
            frame.dispose();
        } else if (e.getSource() == ManageBtn3) {
            new ManageCourse();
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        new AdminPanel();
    }
}
