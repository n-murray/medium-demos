package com.nmurray.todorestdemo.rest.resources;

import com.nmurray.todorestdemo.data.entities.ToDo;
import com.nmurray.todorestdemo.data.repositories.ToDoRepoMock;
import com.nmurray.todorestdemo.data.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoResource {

    /**
     * Uncomment the below when using with real MongoDB database and remove
     * the ToDoRepoMock below
     *
     * @Autowired
     * private ToDoRepository repository;
     */
    private ToDoRepoMock repository = new ToDoRepoMock();

    @GetMapping
    public ResponseEntity<?> getAllToDos() {
        List<ToDo> toDoList = repository.findAll();
        return new ResponseEntity<>(toDoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveToDo(@RequestBody ToDo newToDo) {
        ToDo persistedToDo = repository.save(newToDo);
        return new ResponseEntity<>(persistedToDo, HttpStatus.OK);
    }

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
