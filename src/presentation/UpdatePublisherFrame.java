package presentation;

import business.PublisherBU;
import pojo.PublisherPOJO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePublisherFrame extends JFrame {
    public interface PublisherUpdated {
        public void publisherUpdated(String name, String country);
    }
    private PublisherUpdated callback;
    private int publisher_id;
    private JLabel nameLabel, countryLabel;
    private JTextField nameField, countryField;
    private JButton updateButton;
    private JPanel updatePanel;
    private PublisherBU business;
    public UpdatePublisherFrame(PublisherUpdated callback, int publisher_id) {
        // Set the title of the frame
        super("Update publisher's info");

        this.callback = callback;
        this.publisher_id = publisher_id;

       // Initialize the components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        countryLabel = new JLabel("Country:");
        countryField = new JTextField(20);
        updateButton = new JButton("Update publisher");
        business = new PublisherBU();

        // Add to panel
        updatePanel = new JPanel();
        updatePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Name
        constraints.gridx = 0;
        constraints.gridy = 0;
        updatePanel.add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        updatePanel.add(nameField, constraints);

        // Country
        constraints.gridx = 0;
        constraints.gridy = 1;
        updatePanel.add(countryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        updatePanel.add(countryField, constraints);
        
        // Add button
        constraints.gridx = 1;
        constraints.gridy = 2;
        updatePanel.add(updateButton, constraints);

        add(updatePanel);

        // Set the size and location of the frame
        setSize(450, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty() || countryField.getText().isEmpty()) {
                    // The field is empty
                    JOptionPane.showMessageDialog(null, "Please fill out the field!");
                } else {
                    // Get the user input
                    String name = nameField.getText();
                    String country = countryField.getText();

                    // Add user input info into CategoryPOJO
                    PublisherPOJO updatePublisher = new PublisherPOJO(publisher_id, name, country);

                    // Update category into db
                    int statusCode = business.updatePublisher(updatePublisher);

                    // Show status
                    String updateStatus = "";
                    if (statusCode == -1) {
                        updateStatus = "Error: SQLException";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -2) {
                        updateStatus = "Publisher not found";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -3) {
                        updateStatus = "Update publisher's info failed! OR The publisher's new info is the same as the old one";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else{
                        updateStatus = "Publisher updated successfully";

                        // Call the callback function when the insert is complete
                        callback.publisherUpdated(name, country);

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