import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.Queue;
import java.util.UUID;

public class OperationProducer {
    private final Queue<Operation> queue;

    public OperationProducer(Queue<Operation> queue) {
        this.queue = queue;
    }

    public void submit(OperationType type, BigDecimal amount, Currency currency, UUID sourceAccount,
            UUID destinationAccount, UUID userId) {

        BigDecimal tax = amount.multiply(new BigDecimal("0.01"));

        Operation operation = new Operation(UUID.randomUUID(), type, OperationStatus.PENDING, amount, tax, currency,
                sourceAccount, destinationAccount, userId, Instant.now());

        if (!queue.offer(operation)) {
            throw new IllegalStateException("Operation could not be enqueued: " + operation.id());
        }

    }

}
