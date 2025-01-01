package com.api.routes.auth.dtos;

public class LoginUserDto {
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public LoginUserDto setEmail(String email) {
    this.email = email;
    return this;
  }

  public LoginUserDto setPassword(String password) {
    this.password = password;
    return this;
  }

  public LoginUserDto build() {
    return this;
  }
}
