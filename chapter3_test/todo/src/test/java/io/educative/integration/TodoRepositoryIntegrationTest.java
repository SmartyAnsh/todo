package io.educative.integration;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import io.educative.repositories.TodoRepository;
import io.educative.repositories.TodoTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Date;
import java.util.List;

@DataJpaTest
public class TodoRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoTypeRepository todoTypeRepository;

    @Test
    public void givenTodoObjIsPersisted_whenFindByTitle_thenReturnTodoObj() {
        // given
        Todo doLaundry = new Todo();
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());

        entityManager.persist(doLaundry);
        entityManager.flush();

        // when
        Todo found = todoRepository.findByTitle(doLaundry.getTitle());

        // then
        Assertions.assertEquals(found.getTitle(), doLaundry.getTitle());
    }

    @Test
    public void whenFindAllByTitle_thenReturnAllTodos() {
        // given
        TodoType personal = new TodoType();
        personal.setCode("PERSONAL");
        personal.setDateCreated(new Date());

        entityManager.persist(personal);

        Todo doLaundry = new Todo();
        doLaundry.setTitle("Do Laundry");
        doLaundry.setDateCreated(new Date());
        doLaundry.setDueDate(new Date());
        doLaundry.setDueDate(new Date());
        doLaundry.setType(personal);

        Todo[] todos = new Todo[1];
        todos[0] = doLaundry;

        entityManager.persist(doLaundry);
        entityManager.flush();

        // when
        List<Todo> found = todoRepository.findAllByType(personal);

        // then
        Assertions.assertArrayEquals(found.toArray(), todos);
    }

}
