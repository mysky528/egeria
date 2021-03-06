/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.generated.v115;

import org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.common.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.ArrayList;

/**
 * POJO for the 'inferred_key' asset type in IGC, displayed as 'Inferred Key' in the IGC UI.
 * <br><br>
 * (this code has been generated based on out-of-the-box IGC metadata types;
 *  if modifications are needed, eg. to handle custom attributes,
 *  extending from this class in your own custom class is the best approach.)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class InferredKey extends MainObject {

    public static final String IGC_TYPE_ID = "inferred_key";

    /**
     * The 'table_analysis' property, displayed as 'Table Analysis' in the IGC UI.
     * <br><br>
     * Will be a single {@link Reference} to a {@link MainObject} object.
     */
    protected Reference table_analysis;

    /**
     * The 'total_records' property, displayed as 'Total Records' in the IGC UI.
     */
    protected Number total_records;

    /**
     * The 'primary_key' property, displayed as 'Primary Key' in the IGC UI.
     */
    protected Boolean primary_key;

    /**
     * The 'inferred_database_fields' property, displayed as 'Inferred Database Fields' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link DatabaseColumn} objects.
     */
    protected ReferenceList inferred_database_fields;

    /**
     * The 'referenced_by_inferred_foreign_keys' property, displayed as 'Referenced by Inferred Foreign Keys' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link InferredForeignKey} objects.
     */
    protected ReferenceList referenced_by_inferred_foreign_keys;


    /** @see #table_analysis */ @JsonProperty("table_analysis")  public Reference getTableAnalysis() { return this.table_analysis; }
    /** @see #table_analysis */ @JsonProperty("table_analysis")  public void setTableAnalysis(Reference table_analysis) { this.table_analysis = table_analysis; }

    /** @see #total_records */ @JsonProperty("total_records")  public Number getTotalRecords() { return this.total_records; }
    /** @see #total_records */ @JsonProperty("total_records")  public void setTotalRecords(Number total_records) { this.total_records = total_records; }

    /** @see #primary_key */ @JsonProperty("primary_key")  public Boolean getPrimaryKey() { return this.primary_key; }
    /** @see #primary_key */ @JsonProperty("primary_key")  public void setPrimaryKey(Boolean primary_key) { this.primary_key = primary_key; }

    /** @see #inferred_database_fields */ @JsonProperty("inferred_database_fields")  public ReferenceList getInferredDatabaseFields() { return this.inferred_database_fields; }
    /** @see #inferred_database_fields */ @JsonProperty("inferred_database_fields")  public void setInferredDatabaseFields(ReferenceList inferred_database_fields) { this.inferred_database_fields = inferred_database_fields; }

    /** @see #referenced_by_inferred_foreign_keys */ @JsonProperty("referenced_by_inferred_foreign_keys")  public ReferenceList getReferencedByInferredForeignKeys() { return this.referenced_by_inferred_foreign_keys; }
    /** @see #referenced_by_inferred_foreign_keys */ @JsonProperty("referenced_by_inferred_foreign_keys")  public void setReferencedByInferredForeignKeys(ReferenceList referenced_by_inferred_foreign_keys) { this.referenced_by_inferred_foreign_keys = referenced_by_inferred_foreign_keys; }


    public static final Boolean isInferredKey(Object obj) { return (obj.getClass() == InferredKey.class); }

}
