package com.nmurray.todorestdemo.rest.resources;

import com.nmurray.todorestdemo.data.entities.ToDoItem;
import com.nmurray.todorestdemo.data.repositories.ToDoItemRepoMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nichoals Murray
 *
 * The REST controller class for the {@link ToDoItem} entity. This is used to carry out CRUD operations
 * over HTTP(S).
 */
@RestController
@RequestMapping("/todo")
public class ToDoResource {

    /**
     * Uncomment the below when using with real MongoDB database and remove
     * the ToDoRepoMock below
     *
     * @Autowired
     * private ToDoItemRepository repository;
     */
    private ToDoItemRepoMock repository = new ToDoItemRepoMock();

    /**
     * Returns a list of all persisted {@link ToDoItem} in the repository
     * @return - HTTP Response with the list in it's body.
     */
    @GetMapping
    public ResponseEntity<?> getAllToDos() {
        List<ToDoItem> toDoList = repository.findAll();
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }

    /**
     * Persists the given {@link ToDoItem } in the repository
     * @param newToDo - the new entity to be persisted
     * @return - The newly persisted entity.
     */
    @PostMapping
    public ResponseEntity<?> saveToDo(@RequestBody ToDoItem newToDo) {
        ToDoItem persistedToDo = repository.save(newToDo);
        return new ResponseEntity<>(persistedToDo, HttpStatus.CREATED);
    }

    /**
     * Updates a given {@link ToDoItem } in the repository, if it already exists
     * else it will create a new persisted entity.
     * @param updatedToDo - the entity to be updated/persisted
     * @return - The updated/new persisted entity.
     */
    @PutMapping
    public ResponseEntity<?> updateToDo(@RequestBody ToDoItem updatedToDo) {
        ToDoItem persistedToDo = repository.save(updatedToDo);
        return new ResponseEntity<>(persistedToDo, HttpStatus.CREATED);
    }

    /**
     * Deletes the {@link ToDoItem } with the given ID, if one exists.
     * @param id - the ID of the entity to be removed
     * @return HTTP response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable String id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
