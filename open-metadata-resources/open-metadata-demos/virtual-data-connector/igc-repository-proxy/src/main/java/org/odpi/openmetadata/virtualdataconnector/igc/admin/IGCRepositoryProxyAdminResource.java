/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.virtualdataconnector.igc.admin;

import org.odpi.openmetadata.adminservices.OMAGServerAdminServices;
import org.odpi.openmetadata.adminservices.OMAGServerOperationalServices;
import org.odpi.openmetadata.adminservices.configuration.properties.CohortConfig;
import org.odpi.openmetadata.adminservices.configuration.properties.LocalRepositoryConfig;
import org.odpi.openmetadata.adminservices.configuration.properties.OMAGServerConfig;
import org.odpi.openmetadata.adminservices.properties.OMAGServerConfigResponse;
import org.odpi.openmetadata.adminservices.properties.VoidResponse;
import org.odpi.openmetadata.frameworks.connectors.properties.beans.Connection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * IGCRepositoryProxyAdminResource provides the spring annotations for the server-side
 * implementation of the administrative interface for
 * the IGC Repository Proxy.
 */
@RestController
@RequestMapping("/open-metadata/admin-services/users/{userId}/servers/{serverName}")
public class IGCRepositoryProxyAdminResource {
    private OMAGServerAdminServices       adminAPI            = new OMAGServerAdminServices();
    private OMAGServerOperationalServices operationalServices = new OMAGServerOperationalServices();


    /*
     * =============================================================
     * Help the client discover the type of the server
     */


    /**
     * Return the origin of this server implementation.
     *
     * @return Server Origin
     */
    @RequestMapping(method = RequestMethod.GET, path = "/server-origin")
    public String getServerOrigin() {
        return "IGC Repository Proxy";
    }


    /*
     * =============================================================
     * Configure server - basic options using defaults
     */

    /**
     * Set up the root URL for this server that is used to construct full URL paths to calls for
     * this server's REST interfaces.  The default value is "localhost:8080".
     *
     * @param userId     user that is issuing the request.
     * @param serverName local server name.
     * @param url        String url.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or serverURLRoot parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/server-url-root")
    public VoidResponse setServerURLRoot(@PathVariable String userId,
                                         @PathVariable String serverName,
                                         @RequestParam String url) {
        return adminAPI.setServerURLRoot(userId, serverName, url);
    }


    /**
     * Set up the descriptive type of the server.  This value is added to distributed events to
     * make it easier to understand the source of events.  The default value is "Open Metadata and Governance Server".
     *
     * @param userId     user that is issuing the request.
     * @param serverName local server name.
     * @param typeName   short description for the type of server.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or serverType parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/server-type")
    public VoidResponse setServerType(@PathVariable String userId,
                                      @PathVariable String serverName,
                                      @RequestParam String typeName) {
        return adminAPI.setServerType(userId, serverName, typeName);
    }


    /**
     * Set up the name of the organization that is running this server.  This value is added to distributed events to
     * make it easier to understand the source of events.  The default value is null.
     *
     * @param userId     user that is issuing the request.
     * @param serverName local server name.
     * @param name       String name of the organization.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or organizationName parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/organization-name")
    public VoidResponse setOrganizationName(@PathVariable String userId,
                                            @PathVariable String serverName,
                                            @RequestParam String name) {
        return adminAPI.setOrganizationName(userId, serverName, name);
    }


    /**
     * Set up the user id to use when there is no external user driving the work (for example when processing events
     * from another server).
     *
     * @param userId     - user that is issuing the request.
     * @param serverName - local server name.
     * @param id         - String user is for the server.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or serverURLRoot parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/server-user-id")
    public VoidResponse setServerUserId(@PathVariable String userId,
                                        @PathVariable String serverName,
                                        @RequestParam String id) {
        return adminAPI.setServerUserId(userId, serverName, id);
    }


    /**
     * Set an upper limit in the page size that can be requested on a REST call to the server.  The default
     * value is 1000.
     *
     * @param userId     user that is issuing the request.
     * @param serverName local server name.
     * @param limit      max number of elements that can be returned on a request.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or maxPageSize parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/max-page-size")
    public VoidResponse setMaxPageSize(@PathVariable String userId,
                                       @PathVariable String serverName,
                                       @RequestParam int limit) {
        return adminAPI.setMaxPageSize(userId, serverName, limit);
    }


    /**
     * Set up the default event bus for embedding in event-driven connector.   The resulting connector will
     * be used in the OMRS Topic Connector for each cohort, the in and out topics for each Access Service and
     * the local repositories event mapper.
     *
     * @param userId               user that is issuing the request.
     * @param serverName           local server name.
     * @param connectorProvider    connector provider for the event bus (if it is null then Kafka is assumed).
     * @param topicURLRoot         the common root of the topics used by the open metadata server.
     * @param additionalProperties property name/value pairs used to configure the connection to the event bus connector
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGConfigurationErrorException it is too late to configure the event bus - other configuration already exists or
     * OMAGInvalidParameterException invalid serverName or serviceMode parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/event-bus")
    public VoidResponse setEventBus(@PathVariable String userId,
                                    @PathVariable String serverName,
                                    @RequestParam(required = false) String connectorProvider,
                                    @RequestParam(required = false) String topicURLRoot,
                                    @RequestBody(required = false) Map<String, Object> additionalProperties) {
        return adminAPI.setEventBus(userId, serverName, connectorProvider, topicURLRoot, additionalProperties);
    }


    /**
     * Provide the connection to the local repository - used when the local repository mode is set to repository proxy.
     *
     * @param userId     user that is issuing the request.
     * @param serverName local server name.
     * @param connection connection to the OMRS repository connector.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or repositoryProxyConnection parameter or
     * OMAGConfigurationErrorException the local repository mode has not been set
     */
    @RequestMapping(method = RequestMethod.POST, path = "/local-repository/mode/repository-proxy/connection")
    public VoidResponse setRepositoryProxyConnection(@PathVariable String userId,
                                                     @PathVariable String serverName,
                                                     @RequestBody Connection connection) {
        return adminAPI.setRepositoryProxyConnection(userId, serverName, connection);
    }


    /**
     * Provide the connection to the local repository - used when the local repository mode is set to repository proxy.
     *
     * @param userId               user that is issuing the request.
     * @param serverName           local server name.
     * @param connectorProvider    connector provider class name to the OMRS repository connector.
     * @param additionalProperties additional parameters to pass to the repository connector
     * @return void response or
     * OMAGNotAuthorizedException     the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or repositoryProxyConnection parameter or
     * OMAGConfigurationErrorException the local repository mode has not been set.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/local-repository/mode/repository-proxy/details")
    public VoidResponse setRepositoryProxyConnection(@PathVariable String userId,
                                                     @PathVariable String serverName,
                                                     @RequestParam String connectorProvider,
                                                     @RequestBody(required = false) Map<String, Object> additionalProperties) {
        return adminAPI.setRepositoryProxyConnection(userId, serverName, connectorProvider, additionalProperties);
    }


    /**
     * Provide the connection to the local repository's event mapper if needed.  The default value is null which
     * means no event mapper.  An event mapper is needed if the local repository has additional APIs that can change
     * the metadata in the repository without going through the open metadata and governance services.
     *
     * @param userId     user that is issuing the request.
     * @param serverName local server name.
     * @param connection connection to the OMRS repository event mapper.
     * @return void response
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or localRepositoryEventMapper parameter or
     * OMAGConfigurationErrorException the local repository mode, or the event mapper has not been set
     */
    @RequestMapping(method = RequestMethod.POST, path = "/local-repository/event-mapper-connection")
    public VoidResponse setLocalRepositoryEventMapper(@PathVariable String userId,
                                                      @PathVariable String serverName,
                                                      @RequestBody Connection connection) {
        return adminAPI.setLocalRepositoryEventMapper(userId, serverName, connection);
    }


    /**
     * Provide the connection to the local repository's event mapper if needed.  The default value is null which
     * means no event mapper.  An event mapper is needed if the local repository has additional APIs that can change
     * the metadata in the repository without going through the open metadata and governance services.
     *
     * @param userId               user that is issuing the request.
     * @param serverName           local server name.
     * @param connectorProvider    Java class name of the connector provider for the OMRS repository event mapper.
     * @param eventSource          topic name or URL to the native event source.
     * @param additionalProperties additional properties for the event mapper connection
     * @return void response or
     * OMAGNotAuthorizedException    the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or localRepositoryEventMapper parameter or
     * OMAGConfigurationErrorException the local repository mode has not been set.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/local-repository/event-mapper-details")
    public VoidResponse setLocalRepositoryEventMapper(@PathVariable String userId,
                                                      @PathVariable String serverName,
                                                      @RequestParam String connectorProvider,
                                                      @RequestParam String eventSource,
                                                      @RequestBody(required = false) Map<String, Object> additionalProperties) {
        return adminAPI.setLocalRepositoryEventMapper(userId, serverName, connectorProvider, eventSource, additionalProperties);
    }


    /**
     * Enable registration of server to an open metadata repository cohort.  This is a group of open metadata
     * repositories that are sharing metadata.  An OMAG server can connect to zero, one or more cohorts.
     * Each cohort needs a unique name.  The members of the cohort use a shared topic to exchange registration
     * information and events related to changes in their supported metadata types and instances.
     * They are also able to query each other's metadata directly through REST calls.
     *
     * @param userId               user that is issuing the request.
     * @param serverName           local server name.
     * @param cohortName           name of the cohort.
     * @param additionalProperties additional properties for the event bus connection
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName, cohortName or serviceMode parameter or
     * OMAGConfigurationErrorException the event bus is not set.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/cohorts/{cohortName}")
    public VoidResponse enableCohortRegistration(@PathVariable String userId,
                                                 @PathVariable String serverName,
                                                 @PathVariable String cohortName,
                                                 @RequestBody(required = false) Map<String, Object> additionalProperties) {
        return adminAPI.enableCohortRegistration(userId, serverName, cohortName, additionalProperties);
    }


    /**
     * Unregister this server from an open metadata repository cohort.
     *
     * @param userId     user that is issuing the request.
     * @param serverName local server name.
     * @param cohortName name of the cohort.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName, cohortName or serviceMode parameter.
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/cohorts/{cohortName}")
    public VoidResponse disableCohortRegistration(@PathVariable String userId,
                                                  @PathVariable String serverName,
                                                  @PathVariable String cohortName) {
        return adminAPI.disableCohortRegistration(userId, serverName, cohortName);
    }


    /*
     * =============================================================
     * Configure server - advanced options overriding defaults
     */


    /**
     * Set up the configuration for the local repository.  This overrides the current values.
     *
     * @param userId                user that is issuing the request.
     * @param serverName            local server name.
     * @param localRepositoryConfig configuration properties for the local repository.
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName or localRepositoryConfig parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/local-repository/configuration")
    public VoidResponse setLocalRepositoryConfig(@PathVariable String userId,
                                                 @PathVariable String serverName,
                                                 @RequestBody LocalRepositoryConfig localRepositoryConfig) {
        return adminAPI.setLocalRepositoryConfig(userId, serverName, localRepositoryConfig);
    }


    /**
     * Set up the configuration properties for a cohort.  This may reconfigure an existing cohort or create a
     * cohort.  Use setCohortMode to delete a cohort.
     *
     * @param userId       user that is issuing the request
     * @param serverName   local server name
     * @param cohortName   name of the cohort
     * @param cohortConfig configuration for the cohort
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName, cohortName or cohortConfig parameter.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/cohorts/{cohortName}/configuration")
    public VoidResponse setCohortConfig(@PathVariable String userId,
                                        @PathVariable String serverName,
                                        @PathVariable String cohortName,
                                        @RequestBody CohortConfig cohortConfig) {
        return adminAPI.setCohortConfig(userId, serverName, cohortName, cohortConfig);
    }


    /*
     * =============================================================
     * Query current configuration
     */


    /**
     * Return the stored configuration document for the server.
     *
     * @param userId     user that is issuing the request
     * @param serverName local server name
     * @return OMAGServerConfig properties or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException invalid serverName parameter.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/configuration")
    public OMAGServerConfigResponse getCurrentConfiguration(@PathVariable String userId,
                                                            @PathVariable String serverName) {
        return adminAPI.getStoredConfiguration(userId, serverName);
    }


    /*
     * ========================================================================================
     * Activate and deactivate the open metadata and governance capabilities in the OMAG Server
     */

    /**
     * Activate the open metadata and governance services using the stored configuration information.
     *
     * @param userId     user that is issuing the request
     * @param serverName local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the server name is invalid or
     * OMAGConfigurationErrorException there is a problem using the supplied configuration.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/instance")
    public VoidResponse activateWithStoredConfig(@PathVariable String userId,
                                                 @PathVariable String serverName) {
        return operationalServices.activateWithStoredConfig(userId, serverName);
    }


    /**
     * Activate the open metadata and governance services using the supplied configuration
     * document.
     *
     * @param userId        user that is issuing the request
     * @param configuration properties used to initialize the services
     * @param serverName    local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the server name is invalid or
     * OMAGConfigurationErrorException there is a problem using the supplied configuration.
     */
    @RequestMapping(method = RequestMethod.POST, path = "/instance/configuration")
    public VoidResponse activateWithSuppliedConfig(@PathVariable String userId,
                                                   @PathVariable String serverName,
                                                   @RequestParam OMAGServerConfig configuration) {
        return operationalServices.activateWithSuppliedConfig(userId, serverName, configuration);
    }


    /**
     * Temporarily deactivate any open metadata and governance services.
     *
     * @param userId     user that is issuing the request
     * @param serverName local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the serverName is invalid.
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "/instance")
    public VoidResponse deactivateTemporarily(@PathVariable String userId,
                                              @PathVariable String serverName) {
        return operationalServices.deactivateTemporarily(userId, serverName);
    }


    /**
     * Permanently deactivate any open metadata and governance services and unregister from
     * any cohorts.
     *
     * @param userId     user that is issuing the request
     * @param serverName local server name
     * @return void response or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the serverName is invalid.
     */
    @RequestMapping(method = RequestMethod.DELETE, path = "")
    public VoidResponse deactivatePermanently(@PathVariable String userId,
                                              @PathVariable String serverName) {
        return operationalServices.deactivatePermanently(userId, serverName);
    }


    /**
     * Return the configuration used for the current active instance of the server.  Null is returned if
     * the server instance is not running.
     *
     * @param userId  user that is issuing the request
     * @param serverName  local server name
     * @return configuration properties used to initialize the server or null if not running or
     * OMAGNotAuthorizedException the supplied userId is not authorized to issue this command or
     * OMAGInvalidParameterException the server name is invalid or
     * OMAGConfigurationErrorException there is a problem using the supplied configuration.
     */
    @RequestMapping(method = RequestMethod.GET, path = "/instance/configuration")
    public OMAGServerConfigResponse getActiveConfiguration(@PathVariable String           userId,
                                                           @PathVariable String           serverName)
    {
        return operationalServices.getActiveConfiguration(userId, serverName);
    }

}
