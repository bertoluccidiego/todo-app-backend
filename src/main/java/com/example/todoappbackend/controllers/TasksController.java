package com.example.todoappbackend.controllers;

import com.example.todoappbackend.models.Task;
import com.example.todoappbackend.services.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final Logger logger = Logger.getLogger(TasksController.class.getName());
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping
    public List<Task> getAll() {
        logger.info("Fetching all tasks");

        return tasksService.getAll();
    }

    @PostMapping
    public Task addOne(@RequestBody Task newTask) {
        logger.info("New task text: " + newTask.getText());

        return tasksService.addOne(newTask.getText());
    }

    @DeleteMapping
    public ResponseEntity deleteOne(@RequestParam String taskId) {
        logger.info("Deleting task with id: " + taskId);

        tasksService.deleteOne(taskId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping
    public Task updateOne(@RequestBody Task updatedTask) {
        logger.info("Updating the task with id: " + updatedTask.getId()
                        + " with the text: \"" + updatedTask.getText() + "\"");

        return tasksService.updateOne(updatedTask);
    }
}
