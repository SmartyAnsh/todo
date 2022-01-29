package io.educative.repositories;

import io.educative.domains.Todo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TodoRestRepository extends PagingAndSortingRepository<Todo, Long> {

}
