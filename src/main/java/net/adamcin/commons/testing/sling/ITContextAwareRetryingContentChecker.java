
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

import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.retry.RetryLoop;

/**
 * Deprecated with introduction of graniteit-maven-plugin, which provides this
 * functionality natively
 */
@Deprecated
public class ITContextAwareRetryingContentChecker {
    private final RequestExecutor executor;
    private final RequestBuilder builder;
    private final String username;
    private final String password;

    public ITContextAwareRetryingContentChecker(SlingITContext context) {
        this.executor = context.getRequestExecutor();
        this.builder = context.getRequestBuilder();
        this.username = context.getServerUsername();
        this.password = context.getServerPassword();
    }

    /** Check specified path for expected status, or timeout */
    public void check(final String path, final int expectedStatus, int timeoutSeconds, int intervalBetweenrequestsMsec) {
        final RetryLoop.Condition c = new RetryLoop.Condition() {
            public String getDescription() {
                return "Expecting " + path + " to return HTTP status " + expectedStatus;
            }

            public boolean isTrue() throws Exception {
                executor.execute(builder.buildGetRequest(path)
                        .withCredentials(username, password))
                        .assertStatus(expectedStatus);
                return assertMore(executor);
            }

        };
        new RetryLoop(c, timeoutSeconds, intervalBetweenrequestsMsec) {
            @Override
            protected void onTimeout() {
                ITContextAwareRetryingContentChecker.this.onTimeout();
            }
        };
    }

    /** Optionally perform additional tests in retry condition */
    protected boolean assertMore(RequestExecutor executor) throws Exception {
        return true;
    }

    /** Called if a timeout occurs */
    protected void onTimeout() {
    }
}
