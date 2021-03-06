/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.generated.v117;

import org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.common.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.ArrayList;

/**
 * POJO for the 'table_definition_properties' asset type in IGC, displayed as 'Table Definition Properties' in the IGC UI.
 * <br><br>
 * (this code has been generated based on out-of-the-box IGC metadata types;
 *  if modifications are needed, eg. to handle custom attributes,
 *  extending from this class in your own custom class is the best approach.)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TableDefinitionProperties extends MainObject {

    public static final String IGC_TYPE_ID = "table_definition_properties";

    /**
     * The 'of_ds_table_definition' property, displayed as 'Of DS Table Definition' in the IGC UI.
     * <br><br>
     * Will be a single {@link Reference} to a {@link TableDefinition} object.
     */
    protected Reference of_ds_table_definition;

    /**
     * The 'seq_col_space' property, displayed as 'SEQ Col Space' in the IGC UI.
     */
    protected Number seq_col_space;

    /**
     * The 'seq_fixed_width' property, displayed as 'SEQ Fixed Width' in the IGC UI.
     */
    protected Boolean seq_fixed_width;

    /**
     * The 'seq_delimiter' property, displayed as 'SEQ Delimiter' in the IGC UI.
     */
    protected String seq_delimiter;

    /**
     * The 'seq_quote_char' property, displayed as 'SEQ Quote Char' in the IGC UI.
     */
    protected String seq_quote_char;

    /**
     * The 'locator' property, displayed as 'Locator' in the IGC UI.
     */
    protected String locator;

    /**
     * The 'access_type' property, displayed as 'Access Type' in the IGC UI.
     */
    protected String access_type;

    /**
     * The 'apt_record_prop' property, displayed as 'APT Record Prop' in the IGC UI.
     */
    protected String apt_record_prop;

    /**
     * The 'seq_col_headers' property, displayed as 'SEQ Col Headers' in the IGC UI.
     */
    protected Boolean seq_col_headers;

    /**
     * The 'import_location' property, displayed as 'Import Location' in the IGC UI.
     */
    protected String import_location;

    /**
     * The 'nls_map_name' property, displayed as 'NLS Map Name' in the IGC UI.
     */
    protected String nls_map_name;

    /**
     * The 'seq_omit_new_line' property, displayed as 'SEQ Omit New Line' in the IGC UI.
     */
    protected Boolean seq_omit_new_line;

    /**
     * The 'platform_type' property, displayed as 'Platform Type' in the IGC UI.
     */
    protected String platform_type;

    /**
     * The 'version' property, displayed as 'Version' in the IGC UI.
     */
    protected String version;

    /**
     * The 'a_xmeta_locking_root' property, displayed as 'A XMeta Locking Root' in the IGC UI.
     */
    protected String a_xmeta_locking_root;

    /**
     * The 'registration_timestamp' property, displayed as 'Registration Timestamp' in the IGC UI.
     */
    protected String registration_timestamp;

    /**
     * The 'allow_column_mapping' property, displayed as 'Allow Column Mapping' in the IGC UI.
     */
    protected Boolean allow_column_mapping;

    /**
     * The 'sp_error_codes' property, displayed as 'SP Error Codes' in the IGC UI.
     */
    protected String sp_error_codes;

    /**
     * The 'seq_null_string' property, displayed as 'SEQ Null String' in the IGC UI.
     */
    protected String seq_null_string;

    /**
     * The 'multivalued' property, displayed as 'Multivalued' in the IGC UI.
     */
    protected Boolean multivalued;

    /**
     * The 'pad_char' property, displayed as 'Pad Char' in the IGC UI.
     */
    protected String pad_char;


    /** @see #of_ds_table_definition */ @JsonProperty("of_ds_table_definition")  public Reference getOfDsTableDefinition() { return this.of_ds_table_definition; }
    /** @see #of_ds_table_definition */ @JsonProperty("of_ds_table_definition")  public void setOfDsTableDefinition(Reference of_ds_table_definition) { this.of_ds_table_definition = of_ds_table_definition; }

    /** @see #seq_col_space */ @JsonProperty("seq_col_space")  public Number getSeqColSpace() { return this.seq_col_space; }
    /** @see #seq_col_space */ @JsonProperty("seq_col_space")  public void setSeqColSpace(Number seq_col_space) { this.seq_col_space = seq_col_space; }

    /** @see #seq_fixed_width */ @JsonProperty("seq_fixed_width")  public Boolean getSeqFixedWidth() { return this.seq_fixed_width; }
    /** @see #seq_fixed_width */ @JsonProperty("seq_fixed_width")  public void setSeqFixedWidth(Boolean seq_fixed_width) { this.seq_fixed_width = seq_fixed_width; }

    /** @see #seq_delimiter */ @JsonProperty("seq_delimiter")  public String getSeqDelimiter() { return this.seq_delimiter; }
    /** @see #seq_delimiter */ @JsonProperty("seq_delimiter")  public void setSeqDelimiter(String seq_delimiter) { this.seq_delimiter = seq_delimiter; }

    /** @see #seq_quote_char */ @JsonProperty("seq_quote_char")  public String getSeqQuoteChar() { return this.seq_quote_char; }
    /** @see #seq_quote_char */ @JsonProperty("seq_quote_char")  public void setSeqQuoteChar(String seq_quote_char) { this.seq_quote_char = seq_quote_char; }

    /** @see #locator */ @JsonProperty("locator")  public String getLocator() { return this.locator; }
    /** @see #locator */ @JsonProperty("locator")  public void setLocator(String locator) { this.locator = locator; }

    /** @see #access_type */ @JsonProperty("access_type")  public String getAccessType() { return this.access_type; }
    /** @see #access_type */ @JsonProperty("access_type")  public void setAccessType(String access_type) { this.access_type = access_type; }

    /** @see #apt_record_prop */ @JsonProperty("apt_record_prop")  public String getAptRecordProp() { return this.apt_record_prop; }
    /** @see #apt_record_prop */ @JsonProperty("apt_record_prop")  public void setAptRecordProp(String apt_record_prop) { this.apt_record_prop = apt_record_prop; }

    /** @see #seq_col_headers */ @JsonProperty("seq_col_headers")  public Boolean getSeqColHeaders() { return this.seq_col_headers; }
    /** @see #seq_col_headers */ @JsonProperty("seq_col_headers")  public void setSeqColHeaders(Boolean seq_col_headers) { this.seq_col_headers = seq_col_headers; }

    /** @see #import_location */ @JsonProperty("import_location")  public String getImportLocation() { return this.import_location; }
    /** @see #import_location */ @JsonProperty("import_location")  public void setImportLocation(String import_location) { this.import_location = import_location; }

    /** @see #nls_map_name */ @JsonProperty("nls_map_name")  public String getNlsMapName() { return this.nls_map_name; }
    /** @see #nls_map_name */ @JsonProperty("nls_map_name")  public void setNlsMapName(String nls_map_name) { this.nls_map_name = nls_map_name; }

    /** @see #seq_omit_new_line */ @JsonProperty("seq_omit_new_line")  public Boolean getSeqOmitNewLine() { return this.seq_omit_new_line; }
    /** @see #seq_omit_new_line */ @JsonProperty("seq_omit_new_line")  public void setSeqOmitNewLine(Boolean seq_omit_new_line) { this.seq_omit_new_line = seq_omit_new_line; }

    /** @see #platform_type */ @JsonProperty("platform_type")  public String getPlatformType() { return this.platform_type; }
    /** @see #platform_type */ @JsonProperty("platform_type")  public void setPlatformType(String platform_type) { this.platform_type = platform_type; }

    /** @see #version */ @JsonProperty("version")  public String getVersion() { return this.version; }
    /** @see #version */ @JsonProperty("version")  public void setVersion(String version) { this.version = version; }

    /** @see #a_xmeta_locking_root */ @JsonProperty("a_xmeta_locking_root")  public String getAXmetaLockingRoot() { return this.a_xmeta_locking_root; }
    /** @see #a_xmeta_locking_root */ @JsonProperty("a_xmeta_locking_root")  public void setAXmetaLockingRoot(String a_xmeta_locking_root) { this.a_xmeta_locking_root = a_xmeta_locking_root; }

    /** @see #registration_timestamp */ @JsonProperty("registration_timestamp")  public String getRegistrationTimestamp() { return this.registration_timestamp; }
    /** @see #registration_timestamp */ @JsonProperty("registration_timestamp")  public void setRegistrationTimestamp(String registration_timestamp) { this.registration_timestamp = registration_timestamp; }

    /** @see #allow_column_mapping */ @JsonProperty("allow_column_mapping")  public Boolean getAllowColumnMapping() { return this.allow_column_mapping; }
    /** @see #allow_column_mapping */ @JsonProperty("allow_column_mapping")  public void setAllowColumnMapping(Boolean allow_column_mapping) { this.allow_column_mapping = allow_column_mapping; }

    /** @see #sp_error_codes */ @JsonProperty("sp_error_codes")  public String getSpErrorCodes() { return this.sp_error_codes; }
    /** @see #sp_error_codes */ @JsonProperty("sp_error_codes")  public void setSpErrorCodes(String sp_error_codes) { this.sp_error_codes = sp_error_codes; }

    /** @see #seq_null_string */ @JsonProperty("seq_null_string")  public String getSeqNullString() { return this.seq_null_string; }
    /** @see #seq_null_string */ @JsonProperty("seq_null_string")  public void setSeqNullString(String seq_null_string) { this.seq_null_string = seq_null_string; }

    /** @see #multivalued */ @JsonProperty("multivalued")  public Boolean getMultivalued() { return this.multivalued; }
    /** @see #multivalued */ @JsonProperty("multivalued")  public void setMultivalued(Boolean multivalued) { this.multivalued = multivalued; }

    /** @see #pad_char */ @JsonProperty("pad_char")  public String getPadChar() { return this.pad_char; }
    /** @see #pad_char */ @JsonProperty("pad_char")  public void setPadChar(String pad_char) { this.pad_char = pad_char; }


    public static final Boolean isTableDefinitionProperties(Object obj) { return (obj.getClass() == TableDefinitionProperties.class); }

}
