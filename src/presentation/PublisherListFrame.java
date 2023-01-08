package presentation;

import business.PublisherBU;
import pojo.PublisherPOJO;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Comparator;
import java.util.List;

public class PublisherListFrame extends JFrame{
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

    private JButton addNewPublisherButton;
    private JPanel addNewPublisherPanel;

    private JButton updateButton;
    private JPanel updatePanel;

    private JPanel actionPanel;
    private String headers[] = { "ID", "Name", "Country", "Status"};
    private PublisherBU business;
    private List<PublisherPOJO> publishers;

    public PublisherListFrame() {
        // Set the title of the frame
        super("List of Publishers");

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

        business = new PublisherBU();
        publishers = business.getAll();

        searchField = new JTextField(); // Search by name
        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton = new JButton("Search");

        disable_Button = new JButton("Disable/Hide");

        disabled_List_Button = new JButton("View list");

        addNewPublisherButton =  new JButton("Add");

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
        name_column.setPreferredWidth(500);
        name_column.setResizable(false);
        
         TableColumn country_column = table.getColumnModel().getColumn(2); // name
        country_column.setPreferredWidth(300);
        country_column.setResizable(false);
        
         TableColumn status_column = table.getColumnModel().getColumn(3); // name
        status_column.setPreferredWidth(100);
        status_column.setResizable(false);

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
        for (PublisherPOJO publisher : publishers) {
            model.addRow(new Object[] {
                    publisher.getId(),
                    publisher.getName(),
                    publisher.getCountry(),
                    publisher.getStatus(),
            });
        }

        // Create a panel for the search field and button
        searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search by name:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Create a panel for the disable button and view disabled list
        disable_Panel = new JPanel();
        disable_Panel.add(new JLabel("Disable/Hide publisher:"));
        disable_Panel.add(disable_Button);
        disable_Panel.add(new JLabel("View disabled publishers list:"));
        disable_Panel.add(disabled_List_Button);

        // Create a panel for the add new publisher button
        addNewPublisherPanel = new JPanel();
        addNewPublisherPanel.add(new JLabel("Add new publisher:"));
        addNewPublisherPanel.add(addNewPublisherButton);

        // Create a panel for the update publisher's info button
        updatePanel = new JPanel();
        updatePanel.add(new JLabel("Update publisher's info:"));
        updatePanel.add(updateButton);

        // Create a panel for action buttons
        actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
        actionPanel.add(searchPanel);
        actionPanel.add(disable_Panel);
        actionPanel.add(addNewPublisherPanel);
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

                // Search for publisher
                List<PublisherPOJO> searchResults = business.searchPublisher(name);

                // Clear the table model
                model.setRowCount(0);

                // Add the search results to the table model
                for (PublisherPOJO result : searchResults) {
                    model.addRow(new Object[] {
                            result.getId(),
                            result.getName(),
                            result.getCountry(),
                            result.getStatus(),
                    });
                }
            }
        });

        // Action listener for Add new publisher button
        addNewPublisherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Disable the old frame
                setEnabled(false);

                AddPublisherFrame addPublisherFrame = new AddPublisherFrame(new AddPublisherFrame.PublisherInserted() {
                    public void publisherInserted(int id, String name, String country, boolean status) {
                        // Add a row to the table model
                        model.addRow(new Object[]{
                                id,
                                name,
                                country,
                                status
                        });
                    }
                });

                // Add a listener to the addUserFrame's window closing event
                addPublisherFrame.addWindowListener(new WindowAdapter() {
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
                addPublisherFrame.setVisible(true);
            }
        });
    }
}