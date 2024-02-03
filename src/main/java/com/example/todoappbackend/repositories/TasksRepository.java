package com.example.todoappbackend.repositories;

import com.example.todoappbackend.models.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TasksRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    public TasksRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("tasks")
                .usingGeneratedKeyColumns("id");
    }

    public List<Task> getAll() {
        String sqlStatement = "SELECT * FROM tasks";

        return jdbcTemplate.query(sqlStatement, (rs, rowNum) -> {
            Task task = new Task();
            task.setId(rs.getInt("id"));
            task.setText(rs.getString("text"));
            return task;
        });
    }

    public Task addOne(Task task) {
        Map<String, Object> parameters = new HashMap<>(1);
        parameters.put("text", task.getText());

        Number newTaskId = simpleJdbcInsert.executeAndReturnKey(parameters);
        task.setId(newTaskId.intValue());

        return task;
    }

    public void deleteOne(Task task) {
        String sqlStatement = "DELETE FROM tasks WHERE id = ?";

        jdbcTemplate.update(sqlStatement, task.getId());
    }

    public Task updateOne(Task task) {
        String sqlStatement = "UPDATE tasks SET text = ? WHERE id = ?";

        jdbcTemplate.update(sqlStatement,
                task.getText(),
                task.getId());

        return task;
    }
}
