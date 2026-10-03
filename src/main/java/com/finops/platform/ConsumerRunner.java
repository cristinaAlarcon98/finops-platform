package com.finops.platform;

import java.util.concurrent.BlockingQueue;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRunner implements ApplicationRunner {

    private final BlockingQueue<Operation> queue;

    public ConsumerRunner(BlockingQueue<Operation> queue) {
        this.queue = queue;
    }

    @Override
    public void run(ApplicationArguments args) {

        OperationConsumer consumer1 = new OperationConsumer(queue);
        OperationConsumer consumer2 = new OperationConsumer(queue);
        OperationConsumer consumer3 = new OperationConsumer(queue);

        new Thread(consumer1).start();
        new Thread(consumer2).start();
        new Thread(consumer3).start();

    }
}
