package presentation;

import business.AuthorBU;
import pojo.AuthorPOJO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateAuthorFrame extends JFrame implements ActionListener {
    JPanel panel;
    JLabel idLabel, nameLabel, dobLabel, hometownLabel, msgLabel;
    JTextField idField, nameField, dobField, hometownField;
    JButton updateButton;

    public UpdateAuthorFrame() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel msgPanel = new JPanel();
        msgLabel = new JLabel("Update author");
        msgPanel.add(msgLabel);

        JPanel idPanel = new JPanel();
        idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel namePanel = new JPanel();
        nameLabel = new JLabel("New name:");
        nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel dobPanel = new JPanel();
        dobLabel = new JLabel("New date of birth:");
        dobField = new JTextField(20);
        dobPanel.add(dobLabel);
        dobPanel.add(dobField);

        JPanel hometownPanel = new JPanel();
        hometownLabel = new JLabel("New hometown:");
        hometownField = new JTextField(20);
        hometownPanel.add(hometownLabel);
        hometownPanel.add(hometownField);

        JPanel updateButtonPanel = new JPanel();
        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        updateButtonPanel.add(updateButton);

        panel.add(msgPanel);
        panel.add(idPanel);
        panel.add(namePanel);
        panel.add(dobPanel);
        panel.add(hometownPanel);
        panel.add(updateButtonPanel);
        add(panel);

        setTitle("Update author");
        setSize(500, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getId() {
        return Integer.parseInt(idField.getText());
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
        int id = getId();
        AuthorBU authorBU = new AuthorBU();
        AuthorPOJO author = authorBU.getAuthor(id);
        if(author != null){
            author.setName(getName());
            author.setDob(getDob());
            author.setHomeTown(getHomeTown());
            boolean ans = authorBU.updateAuthor(author);
            if(ans){
                JOptionPane.showMessageDialog(this, "Update author successfully");
            }
            else{
                JOptionPane.showMessageDialog(this, "Update author failed");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Author not found");
        }
    }
}