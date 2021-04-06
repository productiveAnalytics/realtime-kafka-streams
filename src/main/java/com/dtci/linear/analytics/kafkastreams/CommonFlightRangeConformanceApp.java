package com.dtci.linear.analytics.kafkastreams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.TransformerSupplier;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import org.LNDCDC_ADS_RTCRD.FLIGHT_RANGE.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_RANGE;
import org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR;

import java.util.Properties;

import org.LNDCDC_ADS_RTCRD.FLIGHT_QUARTER.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_QUARTER;
import org.LNDCDC_NCS_TCS.FLIGHT_DATES.apache.nifi.LNDCDC_NCS_TCS_FLIGHT_DATES;

import org.apache.nifi.custom.conformed.CONFORMED_CON_COMMON_FLGHT_RNG;
import org.apache.nifi.custom.joined.RTCRD_FLIGHT_RANGE_JOIN_FLIGHT;
import org.apache.nifi.custom.merged.RTCRD_UNIFIED_FLIGHT_DURATION;

import com.dtci.linear.analytics.kafkastreams.joins.FlightRangeUnifiedFlightDurationJoiner;
import com.dtci.linear.analytics.kafkastreams.joins.FltRangeFlightJoiner;

public class CommonFlightRangeConformanceApp extends AbstractKafkaStreamsApp {

	/**
	 * Intermediate state stores
	 */
	private static final String STATE_STORE_ENH_FLIGHT_YEAR 	= "state-store-enhanced-flight_year";
	private static final String STATE_STORE_ENH_FLIGHT_QUARTER 	= "state-store-enhanced-flight_quarter";
	private static final Serde<Long> SER_DE_LONG = Serdes.Long();

    public Topology buildTopology(Properties envProps) {
        final StreamsBuilder builder = new StreamsBuilder();

		 final String FLIGHT_RANGE_TOPIC 		= envProps.getProperty("topic.name.flight_range");
		 final String FLIGHT_YEAR_TOPIC 		= envProps.getProperty("topic.name.flight_year");
		 final String FLIGHT_QUARTER_TOPIC 		= envProps.getProperty("topic.name.flight_quarter");
		 final String FLIGHT_MONTH_TOPIC 		= envProps.getProperty("topic.name.flight_month");
		 final String FLIGHT_WEEK_TOPIC 		= envProps.getProperty("topic.name.flight_week");
		 final String FLIGHT_DATES_TOPIC 		= envProps.getProperty("topic.name.flight_dates");

		 final String FLIGHT_RANGE_CONFORMED_TOPIC = envProps.getProperty("topic.name.flight_range_conformed");
		 final String FLIGHT_RANGE_JOIN_FLIGHT = envProps.getProperty("topic.name.flight.range.join.flight");

		final Serde<LNDCDC_ADS_RTCRD_FLIGHT_RANGE> flightRngSerDe = getSpecificAvroSerde(envProps);
		Consumed<Long, LNDCDC_ADS_RTCRD_FLIGHT_RANGE> flightRangeConsumerCfg = Consumed.with(SER_DE_LONG, flightRngSerDe);
		
		// final <T> KStream<? super Comparable<T>, LNDCDC_ADS_RTCRD_FLIGHT_RANGE> flightRangeStream = 
		final KStream<Long, LNDCDC_ADS_RTCRD_FLIGHT_RANGE> flightRangeStream = builder.stream(FLIGHT_RANGE_TOPIC, flightRangeConsumerCfg);
		final KTable<Long, LNDCDC_ADS_RTCRD_FLIGHT_RANGE> flightRangeTable = flightRangeStream.toTable();

		final FltRangeFlightJoiner fltRangeFlightJoiner = new FltRangeFlightJoiner();
		final Serde<RTCRD_FLIGHT_RANGE_JOIN_FLIGHT> flightRangeJoinFlightSerde = getSpecificAvroSerde(envProps);
		final Serde<LNDCDC_NCS_TCS_FLIGHT_DATES> flightDatesSerde = getSpecificAvroSerde(envProps);

		KTable<Long, LNDCDC_NCS_TCS_FLIGHT_DATES> flightDatesTable = builder.table(FLIGHT_DATES_TOPIC, Consumed.with(SER_DE_LONG, flightDatesSerde));
		KTable<Long, RTCRD_FLIGHT_RANGE_JOIN_FLIGHT> flightRangeJoinedFlight = flightRangeTable.
				leftJoin(flightDatesTable, LNDCDC_ADS_RTCRD_FLIGHT_RANGE::getFLIGHTID, fltRangeFlightJoiner);

		flightRangeJoinedFlight.toStream().to(FLIGHT_RANGE_JOIN_FLIGHT, Produced.with(SER_DE_LONG, flightRangeJoinFlightSerde));

        final Serde<RTCRD_UNIFIED_FLIGHT_DURATION> unifiedFlightDurationSerDe = getSpecificAvroSerde(envProps);
        
     
        StoreBuilder<KeyValueStore<Long, RTCRD_UNIFIED_FLIGHT_DURATION>> flightYearStoreBuilder =
                Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(STATE_STORE_ENH_FLIGHT_YEAR),
						SER_DE_LONG,
                unifiedFlightDurationSerDe);
        // register store
        builder.addStateStore(flightYearStoreBuilder);
        

        StoreBuilder<KeyValueStore<Long, RTCRD_UNIFIED_FLIGHT_DURATION>> flightQuarterStoreBuilder =
                Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(STATE_STORE_ENH_FLIGHT_QUARTER),
						SER_DE_LONG,
                unifiedFlightDurationSerDe);
        // register store
        builder.addStateStore(flightQuarterStoreBuilder);
        
        
        final Serde<LNDCDC_ADS_RTCRD_FLIGHT_YEAR> flightYearSerDe = getSpecificAvroSerde(envProps);
        final KStream<Long, LNDCDC_ADS_RTCRD_FLIGHT_YEAR> flightYrStream = builder
        		.stream(FLIGHT_YEAR_TOPIC, Consumed.with(SER_DE_LONG, flightYearSerDe));
		final KStream<Long, RTCRD_UNIFIED_FLIGHT_DURATION> unifiedYearStream = flightYrStream
				.transform(new TransformerSupplier<Long, LNDCDC_ADS_RTCRD_FLIGHT_YEAR, KeyValue<Long, RTCRD_UNIFIED_FLIGHT_DURATION>>() {
		
					@Override
					public Transformer<Long, LNDCDC_ADS_RTCRD_FLIGHT_YEAR, KeyValue<Long, RTCRD_UNIFIED_FLIGHT_DURATION>> get() {
						
						// TODO: Get using factory
						return new Transformer<Long, LNDCDC_ADS_RTCRD_FLIGHT_YEAR, KeyValue<Long,RTCRD_UNIFIED_FLIGHT_DURATION>>() {
		
							@Override
							public void init(ProcessorContext context) {
								// TODO Auto-generated method stub
								
							}
		
							@Override
							public KeyValue<Long, RTCRD_UNIFIED_FLIGHT_DURATION> transform(Long key,
									LNDCDC_ADS_RTCRD_FLIGHT_YEAR value) {
								final RTCRD_UNIFIED_FLIGHT_DURATION flightDuration = new RTCRD_UNIFIED_FLIGHT_DURATION();
								flightDuration.setFLGHTRNGTYCD("YR");
								flightDuration.setFLIGHTRANGEID(value.getFLIGHTRANGEID());
								flightDuration.setCALENDARID(value.getCALENDARID());
								flightDuration.setYEARNUM(value.getYEARNUM());
								flightDuration.setQUARTERNUM(0d);
								flightDuration.setMONTHNUM(0d);
								flightDuration.setWEEKNUM(0d);
								
								flightDuration.setLASTMODIFIEDBY(value.getLASTMODIFIEDBY());
								flightDuration.setLASTMODIFIEDDT(value.getLASTMODIFIEDDT());
								flightDuration.setSRCKEYVAL(value.getSRCKEYVAL());
								flightDuration.setSRCCDCOPERNM(value.getSRCCDCOPERNM());
								flightDuration.setSRCCOMMITDTUTC(value.getSRCCOMMITDTUTC());
								flightDuration.setTRGCRTDTPARTUTC(value.getTRGCRTDTPARTUTC());
								flightDuration.setSRCSCHEMANM(value.getSRCSCHEMANM());
								
								return KeyValue.<Long, RTCRD_UNIFIED_FLIGHT_DURATION>pair(key, flightDuration);
							}
		
							@Override
							public void close() {
								// TODO Auto-generated method stub
								
							}
						};
					}
				}, 
				STATE_STORE_ENH_FLIGHT_YEAR);
        
        
        final Serde<LNDCDC_ADS_RTCRD_FLIGHT_QUARTER> flightQrtSerDe = getSpecificAvroSerde(envProps);
        final KStream<Long, LNDCDC_ADS_RTCRD_FLIGHT_QUARTER> flightQtrStream = builder
        		.stream(FLIGHT_QUARTER_TOPIC, Consumed.with(SER_DE_LONG, flightQrtSerDe));
        final KStream<Long, RTCRD_UNIFIED_FLIGHT_DURATION> unifiedQuarterStream = flightQtrStream
        		.transform(new TransformerSupplier<Long, LNDCDC_ADS_RTCRD_FLIGHT_QUARTER, KeyValue<Long, RTCRD_UNIFIED_FLIGHT_DURATION>>() {

					@Override
					public Transformer<Long, LNDCDC_ADS_RTCRD_FLIGHT_QUARTER, KeyValue<Long, RTCRD_UNIFIED_FLIGHT_DURATION>> get() {
						
						// TODO: Get using factory
						return new Transformer<Long, LNDCDC_ADS_RTCRD_FLIGHT_QUARTER, KeyValue<Long,RTCRD_UNIFIED_FLIGHT_DURATION>>() {

							@Override
							public void init(ProcessorContext context) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public KeyValue<Long, RTCRD_UNIFIED_FLIGHT_DURATION> transform(Long key,
									LNDCDC_ADS_RTCRD_FLIGHT_QUARTER value) {
								final RTCRD_UNIFIED_FLIGHT_DURATION flightDuration = new RTCRD_UNIFIED_FLIGHT_DURATION();
								flightDuration.setFLGHTRNGTYCD("QTR");
								flightDuration.setFLIGHTRANGEID(value.getFLIGHTRANGEID());
								flightDuration.setCALENDARID(value.getCALENDARID());
								flightDuration.setYEARNUM(value.getYEARNUM());
								flightDuration.setQUARTERNUM(value.getQUARTERNUM());
								flightDuration.setMONTHNUM(0d);
								flightDuration.setWEEKNUM(0d);
								
								flightDuration.setLASTMODIFIEDBY(value.getLASTMODIFIEDBY());
								flightDuration.setLASTMODIFIEDDT(value.getLASTMODIFIEDDT());
								flightDuration.setSRCKEYVAL(value.getSRCKEYVAL());
								flightDuration.setSRCCDCOPERNM(value.getSRCCDCOPERNM());
								flightDuration.setSRCCOMMITDTUTC(value.getSRCCOMMITDTUTC());
								flightDuration.setTRGCRTDTPARTUTC(value.getTRGCRTDTPARTUTC());
								flightDuration.setSRCSCHEMANM(value.getSRCSCHEMANM());
								
								return KeyValue.<Long, RTCRD_UNIFIED_FLIGHT_DURATION>pair(key, flightDuration);
							}

							@Override
							public void close() {
								// TODO Auto-generated method stub
								
							}
						};
					}
				}, 
        		STATE_STORE_ENH_FLIGHT_QUARTER);
        
        final KStream<Long, RTCRD_UNIFIED_FLIGHT_DURATION> mergedStream = unifiedYearStream.merge(unifiedQuarterStream);
        final Materialized<Long, RTCRD_UNIFIED_FLIGHT_DURATION, KeyValueStore<Bytes, byte[]>> mergedTableMaterialized = Materialized
        		.<Long, RTCRD_UNIFIED_FLIGHT_DURATION, KeyValueStore<Bytes, byte[]>>as("unified-flight-duration")
        		.withKeySerde(SER_DE_LONG)
        		.withValueSerde(unifiedFlightDurationSerDe);
        final KTable<Long, RTCRD_UNIFIED_FLIGHT_DURATION> mergedTable = mergedStream
        		.toTable(Named.as("unified-flight-duration-table"), mergedTableMaterialized);

        
        final FlightRangeUnifiedFlightDurationJoiner conformanceJoined = new FlightRangeUnifiedFlightDurationJoiner("ADS_RTCRD");
        KTable<Long, CONFORMED_CON_COMMON_FLGHT_RNG> conformedFlightRange = 
        		flightRangeJoinedFlight.leftJoin(mergedTable, conformanceJoined);


        final Serde<CONFORMED_CON_COMMON_FLGHT_RNG> conformedFlightRangeSerDe = getSpecificAvroSerde(envProps);
        conformedFlightRange.toStream()
        	.to(FLIGHT_RANGE_CONFORMED_TOPIC, 
        		Produced.with(SER_DE_LONG, conformedFlightRangeSerDe));


        return builder.build();
    }

}