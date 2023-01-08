package pojo;

public class DetailImportSheetPOJO {
    int id;
    String book;
    int quantity;
    double import_price;
    double total_cost;

    public DetailImportSheetPOJO() {

    }

    public DetailImportSheetPOJO(String book, int quantity, double import_price, double total_cost) {
        this.book = book;
        this.quantity = quantity;
        this.import_price = import_price;
        this.total_cost = total_cost;
    }

    public DetailImportSheetPOJO(int id, String book, int quantity, double import_price, double total_cost) {
        this.id = id;
        this.book = book;
        this.quantity = quantity;
        this.import_price = import_price;
        this.total_cost = total_cost;
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

    public double getImportPrice() {
        return import_price;
    }

    public void setImportPrice(double import_price) {
        this.import_price = import_price;
    }

    public double getTotalCost() {
        return total_cost;
    }

    public void setTotalCost(double total_cost) {
        this.total_cost = total_cost;
    }

    @Override
    public String toString() {
        return "DetailImportSheetPOJO [book=" + book + ", id=" + id + ", import_price=" + import_price + ", quantity="
                + quantity + ", total_cost=" + total_cost + "]";
    }
}