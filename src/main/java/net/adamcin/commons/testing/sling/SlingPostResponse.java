
/*
 * This is free and unencumbered software released into the public domain.
 *
 * Anyone is free to copy, modify, publish, use, compile, sell, or
 * distribute this software, either in source code form or as a compiled
 * binary, for any purpose, commercial or non-commercial, and by any
 * means.
 *
 * In jurisdictions that recognize copyright laws, the author or authors
 * of this software dedicate any and all copyright interest in the
 * software to the public domain. We make this dedication for the benefit
 * of the public at large and to the detriment of our heirs and
 * successors. We intend this dedication to be an overt act of
 * relinquishment in perpetuity of all present and future rights to this
 * software under copyright law.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * For more information, please refer to <http://unlicense.org/>
 */

package net.adamcin.commons.testing.sling;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple POJO extracted from a Sling POST HTML Response
 */
public class SlingPostResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlingPostResponse.class);

    private String status;
    private String message;
    private String location;
    private String parentLocation;
    private String path;
    private String referer;
    private List<Change> changeLog;

    static final String XPATH_ID_STATUS = "//*[@id='Status']/text()";
    static final String XPATH_ID_MESSAGE = "//*[@id='Message']/text()";
    static final String XPATH_ID_LOCATION = "//*[@id='Location']/text()";
    static final String XPATH_ID_PARENT_LOCATION = "//*[@id='ParentLocation']/text()";
    static final String XPATH_ID_PATH = "//*[@id='Path']/text()";
    static final String XPATH_ID_REFERER = "//*[@id='Referer']/text()";
    static final String XPATH_ID_CHANGE_LOG = "//*[@id='ChangeLog']/pre/text()";

    SlingPostResponse() {
        // discourage direct instantiation
    }

    public static SlingPostResponse createFromInputStream(InputStream stream, String encoding)
            throws IOException {

        InputSource source = new InputSource(new BufferedInputStream(stream));
        source.setEncoding(encoding == null ? "UTF-8" : encoding);

        SlingPostResponse postResponse = new SlingPostResponse();

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(source);

            postResponse.setStatus((String)xpath.compile(XPATH_ID_STATUS).evaluate(document, XPathConstants.STRING));
            postResponse.setMessage((String)xpath.compile(XPATH_ID_MESSAGE).evaluate(document, XPathConstants.STRING));
            postResponse.setLocation((String)xpath.compile(XPATH_ID_LOCATION).evaluate(document, XPathConstants.STRING));
            postResponse.setParentLocation((String)xpath.compile(XPATH_ID_PARENT_LOCATION).evaluate(document, XPathConstants.STRING));
            postResponse.setPath((String)xpath.compile(XPATH_ID_PATH).evaluate(document, XPathConstants.STRING));
            postResponse.setReferer((String)xpath.compile(XPATH_ID_REFERER).evaluate(document, XPathConstants.STRING));

            List<Change> changes = new ArrayList<Change>();

            NodeList changeLogNodes = (NodeList)xpath.compile(XPATH_ID_CHANGE_LOG).evaluate(document, XPathConstants.NODESET);

            if (changeLogNodes != null) {
                for (int i = 0; i < changeLogNodes.getLength(); i++) {
                    String rawChange = changeLogNodes.item(i).getTextContent();
                    rawChange = rawChange.substring(0, rawChange.length() - 2);
                    String[] rawChangeParts = rawChange.split("\\(", 2);
                    if (rawChangeParts.length != 2) {
                        continue;
                    }
                    String changeType = rawChangeParts[0];
                    String[] rawArguments = rawChangeParts[1].split(", ");
                    List<String> arguments = new ArrayList<String>();
                    for (String rawArgument : rawArguments) {
                        arguments.add(rawArgument.substring(1, rawArgument.length() - 1));
                    }

                    Change change = new Change(changeType, arguments.toArray(new String[arguments.size()]));
                    changes.add(change);
                }
            }

            postResponse.setChangeLog(changes);

        } catch (XPathExpressionException e) {
            LOGGER.error("Failed to evaluate xpath statement.", e);
            throw new IOException("Failed to evaluate xpath statement.", e);
        } catch (ParserConfigurationException e) {
            LOGGER.error("Failed to create DocumentBuilder.", e);
            throw new IOException("Failed to create DocumentBuilder.", e);
        } catch (SAXException e) {
            LOGGER.error("Failed to create Document.", e);
            throw new IOException("Failed to create Document.", e);
        }

        LOGGER.info("Returning post response");
        return postResponse;
    }

    /**
     * Don't use this method if you are using a RequestExecutor (as in a SlingTestBase-derived class).
     * The HttpResponse returned by getResponse() has already been consumed, and therefore, you
     * should use the #createFromString() method to parse the string returned from
     * getRequestExecutor().getContent()
     * @param response the response
     * @return a SlingPostResponse
     * @throws java.io.IOException response content type is not text/html
     */
    public static SlingPostResponse createFromHttpResponse(HttpResponse response)
            throws IOException {

        if (response == null || !response.getEntity().getContentType().getValue().startsWith("text/html")) {
            throw new IOException("Invalid HttpResponse Entity - Response Content Type: " +
                    (response == null ? "null" : response.getEntity().getContentType().getValue()));
        }

        return createFromInputStream(response.getEntity().getContent(), "UTF-8");
    }

    public static SlingPostResponse createFromString(String responseContent)
            throws IOException {

        return createFromInputStream(new ByteArrayInputStream(responseContent.getBytes("UTF-8")), "UTF-8");
    }

    public String getStatus() {
        return status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    protected void setLocation(String location) {
        this.location = location;
    }

    public String getParentLocation() {
        return parentLocation;
    }

    protected void setParentLocation(String parentLocation) {
        this.parentLocation = parentLocation;
    }

    public String getPath() {
        return path;
    }

    protected void setPath(String path) {
        this.path = path;
    }

    public String getReferer() {
        return referer;
    }

    protected void setReferer(String referer) {
        this.referer = referer;
    }

    public List<Change> getChangeLog() {
        return changeLog;
    }

    protected void setChangeLog(List<Change> changeLog) {
        this.changeLog = changeLog;
    }

    static class Change {
        String type;
        String[] arguments;

        Change(String type, String[] arguments) {
            this.type = type;
            this.arguments = arguments;
        }

        public String getType() {
            return type;
        }

        public String[] getArguments() {
            return arguments;
        }
    }
}
