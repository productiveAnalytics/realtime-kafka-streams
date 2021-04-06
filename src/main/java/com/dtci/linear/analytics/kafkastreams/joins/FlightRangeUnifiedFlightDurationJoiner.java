package com.dtci.linear.analytics.kafkastreams.joins;

import static com.dtci.linear.analytics.kafkastreams.common.constants.CommonConstants.ADVISOR_ID;
import static com.dtci.linear.analytics.kafkastreams.common.constants.CommonConstants.TM_ZN_KEY_DEFAULT;
import static com.dtci.linear.analytics.kafkastreams.common.constants.CommonConstants.TM_ZN_ET;
import static com.dtci.linear.analytics.kafkastreams.common.constants.CommonConstants.TM_ZN_UTC;
import static com.dtci.linear.analytics.kafkastreams.common.utils.CommonUtils.generateSK;
import static com.dtci.linear.analytics.kafkastreams.common.utils.CommonUtils.getCurrentTimestamp;
import static com.dtci.linear.analytics.kafkastreams.common.utils.CommonUtils.convertTimezone;

import org.apache.kafka.streams.kstream.ValueJoiner;
import org.apache.nifi.custom.joined.RTCRD_FLIGHT_RANGE_JOIN_FLIGHT;
import org.apache.nifi.custom.merged.RTCRD_UNIFIED_FLIGHT_DURATION;
import org.apache.nifi.custom.conformed.CONFORMED_CON_COMMON_FLGHT_RNG;

/**
 * flight-range left-join (merged)flight-duration 
 * 	on flight-range.flight_range_id = flight-duration.flight_range_id
 * 
 * @author chawl001
 */
public final class FlightRangeUnifiedFlightDurationJoiner implements ValueJoiner<RTCRD_FLIGHT_RANGE_JOIN_FLIGHT, RTCRD_UNIFIED_FLIGHT_DURATION, CONFORMED_CON_COMMON_FLGHT_RNG> {

	private static final Integer UNAVAILABLE = -1;
	
	private String schema;
	private String biSrcSysKey;
	
	public FlightRangeUnifiedFlightDurationJoiner(String schemaName) {
		this.schema = schemaName;
		
		try {
			this.biSrcSysKey = generateSK(ADVISOR_ID, schemaName);
		} catch (Exception e) {
			throw new ExceptionInInitializerError("Error initializing biSrcSysKey for Schema: "+ schemaName);
		}
	}
	
	@Override
	public CONFORMED_CON_COMMON_FLGHT_RNG apply(
			RTCRD_FLIGHT_RANGE_JOIN_FLIGHT flightRng,
			RTCRD_UNIFIED_FLIGHT_DURATION unifiedFlightDuration) {
		CONFORMED_CON_COMMON_FLGHT_RNG.Builder avroBuilder = CONFORMED_CON_COMMON_FLGHT_RNG.newBuilder();
		
		String sk = null;
		
		
		/*
		 * left side fields
		 */
		sk = generateSK(flightRng.getFLIGHTRANGEID(), this.schema);
		avroBuilder
		.setFLGHTRNGID(flightRng.getFLIGHTRANGEID())
		.setFLGHTRNGKEY(sk);
		
		sk = generateSK(flightRng.getFLIGHTID(), this.schema);
		avroBuilder
		.setFLGHTID(flightRng.getFLIGHTID())
		.setFLGHTKEY(sk);
		
		avroBuilder
		.setVOACLSSTXT(flightRng.getVOACLASS())
		.setFLGHTRNGDESC(flightRng.getFLGHTRNGDESC())
		.setFLGHTRNGSTARTTMSTMP(flightRng.getFLIGHTRANGESTART())
		.setFLGHTRNGENDTMSTMP(flightRng.getFLIGHTRANGEEND());
		
		/*
		 * handle right side of left-join
		 */
		if (unifiedFlightDuration != null) {
			sk = generateSK(unifiedFlightDuration.getCALENDARID(), this.schema);
			
			avroBuilder
			.setFLGHTRNGTYCD(unifiedFlightDuration.getFLGHTRNGTYCD())
			.setCALENDARID(unifiedFlightDuration.getCALENDARID())
			.setCALTYKEY(sk)
			.setYRNUM(convertNum(unifiedFlightDuration.getYEARNUM()))
			.setQTRNUM(convertNum(unifiedFlightDuration.getQUARTERNUM()))
			.setMTHNUM(convertNum(unifiedFlightDuration.getMONTHNUM()))
			.setWKNUM(convertNum(unifiedFlightDuration.getWEEKNUM()))
			.setDAYNUM(convertNum(unifiedFlightDuration.getDAYNUM()));
		} else {
			avroBuilder
			.setFLGHTRNGTYCD("N/A")
			.setCALENDARID(UNAVAILABLE.longValue())
			.setCALTYKEY(null)
			.setYRNUM(UNAVAILABLE)
			.setQTRNUM(UNAVAILABLE)
			.setMTHNUM(UNAVAILABLE)
			.setWKNUM(UNAVAILABLE)
			.setDAYNUM(UNAVAILABLE);
		}

		
		/*
		 *  ABC fields
		 */
		avroBuilder
		.setTMZNKEY(TM_ZN_KEY_DEFAULT)
		.setLASTMODFDBY(flightRng.getLASTMODIFIEDBY())
		.setLASTMODFDDT(flightRng.getLASTMODIFIEDDT())
		.setSRCCOMMITTMSTMP(flightRng.getSRCCOMMITDTUTC())
		.setSRCPUBSHDTMSTMP(flightRng.getTRGCRTDTPARTUTC())
		.setDELETDFRMSRCIND(deriveDeleteInd(flightRng))
		.setBISRCSYSKEY(this.biSrcSysKey)
		.setCNFRMNCUPTDPROCSNM("TODO: ProcessName-for-KAFKA_STREAMS-ConCommon-FlightRange");
		
		String lastModifiedTSInUTC = null;
		try {
			if (flightRng.getLASTMODIFIEDDT() != null) {
				lastModifiedTSInUTC = convertTimezone(flightRng.getLASTMODIFIEDDT().toString(), TM_ZN_ET, TM_ZN_UTC);
			}
		} catch (Exception e) {
			System.err.println("[ERROR] Issue while parsing LAST_MODIFIED_DATE");
			lastModifiedTSInUTC = null;
		}
		if (null == lastModifiedTSInUTC) {
			lastModifiedTSInUTC = getCurrentTimestamp(null);
		}
		avroBuilder.setTRANSSTRTTMSTMP(lastModifiedTSInUTC);
		
		String conformanceTSInUTC = getCurrentTimestamp(null);
		avroBuilder.setCNFRMNCUPTDTMSTMP(conformanceTSInUTC);
		
		return avroBuilder.build();
	}
	
	private static int convertNum(Double d) {
		return (null != d) ? d.intValue() : UNAVAILABLE;
	}

	
	private String deriveDeleteInd(RTCRD_FLIGHT_RANGE_JOIN_FLIGHT flightRng) {
		String delete_ind = "N";
		
		
		if (null != flightRng.getSRCKEYVAL() && "DELETE".equals(flightRng.getSRCCDCOPERNM())) {
			delete_ind = "Y";
		}
		
		return delete_ind;
	}
}
