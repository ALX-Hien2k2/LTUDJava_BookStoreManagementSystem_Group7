/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author TIN
 */
public class book {
    int id;
    String name;
    double price;
    int quantity;
    int author_id;
    int publisher_id;
    int category_id;

    public book() {
        
    }

    public book(int id, String name, double price, int quantity, int author_id, int publisher_id, int category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.author_id = author_id;
        this.publisher_id = publisher_id;
        this.category_id = category_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
    public String toString(){
        return id + "   " + name + "    " + price + "   " + quantity + "    " + author_id + "   " + publisher_id + "    " + category_id;
    }
}
