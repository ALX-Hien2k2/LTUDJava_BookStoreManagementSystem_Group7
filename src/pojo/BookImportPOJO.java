/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

import java.time.LocalDate;

/**
 *
 * @author TIN
 */
public class BookImportPOJO {
    int id;
    LocalDate day;
    int staffId;
    double total;
    String name;
    public BookImportPOJO() {
    }

    public BookImportPOJO(int id, LocalDate day, int staffId, double total, String name) {
        this.id = id;
        this.day = day;
        this.staffId = staffId;
        this.total = total;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
}
