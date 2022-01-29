package io.educative.services;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import io.educative.repositories.TodoTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Service
public class TodoTypeService {

    private static Map<String, TodoType> todoTypeCollection = new HashMap<>();
    private TodoTypeRepository todoTypeRepository;
    private Validator validator;

    @Autowired
    public TodoTypeService(TodoTypeRepository todoTypeRepository, Validator validator) {
        this.todoTypeRepository = todoTypeRepository;
        this.validator = validator;
    }

    /*public TodoType create(TodoType todoType) {
        Set<ConstraintViolation<TodoType>> violations = validator.validate(todoType);
        for (ConstraintViolation<TodoType> violation : violations) {
            System.out.println(violation.getMessage());
        }
        if (violations.size() < 1) {
            todoTypeCollection.put(todoType.getCode(), todoType);
            return todoType;
        } else {
            return null;
        }
    }

    public TodoType findByCode(String code) {
        return todoTypeCollection.get(code);
    }

    public TodoType update(TodoType todoType) {
        todoType.setLastUpdated(new Date());
        todoTypeCollection.put(todoType.getCode(), todoType);
        return todoType;
    }

    public void deleteByCode(String code) throws Exception {
        if (todoTypeCollection.remove(code) == null) {
            throw new Exception("Code doesn't exist");
        }
    }*/

    public TodoType create(TodoType todoType) {
        Set<ConstraintViolation<TodoType>> violations = validator.validate(todoType);
        for (ConstraintViolation<TodoType> violation : violations) {
            System.out.println(violation.getMessage());
        }
        if (violations.size() < 1) {
            todoTypeRepository.save(todoType);
        }
        return todoType;
    }

    public TodoType update(TodoType todoType) {
        todoType.setLastUpdated(new Date());
        todoType = todoTypeRepository.save(todoType);
        return todoType;
    }

public void deleteByCode(String code) throws Exception {
    if (!todoTypeRepository.existsById(code)) {
        throw new Exception("Code doesn't exist");
    }
    todoTypeRepository.deleteById(code);
}

    public TodoType findByCode(String code) {
        Optional<TodoType> todoTypeResult = todoTypeRepository.findById(code);
        if (todoTypeResult.isPresent()) {
            return todoTypeResult.get();
        } else {
            return null;
        }
    }

    public List<TodoType> findAll(String sort, Sort.Direction order, int pageNumber, int numOfRecords) {
        Sort idDesc = Sort.by(order, sort);
        Pageable pageRequest = PageRequest.of(pageNumber, numOfRecords, idDesc);
        Page<TodoType> todoTypePages = todoTypeRepository.findAll(pageRequest);
        List<TodoType> todoTypes = todoTypePages.getContent();
        return todoTypes;
    }
}
