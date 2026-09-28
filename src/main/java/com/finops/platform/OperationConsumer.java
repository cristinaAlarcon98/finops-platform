package com.finops.platform;

import java.util.concurrent.BlockingQueue;

public class OperationConsumer implements Runnable {
    private final BlockingQueue<Operation> queue;

    public OperationConsumer(BlockingQueue<Operation> queue) {
        this.queue = queue;
    }

    public void process(Operation operation) {

        operation = new Operation(
                operation.id(),
                operation.type(),
                OperationStatus.PROCESSING,
                operation.amount(),
                operation.tax(),
                operation.currency(),
                operation.sourceAccount(),
                operation.destinationAccount(),
                operation.userId(),
                operation.dateTime()

        );

        System.out.println("Operation " + operation.id() + " is being processed");

        operation = new Operation(
                operation.id(),
                operation.type(),
                OperationStatus.COMPLETED,
                operation.amount(),
                operation.tax(),
                operation.currency(),
                operation.sourceAccount(),
                operation.destinationAccount(),
                operation.userId(),
                operation.dateTime()

        );

        System.out.println("Operation " + operation.id() + " is completed!");

    }

    public void run() {
        while (true) {
            try {
                Operation operation = queue.take();
                if (operation == Operation.POISON_PILL)
                    break;
                process(operation);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

    }
}
