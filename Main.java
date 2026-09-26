import java.util.Queue;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {
        Queue<Operation> queue = new ArrayDeque<>();
        OperationProducer producer = new OperationProducer(queue);
        OperationConsumer consumer = new OperationConsumer(queue);

        producer.submit(OperationType.TRANSFER, BigDecimal.valueOf(1000), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.TRANSFER, BigDecimal.valueOf(2000), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());
        producer.submit(OperationType.DEPOSIT, BigDecimal.valueOf(2000), Currency.getInstance("EUR"),
                UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID());

        while (queue.peek() != null) {
            consumer.process();
        }

    }
}
