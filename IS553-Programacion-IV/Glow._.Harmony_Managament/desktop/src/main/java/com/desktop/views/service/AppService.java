package com.desktop.views.service;

import com.desktop.core.database.service.HttpClientService;

public class AppService {
  private final HttpClientService httpClientService = new HttpClientService();

  public String activeDatabase() {
    httpClientService.endpoint = "/activate";
    try {
      return httpClientService.get("", String.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
