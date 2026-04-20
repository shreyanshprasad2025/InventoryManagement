import java.util.ArrayList;

public class InventoryManager {

    ArrayList<Product> products = new ArrayList<>();

    // Add product 
    public boolean addProduct(Product p) {

        if (searchProduct(p.getId()) != null) {
            return false; // duplicate ID
        }

        products.add(p);
        return true;
    }

    // View products
    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Product p : products) {
            p.displayProduct();
        }
    }

    // Search product
    public Product searchProduct(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Update quantity
    public void updateQuantity(int id, int newQuantity) {
        Product p = searchProduct(id);

        if (p != null) {
            p.setQuantity(newQuantity);
            System.out.println("Quantity updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete product
    public void deleteProduct(int id) {
        Product p = searchProduct(id);

        if (p != null) {
            products.remove(p);
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }
}