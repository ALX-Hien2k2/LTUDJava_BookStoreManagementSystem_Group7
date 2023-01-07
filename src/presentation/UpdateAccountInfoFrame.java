package presentation;

import business.UserBU;
import pojo.UserPOJO;
import constant.Constant_var;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class UpdateAccountInfoFrame extends JFrame {
    public interface AccountUpdated {
        public void accountUpdated(String fullname, String username, LocalDate dob, String role);
    }
    private AccountUpdated callback;
    private int account_id;
    private JLabel fullnameLabel;
    private JLabel usernameLabel;
    private JLabel role_comboBoxLabel;
    private JLabel dob_comboBoxLabel;
    private JLabel year_comboBoxLabel;
    private JLabel month_comboBoxLabel;
    private JLabel day_comboBoxLabel;
    private JTextField fullnameField;
    private JTextField usernameField;
    private JComboBox role_comboBox;
    private JComboBox year_comboBox;
    private JComboBox month_comboBox;
    private JComboBox day_comboBox;
    private JButton updateButton;
    private JPanel updatePanel;
    private UserBU business;
    private Constant_var constant_var;
    public UpdateAccountInfoFrame(AccountUpdated callback, int account_id) {
        // Set the title of the frame
        super("Update user account's info");

        this.callback = callback;
        this.account_id = account_id;

        // Initialize the components
        fullnameLabel = new JLabel("Fullname:");
        usernameLabel = new JLabel("Username:");
        role_comboBoxLabel = new JLabel("Role:");
        dob_comboBoxLabel = new JLabel("Date of Birth:");
        year_comboBoxLabel = new JLabel("Year:");
        month_comboBoxLabel = new JLabel("Month:");
        day_comboBoxLabel = new JLabel("Day:");

        fullnameField = new JTextField(20);
        usernameField = new JTextField(20);
        role_comboBox = new JComboBox(new String[] {"Employee", "Admin"});

        // Get the current year
        int currentYear = Year.now().getValue();

        // Create the array of years
        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = Integer.toString(currentYear - i);
        }

        String[] months = new String[12];
        for (int i = 0; i < 12; i++) {
            months[i] = String.format("%02d", i+1);
        }

        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i+1);
        }

        year_comboBox = new JComboBox(years);
        month_comboBox = new JComboBox(months);
        day_comboBox = new JComboBox(days);

        updateButton = new JButton("Update");
        business = new UserBU();
        constant_var = new Constant_var();

        // Add to panel
        updatePanel = new JPanel();
        updatePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Fullname
        constraints.gridx = 0;
        constraints.gridy = 0;
        updatePanel.add(fullnameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        updatePanel.add(fullnameField, constraints);

        // Username
        constraints.gridx = 0;
        constraints.gridy = 1;
        updatePanel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        updatePanel.add(usernameField, constraints);

        // role comboBox
        constraints.gridx = 0;
        constraints.gridy = 2;
        updatePanel.add(role_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        updatePanel.add(role_comboBox, constraints);

        // dob comboBox
        constraints.gridx = 0;
        constraints.gridy = 3;
        updatePanel.add(dob_comboBoxLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        updatePanel.add(year_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        updatePanel.add(year_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        updatePanel.add(month_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        updatePanel.add(month_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        updatePanel.add(day_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        updatePanel.add(day_comboBox, constraints);

        // Add button
        constraints.gridx = 1;
        constraints.gridy = 7;
        updatePanel.add(updateButton, constraints);

        add(updatePanel);

        // Set the size and location of the frame
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fullnameField.getText().isEmpty() || usernameField.getText().isEmpty()) {
                    // One or more fields are empty
                    JOptionPane.showMessageDialog(null, "Please fill out all fields!");
                } else {
                    // Get the user input
                    String fullname = fullnameField.getText();
                    String username = usernameField.getText();
                    String role = (String) role_comboBox.getSelectedItem();
                    String year  = (String) year_comboBox.getSelectedItem();
                    String month  = (String) month_comboBox.getSelectedItem();
                    String day = (String) day_comboBox.getSelectedItem();

                    // Convert the selected date to a LocalDate object
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
                    String dateString = year + " " + month + " " + day;
                    System.out.println("dateString: " + dateString);
                    LocalDate dob = LocalDate.parse(dateString, formatter);

                    // Add user input info into UserPOJO
                    UserPOJO updateAccount = new UserPOJO(account_id, fullname, username, dob, role);

                    // Update account into db
                    int statusCode = business.updateAccountInfo(updateAccount);

                    // Show status
                    String updateStatus = "";
                    if (statusCode == -1) {
                        updateStatus = "Error: SQLException";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -2) {
                        updateStatus = "Account not found";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -3) {
                        updateStatus = "Update account's info failed! OR The account's new info is the same as the old one";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else{
                        updateStatus = "Account updated successfully";

                        // Call the callback function when the insert is complete
                        callback.accountUpdated(fullname, username, dob, role);

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);

                        // Close the frame
                        dispose();
                    }
                }
            }
        });
    }
}
