package com.desktop.views.shared.models;

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

  public ViewModel setViewName(String viewName) {
    this.viewName = viewName;
    return this;
  }

  public ViewModel setDescription(String description) {
    this.description = description;
    return this;
  }

  public ViewModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public ViewModel build() {
    return this;
  }
}
