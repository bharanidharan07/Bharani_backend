package com.project.mainclass.configuration;

import com.project.mainclass.service.EmployeeService;
import com.project.mainclass.util.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class securityconfiguration {

  @Autowired
  private EmployeeService employeeService;

  // @Bean
  // private AuthenticationProvider authprovider() {
  //   DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
  //   provider.setUserDetailsService(employeeService);
  //   provider.setPasswordEncoder(getBcryptPasswordEncoder());
  //   return provider;
  // }

  @Bean
  SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
      .cors()
      .and()
      .csrf()
      .disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.GET, "/**")
      .permitAll()
      .antMatchers(HttpMethod.POST, "/**")
      .permitAll()
      .antMatchers(HttpMethod.DELETE, "/**")
      .permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .exceptionHandling()
      .authenticationEntryPoint((request, response, authException) -> {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write("{\"message\":\"Unauthorized User\"}");
      })
      .and()
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(
      authenticationJwtTokenFilter(),
      UsernamePasswordAuthenticationFilter.class
    );
    return http.build();
  }

  @Bean
  AuthenticationManager authenticationManagerBean(HttpSecurity http)
    throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
      AuthenticationManagerBuilder.class
    );
    authenticationManagerBuilder
      .userDetailsService(employeeService)
      .passwordEncoder(getBcryptPasswordEncoder());
    return authenticationManagerBuilder.build();
  }

  @Bean
  BCryptPasswordEncoder getBcryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  JwtAuthenticationFilter authenticationJwtTokenFilter() {
    return new JwtAuthenticationFilter();
  }
}
