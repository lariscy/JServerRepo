package com.github.lariscy.jserverrepo.shared.model;

/**
 * @author Steven Lariscy
 */
public class Node {
    
    private long id;
    private Long parentId;
    private String name;
    private String label;
    private Integer osTypeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
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

    public Integer getOsTypeId() {
        return osTypeId;
    }

    public void setOsTypeId(Integer osTypeId) {
        this.osTypeId = osTypeId;
    }
    
}
