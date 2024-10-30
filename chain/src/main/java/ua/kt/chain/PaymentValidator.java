package ua.kt.chain;

public interface PaymentValidator {
    PaymentType default_payment_type = PaymentType.ALL_PAYMENT_TYPE;
    void validate(PaymentType paymentType);

}
