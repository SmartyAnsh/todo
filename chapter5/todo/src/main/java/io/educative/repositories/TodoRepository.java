package io.educative.repositories;

import io.educative.domains.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

}
