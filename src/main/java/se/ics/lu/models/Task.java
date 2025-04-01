package se.ics.lu.models;

import java.time.LocalDate;

import se.ics.lu.enums.StatusTypes;

public class Task implements Cloneable {
    private int id;
    private String description;
    private String status = StatusTypes.PROGRESS.toString();
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt;

    public Task(int id, String description, String status) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.updatedAt = LocalDate.now();
    }

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.updatedAt = LocalDate.now();
    }

    public Task() {
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt() {
        this.updatedAt = LocalDate.now();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }

}