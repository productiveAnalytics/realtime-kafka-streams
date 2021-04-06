/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class LNDCDC_ADS_INVNTRY_UNIT_LENGTH extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -840529187008646693L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"LNDCDC_ADS_INVNTRY_UNIT_LENGTH\",\"namespace\":\"org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi\",\"fields\":[{\"name\":\"UNIT_LENGTH_ID\",\"type\":[\"null\",\"long\"]},{\"name\":\"LAST_MODIFIED_BY\",\"type\":[\"null\",\"string\"]},{\"name\":\"LAST_MODIFIED_DT\",\"type\":[\"null\",\"string\"]},{\"name\":\"UNIT_LENGTH_QTY\",\"type\":[\"null\",\"long\"]},{\"name\":\"CNCRNCY_VRSN\",\"type\":[\"null\",\"long\"]},{\"name\":\"SRC_KEY_VAL\",\"type\":\"string\"},{\"name\":\"SRC_CDC_OPER_NM\",\"type\":\"string\"},{\"name\":\"SRC_COMMIT_DT_UTC\",\"type\":\"string\"},{\"name\":\"TRG_CRT_DT_PART_UTC\",\"type\":\"string\"},{\"name\":\"SRC_SCHEMA_NM\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> ENCODER =
      new BinaryMessageEncoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> DECODER =
      new BinaryMessageDecoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this LNDCDC_ADS_INVNTRY_UNIT_LENGTH to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a LNDCDC_ADS_INVNTRY_UNIT_LENGTH from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a LNDCDC_ADS_INVNTRY_UNIT_LENGTH instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static LNDCDC_ADS_INVNTRY_UNIT_LENGTH fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.Long UNIT_LENGTH_ID;
   private java.lang.CharSequence LAST_MODIFIED_BY;
   private java.lang.CharSequence LAST_MODIFIED_DT;
   private java.lang.Long UNIT_LENGTH_QTY;
   private java.lang.Long CNCRNCY_VRSN;
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
  public LNDCDC_ADS_INVNTRY_UNIT_LENGTH() {}

  /**
   * All-args constructor.
   * @param UNIT_LENGTH_ID The new value for UNIT_LENGTH_ID
   * @param LAST_MODIFIED_BY The new value for LAST_MODIFIED_BY
   * @param LAST_MODIFIED_DT The new value for LAST_MODIFIED_DT
   * @param UNIT_LENGTH_QTY The new value for UNIT_LENGTH_QTY
   * @param CNCRNCY_VRSN The new value for CNCRNCY_VRSN
   * @param SRC_KEY_VAL The new value for SRC_KEY_VAL
   * @param SRC_CDC_OPER_NM The new value for SRC_CDC_OPER_NM
   * @param SRC_COMMIT_DT_UTC The new value for SRC_COMMIT_DT_UTC
   * @param TRG_CRT_DT_PART_UTC The new value for TRG_CRT_DT_PART_UTC
   * @param SRC_SCHEMA_NM The new value for SRC_SCHEMA_NM
   */
  public LNDCDC_ADS_INVNTRY_UNIT_LENGTH(java.lang.Long UNIT_LENGTH_ID, java.lang.CharSequence LAST_MODIFIED_BY, java.lang.CharSequence LAST_MODIFIED_DT, java.lang.Long UNIT_LENGTH_QTY, java.lang.Long CNCRNCY_VRSN, java.lang.CharSequence SRC_KEY_VAL, java.lang.CharSequence SRC_CDC_OPER_NM, java.lang.CharSequence SRC_COMMIT_DT_UTC, java.lang.CharSequence TRG_CRT_DT_PART_UTC, java.lang.CharSequence SRC_SCHEMA_NM) {
    this.UNIT_LENGTH_ID = UNIT_LENGTH_ID;
    this.LAST_MODIFIED_BY = LAST_MODIFIED_BY;
    this.LAST_MODIFIED_DT = LAST_MODIFIED_DT;
    this.UNIT_LENGTH_QTY = UNIT_LENGTH_QTY;
    this.CNCRNCY_VRSN = CNCRNCY_VRSN;
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
    case 0: return UNIT_LENGTH_ID;
    case 1: return LAST_MODIFIED_BY;
    case 2: return LAST_MODIFIED_DT;
    case 3: return UNIT_LENGTH_QTY;
    case 4: return CNCRNCY_VRSN;
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
    case 0: UNIT_LENGTH_ID = (java.lang.Long)value$; break;
    case 1: LAST_MODIFIED_BY = (java.lang.CharSequence)value$; break;
    case 2: LAST_MODIFIED_DT = (java.lang.CharSequence)value$; break;
    case 3: UNIT_LENGTH_QTY = (java.lang.Long)value$; break;
    case 4: CNCRNCY_VRSN = (java.lang.Long)value$; break;
    case 5: SRC_KEY_VAL = (java.lang.CharSequence)value$; break;
    case 6: SRC_CDC_OPER_NM = (java.lang.CharSequence)value$; break;
    case 7: SRC_COMMIT_DT_UTC = (java.lang.CharSequence)value$; break;
    case 8: TRG_CRT_DT_PART_UTC = (java.lang.CharSequence)value$; break;
    case 9: SRC_SCHEMA_NM = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'UNIT_LENGTH_ID' field.
   * @return The value of the 'UNIT_LENGTH_ID' field.
   */
  public java.lang.Long getUNITLENGTHID() {
    return UNIT_LENGTH_ID;
  }


  /**
   * Sets the value of the 'UNIT_LENGTH_ID' field.
   * @param value the value to set.
   */
  public void setUNITLENGTHID(java.lang.Long value) {
    this.UNIT_LENGTH_ID = value;
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
   * Gets the value of the 'UNIT_LENGTH_QTY' field.
   * @return The value of the 'UNIT_LENGTH_QTY' field.
   */
  public java.lang.Long getUNITLENGTHQTY() {
    return UNIT_LENGTH_QTY;
  }


  /**
   * Sets the value of the 'UNIT_LENGTH_QTY' field.
   * @param value the value to set.
   */
  public void setUNITLENGTHQTY(java.lang.Long value) {
    this.UNIT_LENGTH_QTY = value;
  }

  /**
   * Gets the value of the 'CNCRNCY_VRSN' field.
   * @return The value of the 'CNCRNCY_VRSN' field.
   */
  public java.lang.Long getCNCRNCYVRSN() {
    return CNCRNCY_VRSN;
  }


  /**
   * Sets the value of the 'CNCRNCY_VRSN' field.
   * @param value the value to set.
   */
  public void setCNCRNCYVRSN(java.lang.Long value) {
    this.CNCRNCY_VRSN = value;
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
   * Creates a new LNDCDC_ADS_INVNTRY_UNIT_LENGTH RecordBuilder.
   * @return A new LNDCDC_ADS_INVNTRY_UNIT_LENGTH RecordBuilder
   */
  public static org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder newBuilder() {
    return new org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder();
  }

  /**
   * Creates a new LNDCDC_ADS_INVNTRY_UNIT_LENGTH RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new LNDCDC_ADS_INVNTRY_UNIT_LENGTH RecordBuilder
   */
  public static org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder newBuilder(org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder other) {
    if (other == null) {
      return new org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder();
    } else {
      return new org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder(other);
    }
  }

  /**
   * Creates a new LNDCDC_ADS_INVNTRY_UNIT_LENGTH RecordBuilder by copying an existing LNDCDC_ADS_INVNTRY_UNIT_LENGTH instance.
   * @param other The existing instance to copy.
   * @return A new LNDCDC_ADS_INVNTRY_UNIT_LENGTH RecordBuilder
   */
  public static org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder newBuilder(org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH other) {
    if (other == null) {
      return new org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder();
    } else {
      return new org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder(other);
    }
  }

  /**
   * RecordBuilder for LNDCDC_ADS_INVNTRY_UNIT_LENGTH instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>
    implements org.apache.avro.data.RecordBuilder<LNDCDC_ADS_INVNTRY_UNIT_LENGTH> {

    private java.lang.Long UNIT_LENGTH_ID;
    private java.lang.CharSequence LAST_MODIFIED_BY;
    private java.lang.CharSequence LAST_MODIFIED_DT;
    private java.lang.Long UNIT_LENGTH_QTY;
    private java.lang.Long CNCRNCY_VRSN;
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
    private Builder(org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.UNIT_LENGTH_ID)) {
        this.UNIT_LENGTH_ID = data().deepCopy(fields()[0].schema(), other.UNIT_LENGTH_ID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.LAST_MODIFIED_BY)) {
        this.LAST_MODIFIED_BY = data().deepCopy(fields()[1].schema(), other.LAST_MODIFIED_BY);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.LAST_MODIFIED_DT)) {
        this.LAST_MODIFIED_DT = data().deepCopy(fields()[2].schema(), other.LAST_MODIFIED_DT);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.UNIT_LENGTH_QTY)) {
        this.UNIT_LENGTH_QTY = data().deepCopy(fields()[3].schema(), other.UNIT_LENGTH_QTY);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.CNCRNCY_VRSN)) {
        this.CNCRNCY_VRSN = data().deepCopy(fields()[4].schema(), other.CNCRNCY_VRSN);
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
     * Creates a Builder by copying an existing LNDCDC_ADS_INVNTRY_UNIT_LENGTH instance
     * @param other The existing instance to copy.
     */
    private Builder(org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.UNIT_LENGTH_ID)) {
        this.UNIT_LENGTH_ID = data().deepCopy(fields()[0].schema(), other.UNIT_LENGTH_ID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.LAST_MODIFIED_BY)) {
        this.LAST_MODIFIED_BY = data().deepCopy(fields()[1].schema(), other.LAST_MODIFIED_BY);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.LAST_MODIFIED_DT)) {
        this.LAST_MODIFIED_DT = data().deepCopy(fields()[2].schema(), other.LAST_MODIFIED_DT);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.UNIT_LENGTH_QTY)) {
        this.UNIT_LENGTH_QTY = data().deepCopy(fields()[3].schema(), other.UNIT_LENGTH_QTY);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.CNCRNCY_VRSN)) {
        this.CNCRNCY_VRSN = data().deepCopy(fields()[4].schema(), other.CNCRNCY_VRSN);
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
      * Gets the value of the 'UNIT_LENGTH_ID' field.
      * @return The value.
      */
    public java.lang.Long getUNITLENGTHID() {
      return UNIT_LENGTH_ID;
    }


    /**
      * Sets the value of the 'UNIT_LENGTH_ID' field.
      * @param value The value of 'UNIT_LENGTH_ID'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setUNITLENGTHID(java.lang.Long value) {
      validate(fields()[0], value);
      this.UNIT_LENGTH_ID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'UNIT_LENGTH_ID' field has been set.
      * @return True if the 'UNIT_LENGTH_ID' field has been set, false otherwise.
      */
    public boolean hasUNITLENGTHID() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'UNIT_LENGTH_ID' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearUNITLENGTHID() {
      UNIT_LENGTH_ID = null;
      fieldSetFlags()[0] = false;
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setLASTMODIFIEDBY(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.LAST_MODIFIED_BY = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'LAST_MODIFIED_BY' field has been set.
      * @return True if the 'LAST_MODIFIED_BY' field has been set, false otherwise.
      */
    public boolean hasLASTMODIFIEDBY() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'LAST_MODIFIED_BY' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearLASTMODIFIEDBY() {
      LAST_MODIFIED_BY = null;
      fieldSetFlags()[1] = false;
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setLASTMODIFIEDDT(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.LAST_MODIFIED_DT = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'LAST_MODIFIED_DT' field has been set.
      * @return True if the 'LAST_MODIFIED_DT' field has been set, false otherwise.
      */
    public boolean hasLASTMODIFIEDDT() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'LAST_MODIFIED_DT' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearLASTMODIFIEDDT() {
      LAST_MODIFIED_DT = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'UNIT_LENGTH_QTY' field.
      * @return The value.
      */
    public java.lang.Long getUNITLENGTHQTY() {
      return UNIT_LENGTH_QTY;
    }


    /**
      * Sets the value of the 'UNIT_LENGTH_QTY' field.
      * @param value The value of 'UNIT_LENGTH_QTY'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setUNITLENGTHQTY(java.lang.Long value) {
      validate(fields()[3], value);
      this.UNIT_LENGTH_QTY = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'UNIT_LENGTH_QTY' field has been set.
      * @return True if the 'UNIT_LENGTH_QTY' field has been set, false otherwise.
      */
    public boolean hasUNITLENGTHQTY() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'UNIT_LENGTH_QTY' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearUNITLENGTHQTY() {
      UNIT_LENGTH_QTY = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'CNCRNCY_VRSN' field.
      * @return The value.
      */
    public java.lang.Long getCNCRNCYVRSN() {
      return CNCRNCY_VRSN;
    }


    /**
      * Sets the value of the 'CNCRNCY_VRSN' field.
      * @param value The value of 'CNCRNCY_VRSN'.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setCNCRNCYVRSN(java.lang.Long value) {
      validate(fields()[4], value);
      this.CNCRNCY_VRSN = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'CNCRNCY_VRSN' field has been set.
      * @return True if the 'CNCRNCY_VRSN' field has been set, false otherwise.
      */
    public boolean hasCNCRNCYVRSN() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'CNCRNCY_VRSN' field.
      * @return This builder.
      */
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearCNCRNCYVRSN() {
      CNCRNCY_VRSN = null;
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setSRCKEYVAL(java.lang.CharSequence value) {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearSRCKEYVAL() {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setSRCCDCOPERNM(java.lang.CharSequence value) {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearSRCCDCOPERNM() {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setSRCCOMMITDTUTC(java.lang.CharSequence value) {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearSRCCOMMITDTUTC() {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setTRGCRTDTPARTUTC(java.lang.CharSequence value) {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearTRGCRTDTPARTUTC() {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder setSRCSCHEMANM(java.lang.CharSequence value) {
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
    public org.LNDCDC_ADS_INVNTRY.UNIT_LENGTH.apache.nifi.LNDCDC_ADS_INVNTRY_UNIT_LENGTH.Builder clearSRCSCHEMANM() {
      SRC_SCHEMA_NM = null;
      fieldSetFlags()[9] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LNDCDC_ADS_INVNTRY_UNIT_LENGTH build() {
      try {
        LNDCDC_ADS_INVNTRY_UNIT_LENGTH record = new LNDCDC_ADS_INVNTRY_UNIT_LENGTH();
        record.UNIT_LENGTH_ID = fieldSetFlags()[0] ? this.UNIT_LENGTH_ID : (java.lang.Long) defaultValue(fields()[0]);
        record.LAST_MODIFIED_BY = fieldSetFlags()[1] ? this.LAST_MODIFIED_BY : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.LAST_MODIFIED_DT = fieldSetFlags()[2] ? this.LAST_MODIFIED_DT : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.UNIT_LENGTH_QTY = fieldSetFlags()[3] ? this.UNIT_LENGTH_QTY : (java.lang.Long) defaultValue(fields()[3]);
        record.CNCRNCY_VRSN = fieldSetFlags()[4] ? this.CNCRNCY_VRSN : (java.lang.Long) defaultValue(fields()[4]);
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
  private static final org.apache.avro.io.DatumWriter<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>
    WRITER$ = (org.apache.avro.io.DatumWriter<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>
    READER$ = (org.apache.avro.io.DatumReader<LNDCDC_ADS_INVNTRY_UNIT_LENGTH>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.UNIT_LENGTH_ID == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.UNIT_LENGTH_ID);
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

    if (this.UNIT_LENGTH_QTY == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.UNIT_LENGTH_QTY);
    }

    if (this.CNCRNCY_VRSN == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.CNCRNCY_VRSN);
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
        this.UNIT_LENGTH_ID = null;
      } else {
        this.UNIT_LENGTH_ID = in.readLong();
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

      if (in.readIndex() != 1) {
        in.readNull();
        this.UNIT_LENGTH_QTY = null;
      } else {
        this.UNIT_LENGTH_QTY = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.CNCRNCY_VRSN = null;
      } else {
        this.CNCRNCY_VRSN = in.readLong();
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
            this.UNIT_LENGTH_ID = null;
          } else {
            this.UNIT_LENGTH_ID = in.readLong();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.LAST_MODIFIED_BY = null;
          } else {
            this.LAST_MODIFIED_BY = in.readString(this.LAST_MODIFIED_BY instanceof Utf8 ? (Utf8)this.LAST_MODIFIED_BY : null);
          }
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.LAST_MODIFIED_DT = null;
          } else {
            this.LAST_MODIFIED_DT = in.readString(this.LAST_MODIFIED_DT instanceof Utf8 ? (Utf8)this.LAST_MODIFIED_DT : null);
          }
          break;

        case 3:
          if (in.readIndex() != 1) {
            in.readNull();
            this.UNIT_LENGTH_QTY = null;
          } else {
            this.UNIT_LENGTH_QTY = in.readLong();
          }
          break;

        case 4:
          if (in.readIndex() != 1) {
            in.readNull();
            this.CNCRNCY_VRSN = null;
          } else {
            this.CNCRNCY_VRSN = in.readLong();
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










