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
