#!/bin/sh
curl -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -d @outbox_order_connector.json

curl -X GET http://localhost:8083/connectors/order_outbox_connector/status

curl -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -d @outbox_inventory_connector.json

curl -X GET http://localhost:8083/connectors/inventory_outbox_connector/status

curl -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -d @outbox_workflow_connector.json

curl -X GET http://localhost:8083/connectors/workflow_outbox_connector/status

curl -X POST http://localhost:8083/connectors \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -d @outbox_payment_connector.json

curl -X GET http://localhost:8083/connectors/payment_outbox_connector/status
