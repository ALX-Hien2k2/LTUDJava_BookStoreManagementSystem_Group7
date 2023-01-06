package presentation;

import business.UserBU;
import pojo.UserPOJO;
import constant.Constant_var;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Date;

public class AddUserFrame extends JFrame {
    public interface AccountInserted {
        public void accountInserted(int id, String fullname, String password, String username, LocalDate dob, String role, Boolean isActive);
    }
    private AccountInserted callback;
    private JLabel fullnameLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel comboBoxLabel;
    private JTextField fullnameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JCheckBox showPasswordCheckBox;
    private JCheckBox showConfirmPasswordCheckBox;
    private JComboBox comboBox;
    private JButton addButton;
    private JPanel addPanel;
    private UserBU business;
    private Constant_var constant_var;
    public AddUserFrame(AccountInserted callback) {
        // Set the title of the frame
        super("Add user account");

        this.callback = callback;

        // Initialize the components
        fullnameLabel = new JLabel("Fullname:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        confirmPasswordLabel = new JLabel("Confirm password:");
        comboBoxLabel = new JLabel("Role:");
        fullnameField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        showPasswordCheckBox = new JCheckBox("Show password");
        showConfirmPasswordCheckBox = new JCheckBox("Show password");
        comboBox = new JComboBox(new String[] {"Employee", "Admin"});
        addButton = new JButton("Add User");
        business = new UserBU();
        constant_var = new Constant_var();

        // Add to panel
        addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Fullname
        constraints.gridx = 0;
        constraints.gridy = 0;
        addPanel.add(fullnameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        addPanel.add(fullnameField, constraints);

        // Username
        constraints.gridx = 0;
        constraints.gridy = 1;
        addPanel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        addPanel.add(usernameField, constraints);

        // Password
        constraints.gridx = 0;
        constraints.gridy = 2;
        addPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        addPanel.add(passwordField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        addPanel.add(showPasswordCheckBox, constraints);

        // Confirm password
        constraints.gridx = 0;
        constraints.gridy = 3;
        addPanel.add(confirmPasswordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        addPanel.add(confirmPasswordField, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        addPanel.add(showConfirmPasswordCheckBox, constraints);

        // ComboBox
        constraints.gridx = 0;
        constraints.gridy = 4;
        addPanel.add(comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        addPanel.add(comboBox, constraints);

        // Add button
        constraints.gridx = 1;
        constraints.gridy = 5;
        addPanel.add(addButton, constraints);

        add(addPanel);

        // Set the size and location of the frame
        setSize(450, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fullnameField.getText().isEmpty() || usernameField.getText().isEmpty() || passwordField.getPassword().length == 0 || confirmPasswordField.getPassword().length == 0) {
                    // One or more fields are empty
                    JOptionPane.showMessageDialog(null, "Please fill out all fields!");
                } else if (!new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
                    // Confirm password not correct
                    JOptionPane.showMessageDialog(null, "Confirm password does not match!");
                } else {
                    // Get the user input
                    String fullname = fullnameField.getText();
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());
                    String role = (String) comboBox.getSelectedItem();

                    System.out.println("role:" + role);
                    // Add user input info into UserPOJO
                    UserPOJO newAccount = new UserPOJO(fullname, username, password, role);

                    // Insert account into db
                    int insertId = business.insertAccount(newAccount);

                    // Show status
                    String insertStatus = "";
                    if(insertId == 0){
                        insertStatus = "get LAST_INSERT_ID() failed";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else if (insertId == -1) {
                        insertStatus = "Error: SQLException";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else if (insertId == -2) {
                        insertStatus = "Username already exists";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else if (insertId == -3) {
                        insertStatus = "User added failed";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else{
                        insertStatus = "User added successfully";

                        // Call the callback function when the insert is complete
                        callback.accountInserted(insertId, fullname, username, password, constant_var.getDefault_dob(), role, true);

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);

                        // Close the frame
                        dispose();
                    }
                }
            }
        });

        showPasswordCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Show password
                    passwordField.setEchoChar((char) 0);
                } else {
                    // Hide password
                    passwordField.setEchoChar('*');
                }
            }
        });

        showConfirmPasswordCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // Show password
                    confirmPasswordField.setEchoChar((char) 0);
                } else {
                    // Hide password
                    confirmPasswordField.setEchoChar('*');
                }
            }
        });
    }
}
