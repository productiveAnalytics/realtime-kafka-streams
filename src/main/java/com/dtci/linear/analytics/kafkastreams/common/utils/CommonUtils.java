package com.dtci.linear.analytics.kafkastreams.common.utils;

import static com.dtci.linear.analytics.kafkastreams.common.constants.CommonConstants.SCHEMA_2_BI_SRC_SYS_KEY_MAP;
import static com.dtci.linear.analytics.kafkastreams.common.constants.CommonConstants.FORMATTER_DATETIMESTAMP__MILLISECONDS_PRECISION;
import static com.dtci.linear.analytics.kafkastreams.common.constants.CommonConstants.TM_ZN_UTC;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * 
 * @author chawl001
 */
public final class CommonUtils {
	
	private static final String PATTERN_SK = "SK_%s__PK_%s";

	public static final String getBI_SRC_SYS_KEY(String schemaName) {
		Objects.requireNonNull(schemaName, "schemaName must be valid");
		final String biSrcSysKey = SCHEMA_2_BI_SRC_SYS_KEY_MAP.get(schemaName);
		if (null == biSrcSysKey) {
			System.err.println("[ERROR] Unable to locate schema: "+schemaName);
			throw new RuntimeException("Could not find schema: "+ schemaName);
		} else {
			return biSrcSysKey;
		}
	}
	
	public static final String generateSK(Long entityPrimaryKey, String schemaName) {
		if (null == entityPrimaryKey) {
			return null;
		} else {
			Objects.requireNonNull(schemaName, "schemaName must be valid");
			
			String biSrcSysKey = getBI_SRC_SYS_KEY(schemaName);
			return String.format(PATTERN_SK, biSrcSysKey, entityPrimaryKey.longValue());
		}
	}
	
	/**
	 * 
	 * @param targetTimeZoneId	Pass null to get timestamp in UTC
	 * @return
	 */
	public static final String getCurrentTimestamp(ZoneId targetTimeZoneId) {
		Instant nowUtc = Instant.now();
		final ZoneId targetTZId = (targetTimeZoneId == null) ? TM_ZN_UTC : targetTimeZoneId;
		
		ZonedDateTime zonedDT = ZonedDateTime.ofInstant(nowUtc, targetTZId);
		String timestampStr = zonedDT.format(FORMATTER_DATETIMESTAMP__MILLISECONDS_PRECISION);
		return timestampStr;
	}
	
	public static final String convertTimezone(String inputDateStr, ZoneId sourceTimeZoneId, ZoneId targetTimeZoneId) {
		
		OffsetDateTime odtInstanceAtOffset = OffsetDateTime.parse(inputDateStr, FORMATTER_DATETIMESTAMP__MILLISECONDS_PRECISION);
		 
        // Instance in UTC
        OffsetDateTime odtInstanceAtUTC = odtInstanceAtOffset.withOffsetSameInstant(ZoneOffset.UTC);
         
        String tsInUTC = odtInstanceAtUTC.format(FORMATTER_DATETIMESTAMP__MILLISECONDS_PRECISION);
        
        return tsInUTC;
    }

}
