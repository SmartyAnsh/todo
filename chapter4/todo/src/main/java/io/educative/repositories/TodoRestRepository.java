package io.educative.repositories;

import io.educative.domains.Todo;
import io.educative.domains.TodoType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "todo", path = "todo")
public interface TodoRestRepository extends PagingAndSortingRepository<Todo, Long> {

    @RestResource
    List<Todo> findAllByTypeCode(@Param("type") String code);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

}
