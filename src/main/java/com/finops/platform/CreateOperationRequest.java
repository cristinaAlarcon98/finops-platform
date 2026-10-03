package com.finops.platform;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.UUID;

public record CreateOperationRequest(OperationType type, BigDecimal amount, Currency currency, UUID sourceAccount,
        UUID destinationAccount, UUID userId) {

}
