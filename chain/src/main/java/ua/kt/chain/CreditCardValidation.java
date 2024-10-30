package ua.kt.chain;

public class CreditCardValidation implements PaymentValidator {
    private final PaymentType defaultPaymentType = PaymentType.CREDIT_CARD;

    @Override
    public void validate(PaymentType paymentType) {
        System.out.println("Validating Credit Card payment");
    }
}
