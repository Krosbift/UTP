package com.api.routes.shared.models.index;

import java.sql.ResultSet;
import java.sql.SQLException;

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

  public RoleTypeModel setRoleTypeId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.roleTypeId = rs.getInt("ROLETYPEID");
    }
    return this;
  }

  public RoleTypeModel setRoleType(String roleType) {
    this.roleType = roleType;
    return this;
  }

  public RoleTypeModel setRoleType(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.roleType = rs.getString("ROLETYPE");
    }
    return this;
  }

  public RoleTypeModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public RoleTypeModel setDescription(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.description = rs.getString("DESCRIPTION");
    }
    return this;
  }

  public RoleTypeModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public RoleTypeModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public RoleTypeModel build() {
    return this;
  }
}
