package presentation;

import business.AuthorBU;
import pojo.AuthorPOJO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAuthorFrame extends JFrame implements ActionListener {
    JPanel panel;
    JLabel nameLabel, dobLabel, hometownLabel, msgLabel;
    JTextField nameField, dobField, hometownField;
    JButton addButton;

    public AddAuthorFrame() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel msgPanel = new JPanel();
        msgLabel = new JLabel("Add author");
        msgPanel.add(msgLabel);

        JPanel namePanel = new JPanel();
        nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel dobPanel = new JPanel();
        dobLabel = new JLabel("Date of birth:");
        dobField = new JTextField(20);
        dobPanel.add(dobLabel);
        dobPanel.add(dobField);

        JPanel hometownPanel = new JPanel();
        hometownLabel = new JLabel("Hometown:");
        hometownField = new JTextField(20);
        hometownPanel.add(hometownLabel);
        hometownPanel.add(hometownField);

        JPanel addButtonPanel = new JPanel();
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        addButtonPanel.add(addButton);

        panel.add(msgPanel);
        panel.add(namePanel);
        panel.add(dobPanel);
        panel.add(hometownPanel);
        panel.add(addButtonPanel);
        add(panel);

        setTitle("Add author");
        setSize(500, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getName() {
        return nameField.getText();
    }

    public String getDob() {
        return dobField.getText();
    }

    public String getHomeTown() {
        return hometownField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AuthorPOJO author = new AuthorPOJO();
        author.setName(getName());
        author.setDob(getDob());
        author.setHomeTown(getHomeTown());

        AuthorBU authorBU = new AuthorBU();
        boolean ans = authorBU.addAuthor(author);

        if (ans) {
            System.out.println("Add a author successfully");
            JOptionPane.showMessageDialog(this, "Add author successfully");
        } else {
            System.out.println("Add a author failed");
            JOptionPane.showMessageDialog(this, "Add author failed");
        }
    }
}