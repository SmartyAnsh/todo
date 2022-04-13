package io.educative.unit;

import io.educative.domains.TodoType;
import io.educative.repositories.TodoTypeRepository;
import io.educative.services.TodoTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.Validator;
import java.util.Date;
import java.util.Optional;


public class TodoTypeServiceTest {

    private TodoTypeRepository todoTypeRepository = Mockito.mock(TodoTypeRepository.class);

    private Validator validator = Mockito.mock(Validator.class);

    private TodoTypeService service = new TodoTypeService(todoTypeRepository, validator);

    @Test
    void whenRead_thenReturnTodoType() {
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDateCreated(new Date());
        Optional<TodoType> personalOptional = Optional.ofNullable(personal);

        //given
        Mockito.when(todoTypeRepository.findById("PERSONAL")).thenReturn(personalOptional);

        //when
        TodoType result = service.findByCode("PERSONAL");

        //then
        Assertions.assertEquals(result.getCode(), personal.getCode());
    }

}
