package io.educative.controllers;

import io.educative.domains.TodoType;
import io.educative.services.TodoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todoType")
public class TodoTypeController {

    private TodoTypeService todoTypeService;

    @Autowired
    public TodoTypeController(TodoTypeService todoTypeService) {
        this.todoTypeService = todoTypeService;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World! from Educative course 'REST APIs with Spring, JPA, and Springfox'";
    }

    @GetMapping(value = "/read", produces = {"application/json", "application/xml"})
    public TodoType readTodoType() {
        TodoType todoType = new TodoType();
        todoType.setCode("PERSONAL");
        todoType.setDescription("Todo for Personal Work");
        return todoType;
    }

    @PostMapping(value = "/create", produces = {"application/json", "application/xml"})
    public TodoType createTodoType() {
        TodoType todoType = new TodoType();
        todoType.setCode("PROFESSIONAL");
        todoType.setDescription("Todo for Professional Work");
        return todoType;
    }

    @PostMapping(consumes={"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    public TodoType create(@RequestBody TodoType todoType) {
        return todoTypeService.create(todoType);
    }

    @GetMapping(value = "/{code}", produces = {"application/xml"})
    public TodoType read(@PathVariable("code") String code) {
        TodoType todoType = todoTypeService.findByCode(code);
        return todoType;
    }

    @PutMapping
    public TodoType updateTodo(@RequestBody TodoType todoType) {
        return todoTypeService.update(todoType);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity delete(@PathVariable("code") String code) {
        try {
            todoTypeService.deleteByCode(code);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}