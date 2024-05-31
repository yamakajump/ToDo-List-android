package fr.yamakajump.todo_list_android.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tasks")
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String title;
    private final String description;
    private final int duration;
    private final String date;
    private final String context;

    public Task(String title, String description, int duration, String date, String context) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.date = date;
        this.context = context;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public String getContext() {
        return context;
    }
}
