package presentation;

import business.CategoryBU;
import pojo.CategoryPOJO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;

public class CategoryListFrame extends JFrame{
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;
    
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    
    private JButton disable_Button;
    private JPanel disable_Panel;
    
    private JButton disabled_List_Button;
    private JPanel disabled_List_Panel;
    
    private JButton addNewCategoryButton;
    private JPanel addNewCategoryPanel;
    
    private JButton updateButton;
    private JPanel updatePanel;
    
    private JPanel actionPanel;
    private String headers[] = { "Id", "Category name"};
    private CategoryBU business;
    private List<CategoryPOJO> categories;

    public CategoryListFrame() {
        // Set the title of the frame
        super("List of Categories");

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
        
        business = new CategoryBU();
        categories = business.getAllCategory();
        
        searchField = new JTextField(); // Search by name
        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton = new JButton("Search");
        
        disable_Button = new JButton("Disable/Hide");
        
        disabled_List_Button = new JButton("View list");
        
        addNewCategoryButton =  new JButton("Add");
        
        updateButton = new JButton("Update");

        // Config components
        // Table model
        model.setColumnIdentifiers(headers);
        table.setModel(model);
        
        // Table
        TableColumn id_column = table.getColumnModel().getColumn(0); // id
        id_column.setPreferredWidth(30);
        id_column.setResizable(false);
        
        TableColumn name_column = table.getColumnModel().getColumn(1); // name
        name_column.setPreferredWidth(700);
        name_column.setResizable(false);
        
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
        for (CategoryPOJO category : categories) {
            model.addRow(new Object[] {
                    category.getId(),
                    category.getName(),
            });
        }

        // Create a panel for the search field and button
        searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create a panel for the disable button and view disabled list
        disable_Panel = new JPanel();
        disable_Panel.add(new JLabel("Disable/Hide category:"));
        disable_Panel.add(disable_Button);
        disable_Panel.add(new JLabel("View disabled categories list:"));
        disable_Panel.add(disabled_List_Button);

        // Create a panel for the add new category button
        addNewCategoryPanel = new JPanel();
        addNewCategoryPanel.add(new JLabel("Add new category:"));
        addNewCategoryPanel.add(addNewCategoryButton);

        // Create a panel for the update category's info button
        updatePanel = new JPanel();
        updatePanel.add(new JLabel("Update category's info:"));
        updatePanel.add(updateButton);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(searchPanel);
        actionPanel.add(disable_Panel);
        actionPanel.add(addNewCategoryPanel);
        actionPanel.add(updatePanel);

        // Add components to frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Set the size and location of the frame
        setSize(800, 400);
        setLocationRelativeTo(null);

        // Exit the application when the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Action listener for Search button (Search by name)
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the name from the search field
                String name = searchField.getText();

                // Search for category
                List<CategoryPOJO> searchResults = business.searchCategory(name);

                // Clear the table model
                model.setRowCount(0);

                // Add the search results to the table model
                for (CategoryPOJO result : searchResults) {
                    model.addRow(new Object[] {
                            result.getId(),
                            result.getName(),
                    });
                }
            }
        });
        
        // Action listener for Add new category button
        addNewCategoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disable the old frame
                setEnabled(false);

                AddCategoryFrame addCategoryFrame = new AddCategoryFrame(new AddCategoryFrame.CategoryInserted() {
                    public void categoryInserted(int id, String cateName) {
                        // Add a row to the table model
                        model.addRow(new Object[]{
                                id,
                                cateName,
                        });
                    }
                });

                // Add a listener to the addUserFrame's window closing event
                addCategoryFrame.addWindowListener(new WindowAdapter() {
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
                addCategoryFrame.setVisible(true);
            }
        });
        
        // Action listener for update category's info button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select an account
                    JOptionPane.showMessageDialog(null, "Please select an category to update", "Error", JOptionPane.ERROR_MESSAGE);

                } else{
                    // Get the Id
                    int category_id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    System.out.println("id:" + category_id);

                    // Disable the old frame
                    setEnabled(false);

                    UpdateCategoryInfoFrame updateCategoryFrame = new UpdateCategoryInfoFrame(new UpdateCategoryInfoFrame.CategoryUpdated() {
                        public void categoryUpdated(String cateName) {
                            // Update the info in the table
                            int row = table.getSelectedRow();
                            int modelRow = table.convertRowIndexToModel(row);

                            // name
                            int name_column = 1;
                            int name_modelColumn = table.convertColumnIndexToModel(name_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(cateName, modelRow, name_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, name_modelColumn);

                        }
                    }, category_id);

                    // Add a listener to the addUserFrame's window closing event
                    updateCategoryFrame.addWindowListener(new WindowAdapter() {
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
                    updateCategoryFrame.setVisible(true);
                }
            }
        });
    }
}
