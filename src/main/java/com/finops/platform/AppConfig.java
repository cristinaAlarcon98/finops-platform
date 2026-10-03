package com.finops.platform;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    BlockingQueue<Operation> createQueue() {
        return new ArrayBlockingQueue<>(100);
    }

    @Bean
    OperationProducer createOperationProducer(BlockingQueue<Operation> queue) {
        return new OperationProducer(queue, 3);

    }

}
