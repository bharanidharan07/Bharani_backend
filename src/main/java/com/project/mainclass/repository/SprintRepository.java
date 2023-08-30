package com.project.mainclass.repository;

import com.project.mainclass.Model.SprintDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository
  extends JpaRepository<SprintDetails, String> {
    
  }
