package com.amazon.pojos;

public class Task {
    private Integer taskID;
    private String title;
    private String description;
    private Boolean completed;

    private Integer userID;

    public Task() {
    }

    public Task(Integer taskID, String title, String description, Boolean completed, Integer userID) {
        this.taskID = taskID;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userID = userID;
    }

    public Task(String title, String description, Boolean completed, Integer userID) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.userID = userID;
    }

    public Task(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
