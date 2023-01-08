/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

/**
 *
 * @author TIN
 */
import pojo.book;
import java.util.*;
import dataaccess.book_da;

public class book_business {

    public List<book> getAll() {
        book_da da = new book_da();
        return da.getAll();
    }

    public List<book> getOutOfStock() {
        book_da da = new book_da();
        return da.getOutOfStock();
    }

    public void Disable_book(int id) {
        book_da da = new book_da();
        da.Disable_book(id);
    }

    public void Enable_book(int id) {
        book_da da = new book_da();
        da.Enable_book(id);
    }

    public boolean check_name_exists(String name) {
        book_da da = new book_da();
        return da.check_book_exists(name);
    }

    public int check_id_by_name(String name, String table) {
        book_da da = new book_da();
        return da.get_id_by_name(name, table);
    }

    public boolean add_book(String name, double price, int quant, int author, int publisher, int category) {
        book_da da = new book_da();
        return da.add_book(name, price, quant, author, publisher, category);
    }

    public void update_string(String name, int id) {
        book_da da = new book_da();
        da.update_string(name, id);
    }

    public void update_double(double name, int id) {
        book_da da = new book_da();
        da.update_double(name, id);
    }

    public void update_int(int name, int id, String target) {
        book_da da = new book_da();
        da.update_int(name, id, target);
    }
}
