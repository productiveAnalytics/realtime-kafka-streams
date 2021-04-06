package com.dtci.linear.analytics.kafkastreams.joins;

import org.LNDCDC_ADS_RTCRD.FLIGHT.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT;
import org.LNDCDC_ADS_RTCRD.FLIGHT_RANGE.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_RANGE;
import org.apache.nifi.custom.joined.RTCRD_FLIGHT_RANGE_JOIN_FLIGHT;
import org.LNDCDC_NCS_TCS.FLIGHT_DATES.apache.nifi.LNDCDC_NCS_TCS_FLIGHT_DATES;
import org.apache.kafka.streams.kstream.ValueJoiner;


public class FltRangeFlightJoiner implements ValueJoiner<LNDCDC_ADS_RTCRD_FLIGHT_RANGE, LNDCDC_NCS_TCS_FLIGHT_DATES, RTCRD_FLIGHT_RANGE_JOIN_FLIGHT> {

    public RTCRD_FLIGHT_RANGE_JOIN_FLIGHT apply(LNDCDC_ADS_RTCRD_FLIGHT_RANGE flightRange, LNDCDC_NCS_TCS_FLIGHT_DATES flightDates) {
        RTCRD_FLIGHT_RANGE_JOIN_FLIGHT.Builder avroBuilder = RTCRD_FLIGHT_RANGE_JOIN_FLIGHT.newBuilder()
                .setFLIGHTRANGEID(flightRange.getFLIGHTRANGEID())
                .setFLIGHTRANGESTART(flightRange.getFLIGHTRANGESTART())
                .setFLIGHTRANGEEND(flightRange.getFLIGHTRANGEEND())
                .setFLIGHTID(flightRange.getFLIGHTID())
                .setVOACLASS(flightRange.getVOACLASS())
                .setLASTMODIFIEDBY(flightRange.getLASTMODIFIEDBY())
                .setLASTMODIFIEDDT(flightRange.getLASTMODIFIEDDT());

        // handle right side of left-join
        if (flightDates != null) {
            avroBuilder.setFLGHTRNGDESC(flightDates.getFLTDESC());

        } else {
            avroBuilder.setFLGHTRNGDESC("NONE");
        }

        avroBuilder
                .setSRCKEYVAL(flightRange.getSRCKEYVAL())
                .setSRCCDCOPERNM(flightRange.getSRCCDCOPERNM())
                .setSRCCOMMITDTUTC(flightRange.getSRCCOMMITDTUTC())
                .setTRGCRTDTPARTUTC(flightRange.getTRGCRTDTPARTUTC())
                .setSRCSCHEMANM(flightRange.getSRCSCHEMANM());
        return avroBuilder.build();
    }
}
