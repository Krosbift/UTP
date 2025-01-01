package com.desktop.views.users.model;

public class RegisterUserDto {
  private String names;
  private String surNames;
  private Integer documentTypeId;
  private String documentNumber;
  private String email;
  private String password;
  private String phone;
  private Integer roleTypeId;
  private String address;

  public String getNames() {
    return names;
  }

  public RegisterUserDto setNames(String names) {
    this.names = names;
    return this;
  }

  public String getSurNames() {
    return surNames;
  }

  public RegisterUserDto setSurNames(String surNames) {
    this.surNames = surNames;
    return this;
  }

  public Integer getDocumentTypeId() {
    return documentTypeId;
  }

  public RegisterUserDto setDocumentTypeId(Integer documentTypeId) {
    this.documentTypeId = documentTypeId;
    return this;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }

  public RegisterUserDto setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public RegisterUserDto setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public RegisterUserDto setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public RegisterUserDto setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public Integer getRoleTypeId() {
    return roleTypeId;
  }

  public RegisterUserDto setRoleTypeId(Integer roleTypeId) {
    this.roleTypeId = roleTypeId;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public RegisterUserDto setAddress(String address) {
    this.address = address;
    return this;
  }
}
