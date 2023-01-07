package presentation;

import business.CategoryBU;
import pojo.CategoryPOJO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCategoryInfoFrame extends JFrame {
    public interface CategoryUpdated {
        public void categoryUpdated(String cateName);
    }
    private CategoryUpdated callback;
    private int category_id;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton updateButton;
    private JPanel updatePanel;
    private CategoryBU business;
    public UpdateCategoryInfoFrame(CategoryUpdated callback, int category_id) {
        // Set the title of the frame
        super("Update category's info");

        this.callback = callback;
        this.category_id = category_id;

        // Initialize the components
        nameLabel = new JLabel("Category's name:");
        
        nameField = new JTextField(20);

        updateButton = new JButton("Update");
        business = new CategoryBU();

        // Add to panel
        updatePanel = new JPanel();
        updatePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Category's name
        constraints.gridx = 0;
        constraints.gridy = 0;
        updatePanel.add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        updatePanel.add(nameField, constraints);

        // Add button
        constraints.gridx = 1;
        constraints.gridy = 1;
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
                if (nameField.getText().isEmpty()) {
                    // The field is empty
                    JOptionPane.showMessageDialog(null, "Please fill out the field!");
                } else {
                    // Get the user input
                    String cateName = nameField.getText();

                    // Add user input info into CategoryPOJO
                    CategoryPOJO updateCategory = new CategoryPOJO(category_id, cateName);

                    // Update category into db
                    int statusCode = business.updateCategoryInfo(updateCategory);

                    // Show status
                    String updateStatus = "";
                    if (statusCode == -1) {
                        updateStatus = "Error: SQLException";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -2) {
                        updateStatus = "Category not found";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -3) {
                        updateStatus = "Update category's info failed! OR The category's new info is the same as the old one";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else{
                        updateStatus = "Category updated successfully";

                        // Call the callback function when the insert is complete
                        callback.categoryUpdated(cateName);

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
