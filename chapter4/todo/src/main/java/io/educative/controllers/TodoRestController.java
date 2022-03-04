package io.educative.controllers;

import io.educative.domains.Todo;
import io.educative.repositories.TodoRestRepository;
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

    private final TodoRestRepository repository;

    @Autowired
    public TodoRestController(TodoRestRepository repo) {
        repository = repo;
    }

    @GetMapping(value = "/todo/search/getTodos")
    public @ResponseBody ResponseEntity<?> getTodos() {
        Iterable<Todo> todos = repository.findAll();
        CollectionModel<Todo> resources = CollectionModel.of(todos);
        resources.add(linkTo(methodOn(TodoRestController.class).getTodos()).withSelfRel());
        return ResponseEntity.ok(resources);
    }

}
