# Kafka Streams based conformance

## How to run
* `mvn clean generate-sources`
* `mvn clean package`
- Add the version below when running
  `java -jar ./target/dp-linear-realtime-kafka-streams-1.0-SNAPSHOT-jar-with-dependencies.jar con-common.flight-range configuration/dev_flight_range.properties`


## Producing test data
- Create the Avro schemas with Conduktor
    - Use Avro -> Topic Name -> Value
- Produce the messages to the avro topic
- String key with Avro Topic name Strategy for the value

```avro

docker exec -i schema-registry /usr/bin/kafka-avro-console-producer --topic albums --broker-list broker:9092\
  --property "parse.key=true"\
  --property 'key.schema={"type":"long"}'\
  --property "key.separator=:"\
  --property value.schema="$(< /Users/hutcs025/DMED-Code/kafka-join-agg-poc/src/main/avro/lndcdc_ads_prps/LNDCDC_ADS_PRPSL_FLIGHT_RANGE_temp.avsc)"

```

## Consuming the data
* The joined stream
    * `docker exec -it schema-registry /usr/bin/kafka-avro-console-consumer --topic proposal-line-flight-range-joined --bootstrap-server broker:9092 --from-beginning`
    * `docker exec -it schema-registry /usr/bin/kafka-avro-console-consumer --topic proposal-line-flight-range-prop-joined --bootstrap-server broker:9092 --from-beginning`
    * kafka-console-consumer --topic streams-output-topic --bootstrap-server localhost:29092 \ --from-beginning \ --property print.key=true \ --property key.separator=" - "
* The aggregated stream
    * `docker exec -it schema-registry /usr/bin/kafka-avro-console-consumer --topic tv-prop-line-dtl-joined --bootstrap-server broker:9092 --from-beginning`

```sql
/*
        take action where the flight range time stamp is greater than the timestamp that was on the flight range stream
        proposal_line 		flight_range
        1						101
        - dependent comes and need to update the primary record (use case 2)
        flight_range_stream
            flight_id  =101
        
        this is the Createtime / row time for the messages landing on the topic
        
        main stream from where we do main conformance and push back to the original stream so the reconformance takes place
        
        3 streams
        
        similar to landing topics for each arrival have one main stream
        flight range , proposal line
         \              /
        intermediate stream , joined flight range proposal line stream
        
        new flight id record comes in with a change to flight range stream 
        
        current join is one stream to multiple tables and corresponds to underlying metadata for the table
        
        timestamp_t1 = from the flight range stream, flight_range_timestamp_rowtime
        flight_id1 = from the message in flight range stream  
        
        -- Check point 
        push flight range first -> using AVRO message no action taken because no proposal line (empty proposal line)
        
        push in proposal line -> goes to proposal line table
        
        then again push flight range (same id) foreign key matching and join happens with proposal line 
        back to the topic. small change to the data within the message
        
        data goes to the temp stream. 
        
        
        
        Next Expectation is the below occurs
        
        -- action on a stream that is driving by the dependent flight range id hitting the flight range stream
        -- temp stream for now based on the matching condition below
        insert into proposalline_stream(select * 
        from proposaline_table 
        where flihghtId=101 and proposallinetimestamp_rowtime < timestamp_t1);

         triggers conformances again when the same record is pushed back to the original stream. 

         flight range updates update all proposal lines with the corresponding keys

         */

/*
take action where the flight range time stamp is greater than the timestamp that was on the flight range stream
proposal_line 		flight_range
1						101
- dependent comes and need to update the primary record (use case 2)
flight_range_stream
flight_id  =101
insert into proposalline_stream(select * from proposaline_table where flihghtId=101 and flight_range_timestamp_rowtime > proposallinetimestamp_rowtime);

reconformance

flight range updates update all proposal lines with the corresponding keys

*/

```

## Notes
* On joins
    * This is a symmetric non-window join. There are two streams involved in this join, the left stream and the right stream, each of which are usually keyed on different key types. The left stream is keyed on the primary key, whereas the right stream is keyed on the foreign key. Each element in the left stream has a foreign-key extractor function applied to it, which extracts the foreign key. The resultant left-event is then joined with the right-event keyed on the corresponding foreign-key. Updates made to the right-event will also trigger joins with the left-events containing that foreign-key. It can be helpful to think of the left-hand materialized stream as events containing a foreign key, and the right-hand materialized stream as entities keyed on the foreign key.

KTable lookups are done on the current KTable state, and thus, out-of-order records can yield non-deterministic result. Furthermore, in practice Kafka Streams does not guarantee that all records will be processed in timestamp order (even if processing records in timestamp order is the goal, it is only best effort).

## To do
- Add additional columns to join

### Links
* Serialization Docs
    * https://docs.confluent.io/platform/current/streams/developer-guide/datatypes.html
