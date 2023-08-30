package com.project.mainclass.service;

import com.project.mainclass.dto.LoginDto;
import com.project.mainclass.Model.EmployeeDetails;
import com.project.mainclass.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "EmployeeService")
public class EmployeeService implements UserDetailsService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  @Lazy
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  //To Create Employee

  public Optional<EmployeeDetails> create(EmployeeDetails employeeDetails) {
    employeeDetails.setPassword(
      bCryptPasswordEncoder.encode(employeeDetails.getPassword())
    );
    EmployeeDetails eDetails = employeeRepository.save(employeeDetails);
    return Optional.of(eDetails);
  }

  //TO get Employee

  public Optional<EmployeeDetails> get(String empId) {
    return employeeRepository.findById(empId);
  }

  public List<EmployeeDetails> getAll() {
    return employeeRepository.findAll();
  }

  public List<EmployeeDetails> empName(String empName) {
    return employeeRepository.findByName(empName);
  }

  public void delete(String empId) {
    employeeRepository.deleteById(empId);
  }

  // for login purpose
  @Override
  public UserDetails loadUserByUsername(String email) {
    EmployeeDetails employeeDetails = employeeRepository
      .findByEmail(email)
      .orElse(null);
    return employeeDetails;
  }

  public EmployeeDetails login(LoginDto loginDto) throws Exception {
    EmployeeDetails employeeDetails = employeeRepository
      .findByEmail(loginDto.getEmail())
      .orElseThrow(() -> new Exception("Invalid user"));
    if (
      !bCryptPasswordEncoder.matches(
        loginDto.getPassword(),
        employeeDetails.getPassword()
      )
    ) {
      throw new Exception("Invalid password");
    }
    return employeeDetails;
  }

}
