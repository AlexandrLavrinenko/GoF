package ua.kt.chain;

public class PayPalValidation implements PaymentValidator {
    private final PaymentType defaultPaymentType = PaymentType.PAYPAL;

    @Override
    public void validate(PaymentType paymentType) {
        System.out.println("Validating PayPal payment");
    }
}
