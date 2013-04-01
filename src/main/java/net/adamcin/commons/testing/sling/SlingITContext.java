package net.adamcin.commons.testing.sling;

import org.apache.http.client.HttpClient;
import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.sling.SlingClient;

public interface SlingITContext {

    /** return a RequestBuilder that points to configured server */
    RequestBuilder getRequestBuilder();

    /** return server base URL */
    String getServerBaseUrl();

    /** Return username configured for execution of HTTP requests */
    String getServerUsername();

    /** Return password configured for execution of HTTP requests */
    String getServerPassword();

    SlingClient getSlingClient();

    HttpClient getHttpClient();

    RequestExecutor getRequestExecutor();
}