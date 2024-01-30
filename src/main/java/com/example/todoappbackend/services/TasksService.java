package com.example.todoappbackend.services;

import com.example.todoappbackend.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TasksService {

    private final List<Task> taskList;

    public TasksService() {
        this.taskList = new ArrayList<>();

        // Temporary pre-added tasks
        taskList.add(new Task(UUID.randomUUID().toString(), "Pre-made task 1"));
        taskList.add(new Task(UUID.randomUUID().toString(), "Pre-made task 2"));
        taskList.add(new Task(UUID.randomUUID().toString(), "Pre-made task 3"));
    }

    public List<Task> getAll() {
        return taskList;
    }

    public Task addOne(String taskText) {
        Task newTask = new Task(UUID.randomUUID().toString(), taskText);
        taskList.add(newTask);
        return newTask;
}
}
