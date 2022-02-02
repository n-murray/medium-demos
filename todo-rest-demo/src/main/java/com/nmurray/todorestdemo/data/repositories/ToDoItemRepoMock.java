package com.nmurray.todorestdemo.data.repositories;

import com.nmurray.todorestdemo.data.entities.ToDoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nicholas Murray
 * A mock repository for testing without a connected MongoDB
 */
public class ToDoItemRepoMock {

    /**
     * A list to represent our database
     */
    private List<ToDoItem> repository = new ArrayList<>();

    /**
     * Default constructor
     */
    public ToDoItemRepoMock() {
    }

    /**
     * Saves the given entity in the mock database
     * @param newToDo - the new entity to be persisted
     * @return - a copy of the saved entity
     */
    public ToDoItem save(ToDoItem newToDo) {
        int index = -99;
        //Check if the newToDo has an ID, if so check in the repository for
        //an existing entity with the same ID
        if (newToDo.getId() != null) {
            index = findIdInRepo(newToDo.getId());
        }

        //If we found an existing entity with the same ID update it instead
        //of creating a new one
        if (index != -99) {
            repository.get(index).setCompleted(newToDo.isCompleted());
            repository.get(index).setDescription(newToDo.getDescription());
            return repository.get(index);
        } else {
            Integer id = repository.size() + 1;
            newToDo.setId(id.toString());
            repository.add(newToDo);
            return newToDo;
        }
    }

    /**
     * Returns the list of all entities from the mock database
     * @return - all saved entities
     */
    public List<ToDoItem> findAll() {
        return repository;
    }

    /**
     * Deletes the entity with the given ID
     * @param id - the ID of the entity to be deleted
     * @throws IllegalArgumentException - when the given ID doesn't exist
     */
    public void deleteById(String id) {
        int index = findIdInRepo(id);
        if(index != -99) {
            repository.remove(index);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Check in the repository for an entity with the given ID
     */
    private int findIdInRepo(String id) {
        for( int i = 0; i <= repository.size(); i++) {
            if(repository.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -99;
    }
}
