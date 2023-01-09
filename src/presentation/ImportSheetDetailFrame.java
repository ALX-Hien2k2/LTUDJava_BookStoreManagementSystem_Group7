// Import sheet detail frame
package presentation;

import business.ImportSheetBU;
import pojo.ImportSheetPOJO;
import pojo.DetailImportSheetPOJO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;

public class ImportSheetDetailFrame extends JFrame {

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;

    private JPanel actionPanel;
    private String headers[] = {"ID", "Book", "Quantity", "Import Price", "Total Cost"};
    private ImportSheetBU business;
    private List<DetailImportSheetPOJO> importSheets;

    public ImportSheetDetailFrame(int id) {
        // Set the title of the frame
        super("Import sheet detail");

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

        business = new ImportSheetBU();
        importSheets = business.getDetailImportSheet(id);

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

        TableColumn import_price_column = table.getColumnModel().getColumn(3);
        import_price_column.setPreferredWidth(50);
        import_price_column.setResizable(false);

        TableColumn total_cost_column = table.getColumnModel().getColumn(4);
        total_cost_column.setPreferredWidth(50);
        total_cost_column.setResizable(false);

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
        for (DetailImportSheetPOJO importSheet : importSheets) {
            model.addRow(new Object[]{
                    importSheet.getId(),
                    importSheet.getBook(),
                    importSheet.getQuantity(),
                    importSheet.getImportPrice(),
                    importSheet.getTotalCost()
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