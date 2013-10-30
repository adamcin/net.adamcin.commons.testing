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

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;

public class MockResourceResolver implements ResourceResolver {

    public Resource resolve(HttpServletRequest request, String absPath) { return null; } 
    public Resource resolve(String absPath) { return null; } 
    public Resource resolve(HttpServletRequest request) { return null; } 
    public String map(String resourcePath) { return null; } 
    public String map(HttpServletRequest request, String resourcePath) { return null; } 
    public Resource getResource(String path) { return null; } 
    public Resource getResource(Resource base, String path) { return null; } 
    public String[] getSearchPath() { return new String[0]; } 
    public Iterator<Resource> listChildren(Resource parent) { return null; } 
    public Iterator<Resource> findResources(String query, String language) { return null; } 
    public Iterator<Map<String, Object>> queryResources(String query, String language) { return null; } 
    public ResourceResolver clone(Map<String, Object> authenticationInfo) throws LoginException { return null; } 
    public boolean isLive() { return false; } 
    public void close() { } 
    public String getUserID() { return null; } 
    public Iterator<String> getAttributeNames() { return null; } 
    public Object getAttribute(String name) { return null; } 
    public <AdapterType> AdapterType adaptTo(Class<AdapterType> type) { return null; }
}
