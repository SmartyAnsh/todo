package io.educative.repositories;

import io.educative.domains.TodoType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "todoType", path = "todoType")
public interface TodoTypeRestRepository extends PagingAndSortingRepository<TodoType, String> {

    @RestResource(path = "byCode", rel = "customFindMethodForCode")
    List<TodoType> findAllByCodeContains(@Param("code") String code);

}
