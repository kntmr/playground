package com.example.demo.type;

public class User {

    private int id;
    private String name;
    private int groupId;

    public User(int id, String name, int groupId) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGroupId() {
        return groupId;
    }

}
