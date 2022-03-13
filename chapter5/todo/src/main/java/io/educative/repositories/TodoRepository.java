package io.educative.repositories;

import io.educative.domains.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.done = true")
    List<Todo> readAllDone();

    List<Todo> findAllDone();

    List<Todo> findAllTitle(String title);

    List<Todo> findAllByDone(Boolean done, Pageable pageable);

    List<Todo> findAllByDoneAndDateDoneAfter(Boolean done, Date dateDone, Pageable pageable);

    List<Todo> findAllByDateCreatedAfter(Date dateCreated, Pageable pageable);

    long countAllByDone(Boolean done);

    long deleteAllByDone(Boolean done);

    @Query("SELECT t FROM Todo t WHERE t.dateCreated >= ?1 and t.dueDate >= ?2")
    List<Todo> fetchTodos(Date dateCreated, Date dueDate);

}
