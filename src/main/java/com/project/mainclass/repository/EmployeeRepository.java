package com.project.mainclass.repository;

import com.project.mainclass.Model.EmployeeDetails;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository
  extends JpaRepository<EmployeeDetails, String> {
  List<EmployeeDetails> findByEmpName(String empName);

  @Query(
    value = "SELECT * FROM employee_details where emp_name LIKE %:name%",
    nativeQuery = true
  )
  List<EmployeeDetails> findByName(@Param(value = "name") String empName);

  Optional<EmployeeDetails> findByEmail(String email);
}
