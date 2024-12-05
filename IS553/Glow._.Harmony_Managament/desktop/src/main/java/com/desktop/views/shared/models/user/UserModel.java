package com.desktop.views.shared.models.user;

import com.desktop.views.shared.models.index.DocumentTypeModel;
import com.desktop.views.shared.models.index.RoleTypeModel;

public class UserModel {
  private Integer userId;
  private String names;
  private String surNames;
  private DocumentTypeModel documentType;
  private String documentNumber;
  private String email;
  private String password;
  private String phone;
  private RoleTypeModel roleType;
  private String address;
  private boolean active;

  public Integer getUserId() {
    return userId;
  }

  public String getNames() {
    return names;
  }

  public String getSurNames() {
    return surNames;
  }

  public DocumentTypeModel getDocumentType() {
    return documentType;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getPhone() {
    return phone;
  }

  public RoleTypeModel getRoleType() {
    return roleType;
  }

  public String getAddress() {
    return address;
  }

  public boolean getActive() {
    return active;
  }

  public UserModel initDocumentType() {
    this.documentType = new DocumentTypeModel();
    return this;
  }

  public UserModel initRoleType() {
    this.roleType = new RoleTypeModel();
    return this;
  }

  public UserModel setUserId(Integer userId) {
    this.userId = userId;
    return this;
  }

  public UserModel setNames(String names) {
    this.names = names;
    return this;
  }

  public UserModel setSurNames(String surNames) {
    this.surNames = surNames;
    return this;
  }

  public UserModel setDocumentTypeId(Integer documentTypeId) {
    this.documentType.setDocumentTypeId(documentTypeId);
    return this;
  }

  public UserModel setDocumentType(String documentType) {
    this.documentType.setDocumentType(documentType);
    return this;
  }

  public UserModel setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
    return this;
  }

  public UserModel setEmail(String email) {
    this.email = email;
    return this;
  }

  public UserModel setPassword(String password) {
    this.password = password;
    return this;
  }

  public UserModel setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public UserModel setRoleTypeId(Integer roleTypeId) {
    this.roleType.setRoleTypeId(roleTypeId);
    return this;
  }

  public UserModel setRoleType(String roleType) {
    this.roleType.setRoleType(roleType);
    return this;
  }

  public UserModel setAddress(String address) {
    this.address = address;
    return this;
  }

  public UserModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public UserModel build() {
    return this;
  }
}
