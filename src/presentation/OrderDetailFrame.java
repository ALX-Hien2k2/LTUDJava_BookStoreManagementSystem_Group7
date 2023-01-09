// Import sheet detail frame
package presentation;

import business.OrderBU;
import pojo.OrderPOJO;
import pojo.DetailOrderPOJO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;

public class OrderDetailFrame extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;

    private JPanel actionPanel;
    private String headers[] = {"ID", "Book", "Quantity", "Cost", "Final Cost", "Promotion"};
    private OrderBU business;
    private List<DetailOrderPOJO> orders;

    public OrderDetailFrame(int id) {
        // Set the title of the frame
        super("Order detail");

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
        orders = business.getDetailOrder(id);

        // Config components
        // Table model
        model.setColumnIdentifiers(headers);
        table.setModel(model);

        // Table
        // Table
        TableColumn id_column = table.getColumnModel().getColumn(0);
        id_column.setPreferredWidth(20);
        id_column.setResizable(false);

        TableColumn book_title_column = table.getColumnModel().getColumn(1); 
        book_title_column.setPreferredWidth(300);
        book_title_column.setResizable(false);

        TableColumn quantity_column = table.getColumnModel().getColumn(2); 
        quantity_column.setPreferredWidth(30);
        quantity_column.setResizable(false);

        TableColumn cost_column = table.getColumnModel().getColumn(3);
        cost_column.setPreferredWidth(50);
        cost_column.setResizable(false);

        TableColumn final_cost_column = table.getColumnModel().getColumn(4);
        final_cost_column.setPreferredWidth(50);
        final_cost_column.setResizable(false);

        TableColumn promotion_column = table.getColumnModel().getColumn(5);
        promotion_column.setPreferredWidth(200);
        promotion_column.setResizable(false);

        table.getTableHeader().setReorderingAllowed(false);

        // Scroll
        scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(800, 400));

        // Action panel
        actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Add components to the frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Add data to table
        for (DetailOrderPOJO order : orders) {
            model.addRow(new Object[]{
                    order.getId(),
                    order.getBook(),
                    order.getQuantity(),
                    order.getCost(),
                    order.getFinalCost(),
                    order.getPromoName()
            });
        }

        // Set size of the frame
        setSize(800, 500);

        // Set location of the frame
        setLocationRelativeTo(null);

        // Set default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set visible
        setVisible(true);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        actionPanel.add(backButton);
    }
}