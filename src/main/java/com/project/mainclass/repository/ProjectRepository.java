package com.project.mainclass.repository;

import com.project.mainclass.Model.ProjectDetails;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository
  extends JpaRepository<ProjectDetails, String> {
  @Query(
    value = "SELECT * FROM project_details where name LIKE %=name%",
    nativeQuery = true
  )
  Optional<ProjectDetails> findByName(@Param(value = "name") String name);
}
