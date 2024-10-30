package ua.kt.chain;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class ValidationService {
    private final ValidationChain validationChain;

    public ValidationService(PaymentValidator... paymentValidators) {
        this.validationChain = new ValidationChain(paymentValidators);
    }

    public ValidationService() {
        PaymentValidator[] validators = Arrays.stream(PaymentType.values())
                .filter(e -> e.getPaymentValidatorClass() != null)
                .map(PaymentType::getPaymentValidatorClass)
                .map(this::instantiateValidator)
                .toArray(PaymentValidator[]::new);
        this.validationChain = new ValidationChain(validators);
    }

    private PaymentValidator instantiateValidator(Class<? extends PaymentValidator> validatorClass) {
        try {
            return validatorClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(validatorClass + " is not a valid PaymentValidator");
        }
    }

    public void validate(PaymentType... paymentTypes) {
        if (Arrays.stream(paymentTypes).anyMatch(e -> e.equals(PaymentType.ALL_PAYMENT_TYPE))) {
            validationChain.getValidatorMap().entrySet().stream()
                    .map(Map.Entry::getValue)
                    .forEach(validator -> validator.validate(PaymentValidator.default_payment_type));
        } else {
            Arrays.stream(paymentTypes)
                    .flatMap(paymentType -> validationChain.getValidatorMap().entrySet().stream()
                            .filter(entry -> {
                                Class<? extends PaymentValidator> validatorClass = paymentType.getPaymentValidatorClass();
                                return validatorClass != null && validatorClass.isInstance(entry.getValue());
                            })
                            .map(Map.Entry::getValue))
                    .filter(Objects::nonNull)
                    .forEach(validator -> validator.validate(PaymentValidator.default_payment_type));
        }
    }
}
