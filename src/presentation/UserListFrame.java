package presentation;

import business.UserBU;
import pojo.UserPOJO;
import constant.Constant_var;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class UserListFrame extends JFrame{
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    private JButton enable_disable_Button;
    private JPanel enable_disable_Panel;
    private JButton addNewAccountButton;
    private JPanel addNewAccountPanel;
    private JButton resetPasswordButton;
    private JPanel resetPasswordPanel;
    private JButton updateButton;
    private JPanel updatePanel;
    private JPanel actionPanel;
    private String headers[] = { "Id", "Username", "Password", "Fullname", "Dob", "Role", "IsActive" };
    private UserBU business;
    private List<UserPOJO> users;
    private Constant_var constant_var;

    public UserListFrame() {
        // Set the title of the frame
        super("List of User Accounts");

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
        business = new UserBU();
        constant_var = new Constant_var();
        users = business.getAllAccount();
        searchField = new JTextField(); // Search by name
        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton = new JButton("Search");
        enable_disable_Button = new JButton("Enable/Disable");
        addNewAccountButton =  new JButton("Add");
        resetPasswordButton=  new JButton("Reset");
        updateButton = new JButton("Update");

        // Config components
        // Table model
        model.setColumnIdentifiers(headers);

        // Table
        table.setModel(model);
        TableColumn id_column = table.getColumnModel().getColumn(0); // id
        id_column.setPreferredWidth(1);
        id_column.setResizable(false);
        TableColumn dob_column = table.getColumnModel().getColumn(4); // dob
        dob_column.setPreferredWidth(1);
        dob_column.setResizable(false);
        TableColumn role_column = table.getColumnModel().getColumn(5); // role
        role_column.setPreferredWidth(1);
        role_column.setResizable(false);
        TableColumn isActive_column = table.getColumnModel().getColumn(6); // isActive
        isActive_column.setPreferredWidth(1);
        isActive_column.setResizable(false);
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

        // Add data to table
        for (UserPOJO user : users) {
            model.addRow(new Object[] {
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getFullname(),
                    user.getDob(),
                    user.getRole(),
                    user.getActive(),
            });
        }

        // Create a panel for the search field and button
        searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create a panel for the enable/disable buttons
        enable_disable_Panel = new JPanel();
        enable_disable_Panel.add(new JLabel("Enable/Disable account:"));
        enable_disable_Panel.add(enable_disable_Button);

        // Create a panel for the add new user account button
        addNewAccountPanel = new JPanel();
        addNewAccountPanel.add(new JLabel("Add new Account:"));
        addNewAccountPanel.add(addNewAccountButton);

        // Create a panel for the reset password button
        resetPasswordPanel = new JPanel();
        resetPasswordPanel.add(new JLabel("Reset Account's password:"));
        resetPasswordPanel.add(resetPasswordButton);

        // Create a panel for the update account's info button
        updatePanel = new JPanel();
        updatePanel.add(new JLabel("Update account's info:"));
        updatePanel.add(updateButton);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(searchPanel);
        actionPanel.add(enable_disable_Panel);
        actionPanel.add(addNewAccountPanel);
        actionPanel.add(resetPasswordPanel);
        actionPanel.add(updatePanel);

        // Add components to frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Set the size and location of the frame
        setSize(800, 400);
        setLocationRelativeTo(null);

        // Exit the application when the frame is closed
        


        // Action listener for Search button (Search by name)
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the name from the search field
                String name = searchField.getText();

                // Search for user accounts
                List<UserPOJO> searchResults = business.searchAccount(name);

                // Clear the table model
                model.setRowCount(0);

                // Add the search results to the table model
                for (UserPOJO result : searchResults) {
                    model.addRow(new Object[] {
                            result.getId(),
                            result.getUsername(),
                            result.getPassword(),
                            result.getFullname(),
                            result.getDob(),
                            result.getRole(),
                            result.getActive(),
                    });
                }
            }
        });

        // Action listener for Enable/Disable button
        enable_disable_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select an account
                    JOptionPane.showMessageDialog(null, "Please select an account to enable/disable", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    // Get the ID and status of the selected account
                    int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    Boolean isActive = (Boolean) table.getValueAt(table.getSelectedRow(), 6);

                    System.out.println("id:" + id);
                    System.out.println("isActive:" + isActive);

                    String status;
                    int row = table.getSelectedRow();
                    int column = 6;
                    if (isActive) {
                        // Disable account
                        status = business.disableAccount(id);

                        // Update the status in the table
                        int modelRow = table.convertRowIndexToModel(row);
                        int modelColumn = table.convertColumnIndexToModel(column);
                        ((DefaultTableModel) table.getModel()).setValueAt(false, modelRow, modelColumn);
                        ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, modelColumn);
                    } else {
                        // Enable account
                        status = business.enableAccount(id);

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

        // Action listener for Add new account button
        addNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disable the old frame
                setEnabled(false);

                AddUserFrame addUserFrame = new AddUserFrame(new AddUserFrame.AccountInserted() {
                    public void accountInserted(int id, String fullname, String username, String password, LocalDate dob, String role, Boolean isActive) {
                        // Add a row to the table model
                        model.addRow(new Object[]{
                                id,
                                username,
                                password,
                                fullname,
                                dob,
                                role,
                                isActive
                        });
                    }
                });

                // Add a listener to the addUserFrame's window closing event
                addUserFrame.addWindowListener(new WindowAdapter() {
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
                addUserFrame.setVisible(true);
            }
        });

        // Action listener for Reset password button
        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select an account
                    JOptionPane.showMessageDialog(null, "Please select an account to reset password", "Error", JOptionPane.ERROR_MESSAGE);
                } else{
                    // Confirm
                    int optionResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the password?", "Confirm", JOptionPane.YES_NO_OPTION);

                    if (optionResult == JOptionPane.YES_OPTION) {
                        // Get the ID
                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);

                        System.out.println("id:" + id);
                        String status = business.resetPassword(id);

                        // Update the status in the table
                        int row = table.getSelectedRow();
                        int column = 2;
                        int modelRow = table.convertRowIndexToModel(row);
                        int modelColumn = table.convertColumnIndexToModel(column);
                        ((DefaultTableModel) table.getModel()).setValueAt(constant_var.getDefault_password(), modelRow, modelColumn);
                        ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, modelColumn);

                        // Pop up notification
                        JOptionPane.showMessageDialog(null, status);
                    }
                }
            }
        });

        // Action listener for update account's info button
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a row is selected
                if (table.getSelectedRow() == -1) {
                    // Display a message telling the user to select an account
                    JOptionPane.showMessageDialog(null, "Please select an account to update", "Error", JOptionPane.ERROR_MESSAGE);

                } else{
                    // Get the Id
                    int account_id = (int) table.getValueAt(table.getSelectedRow(), 0);
                    System.out.println("id:" + account_id);

                    // Disable the old frame
                    setEnabled(false);

                    UpdateAccountInfoFrame updateAccountInfoFrame = new UpdateAccountInfoFrame(new UpdateAccountInfoFrame.AccountUpdated() {
                        public void accountUpdated(String fullname, String username, LocalDate dob, String role) {
                            // Update the info in the table
                            int row = table.getSelectedRow();
                            int modelRow = table.convertRowIndexToModel(row);

                            // username
                            int username_column = 1;
                            int username_modelColumn = table.convertColumnIndexToModel(username_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(username, modelRow, username_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, username_modelColumn);

                            // fullname
                            int fullname_column = 3;
                            int fullname_modelColumn = table.convertColumnIndexToModel(fullname_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(fullname, modelRow, fullname_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, fullname_modelColumn);

                            // dob
                            int dob_column = 4;
                            int dob_modelColumn = table.convertColumnIndexToModel(dob_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(dob, modelRow, dob_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, dob_modelColumn);

                            // role
                            int role_column = 5;
                            int role_modelColumn = table.convertColumnIndexToModel(role_column);
                            ((DefaultTableModel) table.getModel()).setValueAt(role, modelRow, role_modelColumn);
                            ((DefaultTableModel) table.getModel()).fireTableCellUpdated(modelRow, role_modelColumn);
                        }
                    }, account_id);

                    // Add a listener to the addUserFrame's window closing event
                    updateAccountInfoFrame.addWindowListener(new WindowAdapter() {
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
                    updateAccountInfoFrame.setVisible(true);
                }
            }
        });
    }
}