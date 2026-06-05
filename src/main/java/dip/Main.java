package dip;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor creditCardProcessor = new PaymentProcessor(new CreditCardPayment());
        PaymentProcessor payPalProcessor = new PaymentProcessor(new PayPalPayment());
        PaymentProcessor cryptoProcessor = new PaymentProcessor(new CryptoPayment());

        System.out.println("--- Credit Card ---");
        creditCardProcessor.makePayment(150.0);

        System.out.println("\n--- PayPal ---");
        payPalProcessor.makePayment(200.0);

        System.out.println("\n--- Crypto ---");
        cryptoProcessor.makePayment(99.99);
    }
}
