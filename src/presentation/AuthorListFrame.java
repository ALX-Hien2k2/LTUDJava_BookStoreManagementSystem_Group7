package presentation;

import business.AuthorBU;
import pojo.AuthorPOJO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorListFrame extends JFrame implements ActionListener {
    AuthorListFrame() {
        AuthorBU business = new AuthorBU();
        List<AuthorPOJO> authors = business.getAll();

        JFrame frame;
        frame = new JFrame();
        frame.setTitle("Authors list");

        String[] columnNames = {"ID", "Name", "Date of birth", "Hometown", "isActive"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        for (int i = 0; i < authors.size(); i++) {
            int id = authors.get(i).getId();
            String name = authors.get(i).getName();
            String dob = authors.get(i).getDob();
            String homeTown = authors.get(i).getHomeTown();
            boolean isActive = authors.get(i).isActive();
            Object[] data = {id, name, dob, homeTown, isActive};
            tableModel.addRow(data);
        }

        table.setBounds(30, 40, 250, 300);
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);

        frame.setSize(500, 200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

    }
}
