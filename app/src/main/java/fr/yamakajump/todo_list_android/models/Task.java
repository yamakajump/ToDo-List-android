package fr.yamakajump.todo_list_android.models;

import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String description;
    private int duration;
    private String date;
    private String context;

    public Task(String title, String description, int duration, String date, String context) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.context = context;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }
}