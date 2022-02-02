package com.nmurray.todorestdemo.data.repositories;

import com.nmurray.todorestdemo.data.entities.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
