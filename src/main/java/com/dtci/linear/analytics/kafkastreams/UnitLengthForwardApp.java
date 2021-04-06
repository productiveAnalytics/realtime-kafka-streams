package com.dtci.linear.analytics.kafkastreams;

import java.util.Properties;

import org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;

/**
 * App to copy Unit_Length topic to deal with 
 * Exception: Topic lndcdcadsinvntry_unitlength has already been registered by another source
 * 
 * Refer: KAFKA-6687 and KAFKA-6034
 * 
 * 
 * @author chawl001
 */
public final class UnitLengthForwardApp extends AbstractKafkaStreamsApp {

	@Override
	protected Topology buildTopology(Properties envProps) {
		final String UNIT_LENGTH_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "unit_length");
		
		StreamsBuilder forwarderBuilder = new StreamsBuilder();
		
		final Serde<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> unitLengthSerDe = getSpecificAvroSerde(envProps);
		final String mainTopic = UNIT_LENGTH_TOPIC;
		final String copyTopic = mainTopic + "2";
		final Consumed<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH> consumedForUnitLength = Consumed
				.<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH>as("input_from_"+ mainTopic)
				.withValueSerde(unitLengthSerDe);
		final Produced<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH> producedForUnitLength = Produced
				.<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH>as("output_to_"+ copyTopic)
				.withValueSerde(unitLengthSerDe);
		
		forwarderBuilder.stream(mainTopic, consumedForUnitLength).to(copyTopic, producedForUnitLength);
		
		return forwarderBuilder.build();
	}

}
