// Main.java
// Main class to run the inventory system

import java.util.Scanner;
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        int choice;

        do {

            System.out.println("\n===== Inventory Management System =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Quantity");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    Product p = new Product(id, name, quantity, price);
                    manager.addProduct(p);
                    break;

                case 2:
                    manager.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID to search: ");
                    int searchId = sc.nextInt();

                    Product found = manager.searchProduct(searchId);

                    if (found != null) {
                        found.displayProduct();
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    int updateId = sc.nextInt();

                    System.out.print("Enter New Quantity: ");
                    int newQty = sc.nextInt();

                    manager.updateQuantity(updateId, newQty);
                    break;

                case 5:
                    System.out.print("Enter Product ID to delete: ");
                    int deleteId = sc.nextInt();

                    manager.deleteProduct(deleteId);
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}