package net.adamcin.commons.testing.sling;

import org.apache.http.client.HttpClient;
import org.apache.sling.junit.remote.testrunner.SlingRemoteTestParameters;
import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.sling.SlingClient;

public abstract class SlingRemoteTestBase implements SlingRemoteTestParameters {

    protected SlingRemoteTestContext context;

    protected SlingRemoteTestBase() {
        this.context = new SlingRemoteTestContext();
    }

    public String getJunitServletUrl() {
        return context.getServerBaseUrl() + context.getJunitServletPath();
    }

    /** return a RequestBuilder that points to configured server */
    protected RequestBuilder getRequestBuilder() {
        return context.getRequestBuilder();
    }

    /** return server base URL */
    protected String getServerBaseUrl() {
        return context.getServerBaseUrl();
    }

    /** Return username configured for execution of HTTP requests */
    protected String getServerUsername() {
        return context.getServerUsername();
    }

    /** Return password configured for execution of HTTP requests */
    protected String getServerPassword() {
        return context.getServerPassword();
    }

    protected SlingClient getSlingClient() {
        return context.getSlingClient();
    }

    protected HttpClient getHttpClient() {
        return context.getHttpClient();
    }

    protected RequestExecutor getRequestExecutor() {
        return context.getRequestExecutor();
    }
}
