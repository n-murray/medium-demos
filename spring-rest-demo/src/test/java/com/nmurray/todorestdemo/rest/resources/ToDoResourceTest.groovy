package com.nmurray.todorestdemo.rest.resources

import com.nmurray.todorestdemo.TodoRestDemoApplication
import com.nmurray.todorestdemo.data.entities.ToDoItem
import com.nmurray.todorestdemo.data.repositories.ToDoItemRepository
import com.nmurray.todorestdemo.data.repositories.ToDoItemRepositoryMockImpl
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(classes = TodoRestDemoApplication.class)
class ToDoResourceTest extends Specification {

    @Shared
    ToDoItemRepository repo = new ToDoItemRepositoryMockImpl()

    @Shared
    def todo1 = new ToDoItem("An important description", false)

    @Shared
    def todo2 = new ToDoItem("A test description", false)

    def todoList = [ todo1, todo2 ]

    @Shared
    ToDoResource toDoResource = new ToDoResource()

    def setupSpec() {
        toDoResource.repository = repo
    }
    def setup() {
        repo.deleteAll()
    }

    def "When getAllTodos is called it should return all items in the repository" () {
        given: "A mocked repository with todo items"
            repo.save(todo1)
            repo.save(todo2)
        when: "The getAllTodos endpoint is called"
            def response = toDoResource.getAllToDos()
        then: "All  todo items are returned in the response body"
            response.statusCodeValue == 200
            response.body == todoList
    }

    def "When a new todo item is sent to the saveToDo endpoint it is persisted" () {
        given: "A mock empty repository"

        when: "the saveTodo endpoint is called"
            def response = toDoResource.saveToDo(todo1)
        then: "The item is sent to the repository and the persisted item is returned"
            response.statusCodeValue == 201
            response.body == todo1
    }

    def "When an updated todo item is sent to the updateToDo endpoint it is persisted" () {
        given: "A mock empty repository with an existing todo item"
            repo.save(todo1)
            todo1.setDescription("A new description")
        when: "the saveTodo endpoint is called"
            def response = toDoResource.updateToDo(todo1)
        then: "The item is sent to the repository and the persisted item is returned"
            response.statusCodeValue == 201
            ToDoItem todoUpdated = response.body as ToDoItem
            todoUpdated.getDescription() == "A new description"
    }

    def "When the delete endpoint is called the correct response is returned" () {
        given: "A mock repository with existing todo items"
            repo.save(todo1)
            repo.save(todo2)
        when: "the delete endpoint is called"
            def response = toDoResource.deleteToDo(id as String)
        then: "the correct response is returned"
            response.statusCodeValue == expectedCode
        where:
            id | expectedCode
            "1"| 200
            "3"| 400
    }



}
