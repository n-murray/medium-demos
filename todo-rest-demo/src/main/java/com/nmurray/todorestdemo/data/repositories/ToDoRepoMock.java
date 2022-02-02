package com.nmurray.todorestdemo.data.repositories;

import com.nmurray.todorestdemo.data.entities.ToDo;

import java.util.ArrayList;
import java.util.List;

public class ToDoRepoMock {

    /**
     * A list to represent our database
     */
    private List<ToDo> repository = new ArrayList<>();

    /**
     * Default constructor
     */
    public ToDoRepoMock() {
    }

    /**
     * Saves the given entity in the mock database
     * @param newToDo - the new entity to be persisted
     * @return - a copy of the saved entity
     */
    public ToDo save(ToDo newToDo) {
        Integer id = repository.size()+1;
        newToDo.setId(id.toString());
        repository.add(newToDo);
        return newToDo;
    }

    /**
     * Returns the list of all entities from the mock database
     * @return - all saved entities
     */
    public List<ToDo> findAll() {
        return repository;
    }

    /**
     * Deletes the entity with the given ID
     * @param id - the ID of the entity to be deleted
     * @throws IllegalArgumentException - when the given ID doesn't exist
     */
    public void deleteById(String id) {
        int index = -99;
        boolean found = false;
        for( int i = 0; i <= repository.size(); i++) {
            if(repository.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        if(index != -99) {
            repository.remove(index);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
