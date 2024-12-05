package com.desktop.views.minimals.service;

import java.util.List;
import com.desktop.core.database.service.HttpClientService;
import com.desktop.views.minimals.model.ProductMinimalStockModel;
import com.desktop.views.shared.models.inventory.ProductMovementModel;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MinimalService {
  private final HttpClientService httpClientService = new HttpClientService();

  public List<ProductMinimalStockModel> getProductMinimalStock() {
    httpClientService.endpoint = "/inventory";
    try {
      return httpClientService.getList("/find-minimal-products", ProductMinimalStockModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ProductMovementModel updateProductMinimalStock(String productName) {
    httpClientService.endpoint = "/inventory";
    try {
      String encodedProductName = URLEncoder.encode(productName, StandardCharsets.UTF_8.toString());
      return httpClientService.post("/order-product?productName=" + encodedProductName, null,
          ProductMovementModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
