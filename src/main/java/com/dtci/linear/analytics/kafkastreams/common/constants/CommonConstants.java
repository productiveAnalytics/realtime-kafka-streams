package com.dtci.linear.analytics.kafkastreams.common.constants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonConstants {
	
	public static final Long ADVISOR_ID = 1001L;
	
	public static final String TM_ZN_KEY_DEFAULT = "SK_100_PK_ET";
	public static final String PATTERN_DATETIMESTAMP__MILLISECONDS_PRECISION 	= "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static final ZoneId TM_ZN_ET = ZoneId.of("America/New_York");
	public static final ZoneId TM_ZN_UTC = ZoneId.of("UTC");
	
	public static final DateTimeFormatter FORMATTER_DATETIMESTAMP__MILLISECONDS_PRECISION = DateTimeFormatter.ofPattern(PATTERN_DATETIMESTAMP__MILLISECONDS_PRECISION);

	public static final Map<String, String> SCHEMA_2_BI_SRC_SYS_KEY_MAP;
	
	static {
		SCHEMA_2_BI_SRC_SYS_KEY_MAP = new HashMap<String, String>();
		
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("ADS_COMMON", 	"100");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("ADS_CUSTFILE", "101");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("TCS", 			"102");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("ADS_PRPSL", 	"103");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("ADS_RTCRD", 	"104");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("ADS_DLMGMT", 	"105");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("ADS_ORDER", 	"106");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("ADS_UNIT", 	"107");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("NCSAR", 		"108");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("LNDCDC_STG", 	"109");
		SCHEMA_2_BI_SRC_SYS_KEY_MAP.put("PPS", 			"110");
	}
	
	private CommonConstants() {
		// block instantiation
	}
	
}
