package io.educative.controllers;

import io.educative.domains.Todo;
import io.educative.services.TodoService;
import io.educative.services.TodoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private TodoService todoService;
    private TodoTypeService todoTypeService;

    @Autowired
    public TodoController(TodoService todoService, TodoTypeService todoTypeService) {
        this.todoService = todoService;
        this.todoTypeService = todoTypeService;
    }

    @GetMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    /*
    @GetMapping("/get")
    List<Todo> findAll() {
        return todoService.findAll();
    }

    @GetMapping("/todo/{id}")
    Todo find(@PathVariable("id") long id) {
        Optional<Todo> todo = todoService.findById(id);
        return todo.get();
    }

    @PostMapping("/todo")
    @LogMethodDetails
    Todo newTodo(@Valid @RequestBody Todo todo) {
        int x = 1/0;
        return todoService.create(todo);
    }

    @GetMapping("/readDone")
    Collection<Todo> readAllDone() {
        return todoService.readAllDone();
    }

    @GetMapping("/readByName")
    Collection<Todo> readAllByName(@RequestParam String name) {
        return todoService.readAllByName(name);
    }

    @GetMapping( "/fetchTodo/{id}")
    public ResponseEntity<Todo> getTodoByUd (@PathVariable("id") long id) {
        Todo todo = todoService.findById(id).get();
        return ResponseEntity.ok()
                .eTag(Long.toString(todo.getLastUpdated().getTime()))
                .body(todo);
    }*/


    @PostMapping
    public Todo create(@Valid @RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> read(@PathVariable("id") Long id) {
        Todo todo = todoService.findById(id);
        if (null != todo) {
            return new ResponseEntity(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    /*@GetMapping("/{id}")
    ResponseEntity readTodo(@PathVariable("id") Long id) {
        Todo todo = todoService.findById(id);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }*/

    @PutMapping
    public Todo updateTodo(@RequestBody Todo todo) {
        return todoService.update(todo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        try {
            todoService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public List<Todo> findAll(@RequestParam String sort, @RequestParam String order, @RequestParam int pageNumber, @RequestParam int numOfRecords) {
        return todoService.findAll(sort, Sort.Direction.fromString(order), pageNumber, numOfRecords);
    }

}
