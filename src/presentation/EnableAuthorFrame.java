package presentation;

import business.AuthorBU;
import pojo.AuthorPOJO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnableAuthorFrame extends JFrame implements ActionListener {
    JPanel panel;
    JLabel idLabel, msgLabel;
    JTextField idField;
    JButton submitButton;

    public EnableAuthorFrame() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel msgPanel = new JPanel();
        msgLabel = new JLabel("Enable author");
        msgPanel.add(msgLabel);

        JPanel idPanel = new JPanel();
        idLabel = new JLabel("ID: ");
        idField = new JTextField(20);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel submitButtonPanel = new JPanel();
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButtonPanel.add(submitButton);

        panel.add(msgPanel);
        panel.add(idPanel);
        panel.add(submitButtonPanel);
        add(panel);

        setTitle("Enable author");
        setSize(500, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id = Integer.parseInt(idField.getText());
        AuthorBU authorBU = new AuthorBU();
        AuthorPOJO author = authorBU.getAuthor(id);
        if(author != null){
            boolean ans = authorBU.enableAuthor(id);
            if(ans){
                JOptionPane.showMessageDialog(this, "Enable author successfully");
            }
            else{
                JOptionPane.showMessageDialog(this, "Enable author failed");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Author not found");
        }
    }
}