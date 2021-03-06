/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.generated.v117;

import org.odpi.openmetadata.adapters.repositoryservices.igc.clientlibrary.model.common.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.ArrayList;

/**
 * POJO for the 'blueprint_element_link' asset type in IGC, displayed as 'Blueprint Element Link' in the IGC UI.
 * <br><br>
 * (this code has been generated based on out-of-the-box IGC metadata types;
 *  if modifications are needed, eg. to handle custom attributes,
 *  extending from this class in your own custom class is the best approach.)
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class BlueprintElementLink extends MainObject {

    public static final String IGC_TYPE_ID = "blueprint_element_link";

    /**
     * The 'element_name' property, displayed as 'Element Name' in the IGC UI.
     */
    protected String element_name;


    /** @see #element_name */ @JsonProperty("element_name")  public String getElementName() { return this.element_name; }
    /** @see #element_name */ @JsonProperty("element_name")  public void setElementName(String element_name) { this.element_name = element_name; }


    public static final Boolean isBlueprintElementLink(Object obj) { return (obj.getClass() == BlueprintElementLink.class); }

}
