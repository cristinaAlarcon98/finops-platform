import java.util.Queue;

public class OperationConsumer {
    private final Queue<Operation> queue;

    public OperationConsumer(Queue<Operation> queue) {
        this.queue = queue;
    }

    public void process() {
        Operation operation = queue.poll();
        if (operation == null) {
            return;
        }

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
}
