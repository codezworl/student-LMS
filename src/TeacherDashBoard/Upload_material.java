package TeacherDashBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Upload_material extends JFrame {

    private JButton uploadMaterialButton;
    private JButton goback;

    public Upload_material() {
        setTitle("Teacher Material Module");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        uploadMaterialButton = new JButton("Upload Study Material");
        uploadMaterialButton.setBounds(50, 50, 250, 30);
        uploadMaterialButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    uploadFileToDB(selectedFile);
                }
            }
        });
        add(uploadMaterialButton);

        goback = new JButton("Go Backs");
        goback.setBounds(50, 100, 250, 30);
        goback.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic to create a testing.test (e.g., open a testing.test creation window)
//                JOptionPane.showMessageDialog(null, "Create Test functionality to be implemented.");
                new TeacherModule();
                dispose();
            }
        });
        add(goback);
    }

    private void uploadFileToDB(File file) {
        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms\", \"root\", \"2000");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lms", "root", "2000");
            String query = "INSERT INTO upload_material (Subject, Filename) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, file.getName());

            FileInputStream fileInputStream = new FileInputStream(file);
            preparedStatement.setBinaryStream(2, fileInputStream, (int) file.length());

            preparedStatement.executeUpdate();

            fileInputStream.close();
            preparedStatement.close();
            connection.close();

            JOptionPane.showMessageDialog(null, "File uploaded to the database: " + file.getName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Upload_material::new);
    }
}
