package io.educative.repositories;

import io.educative.domains.TodoType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TodoTypeRepository extends PagingAndSortingRepository<TodoType, String> {

}
