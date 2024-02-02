package com.example.todoappbackend.repositories;

import com.example.todoappbackend.models.Task;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class TasksRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

    public TasksRepository(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tasks")
                .usingGeneratedKeyColumns("id");
    }

    public Task addOne(Task task) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("text", task.getText());

        Number newTaskId = simpleJdbcInsert.executeAndReturnKey(parameters);
        task.setId(newTaskId.intValue());

        return task;
    }
}
