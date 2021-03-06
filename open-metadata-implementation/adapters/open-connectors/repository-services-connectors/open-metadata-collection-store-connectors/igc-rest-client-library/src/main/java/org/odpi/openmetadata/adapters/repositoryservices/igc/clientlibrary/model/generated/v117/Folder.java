/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.generated.v117;

import org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.common.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.ArrayList;

/**
 * POJO for the 'folder' asset type in IGC, displayed as 'Folder' in the IGC UI.
 * <br><br>
 * (this code has been generated based on out-of-the-box IGC metadata types;
 *  if modifications are needed, eg. to handle custom attributes,
 *  extending from this class in your own custom class is the best approach.)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Folder extends MainObject {

    public static final String IGC_TYPE_ID = "folder";

    /**
     * The 'description' property, displayed as 'Description' in the IGC UI.
     */
    protected String description;

    /**
     * The 'parent_folder' property, displayed as 'Parent Folder' in the IGC UI.
     * <br><br>
     * Will be a single {@link Reference} to a {@link Folder} object.
     */
    protected Reference parent_folder;

    /**
     * The 'contains_folders' property, displayed as 'Contains Folders' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link Folder} objects.
     */
    protected ReferenceList contains_folders;

    /**
     * The 'contains_mapping_documents' property, displayed as 'Contains Mapping Documents' in the IGC UI.
     * <br><br>
     * Will be a {@link ReferenceList} of {@link MainObject} objects.
     */
    protected ReferenceList contains_mapping_documents;


    /** @see #description */ @JsonProperty("description")  public String getDescription() { return this.description; }
    /** @see #description */ @JsonProperty("description")  public void setDescription(String description) { this.description = description; }

    /** @see #parent_folder */ @JsonProperty("parent_folder")  public Reference getParentFolder() { return this.parent_folder; }
    /** @see #parent_folder */ @JsonProperty("parent_folder")  public void setParentFolder(Reference parent_folder) { this.parent_folder = parent_folder; }

    /** @see #contains_folders */ @JsonProperty("contains_folders")  public ReferenceList getContainsFolders() { return this.contains_folders; }
    /** @see #contains_folders */ @JsonProperty("contains_folders")  public void setContainsFolders(ReferenceList contains_folders) { this.contains_folders = contains_folders; }

    /** @see #contains_mapping_documents */ @JsonProperty("contains_mapping_documents")  public ReferenceList getContainsMappingDocuments() { return this.contains_mapping_documents; }
    /** @see #contains_mapping_documents */ @JsonProperty("contains_mapping_documents")  public void setContainsMappingDocuments(ReferenceList contains_mapping_documents) { this.contains_mapping_documents = contains_mapping_documents; }


    public static final Boolean isFolder(Object obj) { return (obj.getClass() == Folder.class); }

}
