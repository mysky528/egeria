/* SPDX-License-Identifier: Apache 2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.adminservices;

/**
 * OMAGServerOriginServices support the origin services for Egeria's OMAG Server.  It is overridden in
 * other server implementations.
 */
public class OMAGServerOriginServices
{
    final String   implementationOrigin = "Egeria OMAG Server";


    /**
     * Return the origin of this server implementation.
     *
     * @param userId name of the user making the request
     * @param serverName name of the server that the request is for
     * @return String description
     */
    public String getServerOrigin(String    userId,
                                  String    serverName)
    {
        return implementationOrigin;
    }
}