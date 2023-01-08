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

public class PromotionListFrame extends JFrame{
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;
    
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    
    private JButton past_promo_Button;
    private JButton current_promo_Button;
    private JButton upcoming_promo_Button;
    private JButton reset_filter_Button;
    private JPanel filterPanel;
    
    private JButton disable_Button;
    private JButton disabled_List_Button;
    private JPanel disable_Panel;
    
    private JButton open_close_Button;
    private JPanel open_close_Panel;
    
    private JButton addNewPromotionButton;
    private JPanel addNewPromotionPanel;
    
    private JButton updateDateButton;
    private JPanel updateDatePanel;
    
    private JButton updateInfoButton;
    private JPanel updateInfoPanel;
    
    private JPanel actionPanel;
    private String headers[] = { "Id", "Promotion name", "Description", "Start Date", "End Date", "Discount", "Max Order", "Apply once", "Apply to anonymous", "Open"};
    private PromotionBU business;
    private List<PromotionPOJO> promotions;

    public PromotionListFrame() {
        // Set the title of the frame
        super("List of Promotions");

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
        promotions = business.getAllPromotion();
        
        searchField = new JTextField(); // Search by name
        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton = new JButton("Search");
        
        past_promo_Button = new JButton("Past");
        current_promo_Button = new JButton("Current");
        upcoming_promo_Button = new JButton("Upcoming");
        reset_filter_Button = new JButton("Reset");
        
        disable_Button = new JButton("Disable/Hide");
        disabled_List_Button = new JButton("View list");
        
        open_close_Button = new JButton("Open/Close");
        
        addNewPromotionButton =  new JButton("Add");
        
        updateDateButton = new JButton("Update");
        updateInfoButton = new JButton("Update");

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

        // Create a panel for the filter buttons
        filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter promotions:"));
        filterPanel.add(past_promo_Button);
        filterPanel.add(current_promo_Button);
        filterPanel.add(upcoming_promo_Button);
        filterPanel.add(reset_filter_Button);
                
        // Create a panel for the disable button and view disabled list
        disable_Panel = new JPanel();
        disable_Panel.add(new JLabel("Disable/Hide promotion:"));
        disable_Panel.add(disable_Button);
        disable_Panel.add(new JLabel("View disabled promotion list:"));
        disable_Panel.add(disabled_List_Button);

        // Create a panel for the close button
        open_close_Panel = new JPanel();
        open_close_Panel.add(new JLabel("Open/Close Promotion:"));
        open_close_Panel.add(open_close_Button);
        
        // Create a panel for the add new promotion button
        addNewPromotionPanel = new JPanel();
        addNewPromotionPanel.add(new JLabel("Add new promotion:"));
        addNewPromotionPanel.add(addNewPromotionButton);

        // Create a panel for the update promotion's info button
        updateInfoPanel = new JPanel();
        updateInfoPanel.add(new JLabel("Update promotion's info:"));
        updateInfoPanel.add(updateInfoButton);
        
        // Create a panel for the update promotion's date button
        updateDatePanel = new JPanel();
        updateDatePanel.add(new JLabel("Update promotion's date:"));
        updateDatePanel.add(updateDateButton);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(searchPanel);
        actionPanel.add(filterPanel);
        actionPanel.add(disable_Panel);
        actionPanel.add(open_close_Panel);
        actionPanel.add(addNewPromotionPanel);
        actionPanel.add(updateDatePanel);
        actionPanel.add(updateInfoPanel);

        // Add components to frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Set the size and location of the frame
        setSize(2000, 500);
        setLocationRelativeTo(null);

        // Exit the application when the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Action listener for Search button (Search by name)
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the search text from the search field
                String searchText = searchField.getText();
    
                // Create a RowFilter to filter the rows based on the search text (case-insensitive)
                RowFilter<TableModel, Object> rowFilter = RowFilter.regexFilter("(?i)[\\p{L}\\p{M}]*" + searchText + "[\\p{L}\\p{M}]*", 1);
    
                // Set the RowFilter on the sorter
                sorter.setRowFilter(rowFilter);
            }
        });
        
        // Action listener for filter past promotions button
        past_promo_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filter promotions
                List<PromotionPOJO> searchResults = business.getPastPromotion();

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
        
        // Action listener for filter current promotions button
        current_promo_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filter promotions
                List<PromotionPOJO> searchResults = business.getCurrentPromotion();

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
        
        // Action listener for filter upcoming promotions button
        upcoming_promo_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filter promotions
                List<PromotionPOJO> searchResults = business.getUpcomingPromotion();

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
        
        // Action listener for reset filter button
        reset_filter_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Filter promotions
                List<PromotionPOJO> searchResults = business.getAllPromotion();

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
        
//        // Action listener for Add new category button
//        addNewCategoryButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Disable the old frame
//                setEnabled(false);
//
//                AddCategoryFrame addCategoryFrame = new AddCategoryFrame(new AddCategoryFrame.CategoryInserted() {
//                    public void categoryInserted(int id, String cateName) {
//                        // Add a row to the table model
//                        model.addRow(new Object[]{
//                                id,
//                                cateName,
//                        });
//                    }
//                });
//
//                // Add a listener to the addUserFrame's window closing event
//                addCategoryFrame.addWindowListener(new WindowAdapter() {
//                    public void windowClosed(WindowEvent e) {
//                        System.out.println("windowClosed");
//                        // Enable the old frame
//                        setEnabled(true);
//                        setVisible(true);
//                    }
//                    public void windowClosing(WindowEvent e) {
//                        System.out.println("windowClosing");
//                        // Enable the old frame
//                        setEnabled(true);
//                        setVisible(true);
//                    }
//                });
//
//                // Make the addUserFrame visible
//                addCategoryFrame.setVisible(true);
//            }
//        });
//        

        // Action listener for update promotion's date button
        updateDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select an account
                    JOptionPane.showMessageDialog(null, "Please select an promotion to update", "Error", JOptionPane.ERROR_MESSAGE);

                } else{
                    // Get the Id
                    int promotion_id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    System.out.println("id:" + promotion_id);

                    // Disable the old frame
                    setEnabled(false);

                    UpdatePromotionDateFrame updatePromotionDateFrame = new UpdatePromotionDateFrame(new UpdatePromotionDateFrame.PromotionDateUpdated() {
                        public void promotionDateUpdated(LocalDate start_date, LocalDate end_date) {
                            // Update the info in the table
                            int row = table.getSelectedRow();
                            int modelRow = table.convertRowIndexToModel(row);

                            // start_date
                            int start_date_column = 3;
                            int start_date_modelColumn = table.convertColumnIndexToModel(start_date_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(start_date, modelRow, start_date_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, start_date_modelColumn);
                            
                            // end_date
                            int end_date_column = 4;
                            int end_date_modelColumn = table.convertColumnIndexToModel(end_date_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(end_date, modelRow, end_date_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, end_date_modelColumn);

                        }
                    }, promotion_id);

                    // Add a listener to the addUserFrame's window closing event
                    updatePromotionDateFrame.addWindowListener(new WindowAdapter() {
                        public void windowClosed(WindowEvent e) {
                            System.out.println("windowClosed");
                            // Enable the old frame
                            setEnabled(true);
                            setVisible(true);
                        }
                        public void windowClosing(WindowEvent e) {
                            System.out.println("windowClosing");
                            // Enable the old frame
                            setEnabled(true);
                            setVisible(true);
                        }
                    });

                    // Make the addUserFrame visible
                    updatePromotionDateFrame.setVisible(true);
                }
            }
        });

        // Action listener for update promotion's info button
        updateInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select an account
                    JOptionPane.showMessageDialog(null, "Please select an promotion to update", "Error", JOptionPane.ERROR_MESSAGE);

                } else{
                    // Get the Id
                    int promotion_id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    System.out.println("id:" + promotion_id);

                    // Disable the old frame
                    setEnabled(false);

                    UpdatePromotionInfoFrame updatePromotionFrame = new UpdatePromotionInfoFrame(new UpdatePromotionInfoFrame.PromotionUpdated() {
                        public void promotionUpdated(String promoName, String description, int discount, int maxOrder, boolean applyOnce, boolean applyToAnonymous) {
                            // Update the info in the table
                            int row = table.getSelectedRow();
                            int modelRow = table.convertRowIndexToModel(row);

                            // name
                            int name_column = 1;
                            int name_modelColumn = table.convertColumnIndexToModel(name_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(promoName, modelRow, name_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, name_modelColumn);
                            
                            // description
                            int description_column = 2;
                            int description_modelColumn = table.convertColumnIndexToModel(description_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(description, modelRow, description_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, description_modelColumn);

                            // discount
                            int discount_column = 5;
                            int discount_modelColumn = table.convertColumnIndexToModel(discount_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(discount, modelRow, discount_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, discount_modelColumn);
                            
                            // max_order
                            int max_order_column = 6;
                            int max_order_modelColumn = table.convertColumnIndexToModel(max_order_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(maxOrder, modelRow, max_order_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, max_order_modelColumn);
                            
                            // applyOnce
                            int applyOnce_column = 7;
                            int applyOnce_modelColumn = table.convertColumnIndexToModel(applyOnce_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(applyOnce, modelRow, applyOnce_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, applyOnce_modelColumn);
                            
                            // applyToAnonymous
                            int applyToAnonymous_column = 8;
                            int applyToAnonymous_modelColumn = table.convertColumnIndexToModel(applyToAnonymous_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(applyToAnonymous, modelRow, applyToAnonymous_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, applyToAnonymous_modelColumn);
                        }
                    }, promotion_id);

                    // Add a listener to the addUserFrame's window closing event
                    updatePromotionFrame.addWindowListener(new WindowAdapter() {
                        public void windowClosed(WindowEvent e) {
                            System.out.println("windowClosed");
                            // Enable the old frame
                            setEnabled(true);
                            setVisible(true);
                        }
                        public void windowClosing(WindowEvent e) {
                            System.out.println("windowClosing");
                            // Enable the old frame
                            setEnabled(true);
                            setVisible(true);
                        }
                    });

                    // Make the addUserFrame visible
                    updatePromotionFrame.setVisible(true);
                }
            }
        });
        
//        // Action listener for disable a category button
//        disable_Button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Check if a row is selected
//                if (table.getSelectedRow() == -1) {
//                    // Display a message telling the user to select a category
//                    JOptionPane.showMessageDialog(null, "Please select a category to disable", "Error", JOptionPane.ERROR_MESSAGE);
//                } else{
//                    // Confirm
//                    int optionResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to disable the category?", "Confirm", JOptionPane.YES_NO_OPTION);
//                    
//                    if (optionResult == JOptionPane.YES_OPTION) {
//                        // Get the Id of the selected category
//                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
//
//                        System.out.println("id:" + id);
//
//                        // Disable category
//                        int statusCode = business.disableCategory(id);
//                        String status = "";
//                        if(statusCode == -1){
//                            status = "SQL Exception";
//
//                            // Notification
//                            JOptionPane.showMessageDialog(null, status);
//                        } else if(statusCode == -2){
//                            status = "Category not found or already be disabled";
//
//                            // Notification
//                            JOptionPane.showMessageDialog(null, status);
//                        } else if(statusCode == -3){
//                            status = "Disable category fail!";
//
//                            // Notification
//                            JOptionPane.showMessageDialog(null, status);
//                        } else{
//                            status = "Disable category successfully!";
//
//                            // Remove the disabled category from the table
//                            int columnIndex = 0; // id
//                            Object valueToSearch = Integer.valueOf(id);
//
//                            for (int i = 0; i < model.getRowCount(); i++) {
//                                // Get the value at the specified column of the current row
//                                Object cellValue = model.getValueAt(i, columnIndex);
//
//                                // Check if the value of the cell is equal to the value you are looking for
//                                if (valueToSearch.equals(cellValue)) {
//                                    // If the value is found, remove the row from the model
//                                    model.removeRow(i);
//                                    break;
//                                }
//                            }
//
//                            // Notification
//                            JOptionPane.showMessageDialog(null, status);
//                        }
//                    }
//                }
//            }
//        });
//        
//        // Action listener for disable a category button
//        disabled_List_Button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Disable the old frame
//                setEnabled(false);
//
//                DisabledCategoryListFrame disabledCategoryListFrame = new DisabledCategoryListFrame(new DisabledCategoryListFrame.CategoryEnabled() {
//                    public void categoryEnabled(int id, String cateName) {
//                        // Add a row to the table model
//                        model.addRow(new Object[]{
//                                id,
//                                cateName,
//                        });
//                    }
//                });
//
//                // Add a listener to the addUserFrame's window closing event
//                disabledCategoryListFrame.addWindowListener(new WindowAdapter() {
//                    public void windowClosed(WindowEvent e) {
//                        System.out.println("windowClosed");
//                        // Enable the old frame
//                        setEnabled(true);
//                        setVisible(true);
//                    }
//                    public void windowClosing(WindowEvent e) {
//                        System.out.println("windowClosing");
//                        // Enable the old frame
//                        setEnabled(true);
//                        setVisible(true);
//                    }
//                });
//
//                // Make the addUserFrame visible
//                disabledCategoryListFrame.setVisible(true);
//            }
//        });

        // Action listener for Enable/Disable button
        open_close_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select an account
                    JOptionPane.showMessageDialog(null, "Please select an promotion to open/close", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    // Get the ID and status of the selected promotion
                    int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    Boolean isOpen = (Boolean) table.getValueAt(table.getSelectedRow(), 9);

                    System.out.println("id:" + id);
                    System.out.println("isOpen:" + isOpen);

                    String status;
                    int row = table.getSelectedRow();
                    int column = 9;
                    if (isOpen) {
                        // Close promotion
                        status = business.closePromotion(id);

                        // Update the status in the table
                        int modelRow = table.convertRowIndexToModel(row);
                        int modelColumn = table.convertColumnIndexToModel(column);
                        ((DefaultTableModel) table.getModel()).setValueAt(false, modelRow, modelColumn);
                        ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, modelColumn);
                    } else {
                        // Open account
                        status = business.openPromotion(id);

                        // Update the status in the table
                        int modelRow = table.convertRowIndexToModel(row);
                        int modelColumn = table.convertColumnIndexToModel(column);
                        ((DefaultTableModel) table.getModel()).setValueAt(true, modelRow, modelColumn);
                        ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, modelColumn);
                    }
                    JOptionPane.showMessageDialog(null, status);
                }
            }
        });
    }
}
