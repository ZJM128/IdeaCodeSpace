package com.atguigu.team.domain;

public enum positionName {

    PROGRAMMER("程序员"),
    DESIGNER("设计师"),
    ARCHITECT("架构师");
    private String name;

    positionName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
