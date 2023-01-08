/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author TIN
 */
public class OrderPOJO {
    int orderId;
    int memberId;
    int employeeId;
    List<book> books;

    public OrderPOJO() {
    }

    public OrderPOJO(int orderId, int memberId, int employeeId, LocalDate day, double total) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.employeeId = employeeId;
        this.day = day;
        this.total = total;
    }

    public OrderPOJO(int orderId, int memberId, int employeeId, List<book> books, LocalDate day, double total) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.employeeId = employeeId;
        this.books = books;
        this.day = day;
        this.total = total;
    }
    
    LocalDate day;
    double total;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<book> getBooks() {
        return books;
    }

    public void setBooks(List<book> books) {
        this.books = books;
    }
    
}
