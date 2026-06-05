package dip;

public class CryptoPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing Crypto payment of $" + amount);
    }
}
