package io.educative.repositories;

import io.educative.domains.TodoType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
//@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
public interface TodoTypeRepository extends PagingAndSortingRepository<TodoType, String> {

    //List<TodoType> findAllByDateCreatedAfter(Date dateCreated);

    List<TodoType> findAllByDateCreatedAfter(Date dateCreated, Pageable pageable);

}
