package presentation;

import business.CategoryBU;
import pojo.CategoryPOJO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class AddCategoryFrame extends JFrame {
    public interface CategoryInserted {
        public void categoryInserted(int id, String name);
    }
    private CategoryInserted callback;
    private JLabel nameLabel;
    private JTextField nameField;
    private JButton addButton;
    private JPanel addPanel;
    private CategoryBU business;
    public AddCategoryFrame(CategoryInserted callback) {
        // Set the title of the frame
        super("Add new category");

        this.callback = callback;

        // Initialize the components
        nameLabel = new JLabel("Category's name:");
        nameField = new JTextField(20);
        addButton = new JButton("Add new Category");
        business = new CategoryBU();

        // Add to panel
        addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Category's name
        constraints.gridx = 0;
        constraints.gridy = 0;
        addPanel.add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        addPanel.add(nameField, constraints);

        // Add button
        constraints.gridx = 1;
        constraints.gridy = 1;
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
                if (nameField.getText().isEmpty()) {
                    // The field is empty
                    JOptionPane.showMessageDialog(null, "Please fill out the field!");
                } else {
                    // Get the user input
                    String cateName = nameField.getText();

                    // Add user input info into CategoryPOJO
                    CategoryPOJO newAccount = new CategoryPOJO(cateName);

                    // Insert account into db
                    int insertId = business.insertCategory(newAccount);

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
                        insertStatus = "The category's name already exists";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else if (insertId == -3) {
                        insertStatus = "Category added failed";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else{
                        insertStatus = "Category added successfully";

                        // Call the callback function when the insert is complete
                        callback.categoryInserted(insertId, cateName);

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
