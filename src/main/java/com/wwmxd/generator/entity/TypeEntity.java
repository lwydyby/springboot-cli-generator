package com.wwmxd.generator.entity;

public class TypeEntity {
    private String name;
    private String type;
    private String Bname;

    public String getBname() {
        return Bname;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TypeEntity{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
