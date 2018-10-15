package com.example.demo.type;

public class ToDo {

    private int id;
    private String content;
    private boolean completed;
    private int userId;

    public ToDo(int id, String content, boolean completed, int userId) {
        this.id = id;
        this.content = content;
        this.completed = completed;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getUserId() {
        return userId;
    }

}
