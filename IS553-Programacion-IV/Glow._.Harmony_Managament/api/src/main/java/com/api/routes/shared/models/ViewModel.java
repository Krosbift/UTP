package com.api.routes.shared.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewModel {
  private Integer viewId;
  private String viewName;
  private String description;
  private boolean active;

  public Integer getViewId() {
    return viewId;
  }

  public String getViewName() {
    return viewName;
  }

  public String getDescription() {
    return description;
  }

  public boolean getActive() {
    return active;
  }

  public ViewModel setViewId(Integer viewId) {
    this.viewId = viewId;
    return this;
  }

  public ViewModel setViewId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.viewId = rs.getInt("VIEWID");
    }
    return this;
  }

  public ViewModel setViewName(String viewName) {
    this.viewName = viewName;
    return this;
  }

  public ViewModel setViewName(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.viewName = rs.getString("NAME");
    }
    return this;
  }

  public ViewModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public ViewModel setDescription(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.description = rs.getString("DESCRIPTION");
    }
    return this;
  }

  public ViewModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public ViewModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public ViewModel build() {
    return this;
  }
}
