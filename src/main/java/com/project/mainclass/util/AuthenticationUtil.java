package com.project.mainclass.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private TokenProvider tokenProvider;

  

  public AuthenticationUtil(
    AuthenticationManager authenticationManager,
    TokenProvider tokenProvider
  ) {
    this.tokenProvider = tokenProvider;
    this.authenticationManager = authenticationManager;
  }

  public String authentication(String empName, String password) {
    try {
      final Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(empName, password)
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
      // String id = employeeService.loadUserByUsername(empName).getUsername();
      return tokenProvider.generateToken(authentication, empName, password);
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  } 
}
