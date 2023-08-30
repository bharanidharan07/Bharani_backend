package com.project.mainclass.dto;

import lombok.Data;

@Data
public class UserDto {

  private String id;

  private String username;

  private String password;

  private String email;

  private String phone;

  private String createdBy;

  private boolean accountExpired;

  private boolean accountLocked;

  private boolean enabled = true;

  private boolean credentialsExpired;

}
