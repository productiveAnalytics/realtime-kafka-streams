package com.dtci.linear.analytics.kafkastreams;

import java.util.Properties;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Initializer;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Named;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.kstream.TransformerSupplier;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

// main/driver entity
import org.LNDCDC_ADS_PRPSL.PROPOSAL_LINE.apache.nifi.LNDCDC_ADS_PRPSL_PROPOSAL_LINE;

// dependents
import org.LNDCDC_ADS_RTCRD.ROTATION_PERIOD_RELEASE.apache.nifi.LNDCDC_ADS_RTCRD_ROTATION_PERIOD_RELEASE;
import org.LNDCDC_ADS_INVNTRY.INVENTORY_TYPE.apache.nifi.LNDCDC_ADS_INVNTRY_INVENTORY_TYPE;
import org.LNDCDC_ADS_PRPSL.PROPOSAL.apache.nifi.LNDCDC_ADS_PRPSL_PROPOSAL;
import org.LNDCDC_ADS_RTCRD.RATE_CARD.apache.nifi.LNDCDC_ADS_RTCRD_RATE_CARD;
import org.LNDCDC_NCS_TCS.CURRENCY_EXCHANGE.apache.nifi.LNDCDC_NCS_TCS_CURRENCY_EXCHANGE;
import org.LNDCDC_NCS_TCS.CURRENCIES.apache.nifi.LNDCDC_NCS_TCS_CURRENCIES;
import org.LNDCDC_ADS_PRPSL.TELEVISION_PROPOSAL.apache.nifi.LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL;
import org.LNDCDC_ADS_PRPSL.AD_UNIT_SCHD_ALOCTN_TYP.apache.nifi.LNDCDC_ADS_PRPSL_AD_UNIT_SCHD_ALOCTN_TYP;
import org.LNDCDC_ADS_PRPSL.TELEVISION_PROPOSAL_LINE.apache.nifi.LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL_LINE;
import org.LNDCDC_ADS_PRPSL.PRPSL_INVNTRY_TYP.apache.nifi.LNDCDC_ADS_PRPSL_PRPSL_INVNTRY_TYP;
import org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH;
import org.LNDCDC_ADS_RTCRD.PKG_PRD_VRSN_RLS.apache.nifi.LNDCDC_ADS_RTCRD_PKG_PRD_VRSN_RLS;
import org.LNDCDC_ADS_RTCRD.RATE_CARD_RELEASE.apache.nifi.LNDCDC_ADS_RTCRD_RATE_CARD_RELEASE;
import org.LNDCDC_ADS_PRPSL.TLVSN_LN_ASGMNT_CNSTRNT.apache.nifi.LNDCDC_ADS_PRPSL_TLVSN_LN_ASGMNT_CNSTRNT;
import org.LNDCDC_ADS_PRPSL.TLVSN_PRPSL_LN_DTL.apache.nifi.LNDCDC_ADS_PRPSL_TLVSN_PRPSL_LN_DTL;
import org.LNDCDC_ADS_PRPSL.TLVSN_LN_DTL_EPSD_AIRG.apache.nifi.LNDCDC_ADS_PRPSL_TLVSN_LN_DTL_EPSD_AIRG;
import org.LNDCDC_ADS_PRPSL.TLVSN_ASGMNT_DAY_CNSTRN.apache.nifi.LNDCDC_ADS_PRPSL_TLVSN_ASGMNT_DAY_CNSTRN;

// aggregates
import org.apache.nifi.custom.aggregated.TV_ASSIGNMENT_DAYS_AGG;

// Unified POJO
import org.apache.nifi.custom.joined.UNIFIED_JOIN_FOR_PROPOSAL_LINE;

/**
 * Conform proposal_line using Kafka Streams
 * 
 * NOTE: Start dependency app UnitLengthForwardApp
 * 
 * @author chawl001
 * @see com.dtci.linear.analytics.kafkastreams.UnitLengthForwardApp
 */
public class ProposalLineConformanceApp extends AbstractKafkaStreamsApp {

	/**
	 * Intermediate state stores
	 */
	private static final String STATE_STORE_PROPOSAL_LINE_JOIN_READY 	= "state-store-proposal-line-join-ready";
	private static final String STATE_STORE_PROPOSAL_JOIN_READY 		= "state-store-proposal-join-ready";
	private static final String STATE_STORE_CURRENCY_EXCHANGE_USD_JOIN_READY   = "state-store-currency-exchange-USD-join-ready";
	private static final String STATE_STORE_PROPOSAL_INVENTORY_TYPE_JOIN_READY = "state-store-proposal-inventory-type-join-ready";
	private static final String STATE_STORE_TV_PROPOSAL_JOIN_READY 		= "state-store-tv-proposal-join-ready";
	private static final String STATE_STORE_TV_PROPOSAL_LINE_JOIN_READY = "state-store-tv-proposal-line-join-ready";
	private static final String STATE_STORE_PACKAGE_PRODUCT_VERSION_RELEASE_JOIN_READY = "state-store-package-product-version-release-join-ready";
	private static final String STATE_STORE_TV_LINE_ASSIGNMENT_CONSTRAINT_JOIN_READY = "state-store-tv-line-assignment-constraint-join-ready";
	private static final String STATE_STORE_TV_PROPOSAL_LINE_DETAIL_JOIN_READY	= "state-store-tv-proposal-line-detail-join-ready";
	
	private static final Serde<Long> SER_DE_LONG = Serdes.Long();
	
	private static final String INDICATOR_N = "N";
	private static final String INDICATOR_Y = "Y";
	
	private static final int MON = 0;
	private static final int TUE = 1;
	private static final int WED = 2;
	private static final int THU = 3;
	private static final int FRI = 4;
	private static final int SAT = 5;
	private static final int SUN = 6;
	
	private static enum JOIN_NAME {
		CURRENCY_EXCHANGE_LJ_CURRENCIES("currency_exchange_join_currencies"),
		PROPOSAL_LJ_RATECARD("proposal_join_ratecard"),
		PROPOSAL_LJ_CURRENCY_EXCHANGE_FOR_USD("proposal_join_currency_exchange_USD"),
//		PROPOSAL_LJ_TV_PROPOSAL("proposal_join_tv_proposal"),
		
		PROPOSAL_INVENTORY_TYPE_LJ_UNIT_LENGTH("proposal_inventory_type_join_unit_length"),
		
		TV_PROPOSAL_LINE_LJ_UNIT_LENGTH("tv_proposal_line_join_unit_length"),
		TV_PROPOSAL_LINE_LJ_TV_LINE_ASSIGNMENT_CONSTRAINT_LEVEL("tv_proposal_line_join_tv_line_assignment_constraint-level"),
		TV_PROPOSAL_LINE_LJ_TV_LINE_DETAIL_AGGREGATE("tv_proposal_line_join_tv_proposal_line_detail_aggregate-level"),
		
//		TV_PROPOSAL_LJ_TV_PROPOSAL_LINE("tv_proposal_join_tv_proposal_line"),
		TV_PROPOSAL_LJ_AD_UNIT_SCHEDULE_ALLOCATION_TYPE("tv_proposal_join_ad_unit_schedule_alloc_type"),
		
		PKG_PRD_VERSION_RELEASE_LJ_RATECARD_RELEASE("package_product_version_release_join_ratecard_release"),
		
		TV_LINE_ASSIGNMENT_CONSTRAINT_LJ_TV_ASSIGNMENT_DAY_CONSTRAINT("tv_line_assignment_constraint_join_tv_assignment_day_constraint_agg"),
		
		TV_PROPOSAL_LINE_DETAIL_AGG_LT_TV_LINE_DETAIL_EPISOD_AIRING_AGG("tv_proposal_line_detail_agg_join_tv_line_detail_episode_airing_agg");
		
		private String joinName;
		
		private JOIN_NAME(String name) {
			joinName = name;
		}
		
		public String getName() {
			return this.joinName;
		}
	}

    public Topology buildTopology(Properties envProps) {
        final StreamsBuilder builder = new StreamsBuilder();
        
        // TODO: externalize topic key as constant per module
        final String PROPOSAL_LINE_TOPIC 	= envProps.getProperty(PATTERN_TOPIC_NAME + "proposal_line");
        
        /*
         * dependents
         */
        final String ROTATION_PERIOD_RELEASE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "rotation_period_release");
        final String INVENTORY_TYPE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "inventory_type");
        
        final String PROPOSAL_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "proposal");
        final String RATECARD_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "ratecard");
        
        final String CURRENCY_EXCHANGE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "currency_exchange");
        final String CURRENCIES_TOPIC			= envProps.getProperty(PATTERN_TOPIC_NAME + "currencies");
        		
        final String PKG_PRD_VERSION_RELEASE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "package_product_version_release");
        final String RATECARD_RELEASE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "ratecard_release");
        
        final String TV_PROPOSAL_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "television_proposal");
        final String AD_UNIT_SCHEDULE_ALLOCATION_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "ad_unit_schedule_allocation_type");
        
        final String TV_PROPOSAL_LINE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "television_proposal_line");
        final String PROPOSAL_INVENTORY_TYPE = envProps.getProperty(PATTERN_TOPIC_NAME + "proposal_inventory_type");
        final String UNIT_LENGTH_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "unit_length");
        
        final String TV_LINE_ASSIGN_CONSTRAINT_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "television_line_assignment_constraint");

        final String TV_ASSIGN_DAY_CONSTRAINT_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "television_assignment_day_constraint");
        final String TV_PROPOSAL_LINE_DETAIL_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "television_proposal_line_detail");
        final String TV_LINE_DETAIL_EPISOD_AIRING_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "television_line_detail_episod_airing");

        
        // conformed output
        final String PROPOSAL_LINE_CONFORMED_TOPIC 	= envProps.getProperty(PATTERN_TOPIC_NAME + "proposal_line_conformed");
        
		validateTopics(PROPOSAL_LINE_TOPIC,
				PROPOSAL_TOPIC, RATECARD_TOPIC, CURRENCY_EXCHANGE_TOPIC, CURRENCIES_TOPIC,
				PKG_PRD_VERSION_RELEASE_TOPIC, RATECARD_RELEASE_TOPIC,
				TV_PROPOSAL_TOPIC, AD_UNIT_SCHEDULE_ALLOCATION_TOPIC,
				TV_PROPOSAL_LINE_TOPIC, PROPOSAL_INVENTORY_TYPE, UNIT_LENGTH_TOPIC,
				TV_LINE_ASSIGN_CONSTRAINT_TOPIC,
				
				// topic on which aggregation is performed
				TV_PROPOSAL_LINE_DETAIL_TOPIC,
				TV_LINE_DETAIL_EPISOD_AIRING_TOPIC,
				TV_ASSIGN_DAY_CONSTRAINT_TOPIC,
				
				/*
				 * TODO: add other topics to validate here
				 */
				PROPOSAL_LINE_CONFORMED_TOPIC);
		
/*
insert into conformance_lndcdcadsprpsl_tlvsnasgmntdaycnstrn_aggr
(
    select * 
    from (
        select TLVSN_LN_ASGMNT_CNSTRNT_ID, LISTAGG( cast( DAY_OF_WK_NUM as String)) as DAY_OF_WK_NUM_LIST
        from lndcdcadsprpsl_tlvsnasgmntdaycnstrn 
        group by TLVSN_LN_ASGMNT_CNSTRNT_ID
        ),  LATERAL TABLE(DayNumUDF(DAY_OF_WK_NUM_LIST))
)
*/

		
		final Serde<LNDCDC_ADS_PRPSL_PROPOSAL_LINE> proposalLineSerDe = getSpecificAvroSerde(envProps);
		KStream<Long, LNDCDC_ADS_PRPSL_PROPOSAL_LINE> proposalLineStream = builder
				.stream(PROPOSAL_LINE_TOPIC, Consumed.with(SER_DE_LONG, proposalLineSerDe));
		
		final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> proposalLineJoinReadySerDe = getSpecificAvroSerde(envProps);
		StoreBuilder<KeyValueStore<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> proposalLineJoinReadyStoreBuilderForStream =
				Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(STATE_STORE_PROPOSAL_LINE_JOIN_READY + "_stream"),
						SER_DE_LONG,
						proposalLineJoinReadySerDe);
		builder.addStateStore(proposalLineJoinReadyStoreBuilderForStream);

		// Transform to utilize unified join (Step 1)
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> proposalLineJoinReadyTbl = 
				proposalLineStream
				.transform(new TransformerSupplier<Long, LNDCDC_ADS_PRPSL_PROPOSAL_LINE, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						@Override
						public Transformer<Long, LNDCDC_ADS_PRPSL_PROPOSAL_LINE, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
							return new Transformer<Long, LNDCDC_ADS_PRPSL_PROPOSAL_LINE, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
								
								private ProcessorContext processorCtx;
								
								@Override
								public void init(ProcessorContext context) {
									this.processorCtx = context;
								}

								@Override
								public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_ADS_PRPSL_PROPOSAL_LINE pl) {
									UNIFIED_JOIN_FOR_PROPOSAL_LINE proposalLineJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
											// Important ID and FK
											.setID$1(pl.getPROPOSALLINEID())
											.setPROPOSALLINEROWTIME(processorCtx.timestamp())
											.setJOINEDROWTIME(processorCtx.timestamp())
											.setPROPOSALLINEID(pl.getPROPOSALLINEID())
											.setPROPOSALID(pl.getPROPOSALID())
											.setPARENTPROPOSALLINEID(pl.getPARENTPROPOSALLINEID())
											.setFLIGHTID(pl.getFLIGHTID())
											.setOTLTEXTID(pl.getOTLTEXTID())
											
											/*
											 * TODO: populate other fields from PROPOSAL_LINE
											 */
											
											// ABC fields
											.setSRCKEYVAL(pl.getSRCKEYVAL())
											.setSRCCDCOPERNM(pl.getSRCCDCOPERNM())
											.setSRCCOMMITDTUTC(pl.getSRCCOMMITDTUTC())
											.setTRGCRTDTPARTUTC(pl.getTRGCRTDTPARTUTC())
											.setSRCSCHEMANM(pl.getSRCSCHEMANM())
											
											.build();
									
									return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, proposalLineJoinReady);
								}

								@Override
								public void close() {
									// do nothing
								}
							};
						}
					},
					STATE_STORE_PROPOSAL_LINE_JOIN_READY + "_stream"
				)
				.toTable(
					Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_PROPOSAL_LINE_JOIN_READY + "_table")
					.withKeySerde(SER_DE_LONG)
					.withValueSerde(proposalLineJoinReadySerDe)
				);
        
		// bring onto Proposal level (step 2)
		final Serde<LNDCDC_ADS_RTCRD_ROTATION_PERIOD_RELEASE> rotationPeriodReleaseSerDe = getSpecificAvroSerde(envProps);
		final KTable<Long, LNDCDC_ADS_RTCRD_ROTATION_PERIOD_RELEASE> rotationPeriodRelease = builder.table(
				ROTATION_PERIOD_RELEASE_TOPIC,
				Consumed.with(SER_DE_LONG, rotationPeriodReleaseSerDe),
				Materialized.<Long, LNDCDC_ADS_RTCRD_ROTATION_PERIOD_RELEASE, KeyValueStore<Bytes, byte[]>>as("rotation-period-release_table"));
				
		final Serde<LNDCDC_ADS_INVNTRY_INVENTORY_TYPE> inventoryTypeSerDe = getSpecificAvroSerde(envProps);
		final KTable<Long, LNDCDC_ADS_INVNTRY_INVENTORY_TYPE> inventoryType = builder.table(
				INVENTORY_TYPE_TOPIC, 
				Consumed.with(SER_DE_LONG, inventoryTypeSerDe),
				Materialized.<Long, LNDCDC_ADS_INVNTRY_INVENTORY_TYPE, KeyValueStore<Bytes, byte[]>>as("inventory-type_table"));
		
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> stagedProposalTable = buildProposalLevelTable(builder,envProps);
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> stagedTVProposalTable = buildTVProposalLevelTable(builder, envProps);
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> stagedTVProposalLineTable =  buildTVProposalLineLevelTable(builder, envProps);
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> stagedProposalInventoryTypeTable = buildProposalInventoryTypeLevelTable(builder,envProps);
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> stagedPackageProductVersionReleaseTable = buildPackageProductVersionReleaseLevelTable(builder,envProps);
//		final 
		// dependency ..n
					
		//
		// TODO: IMPROVEMENT by creating tree and traverse it to build immediate-level joins for PROPOSAL_LINE
		//
		// Step 3
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> preconformanceTbl =
		proposalLineJoinReadyTbl
		.leftJoin(
			rotationPeriodRelease,
			UNIFIED_JOIN_FOR_PROPOSAL_LINE::getROTATIONPERIODRELEASEID,
			new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_ADS_RTCRD_ROTATION_PERIOD_RELEASE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
				@Override
				public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePL, LNDCDC_ADS_RTCRD_ROTATION_PERIOD_RELEASE rotationPeriodRelease) {
					if (rotationPeriodRelease != null) {
						intermediatePL.setROTATIONPERIODID(rotationPeriodRelease.getROTATIONPERIODID());
						intermediatePL.setUNITRATE(rotationPeriodRelease.getUNITRATE());
					}
					
					return intermediatePL;
				}
			},
			Named.as("proposal_line_left_join_rotation_period_release")
		)
		.leftJoin(
			inventoryType,
			UNIFIED_JOIN_FOR_PROPOSAL_LINE::getSALESINVENTORYTYPEID,
			new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_ADS_INVNTRY_INVENTORY_TYPE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
				@Override
				public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePL, LNDCDC_ADS_INVNTRY_INVENTORY_TYPE inventoryType) {
					if (inventoryType != null) {
						intermediatePL.setINVENTORYTYPECD(inventoryType.getINVENTORYTYPECD());
					}
					
					return intermediatePL;
				}
			},
			Named.as("proposal_line_left_join_rotation_period_release")
		)
		.leftJoin(
			stagedProposalTable,
			UNIFIED_JOIN_FOR_PROPOSAL_LINE::getPROPOSALID,
			new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
				@Override
				public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePL, UNIFIED_JOIN_FOR_PROPOSAL_LINE proposal) {
					if (proposal != null) {
						intermediatePL.setRATECARDID(proposal.getRATECARDID());
						intermediatePL.setEQVRTSIND(proposal.getEQVRTSIND());
						intermediatePL.setAGENCYCOMMISSION(proposal.getAGENCYCOMMISSION());
						intermediatePL.setCURRENCYKEY(proposal.getCURRENCYKEY());
					}
					
					return intermediatePL;
				}
			},
			Named.as("proposal_line_left_join_proposal-staged")
		)
		.leftJoin(
			stagedTVProposalTable,
			UNIFIED_JOIN_FOR_PROPOSAL_LINE::getPROPOSALID,
			new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
				@Override
				public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePL, UNIFIED_JOIN_FOR_PROPOSAL_LINE tvProposal) {
					if (tvProposal != null) {
						intermediatePL.setADUNITSCHDALOCTNTYPID(tvProposal.getADUNITSCHDALOCTNTYPID());
					}
					
					return intermediatePL;
				}
			},
			Named.as("proposal_line_left_join_tv_proposal-staged")
		)
		.leftJoin(
			stagedTVProposalLineTable,
			UNIFIED_JOIN_FOR_PROPOSAL_LINE::getPROPOSALID,
			new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
				@Override
				public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePL, UNIFIED_JOIN_FOR_PROPOSAL_LINE tvProposalLine) {
					if (tvProposalLine != null) {
						/*
						 * TV_PROPOSAL_LINE join UNIT_LENGTH
						 */
						// UNIT_LGTH_ID
						intermediatePL.setTVUNITLENGTHQTY(tvProposalLine.getTVUNITLENGTHQTY());
						
						intermediatePL.setCOSTPERUNITQTY(tvProposalLine.getCOSTPERUNITQTY());
						intermediatePL.setINITCOSTPERUNITQTY(tvProposalLine.getINITCOSTPERUNITQTY());
						intermediatePL.setMAXADUNITSPERWKQTY(tvProposalLine.getMAXADUNITSPERWKQTY());
						// Use _1 fields for TV_Proposal_Line
						intermediatePL.setUNITLENGTHID1(tvProposalLine.getUNITLENGTHID1());
						intermediatePL.setUNITLENGTHQTY1(tvProposalLine.getUNITLENGTHQTY1());
						
						/*
						 * TV_PROPOSAL_LINE join TV Line Assignment Constraint
						 */
					}
					
					return intermediatePL;
				}
			},
			Named.as("proposal_line_left_join_tv_proposal_line-staged")				
		)
		.leftJoin(
			stagedProposalInventoryTypeTable,
			UNIFIED_JOIN_FOR_PROPOSAL_LINE::getPROPOSALID,
			new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
				@Override
				public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePL, UNIFIED_JOIN_FOR_PROPOSAL_LINE proposalInventoryType) {
					if (proposalInventoryType != null) {
						intermediatePL.setPRPSLINVNTRYTYPID(proposalInventoryType.getPRPSLINVNTRYTYPID());
						intermediatePL.setPRPSLINVNTRYTYPUNITLENGTHID(proposalInventoryType.getPRPSLINVNTRYTYPUNITLENGTHID());
						intermediatePL.setRTADJMTFACTOR(proposalInventoryType.getRTADJMTFACTOR());

						// Use _2 fields for Proposal_Inventory_Type
						intermediatePL.setUNITLENGTHID2(proposalInventoryType.getUNITLENGTHID2());
						intermediatePL.setUNITLENGTHQTY2(proposalInventoryType.getUNITLENGTHQTY2());
					}
					
					return intermediatePL;
				}
			},
			Named.as("proposal_line_left_join_tv_proposal_inventory_type-staged")				
		)
		.leftJoin(
				stagedPackageProductVersionReleaseTable,
				UNIFIED_JOIN_FOR_PROPOSAL_LINE::getPKGPRDVRSNRLSID,
				new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
					@Override
					public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePL, UNIFIED_JOIN_FOR_PROPOSAL_LINE pkgPrdVrsnRls) {
						if (pkgPrdVrsnRls != null) {
							intermediatePL.setRATECARDRELEASEID(pkgPrdVrsnRls.getRATECARDRELEASEID());
							intermediatePL.setRELEASEDT(pkgPrdVrsnRls.getRELEASEDT());
						}
						
						return intermediatePL;
					}
				},
				Named.as("proposal_line_left_join_package_product_version_release-staged")				
			)
		;
		
		preconformanceTbl.toStream()
		.to("preconformance-proposal-line-topic", 
			Produced.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>as("preconformance-proposal-line-producer").withValueSerde(proposalLineJoinReadySerDe)
		);
		
        return builder.build(envProps);
    }

    /*
     * TODO: move this into framework that traverses tree, and uses join fields & projected fields from configuration
     * 
     * @param <T>
     * @param joinName
     * @param leftTable
     * @param rightTable
     * 
     * @return KTable with Unified POJO
     */
    private static <T extends SpecificRecord> KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> leftJoinTables(JOIN_NAME joinName, 
    		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> leftTable, 
    		KTable<Long, T> rightTable) {
		switch (joinName) 
		{
			case PROPOSAL_LJ_RATECARD:
				@SuppressWarnings("unchecked")
				final KTable<Long, LNDCDC_ADS_RTCRD_RATE_CARD> rateCardTbl = 
					(KTable<Long, LNDCDC_ADS_RTCRD_RATE_CARD>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> proposalLeftJoinRatecardTbl = 
				leftTable
				.leftJoin(
					rateCardTbl,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getRATECARDID,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_ADS_RTCRD_RATE_CARD, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateProposal, LNDCDC_ADS_RTCRD_RATE_CARD rateCard) {
							if (rateCard != null) {
								intermediateProposal.setRATECARDTYPEID(rateCard.getRATECARDTYPEID());
							}
							
							return intermediateProposal;
						}
					},
					Named.as(JOIN_NAME.PROPOSAL_LJ_RATECARD.getName())
				);
				return proposalLeftJoinRatecardTbl;
				
			case CURRENCY_EXCHANGE_LJ_CURRENCIES:
				@SuppressWarnings("unchecked")
				final KTable<Long, LNDCDC_NCS_TCS_CURRENCIES> usdCurrenciesTbl = 
					(KTable<Long, LNDCDC_NCS_TCS_CURRENCIES>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> currencyExchangeLeftJoinUSDCurrenciesTbl = 
				leftTable
				.leftJoin(
					usdCurrenciesTbl,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getCECURRENCYID,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_NCS_TCS_CURRENCIES, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE currExchange, LNDCDC_NCS_TCS_CURRENCIES usdCurrency) {
							if (usdCurrency != null) {
								// Nothing pulled from currencies
							}
							
							return currExchange;
						}
					},
					Named.as(JOIN_NAME.CURRENCY_EXCHANGE_LJ_CURRENCIES.getName())
				);
				return currencyExchangeLeftJoinUSDCurrenciesTbl;
				
			case PROPOSAL_LJ_CURRENCY_EXCHANGE_FOR_USD:
				@SuppressWarnings("unchecked")
				final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> currencyExchangeTbl =
					(KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>) rightTable;
				final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> proposalLeftJoinCurrencyExchangeTbl = 
				leftTable
				.leftJoin(
					currencyExchangeTbl,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getCURRENCYKEY,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateProposal, UNIFIED_JOIN_FOR_PROPOSAL_LINE currExchng) {
							if (null != currExchng) {
								// TODO: date between logic
								
								intermediateProposal.setCECURRENCYID(currExchng.getCECURRENCYID());
								intermediateProposal.setCEEXCHGRATE(currExchng.getCEEXCHGRATE());
								intermediateProposal.setCEEFFDATE(currExchng.getCEEFFDATE());
								intermediateProposal.setCEENDDATE(currExchng.getCEENDDATE());
								intermediateProposal.setCETOCURRENCY(currExchng.getCETOCURRENCY());
								intermediateProposal.setCDCKEY(currExchng.getCDCKEY());
							}
							
							return intermediateProposal;
						}
					},
					Named.as(JOIN_NAME.PROPOSAL_LJ_CURRENCY_EXCHANGE_FOR_USD.getName())
				);
				return proposalLeftJoinCurrencyExchangeTbl;
					
			
			case TV_PROPOSAL_LJ_AD_UNIT_SCHEDULE_ALLOCATION_TYPE:
				@SuppressWarnings("unchecked")
				final KTable<Long, LNDCDC_ADS_PRPSL_AD_UNIT_SCHD_ALOCTN_TYP> adUnitScheduleAllocTypeTbl = 
					(KTable<Long, LNDCDC_ADS_PRPSL_AD_UNIT_SCHD_ALOCTN_TYP>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLeftJoinAdUnitScheduleAllocTypeTbl =
				leftTable
				.leftJoin(
					adUnitScheduleAllocTypeTbl,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getADUNITSCHDALOCTNTYPID,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_ADS_PRPSL_AD_UNIT_SCHD_ALOCTN_TYP, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateTVProposal, LNDCDC_ADS_PRPSL_AD_UNIT_SCHD_ALOCTN_TYP adUnitScheduleAllocType) {
							if (adUnitScheduleAllocType != null) {
								String adUnitScheduleAllocCode = adUnitScheduleAllocType.getADUNITSCHDALOCTNTYPCD().toString();
								
								if ("ROS".equals(adUnitScheduleAllocCode)) {
									// will be non-null only if AD_UNIT_SCHD_ALOCTN_TYP_ID=ROS
									intermediateTVProposal.setADUNITSCHDALOCTNTYPID(adUnitScheduleAllocType.getADUNITSCHDALOCTNTYPID());
								} else {
									intermediateTVProposal.setADUNITSCHDALOCTNTYPID(null);
								}
							} else {
								intermediateTVProposal.setADUNITSCHDALOCTNTYPID(null);
							}
							
							return intermediateTVProposal;
						}
					},
					Named.as(JOIN_NAME.TV_PROPOSAL_LJ_AD_UNIT_SCHEDULE_ALLOCATION_TYPE.getName())
				);
				return tvProposalLeftJoinAdUnitScheduleAllocTypeTbl;
				
			case TV_PROPOSAL_LINE_LJ_UNIT_LENGTH:
				@SuppressWarnings("unchecked")
				final KTable<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH> unitLengthTbl = 
					(KTable<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineLeftJoinUnitLengthTbl =
				leftTable
				.leftJoin(
					unitLengthTbl,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getTVUNITLENGTHQTY, // acts as Unit_Length_ID
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_ADS_INVNTRY_UNIT_LENGTH, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateTVProposalLine, LNDCDC_ADS_INVNTRY_UNIT_LENGTH unitLenght) {
							if (unitLenght != null) {
								intermediateTVProposalLine.setUNITLENGTHID1(unitLenght.getUNITLENGTHID());
								intermediateTVProposalLine.setUNITLENGTHQTY1(unitLenght.getUNITLENGTHQTY()); // actual Unit_Length_Quantity
							} else {
								intermediateTVProposalLine.setUNITLENGTHID1(PLACEHOLDER_LONG);
								intermediateTVProposalLine.setUNITLENGTHQTY1(PLACEHOLDER_LONG);
							}
							
							return intermediateTVProposalLine;
						}
					},
					Named.as(JOIN_NAME.TV_PROPOSAL_LINE_LJ_UNIT_LENGTH.getName())
				);
				return tvProposalLineLeftJoinUnitLengthTbl;

				
			case TV_PROPOSAL_LINE_LJ_TV_LINE_ASSIGNMENT_CONSTRAINT_LEVEL:
				@SuppressWarnings("unchecked")
				final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineAssignmentConstraintLevelTble = 
					(KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineLeftJoinTVLineAssignmentConstraintAggTbl =
				leftTable
				.leftJoin(
					tvProposalLineAssignmentConstraintLevelTble,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getTELEVISIONPROPOSALLINEID,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateTVProposalLine, UNIFIED_JOIN_FOR_PROPOSAL_LINE tvLineAssignmentConstraintLevelTable) {
							if (null != tvLineAssignmentConstraintLevelTable) {
								intermediateTVProposalLine.setMONIND(tvLineAssignmentConstraintLevelTable.getMONIND());
								intermediateTVProposalLine.setTUEIND(tvLineAssignmentConstraintLevelTable.getTUEIND());
								intermediateTVProposalLine.setWEDIND(tvLineAssignmentConstraintLevelTable.getWEDIND());
								intermediateTVProposalLine.setTHUIND(tvLineAssignmentConstraintLevelTable.getTHUIND());
								intermediateTVProposalLine.setFRIIND(tvLineAssignmentConstraintLevelTable.getFRIIND());
								intermediateTVProposalLine.setSATIND(tvLineAssignmentConstraintLevelTable.getSATIND());
								intermediateTVProposalLine.setSUNIND(tvLineAssignmentConstraintLevelTable.getSUNIND());
							}
							
							return intermediateTVProposalLine;
						}
					},
					Named.as(JOIN_NAME.TV_PROPOSAL_LINE_LJ_TV_LINE_ASSIGNMENT_CONSTRAINT_LEVEL.getName())
				);
				return tvProposalLineLeftJoinTVLineAssignmentConstraintAggTbl;
				
			case TV_PROPOSAL_LINE_LJ_TV_LINE_DETAIL_AGGREGATE:
				@SuppressWarnings("unchecked")
				final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineDetailAggregatedTbl = 
					(KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineLeftJoinTVProposalLineDetailAgg =
				leftTable
				.leftJoin(
						tvProposalLineDetailAggregatedTbl,
						UNIFIED_JOIN_FOR_PROPOSAL_LINE::getTELEVISIONPROPOSALLINEID,
						new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
							@Override
							public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateTVProposalLine, 
									UNIFIED_JOIN_FOR_PROPOSAL_LINE tvLineDetailAgg) {
								if (null != tvLineDetailAgg) {
									intermediateTVProposalLine.setUNITQTYSUM(tvLineDetailAgg.getUNITQTYSUM());
									intermediateTVProposalLine.setTOTALCOUNT(tvLineDetailAgg.getTOTALCOUNT());
								}
								
								return intermediateTVProposalLine;
							}
						},
						Named.as(JOIN_NAME.TV_PROPOSAL_LINE_LJ_TV_LINE_DETAIL_AGGREGATE.getName())
						
				);
				return tvProposalLineLeftJoinTVProposalLineDetailAgg;
				
				
			case TV_LINE_ASSIGNMENT_CONSTRAINT_LJ_TV_ASSIGNMENT_DAY_CONSTRAINT:
				@SuppressWarnings("unchecked")
				final KTable<Long, TV_ASSIGNMENT_DAYS_AGG> tvAssignmentDayConstraintAggregatedTbl = 
					(KTable<Long, TV_ASSIGNMENT_DAYS_AGG>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvLnAsgnCnstntLeftJoinTVAsgnDaysCnstntTbl =
				leftTable
				.leftJoin(
					tvAssignmentDayConstraintAggregatedTbl,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getTLVSNLNASGMNTCNSTRNTID,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, TV_ASSIGNMENT_DAYS_AGG, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(
								UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateTVLineAssignmentConstraint,
								TV_ASSIGNMENT_DAYS_AGG tvAssignmentDayConstraintAggregate)
						{
							if (null != tvAssignmentDayConstraintAggregate) {
								intermediateTVLineAssignmentConstraint.setMONIND(tvAssignmentDayConstraintAggregate.getMONIND());
								intermediateTVLineAssignmentConstraint.setTUEIND(tvAssignmentDayConstraintAggregate.getTUEIND());
								intermediateTVLineAssignmentConstraint.setWEDIND(tvAssignmentDayConstraintAggregate.getWEDIND());
								intermediateTVLineAssignmentConstraint.setTHUIND(tvAssignmentDayConstraintAggregate.getTHUIND());
								intermediateTVLineAssignmentConstraint.setFRIIND(tvAssignmentDayConstraintAggregate.getFRIIND());
								intermediateTVLineAssignmentConstraint.setSATIND(tvAssignmentDayConstraintAggregate.getSATIND());
								intermediateTVLineAssignmentConstraint.setSUNIND(tvAssignmentDayConstraintAggregate.getSUNIND());
							} else {
								intermediateTVLineAssignmentConstraint.setMONIND(null);
								intermediateTVLineAssignmentConstraint.setTUEIND(null);
								intermediateTVLineAssignmentConstraint.setWEDIND(null);
								intermediateTVLineAssignmentConstraint.setTHUIND(null);
								intermediateTVLineAssignmentConstraint.setFRIIND(null);
								intermediateTVLineAssignmentConstraint.setSATIND(null);
								intermediateTVLineAssignmentConstraint.setSUNIND(null);
							}
							
							return intermediateTVLineAssignmentConstraint;
						}
					},
					Named.as(JOIN_NAME.TV_LINE_ASSIGNMENT_CONSTRAINT_LJ_TV_ASSIGNMENT_DAY_CONSTRAINT.getName())
				);
				return tvLnAsgnCnstntLeftJoinTVAsgnDaysCnstntTbl;
						
				
			case PROPOSAL_INVENTORY_TYPE_LJ_UNIT_LENGTH:
				@SuppressWarnings("unchecked")
				final KTable<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH> unitLengthTbl2 = 
					(KTable<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> proposalInventoryTypeLeftJoinUnitLengthTbl =
				leftTable
				.leftJoin(
					unitLengthTbl2,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getPRPSLINVNTRYTYPUNITLENGTHID,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_ADS_INVNTRY_UNIT_LENGTH, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediateProposalInventoryType, LNDCDC_ADS_INVNTRY_UNIT_LENGTH unitLenght) {
							if (unitLenght != null) {
								intermediateProposalInventoryType.setUNITLENGTHID2(unitLenght.getUNITLENGTHID());
								intermediateProposalInventoryType.setUNITLENGTHQTY2(unitLenght.getUNITLENGTHQTY());
							} else {
								intermediateProposalInventoryType.setUNITLENGTHID2(PLACEHOLDER_LONG);
								intermediateProposalInventoryType.setUNITLENGTHQTY2(PLACEHOLDER_LONG);
							}
							
							return intermediateProposalInventoryType;
						}
					},
					Named.as(JOIN_NAME.PROPOSAL_INVENTORY_TYPE_LJ_UNIT_LENGTH.getName())
				);
				return proposalInventoryTypeLeftJoinUnitLengthTbl;

				
			case PKG_PRD_VERSION_RELEASE_LJ_RATECARD_RELEASE:
				@SuppressWarnings("unchecked")
				final KTable<Long, LNDCDC_ADS_RTCRD_RATE_CARD_RELEASE> ratecardReleaseTbl = 
					(KTable<Long, LNDCDC_ADS_RTCRD_RATE_CARD_RELEASE>) rightTable;
				KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> pkgPrdVrsnRelLeftJoinRatecardReleaseTbl =
				leftTable
				.leftJoin(
					ratecardReleaseTbl,
					UNIFIED_JOIN_FOR_PROPOSAL_LINE::getRATECARDRELEASEID,
					new ValueJoiner<UNIFIED_JOIN_FOR_PROPOSAL_LINE, LNDCDC_ADS_RTCRD_RATE_CARD_RELEASE, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {
						@Override
						public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(UNIFIED_JOIN_FOR_PROPOSAL_LINE intermediatePkgPrdVerRel, LNDCDC_ADS_RTCRD_RATE_CARD_RELEASE ratecardRelease) {
							if (ratecardRelease != null) {
								intermediatePkgPrdVerRel.setRELEASEDT(ratecardRelease.getRELEASEDT());
							} else {
								intermediatePkgPrdVerRel.setRELEASEDT(PLACEHOLDER_STR);
							}
							
							return intermediatePkgPrdVerRel;
						}
					},
					Named.as(JOIN_NAME.PKG_PRD_VERSION_RELEASE_LJ_RATECARD_RELEASE.getName())
				);
				return pkgPrdVrsnRelLeftJoinRatecardReleaseTbl;
				
			default:
				System.err.println("ERROR: Unimplemented join for "+ joinName);
				return null; 
		}
    }
    
	private static void registerStateStore(final StreamsBuilder builder,
			final String stateStoreName,
			final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe) 
	{
		StoreBuilder<KeyValueStore<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> joinReadyStoreBuilderForStream =
				Stores.keyValueStoreBuilder(Stores.persistentKeyValueStore(stateStoreName + "_stream"),
						SER_DE_LONG,
						unifiedJoinReadySerDe);
		builder.addStateStore(joinReadyStoreBuilderForStream);
	}
    
    /*
     * PROPOSAL table level
     */
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildProposalLevelTable(final StreamsBuilder builder, final Properties envProps) {
        final String PROPOSAL_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "proposal");
        final String RATECARD_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "ratecard");
        
        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_ADS_PRPSL_PROPOSAL> proposalSerDe = getSpecificAvroSerde(envProps);
        
        registerStateStore(builder, STATE_STORE_PROPOSAL_JOIN_READY, unifiedJoinReadySerDe);
        
        // Step 1.: make join ready
        KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> proposalTbl =
        builder.stream(PROPOSAL_TOPIC, Consumed.with(SER_DE_LONG, proposalSerDe))
			.transform(new TransformerSupplier<Long, LNDCDC_ADS_PRPSL_PROPOSAL, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
				@Override
				public Transformer<Long, LNDCDC_ADS_PRPSL_PROPOSAL, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
					return new Transformer<Long, LNDCDC_ADS_PRPSL_PROPOSAL, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						
						private ProcessorContext processorCtx;
						
						@Override
						public void init(ProcessorContext context) {
							this.processorCtx = context;
						}
	
						@Override
						public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_ADS_PRPSL_PROPOSAL proposal) {
							UNIFIED_JOIN_FOR_PROPOSAL_LINE proposalLineJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()

									.setPROPOSALLINEROWTIME(PLACEHOLDER_LONG)
									.setJOINEDROWTIME(processorCtx.timestamp())
									
									.setPROPOSALID(proposal.getPROPOSALID())
									.setRATECARDID(proposal.getRATECARDID())	// FK
									.setEQVRTSIND(proposal.getEQVRTSIND())
									.setAGENCYCOMMISSION(proposal.getAGENCYCOMMISSION())
									.setCURRENCYKEY(proposal.getCURRENCYKEY())
									.setPROPOSALCREATEDDT(proposal.getCREATEDDT())
									
									.build();
							
							return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, proposalLineJoinReady);
						}
	
						@Override
						public void close() {
							// do nothing
						}
					};
				}
			},
			STATE_STORE_PROPOSAL_JOIN_READY + "_stream"
		)
		.toTable(
			Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_PROPOSAL_JOIN_READY + "_table")
			.withKeySerde(SER_DE_LONG)
			.withValueSerde(unifiedJoinReadySerDe)
		);
        
        // Step 2.1 get all dependencies
        final Serde<LNDCDC_ADS_RTCRD_RATE_CARD> ratecardSerDe = getSpecificAvroSerde(envProps);
		KTable<Long, LNDCDC_ADS_RTCRD_RATE_CARD> ratecardTbl = builder.table(RATECARD_TOPIC, Consumed.with(SER_DE_LONG, ratecardSerDe));
		// Step 3.1 left-join to extract needed info
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> leftJoin1 = leftJoinTables(
				JOIN_NAME.PROPOSAL_LJ_RATECARD,
				proposalTbl,
				ratecardTbl
		);
		
		// Step 2.2
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> currencyExchangeJoinReady = buildCurrencyExchangeLevelTable(builder, envProps);
		// Step 3.2
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> leftJoin2 = leftJoinTables(
				JOIN_NAME.PROPOSAL_LJ_CURRENCY_EXCHANGE_FOR_USD,
				leftJoin1,
				currencyExchangeJoinReady
		);
		
		return leftJoin2;
    }
    
    /*
    insert into conformance_lndcdcncstcs_curr_exchg
    (
	    select 
	    	lndcdcncstcs_currencyexchange._ID as _ID,
		    lndcdcncstcs_currencyexchange.SRC_KEY_VAL,lndcdcncstcs_currencyexchange.CDC_KEY,
			CONVERT_TZ(lndcdcncstcs_currencyexchange.CE_EFF_DATE,'America/New_York','UTC') as CE_EFF_DATE_UTC, 
			CONVERT_TZ(lndcdcncstcs_currencyexchange.CE_END_DATE,'America/New_York','UTC') as CE_END_DATE_UTC,
			lndcdcncstcs_currencyexchange.CE_EXCHG_RATE, lndcdcncstcs_currencyexchange.CE_CURRENCY_ID
		from lndcdcncstcs_currencyexchange
			inner join lndcdcncstcs_currencies 
				on lndcdcncstcs_currencyexchange.CE_TO_CURRENCY=lndcdcncstcs_currencies._ID
		where lndcdcncstcs_currencies.CU_CURRENCY_ABBR='USD'
	)
    */
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildCurrencyExchangeLevelTable(final StreamsBuilder builder, final Properties envProps) {
        final String CURRENCY_EXCHANGE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "currency_exchange");
        final String CURRENCIES_TOPIC			= envProps.getProperty(PATTERN_TOPIC_NAME + "currencies");
        
        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_NCS_TCS_CURRENCY_EXCHANGE> currencyExchngSerDe = getSpecificAvroSerde(envProps);
        
        registerStateStore(builder, STATE_STORE_CURRENCY_EXCHANGE_USD_JOIN_READY, unifiedJoinReadySerDe);
        
        // Step 1
        KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> currencyExchangeTbl =
        builder.stream(CURRENCY_EXCHANGE_TOPIC, Consumed.with(SER_DE_LONG, currencyExchngSerDe))
			.transform(new TransformerSupplier<Long, LNDCDC_NCS_TCS_CURRENCY_EXCHANGE, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
				@Override
				public Transformer<Long, LNDCDC_NCS_TCS_CURRENCY_EXCHANGE, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
					return new Transformer<Long, LNDCDC_NCS_TCS_CURRENCY_EXCHANGE, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						
						private ProcessorContext processorCtx;
						
						@Override
						public void init(ProcessorContext context) {
							this.processorCtx = context;
						}
	
						@Override
						public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_NCS_TCS_CURRENCY_EXCHANGE currExchng) {
							UNIFIED_JOIN_FOR_PROPOSAL_LINE currencyExchangeJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
									.setPROPOSALLINEROWTIME(PLACEHOLDER_LONG)
									.setJOINEDROWTIME(processorCtx.timestamp())
									
									.setCECURRENCYID(currExchng.getCECURRENCYID())
									.setCEEXCHGRATE(currExchng.getCEEXCHGRATE())
									.setCEEFFDATE(currExchng.getCEEFFDATE())
									.setCEENDDATE(currExchng.getCEENDDATE())
									.setCETOCURRENCY(currExchng.getCETOCURRENCY()) // FK
									.setCDCKEY(currExchng.getCDCKEY())
									
									.build();
							
							return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, currencyExchangeJoinReady);
						}
	
						@Override
						public void close() {
							// do nothing
						}
					};
				}
			},
			STATE_STORE_CURRENCY_EXCHANGE_USD_JOIN_READY + "_stream"
		)
		.toTable(
			Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_CURRENCY_EXCHANGE_USD_JOIN_READY + "_table")
			.withKeySerde(SER_DE_LONG)
			.withValueSerde(unifiedJoinReadySerDe)
		);
        
        // Step 2
        final Serde<LNDCDC_NCS_TCS_CURRENCIES> currenciesSerDe = getSpecificAvroSerde(envProps);
		KTable<Long, LNDCDC_NCS_TCS_CURRENCIES> usdCurrenciesTbl = builder
				.table(CURRENCIES_TOPIC, Consumed.with(SER_DE_LONG, currenciesSerDe))
				.filter((k,v) -> "USD".equals(v.getCUCURRENCYABBR()), Named.as("only_currency_USD"));
		
		// Step 3
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> currExchgForUSD = 
				leftJoinTables(
						JOIN_NAME.CURRENCY_EXCHANGE_LJ_CURRENCIES, 
						currencyExchangeTbl,
						usdCurrenciesTbl
		);
		
		// repartition to allow join on CE_CURRENCY_ID
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> repartitionedCurrExchgTbl = currExchgForUSD
				.toStream()
				.selectKey(new KeyValueMapper<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, Long>() {
	
						@Override
						public Long apply(Long key, UNIFIED_JOIN_FOR_PROPOSAL_LINE value) {
							return value.getCECURRENCYID();
						}
						
					}, Named.as("repartition-curr-exchg_stream")
				)
				.toTable(Named.as("repartition-curr-exchg_table"));
		
		return repartitionedCurrExchgTbl;
    }

    
    /*
     * TV_PROPOSAL table level
     */
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildTVProposalLevelTable(final StreamsBuilder builder, final Properties envProps) {
        final String TV_PROPOSAL_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "television_proposal");
        final String AD_UNIT_SCHEDULE_ALLOCATION_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "ad_unit_schedule_allocation_type");
		
        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL> tvProposalSerDe = getSpecificAvroSerde(envProps);
        
        registerStateStore(builder, STATE_STORE_TV_PROPOSAL_JOIN_READY, unifiedJoinReadySerDe);
        
        // Step 1
        KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalTbl =
        builder.stream(TV_PROPOSAL_TOPIC, Consumed.with(SER_DE_LONG, tvProposalSerDe))
			.transform(new TransformerSupplier<Long, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
				@Override
				public Transformer<Long, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
					return new Transformer<Long, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						
						private ProcessorContext processorCtx;
						
						@Override
						public void init(ProcessorContext context) {
							this.processorCtx = context;
						}
	
						@Override
						public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL tvProposal) {
							UNIFIED_JOIN_FOR_PROPOSAL_LINE proposalLineJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
									.setPROPOSALLINEROWTIME(PLACEHOLDER_LONG)
									.setJOINEDROWTIME(processorCtx.timestamp())
									
									.setTELEVISIONPROPOSALID(tvProposal.getTELEVISIONPROPOSALID())
									
									.build();
							
							return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, proposalLineJoinReady);
						}
	
						@Override
						public void close() {
							// do nothing
						}
					};
				}
			},
			STATE_STORE_TV_PROPOSAL_JOIN_READY + "_stream"
		)
		.toTable(
			Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_TV_PROPOSAL_JOIN_READY + "_table")
			.withKeySerde(SER_DE_LONG)
			.withValueSerde(unifiedJoinReadySerDe)
		);
        
        // Step 2
        final Serde<LNDCDC_ADS_PRPSL_AD_UNIT_SCHD_ALOCTN_TYP> adUnitScheduleAllocTypeSerDe = getSpecificAvroSerde(envProps);
		KTable<Long, LNDCDC_ADS_PRPSL_AD_UNIT_SCHD_ALOCTN_TYP> adUnitScheduleAllocTypeTbl = builder
				.table(AD_UNIT_SCHEDULE_ALLOCATION_TOPIC, Consumed.with(SER_DE_LONG, adUnitScheduleAllocTypeSerDe));
		
		// Step 3
		return leftJoinTables(JOIN_NAME.TV_PROPOSAL_LJ_AD_UNIT_SCHEDULE_ALLOCATION_TYPE, tvProposalTbl, adUnitScheduleAllocTypeTbl);
    }
    
    /*
     * TV_Proposal_LINE table level
     */
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildTVProposalLineLevelTable(final StreamsBuilder builder, final Properties envProps) {
        final String TV_PROPOSAL_LINE_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "television_proposal_line");
        final String UNIT_LENGTH_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "unit_length");
		
        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL_LINE> tvProposalLineSerDe = getSpecificAvroSerde(envProps);
        
        registerStateStore(builder, STATE_STORE_TV_PROPOSAL_LINE_JOIN_READY, unifiedJoinReadySerDe);
        
        // Step 1
        KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineTbl =
        builder.stream(TV_PROPOSAL_LINE_TOPIC, Consumed.with(SER_DE_LONG, tvProposalLineSerDe))
			.transform(new TransformerSupplier<Long, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL_LINE, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
				@Override
				public Transformer<Long, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL_LINE, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
					return new Transformer<Long, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL_LINE, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						
						private ProcessorContext processorCtx;
						
						@Override
						public void init(ProcessorContext context) {
							this.processorCtx = context;
						}
	
						@Override
						public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_ADS_PRPSL_TELEVISION_PROPOSAL_LINE tvProposalLine) {
							UNIFIED_JOIN_FOR_PROPOSAL_LINE proposalLineJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
									.setPROPOSALLINEROWTIME(PLACEHOLDER_LONG)
									.setJOINEDROWTIME(processorCtx.timestamp())
									
									.setTELEVISIONPROPOSALLINEID(tvProposalLine.getTELEVISIONPROPOSALLINEID())
									
//									+ " lndcdcadsprpsl_televisionproposalline.COST_PER_UNIT_QTY as UNIT_RT ,"
//									+ " lndcdcadsprpsl_televisionproposalline.INIT_COST_PER_UNIT_QTY as INIT_UNIT_RT,"
//									+ " lndcdcadsprpsl_televisionproposalline.UNIT_LENGTH_QTY as UNIT_LGTH_ID ,"
//									+ " lndcdcadsprpsl_televisionproposalline.MAX_AD_UNITS_PER_WK_QTY as MAX_UNIT_PER_WK_QTY,"
									.setCOSTPERUNITQTY(tvProposalLine.getCOSTPERUNITQTY())
									.setINITCOSTPERUNITQTY(tvProposalLine.getINITCOSTPERUNITQTY())
									.setTVUNITLENGTHQTY((null == tvProposalLine.getUNITLENGTHQTY()) ? PLACEHOLDER_LONG : tvProposalLine.getUNITLENGTHQTY().longValue())
									.setMAXADUNITSPERWKQTY(tvProposalLine.getMAXADUNITSPERWKQTY())
									
									.build();
							
							return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, proposalLineJoinReady);
						}
	
						@Override
						public void close() {
							// do nothing
						}
					};
				}
			},
			STATE_STORE_TV_PROPOSAL_LINE_JOIN_READY + "_stream"
		)
		.toTable(
			Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_TV_PROPOSAL_LINE_JOIN_READY + "_table")
			.withKeySerde(SER_DE_LONG)
			.withValueSerde(unifiedJoinReadySerDe)
		);
        
        // Step 2.1
        final Serde<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> unitLengthForTVProposalLineSerDe = getSpecificAvroSerde(envProps);
		KTable<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH> unitLengthForTVProposalLineTbl = builder
				.table(UNIT_LENGTH_TOPIC, Consumed.with(SER_DE_LONG, unitLengthForTVProposalLineSerDe));
		// Step 3.1
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> leftJoin1 = leftJoinTables(
				JOIN_NAME.TV_PROPOSAL_LINE_LJ_UNIT_LENGTH, 
				tvProposalLineTbl,
				unitLengthForTVProposalLineTbl
		);
		
		// Step 2.2
		final KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineAssignmentConstraintLevelTable = 
				buildTVAssignmentConstraintLevelTable(builder, envProps);
		// Step 3.2
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> leftJoin2 = leftJoinTables(
				JOIN_NAME.TV_PROPOSAL_LINE_LJ_TV_LINE_ASSIGNMENT_CONSTRAINT_LEVEL,
				leftJoin1,
				tvProposalLineAssignmentConstraintLevelTable
		);

		// Step 2.3
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineDetailAggregateTable = 
				buildTVProposalLineDetailsLevelTable(builder, envProps);
		// Step 3.3
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> leftJoin3 = leftJoinTables(
				JOIN_NAME.TV_PROPOSAL_LINE_LJ_TV_LINE_DETAIL_AGGREGATE,
				leftJoin2,
				tvProposalLineDetailAggregateTable
		);
		
		return leftJoin3;
    }
    
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildTVProposalLineDetailsLevelTable(final StreamsBuilder builder, final Properties envProps) {
        final String TV_PROPOSAL_LINE_DETAIL_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "television_proposal_line_detail");
        final String TV_LINE_DETAIL_EPISOD_AIRING_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "television_line_detail_episod_airing");
		
        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_ADS_PRPSL_TLVSN_PRPSL_LN_DTL> tvProposalLineDetailSerDe = getSpecificAvroSerde(envProps);
        
        registerStateStore(builder, STATE_STORE_TV_PROPOSAL_LINE_DETAIL_JOIN_READY, unifiedJoinReadySerDe);
        
        // Step 1
		/*
			insert into conformance_lndcdcadsprpsl_tlvsnprpsllndtl_aggr 
			(
			select TELEVISION_PROPOSAL_LINE_ID, sum(UNIT_QTY) as UNIT_QTY_SUM
			from lndcdcadsprpsl_tlvsnprpsllndtl 
			group by TELEVISION_PROPOSAL_LINE_ID
			)
		 */
		KStream<Long, LNDCDC_ADS_PRPSL_TLVSN_PRPSL_LN_DTL> tvProposalLineDetailStream = builder
				.stream(TV_PROPOSAL_LINE_DETAIL_TOPIC, Consumed.with(SER_DE_LONG, tvProposalLineDetailSerDe));
		KTable<Long, Double> tvProposalLineDetail_agg = tvProposalLineDetailStream
			.groupBy(new KeyValueMapper<Long, LNDCDC_ADS_PRPSL_TLVSN_PRPSL_LN_DTL, Long>() {
	
				@Override
				public Long apply(Long key, LNDCDC_ADS_PRPSL_TLVSN_PRPSL_LN_DTL value) {
					/*
					String srcKeyVal = value.getSRCKEYVAL().toString();
					Long newKey = Long.valueOf(srcKeyVal);
					return newKey;
					*/
					return value.getTELEVISIONPROPOSALLINEID();
				}
				
			}, Grouped.with(SER_DE_LONG, tvProposalLineDetailSerDe))
			.aggregate(new Initializer<Double>() {
		
					@Override
					public Double apply() {
						return 0d;
					}
				}, 
				new Aggregator<Long, LNDCDC_ADS_PRPSL_TLVSN_PRPSL_LN_DTL, Double>() {
	
					@Override
					public Double apply(Long key, LNDCDC_ADS_PRPSL_TLVSN_PRPSL_LN_DTL value, Double aggregatedUnitQty) {
						Double unitQty = value.getUNITQTY();
						if (null == unitQty) {
							return aggregatedUnitQty;
						} else {
							return Double.sum(aggregatedUnitQty, unitQty);
						}
					}
				},
				Named.as("sum-unit_qty-by-key-for_TV_proposal_line_detail"),
				Materialized.<Long, Double, KeyValueStore<Bytes,byte[]>>as("sum-unit_qty-agg")
			)
			;
		
        // Step 2
		/*
			insert into conformance_lndcdcadsprpsl_tlvsnlndtlepsdairg_aggr 
			(
			select TLVSN_PRPSL_LN_DTL_ID, count(TLVSN_PRPSL_LN_DTL_ID) as TOTAL_COUNT
			from lndcdcadsprpsl_tlvsnlndtlepsdairg 
			group by TLVSN_PRPSL_LN_DTL_ID
			)
		 */
		final Serde<LNDCDC_ADS_PRPSL_TLVSN_LN_DTL_EPSD_AIRG> tvProposalLineDetailEpisodeAiringSerDe = getSpecificAvroSerde(envProps);
		KStream<Long, LNDCDC_ADS_PRPSL_TLVSN_LN_DTL_EPSD_AIRG> tvProposalLineDetailEpisodeAiringStream = builder
				.stream(TV_LINE_DETAIL_EPISOD_AIRING_TOPIC, Consumed.with(SER_DE_LONG, tvProposalLineDetailEpisodeAiringSerDe));
		KTable<Long, Long> tvProposalLineDetailEpisodeAiring_agg = tvProposalLineDetailEpisodeAiringStream
				.groupBy(
						new KeyValueMapper<Long, LNDCDC_ADS_PRPSL_TLVSN_LN_DTL_EPSD_AIRG, Long>() {
							
							@Override
							public Long apply(Long key, LNDCDC_ADS_PRPSL_TLVSN_LN_DTL_EPSD_AIRG value) {
								/*
								String srcKeyVal = value.getSRCKEYVAL().toString();
								Long newKey = Long.valueOf(srcKeyVal);
								return newKey;
								*/
								return value.getTLVSNPRPSLLNDTLID();
							}
							
						}, Grouped.with(SER_DE_LONG, tvProposalLineDetailEpisodeAiringSerDe)
				)
				.count(Named.as("total_count-by-key-for-TV_proposal_line_detail_episode_airing"), 
					   Materialized.<Long, Long, KeyValueStore<Bytes,byte[]>>as("total_count-agg"));
		
		
		// step 3
		/*
			tlvsnprpsllndtl_aggr.TELEVISION_PROPOSAL_LINE_ID = lndcdcadsprpsltlvsnlndtlepsdairg_aggr.TLVSN_PRPSL_LN_DTL_ID
		 */
		KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineDetailLevelTbl =
		tvProposalLineDetail_agg.leftJoin(tvProposalLineDetailEpisodeAiring_agg, 
				new ValueJoiner<Double, Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>() {

					@Override
					public UNIFIED_JOIN_FOR_PROPOSAL_LINE apply(Double sumUnitQuantity, Long totalCount) {
						UNIFIED_JOIN_FOR_PROPOSAL_LINE u = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
								.setUNITQTYSUM(sumUnitQuantity)
								.setTOTALCOUNT(totalCount)
								.build();
						return u;
					}
					
				}, 
				Named.as("TV_proposal_line_detail_agg-join-ready"),
				Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as("TV_proposal_line_detail_agg-state-store")
		);
		
		return tvProposalLineDetailLevelTbl;
    }    
    
    /*
     * PROPOSAL_INVENTORY_TYPE table level
     */
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildProposalInventoryTypeLevelTable(final StreamsBuilder builder, final Properties envProps) {
        final String PROPOSAL_INVENTORY_TYPE_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "proposal_inventory_type");
        final String UNIT_LENGTH_TOPIC_2	= envProps.getProperty(PATTERN_TOPIC_NAME + "unit_length") + "2";

        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_ADS_PRPSL_PRPSL_INVNTRY_TYP> proposalInventoryTypeSerDe = getSpecificAvroSerde(envProps);
        
        registerStateStore(builder, STATE_STORE_PROPOSAL_INVENTORY_TYPE_JOIN_READY, unifiedJoinReadySerDe);
        
        // Step 1
        KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvProposalLineTbl =
        builder.stream(PROPOSAL_INVENTORY_TYPE_TOPIC, Consumed.with(SER_DE_LONG, proposalInventoryTypeSerDe))
			.transform(new TransformerSupplier<Long, LNDCDC_ADS_PRPSL_PRPSL_INVNTRY_TYP, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
				@Override
				public Transformer<Long, LNDCDC_ADS_PRPSL_PRPSL_INVNTRY_TYP, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
					return new Transformer<Long, LNDCDC_ADS_PRPSL_PRPSL_INVNTRY_TYP, KeyValue<Long,UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						
						private ProcessorContext processorCtx;
						
						@Override
						public void init(ProcessorContext context) {
							this.processorCtx = context;
						}
	
						@Override
						public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_ADS_PRPSL_PRPSL_INVNTRY_TYP proposalInventoryType) {
							UNIFIED_JOIN_FOR_PROPOSAL_LINE proposalLineJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
									.setPROPOSALLINEROWTIME(PLACEHOLDER_LONG)
									.setJOINEDROWTIME(processorCtx.timestamp())
									
									.setPRPSLINVNTRYTYPID(proposalInventoryType.getINVNTRYTYPID())
									.setPRPSLINVNTRYTYPUNITLENGTHID(proposalInventoryType.getUNITLENGTHID())
									.setRTADJMTFACTOR(proposalInventoryType.getRTADJMTFACTOR())
									
									.build();
							
							return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, proposalLineJoinReady);
						}
	
						@Override
						public void close() {
							// do nothing
						}
					};
				}
			},
			STATE_STORE_PROPOSAL_INVENTORY_TYPE_JOIN_READY + "_stream"
		)
		.toTable(
			Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_PROPOSAL_INVENTORY_TYPE_JOIN_READY + "_table")
			.withKeySerde(SER_DE_LONG)
			.withValueSerde(unifiedJoinReadySerDe)
		);
        
        // Step 2
        final Serde<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> unitLengthForProposalInventoryTypeSerDe = getSpecificAvroSerde(envProps);
		KTable<Long, LNDCDC_ADS_INVNTRY_UNIT_LENGTH> unitLengthForProposalInventoryTypeTbl = builder
				.table(UNIT_LENGTH_TOPIC_2, Consumed.with(SER_DE_LONG, unitLengthForProposalInventoryTypeSerDe));
		
		// Step 3
		return leftJoinTables(JOIN_NAME.PROPOSAL_INVENTORY_TYPE_LJ_UNIT_LENGTH, tvProposalLineTbl, unitLengthForProposalInventoryTypeTbl);
    }
    
    /*
     * PKG_PRD_VERSN_REL table level
     * 
     */
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildPackageProductVersionReleaseLevelTable(final StreamsBuilder builder, final Properties envProps) {
    	final String PKG_PRD_VERSION_RELEASE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "package_product_version_release");
        final String RATECARD_RELEASE_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "ratecard_release");

        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_ADS_RTCRD_PKG_PRD_VRSN_RLS> pkgPrdVersionReleaseSerDe = getSpecificAvroSerde(envProps);
        
		registerStateStore(builder, STATE_STORE_PACKAGE_PRODUCT_VERSION_RELEASE_JOIN_READY, unifiedJoinReadySerDe);
		
        // Step 1
        KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> pkgPrdVrsnRelTbl =
        builder.stream(PKG_PRD_VERSION_RELEASE_TOPIC, Consumed.with(SER_DE_LONG, pkgPrdVersionReleaseSerDe))
			.transform(new TransformerSupplier<Long, LNDCDC_ADS_RTCRD_PKG_PRD_VRSN_RLS, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
				@Override
				public Transformer<Long, LNDCDC_ADS_RTCRD_PKG_PRD_VRSN_RLS, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
					return new Transformer<Long, LNDCDC_ADS_RTCRD_PKG_PRD_VRSN_RLS, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						
						private ProcessorContext processorCtx;
						
						@Override
						public void init(ProcessorContext context) {
							this.processorCtx = context;
						}
	
						@Override
						public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_ADS_RTCRD_PKG_PRD_VRSN_RLS pkgPrdVersionRelease) {
							UNIFIED_JOIN_FOR_PROPOSAL_LINE packageProductVersionReleaseJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
									.setPROPOSALLINEROWTIME(PLACEHOLDER_LONG)
									.setJOINEDROWTIME(processorCtx.timestamp())
									
									.setRATECARDRELEASEID(pkgPrdVersionRelease.getRATECARDRELEASEID())
									
									.build();
							
							return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, packageProductVersionReleaseJoinReady);
						}
	
						@Override
						public void close() {
							// do nothing
						}
					};
				}
			},
			STATE_STORE_PACKAGE_PRODUCT_VERSION_RELEASE_JOIN_READY + "_stream"
		)
		.toTable(
			Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_PACKAGE_PRODUCT_VERSION_RELEASE_JOIN_READY + "_table")
			.withKeySerde(SER_DE_LONG)
			.withValueSerde(unifiedJoinReadySerDe)
		);
        
        // Step 2
        final Serde<LNDCDC_ADS_RTCRD_RATE_CARD_RELEASE> ratecardReleaseSerDe = getSpecificAvroSerde(envProps);
		KTable<Long, LNDCDC_ADS_RTCRD_RATE_CARD_RELEASE> ratecardReleaseTbl = builder
				.table(RATECARD_RELEASE_TOPIC, Consumed.with(SER_DE_LONG, ratecardReleaseSerDe));
		
		// Step 3
		return leftJoinTables(JOIN_NAME.PKG_PRD_VERSION_RELEASE_LJ_RATECARD_RELEASE, pkgPrdVrsnRelTbl, ratecardReleaseTbl);
    }
    
    /*
     * TV Line Assignment Constraint table level
     */
    private static KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> buildTVAssignmentConstraintLevelTable(
    		final StreamsBuilder builder, 
    		final Properties envProps)
    {
    	final String TV_LINE_ASSIGN_CONSTRAINT_TOPIC = envProps.getProperty(PATTERN_TOPIC_NAME + "television_line_assignment_constraint");
        final String TV_ASSIGN_DAY_CONSTRAINT_TOPIC	= envProps.getProperty(PATTERN_TOPIC_NAME + "television_assignment_day_constraint");

        final Serde<UNIFIED_JOIN_FOR_PROPOSAL_LINE> unifiedJoinReadySerDe = getSpecificAvroSerde(envProps);
        final Serde<LNDCDC_ADS_PRPSL_TLVSN_LN_ASGMNT_CNSTRNT> tvAssignmanentConstraintSerDe = getSpecificAvroSerde(envProps);
        
		/*
		select t.tlvsn_ln_asgmnt_cnstrnt_id, t.TELEVISION_PROPOSAL_LINE_ID, t.apply_weekly_aloctn_ind
		from lndcdc_ads_prpsl.TLVSN_LN_ASGMNT_CNSTRNT t
		where tlvsn_ln_asgmnt_cnstrnt_id in (100, 207010, 367711, 368772, 540088);
		*/

		registerStateStore(builder, STATE_STORE_TV_LINE_ASSIGNMENT_CONSTRAINT_JOIN_READY, unifiedJoinReadySerDe);
		
		/*
			select t.tlvsn_ln_asgmnt_cnstrnt_id, t.TELEVISION_PROPOSAL_LINE_ID, t.apply_weekly_aloctn_ind
			from lndcdc_ads_prpsl.TLVSN_LN_ASGMNT_CNSTRNT t
			where tlvsn_ln_asgmnt_cnstrnt_id in (100, 207010, 367711, 368772, 540088);
		*/
        // Step 1
        KTable<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> tvLineAssignmentConstraintTbl =
        builder.stream(TV_LINE_ASSIGN_CONSTRAINT_TOPIC, Consumed.with(SER_DE_LONG, tvAssignmanentConstraintSerDe))
			.transform(new TransformerSupplier<Long, LNDCDC_ADS_PRPSL_TLVSN_LN_ASGMNT_CNSTRNT, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
				@Override
				public Transformer<Long, LNDCDC_ADS_PRPSL_TLVSN_LN_ASGMNT_CNSTRNT, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>> get() {
					return new Transformer<Long, LNDCDC_ADS_PRPSL_TLVSN_LN_ASGMNT_CNSTRNT, KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>>() {
						
						private ProcessorContext processorCtx;
						
						@Override
						public void init(ProcessorContext context) {
							this.processorCtx = context;
						}
	
						@Override
						public KeyValue<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE> transform(Long key, LNDCDC_ADS_PRPSL_TLVSN_LN_ASGMNT_CNSTRNT tvLineAssignmentConstraint) {
							UNIFIED_JOIN_FOR_PROPOSAL_LINE packageProductVersionReleaseJoinReady = UNIFIED_JOIN_FOR_PROPOSAL_LINE.newBuilder()
									.setPROPOSALLINEROWTIME(PLACEHOLDER_LONG)
									.setJOINEDROWTIME(processorCtx.timestamp())
									
									.setTELEVISIONPROPOSALLINEID(tvLineAssignmentConstraint.getTELEVISIONPROPOSALLINEID())
									.setTLVSNLNASGMNTCNSTRNTID(tvLineAssignmentConstraint.getTLVSNLNASGMNTCNSTRNTID())
									.setAPPLYWEEKLYALOCTNIND(tvLineAssignmentConstraint.getAPPLYWEEKLYALOCTNIND())
									
									.build();
							
							return KeyValue.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE>pair(key, packageProductVersionReleaseJoinReady);
						}
	
						@Override
						public void close() {
							// do nothing
						}
					};
				}
			},
			STATE_STORE_TV_LINE_ASSIGNMENT_CONSTRAINT_JOIN_READY + "_stream"
		)
		.toTable(
			Materialized.<Long, UNIFIED_JOIN_FOR_PROPOSAL_LINE, KeyValueStore<Bytes, byte[]>>as(STATE_STORE_TV_LINE_ASSIGNMENT_CONSTRAINT_JOIN_READY + "_table")
			.withKeySerde(SER_DE_LONG)
			.withValueSerde(unifiedJoinReadySerDe)
		);
        
        // Step 2
		/*
			select TLVSN_LN_ASGMNT_CNSTRNT_ID, LISTAGG(t.DAY_OF_WK_NUM, ',') WITHIN GROUP (ORDER BY t.TLVSN_ASGMNT_DAY_CNSTRNT_ID)
			from lndcdc_ads_prpsl.tlvsn_asgmnt_day_cnstrn t
			where TLVSN_LN_ASGMNT_CNSTRNT_ID in (100, 207010, 367711, 368772, 540088)
			group by TLVSN_LN_ASGMNT_CNSTRNT_ID;
		*/
		final Serde<LNDCDC_ADS_PRPSL_TLVSN_ASGMNT_DAY_CNSTRN> tvAssignementDayConstraintSerDe = getSpecificAvroSerde(envProps);
		KStream<Long, LNDCDC_ADS_PRPSL_TLVSN_ASGMNT_DAY_CNSTRN> tvAssignementDayConstraintStream = builder
				.stream(TV_ASSIGN_DAY_CONSTRAINT_TOPIC, Consumed.with(SER_DE_LONG, tvAssignementDayConstraintSerDe));
		KTable<Long, TV_ASSIGNMENT_DAYS_AGG> tvAssignmentDays_agg = tvAssignementDayConstraintStream
				.groupBy(new KeyValueMapper<Long, LNDCDC_ADS_PRPSL_TLVSN_ASGMNT_DAY_CNSTRN, Long>() {
					
					@Override
					public Long apply(Long key, LNDCDC_ADS_PRPSL_TLVSN_ASGMNT_DAY_CNSTRN value) {
						return value.getTLVSNLNASGMNTCNSTRNTID();
					}
					
				}, Grouped.with(SER_DE_LONG, tvAssignementDayConstraintSerDe))
				.aggregate(new Initializer<TV_ASSIGNMENT_DAYS_AGG>() {
					
					@Override
					public TV_ASSIGNMENT_DAYS_AGG apply() {
						TV_ASSIGNMENT_DAYS_AGG initialAgg = TV_ASSIGNMENT_DAYS_AGG.newBuilder()
								.setMONIND(INDICATOR_N)
								.setTUEIND(INDICATOR_N)
								.setWEDIND(INDICATOR_N)
								.setTHUIND(INDICATOR_N)
								.setFRIIND(INDICATOR_N)
								.setSATIND(INDICATOR_N)
								.setSUNIND(INDICATOR_N)
								.setTotalDaysSet(0)
								.build();
						return initialAgg;
					}
					
				}, new Aggregator<Long, LNDCDC_ADS_PRPSL_TLVSN_ASGMNT_DAY_CNSTRN, TV_ASSIGNMENT_DAYS_AGG>(){
	
	
					@Override
					public TV_ASSIGNMENT_DAYS_AGG apply(Long key, 
							LNDCDC_ADS_PRPSL_TLVSN_ASGMNT_DAY_CNSTRN value,
							TV_ASSIGNMENT_DAYS_AGG aggregate) {
						switch(value.getDAYOFWKNUM().intValue())
						{
							case MON: aggregate.setMONIND(INDICATOR_Y); break;
							case TUE: aggregate.setTUEIND(INDICATOR_Y); break;
							case WED: aggregate.setWEDIND(INDICATOR_Y); break;
							case THU: aggregate.setTHUIND(INDICATOR_Y); break;
							case FRI: aggregate.setFRIIND(INDICATOR_Y); break;
							case SAT: aggregate.setSATIND(INDICATOR_Y); break;
							case SUN: aggregate.setSUNIND(INDICATOR_Y); break;
							
							default: // do nothing;
						}
						
						return aggregate;
					}
					
				},
				Named.as("day_indicator"),
				Materialized.<Long, TV_ASSIGNMENT_DAYS_AGG, KeyValueStore<Bytes,byte[]>>as("tv_line_assingment_day_constraint-agg")
				);
		
		// Step 3
		return leftJoinTables(JOIN_NAME.TV_LINE_ASSIGNMENT_CONSTRAINT_LJ_TV_ASSIGNMENT_DAY_CONSTRAINT, tvLineAssignmentConstraintTbl, tvAssignmentDays_agg);
    }

}