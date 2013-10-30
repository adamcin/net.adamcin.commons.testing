
package net.adamcin.commons.testing.sling;

import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.retry.RetryLoop;

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
