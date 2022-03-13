package io.educative.utils;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import io.educative.services.TodoService;
import io.educative.services.TodoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DataLoader implements ApplicationRunner {

    private TodoService todoService;
    private TodoTypeService todoTypeService;

    @Autowired
    public DataLoader(TodoService todoService, TodoTypeService todoTypeService) {
        this.todoService = todoService;
        this.todoTypeService = todoTypeService;
    }

    public void run(ApplicationArguments args) throws ParseException {
        TodoType personalType = new TodoType();
        personalType.setCode("PERSONAL");
        personalType.setDescription("Todo for Personal Work");
        todoTypeService.create(personalType);

        Todo todo1 = new Todo();
        todo1.setTitle("Do Laundry");
        todo1.setDescription("laundry...");
        todo1.setDateCreated(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("09/01/2022 15:20"));
        todo1.setDueDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/01/2022 16:00"));
        todoService.create(todo1);

        Todo todo2 = new Todo();
        todo2.setTitle("Pay electricity bill");
        todo2.setDescription("Pay electricity bill...");
        todo2.setDateCreated(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("10/01/2022 15:20"));
        todo2.setDueDate(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/01/2022 16:00"));
        todoService.create(todo2);

    }
}

