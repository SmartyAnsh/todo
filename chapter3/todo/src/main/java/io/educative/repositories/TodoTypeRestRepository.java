package io.educative.repositories;

import io.educative.domains.TodoType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TodoTypeRestRepository extends PagingAndSortingRepository<TodoType, String> {

}
