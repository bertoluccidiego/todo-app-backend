package com.example.todoappbackend.repositories;


import com.example.todoappbackend.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends CrudRepository<Task, Long> {
}
