package com.project.mainclass.util;

import com.project.mainclass.service.EmployeeService;
import com.project.mainclass.props.JwtProps;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtProps jwtProps;

  @Autowired  
  private TokenProvider jwtTokenUtil;

  private Set<String> skipUrls = new HashSet<>();

  private AntPathMatcher pathMatcher = new AntPathMatcher();

  @Autowired
  private EmployeeService employeeService;

  @Override 
  protected boolean shouldNotFilter(HttpServletRequest request)
    throws ServletException {
      skipUrls.add("/auth/competitor/register");
      skipUrls.add("/auth/recruiter/register");
    skipUrls.add("/user/findUser");
    skipUrls.add("/auth/change-password");
    skipUrls.add("/auth/collegeStudent/register");
    skipUrls.add("/employee/login");
    skipUrls.add("/employee");
    return skipUrls
      .stream()
      .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
  }

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain chain
  ) throws ServletException, IOException {
    String header = request.getHeader("Authorization");
    String email = null;
    String authToken = null;

    authToken = header.replace(jwtProps.getTokenPrefix(), "");

    try {
      email = jwtTokenUtil.getEmailFromToken(authToken);
    } catch (IllegalArgumentException e) {
      logger.error("An error occurred while fetching Username from Token", e);
      try {
        throw new Exception(
          "An error occurred while fetching Username from Token"
        );
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } catch (ExpiredJwtException e) {
      logger.warn("The token has expired", e);
      try {
        throw new Exception("The token has expired");
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } catch (SignatureException e) {
      logger.error("Authentication Failed. Username or Password not valid.");
      try {
        throw new Exception(
          "Authentication Failed. Username or Password not valid."
        );
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }

    // if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
    UserDetails userDetails = employeeService.loadUserByUsername(email);
    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails,
        null,
        userDetails.getAuthorities()
      );
      authentication.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)
      );
      logger.info("authenticated user " + email + ", setting security context");
      SecurityContextHolder.getContext().setAuthentication(authentication);
      chain.doFilter(request, response);
    } else {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      response.getWriter().write("{\"message\":\"Unauthorized User\"}");
    }
  }
}
