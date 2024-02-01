package com.example.todoappbackend.models;

import java.util.Objects;

public class Task {

    private final String id;
    private String text;

    public Task(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
