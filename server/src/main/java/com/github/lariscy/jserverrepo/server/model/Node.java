package com.github.lariscy.jserverrepo.server.model;

/**
 * @author Steven Lariscy
 */
public class Node {
    
    private long id;
    private long parentId;
    private String name;
    private String label;
    private int osTypeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getOsTypeId() {
        return osTypeId;
    }

    public void setOsTypeId(int osTypeId) {
        this.osTypeId = osTypeId;
    }
    
}
