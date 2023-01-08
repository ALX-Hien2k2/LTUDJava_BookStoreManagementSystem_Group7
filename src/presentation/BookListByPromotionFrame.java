package presentation;

import business.book_business;
import pojo.book;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class BookListByPromotionFrame extends JFrame{
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scroll;
    
    private JTextField searchField;
    private JButton searchButton;
    private JPanel searchPanel;
    
    private JPanel actionPanel;
    private String headers[] = { "Id", "Book name", "Price", "Quantity", "Author", "Publisher", "Category", "Promotion_id"};
    private book_business business;
    private List<book> books;

    public BookListByPromotionFrame(int promo_id) {
        // Set the title of the frame
        super("List of Books by Promotion");
        
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
        
        business = new book_business();
        books = business.getBookByPromotion(promo_id);
        
        searchField = new JTextField(); // Search by name
        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton = new JButton("Search");

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
        
        TableColumn price_column = table.getColumnModel().getColumn(2); // price
        price_column.setPreferredWidth(50);
        price_column.setResizable(true);

        TableColumn quantity_column = table.getColumnModel().getColumn(3); // quantity
        quantity_column.setPreferredWidth(50);
        quantity_column.setResizable(false);
        
        TableColumn author_column = table.getColumnModel().getColumn(4); // author
        author_column.setPreferredWidth(50);
        author_column.setResizable(true);
        
        TableColumn publisher_column = table.getColumnModel().getColumn(5); // publisher
        publisher_column.setPreferredWidth(50);
        publisher_column.setResizable(true);
        
        TableColumn category_column = table.getColumnModel().getColumn(6); // category
        category_column.setPreferredWidth(50);
        category_column.setResizable(true);
        
        TableColumn promotion_id_column = table.getColumnModel().getColumn(7); // promotion_id
        promotion_id_column.setPreferredWidth(10);
        promotion_id_column.setResizable(false);
        
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
        for (book book : books) {
            model.addRow(new Object[] {
                    book.getId(),
                    book.getName(),
                    book.getPrice(),
                    book.getQuantity(),
                    book.getAuthor(),
                    book.getPublisher(),
                    book.getCategory(),
                    book.getPromotion_id(),
            });
        }

        // Create a panel for the search field and button
        searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(searchPanel);

        // Add components to frame
        add(scroll, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        // Set the size and location of the frame
        setSize(1200, 500);
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
                List<book> searchResults = business.searchBookByPromotion(name, promo_id);

                // Clear the table model
                model.setRowCount(0);

                // Add the search results to the table model
                for (book result : searchResults) {
                    model.addRow(new Object[] {
                        result.getId(),
                        result.getName(),
                        result.getPrice(),
                        result.getQuantity(),
                        result.getAuthor(),
                        result.getPublisher(),
                        result.getCategory(),
                        result.getPromotion_id(),
                    });
                }
            }
        });
    }
}