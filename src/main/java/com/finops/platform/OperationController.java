package com.finops.platform;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationProducer producer;

    public OperationController(OperationProducer producer) {
        this.producer = producer;
    }

    @PostMapping
    public void submit(@RequestBody CreateOperationRequest request) {
        producer.submit(request.type(), request.amount(), request.currency(), request.sourceAccount(),
                request.destinationAccount(), request.userId());

    }

}
