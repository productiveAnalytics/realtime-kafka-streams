/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class LNDCDC_ADS_RTCRD_FLIGHT_YEAR extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6823987037122675462L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"LNDCDC_ADS_RTCRD_FLIGHT_YEAR\",\"namespace\":\"org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi\",\"fields\":[{\"name\":\"FLIGHT_RANGE_ID\",\"type\":[\"null\",\"long\"]},{\"name\":\"YEAR_NUM\",\"type\":[\"null\",\"double\"]},{\"name\":\"CALENDAR_ID\",\"type\":[\"null\",\"long\"]},{\"name\":\"LAST_MODIFIED_BY\",\"type\":[\"null\",\"string\"]},{\"name\":\"LAST_MODIFIED_DT\",\"type\":[\"null\",\"string\"]},{\"name\":\"SRC_KEY_VAL\",\"type\":\"string\"},{\"name\":\"SRC_CDC_OPER_NM\",\"type\":\"string\"},{\"name\":\"SRC_COMMIT_DT_UTC\",\"type\":\"string\"},{\"name\":\"TRG_CRT_DT_PART_UTC\",\"type\":\"string\"},{\"name\":\"SRC_SCHEMA_NM\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR> ENCODER =
      new BinaryMessageEncoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR> DECODER =
      new BinaryMessageDecoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this LNDCDC_ADS_RTCRD_FLIGHT_YEAR to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a LNDCDC_ADS_RTCRD_FLIGHT_YEAR from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a LNDCDC_ADS_RTCRD_FLIGHT_YEAR instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static LNDCDC_ADS_RTCRD_FLIGHT_YEAR fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.Long FLIGHT_RANGE_ID;
   private java.lang.Double YEAR_NUM;
   private java.lang.Long CALENDAR_ID;
   private java.lang.CharSequence LAST_MODIFIED_BY;
   private java.lang.CharSequence LAST_MODIFIED_DT;
   private java.lang.CharSequence SRC_KEY_VAL;
   private java.lang.CharSequence SRC_CDC_OPER_NM;
   private java.lang.CharSequence SRC_COMMIT_DT_UTC;
   private java.lang.CharSequence TRG_CRT_DT_PART_UTC;
   private java.lang.CharSequence SRC_SCHEMA_NM;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public LNDCDC_ADS_RTCRD_FLIGHT_YEAR() {}

  /**
   * All-args constructor.
   * @param FLIGHT_RANGE_ID The new value for FLIGHT_RANGE_ID
   * @param YEAR_NUM The new value for YEAR_NUM
   * @param CALENDAR_ID The new value for CALENDAR_ID
   * @param LAST_MODIFIED_BY The new value for LAST_MODIFIED_BY
   * @param LAST_MODIFIED_DT The new value for LAST_MODIFIED_DT
   * @param SRC_KEY_VAL The new value for SRC_KEY_VAL
   * @param SRC_CDC_OPER_NM The new value for SRC_CDC_OPER_NM
   * @param SRC_COMMIT_DT_UTC The new value for SRC_COMMIT_DT_UTC
   * @param TRG_CRT_DT_PART_UTC The new value for TRG_CRT_DT_PART_UTC
   * @param SRC_SCHEMA_NM The new value for SRC_SCHEMA_NM
   */
  public LNDCDC_ADS_RTCRD_FLIGHT_YEAR(java.lang.Long FLIGHT_RANGE_ID, java.lang.Double YEAR_NUM, java.lang.Long CALENDAR_ID, java.lang.CharSequence LAST_MODIFIED_BY, java.lang.CharSequence LAST_MODIFIED_DT, java.lang.CharSequence SRC_KEY_VAL, java.lang.CharSequence SRC_CDC_OPER_NM, java.lang.CharSequence SRC_COMMIT_DT_UTC, java.lang.CharSequence TRG_CRT_DT_PART_UTC, java.lang.CharSequence SRC_SCHEMA_NM) {
    this.FLIGHT_RANGE_ID = FLIGHT_RANGE_ID;
    this.YEAR_NUM = YEAR_NUM;
    this.CALENDAR_ID = CALENDAR_ID;
    this.LAST_MODIFIED_BY = LAST_MODIFIED_BY;
    this.LAST_MODIFIED_DT = LAST_MODIFIED_DT;
    this.SRC_KEY_VAL = SRC_KEY_VAL;
    this.SRC_CDC_OPER_NM = SRC_CDC_OPER_NM;
    this.SRC_COMMIT_DT_UTC = SRC_COMMIT_DT_UTC;
    this.TRG_CRT_DT_PART_UTC = TRG_CRT_DT_PART_UTC;
    this.SRC_SCHEMA_NM = SRC_SCHEMA_NM;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return FLIGHT_RANGE_ID;
    case 1: return YEAR_NUM;
    case 2: return CALENDAR_ID;
    case 3: return LAST_MODIFIED_BY;
    case 4: return LAST_MODIFIED_DT;
    case 5: return SRC_KEY_VAL;
    case 6: return SRC_CDC_OPER_NM;
    case 7: return SRC_COMMIT_DT_UTC;
    case 8: return TRG_CRT_DT_PART_UTC;
    case 9: return SRC_SCHEMA_NM;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: FLIGHT_RANGE_ID = (java.lang.Long)value$; break;
    case 1: YEAR_NUM = (java.lang.Double)value$; break;
    case 2: CALENDAR_ID = (java.lang.Long)value$; break;
    case 3: LAST_MODIFIED_BY = (java.lang.CharSequence)value$; break;
    case 4: LAST_MODIFIED_DT = (java.lang.CharSequence)value$; break;
    case 5: SRC_KEY_VAL = (java.lang.CharSequence)value$; break;
    case 6: SRC_CDC_OPER_NM = (java.lang.CharSequence)value$; break;
    case 7: SRC_COMMIT_DT_UTC = (java.lang.CharSequence)value$; break;
    case 8: TRG_CRT_DT_PART_UTC = (java.lang.CharSequence)value$; break;
    case 9: SRC_SCHEMA_NM = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'FLIGHT_RANGE_ID' field.
   * @return The value of the 'FLIGHT_RANGE_ID' field.
   */
  public java.lang.Long getFLIGHTRANGEID() {
    return FLIGHT_RANGE_ID;
  }


  /**
   * Sets the value of the 'FLIGHT_RANGE_ID' field.
   * @param value the value to set.
   */
  public void setFLIGHTRANGEID(java.lang.Long value) {
    this.FLIGHT_RANGE_ID = value;
  }

  /**
   * Gets the value of the 'YEAR_NUM' field.
   * @return The value of the 'YEAR_NUM' field.
   */
  public java.lang.Double getYEARNUM() {
    return YEAR_NUM;
  }


  /**
   * Sets the value of the 'YEAR_NUM' field.
   * @param value the value to set.
   */
  public void setYEARNUM(java.lang.Double value) {
    this.YEAR_NUM = value;
  }

  /**
   * Gets the value of the 'CALENDAR_ID' field.
   * @return The value of the 'CALENDAR_ID' field.
   */
  public java.lang.Long getCALENDARID() {
    return CALENDAR_ID;
  }


  /**
   * Sets the value of the 'CALENDAR_ID' field.
   * @param value the value to set.
   */
  public void setCALENDARID(java.lang.Long value) {
    this.CALENDAR_ID = value;
  }

  /**
   * Gets the value of the 'LAST_MODIFIED_BY' field.
   * @return The value of the 'LAST_MODIFIED_BY' field.
   */
  public java.lang.CharSequence getLASTMODIFIEDBY() {
    return LAST_MODIFIED_BY;
  }


  /**
   * Sets the value of the 'LAST_MODIFIED_BY' field.
   * @param value the value to set.
   */
  public void setLASTMODIFIEDBY(java.lang.CharSequence value) {
    this.LAST_MODIFIED_BY = value;
  }

  /**
   * Gets the value of the 'LAST_MODIFIED_DT' field.
   * @return The value of the 'LAST_MODIFIED_DT' field.
   */
  public java.lang.CharSequence getLASTMODIFIEDDT() {
    return LAST_MODIFIED_DT;
  }


  /**
   * Sets the value of the 'LAST_MODIFIED_DT' field.
   * @param value the value to set.
   */
  public void setLASTMODIFIEDDT(java.lang.CharSequence value) {
    this.LAST_MODIFIED_DT = value;
  }

  /**
   * Gets the value of the 'SRC_KEY_VAL' field.
   * @return The value of the 'SRC_KEY_VAL' field.
   */
  public java.lang.CharSequence getSRCKEYVAL() {
    return SRC_KEY_VAL;
  }


  /**
   * Sets the value of the 'SRC_KEY_VAL' field.
   * @param value the value to set.
   */
  public void setSRCKEYVAL(java.lang.CharSequence value) {
    this.SRC_KEY_VAL = value;
  }

  /**
   * Gets the value of the 'SRC_CDC_OPER_NM' field.
   * @return The value of the 'SRC_CDC_OPER_NM' field.
   */
  public java.lang.CharSequence getSRCCDCOPERNM() {
    return SRC_CDC_OPER_NM;
  }


  /**
   * Sets the value of the 'SRC_CDC_OPER_NM' field.
   * @param value the value to set.
   */
  public void setSRCCDCOPERNM(java.lang.CharSequence value) {
    this.SRC_CDC_OPER_NM = value;
  }

  /**
   * Gets the value of the 'SRC_COMMIT_DT_UTC' field.
   * @return The value of the 'SRC_COMMIT_DT_UTC' field.
   */
  public java.lang.CharSequence getSRCCOMMITDTUTC() {
    return SRC_COMMIT_DT_UTC;
  }


  /**
   * Sets the value of the 'SRC_COMMIT_DT_UTC' field.
   * @param value the value to set.
   */
  public void setSRCCOMMITDTUTC(java.lang.CharSequence value) {
    this.SRC_COMMIT_DT_UTC = value;
  }

  /**
   * Gets the value of the 'TRG_CRT_DT_PART_UTC' field.
   * @return The value of the 'TRG_CRT_DT_PART_UTC' field.
   */
  public java.lang.CharSequence getTRGCRTDTPARTUTC() {
    return TRG_CRT_DT_PART_UTC;
  }


  /**
   * Sets the value of the 'TRG_CRT_DT_PART_UTC' field.
   * @param value the value to set.
   */
  public void setTRGCRTDTPARTUTC(java.lang.CharSequence value) {
    this.TRG_CRT_DT_PART_UTC = value;
  }

  /**
   * Gets the value of the 'SRC_SCHEMA_NM' field.
   * @return The value of the 'SRC_SCHEMA_NM' field.
   */
  public java.lang.CharSequence getSRCSCHEMANM() {
    return SRC_SCHEMA_NM;
  }


  /**
   * Sets the value of the 'SRC_SCHEMA_NM' field.
   * @param value the value to set.
   */
  public void setSRCSCHEMANM(java.lang.CharSequence value) {
    this.SRC_SCHEMA_NM = value;
  }

  /**
   * Creates a new LNDCDC_ADS_RTCRD_FLIGHT_YEAR RecordBuilder.
   * @return A new LNDCDC_ADS_RTCRD_FLIGHT_YEAR RecordBuilder
   */
  public static org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder newBuilder() {
    return new org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder();
  }

  /**
   * Creates a new LNDCDC_ADS_RTCRD_FLIGHT_YEAR RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new LNDCDC_ADS_RTCRD_FLIGHT_YEAR RecordBuilder
   */
  public static org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder newBuilder(org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder other) {
    if (other == null) {
      return new org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder();
    } else {
      return new org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder(other);
    }
  }

  /**
   * Creates a new LNDCDC_ADS_RTCRD_FLIGHT_YEAR RecordBuilder by copying an existing LNDCDC_ADS_RTCRD_FLIGHT_YEAR instance.
   * @param other The existing instance to copy.
   * @return A new LNDCDC_ADS_RTCRD_FLIGHT_YEAR RecordBuilder
   */
  public static org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder newBuilder(org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR other) {
    if (other == null) {
      return new org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder();
    } else {
      return new org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder(other);
    }
  }

  /**
   * RecordBuilder for LNDCDC_ADS_RTCRD_FLIGHT_YEAR instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>
    implements org.apache.avro.data.RecordBuilder<LNDCDC_ADS_RTCRD_FLIGHT_YEAR> {

    private java.lang.Long FLIGHT_RANGE_ID;
    private java.lang.Double YEAR_NUM;
    private java.lang.Long CALENDAR_ID;
    private java.lang.CharSequence LAST_MODIFIED_BY;
    private java.lang.CharSequence LAST_MODIFIED_DT;
    private java.lang.CharSequence SRC_KEY_VAL;
    private java.lang.CharSequence SRC_CDC_OPER_NM;
    private java.lang.CharSequence SRC_COMMIT_DT_UTC;
    private java.lang.CharSequence TRG_CRT_DT_PART_UTC;
    private java.lang.CharSequence SRC_SCHEMA_NM;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.FLIGHT_RANGE_ID)) {
        this.FLIGHT_RANGE_ID = data().deepCopy(fields()[0].schema(), other.FLIGHT_RANGE_ID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.YEAR_NUM)) {
        this.YEAR_NUM = data().deepCopy(fields()[1].schema(), other.YEAR_NUM);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.CALENDAR_ID)) {
        this.CALENDAR_ID = data().deepCopy(fields()[2].schema(), other.CALENDAR_ID);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.LAST_MODIFIED_BY)) {
        this.LAST_MODIFIED_BY = data().deepCopy(fields()[3].schema(), other.LAST_MODIFIED_BY);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.LAST_MODIFIED_DT)) {
        this.LAST_MODIFIED_DT = data().deepCopy(fields()[4].schema(), other.LAST_MODIFIED_DT);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.SRC_KEY_VAL)) {
        this.SRC_KEY_VAL = data().deepCopy(fields()[5].schema(), other.SRC_KEY_VAL);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.SRC_CDC_OPER_NM)) {
        this.SRC_CDC_OPER_NM = data().deepCopy(fields()[6].schema(), other.SRC_CDC_OPER_NM);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.SRC_COMMIT_DT_UTC)) {
        this.SRC_COMMIT_DT_UTC = data().deepCopy(fields()[7].schema(), other.SRC_COMMIT_DT_UTC);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (isValidValue(fields()[8], other.TRG_CRT_DT_PART_UTC)) {
        this.TRG_CRT_DT_PART_UTC = data().deepCopy(fields()[8].schema(), other.TRG_CRT_DT_PART_UTC);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
      if (isValidValue(fields()[9], other.SRC_SCHEMA_NM)) {
        this.SRC_SCHEMA_NM = data().deepCopy(fields()[9].schema(), other.SRC_SCHEMA_NM);
        fieldSetFlags()[9] = other.fieldSetFlags()[9];
      }
    }

    /**
     * Creates a Builder by copying an existing LNDCDC_ADS_RTCRD_FLIGHT_YEAR instance
     * @param other The existing instance to copy.
     */
    private Builder(org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.FLIGHT_RANGE_ID)) {
        this.FLIGHT_RANGE_ID = data().deepCopy(fields()[0].schema(), other.FLIGHT_RANGE_ID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.YEAR_NUM)) {
        this.YEAR_NUM = data().deepCopy(fields()[1].schema(), other.YEAR_NUM);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.CALENDAR_ID)) {
        this.CALENDAR_ID = data().deepCopy(fields()[2].schema(), other.CALENDAR_ID);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.LAST_MODIFIED_BY)) {
        this.LAST_MODIFIED_BY = data().deepCopy(fields()[3].schema(), other.LAST_MODIFIED_BY);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.LAST_MODIFIED_DT)) {
        this.LAST_MODIFIED_DT = data().deepCopy(fields()[4].schema(), other.LAST_MODIFIED_DT);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.SRC_KEY_VAL)) {
        this.SRC_KEY_VAL = data().deepCopy(fields()[5].schema(), other.SRC_KEY_VAL);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.SRC_CDC_OPER_NM)) {
        this.SRC_CDC_OPER_NM = data().deepCopy(fields()[6].schema(), other.SRC_CDC_OPER_NM);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.SRC_COMMIT_DT_UTC)) {
        this.SRC_COMMIT_DT_UTC = data().deepCopy(fields()[7].schema(), other.SRC_COMMIT_DT_UTC);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.TRG_CRT_DT_PART_UTC)) {
        this.TRG_CRT_DT_PART_UTC = data().deepCopy(fields()[8].schema(), other.TRG_CRT_DT_PART_UTC);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.SRC_SCHEMA_NM)) {
        this.SRC_SCHEMA_NM = data().deepCopy(fields()[9].schema(), other.SRC_SCHEMA_NM);
        fieldSetFlags()[9] = true;
      }
    }

    /**
      * Gets the value of the 'FLIGHT_RANGE_ID' field.
      * @return The value.
      */
    public java.lang.Long getFLIGHTRANGEID() {
      return FLIGHT_RANGE_ID;
    }


    /**
      * Sets the value of the 'FLIGHT_RANGE_ID' field.
      * @param value The value of 'FLIGHT_RANGE_ID'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setFLIGHTRANGEID(java.lang.Long value) {
      validate(fields()[0], value);
      this.FLIGHT_RANGE_ID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'FLIGHT_RANGE_ID' field has been set.
      * @return True if the 'FLIGHT_RANGE_ID' field has been set, false otherwise.
      */
    public boolean hasFLIGHTRANGEID() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'FLIGHT_RANGE_ID' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearFLIGHTRANGEID() {
      FLIGHT_RANGE_ID = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'YEAR_NUM' field.
      * @return The value.
      */
    public java.lang.Double getYEARNUM() {
      return YEAR_NUM;
    }


    /**
      * Sets the value of the 'YEAR_NUM' field.
      * @param value The value of 'YEAR_NUM'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setYEARNUM(java.lang.Double value) {
      validate(fields()[1], value);
      this.YEAR_NUM = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'YEAR_NUM' field has been set.
      * @return True if the 'YEAR_NUM' field has been set, false otherwise.
      */
    public boolean hasYEARNUM() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'YEAR_NUM' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearYEARNUM() {
      YEAR_NUM = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'CALENDAR_ID' field.
      * @return The value.
      */
    public java.lang.Long getCALENDARID() {
      return CALENDAR_ID;
    }


    /**
      * Sets the value of the 'CALENDAR_ID' field.
      * @param value The value of 'CALENDAR_ID'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setCALENDARID(java.lang.Long value) {
      validate(fields()[2], value);
      this.CALENDAR_ID = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'CALENDAR_ID' field has been set.
      * @return True if the 'CALENDAR_ID' field has been set, false otherwise.
      */
    public boolean hasCALENDARID() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'CALENDAR_ID' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearCALENDARID() {
      CALENDAR_ID = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'LAST_MODIFIED_BY' field.
      * @return The value.
      */
    public java.lang.CharSequence getLASTMODIFIEDBY() {
      return LAST_MODIFIED_BY;
    }


    /**
      * Sets the value of the 'LAST_MODIFIED_BY' field.
      * @param value The value of 'LAST_MODIFIED_BY'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setLASTMODIFIEDBY(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.LAST_MODIFIED_BY = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'LAST_MODIFIED_BY' field has been set.
      * @return True if the 'LAST_MODIFIED_BY' field has been set, false otherwise.
      */
    public boolean hasLASTMODIFIEDBY() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'LAST_MODIFIED_BY' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearLASTMODIFIEDBY() {
      LAST_MODIFIED_BY = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'LAST_MODIFIED_DT' field.
      * @return The value.
      */
    public java.lang.CharSequence getLASTMODIFIEDDT() {
      return LAST_MODIFIED_DT;
    }


    /**
      * Sets the value of the 'LAST_MODIFIED_DT' field.
      * @param value The value of 'LAST_MODIFIED_DT'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setLASTMODIFIEDDT(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.LAST_MODIFIED_DT = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'LAST_MODIFIED_DT' field has been set.
      * @return True if the 'LAST_MODIFIED_DT' field has been set, false otherwise.
      */
    public boolean hasLASTMODIFIEDDT() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'LAST_MODIFIED_DT' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearLASTMODIFIEDDT() {
      LAST_MODIFIED_DT = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'SRC_KEY_VAL' field.
      * @return The value.
      */
    public java.lang.CharSequence getSRCKEYVAL() {
      return SRC_KEY_VAL;
    }


    /**
      * Sets the value of the 'SRC_KEY_VAL' field.
      * @param value The value of 'SRC_KEY_VAL'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setSRCKEYVAL(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.SRC_KEY_VAL = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_KEY_VAL' field has been set.
      * @return True if the 'SRC_KEY_VAL' field has been set, false otherwise.
      */
    public boolean hasSRCKEYVAL() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'SRC_KEY_VAL' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearSRCKEYVAL() {
      SRC_KEY_VAL = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'SRC_CDC_OPER_NM' field.
      * @return The value.
      */
    public java.lang.CharSequence getSRCCDCOPERNM() {
      return SRC_CDC_OPER_NM;
    }


    /**
      * Sets the value of the 'SRC_CDC_OPER_NM' field.
      * @param value The value of 'SRC_CDC_OPER_NM'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setSRCCDCOPERNM(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.SRC_CDC_OPER_NM = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_CDC_OPER_NM' field has been set.
      * @return True if the 'SRC_CDC_OPER_NM' field has been set, false otherwise.
      */
    public boolean hasSRCCDCOPERNM() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'SRC_CDC_OPER_NM' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearSRCCDCOPERNM() {
      SRC_CDC_OPER_NM = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'SRC_COMMIT_DT_UTC' field.
      * @return The value.
      */
    public java.lang.CharSequence getSRCCOMMITDTUTC() {
      return SRC_COMMIT_DT_UTC;
    }


    /**
      * Sets the value of the 'SRC_COMMIT_DT_UTC' field.
      * @param value The value of 'SRC_COMMIT_DT_UTC'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setSRCCOMMITDTUTC(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.SRC_COMMIT_DT_UTC = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_COMMIT_DT_UTC' field has been set.
      * @return True if the 'SRC_COMMIT_DT_UTC' field has been set, false otherwise.
      */
    public boolean hasSRCCOMMITDTUTC() {
      return fieldSetFlags()[7];
    }


    /**
      * Clears the value of the 'SRC_COMMIT_DT_UTC' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearSRCCOMMITDTUTC() {
      SRC_COMMIT_DT_UTC = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'TRG_CRT_DT_PART_UTC' field.
      * @return The value.
      */
    public java.lang.CharSequence getTRGCRTDTPARTUTC() {
      return TRG_CRT_DT_PART_UTC;
    }


    /**
      * Sets the value of the 'TRG_CRT_DT_PART_UTC' field.
      * @param value The value of 'TRG_CRT_DT_PART_UTC'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setTRGCRTDTPARTUTC(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.TRG_CRT_DT_PART_UTC = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'TRG_CRT_DT_PART_UTC' field has been set.
      * @return True if the 'TRG_CRT_DT_PART_UTC' field has been set, false otherwise.
      */
    public boolean hasTRGCRTDTPARTUTC() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'TRG_CRT_DT_PART_UTC' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearTRGCRTDTPARTUTC() {
      TRG_CRT_DT_PART_UTC = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'SRC_SCHEMA_NM' field.
      * @return The value.
      */
    public java.lang.CharSequence getSRCSCHEMANM() {
      return SRC_SCHEMA_NM;
    }


    /**
      * Sets the value of the 'SRC_SCHEMA_NM' field.
      * @param value The value of 'SRC_SCHEMA_NM'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder setSRCSCHEMANM(java.lang.CharSequence value) {
      validate(fields()[9], value);
      this.SRC_SCHEMA_NM = value;
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_SCHEMA_NM' field has been set.
      * @return True if the 'SRC_SCHEMA_NM' field has been set, false otherwise.
      */
    public boolean hasSRCSCHEMANM() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'SRC_SCHEMA_NM' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_RTCRD.FLIGHT_YEAR.apache.nifi.LNDCDC_ADS_RTCRD_FLIGHT_YEAR.Builder clearSRCSCHEMANM() {
      SRC_SCHEMA_NM = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LNDCDC_ADS_RTCRD_FLIGHT_YEAR build() {
      try {
        LNDCDC_ADS_RTCRD_FLIGHT_YEAR record = new LNDCDC_ADS_RTCRD_FLIGHT_YEAR();
        record.FLIGHT_RANGE_ID = fieldSetFlags()[0] ? this.FLIGHT_RANGE_ID : (java.lang.Long) defaultValue(fields()[0]);
        record.YEAR_NUM = fieldSetFlags()[1] ? this.YEAR_NUM : (java.lang.Double) defaultValue(fields()[1]);
        record.CALENDAR_ID = fieldSetFlags()[2] ? this.CALENDAR_ID : (java.lang.Long) defaultValue(fields()[2]);
        record.LAST_MODIFIED_BY = fieldSetFlags()[3] ? this.LAST_MODIFIED_BY : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.LAST_MODIFIED_DT = fieldSetFlags()[4] ? this.LAST_MODIFIED_DT : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.SRC_KEY_VAL = fieldSetFlags()[5] ? this.SRC_KEY_VAL : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.SRC_CDC_OPER_NM = fieldSetFlags()[6] ? this.SRC_CDC_OPER_NM : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.SRC_COMMIT_DT_UTC = fieldSetFlags()[7] ? this.SRC_COMMIT_DT_UTC : (java.lang.CharSequence) defaultValue(fields()[7]);
        record.TRG_CRT_DT_PART_UTC = fieldSetFlags()[8] ? this.TRG_CRT_DT_PART_UTC : (java.lang.CharSequence) defaultValue(fields()[8]);
        record.SRC_SCHEMA_NM = fieldSetFlags()[9] ? this.SRC_SCHEMA_NM : (java.lang.CharSequence) defaultValue(fields()[9]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>
    WRITER$ = (org.apache.avro.io.DatumWriter<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>
    READER$ = (org.apache.avro.io.DatumReader<LNDCDC_ADS_RTCRD_FLIGHT_YEAR>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.FLIGHT_RANGE_ID == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.FLIGHT_RANGE_ID);
    }

    if (this.YEAR_NUM == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeDouble(this.YEAR_NUM);
    }

    if (this.CALENDAR_ID == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.CALENDAR_ID);
    }

    if (this.LAST_MODIFIED_BY == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.LAST_MODIFIED_BY);
    }

    if (this.LAST_MODIFIED_DT == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.LAST_MODIFIED_DT);
    }

    out.writeString(this.SRC_KEY_VAL);

    out.writeString(this.SRC_CDC_OPER_NM);

    out.writeString(this.SRC_COMMIT_DT_UTC);

    out.writeString(this.TRG_CRT_DT_PART_UTC);

    out.writeString(this.SRC_SCHEMA_NM);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      if (in.readIndex() != 1) {
        in.readNull();
        this.FLIGHT_RANGE_ID = null;
      } else {
        this.FLIGHT_RANGE_ID = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.YEAR_NUM = null;
      } else {
        this.YEAR_NUM = in.readDouble();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.CALENDAR_ID = null;
      } else {
        this.CALENDAR_ID = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.LAST_MODIFIED_BY = null;
      } else {
        this.LAST_MODIFIED_BY = in.readString(this.LAST_MODIFIED_BY instanceof Utf8 ? (Utf8)this.LAST_MODIFIED_BY : null);
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.LAST_MODIFIED_DT = null;
      } else {
        this.LAST_MODIFIED_DT = in.readString(this.LAST_MODIFIED_DT instanceof Utf8 ? (Utf8)this.LAST_MODIFIED_DT : null);
      }

      this.SRC_KEY_VAL = in.readString(this.SRC_KEY_VAL instanceof Utf8 ? (Utf8)this.SRC_KEY_VAL : null);

      this.SRC_CDC_OPER_NM = in.readString(this.SRC_CDC_OPER_NM instanceof Utf8 ? (Utf8)this.SRC_CDC_OPER_NM : null);

      this.SRC_COMMIT_DT_UTC = in.readString(this.SRC_COMMIT_DT_UTC instanceof Utf8 ? (Utf8)this.SRC_COMMIT_DT_UTC : null);

      this.TRG_CRT_DT_PART_UTC = in.readString(this.TRG_CRT_DT_PART_UTC instanceof Utf8 ? (Utf8)this.TRG_CRT_DT_PART_UTC : null);

      this.SRC_SCHEMA_NM = in.readString(this.SRC_SCHEMA_NM instanceof Utf8 ? (Utf8)this.SRC_SCHEMA_NM : null);

    } else {
      for (int i = 0; i < 10; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.FLIGHT_RANGE_ID = null;
          } else {
            this.FLIGHT_RANGE_ID = in.readLong();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.YEAR_NUM = null;
          } else {
            this.YEAR_NUM = in.readDouble();
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.CALENDAR_ID = null;
          } else {
            this.CALENDAR_ID = in.readLong();
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.LAST_MODIFIED_BY = null;
          } else {
            this.LAST_MODIFIED_BY = in.readString(this.LAST_MODIFIED_BY instanceof Utf8 ? (Utf8)this.LAST_MODIFIED_BY : null);
          }
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.LAST_MODIFIED_DT = null;
          } else {
            this.LAST_MODIFIED_DT = in.readString(this.LAST_MODIFIED_DT instanceof Utf8 ? (Utf8)this.LAST_MODIFIED_DT : null);
          }
          break;

        case 5:
          this.SRC_KEY_VAL = in.readString(this.SRC_KEY_VAL instanceof Utf8 ? (Utf8)this.SRC_KEY_VAL : null);
          break;

        case 6:
          this.SRC_CDC_OPER_NM = in.readString(this.SRC_CDC_OPER_NM instanceof Utf8 ? (Utf8)this.SRC_CDC_OPER_NM : null);
          break;

        case 7:
          this.SRC_COMMIT_DT_UTC = in.readString(this.SRC_COMMIT_DT_UTC instanceof Utf8 ? (Utf8)this.SRC_COMMIT_DT_UTC : null);
          break;

        case 8:
          this.TRG_CRT_DT_PART_UTC = in.readString(this.TRG_CRT_DT_PART_UTC instanceof Utf8 ? (Utf8)this.TRG_CRT_DT_PART_UTC : null);
          break;

        case 9:
          this.SRC_SCHEMA_NM = in.readString(this.SRC_SCHEMA_NM instanceof Utf8 ? (Utf8)this.SRC_SCHEMA_NM : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










