public class FF {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        // Base case: if no more periods, return the present value
        if (periods == 0) {
            return presentValue;
        }

        // Recursive case: calculate future value for one less period
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        // Example: Calculate future value with initial amount, growth rate, and periods
        double initialAmount = 1000.0; // Initial investment or present value
        double growthRate = 0.05;      // 5% growth rate
        int periods = 10;              // Number of periods (e.g., years)

        double futureValue = calculateFutureValue(initialAmount, growthRate, periods);
        System.out.printf("Future Value after %d periods: %.2f\n", periods, futureValue);
    }
}