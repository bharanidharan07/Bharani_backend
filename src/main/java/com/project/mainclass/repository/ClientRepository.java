package com.project.mainclass.repository;

import com.project.mainclass.Model.ClientDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientDetails, String> {
  @Query(
    value = "SELECT * FROM client_details where name LIKE %:name%",
    nativeQuery = true
  )
  List<ClientDetails> findByName(@Param(value = "name") String name);
}
