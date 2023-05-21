package org.example.springbasics.entities;

import java.util.Date;
import java.util.Formatter;

public class Task {

    Integer id;
    String title;
    String description;
    Date taskDate;

    public Task(Integer id, String title, String description, Date taskDate){
        this.id = id;
        this.title = title;
        this.description = description;
        this.taskDate = taskDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}
