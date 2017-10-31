package dk.n4;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_GUI {

    // Declare the frame/window
    private JFrame frame;

    // Declare user text field
    private JTextField userNameField;

    // Declare password field
    private JPasswordField passwordField;

    // Declare user label
    private JLabel userNameLbl;

    // Declare password label
    private JLabel passwordLbl;

    // Declare connect button
    private JButton btnConnect;

    // Constructor
    public Login_GUI() {
        initialize();
    }

    // The initialize method that builds the window
    private void initialize() {

        // Creates the frame/window
        frame = new JFrame("Login Window");
        frame.setBounds(500,500,450,300);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // Create user field
        userNameField = new JTextField();
        userNameField.setBounds(156,56,203,26);
        frame.getContentPane().add(userNameField);
        userNameField.setColumns(10);

        // Create password field
        passwordField = new JPasswordField();
        passwordField.setBounds(156,107,203,26);
        frame.getContentPane().add(passwordField);

        // Create user label
        userNameLbl = new JLabel("Username:");
        userNameLbl.setBounds(57,61,87,16);
        frame.getContentPane().add(userNameLbl);

        // Create password label
        passwordLbl = new JLabel("Password:");
        passwordLbl.setBounds(63,112,70,16);
        frame.getContentPane().add(passwordLbl);

        // Create connect button
        btnConnect = new JButton("Connect");
        btnConnect.setBounds(148,172,117,29);
        frame.getContentPane().add(btnConnect);

        // Create button action
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB_Statements stmts = new DB_Statements();
                try {
                    String username = userNameField.getText();
                    String password = new String(passwordField.getPassword());
                    if (stmts.checkLogin(username, password)) {
                        JOptionPane.showMessageDialog(null,"User found\nAccess granted");
                        frame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null,"User not found\nAccess denied");
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        });

        frame.setVisible(true);

    }

}