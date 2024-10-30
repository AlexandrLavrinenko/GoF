package ua.kt.chain;

import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class ValidationChain {

    private final Map<Integer, PaymentValidator> validatorMap;

    public ValidationChain(PaymentValidator ... validators) {
        this.validatorMap = IntStream.range(0, validators.length)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> validators[i],
                        (existing, replacement) -> existing,
                        LinkedHashMap::new));
    }
}
