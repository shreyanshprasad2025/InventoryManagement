// Product.java
// This class represents a product in the inventory

public class Product {

    private int id;
    private String name;
    private int quantity;
    private double price;

    // Constructor
    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for Quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Display product
    public void displayProduct() {
        System.out.println("ID: " + id +
                " | Name: " + name +
                " | Quantity: " + quantity +
                " | Price: " + price);
    }
    public String getName() {
    return name;
}

public int getQuantity() {
    return quantity;
}

public double getPrice() {
    return price;
}
}
