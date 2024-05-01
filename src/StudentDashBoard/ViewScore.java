package StudentDashBoard;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewScore extends JFrame {
    private JTable scoreTable;
    private JButton backButton;

    public ViewScore() {
        setTitle("Score Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Your id");
        model.addColumn("Course Name");
        model.addColumn("Your Score");
        model.addColumn("Description");
        scoreTable = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(scoreTable);
        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    public void retrieveScore(String id) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM score WHERE Student_id=?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            DefaultTableModel model = (DefaultTableModel) scoreTable.getModel();
//            model.setRowCount(0); // Clear existing data

            while (resultSet.next()) {
                String studentId = resultSet.getString("Student_id");
                String courseName = resultSet.getString("Course_name");
                float score = resultSet.getFloat("Student_Score");
                String desc = resultSet.getString("description");

                model.addRow(new Object[]{studentId, courseName, score, desc});
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "No Data Found for ID: " + id);
            }

            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        ViewScore viewScore = new ViewScore();

    }
}
