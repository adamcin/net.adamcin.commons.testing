package net.adamcin.commons.testing.sling;


import org.apache.sling.testing.tools.sling.SlingTestBase;

@Deprecated
public class VltpackITContext extends SlingRemoteTestContext {

    public static final String SERVER_HOSTNAME_PROP = SlingTestBase.SERVER_HOSTNAME_PROP;

    @Deprecated
    public String getConfiguredHostname() {
        return System.getProperty(SERVER_HOSTNAME_PROP);
    }
}
