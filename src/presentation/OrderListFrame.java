package presentation;

import business.OrderBU;
import pojo.OrderPOJO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;

public class OrderListFrame extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;

    private JButton detail_Button;
    private JPanel detail_Panel;

    private JButton addNewOrderButton;
    private JPanel addNewOrderPanel;

    private JPanel actionPanel;
    private String headers[] = {"ID", "Date", "Member", "Staff"};
    private OrderBU business;
    private List<OrderPOJO> orders;

    public OrderListFrame() {
        // Set the title of the frame
        super("List of Orders");

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

        business = new OrderBU();
        orders = business.getAllOrder();

        detail_Button = new JButton("View detail");

        addNewOrderButton = new JButton("Add");

        // Config components
        // Table model
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        // Table
        TableColumn id_column = table.getColumnModel().getColumn(0);
        id_column.setPreferredWidth(20);
        id_column.setResizable(false);

        TableColumn date = table.getColumnModel().getColumn(1); 
        date.setPreferredWidth(30);
        date.setResizable(false);

        TableColumn member = table.getColumnModel().getColumn(2); 
        member.setPreferredWidth(50);
        member.setResizable(false);

        TableColumn staff = table.getColumnModel().getColumn(3); 
        staff.setPreferredWidth(50);
        staff.setResizable(false);

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
        for (OrderPOJO order : orders) {
            model.addRow(new Object[]{
                    order.getId(),
                    order.getDate(),
                    order.getMember(),
                    order.getStaff(),
                });
        }

        // Create a panel for the disable button and view disabled list
        detail_Panel = new JPanel();
        detail_Panel.add(new JLabel("View detail of order:"));
        detail_Panel.add(detail_Button);

        // Create a panel for the add new Order button
        addNewOrderPanel = new JPanel();
        addNewOrderPanel.add(new JLabel("Add new order:"));
        addNewOrderPanel.add(addNewOrderButton);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(detail_Panel);
        actionPanel.add(addNewOrderPanel);

        // Add components to frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Set the size and location of the frame
        setSize(800, 400);
        setLocationRelativeTo(null);

        // Exit the application when the frame is closed
  

        // Set the visibility of the frame
        setVisible(true);

        // Add action listener for the detail button
        detail_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row
                int selectedRow = table.getSelectedRow();

                // If no row is selected, show a message dialog
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a order to view detail", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Get the id of the selected row
                    int id = (int) table.getValueAt(selectedRow, 0);

                    // Create a new OrderDetailFrame
                    OrderDetailFrame detailFrame = new OrderDetailFrame(id);
                }
            }
        });
    }
}
