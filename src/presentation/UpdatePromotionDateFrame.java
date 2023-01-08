package presentation;

import business.PromotionBU;
import pojo.PromotionPOJO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;

public class UpdatePromotionDateFrame extends JFrame {
    public interface PromotionDateUpdated {
        public void promotionDateUpdated(LocalDate start_date, LocalDate end_date);
    }
    private PromotionDateUpdated callback;
    private int promotion_id;
   
    private JLabel start_date_comboBoxLabel;
    private JLabel start_year_comboBoxLabel;
    private JLabel start_month_comboBoxLabel;
    private JLabel start_day_comboBoxLabel;
    private JComboBox start_year_comboBox;
    private JComboBox start_month_comboBox;
    private JComboBox start_day_comboBox;
    
    private JLabel end_date_comboBoxLabel;
    private JLabel end_year_comboBoxLabel;
    private JLabel end_month_comboBoxLabel;
    private JLabel end_day_comboBoxLabel;
    private JComboBox end_year_comboBox;
    private JComboBox end_month_comboBox;
    private JComboBox end_day_comboBox;
    
    private JButton updateButton;
    private JPanel updatePanel;
    
    private PromotionBU business;
    
    public UpdatePromotionDateFrame(PromotionDateUpdated callback, int promotion_id) {
        // Set the title of the frame
        super("Update promotion's date");

        this.callback = callback;
        this.promotion_id = promotion_id;

        // Initialize the components
        start_date_comboBoxLabel = new JLabel("Start Data:");
        start_year_comboBoxLabel = new JLabel("year:");
        start_month_comboBoxLabel = new JLabel("month:");
        start_day_comboBoxLabel = new JLabel("day:");

        end_date_comboBoxLabel = new JLabel("End Data:");
        end_year_comboBoxLabel = new JLabel("year:");
        end_month_comboBoxLabel = new JLabel("month:");
        end_day_comboBoxLabel = new JLabel("day:");
        
        updateButton = new JButton("Update");
        
        business = new PromotionBU();

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
        
        // Add to panel
        updatePanel = new JPanel();
        updatePanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Start date comboBox
        constraints.gridx = 0;
        constraints.gridy = 0;
        updatePanel.add(start_date_comboBoxLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        updatePanel.add(start_year_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        updatePanel.add(start_year_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        updatePanel.add(start_month_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        updatePanel.add(start_month_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        updatePanel.add(start_day_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        updatePanel.add(start_day_comboBox, constraints);

        // End date comboBox
        constraints.gridx = 0;
        constraints.gridy = 4;
        updatePanel.add(end_date_comboBoxLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        updatePanel.add(end_year_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        updatePanel.add(end_year_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        updatePanel.add(end_month_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        updatePanel.add(end_month_comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        updatePanel.add(end_day_comboBoxLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 7;
        updatePanel.add(end_day_comboBox, constraints);
        
        // Update button
        constraints.gridx = 1;
        constraints.gridy = 8;
        updatePanel.add(updateButton, constraints);
        
        add(updatePanel);

        // Set the size and location of the frame
        setSize(350, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the user input
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
                PromotionPOJO updatePromotionDate = new PromotionPOJO(promotion_id, start_date, end_date);

                // Update promotion into db
                int statusCode = business.updatePromotionDate(updatePromotionDate);

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
                    updateStatus = "Update promotion's date failed! OR The promotion's new date is the same as the old one";

                    // Pop up notification
                    JOptionPane.showMessageDialog(null, updateStatus);
                } else{
                    updateStatus = "Promotion's date updated successfully";

                    // Call the callback function when the insert is complete
                    callback.promotionDateUpdated(start_date, end_date);

                    // Pop up notification
                    JOptionPane.showMessageDialog(null, updateStatus);

                    // Close the frame
                    dispose();
                }
            }
        });
    }
}