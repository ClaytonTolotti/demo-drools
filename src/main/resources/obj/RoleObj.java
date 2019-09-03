package com.clayton.drools.obj;

public class RoleObj {

    private Long id;
    private String name;

    public RoleObj() {
    }

    public RoleObj(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
