package io.educative.unit;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import io.educative.repositories.TodoRepository;
import io.educative.repositories.TodoTypeRepository;
import io.educative.services.TodoService;
import io.educative.services.TodoTypeService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.Validator;
import java.util.Date;
import java.util.Optional;


public class TodoServiceTest {

    private TodoRepository todoRepository = Mockito.mock(TodoRepository.class);
    private TodoTypeRepository todoTypeRepository = Mockito.mock(TodoTypeRepository.class);

    private Validator validator = Mockito.mock(Validator.class);

    private TodoService service = new TodoService(todoRepository, todoTypeRepository, validator);

    @Test
    void whenUpdate_thenReturnTodo() {
        Todo doLaundry = new Todo();
        doLaundry.setId(1L);
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());
        doLaundry.setDone(true);

        //given
        Mockito.when(todoRepository.save(doLaundry)).thenReturn(doLaundry);

        //when
        Todo result = service.update(doLaundry);

        //then
        Assert.assertNotNull(result.getDateDone());
    }
}
