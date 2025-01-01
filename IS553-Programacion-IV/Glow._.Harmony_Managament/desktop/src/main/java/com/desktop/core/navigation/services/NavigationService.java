package com.desktop.core.navigation.services;

import com.desktop.core.database.service.HttpClientService;
import com.desktop.views.shared.models.ViewModel;

public class NavigationService {
  private final HttpClientService httpClientService = new HttpClientService();
  /**
   * Retrieves an array of ViewModel objects from the server.
   * 
   * This method sets the endpoint to "/index" and constructs the parameters
   * for the request as "/find-views". It then makes a GET request to the server
   * using the httpClientService and attempts to parse the response into an array
   * of ViewModel objects.
   * 
   * @return an array of ViewModel objects if the request is successful;
   *         null if an exception occurs during the request.
   */
  public ViewModel[] getViews() {
    httpClientService.endpoint = "/index";
    StringBuilder params = new StringBuilder("/views");
    try {
      return httpClientService.get(params.toString(), ViewModel[].class);
    } catch (Exception e) {
      return null;
    }
  }
}
