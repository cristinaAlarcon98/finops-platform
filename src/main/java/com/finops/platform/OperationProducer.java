package com.finops.platform;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class OperationProducer {
    private final BlockingQueue<Operation> queue;
    private final int numberOfConsumers;

    public OperationProducer(BlockingQueue<Operation> queue, int numberOfConsumers) {
        this.queue = queue;
        this.numberOfConsumers = numberOfConsumers;
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

    public void sendPoisonPills() {
        for (int i = 0; i < numberOfConsumers; i++) {
            queue.offer(Operation.POISON_PILL);
        }
    }

}
