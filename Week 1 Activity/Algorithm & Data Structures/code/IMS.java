import java.util.HashMap;
import java.util.Map;

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

class Inventory {
    private Map<Integer, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    // Method to add a product
    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product already exists. Use updateProduct to modify the existing product.");
        } else {
            products.put(product.getProductId(), product);
            System.out.println("Product " + product.getProductName() + " added to inventory.");
        }
    }

    // Method to update a product
    public void updateProduct(int productId, String productName, Integer quantity, Double price) {
        Product product = products.get(productId);
        if (product != null) {
            if (productName != null) {
                product.setProductName(productName);
            }
            if (quantity != null) {
                product.setQuantity(quantity);
            }
            if (price != null) {
                product.setPrice(price);
            }
            System.out.println("Product " + productId + " updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Method to delete a product
    public void deleteProduct(int productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
            System.out.println("Product " + productId + " deleted from inventory.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Method to display all products in the inventory
    public void displayInventory() {
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }
}

public class IMS {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        // Add products
        Product product1 = new Product(1, "Widget", 100, 9.99);
        Product product2 = new Product(2, "Gadget", 150, 14.99);

        inventory.addProduct(product1);
        inventory.addProduct(product2);

        // Update a product
        inventory.updateProduct(1, null, 120, 10.99);

        // Delete a product
        inventory.deleteProduct(2);

        // Display all products
        inventory.displayInventory();
    }
}