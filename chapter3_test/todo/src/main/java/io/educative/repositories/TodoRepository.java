package io.educative.repositories;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    Todo findByTitle(String title);

    List<Todo> findAllByType(TodoType todoType);

    Todo findByDateCreatedGreaterThanEqual(Date dateCreated);

    Todo findByDoneAndDateDone(Boolean done, Date dateDone);

    List<Todo> findAllByDone(Boolean done);

    List<Todo> findAllByDateCreatedAfter(Date dateCreated);

    long countAllByDone(Boolean done);

    long countAllByDateCreatedGreaterThanAndDueDate(Date dateCreated, Date dueDate);

    long deleteAllByDone(Boolean done);

    List<Todo> findAllByDoneAndDateDoneAfter(Boolean done, Date dateDone);

}
