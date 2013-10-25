package net.adamcin.commons.testing.sling;


import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.sling.testing.tools.http.RequestBuilder;
import org.apache.sling.testing.tools.http.RequestExecutor;
import org.apache.sling.testing.tools.sling.SlingClient;
import org.apache.sling.testing.tools.sling.SlingTestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class VltpackITContext extends GraniteITContext {

    public static final String SERVER_HOSTNAME_PROP = SlingTestBase.SERVER_HOSTNAME_PROP;

    @Deprecated
    public String getConfiguredHostname() {
        return System.getProperty(SERVER_HOSTNAME_PROP);
    }
}
