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
