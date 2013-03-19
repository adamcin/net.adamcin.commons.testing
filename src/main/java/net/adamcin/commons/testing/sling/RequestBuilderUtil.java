/*
 * @(#)RequestBuilderUtil Dec 02, 2011
 *
 * COPYRIGHT (c) 2011 by Recreational Equipment Incorporated. All rights
 * reserved.
 */

package net.adamcin.commons.testing.sling;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.sling.testing.tools.sling.SlingTestBase;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author madamcin
 * @version $Id: RequestBuilderUtil.java$
 */
public final class RequestBuilderUtil {
    
    public static final String PROP_USER = SlingTestBase.TEST_SERVER_USERNAME;
    public static final String PROP_PASS = SlingTestBase.TEST_SERVER_PASSWORD;
    public static final String USER;
    public static final String PASS;
    
    static {
        final String configuredUser = System.getProperty(PROP_USER);
        if (configuredUser != null && configuredUser.trim().length() > 0) {
            USER = configuredUser;
        } else {
            USER = SlingTestBase.ADMIN;
        }

        final String configuredPass = System.getProperty(PROP_PASS);
        if (configuredPass != null && configuredPass.trim().length() > 0) {
            PASS = configuredPass;
        } else {
            PASS = SlingTestBase.ADMIN;
        }
    }

    private RequestBuilderUtil() {
        // prevent instantiation
    }

    public static void setMultipartParamsFromProps (MultipartEntity entity, Map<String, Object> props)
            throws UnsupportedEncodingException {
        if (entity != null) {
            if (props != null) {
                for (Map.Entry<String, Object> e : props.entrySet()) {
                    entity.addPart(e.getKey(), new StringBody(e.getValue().toString()));
                }
            }
        }
    }
}
