package io.educative.repositories;

import io.educative.domains.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

    @Query("SELECT t FROM Todo t WHERE t.done = 1")
    List<Todo> readAllDone();

    List<Todo> findAllDone();

    List<Todo> findAllTitle(String title);

    //List<Todo> findAllByDateCreatedAfter(Date dateCreated);

    List<Todo> findAllByDone(Boolean done, Pageable pageable);

    List<Todo> findAllByDoneAndDateDoneAfter(Boolean done, Date dateDone, Sort sort);

    List<Todo> findAllByDateCreatedAfter(Date dateCreated, Sort sort);

    long countAllByDone(Boolean done);

    long deleteAllByDone(Boolean done);

}
