package com.dtci.linear.analytics.kafkastreams;

import static io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;

import javax.validation.ValidationException;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;

import io.confluent.common.utils.TestUtils;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;

/**
 * Super Class with common functionality
 * 
 * @author chawl001
 *
 */
public abstract class AbstractKafkaStreamsApp {
	
	public static final String PATTERN_TOPIC_NAME = "topic.name.";
	
	public static final Long PLACEHOLDER_LONG = -1L;
	public static final String PLACEHOLDER_STR = null; 
	
    protected static Properties loadEnvProperties(String fileName) throws IOException {
        final Properties envProps = new Properties();
        final FileInputStream input = new FileInputStream(fileName);
        envProps.load(input);
        input.close();

        return envProps;
    }

	protected static Properties buildStreamsProperties(Properties envProps) {
	    Properties props = new Properties();
	
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, envProps.getProperty("application.id"));
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, envProps.getProperty("bootstrap.servers"));
		props.put(StreamsConfig.STATE_DIR_CONFIG, TestUtils.tempDirectory().getPath());
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.LongSerde.class);
//		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class); // Use this for NiFi integration
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
		props.put(SCHEMA_REGISTRY_URL_CONFIG, envProps.getProperty("schema.registry.url"));
		
		// Topology optimization activated
		props.put(StreamsConfig.TOPOLOGY_OPTIMIZATION_CONFIG, StreamsConfig.OPTIMIZE);
	    
	    props.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, envProps.getProperty("schema.registry.url"));
	
	    return props;
	}

	public static final <T extends SpecificRecord> SpecificAvroSerde<T> getSpecificAvroSerde(final Properties envProps) {
		final SpecificAvroSerde<T> specificAvroSerde = new SpecificAvroSerde<>();

		final HashMap<String, String> serdeConfig = new HashMap<>();
		serdeConfig.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, envProps.getProperty("schema.registry.url"));

		specificAvroSerde.configure(serdeConfig, false);
		return specificAvroSerde;
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> Serde<T> getPrimitiveDataAvroSerde(final Properties envProps, boolean isKey) {
		final KafkaAvroDeserializer deserializer = new KafkaAvroDeserializer();
		final KafkaAvroSerializer serializer = new KafkaAvroSerializer();
		final Map<String, String> config = new HashMap<>();
		config.put(SCHEMA_REGISTRY_URL_CONFIG,  envProps.getProperty("schema.registry.url"));
		deserializer.configure(config, isKey);
		serializer.configure(config, isKey);
		return (Serde<T>)Serdes.serdeFrom(serializer, deserializer);
	}
	
	private static final void createTopics(final Properties envProps) throws InterruptedException {
		final Map<String, Object> config = new HashMap<>();
		config.put("bootstrap.servers", envProps.getProperty("bootstrap.servers"));

		try (final AdminClient client = AdminClient.create(config)) {

			Map<String, String> topicNames = new TreeMap<String, String>();
			
			for (Object keyObj : envProps.keySet()) {
				String propertyName = (String) keyObj;
				
				if (propertyName.startsWith(PATTERN_TOPIC_NAME)) {
					topicNames.put(propertyName, envProps.getProperty(propertyName));
				}
			}

			final List<NewTopic> topics = new ArrayList<>();

			final Integer partitionCount = Integer.parseInt(envProps.getProperty("topic.partitions"));
			final Short replicationFactor = Short.parseShort(envProps.getProperty("topic.replication.factor"));

			for (Map.Entry<String, String> topicPair : topicNames.entrySet()) {
				Objects.requireNonNull(topicPair.getValue(), String.format("Input topic %s not set properly. Check config or topic names.\n", topicPair.getKey()));
				topics.add(new NewTopic(
						topicPair.getValue(),
						partitionCount,
						replicationFactor));
				System.out.printf("%s parameter added successfully as topic %s.\n", topicPair.getKey(), topicPair.getValue());
			}

			CreateTopicsResult topicCreationResult = client.createTopics(topics);
			KafkaFuture<Void> f = topicCreationResult.all();
			while (!f.isDone()) {
				System.out.println("Waiting for Topic creation...");
				Thread.sleep(100);
			}
		}
	}
	
    public final void processStreams(String[] args) throws Exception {

        if (args.length < 1) {
            throw new IllegalArgumentException("This program takes one argument: the path to an environment configuration file.");
        }

        final Properties envProps = loadEnvProperties(args[0]);

//        createTopics(envProps);
        
        final Properties streamProps = buildStreamsProperties(envProps);
        final Topology topology = this.buildTopology(envProps);
        
        System.out.println("Topology Description Start =============================================");
        System.out.println(TestUtils.tempDirectory().getPath());
        System.out.println(topology.describe());
        System.out.println("Topology Description End =============================================");

        final KafkaStreams streams = new KafkaStreams(topology, streamProps);
        final CountDownLatch latch = new CountDownLatch(1);

        // Attach shutdown handler to catch Control-C.
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close(Duration.ofSeconds(5));
                latch.countDown();
            }
        });

        try {
            streams.cleanUp();
            streams.start();
            latch.await();
        } catch (Throwable e) {
            System.exit(1);
        }
        System.exit(0);
    }
    
    public static void validateTopics(String ...topicName) throws ValidationException
    {
    	List<String> invalidTopicList = new ArrayList<>();
    	
    	String topicToValidate;
    	for (int i=0; i < topicName.length; ++i) {
    		topicToValidate = topicName[i];
    		
    		if (null == topicToValidate || topicToValidate.trim().isEmpty()) {
    			invalidTopicList.add(topicToValidate);
    		}
    	}
    	
    	
    }
    
    /**
     * Build kafka-stream processing topology.
     * Consider using validateTopic() before start processing
     * 
     * @param envProps
     * @return Topology
     * 
     * @see com.dtci.linear.analytics.kafkastreams.AbstractKafkaStreamsApp#validateTopics(String...)
     */
    protected abstract Topology buildTopology(Properties envProps);
    
}