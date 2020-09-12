package com.atguigu.teamUpgrade.service;

public enum Status {
    FREE("FREE"),
    BUSY("BUSY"),
    VOCATIN("VOCATIN");
    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
