# FinOps Platform

Backend system for reliable financial operations processing in a distributed environment.

Built to cover real backend engineering problems: concurrency, consistency, idempotency,
event-driven architecture, failure recovery, observability, and scalability.

## Stack
- Java 21
- Spring Boot (Week 3)
- PostgreSQL (Week 4)
- Kafka (Week 6)
- Redis (Week 8)
- Docker (Week 11)

## Architecture

### Week 1 — Pure Java + In-Memory Queue
Producer/Consumer pattern with a FIFO queue.
- `Operation` — immutable record modeling a financial operation
- `OperationProducer` — creates and enqueues operations
- `OperationConsumer` — processes operations from the queue
- `Main` — composition root, wires all dependencies

```
[Producer] ──push──▶ [ Queue ] ──pull──▶ [Consumer]
```

## Running

```bash
javac *.java
java Main
```
