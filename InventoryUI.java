import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryUI {

    private InventoryManager manager = new InventoryManager();
    private JTextArea displayArea;

    private final Color bg = new Color(20, 20, 20);
    private final Color side = new Color(30, 30, 30);
    private final Color content = new Color(40, 40, 40);
    private final Color accent = new Color(0, 120, 215);
    private final Color hover = new Color(0, 150, 255);

    public InventoryUI() {

        JFrame frame = new JFrame("Inventory Dashboard");
        frame.setSize(850, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel title = new JLabel("Inventory Management System", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setOpaque(true);
        title.setBackground(bg);
        title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        frame.add(title, BorderLayout.NORTH);

        // Sidebar
        JPanel sidebar = new JPanel(new GridLayout(6, 1, 10, 10));
        sidebar.setBackground(side);
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] actions = {
                "Add Product", "View Products", "Search Product",
                "Update Quantity", "Delete Product", "Exit"
        };

        for (String action : actions) {
            JButton btn = createButton(action);
            btn.addActionListener(e -> handleAction(action));
            sidebar.add(btn);
        }

        frame.add(sidebar, BorderLayout.WEST);

        // Content Area
        displayArea = new JTextArea();
        displayArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        displayArea.setBackground(content);
        displayArea.setForeground(Color.WHITE);
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        frame.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    //  Central logic handler
    private void handleAction(String action) {
        try {
            switch (action) {

                case "Add Product":
                    int id = Integer.parseInt(input("Enter ID"));
                    String name = input("Enter Name");
                    int qty = Integer.parseInt(input("Enter Quantity"));
                    double price = Double.parseDouble(input("Enter Price"));

                    Product p = new Product(id, name, qty, price);

                    boolean added = manager.addProduct(p);

                    if (added) {
                        display("Product Added Successfully\n\n" + format(p));
                    } else {
                        display("Error: Product ID already exists.");
                    }
                    break;

                case "View Products":
                    if (manager.products.isEmpty()) {
                        display("No products available.");
                        return;
                    }

                    StringBuilder sb = new StringBuilder("All Products\n\n");
                    for (Product pr : manager.products) {
                        sb.append(format(pr)).append("\n\n");
                    }
                    display(sb.toString());
                    break;

                case "Search Product":
                    Product found = manager.searchProduct(Integer.parseInt(input("Enter ID")));
                    display(found != null ? format(found) : "Product not found.");
                    break;

                case "Update Quantity":
                    int uid = Integer.parseInt(input("Enter ID"));
                    int newQty = Integer.parseInt(input("Enter Quantity"));

                    Product up = manager.searchProduct(uid);
                    if (up != null) {
                        manager.updateQuantity(uid, newQty);
                        display("Updated Successfully\n\n" + format(up));
                    } else {
                        display("Product not found.");
                    }
                    break;

                case "Delete Product":
                    int did = Integer.parseInt(input("Enter ID"));

                    Product dp = manager.searchProduct(did);
                    if (dp != null) {
                        manager.deleteProduct(did);
                        display("Product Deleted\n\n" + format(dp));
                    } else {
                        display("Product not found.");
                    }
                    break;

                case "Exit":
                    System.exit(0);
            }

        } catch (Exception e) {
            display("Invalid input.");
        }
    }

    private JButton createButton(String text) {
        JButton b = new JButton(text);
        b.setBackground(accent);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Segoe UI", Font.BOLD, 14));
        b.setFocusPainted(false);

        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                b.setBackground(hover);
            }

            public void mouseExited(MouseEvent e) {
                b.setBackground(accent);
            }
        });

        return b;
    }

    private String input(String msg) {
        return JOptionPane.showInputDialog(msg);
    }

    private void display(String text) {
        displayArea.setText(text);
    }

    private String format(Product p) {
        return "ID: " + p.getId() +
                "\nName: " + p.getName() +
                "\nQuantity: " + p.getQuantity() +
                "\nPrice: Rs. " + p.getPrice();
    }

    public static void main(String[] args) {
        new InventoryUI();
    }
}