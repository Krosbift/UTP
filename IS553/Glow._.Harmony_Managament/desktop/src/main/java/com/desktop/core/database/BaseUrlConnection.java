package com.desktop.core.database;

public enum BaseUrlConnection {
  BASE_URL("http://localhost:3010");

  private final String url;

  BaseUrlConnection(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}