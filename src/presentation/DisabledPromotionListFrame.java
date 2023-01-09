package presentation;

import business.PromotionBU;
import pojo.PromotionPOJO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class DisabledPromotionListFrame extends JFrame{
    public interface PromotionEnabled {
        public void promotionEnabled(int id, String name, String description, LocalDate start_date, LocalDate end_date, int discount, int maxOrder, boolean apply_once, boolean applyToAnonymous, boolean isOpen);
    }
    private PromotionEnabled callback;
    
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;
    
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    
    private JButton enable_Button;
    private JPanel enable_Panel;
    
    private JPanel actionPanel;
    private String headers[] = { "Id", "Promotion name", "Description", "Start Date", "End Date", "Discount", "Max Order", "Apply once", "Apply to anonymous", "Open"};
    private PromotionBU business;
    private List<PromotionPOJO> promotions;

    public DisabledPromotionListFrame(PromotionEnabled callback) {
        // Set the title of the frame
        super("List of Disabled Promotions");

        this.callback = callback;
        
        // Set layout of the frame
        setLayout(new BorderLayout());

        // Initialize components
        // Override isCellEditable method
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Always false to prevent from editing
                return false;
            }
        };
        table = new JTable();
        
        business = new PromotionBU();
        promotions = business.getAllDisabledPromotion();
        
        searchField = new JTextField(); // Search by name
        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton = new JButton("Search");
        
        enable_Button = new JButton("Enable/Show");

        // Config components
        // Table model
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        
        // Table
        TableColumn id_column = table.getColumnModel().getColumn(0); // id
        id_column.setPreferredWidth(10);
        id_column.setResizable(false);
        
        TableColumn name_column = table.getColumnModel().getColumn(1); // name
        name_column.setPreferredWidth(150);
        name_column.setResizable(true);
        
        TableColumn description_column = table.getColumnModel().getColumn(2); // description
        description_column.setPreferredWidth(450);
        description_column.setResizable(true);

        TableColumn startDate_column = table.getColumnModel().getColumn(3); // start date
        startDate_column.setPreferredWidth(30);
        startDate_column.setResizable(false);
        
        TableColumn endDate_column = table.getColumnModel().getColumn(4); // end date
        endDate_column.setPreferredWidth(30);
        endDate_column.setResizable(false);
        
        TableColumn discount_column = table.getColumnModel().getColumn(5); // end date
        discount_column.setPreferredWidth(30);
        discount_column.setResizable(false);
        
        TableColumn maxOrder_column = table.getColumnModel().getColumn(6); // end date
        maxOrder_column.setPreferredWidth(30);
        maxOrder_column.setResizable(false);
        
        TableColumn applyOnce_column = table.getColumnModel().getColumn(7); // end date
        applyOnce_column.setPreferredWidth(30);
        applyOnce_column.setResizable(false);
        
        TableColumn applyToAnonymous_column = table.getColumnModel().getColumn(8); // end date
        applyToAnonymous_column.setPreferredWidth(30);
        applyToAnonymous_column.setResizable(false);
        
        TableColumn open_column = table.getColumnModel().getColumn(9); // end date
        open_column.setPreferredWidth(30);
        open_column.setResizable(false);
        
        table.getTableHeader().setReorderingAllowed(false);

        // Create a TableRowSorter for the table
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());

        // Set the Comparator for the id column
        sorter.setComparator(0, new Comparator<Integer>() {
            @Override
            public int compare(Integer id1, Integer id2) {
                // Compare the id values as integers
                return id1.compareTo(id2);
            }
        });

        // Set the sorter as the RowSorter for the table
        table.setRowSorter(sorter);

        // Add a mouse listener to the table header to trigger sorting when the user clicks a column title
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table.columnAtPoint(e.getPoint());
                if (column >= 0) {
                    sorter.sort();
                }
            }
        });

        // Scroll bar
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(480, 300));

        // Add data to table
        for (PromotionPOJO promotion : promotions) {
            model.addRow(new Object[] {
                    promotion.getId(),
                    promotion.getName(),
                    promotion.getDescription(),
                    promotion.getStart_date(),
                    promotion.getEnd_date(),
                    promotion.getDiscount(),
                    promotion.getMax_order(),
                    promotion.isCan_customer_once(),
                    promotion.isCan_anonymous(),
                    promotion.isIsOpen(),
            });
        }

        // Create a panel for the search field and button
        searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create a panel for the disable button and view disabled list
        enable_Panel = new JPanel();
        enable_Panel.add(new JLabel("Enable/Show promotion:"));
        enable_Panel.add(enable_Button);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(searchPanel);
        actionPanel.add(enable_Panel);

        // Add components to frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Set the size and location of the frame
        setSize(2000, 500);
        setLocation(300, 100);

        // Exit the application when the frame is closed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Action listener for Search button (Search by name)
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the name from the search field
                String name = searchField.getText();

                // Search for promotion
                List<PromotionPOJO> searchResults = business.searchDisabledPromotion(name);

                // Clear the table model
                model.setRowCount(0);

                // Add the search results to the table model
                for (PromotionPOJO result : searchResults) {
                    model.addRow(new Object[] {
                            result.getId(),
                            result.getName(),
                            result.getDescription(),
                            result.getStart_date(),
                            result.getEnd_date(),
                            result.getDiscount(),
                            result.getMax_order(),
                            result.isCan_customer_once(),
                            result.isCan_anonymous(),
                            result.isIsOpen(),
                    });
                }
            }
        });
        
        // Action listener for disable a promotion button
        enable_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select a promotion
                    JOptionPane.showMessageDialog(null, "Please select a promotion to enable", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    // Get the Id of the selected promotion
                    int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    String name = (String) table.getValueAt(table.getSelectedRow(), 1);
                    String description = (String) table.getValueAt(table.getSelectedRow(), 2);
                    LocalDate start_date = (LocalDate) table.getValueAt(table.getSelectedRow(), 3);
                    LocalDate end_date = (LocalDate) table.getValueAt(table.getSelectedRow(), 4);
                    int discount = (int) table.getValueAt(table.getSelectedRow(), 5);
                    int maxOrder = (int) table.getValueAt(table.getSelectedRow(), 6);
                    Boolean applyOnce = (Boolean) table.getValueAt(table.getSelectedRow(), 7);
                    Boolean applyToAnonymous = (Boolean) table.getValueAt(table.getSelectedRow(), 8);
                    Boolean isOpen = (Boolean) table.getValueAt(table.getSelectedRow(), 9);
                    
                    System.out.println("id:" + id);

                    // Enable promotion
                    int statusCode = business.enablePromotion(id);
                    
                    String status = "";
                    if(statusCode == -1){
                        status = "SQL Exception";

                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    } else if(statusCode == -2){
                        status = "Promotion not found or already be enabled";

                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    } else if(statusCode == -3){
                        status = "Enabled promotion fail!";

                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    } else{
                        status = "Enabled promotion successfully!";

                        // Remove the enabled promotion from the table
                        int columnIndex = 0; // id
                        Object valueToSearch = Integer.valueOf(id);

                        for (int i = 0; i < model.getRowCount(); i++) {
                            // Get the value at the specified column of the current row
                            Object cellValue = model.getValueAt(i, columnIndex);

                            // Check if the value of the cell is equal to the value you are looking for
                            if (valueToSearch.equals(cellValue)) {
                                // If the value is found, remove the row from the model
                                model.removeRow(i);
                                break;
                            }
                        }

                        // Callback to the previous frame to update the table
                        callback.promotionEnabled(id, name, description, start_date, end_date, discount, maxOrder, applyOnce, applyToAnonymous, isOpen);
                        
                        // Notification
                        JOptionPane.showMessageDialog(null, status);
                    }
                }
            }
        });
    }
}