package com.finops.platform;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.math.BigDecimal;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Operation> queue = new ArrayBlockingQueue<>(100);
        OperationProducer producer = new OperationProducer(queue, 3);
        OperationConsumer consumer1 = new OperationConsumer(queue);
        OperationConsumer consumer2 = new OperationConsumer(queue);
        OperationConsumer consumer3 = new OperationConsumer(queue);

        producer.submit(OperationType.TRANSFER, BigDecimal.valueOf(1000), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.TRANSFER, BigDecimal.valueOf(2000), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.DEPOSIT, BigDecimal.valueOf(500), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.WITHDRAWAL, BigDecimal.valueOf(300), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.PAYMENT, BigDecimal.valueOf(750), Currency.getInstance("GBP"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.TRANSFER, BigDecimal.valueOf(1500), Currency.getInstance("USD"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.DEPOSIT, BigDecimal.valueOf(200), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.PAYMENT, BigDecimal.valueOf(99), Currency.getInstance("GBP"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.WITHDRAWAL, BigDecimal.valueOf(450), Currency.getInstance("USD"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.sendPoisonPills();

        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();

    }
}
