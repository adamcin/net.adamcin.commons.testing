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
import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.sling.SlingClient;

/**
 * Deprecated after introduction of graniteit-maven-plugin
 */
@Deprecated
public interface SlingITContext {

    /**
     * a RequestBuilder that points to configured server
     * @return a RequestBuilder that points to configured server
     */
    RequestBuilder getRequestBuilder();

    /**
     * server base URL
     * @return server base URL
     */
    String getServerBaseUrl();

    /**
     * username configured for execution of HTTP requests
     * @return username configured for execution of HTTP requests
     */
    String getServerUsername();

    /**
     * password configured for execution of HTTP requests
     * @return password configured for execution of HTTP requests
     */
    String getServerPassword();

    /**
     * Sling JUnit Servlet Path
     * @return Sling JUnit Servlet Path
     */
    String getJunitServletPath();

    /**
     * the sling client
     * @return the sling client
     */
    SlingClient getSlingClient();

    /**
     * the underlying http client
     * @return the underlying httpclient
     */
    HttpClient getHttpClient();

    /**
     * the request executor
     * @return the request executor
     */
    RequestExecutor getRequestExecutor();
}
