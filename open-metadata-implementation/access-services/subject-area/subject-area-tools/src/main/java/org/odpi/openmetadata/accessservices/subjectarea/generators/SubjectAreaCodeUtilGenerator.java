/* SPDX-License-Identifier: Apache-2.0 */
/* Copyright Contributors to the ODPi Egeria project. */
package org.odpi.openmetadata.accessservices.subjectarea.generators;

import org.odpi.openmetadata.accessservices.subjectarea.model.*;
import org.odpi.openmetadata.accessservices.subjectarea.utils.GeneratorUtilities;

import java.io.*;
import java.util.*;

/**
 * The Subject Area OMAS API exposes many concepts as java beans. To ease the mapping of those beans to the underluying OMRS interface, OMRS java beans are
 * generated by this class. It generates a java class for each entity, relationship, reference, classification. It also generates mapper classes to map between the omrs
 * api objects to the omrs bean objects. There is a omrs bean accessor and test classes that exposes operations like create ,get, read and delete.
 * <p>
 * The OMAS API can then call the OMRS bean accessor and the OMRS bean accessor deals with mapping to the omrs interface.
 */
public class SubjectAreaCodeUtilGenerator
{
    public static final String GENERATOR = "open-metadata-implementation/access-services/subject-area/subject-area-tools";
    public static final String TEMPLATES_FOLDER = GENERATOR + "/src/main/resources/templates/";
    public static final String SUBJECTAREA_OMAS_CLIENT = "open-metadata-implementation/access-services/subject-area/subject-area-client";
    public static final String SUBJECTAREA_OMAS_SERVER = "open-metadata-implementation/access-services/subject-area/subject-area-server";
    public static final String SUBJECTAREA_OMAS_SPRING = "open-metadata-implementation/access-services/subject-area/subject-area-spring";
    public static final String SPRING_TERM = SUBJECTAREA_OMAS_SPRING + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/spring/SubjectAreaTermRESTResource.java";
    public static final String SPRING_CAT = SUBJECTAREA_OMAS_SPRING + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/spring/SubjectAreaCategoryRESTResource.java";
    public static final String SPRING_GLOSS = SUBJECTAREA_OMAS_SPRING + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/spring/SubjectAreaGlossaryRESTResource.java";
    public static final String SPRING_REL = SUBJECTAREA_OMAS_SPRING + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/spring/SubjectAreaRelationshipRESTResource.java";
    public static final String CLI_TERM_IMPL = SUBJECTAREA_OMAS_CLIENT + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/client/SubjectAreaTermImpl.java";
    public static final String CLI_CAT_IMPL = SUBJECTAREA_OMAS_CLIENT + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/client/SubjectAreaCategoryImpl.java";
    public static final String CLI_GLOSS_IMPL = SUBJECTAREA_OMAS_CLIENT + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/client/SubjectAreaGlossaryImpl.java";
    public static final String CLI_REL_IMPL = SUBJECTAREA_OMAS_CLIENT + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/client/SubjectAreaRelationshipImpl.java";
    public static final String SER_TERM_IMPL = SUBJECTAREA_OMAS_SERVER + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/services/SubjectAreaTermRestServices.java";
    public static final String SER_CAT_IMPL = SUBJECTAREA_OMAS_SERVER+ "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/services/SubjectAreaCategoryRestServices.java";
    public static final String SER_GLOSS_IMPL = SUBJECTAREA_OMAS_SERVER + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/services/SubjectAreaGlossaryRestServices.java";
    public static final String SER_REL_IMPL = SUBJECTAREA_OMAS_SERVER + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/services/SubjectAreaRelationshipRestServices.java";
    private String templateName = null;

    public SubjectAreaCodeUtilGenerator(String templateName)
    {
        this.templateName = TEMPLATES_FOLDER + templateName;
    }

    public static void main(String[] args) throws IOException
    {

        // run the generation
        // generateClientSideRelationshipImpl(args);
        // generateDetectRelationships(args);
        // generateFVT(args);
         generateCliInterface();
        // generateAddGuidNotRecogonizedJD();
        // SubjectAreaRelationshipRESTServices
        // addInstance();
         // AddServerNameJD();
         //moveOverJDFromServerToClient();
        //moveOverJDFromServerToSpring();
    }

    private static void AddServerNameJD() throws IOException
    {
        String inputFileName = SER_REL_IMPL;
        FileWriter outputFileWriter = new FileWriter("rel");
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        String line = reader.readLine();
        boolean inIf = false;
        while (line != null)
        {
            if (line.contains("@param userId"))
            {
                outputFileWriter.write("     * @param serverName         serverName under which this request is performed, this is used in multi tenanting to identify the tenant\n");
            }
            outputFileWriter.write(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        outputFileWriter.close();
    }

    private static void addInstance() throws IOException
    {
        String inputFileName = SUBJECTAREA_OMAS_SERVER + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/server/services/SubjectAreaRelationshipRESTServices.java";
        FileWriter outputFileWriter = new FileWriter("SubjectAreaRelationshipRESTServices.java");
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        String line = reader.readLine();
        boolean inIf = false;
        while (line != null)
        {
            if (line.contains("SubjectAreaOMASAPIResponse response = null;"))
            {
                outputFileWriter.write("        // initialise omrs API helper with the right instance based on the server name\n");
                outputFileWriter.write("        SubjectAreaOMASAPIResponse response = initialiseOMRSAPIHelperForInstance(serverName);\n");
                outputFileWriter.write("        if (response == null) {\n");
                inIf = true;
            } else if (line.contains("@param userId"))
            {
                outputFileWriter.write("     * @param serverName         serverName under which this request is performed, this is used in multi tenanting to identify the tenant\n");
                outputFileWriter.write(line + "\n");
            } else if (line.contains("(String userId"))
            {
                String newLine = line.replace("(String userId", "(String serverName, String userId");
                outputFileWriter.write(newLine + "\n");
            } else
            {
                if (inIf)
                {
                    if (line.contains("if (log.isDebugEnabled())"))
                    {
                        outputFileWriter.write("        }\n");
                        inIf = false;
                    } else if (line.contains("return") && line.contains("response"))
                    {
                        outputFileWriter.write("        }\n");
                        outputFileWriter.write("        if (log.isDebugEnabled())\n");
                        outputFileWriter.write("        {\n");
                        outputFileWriter.write("             log.debug(\"<== successful method : \" + methodName + \",userId=\" + userId + \", response=\" + response);\n");
                        outputFileWriter.write("        }\n");
                        inIf = false;
                    }
                }
                outputFileWriter.write(line + "\n");
            }
            line = reader.readLine();
        }
        reader.close();
        outputFileWriter.close();

    }


    private static void generateCliInterface() throws IOException
    {
        String outputFileName = "SubjectAreaRel.java";
        FileWriter outputFileWriter = new FileWriter(outputFileName);
        String inputFileName = CLI_REL_IMPL;

        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        String line = reader.readLine();
        boolean output = false;
        while (line != null)
        {
            if (line.contains("/**"))
            {
                output = true;
            }
            if (output)
            {
                if (line.contains("{"))
                {
                    line = line.replace("{", ";");
                    outputFileWriter.write(line + "\n");
                    output = false;
                } else
                {
                    outputFileWriter.write(line + "\n");
                }
            }
            line = reader.readLine();
        }
        reader.close();
        outputFileWriter.close();

    }

    private static void moveOverJDFromServerToSpring() throws IOException
    {
//
        // String springFileName = SER_CAT_IMPL;
        String springFileName = SER_REL_IMPL;
        Map<String, String> methodToSpringJDMap = getMethodJDMapForServer(springFileName);

        for (String key : methodToSpringJDMap.keySet())
        {

            System.err.println("Key:" + key + "\n:javadoc\n" + methodToSpringJDMap.get(key));
        }

        String cliFileName = SPRING_REL;
        String outputFileName = "rel.java";
        FileWriter outputFileWriter = new FileWriter(outputFileName);

        BufferedReader reader = new BufferedReader(new FileReader(cliFileName));
        String line = reader.readLine();

        int jdnumber = 0;
        String javadoc = null;
        boolean inJD = false;
        String request = null;
        while (line != null)
        {
            if (line.contains("/**"))
            {
                jdnumber++;
                javadoc = line + "\n";
                // ignore class jd and constructor jd.
                if (jdnumber > 2)
                {
                    inJD = true;
                } else {
                    outputFileWriter.write(line + "\n");
                }
            } else if (inJD)
            {
                if (line.contains("*/"))
                {
                    javadoc = javadoc + line + "\n";
                    inJD = false;
                    line = reader.readLine();
                    // jump to the next line with content
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }
                    if (line.contains("@RequestMapping"))
                    {
                        request = line;
                        line = reader.readLine();
                    }
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }

                    // should be the method line - it should look like this
                    //public  Term getTermByGuid(
                    StringTokenizer st = new StringTokenizer(line, " (");
                    int count = 0;
                    while (st.hasMoreTokens())
                    {
                        count++;
                        String token = st.nextToken();
                        if (count == 1 && token.equals("private"))
                        {
                            //ignore private methods
                            break;
                        } else if (count == 3)
                        {
                            String methodName = token;
                            // here is where we do the work!
                            String newJavaDoc = methodToSpringJDMap.get(methodName);
                            if (newJavaDoc== null) {
                                // do not have a replacement so use what we have already.
                                outputFileWriter.write(javadoc);
                            } else {

                                outputFileWriter.write(newJavaDoc);
                                outputFileWriter.write(request +"\n");
                            }
                            outputFileWriter.write(line +"\n");
                        }
                    }

                } else
                {
                    javadoc = javadoc + line + "\n";
                }
            } else {
                outputFileWriter.write(line + "\n");
            }
            line = reader.readLine();

        }
        reader.close();
        outputFileWriter.close();


    }
    private static void moveOverJDFromServerToClient() throws IOException
    {


        String springFileName = SPRING_TERM;
        Map<String, String> methodToSpringJDMap = getMethodJDMapForCli(springFileName);

        for (String key : methodToSpringJDMap.keySet())
        {

            System.err.println("Key:" + key + "\n:javadoc\n" + methodToSpringJDMap.get(key));
        }

        String cliFileName = CLI_TERM_IMPL;
        String outputFileName = "term.java";
        FileWriter outputFileWriter = new FileWriter(outputFileName);

        BufferedReader reader = new BufferedReader(new FileReader(cliFileName));
        String line = reader.readLine();

        int jdnumber = 0;
        String javadoc = null;
        boolean inJD = false;
        while (line != null)
        {
            if (line.contains("/**"))
            {
                jdnumber++;
                javadoc = line + "\n";
                // ignore class jd and constructor jd.
                if (jdnumber > 2)
                {
                    inJD = true;
                } else {
                    outputFileWriter.write(line + "\n");
                }
            } else if (inJD)
            {
                if (line.contains("*/"))
                {
                    javadoc = javadoc + line + "\n";
                    inJD = false;
                    line = reader.readLine();
                    // jump to the next line with content
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }
                    if (line.contains("@RequestMapping"))
                    {
                        line = reader.readLine();
                    }
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }

                    // should be the method line - it should look like this
                    //public  Term getTermByGuid(
                    StringTokenizer st = new StringTokenizer(line, " (");
                    int count = 0;
                    while (st.hasMoreTokens())
                    {
                        count++;
                        String token = st.nextToken();
                        if (count == 1 && token.equals("private"))
                        {
                            //ignore private methods
                            break;
                        } else if (count == 3)
                        {
                            String methodName = token;
//                            if (methodName.startsWith("replace") || methodName.startsWith("update") || methodName.startsWith("purge")) {
//                                outputFileWriter.write(javadoc);
//                                outputFileWriter.write(line +"\n");
//
                                // here is where we do the work!
                                String newJavaDoc = methodToSpringJDMap.get(methodName);
                                if (newJavaDoc== null) {
                                    // do not have a replacement so use what we have already.
                                     outputFileWriter.write(javadoc);
                                     outputFileWriter.write(line +"\n");
                                } else {
                                // replace @return
                                int cliindexOfReturn = javadoc.indexOf("@return");
                                String cliReturn = javadoc.substring(cliindexOfReturn);
                                int indexOfEndofReturn = cliReturn.indexOf("\n");
                                cliReturn = cliReturn.substring(0,indexOfEndofReturn);

                                newJavaDoc =newJavaDoc.replace("@return",cliReturn);

                                 outputFileWriter.write(newJavaDoc);
                                // remove any existing throws from the line
                                int indexOfThrows = line.indexOf("throws");
                                    String methodLine =null;
                                if (indexOfThrows ==-1) {
                                    methodLine = line;
                                } else
                                {
                                    methodLine = line.substring(0, indexOfThrows);
                                }
                                while (!line.contains("{"))
                                {
                                    line = reader.readLine();
                                }
                                outputFileWriter.write(methodLine + "\n");

                                StringTokenizer st2 = new StringTokenizer(newJavaDoc);
                                String throwsClause = "";
                                while (st2.hasMoreTokens())
                                {
                                    String jdToken = st2.nextToken();
                                    if (jdToken.startsWith("@throws")) {
                                        throwsClause= throwsClause+"       throws ";
                                        throwsClause= throwsClause+st2.nextToken() +",\n";
                                    }
                                }
                                throwsClause=throwsClause.substring(0,throwsClause.length()-2);
                                outputFileWriter.write(throwsClause+"\n");
                                outputFileWriter.write("       {\n");
                            }
                        }
                    }

                } else
                {
                    javadoc = javadoc + line + "\n";
                }
            } else {
                outputFileWriter.write(line + "\n");
            }
            line = reader.readLine();

        }
        reader.close();
        outputFileWriter.close();

    }

    private static Map<String, String> getMethodJDMapForCli(String fileName) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        Map<String, String> methodToSpringJDMap = new HashMap<>();
        int jdnumber = 0;
        String javadoc = null;
        boolean inJD = false;
        while (line != null)
        {
            if (line.contains("/**"))
            {
                jdnumber++;
                javadoc = line + "\n";
                // ignore class jd and constructor jd.
                if (jdnumber > 2)
                {
                    inJD = true;
                }
            } else if (inJD)
            {
                if (line.contains("@return"))
                {
                    javadoc = javadoc + "     * @return\n";
                } else if (line.contains("when not successful the following Exception responses can occur"))
                {
                    // ignore
                } else if (line.contains("*/"))
                {
                    javadoc = javadoc + line + "\n";
                    inJD = false;
                    line = reader.readLine();
                    // jump to the next line with content
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }
                    if (line.contains("@RequestMapping"))
                    {
                        line = reader.readLine();
                    }
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }

                    // should be the method line - it should look like this
                    //public  Term getTermByGuid(
                    StringTokenizer st = new StringTokenizer(line, " (");
                    int count = 0;
                    while (st.hasMoreTokens())
                    {
                        count++;
                        String token = st.nextToken();
                        if (count == 1 && token.equals("private"))
                        {
                            //ignore private methods
                            break;
                        } else if (count == 3)
                        {
                            String methodName = token;
                            if (!(methodName.contains("update") || methodName.contains("delete")))
                            {
                                methodToSpringJDMap.put(methodName, javadoc);
                            }
                        }
                    }

                } else
                {

                    line = line.replace("<li>", "@throws");
                    line = line.replace("</li>", "");
                    line  = line.replace("<ul>","");
                    line  = line.replace("</ul>"," Client library Exceptions\n"+
                    "     * @throws MetadataServerUncontactableException Unable to contact the server\n" +
                    "     * @throws UnexpectedResponseException an unexpected response was returned from the server");
                    javadoc = javadoc + line +"\n";
                }
            }
            line = reader.readLine();

        }
        reader.close();
        return methodToSpringJDMap;
    }
    private static Map<String, String> getMethodJDMapForServer(String fileName) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();

        Map<String, String> methodToSpringJDMap = new HashMap<>();
        int jdnumber = 0;
        String javadoc = null;
        boolean inJD = false;
        while (line != null)
        {
            if (line.contains("/**"))
            {
                jdnumber++;
                javadoc = line + "\n";
                // ignore class jd and constructor jd.
                if (jdnumber > 2)
                {
                    inJD = true;
                }
            } else if (inJD)
            {
               if (line.contains("*/"))
                {
                    javadoc = javadoc + line + "\n";
                    inJD = false;
                    line = reader.readLine();
                    // jump to the next line with content
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }
                    while (line.length() < 4)
                    {
                        line = reader.readLine();
                    }

                    // should be the method line - it should look like this
                    //public  Term getTermByGuid(
                    StringTokenizer st = new StringTokenizer(line, " (");
                    int count = 0;
                    while (st.hasMoreTokens())
                    {
                        count++;
                        String token = st.nextToken();
                        if (count == 1 && token.equals("private"))
                        {
                            //ignore private methods
                            break;
                        } else if (count == 3)
                        {
                            String methodName = token;
                            methodToSpringJDMap.put(methodName, javadoc);

                        }
                    }

                } else
                {
                   javadoc = javadoc + line +"\n";
                }
            }
            line = reader.readLine();

        }
        reader.close();
        return methodToSpringJDMap;
    }

    private static void generateAddGuidNotRecogonizedJD() throws IOException

    {
        String inputFileName = SUBJECTAREA_OMAS_CLIENT + "/src/main/java/org/odpi/openmetadata/accessservices/subjectarea/client/SubjectAreaRelationshipImpl.java";
        FileWriter outputFileWriter = new FileWriter("SubjectAreaRelationshipImpl.java");
        BufferedReader reader = new BufferedReader(new FileReader(inputFileName));
        String line = reader.readLine();
        boolean inJD = false;
        boolean inThrows = false;
        boolean gotUnRecGuid = false;
        while (line != null)
        {
            if (line.contains("/**"))
            {
                inJD = true;
                inThrows = false;
                gotUnRecGuid = false;
            }
            if (inJD)
            {
                if (line.contains("*/"))
                {
                    inJD = false;
                    inThrows = false;
                }

                if (line.contains("@throws") && !gotUnRecGuid)
                {
                    inThrows = true;
                    if (line.contains("UnrecognizedGUIDException"))
                    {
                        gotUnRecGuid = true;
                    }
                }
                if (!line.contains("@throws") && inThrows && !gotUnRecGuid)
                {
                    inThrows = false;
                    gotUnRecGuid = true;
                    outputFileWriter.write("     * @throws UnrecognizedGUIDException            the supplied guid was not recognised\n");
                }
            }
            outputFileWriter.write(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        outputFileWriter.close();
    }

    private static void generateDetectRelationships(String[] args) throws IOException
    {
        OmrsBeanModel omrsBeanModel = new OmrsBeanModel();

        SubjectAreaCodeUtilGenerator generator = new SubjectAreaCodeUtilGenerator("TestDetectTemplate");

        FileWriter outputFileWriter = new FileWriter("TestDetect.java");

        // generateClientSideRelationshipImpl

        String name = "translation";
        String description = "link between glossary terms that provide different natural language translation of the same concept";
        String url = "BASE_RELATIONSHIPS_TRANSLATION_URL";
        Map<String, String> replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "usedInContext";
        description = "link between glossary terms where on describes the context where the other one is valid to use";
        url = "BASE_RELATIONSHIPS_USED_IN_CONTEXT_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "preferredTerm";
        description = "link to an alternative term that the organization prefer is used";
        url = "BASE_RELATIONSHIPS_PREFERRED_TERM_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "validValue";
        description = "link between glossary terms where one defines one of the data values for the another";
        url = "BASE_RELATIONSHIPS_VALID_VALUE_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "replacementTerm";
        description = "link to a glossary term that is replacing an obsolete glossary term";
        url = "BASE_RELATIONSHIPS_REPLACEMENT_TERM_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "termTYPEDBYRelationship";
        description = "defines the relationship between a spine attribute and its type";
        url = "BASE_RELATIONSHIPS_TYPED_BY_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "isa";
        description = "link between a more general glossary term and a more specific definition";
        url = "BASE_RELATIONSHIPS_IS_A_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "TermISATypeOFRelationship";
        description = "defines an inheritance relationship between two spine objects";
        url = "BASE_RELATIONSHIPS_IS_A_TYPE_OF_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);

        outputFileWriter.close();
    }

    private static void generateFVT(String[] args) throws IOException
    {
        OmrsBeanModel omrsBeanModel = new OmrsBeanModel();

        SubjectAreaCodeUtilGenerator generator = new SubjectAreaCodeUtilGenerator("TestFVTTemplate");

        FileWriter outputFileWriter = new FileWriter("FVTFragment.java");

        // generateClientSideRelationshipImpl

        String name = "translation";
        String description = "link between glossary terms that provide different natural language translation of the same concept";
        String url = "BASE_RELATIONSHIPS_TRANSLATION_URL";
        Map<String, String> replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "usedInContext";
        description = "link between glossary terms where on describes the context where the other one is valid to use";
        url = "BASE_RELATIONSHIPS_USED_IN_CONTEXT_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "preferredTerm";
        description = "link to an alternative term that the organization prefer is used";
        url = "BASE_RELATIONSHIPS_PREFERRED_TERM_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "validValue";
        description = "link between glossary terms where one defines one of the data values for the another";
        url = "BASE_RELATIONSHIPS_VALID_VALUE_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "replacementTerm";
        description = "link to a glossary term that is replacing an obsolete glossary term";
        url = "BASE_RELATIONSHIPS_REPLACEMENT_TERM_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "termTYPEDBYRelationship";
        description = "defines the relationship between a spine attribute and its type";
        url = "BASE_RELATIONSHIPS_TYPED_BY_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "isa";
        description = "link between a more general glossary term and a more specific definition";
        url = "BASE_RELATIONSHIPS_IS_A_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "TermISATypeOFRelationship";
        description = "defines an inheritance relationship between two spine objects";
        url = "BASE_RELATIONSHIPS_IS_A_TYPE_OF_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);

        outputFileWriter.close();
    }


    public static void generateClientSideRelationshipImpl(String[] args) throws IOException
    {
        String templateName = "TestRelTemplate";
        SubjectAreaCodeUtilGenerator generator = new SubjectAreaCodeUtilGenerator(templateName);

        FileWriter outputFileWriter = new FileWriter("testout.java");

        // generateClientSideRelationshipImpl

        String name = "translation";
        String description = "link between glossary terms that provide different natural language translation of the same concept";
        String url = "BASE_RELATIONSHIPS_TRANSLATION_URL";
        Map<String, String> replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "usedInContext";
        description = "link between glossary terms where on describes the context where the other one is valid to use";
        url = "BASE_RELATIONSHIPS_USED_IN_CONTEXT_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "preferredTerm";
        description = "link to an alternative term that the organization prefer is used";
        url = "BASE_RELATIONSHIPS_PREFERRED_TERM_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "validValue";
        description = "link between glossary terms where one defines one of the data values for the another";
        url = "BASE_RELATIONSHIPS_VALID_VALUE_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "replacementTerm";
        description = "link to a glossary term that is replacing an obsolete glossary term";
        url = "BASE_RELATIONSHIPS_REPLACEMENT_TERM_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "termTYPEDBYRelationship";
        description = "defines the relationship between a spine attribute and its type";
        url = "BASE_RELATIONSHIPS_TYPED_BY_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "isa";
        description = "link between a more general glossary term and a more specific definition";
        url = "BASE_RELATIONSHIPS_IS_A_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);
        name = "TermISATypeOFRelationship";
        description = "defines an inheritance relationship between two spine objects";
        url = "BASE_RELATIONSHIPS_IS_A_TYPE_OF_URL";
        replacementMap = genMap(name, description, url);
        generator.generateOutput(outputFileWriter, replacementMap);

        outputFileWriter.close();

    }

    private static Map<String, String> genMap(String name, String description, String url)
    {
        Map<String, String> replacementMap = new HashMap();
        replacementMap.put("name", name);
        replacementMap.put("desc", description);
        replacementMap.put("uname", GeneratorUtilities.uppercase1stLetter(name));
        replacementMap.put("alluname", name.toUpperCase());
        replacementMap.put("url", url);
        return replacementMap;
    }

    /**
     * write out
     *
     * @throws IOException
     */
    private void generateOutput(FileWriter outputFileWriter, Map<String, String> replacementMap) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(templateName));
        String line = reader.readLine();
        while (line != null)
        {
            String newLine = replaceTokensInLineFromMap(replacementMap, line);
            outputFileWriter.write(newLine + "\n");
            line = reader.readLine();
        }
        reader.close();

    }

    private String replaceTokensInLineFromMap(Map<String, String> referenceMap, String newLine)
    {
        if (referenceMap != null)
        {
            for (String key : referenceMap.keySet())
            {
                if (referenceMap.get(key) != null)
                {
                    try
                    {
                        newLine = newLine.replaceAll(GeneratorUtilities.getRegexToken(key), referenceMap.get(key));
                    } catch (NullPointerException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return newLine;
    }


}
