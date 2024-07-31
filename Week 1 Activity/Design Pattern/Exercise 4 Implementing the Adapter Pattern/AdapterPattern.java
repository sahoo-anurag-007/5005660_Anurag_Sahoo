public interface PaymentProcessor {
    void processPayment(double amount);
}
public class PayPal {
    public void processPayPalPayment(double amount, String payerId) {
        // Implement PayPal payment processing
    }
}

public class Stripe {
    public void processStripePayment(double amount, String token) {
        // Implement Stripe payment processing
    }
}
public class PayPalAdapter implements PaymentProcessor {
    private PayPal payPal;

    public PayPalAdapter(PayPal payPal) {
        this.payPal = payPal;
    }

    @Override
    public void processPayment(double amount) {
        payPal.processPayPalPayment(amount, "payerId");
    }
}

public class StripeAdapter implements PaymentProcessor {
    private Stripe stripe;

    public StripeAdapter(Stripe stripe) {
        this.stripe = stripe;
    }

    @Override
    public void processPayment(double amount) {
        stripe.processStripePayment(amount, "token");
    }
}
public class AdapterPattern {
    public static void main(String[] args) {
        PayPal payPal = new PayPal();
        PaymentProcessor payPalAdapter = new PayPalAdapter(payPal);
        payPalAdapter.processPayment(100.0);

        Stripe stripe = new Stripe();
        PaymentProcessor stripeAdapter = new StripeAdapter(stripe);
        stripeAdapter.processPayment(100.0);
    }
}