import java.util.Arrays;
class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

class SortAlgorithms {

    // Bubble Sort implementation
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j + 1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no elements were swapped, the array is sorted
            if (!swapped) break;
        }
    }
    
    // Quick Sort implementation
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            quickSort(orders, low, pivotIndex - 1);
            quickSort(orders, pivotIndex + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        // Choose the rightmost element as pivot
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                // Swap orders[i] and orders[j]
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }

        // Swap orders[i + 1] and orders[high] (or pivot)
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1; // Return the partitioning index
    }
}

public class SCO {
    public static void main(String[] args) {
        // Create an array of orders
        Order[] orders = {
            new Order(1, "Alice", 250.00),
            new Order(2, "Bob", 150.00),
            new Order(3, "Charlie", 300.00),
            new Order(4, "Diana", 200.00),
            new Order(5, "Eve", 100.00)
        };

        // Perform Bubble Sort
        System.out.println("Bubble Sort:");
        Order[] bubbleSortedOrders = Arrays.copyOf(orders, orders.length);
        SortAlgorithms.bubbleSort(bubbleSortedOrders);
        for (Order order : bubbleSortedOrders) {
            System.out.println(order);
        }

        // Perform Quick Sort
        System.out.println("\nQuick Sort:");
        Order[] quickSortedOrders = Arrays.copyOf(orders, orders.length);
        SortAlgorithms.quickSort(quickSortedOrders, 0, quickSortedOrders.length - 1);
        for (Order order : quickSortedOrders) {
            System.out.println(order);
        }
    }
}