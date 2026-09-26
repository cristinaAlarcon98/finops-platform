import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;

public record Operation(UUID id, OperationType type, OperationStatus status, BigDecimal amount, BigDecimal tax,
        Currency currency, UUID sourceAccount, UUID destinationAccount, UUID userId, Instant dateTime) {
}