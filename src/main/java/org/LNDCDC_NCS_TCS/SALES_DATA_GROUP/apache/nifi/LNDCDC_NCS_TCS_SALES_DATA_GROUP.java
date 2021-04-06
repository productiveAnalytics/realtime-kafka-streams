/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class LNDCDC_NCS_TCS_SALES_DATA_GROUP extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 686267309777085519L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"LNDCDC_NCS_TCS_SALES_DATA_GROUP\",\"namespace\":\"org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi\",\"fields\":[{\"name\":\"DATA_GRP_ID\",\"type\":[\"null\",\"long\"]},{\"name\":\"DATA_GRP_NAME\",\"type\":[\"null\",\"string\"]},{\"name\":\"SRC_KEY_VAL\",\"type\":\"string\"},{\"name\":\"SRC_CDC_OPER_NM\",\"type\":\"string\"},{\"name\":\"SRC_COMMIT_DT_UTC\",\"type\":\"string\"},{\"name\":\"TRG_CRT_DT_PART_UTC\",\"type\":\"string\"},{\"name\":\"SRC_SCHEMA_NM\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP> ENCODER =
      new BinaryMessageEncoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP> DECODER =
      new BinaryMessageDecoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<LNDCDC_NCS_TCS_SALES_DATA_GROUP>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this LNDCDC_NCS_TCS_SALES_DATA_GROUP to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a LNDCDC_NCS_TCS_SALES_DATA_GROUP from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a LNDCDC_NCS_TCS_SALES_DATA_GROUP instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static LNDCDC_NCS_TCS_SALES_DATA_GROUP fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

   private java.lang.Long DATA_GRP_ID;
   private java.lang.CharSequence DATA_GRP_NAME;
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
  public LNDCDC_NCS_TCS_SALES_DATA_GROUP() {}

  /**
   * All-args constructor.
   * @param DATA_GRP_ID The new value for DATA_GRP_ID
   * @param DATA_GRP_NAME The new value for DATA_GRP_NAME
   * @param SRC_KEY_VAL The new value for SRC_KEY_VAL
   * @param SRC_CDC_OPER_NM The new value for SRC_CDC_OPER_NM
   * @param SRC_COMMIT_DT_UTC The new value for SRC_COMMIT_DT_UTC
   * @param TRG_CRT_DT_PART_UTC The new value for TRG_CRT_DT_PART_UTC
   * @param SRC_SCHEMA_NM The new value for SRC_SCHEMA_NM
   */
  public LNDCDC_NCS_TCS_SALES_DATA_GROUP(java.lang.Long DATA_GRP_ID, java.lang.CharSequence DATA_GRP_NAME, java.lang.CharSequence SRC_KEY_VAL, java.lang.CharSequence SRC_CDC_OPER_NM, java.lang.CharSequence SRC_COMMIT_DT_UTC, java.lang.CharSequence TRG_CRT_DT_PART_UTC, java.lang.CharSequence SRC_SCHEMA_NM) {
    this.DATA_GRP_ID = DATA_GRP_ID;
    this.DATA_GRP_NAME = DATA_GRP_NAME;
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
    case 0: return DATA_GRP_ID;
    case 1: return DATA_GRP_NAME;
    case 2: return SRC_KEY_VAL;
    case 3: return SRC_CDC_OPER_NM;
    case 4: return SRC_COMMIT_DT_UTC;
    case 5: return TRG_CRT_DT_PART_UTC;
    case 6: return SRC_SCHEMA_NM;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: DATA_GRP_ID = (java.lang.Long)value$; break;
    case 1: DATA_GRP_NAME = (java.lang.CharSequence)value$; break;
    case 2: SRC_KEY_VAL = (java.lang.CharSequence)value$; break;
    case 3: SRC_CDC_OPER_NM = (java.lang.CharSequence)value$; break;
    case 4: SRC_COMMIT_DT_UTC = (java.lang.CharSequence)value$; break;
    case 5: TRG_CRT_DT_PART_UTC = (java.lang.CharSequence)value$; break;
    case 6: SRC_SCHEMA_NM = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'DATA_GRP_ID' field.
   * @return The value of the 'DATA_GRP_ID' field.
   */
  public java.lang.Long getDATAGRPID() {
    return DATA_GRP_ID;
  }


  /**
   * Sets the value of the 'DATA_GRP_ID' field.
   * @param value the value to set.
   */
  public void setDATAGRPID(java.lang.Long value) {
    this.DATA_GRP_ID = value;
  }

  /**
   * Gets the value of the 'DATA_GRP_NAME' field.
   * @return The value of the 'DATA_GRP_NAME' field.
   */
  public java.lang.CharSequence getDATAGRPNAME() {
    return DATA_GRP_NAME;
  }


  /**
   * Sets the value of the 'DATA_GRP_NAME' field.
   * @param value the value to set.
   */
  public void setDATAGRPNAME(java.lang.CharSequence value) {
    this.DATA_GRP_NAME = value;
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
   * Creates a new LNDCDC_NCS_TCS_SALES_DATA_GROUP RecordBuilder.
   * @return A new LNDCDC_NCS_TCS_SALES_DATA_GROUP RecordBuilder
   */
  public static org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder newBuilder() {
    return new org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder();
  }

  /**
   * Creates a new LNDCDC_NCS_TCS_SALES_DATA_GROUP RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new LNDCDC_NCS_TCS_SALES_DATA_GROUP RecordBuilder
   */
  public static org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder newBuilder(org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder other) {
    if (other == null) {
      return new org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder();
    } else {
      return new org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder(other);
    }
  }

  /**
   * Creates a new LNDCDC_NCS_TCS_SALES_DATA_GROUP RecordBuilder by copying an existing LNDCDC_NCS_TCS_SALES_DATA_GROUP instance.
   * @param other The existing instance to copy.
   * @return A new LNDCDC_NCS_TCS_SALES_DATA_GROUP RecordBuilder
   */
  public static org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder newBuilder(org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP other) {
    if (other == null) {
      return new org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder();
    } else {
      return new org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder(other);
    }
  }

  /**
   * RecordBuilder for LNDCDC_NCS_TCS_SALES_DATA_GROUP instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<LNDCDC_NCS_TCS_SALES_DATA_GROUP>
    implements org.apache.avro.data.RecordBuilder<LNDCDC_NCS_TCS_SALES_DATA_GROUP> {

    private java.lang.Long DATA_GRP_ID;
    private java.lang.CharSequence DATA_GRP_NAME;
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
    private Builder(org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.DATA_GRP_ID)) {
        this.DATA_GRP_ID = data().deepCopy(fields()[0].schema(), other.DATA_GRP_ID);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.DATA_GRP_NAME)) {
        this.DATA_GRP_NAME = data().deepCopy(fields()[1].schema(), other.DATA_GRP_NAME);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.SRC_KEY_VAL)) {
        this.SRC_KEY_VAL = data().deepCopy(fields()[2].schema(), other.SRC_KEY_VAL);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.SRC_CDC_OPER_NM)) {
        this.SRC_CDC_OPER_NM = data().deepCopy(fields()[3].schema(), other.SRC_CDC_OPER_NM);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.SRC_COMMIT_DT_UTC)) {
        this.SRC_COMMIT_DT_UTC = data().deepCopy(fields()[4].schema(), other.SRC_COMMIT_DT_UTC);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.TRG_CRT_DT_PART_UTC)) {
        this.TRG_CRT_DT_PART_UTC = data().deepCopy(fields()[5].schema(), other.TRG_CRT_DT_PART_UTC);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.SRC_SCHEMA_NM)) {
        this.SRC_SCHEMA_NM = data().deepCopy(fields()[6].schema(), other.SRC_SCHEMA_NM);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing LNDCDC_NCS_TCS_SALES_DATA_GROUP instance
     * @param other The existing instance to copy.
     */
    private Builder(org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.DATA_GRP_ID)) {
        this.DATA_GRP_ID = data().deepCopy(fields()[0].schema(), other.DATA_GRP_ID);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.DATA_GRP_NAME)) {
        this.DATA_GRP_NAME = data().deepCopy(fields()[1].schema(), other.DATA_GRP_NAME);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.SRC_KEY_VAL)) {
        this.SRC_KEY_VAL = data().deepCopy(fields()[2].schema(), other.SRC_KEY_VAL);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.SRC_CDC_OPER_NM)) {
        this.SRC_CDC_OPER_NM = data().deepCopy(fields()[3].schema(), other.SRC_CDC_OPER_NM);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.SRC_COMMIT_DT_UTC)) {
        this.SRC_COMMIT_DT_UTC = data().deepCopy(fields()[4].schema(), other.SRC_COMMIT_DT_UTC);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.TRG_CRT_DT_PART_UTC)) {
        this.TRG_CRT_DT_PART_UTC = data().deepCopy(fields()[5].schema(), other.TRG_CRT_DT_PART_UTC);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.SRC_SCHEMA_NM)) {
        this.SRC_SCHEMA_NM = data().deepCopy(fields()[6].schema(), other.SRC_SCHEMA_NM);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'DATA_GRP_ID' field.
      * @return The value.
      */
    public java.lang.Long getDATAGRPID() {
      return DATA_GRP_ID;
    }


    /**
      * Sets the value of the 'DATA_GRP_ID' field.
      * @param value The value of 'DATA_GRP_ID'.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder setDATAGRPID(java.lang.Long value) {
      validate(fields()[0], value);
      this.DATA_GRP_ID = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'DATA_GRP_ID' field has been set.
      * @return True if the 'DATA_GRP_ID' field has been set, false otherwise.
      */
    public boolean hasDATAGRPID() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'DATA_GRP_ID' field.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder clearDATAGRPID() {
      DATA_GRP_ID = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'DATA_GRP_NAME' field.
      * @return The value.
      */
    public java.lang.CharSequence getDATAGRPNAME() {
      return DATA_GRP_NAME;
    }


    /**
      * Sets the value of the 'DATA_GRP_NAME' field.
      * @param value The value of 'DATA_GRP_NAME'.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder setDATAGRPNAME(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.DATA_GRP_NAME = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'DATA_GRP_NAME' field has been set.
      * @return True if the 'DATA_GRP_NAME' field has been set, false otherwise.
      */
    public boolean hasDATAGRPNAME() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'DATA_GRP_NAME' field.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder clearDATAGRPNAME() {
      DATA_GRP_NAME = null;
      fieldSetFlags()[1] = false;
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
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder setSRCKEYVAL(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.SRC_KEY_VAL = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_KEY_VAL' field has been set.
      * @return True if the 'SRC_KEY_VAL' field has been set, false otherwise.
      */
    public boolean hasSRCKEYVAL() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'SRC_KEY_VAL' field.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder clearSRCKEYVAL() {
      SRC_KEY_VAL = null;
      fieldSetFlags()[2] = false;
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
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder setSRCCDCOPERNM(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.SRC_CDC_OPER_NM = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_CDC_OPER_NM' field has been set.
      * @return True if the 'SRC_CDC_OPER_NM' field has been set, false otherwise.
      */
    public boolean hasSRCCDCOPERNM() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'SRC_CDC_OPER_NM' field.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder clearSRCCDCOPERNM() {
      SRC_CDC_OPER_NM = null;
      fieldSetFlags()[3] = false;
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
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder setSRCCOMMITDTUTC(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.SRC_COMMIT_DT_UTC = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_COMMIT_DT_UTC' field has been set.
      * @return True if the 'SRC_COMMIT_DT_UTC' field has been set, false otherwise.
      */
    public boolean hasSRCCOMMITDTUTC() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'SRC_COMMIT_DT_UTC' field.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder clearSRCCOMMITDTUTC() {
      SRC_COMMIT_DT_UTC = null;
      fieldSetFlags()[4] = false;
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
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder setTRGCRTDTPARTUTC(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.TRG_CRT_DT_PART_UTC = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'TRG_CRT_DT_PART_UTC' field has been set.
      * @return True if the 'TRG_CRT_DT_PART_UTC' field has been set, false otherwise.
      */
    public boolean hasTRGCRTDTPARTUTC() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'TRG_CRT_DT_PART_UTC' field.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder clearTRGCRTDTPARTUTC() {
      TRG_CRT_DT_PART_UTC = null;
      fieldSetFlags()[5] = false;
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
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder setSRCSCHEMANM(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.SRC_SCHEMA_NM = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'SRC_SCHEMA_NM' field has been set.
      * @return True if the 'SRC_SCHEMA_NM' field has been set, false otherwise.
      */
    public boolean hasSRCSCHEMANM() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'SRC_SCHEMA_NM' field.
      * @return This builder.
      */
    public org.LNDCDC_NCS_TCS.SALES_DATA_GROUP.apache.nifi.LNDCDC_NCS_TCS_SALES_DATA_GROUP.Builder clearSRCSCHEMANM() {
      SRC_SCHEMA_NM = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public LNDCDC_NCS_TCS_SALES_DATA_GROUP build() {
      try {
        LNDCDC_NCS_TCS_SALES_DATA_GROUP record = new LNDCDC_NCS_TCS_SALES_DATA_GROUP();
        record.DATA_GRP_ID = fieldSetFlags()[0] ? this.DATA_GRP_ID : (java.lang.Long) defaultValue(fields()[0]);
        record.DATA_GRP_NAME = fieldSetFlags()[1] ? this.DATA_GRP_NAME : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.SRC_KEY_VAL = fieldSetFlags()[2] ? this.SRC_KEY_VAL : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.SRC_CDC_OPER_NM = fieldSetFlags()[3] ? this.SRC_CDC_OPER_NM : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.SRC_COMMIT_DT_UTC = fieldSetFlags()[4] ? this.SRC_COMMIT_DT_UTC : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.TRG_CRT_DT_PART_UTC = fieldSetFlags()[5] ? this.TRG_CRT_DT_PART_UTC : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.SRC_SCHEMA_NM = fieldSetFlags()[6] ? this.SRC_SCHEMA_NM : (java.lang.CharSequence) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<LNDCDC_NCS_TCS_SALES_DATA_GROUP>
    WRITER$ = (org.apache.avro.io.DatumWriter<LNDCDC_NCS_TCS_SALES_DATA_GROUP>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<LNDCDC_NCS_TCS_SALES_DATA_GROUP>
    READER$ = (org.apache.avro.io.DatumReader<LNDCDC_NCS_TCS_SALES_DATA_GROUP>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    if (this.DATA_GRP_ID == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.DATA_GRP_ID);
    }

    if (this.DATA_GRP_NAME == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeString(this.DATA_GRP_NAME);
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
        this.DATA_GRP_ID = null;
      } else {
        this.DATA_GRP_ID = in.readLong();
      }

      if (in.readIndex() != 1) {
        in.readNull();
        this.DATA_GRP_NAME = null;
      } else {
        this.DATA_GRP_NAME = in.readString(this.DATA_GRP_NAME instanceof Utf8 ? (Utf8)this.DATA_GRP_NAME : null);
      }

      this.SRC_KEY_VAL = in.readString(this.SRC_KEY_VAL instanceof Utf8 ? (Utf8)this.SRC_KEY_VAL : null);

      this.SRC_CDC_OPER_NM = in.readString(this.SRC_CDC_OPER_NM instanceof Utf8 ? (Utf8)this.SRC_CDC_OPER_NM : null);

      this.SRC_COMMIT_DT_UTC = in.readString(this.SRC_COMMIT_DT_UTC instanceof Utf8 ? (Utf8)this.SRC_COMMIT_DT_UTC : null);

      this.TRG_CRT_DT_PART_UTC = in.readString(this.TRG_CRT_DT_PART_UTC instanceof Utf8 ? (Utf8)this.TRG_CRT_DT_PART_UTC : null);

      this.SRC_SCHEMA_NM = in.readString(this.SRC_SCHEMA_NM instanceof Utf8 ? (Utf8)this.SRC_SCHEMA_NM : null);

    } else {
      for (int i = 0; i < 7; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          if (in.readIndex() != 1) {
            in.readNull();
            this.DATA_GRP_ID = null;
          } else {
            this.DATA_GRP_ID = in.readLong();
          }
          break;

        case 1:
          if (in.readIndex() != 1) {
            in.readNull();
            this.DATA_GRP_NAME = null;
          } else {
            this.DATA_GRP_NAME = in.readString(this.DATA_GRP_NAME instanceof Utf8 ? (Utf8)this.DATA_GRP_NAME : null);
          }
          break;

        case 2:
          this.SRC_KEY_VAL = in.readString(this.SRC_KEY_VAL instanceof Utf8 ? (Utf8)this.SRC_KEY_VAL : null);
          break;

        case 3:
          this.SRC_CDC_OPER_NM = in.readString(this.SRC_CDC_OPER_NM instanceof Utf8 ? (Utf8)this.SRC_CDC_OPER_NM : null);
          break;

        case 4:
          this.SRC_COMMIT_DT_UTC = in.readString(this.SRC_COMMIT_DT_UTC instanceof Utf8 ? (Utf8)this.SRC_COMMIT_DT_UTC : null);
          break;

        case 5:
          this.TRG_CRT_DT_PART_UTC = in.readString(this.TRG_CRT_DT_PART_UTC instanceof Utf8 ? (Utf8)this.TRG_CRT_DT_PART_UTC : null);
          break;

        case 6:
          this.SRC_SCHEMA_NM = in.readString(this.SRC_SCHEMA_NM instanceof Utf8 ? (Utf8)this.SRC_SCHEMA_NM : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










