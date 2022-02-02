package com.nmurray.todorestdemo.data.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * @author Nicholas Murray
 * A model class for the REST and Relational Mapping to the Database.
 */
public class ToDo {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean completed;

    //Default constructor for use by the REST controller
    public ToDo() {}

    /**
     * A parameterised constructor for use when testing
     */
    public ToDo(String description, boolean completed) {
        setDescription(description);
        setCompleted(completed);
    }
}
