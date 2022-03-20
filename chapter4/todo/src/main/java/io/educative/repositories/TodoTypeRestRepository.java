package io.educative.repositories;

import io.educative.domains.TodoType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "todoType", path = "todoType")
public interface TodoTypeRestRepository extends PagingAndSortingRepository<TodoType, String> {

    /*@Override
    @RestResource(exported = false)
    Optional<TodoType> findById(String code);*/

    @RestResource(path = "byCode", rel = "customFindMethodForCode")
    List<TodoType> findAllByCodeContains(@Param("code") String code);

}
