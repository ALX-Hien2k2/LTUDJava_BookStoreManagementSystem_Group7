package presentation;

import business.PromotionBU;
import pojo.PromotionPOJO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.text.NumberFormatter;

public class AddPromotionFrame extends JFrame {
    public interface PromotionInserted {
        public void promotionInserted(int id, String name, String description, LocalDate start_date, LocalDate end_date, int discount, int maxOrder, boolean apply_once, boolean applyToAnonymous, boolean isOpen);
    }
    private PromotionInserted callback;
    
    private JLabel nameLabel;
    private JTextField nameField;
     
    private JLabel descriptionLabel;
    private JTextField descriptionField;
    
    private JLabel start_dateLabel;
    private JLabel start_year_comboBoxLabel;
    private JLabel start_month_comboBoxLabel;
    private JLabel start_day_comboBoxLabel;
    private JComboBox start_year_comboBox;
    private JComboBox start_month_comboBox;
    private JComboBox start_day_comboBox;
    
    private JLabel end_dateLabel;
    private JLabel end_year_comboBoxLabel;
    private JLabel end_month_comboBoxLabel;
    private JLabel end_day_comboBoxLabel;
    private JComboBox end_year_comboBox;
    private JComboBox end_month_comboBox;
    private JComboBox end_day_comboBox;
    
    private JLabel discountLabel;
    private JFormattedTextField discountField;
    
    private JLabel maxOrderLabel;
    private JFormattedTextField maxOrderField;
    
    private JLabel applyOnceLabel;
    private JComboBox applyOnceComboBox;
     
    private JLabel applyToAnonymousLabel;
    private JComboBox applyToAnonymousComboBox;
    
    private JButton addButton;
    private JPanel addPanel;
    private PromotionBU business;
    
    public AddPromotionFrame(PromotionInserted callback) {
        // Set the title of the frame
        super("Add new promotion campaign");

        this.callback = callback;

        // Initialize the components
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        
        descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField(20);
        
        // ComboBox
        start_dateLabel = new JLabel("Start Date:");
        start_year_comboBoxLabel = new JLabel("year:");
        start_month_comboBoxLabel = new JLabel("month:");
        start_day_comboBoxLabel = new JLabel("day:");

        end_dateLabel = new JLabel("End Date:");
        end_year_comboBoxLabel = new JLabel("year:");
        end_month_comboBoxLabel = new JLabel("month:");
        end_day_comboBoxLabel = new JLabel("day:");
        
        // Get the current year
        int currentYear = Year.now().getValue() + 5;

        // Create the array of years, months, days
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

        start_year_comboBox = new JComboBox(years);
        start_month_comboBox = new JComboBox(months);
        start_day_comboBox = new JComboBox(days);
        
        end_year_comboBox = new JComboBox(years);
        end_month_comboBox = new JComboBox(months);
        end_day_comboBox = new JComboBox(days);
        
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
        
        addButton = new JButton("Add");
        business = new PromotionBU();

        // Add to panel
        addPanel = new JPanel();
        addPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Promotion's name
        constraints.gridx = 0;
        constraints.gridy = 0;
        addPanel.add(nameLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        addPanel.add(nameField, constraints);

        // Promotion's description
        constraints.gridx = 0;
        constraints.gridy = 1;
        addPanel.add(descriptionLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        addPanel.add(descriptionField, constraints);
        
        // Start date comboBox
        constraints.gridx = 0;
        constraints.gridy = 2;
        addPanel.add(start_dateLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        addPanel.add(start_year_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        addPanel.add(start_year_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        addPanel.add(start_month_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        addPanel.add(start_month_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        addPanel.add(start_day_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        addPanel.add(start_day_comboBox, constraints);

        // End date comboBox
        constraints.gridx = 0;
        constraints.gridy = 6;
        addPanel.add(end_dateLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        addPanel.add(end_year_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        addPanel.add(end_year_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 8;
        addPanel.add(end_month_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 8;
        addPanel.add(end_month_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 9;
        addPanel.add(end_day_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 9;
        addPanel.add(end_day_comboBox, constraints);
        
        // Promotion's discount
        constraints.gridx = 0;
        constraints.gridy = 10;
        addPanel.add(discountLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 10;
        addPanel.add(discountField, constraints);
        
        // Promotion's maxOrder
        constraints.gridx = 0;
        constraints.gridy = 11;
        addPanel.add(maxOrderLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 11;
        addPanel.add(maxOrderField, constraints);
        
        //ComboBox
            // Apply once
        constraints.gridx = 0;
        constraints.gridy = 12;
        addPanel.add(applyOnceLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 12;
        addPanel.add(applyOnceComboBox, constraints);
        
            // Apply to anonymous
        constraints.gridx = 0;
        constraints.gridy = 13;
        addPanel.add(applyToAnonymousLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 13;
        addPanel.add(applyToAnonymousComboBox, constraints);

        // Add button
        constraints.gridx = 1;
        constraints.gridy = 14;
        addPanel.add(addButton, constraints);

        add(addPanel);

        // Set the size and location of the frame
        setSize(450, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().isEmpty() || descriptionField.getText().isEmpty() || discountField.getValue() == null || maxOrderField.getValue() == null) {
                    // The fields are empty
                    JOptionPane.showMessageDialog(null, "Please fill out all fields!");
                } else {
                    // Get the user input
                    String promoName = nameField.getText();
                    String description = descriptionField.getText();
                    int discount = (Integer) discountField.getValue();
                    int maxOrder = (Integer) maxOrderField.getValue();
                    boolean applyOnce = Boolean.parseBoolean((String) applyOnceComboBox.getSelectedItem());
                    boolean applyToAnonymous = Boolean.parseBoolean((String) applyToAnonymousComboBox.getSelectedItem());
                    
                    String start_year  = (String) start_year_comboBox.getSelectedItem();
                    String start_month  = (String) start_month_comboBox.getSelectedItem();
                    String start_day = (String) start_day_comboBox.getSelectedItem();

                    String end_year  = (String) end_year_comboBox.getSelectedItem();
                    String end_month  = (String) end_month_comboBox.getSelectedItem();
                    String end_day = (String) end_day_comboBox.getSelectedItem();

                    // Convert the selected date to a LocalDate object
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");

                    String startDateString = start_year + " " + start_month + " " + start_day;
                    System.out.println("startDateString: " + startDateString);
                    LocalDate start_date = LocalDate.parse(startDateString, formatter);

                    String endDateString = end_year + " " + end_month + " " + end_day;
                    System.out.println("startDateString: " + startDateString);
                    LocalDate end_date = LocalDate.parse(endDateString, formatter);
                    

                    // Add user input info into PromotionPOJO
                    PromotionPOJO newPromotion = new PromotionPOJO(promoName, description, start_date, end_date, discount, maxOrder, applyOnce, applyToAnonymous);

                    // Insert account into db
                    int insertId = business.insertPromotion(newPromotion);

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
                        insertStatus = "Promotion added failed";

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, insertStatus);
                    } else{
                        insertStatus = "Promotion added successfully";

                        // Call the callback function when the insert is complete
                        callback.promotionInserted(insertId, promoName, description, start_date, end_date, discount, maxOrder, applyOnce, applyToAnonymous, true);

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