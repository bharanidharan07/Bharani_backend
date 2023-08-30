package com.project.mainclass.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.mainclass.Model.EmployeeDetails;
import com.project.mainclass.service.EmployeeService;
import com.project.mainclass.dto.LoginDto;
import com.project.mainclass.util.AuthenticationUtil;

@RestController
// @CrossOrigin(value = "*")
@RequestMapping("/employee")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private AuthenticationUtil authenticationUtil;

  //TO Create Employee
  @PostMapping
  public String createEmployee(@RequestBody EmployeeDetails employeeDetails) {
    Optional<EmployeeDetails> employee = employeeService.create(
      employeeDetails
    );
    if (employee.isPresent()) {
      return "Value has been inserted";
    }
    return "value has not been inserted";
  }

  //TO Get Employee
  @GetMapping(value = "/emp-id")
  public Optional<EmployeeDetails> getEmployeeDetailsByID(
    @RequestParam("empId") String empId
  ) {
    return employeeService.get(empId);
  }
  

  @GetMapping("/all")
  public List<EmployeeDetails> getAllEmoloyeeDetails() {
    return employeeService.getAll();
  }

  @GetMapping(value = "/emp-Name")
  public List<EmployeeDetails> getByName(
    @RequestParam("empName") String empName
  ) {
    return employeeService.empName(empName);
  }

  //TO Delete Employee
  @DeleteMapping(value = "/emp-id")
  public void removeEmployee(@RequestParam("empId") String empId) {
    employeeService.delete(empId);
  }

  @PostMapping(value = "/login")
  public ResponseEntity<String> post(@RequestBody LoginDto loginDto)
    throws Exception {
    EmployeeDetails loginUser = employeeService.login(loginDto);
    return ResponseEntity.ok(
      authenticationUtil.authentication(
        loginUser.getEmail(),
        loginDto.getPassword()
      )
    );
  }
}
  