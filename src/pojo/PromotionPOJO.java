package pojo;

import java.time.LocalDate;

public class PromotionPOJO {
    int id;
    String name;
    String description;
    LocalDate start_date;
    LocalDate end_date;
    int discount;
    int max_order;
    boolean can_customer_once;
    boolean can_anonymous;
    boolean isActive;
    boolean isOpen;

    public PromotionPOJO(int id, String name, String description, LocalDate start_date, LocalDate end_date, int discount, int max_order, boolean can_customer_once, boolean can_anonymous, boolean isActive, boolean isOpen) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.discount = discount;
        this.max_order = max_order;
        this.can_customer_once = can_customer_once;
        this.can_anonymous = can_anonymous;
        this.isActive = isActive;
        this.isOpen = isOpen;
    }

    public PromotionPOJO(int id, String name, String description, int discount, int max_order, boolean can_customer_once, boolean can_anonymous){
        this.id = id;
        this.name = name;
        this.description = description;
        this.discount = discount;
        this.max_order = max_order;
        this.can_customer_once = can_customer_once;
        this.can_anonymous = can_anonymous;
    }
    
    public PromotionPOJO(int id, LocalDate start_date, LocalDate end_date){
        this.id = id;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    
    public PromotionPOJO(String name, String description, LocalDate start_date, LocalDate end_date, int discount, int max_order, boolean can_customer_once, boolean can_anonymous) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.end_date = end_date;
        this.discount = discount;
        this.max_order = max_order;
        this.can_customer_once = can_customer_once;
        this.can_anonymous = can_anonymous;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getMax_order() {
        return max_order;
    }

    public void setMax_order(int max_order) {
        this.max_order = max_order;
    }

    public boolean isCan_customer_once() {
        return can_customer_once;
    }

    public void setCan_customer_once(boolean can_customer_once) {
        this.can_customer_once = can_customer_once;
    }

    public boolean isCan_anonymous() {
        return can_anonymous;
    }

    public void setCan_anonymous(boolean can_anonymous) {
        this.can_anonymous = can_anonymous;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }
}
