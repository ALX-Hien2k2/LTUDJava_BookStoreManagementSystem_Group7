package pojo;

import java.time.LocalDate;

public class ImportSheetPOJO {
    int id;
    LocalDate date;
    String staff;
    double total_cost;

    public ImportSheetPOJO() {

    }

    public ImportSheetPOJO(LocalDate date, String staff, double total_cost) {
        this.date = date;
        this.staff = staff;
        this.total_cost = total_cost;
    }

    public ImportSheetPOJO(int id, LocalDate date, String staff, double total_cost) {
        this.id = id;
        this.date = date;
        this.staff = staff;
        this.total_cost = total_cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public double getTotalCost() {
        return total_cost;
    }

    public void setTotalCost(double total_cost) {
        this.total_cost = total_cost;
    }

    @Override
    public String toString() {
        return "ImportSheetPOJO{" + "id=" + id + ", date=" + date + ", staff=" + staff + ", total_cost=" + total_cost + '}';
    }
}
