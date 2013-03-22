package net.adamcin.commons.testing.sling;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceWrapper;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MockResource extends ResourceWrapper {

    private String name;
    private List<Resource> children;

    public MockResource(Resource resource, String name) {
        super(resource);
        this.name = name;
        this.children = new LinkedList<Resource>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Iterator<Resource> listChildren() {
        return this.children.iterator();
    }

    public void addChild(MockResource child) {
        this.children.add(child);
    }
}