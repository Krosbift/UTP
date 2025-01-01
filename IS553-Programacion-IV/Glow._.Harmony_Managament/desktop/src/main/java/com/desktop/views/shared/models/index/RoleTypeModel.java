package com.desktop.views.shared.models.index;

public class RoleTypeModel {
  private Integer roleTypeId;
  private String roleType;
  private String description;
  private boolean active;

  public Integer getRoleTypeId() {
    return roleTypeId;
  }

  public String getRoleType() {
    return roleType;
  }

  public String getDescription() {
    return description;
  }

  public boolean getActive() {
    return active;
  }

  public RoleTypeModel setRoleTypeId(Integer roleTypeId) {
    this.roleTypeId = roleTypeId;
    return this;
  }

  public RoleTypeModel setRoleType(String roleType) {
    this.roleType = roleType;
    return this;
  }

  public RoleTypeModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public RoleTypeModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public RoleTypeModel build() {
    return this;
  }
}
