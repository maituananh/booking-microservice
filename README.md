# Booking Microservice

A microservices system for booking management using Saga Pattern with Event-Driven Architecture, Debezium CDC (Change Data Capture), and Kafka.

## 🏗️ Architecture

The system consists of the following microservices:

- **Order Service**: Manages orders
- **Inventory Service**: Manages inventory and reservations
- **Payment Service**: Handles payments
- **Orchestrator**: Orchestrates Saga workflows
- **Common**: Shared libraries

### Technology Stack

- **Backend**: Spring Boot, Spring Cloud Stream
- **Database**: PostgreSQL
- **Message Broker**: Apache Kafka
- **CDC**: Debezium with Outbox Pattern
- **Build Tool**: Gradle
- **Container**: Docker & Docker Compose

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Docker & Docker Compose
- Gradle (or use `./gradlew`)

### 1. Start Infrastructure

Start PostgreSQL, Kafka, Zookeeper, and Debezium Connect:

```bash
docker-compose up -d
```

Check container status:

```bash
docker ps
```

Wait about 10-15 seconds for all services to be ready.

### 2. Register Debezium Connectors

After Kafka Connect is ready, register the connectors:

```bash
./register-connectors.sh
```

### 3. Start Microservices

#### Order Service
```bash
cd order-service
./gradlew bootRun
```

#### Inventory Service
```bash
cd inventory-service
./gradlew bootRun
```

#### Orchestrator
```bash
cd orchestrator
./gradlew bootRun
```

## 🔍 Monitoring & Debugging

### Check Debezium Connectors

#### List all connectors
```bash
curl -s http://localhost:8083/connectors | jq .
```

#### Check status of specific connector

**Order Outbox Connector:**
```bash
curl -s http://localhost:8083/connectors/order_outbox_connector/status | jq .
```

**Inventory Outbox Connector:**
```bash
curl -s http://localhost:8083/connectors/inventory_outbox_connector/status | jq .
```

**Workflow Outbox Connector:**
```bash
curl -s http://localhost:8083/connectors/workflow_outbox_connector/status | jq .
```

#### View connector configuration
```bash
curl -s http://localhost:8083/connectors/inventory_outbox_connector/config | jq .
```

#### Restart connector
```bash
curl -X POST http://localhost:8083/connectors/inventory_outbox_connector/restart
```

#### Delete connector
```bash
curl -X DELETE http://localhost:8083/connectors/inventory_outbox_connector
```

### Check Database

#### Connect to PostgreSQL
```bash
docker exec -it debezium-postgres psql -U postgres
```

#### List databases
```bash
docker exec -it debezium-postgres psql -U postgres -c "\l"
```

#### List tables in specific database
```bash
# Order database
docker exec -it debezium-postgres psql -U postgres -d orderdb -c "\dt"

# Inventory database
docker exec -it debezium-postgres psql -U postgres -d inventorydb -c "\dt"
```

#### Check data in outbox tables
```bash
# Inventory outbox
docker exec -it debezium-postgres psql -U postgres -d inventorydb -c "SELECT id, aggregate_id, type, topic, created_at FROM inventories_outbox ORDER BY created_at DESC LIMIT 5;"

# Order outbox
docker exec -it debezium-postgres psql -U postgres -d orderdb -c "SELECT id, aggregate_id, type, topic, created_at FROM orders_outbox ORDER BY created_at DESC LIMIT 5;"
```

### Check Kafka Topics

#### List all topics
```bash
docker exec debezium-kafka /kafka/bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
```

#### Read messages from topic
```bash
docker exec debezium-kafka /kafka/bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic ORDER_SUCCESS.events \
  --from-beginning
```

### View Logs

#### Kafka Connect logs
```bash
docker logs debezium-connect --tail 100 -f
```

#### PostgreSQL logs
```bash
docker logs debezium-postgres --tail 100 -f
```

#### Kafka logs
```bash
docker logs debezium-kafka --tail 100 -f
```

### Debezium UI

Access Debezium UI to manage connectors via web interface:

```
http://localhost:8080
```

## 🔧 Troubleshooting

### Events not being published from outbox

1. **Check connector status**:
   ```bash
   curl -s http://localhost:8083/connectors/inventory_outbox_connector/status | jq '.connector.state, .tasks[0].state'
   ```
   
2. **Check database name in connector config**:
   ```bash
   curl -s http://localhost:8083/connectors/inventory_outbox_connector/config | jq '.["database.dbname"]'
   ```
   Ensure the database name matches the actual database (e.g., `inventorydb` for inventory service).

3. **Check Kafka Connect logs**:
   ```bash
   docker logs debezium-connect --tail 50 | grep -i "inventory\|error\|warn"
   ```

4. **Restart connector if needed**:
   ```bash
   curl -X POST http://localhost:8083/connectors/inventory_outbox_connector/restart
   ```

### Connector in FAILED state

1. View error details:
   ```bash
   curl -s http://localhost:8083/connectors/inventory_outbox_connector/status | jq .
   ```

2. Delete and re-register connector:
   ```bash
   curl -X DELETE http://localhost:8083/connectors/inventory_outbox_connector
   sleep 2
   curl -X POST http://localhost:8083/connectors \
     -H 'Content-Type: application/json' \
     -d @outbox_inventory_connector.json
   ```

### Replication slot conflict

If you encounter "replication slot already exists" error:

1. Connect to PostgreSQL:
   ```bash
   docker exec -it debezium-postgres psql -U postgres
   ```

2. Drop replication slot:
   ```sql
   SELECT pg_drop_replication_slot('inventory_outbox_connector');
   ```

3. Restart connector.

## 📁 Project Structure

```
booking-microservice/
├── common/                          # Common libraries
├── order-service/                   # Order microservice
├── inventory-service/               # Inventory microservice
├── payment-service/                 # Payment microservice
├── orchestrator/                    # Saga orchestrator
├── docker-compose.yaml              # Infrastructure setup
├── outbox_order_connector.json      # Order Debezium connector config
├── outbox_inventory_connector.json  # Inventory Debezium connector config
├── outbox_workflow_connector.json   # Workflow Debezium connector config
├── register-connectors.sh           # Script to register connectors
└── delete-connectors.sh             # Script to delete connectors
```

## 🛠️ Utility Scripts

### Register all connectors
```bash
./register-connectors.sh
```

### Delete all connectors
```bash
./delete-connectors.sh
```

## 📝 Notes

- Ensure connectors are registered **after** Kafka Connect is ready
- Each microservice has its own database (orderdb, inventorydb, workflowdb)
- Outbox pattern is used to ensure eventual consistency
- Events are routed to Kafka topics based on the `topic` field in the outbox table

## 📞 Support

If you encounter issues, check:
1. Docker containers are running: `docker ps`
2. Debezium connector status: `curl -s http://localhost:8083/connectors | jq .`
3. Application logs of microservices
4. Kafka Connect logs: `docker logs debezium-connect --tail 100`
