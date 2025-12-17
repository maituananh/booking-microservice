#!/bin/sh
curl -X DELETE http://localhost:8083/connectors/order_outbox_connector

curl -X DELETE http://localhost:8083/connectors/inventory_outbox_connector

curl -X DELETE http://localhost:8083/connectors/workflow_outbox_connector

curl -X DELETE http://localhost:8083/connectors/payment_outbox_connector
