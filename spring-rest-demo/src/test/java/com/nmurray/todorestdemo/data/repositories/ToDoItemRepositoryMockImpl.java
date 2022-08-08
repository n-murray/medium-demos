package com.nmurray.todorestdemo.data.repositories;

import com.nmurray.todorestdemo.data.entities.ToDoItem;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ToDoItemRepositoryMockImpl implements ToDoItemRepository {

    /**
     * A list to represent our database
     */
    private List<ToDoItem> repository = new ArrayList<>();

    /**
     * Check in the repository for an entity with the given ID
     */
    private int findIdInRepo(String id) {
        try {
            for (int i = 0; i <= repository.size(); i++) {
                if (repository.get(i).getId().equals(id)) {
                    return i;
                }
            }
            return -99;
        }
        catch (Exception e) {
            return -99;
        }
    }

    @Override
    public <S extends ToDoItem> S save(S newToDo) {
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
            return (S) repository.get(index);
        } else {
            Integer id = repository.size() + 1;
            newToDo.setId(id.toString());
            repository.add(newToDo);
            return newToDo;
        }
    }

    @Override
    public <S extends ToDoItem> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ToDoItem> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<ToDoItem> findAll() {
        return repository;
    }

    @Override
    public Iterable<ToDoItem> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String id) {
        int index = findIdInRepo(id);
        if(index != -99) {
            repository.remove(index);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void delete(ToDoItem entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends ToDoItem> entities) {
        repository.clear();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<ToDoItem> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<ToDoItem> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ToDoItem> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends ToDoItem> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends ToDoItem> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends ToDoItem> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends ToDoItem> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends ToDoItem> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends ToDoItem> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends ToDoItem> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends ToDoItem, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
