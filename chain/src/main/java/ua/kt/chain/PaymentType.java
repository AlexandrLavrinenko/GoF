package ua.kt.chain;

import lombok.Getter;

@Getter
public enum PaymentType {
    CREDIT_CARD("Credit card", CreditCardValidation.class),
    PAYPAL("Paypal", PayPalValidation.class),
    ALL_PAYMENT_TYPE("All payment types", null);


    private final String description;
    private final Class<? extends PaymentValidator> paymentValidatorClass;

    PaymentType(String description, Class<? extends PaymentValidator> paymentValidatorClass) {
        this.description = description;
        this.paymentValidatorClass = paymentValidatorClass;
    }
}
