package pojo;

import java.time.LocalDate;

public class OrderPOJO {
    int id;
    LocalDate date;
    String member;
    String staff;

    public OrderPOJO() {

    }

    public OrderPOJO(LocalDate date, String member, String staff) {
        this.date = date;
        this.member = member;
        this.staff = staff;
    }

    public OrderPOJO(int id, LocalDate date, String member, String staff) {
        this.id = id;
        this.date = date;
        this.member = member;
        this.staff = staff;
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

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "OrderPOJO [date=" + date + ", id=" + id + ", member=" + member + ", staff=" + staff + "]";
    }
}
