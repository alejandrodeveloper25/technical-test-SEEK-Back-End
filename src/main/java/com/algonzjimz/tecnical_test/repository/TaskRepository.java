package com.algonzjimz.tecnical_test.repository;

import com.algonzjimz.tecnical_test.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(String status);
}
