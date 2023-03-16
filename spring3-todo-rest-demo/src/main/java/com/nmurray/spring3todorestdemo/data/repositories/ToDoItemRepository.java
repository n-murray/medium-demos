package com.nmurray.spring3todorestdemo.data.repositories;

import com.nmurray.spring3todorestdemo.data.entities.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Nicholas Murray
 *
 * A MongoRepository for the {@link ToDoItem } entity to carry out CRUD operations in a
 * MongoDB database
 */
public interface ToDoItemRepository extends MongoRepository<ToDoItem, String> {
}
