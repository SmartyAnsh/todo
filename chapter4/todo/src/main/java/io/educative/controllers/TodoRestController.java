package io.educative.controllers;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import io.educative.repositories.TodoRestRepository;
import io.educative.repositories.TodoTypeRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RepositoryRestController
public class TodoRestController {

    private final TodoRestRepository todoRepository;
    private final TodoTypeRestRepository todoTypeRepository;

    @Autowired
    public TodoRestController(TodoRestRepository todoRepository, TodoTypeRestRepository todoTypeRepository) {
        this.todoRepository = todoRepository;
        this.todoTypeRepository = todoTypeRepository;
    }

    @GetMapping(value = "/todo/todoTypes")
    public @ResponseBody ResponseEntity<?> todoTypes() {
        CollectionModel<TodoType> resources = CollectionModel.of(todoTypeRepository.findAll());
        resources.add(linkTo(methodOn(TodoRestController.class).todoTypes()).withSelfRel());
        resources.add(linkTo(methodOn(TodoRestController.class).getTodos()).withRel("getTodos"));
        return ResponseEntity.ok(resources);
    }

    @GetMapping(value = "/todo/search/getTodos")
    public @ResponseBody ResponseEntity<?> getTodos() {
        Iterable<Todo> todos = todoRepository.findAll();
        CollectionModel<Todo> resources = CollectionModel.of(todos);
        resources.add(linkTo(methodOn(TodoRestController.class).getTodos()).withSelfRel());
        return ResponseEntity.ok(resources);
    }

}
