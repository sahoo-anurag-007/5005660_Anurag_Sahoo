import java.util.Arrays;

class Product {
    private int productId;
    private String productName;
    private String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}

class SearchAlgorithms {

    // Linear search implementation
    public static Product linearSearch(Product[] products, String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product; // Product found
            }
        }
        return null; // Product not found
    }

    // Binary search implementation
    public static Product binarySearch(Product[] products, String productName) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareToIgnoreCase(productName);

            if (comparison == 0) {
                return products[mid]; // Product found
            } else if (comparison < 0) {
                left = mid + 1; // Search in the right half
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return null; // Product not found
    }
}

public class EPSF {
    public static void main(String[] args) {
        // Create an array of products
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Smartphone", "Electronics"),
            new Product(3, "Shoes", "Fashion"),
            new Product(4, "Watch", "Accessories"),
            new Product(5, "Tablet", "Electronics")
        };

        // Sort the products array by product name for binary search
        Arrays.sort(products, (p1, p2) -> p1.getProductName().compareToIgnoreCase(p2.getProductName()));

        // Perform linear search
        Product foundProductLinear = SearchAlgorithms.linearSearch(products, "Shoes");
        if (foundProductLinear != null) {
            System.out.println("Linear Search: Found " + foundProductLinear);
        } else {
            System.out.println("Linear Search: Product not found.");
        }

        // Perform binary search
        Product foundProductBinary = SearchAlgorithms.binarySearch(products, "Shoes");
        if (foundProductBinary != null) {
            System.out.println("Binary Search: Found " + foundProductBinary);
        } else {
            System.out.println("Binary Search: Product not found.");
        }
    }
    
}
