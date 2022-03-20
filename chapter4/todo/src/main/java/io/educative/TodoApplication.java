package io.educative;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public RepresentationModelProcessor<EntityModel<Todo>> todoProcessor() {

        return new RepresentationModelProcessor<EntityModel<Todo>>() {

            @Override
            public EntityModel<Todo> process(EntityModel<Todo> model) {
                model.add(Link.of("http://localhost:8080/todo/todoTypes", "types"));
                return model;
            }
        };
    }
}
