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
import org.apache.sling.junit.remote.testrunner.SlingRemoteTestParameters;
import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.sling.SlingClient;

/**
 * Abstract class intended to be used as the basis for client-side Sling Junit Framework
 * IT classes which call remote Sling Tests
 */
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
