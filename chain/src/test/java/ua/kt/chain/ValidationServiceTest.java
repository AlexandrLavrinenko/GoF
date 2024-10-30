package ua.kt.chain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restore() {
        System.setOut(originalOut);
    }

    @Test
    void testCreditCardValidationWithConstructorWithParam() {
        ValidationService service = new ValidationService(new CreditCardValidation());
        service.validate(PaymentType.CREDIT_CARD);
        String output = outputStream.toString().trim();
        assertEquals("Validating Credit Card payment", output);
    }
    @Test
    void testCreditCardValidation() {
        ValidationService service = new ValidationService();
        service.validate(PaymentType.CREDIT_CARD);
        String output = outputStream.toString().trim();
        assertEquals("Validating Credit Card payment", output);
    }

    @Test
    void testPayPalValidationWithConstructorWithParam() {
        ValidationService service = new ValidationService(new PayPalValidation());
        service.validate(PaymentType.PAYPAL);
        String output = outputStream.toString().trim();
        assertEquals("Validating PayPal payment", output);
    }

    @Test
    void testPayPalValidation() {
        ValidationService service = new ValidationService();
        service.validate(PaymentType.PAYPAL);
        String output = outputStream.toString().trim();
        assertEquals("Validating PayPal payment", output);
    }

    @Test
    void testCreditCardAndPayPalValidation() {
        ValidationService service = new ValidationService();
        service.validate(PaymentType.CREDIT_CARD, PaymentType.PAYPAL);
        String output = outputStream.toString().trim();
        assertEquals("Validating Credit Card payment" + System.lineSeparator() +
                     "Validating PayPal payment", output);
    }

    @Test
    void testAllPaymentTypeValidation() {
        ValidationService service = new ValidationService();
        service.validate(PaymentType.ALL_PAYMENT_TYPE);
        String output = outputStream.toString().trim();
        assertEquals("Validating Credit Card payment" + System.lineSeparator() +
                     "Validating PayPal payment", output);
    }

    @Test
    void testCreditCardAndPayPalValidationWithConstructorWithParam() {
        ValidationService service = new ValidationService(new CreditCardValidation(), new PayPalValidation());
        service.validate(PaymentType.CREDIT_CARD, PaymentType.PAYPAL);
        String output = outputStream.toString().trim();
        assertEquals("Validating Credit Card payment" + System.lineSeparator() +
                     "Validating PayPal payment", output);
    }

    @Test
    void testAllPaymentTypeValidationWithConstructorWithParam() {
        ValidationService service = new ValidationService(new CreditCardValidation(), new PayPalValidation());
        service.validate(PaymentType.ALL_PAYMENT_TYPE);
        String output = outputStream.toString().trim();
        assertEquals("Validating Credit Card payment" + System.lineSeparator() +
                     "Validating PayPal payment", output);
    }

    @Test
    void testAllPaymentTypeValidationWithConstructorWithParamReverseOrder() {
        ValidationService service = new ValidationService(new PayPalValidation(), new CreditCardValidation());
        service.validate(PaymentType.ALL_PAYMENT_TYPE);
        String output = outputStream.toString().trim();
        assertEquals("Validating PayPal payment" + System.lineSeparator() +
                     "Validating Credit Card payment", output);
    }

    @Test
    void testCreditCardAndPayPalValidationWithConstructorWithParamReverseOrder() {
        ValidationService service = new ValidationService(new PayPalValidation(), new CreditCardValidation());
        service.validate(PaymentType.CREDIT_CARD, PaymentType.PAYPAL);
        String output = outputStream.toString().trim();
        assertEquals("Validating Credit Card payment" + System.lineSeparator() +
                     "Validating PayPal payment", output);
    }

    @Test
    void testCreditCardAndPayPalValidationWithConstructorWithParamReversePaymentsOrder() {
        ValidationService service = new ValidationService(new CreditCardValidation(), new PayPalValidation());
        service.validate(PaymentType.PAYPAL, PaymentType.CREDIT_CARD);
        String output = outputStream.toString().trim();
        assertEquals("Validating PayPal payment" + System.lineSeparator() +
                     "Validating Credit Card payment", output);
    }
}