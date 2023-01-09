package pojo;

public class DetailOrderPOJO {
    int id;
    String book;
    int quantity;
    double cost;
    double final_cost;
    String promo_name;

    public DetailOrderPOJO() {

    }

    public DetailOrderPOJO(String book, int quantity, double cost, double final_cost, String promo_name) {
        this.book = book;
        this.quantity = quantity;
        this.cost = cost;
        this.final_cost = final_cost;
        this.promo_name = promo_name;
    }

    public DetailOrderPOJO(int id, String book, int quantity, double cost, double final_cost, String promo_name) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        this.cost = cost;
        this.final_cost = final_cost;
        this.promo_name = promo_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getFinalCost() {
        return final_cost;
    }

    public void setFinalCost(double final_cost) {
        this.final_cost = final_cost;
    }

    public String getPromoName() {
        return promo_name;
    }

    public void setPromoName(String promo_name) {
        this.promo_name = promo_name;
    }

    @Override
    public String toString() {
        return "DetailOrderPOJO [book=" + book + ", cost=" + cost + ", final_cost=" + final_cost + ", id=" + id
                + ", promo_name=" + promo_name + ", quantity=" + quantity + "]";
    }
}