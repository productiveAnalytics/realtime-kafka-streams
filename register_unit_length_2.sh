#!/bin/bash
SCHEMA_REGISTRY_URL="http://kafka1:8081"
MAIN_TOPIC="lndcdcadsinvntry_unitlength"
AVRO_SCHEMA_FILENAME="schema_for_unit_length_2.json"
echo "Fetching AVRO schema for ${MAIN_TOPIC} using AVRO schema registry ${SCHEMA_REGISTRY_URL}"

rm -rf ./${AVRO_SCHEMA_FILENAME}
echo "Cleanup complete"
curl -s -X GET ${SCHEMA_REGISTRY_URL}/subjects/${MAIN_TOPIC}-value/versions/latest | jq 'del(.subject)|del(.version)|del(.id)' >> ${AVRO_SCHEMA_FILENAME}
echo "AVRO schema for ${MAIN_TOPIC}: \n"
cat < ./${AVRO_SCHEMA_FILENAME}
echo
echo
COPY_TOPIC="${MAIN_TOPIC}2"
echo "Registering AVRO schema for ${COPY_TOPIC}"
curl -i -H "Content-Type: application/vnd.schemaregistry.v1+json" -X POST ${SCHEMA_REGISTRY_URL}/subjects/${COPY_TOPIC}-value/versions --data-binary "@${AVRO_SCHEMA_FILENAME}"
echo
echo "waiting to register..."
echo
sleep 5
curl -s -X GET ${SCHEMA_REGISTRY_URL}/subjects/${COPY_TOPIC}-value/versions/latest | jq '.' 
echo "Done!"