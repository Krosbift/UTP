package com.desktop.views.login.models;

public class SendLogin {
  private String email;
  private String password;

  public String getEmail() {
    return email;
  }

  public SendLogin setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public SendLogin setPassword(String password) {
    this.password = password;
    return this;
  }

  public SendLogin build() {
    return this;
  }
}
