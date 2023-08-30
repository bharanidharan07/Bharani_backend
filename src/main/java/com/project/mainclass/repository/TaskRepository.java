package com.project.mainclass.repository;

import com.project.mainclass.Model.TaskDetails;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDetails, String> {
  @Query(
    value = "SELECT * FROM task_details where name LIKE %:name%",
    nativeQuery = true
  )
  Optional<TaskDetails> findByName(@Param(value = "name") String name);
}
