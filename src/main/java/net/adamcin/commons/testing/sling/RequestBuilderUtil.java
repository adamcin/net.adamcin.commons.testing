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

public final class RequestBuilderUtil {

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
