# FinOps Platform

Backend system for reliable financial operations processing in a distributed environment.

Built to cover real backend engineering problems: concurrency, consistency, idempotency,
event-driven architecture, failure recovery, observability, and scalability.

## Stack
- Java 21
- Spring Boot
- PostgreSQL (Week 4)
- Kafka (Week 6)
- Redis (Week 8)
- Docker (Week 11)

## Architecture

### Phase 1 — Pure Java + In-Memory Queue
Producer/Consumer pattern with a FIFO bounded queue and 3 concurrent consumers.
- `Operation` — immutable record modeling a financial operation
- `OperationProducer` — validates, generates ID/tax/timestamp, enqueues
- `OperationConsumer` — processes operations concurrently via blocking queue
- `AppConfig` — composition root, wires all dependencies

```
[Producer] ──push──▶ [ BlockingQueue ] ──pull──▶ [Consumer x3]
```

### Phase 2 — Spring Boot + REST API
HTTP layer on top of the existing Producer/Consumer architecture.
- `POST /operations` — submit a new financial operation
- `OperationController` — receives HTTP requests, delegates to producer
- `CreateOperationRequest` — DTO with the 6 fields the client provides
- `ConsumerRunner` — starts consumer threads on application startup

```
HTTP Request ──▶ Controller ──▶ Producer ──▶ Queue ──▶ Consumer
```

## Running

```bash
./mvnw spring-boot:run
```

### Submit an operation
```bash
curl -X POST http://localhost:8080/operations \
  -H "Content-Type: application/json" \
  -d '{"type":"TRANSFER","amount":1000,"currency":"EUR","sourceAccount":"<uuid>","destinationAccount":"<uuid>","userId":"<uuid>"}'
```
