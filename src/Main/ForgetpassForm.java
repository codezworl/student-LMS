package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgetpassForm extends JFrame {
    private JTextField universityField, majorField, phoneField;

    public ForgetpassForm() {
        setTitle("Password Reset Form");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel universityLabel = new JLabel("University Name:");
        universityField = new JTextField(20);
        JLabel majorLabel = new JLabel("Major:");
        majorField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        // Change button color
//        submitButton.setBackground(new Color(0, 123, 255));
        submitButton.setForeground(Color.WHITE);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String universityName = universityField.getText();
                String major = majorField.getText();
                String phoneNumber = phoneField.getText();

                // Validate the information and reset the password
                // (This part would involve checking the entered information against the user's account information in a real application)

                JOptionPane.showMessageDialog(null, "Thank you. Your password reset request has been submitted. You will receive further instructions at " + phoneNumber);
            }
        });

        panel.add(universityLabel);
        panel.add(universityField);
        panel.add(majorLabel);
        panel.add(majorField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(submitButton);

        add(panel);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new ForgetpassForm();
    }
}
