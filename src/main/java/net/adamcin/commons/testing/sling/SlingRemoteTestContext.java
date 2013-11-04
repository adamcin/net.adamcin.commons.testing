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

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.sling.SlingClient;
import org.apache.sling.testing.tools.sling.SlingTestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simpler implementation of a context object which gets its values from
 * {@link System#getProperties()}, which may be set by the graniteit-maven-plugin
 */
public class SlingRemoteTestContext implements SlingITContext {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlingRemoteTestContext.class);
    public static final String TEST_SERVER_URL_PROP = SlingTestBase.TEST_SERVER_URL_PROP;
    public static final String TEST_SERVER_USERNAME = SlingTestBase.TEST_SERVER_USERNAME;
    public static final String TEST_SERVER_PASSWORD = SlingTestBase.TEST_SERVER_PASSWORD;
    public static final String SLING_JUNIT_PATH_PROP = "sling.junit.path";

    public static final String LOCALHOST_4502 = "http://localhost:4502";
    public static final String ADMIN = "admin";

    private final String serverBaseUrl;
    private final String serverUsername;
    private final String serverPassword;
    private final SlingClient slingClient;
    private RequestBuilder builder;

    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private RequestExecutor executor = new RequestExecutor(httpClient);

    public SlingRemoteTestContext() {
        final String configuredUrl = this.getConfiguredUrl();
        if(configuredUrl != null) {
            serverBaseUrl = configuredUrl;
        } else {
            serverBaseUrl = LOCALHOST_4502;
        }

        // Set configured username using "admin" as default credential
        final String configuredUsername = this.getConfiguredUsername();
        if (configuredUsername != null && configuredUsername.trim().length() > 0) {
            serverUsername = configuredUsername;
        } else {
            serverUsername = ADMIN;
        }

        // Set configured password using "admin" as default credential
        final String configuredPassword = this.getConfiguredPassword();
        if (configuredPassword != null && configuredPassword.trim().length() > 0) {
            serverPassword = configuredPassword;
        } else {
            serverPassword = ADMIN;
        }

        builder = new RequestBuilder(serverBaseUrl);
        slingClient = new SlingClient(getServerBaseUrl(), serverUsername, serverPassword);
    }

    public String getConfiguredUrl() {
        return System.getProperty(TEST_SERVER_URL_PROP);
    }

    public String getConfiguredUsername() {
        return System.getProperty(TEST_SERVER_USERNAME);
    }

    public String getConfiguredPassword() {
        return System.getProperty(TEST_SERVER_PASSWORD);
    }

    public String getJunitServletPath() {
        return System.getProperty(SLING_JUNIT_PATH_PROP);
    }

    public RequestBuilder getRequestBuilder() {
        return this.builder;
    }

    public String getServerBaseUrl() {
        return this.serverBaseUrl;
    }

    public String getServerUsername() {
        return this.serverUsername;
    }

    public String getServerPassword() {
        return this.serverPassword;
    }

    public SlingClient getSlingClient() {
        return this.slingClient;
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public RequestExecutor getRequestExecutor() {
        return this.executor;
    }


}
