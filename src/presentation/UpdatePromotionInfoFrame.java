package presentation;

import business.PromotionBU;
import pojo.PromotionPOJO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

public class UpdatePromotionInfoFrame extends JFrame {
    public interface PromotionUpdated {
        public void promotionUpdated(String promoName, String description, int discount, int maxOrder, boolean applyOnce, boolean applyToAnonymous);
    }
    private PromotionUpdated callback;
    private int promotion_id;
    
    private JLabel nameLabel;
    private JTextField nameField;
    
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    
    private JLabel discountLabel;
    private JFormattedTextField discountField;
    
    private JLabel maxOrderLabel;
    private JFormattedTextField maxOrderField;
    
    private JLabel applyOnceLabel;
    private JComboBox applyOnceComboBox;
     
    private JLabel applyToAnonymousLabel;
    private JComboBox applyToAnonymousComboBox;
    
    private JButton updateButton;
    private JPanel updatePanel;
    
    private PromotionBU business;
    
    public UpdatePromotionInfoFrame(PromotionUpdated callback, int promotion_id) {
        // Set the title of the frame
        super("Update promotion's info");

        this.callback = callback;
        this.promotion_id = promotion_id;

        // Initialize the components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        
        descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField(20);
        
        // Number formatter
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        formatter.setCommitsOnValidEdit(true);
        
        discountLabel = new JLabel("Discount:");
        discountField = new JFormattedTextField(formatter);
        
        maxOrderLabel = new JLabel("Max order:");
        maxOrderField = new JFormattedTextField(formatter);
        
        updateButton = new JButton("Update");
        business = new PromotionBU();

        // ComboBox
        applyOnceLabel = new JLabel("Apply once:");
        applyOnceComboBox = new JComboBox();
            // Add items to the combo box
        applyOnceComboBox.addItem("true");
        applyOnceComboBox.addItem("false");
        
        applyToAnonymousLabel = new JLabel("Apply to anonymous:");
        applyToAnonymousComboBox = new JComboBox();
            // Add items to the combo box
        applyToAnonymousComboBox.addItem("true");
        applyToAnonymousComboBox.addItem("false");
        
        // Add to panel
        updatePanel = new JPanel();
        updatePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Promotion's name
        constraints.gridx = 0;
        constraints.gridy = 0;
        updatePanel.add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        updatePanel.add(nameField, constraints);

        // Promotion's description
        constraints.gridx = 0;
        constraints.gridy = 1;
        updatePanel.add(descriptionLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        updatePanel.add(descriptionField, constraints);
        
        // Promotion's discount
        constraints.gridx = 0;
        constraints.gridy = 2;
        updatePanel.add(discountLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        updatePanel.add(discountField, constraints);
        
        // Promotion's maxOrder
        constraints.gridx = 0;
        constraints.gridy = 3;
        updatePanel.add(maxOrderLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        updatePanel.add(maxOrderField, constraints);
        
        //ComboBox
            // Apply once
        constraints.gridx = 0;
        constraints.gridy = 4;
        updatePanel.add(applyOnceLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        updatePanel.add(applyOnceComboBox, constraints);
        
            // Apply to anonymous
        constraints.gridx = 0;
        constraints.gridy = 5;
        updatePanel.add(applyToAnonymousLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        updatePanel.add(applyToAnonymousComboBox, constraints);
        
        // Update button
        constraints.gridx = 1;
        constraints.gridy = 6;
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
                if (nameField.getText().isEmpty() || descriptionField.getText().isEmpty() || discountField.getValue() == null || maxOrderField.getValue() == null) {
                    // Fields are empty
                    JOptionPane.showMessageDialog(null, "Please fill out all the fields!");
                } else if (!(((Integer) discountField.getValue()) instanceof Number) || !(((Integer) maxOrderField.getValue()) instanceof Number)) {
                    // Input value is not a number
                    JOptionPane.showMessageDialog(null, "Please fill only number in discount and maxOrder field!");
                } else {
                    // Get the user input
                    String promoName = nameField.getText();
                    String description = descriptionField.getText();
                    int discount = (Integer) discountField.getValue();
                    int maxOrder = (Integer) maxOrderField.getValue();
                    boolean applyOnce = Boolean.parseBoolean((String) applyOnceComboBox.getSelectedItem());
                    boolean applyToAnonymous = Boolean.parseBoolean((String) applyToAnonymousComboBox.getSelectedItem());
                    
                    // Add user input info into PromotionPOJO
                    PromotionPOJO updatePromotion = new PromotionPOJO(promotion_id, promoName, description, discount, maxOrder, applyOnce, applyToAnonymous);

                    // Update promotion into db
                    int statusCode = business.updatePromotionInfo(updatePromotion);

                    // Show status
                    String updateStatus = "";
                    if (statusCode == -1) {
                        updateStatus = "Error: SQLException";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -2) {
                        updateStatus = "Promotion not found";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else if (statusCode == -3) {
                        updateStatus = "Update promotion's info failed! OR The promotion's new info is the same as the old one";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, updateStatus);
                    } else{
                        updateStatus = "Promotion updated successfully";

                        // Call the callback function when the insert is complete
                        callback.promotionUpdated(promoName, description, discount, maxOrder, applyOnce, applyToAnonymous);

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
