package presentation;

import business.PublisherBU;
import pojo.PublisherPOJO;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddPublisherFrame extends JFrame {
    public interface PublisherInserted {
        public void publisherInserted(int id, String name, String country);
    }
    private PublisherInserted callback;
    private JLabel nameLabel, countryLabel;
    private JTextField nameField, countryField;
    private JButton addButton;
    private JPanel addPanel;
    private PublisherBU business;
    public AddPublisherFrame(PublisherInserted callback) {
        // Set the title of the frame
        super("Add new publisher");

        this.callback = callback;

        // Initialize the components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        countryLabel = new JLabel("Country:");
        countryField = new JTextField(20);
        addButton = new JButton("Add new Publisher");
        business = new PublisherBU();

        // Add to panel
        addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Name
        constraints.gridx = 0;
        constraints.gridy = 0;
        addPanel.add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        addPanel.add(nameField, constraints);

        // Country
        constraints.gridx = 0;
        constraints.gridy = 1;
        addPanel.add(countryLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        addPanel.add(countryField, constraints);
        
        // Add button
        constraints.gridx = 1;
        constraints.gridy = 2;
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
                if (nameField.getText().isEmpty() || countryField.getText().isEmpty()) {
                    // The field is empty
                    JOptionPane.showMessageDialog(null, "Please fill out the field!");
                } else {
                    // Get the user input
                    String name = nameField.getText();
                    String country = countryField.getText();

                    // Add user input info into CategoryPOJO
                    PublisherPOJO newPublisher = new PublisherPOJO(name, country);

                    // Insert account into db
                    int insertId = business.insertPublisher(newPublisher);

                    // Show status
                    String insertStatus = "";
                    if (insertId == 0){
                        insertStatus = "get LAST_INSERT_ID() failed";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else if (insertId == -1) {
                        insertStatus = "Error: SQLException";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else if (insertId == -2) {
                        insertStatus = "The publisher's name already exists";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else if (insertId == -3) {
                        insertStatus = "Publisher added failed";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else{
                        insertStatus = "Publisher added successfully";

                        // Call the callback function when the insert is complete
                        callback.publisherInserted(insertId, name, country);

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);

                        // Close the frame
                        dispose();
                    }
                }
            }
        });
    }
}