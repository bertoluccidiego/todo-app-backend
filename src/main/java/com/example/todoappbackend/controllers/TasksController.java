package com.example.todoappbackend.controllers;

import com.example.todoappbackend.models.Task;
import com.example.todoappbackend.repositories.TasksRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final Logger logger = Logger.getLogger(TasksController.class.getName());
    private final TasksRepository tasksRepository;

    public TasksController(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @GetMapping
    public Iterable<Task> getAll() {
        logger.info("Fetching all tasks");

        return tasksRepository.findAll();
    }

    @PostMapping
    public Task addOne(@RequestBody Task newTask) {
        logger.info("New task text: " + newTask.getText());

        return tasksRepository.save(newTask);
    }

    @DeleteMapping
    public ResponseEntity deleteOne(@RequestBody Task taskToDelete) {
        logger.info("Deleting task with id: " + taskToDelete.getId());

        tasksRepository.deleteById(taskToDelete.getId());
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping
    public Task updateOne(@RequestBody Task updatedTask) {
        logger.info("Updating the task with id: " + updatedTask.getId()
                        + " with the text: \"" + updatedTask.getText() + "\"");

        return tasksRepository.save(updatedTask);
    }
}
