/*
 * @(#)SlingPostResponseTest Dec 02, 2011
 *
 * COPYRIGHT (c) 2011 by Recreational Equipment Incorporated. All rights
 * reserved.
 */

package net.adamcin.commons.testing.sling;

import net.adamcin.commons.testing.junit.TestBody;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author madamcin
 * @version $Id: SlingPostResponseTest.java$
 */
public class SlingPostResponseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SlingPostResponseTest.class);
    
    @Test
    public void testSlingPostResponseFromInputStream() {
        TestBody.test(new TestBody() {
            InputStream testFooBarFooResponseHtmlInputStream = null;

            @Override
            protected void execute() throws Exception {
                testFooBarFooResponseHtmlInputStream =
                        this.getClass().getResourceAsStream("/test_foo_bar_foo_response.html");
                SlingPostResponse response =
                        SlingPostResponse.createFromInputStream(testFooBarFooResponseHtmlInputStream, "UTF-8");

                assertEquals("Check Status: ",
                        "201",
                        response.getStatus());
                assertEquals("Check Message: ",
                        "Created",
                        response.getMessage());
                assertEquals("Check Location: ",
                        "/test/foo/bar/foo",
                        response.getLocation());
                assertEquals("Check ParentLocation: ",
                        "/test/foo/bar",
                        response.getParentLocation());
                assertEquals("Check Path: ",
                        "/test/foo/bar/foo",
                        response.getPath());
                assertEquals("Check Referer: ",
                        "",
                        response.getReferer());

                List<SlingPostResponse.Change> changes = response.getChangeLog();
                assertNotNull("Check ChangeLog not null: ", changes);

                assertEquals("Check ChangeLog has 4 changes",
                        4,
                        changes.size());
                assertEquals("Check first change type is 'created'",
                        "created",
                        changes.get(0).getType());
                assertEquals("Check n arguments of last change is 1",
                        1,
                        changes.get(3).getArguments().length);
                assertEquals("Check first argument of last change is /test/foo/bar/foo/sling:resourceType with no quotes",
                        "/test/foo/bar/foo/sling:resourceType", changes.get(3).getArguments()[0]);
            }

            @Override
            protected void cleanUp() {
                IOUtils.closeQuietly(testFooBarFooResponseHtmlInputStream);
            }
        });
    }

}
