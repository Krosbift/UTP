package com.desktop.core.database.service;

import java.util.List;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.desktop.core.database.BaseUrlConnection;
import java.io.StringReader;
import java.lang.reflect.Type;

public class HttpClientService {
  public String endpoint;
  private final String baseUrl = BaseUrlConnection.BASE_URL.getUrl();
  private final HttpClient httpClient = HttpClient.newHttpClient();
  private final Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd")
      .create();

  public <T> List<T> getList(String params, Class<T> responseType) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    String responseBody = response.body();
    Type listType = TypeToken.getParameterized(List.class, responseType).getType();
    try {
      JsonReader reader = new JsonReader(new StringReader(responseBody));
      reader.setLenient(true);
      return gson.fromJson(reader, listType);
    } catch (IllegalStateException e) {
      T singleObject = gson.fromJson(responseBody, responseType);
      return List.of(singleObject);
    }
  }

  public <T> T get(String params, Class<T> responseType) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T post(String params, Object requestBody, Class<T> responseType) throws Exception {
    String jsonRequestBody = gson.toJson(requestBody);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .POST(BodyPublishers.ofString(jsonRequestBody))
        .header("Content-Type", "application/json")
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T patch(String params, Object requestBody, Class<T> responseType) throws Exception {
    String jsonRequestBody = gson.toJson(requestBody);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .method("PATCH", BodyPublishers.ofString(jsonRequestBody))
        .header("Content-Type", "application/json")
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T delete(String params, Class<T> responseType) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .DELETE()
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    if (responseType == Integer.class) {
      return responseType.cast(Integer.parseInt(response.body()));
    }
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T delete(String params, Object requestBody, Class<T> responseType) throws Exception {
    String jsonRequestBody = gson.toJson(requestBody);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .method("DELETE", BodyPublishers.ofString(jsonRequestBody))
        .header("Content-Type", "application/json")
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T get(String params, Type responseType) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .GET()
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T post(String params, Object requestBody, Type responseType) throws Exception {
    String jsonRequestBody = gson.toJson(requestBody);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .POST(BodyPublishers.ofString(jsonRequestBody))
        .header("Content-Type", "application/json")
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T patch(String params, Object requestBody, Type responseType) throws Exception {
    String jsonRequestBody = gson.toJson(requestBody);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .method("PATCH", BodyPublishers.ofString(jsonRequestBody))
        .header("Content-Type", "application/json")
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T delete(String params, Type responseType) throws Exception {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .DELETE()
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }

  public <T> T delete(String params, Object requestBody, Type responseType) throws Exception {
    String jsonRequestBody = gson.toJson(requestBody);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(baseUrl + endpoint + params))
        .method("DELETE", BodyPublishers.ofString(jsonRequestBody))
        .header("Content-Type", "application/json")
        .build();

    HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
    JsonReader reader = new JsonReader(new StringReader(response.body()));
    reader.setLenient(true);
    return gson.fromJson(reader, responseType);
  }
}