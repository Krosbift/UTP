package com.desktop.views.inventory.services;

import java.util.List;
import com.desktop.core.database.service.HttpClientService;
import com.desktop.views.inventory.model.GetInventoryDto;
import com.desktop.views.inventory.model.ProductStockModel;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.index.ProductCategoryModel;

public class InventoryService {
  private final HttpClientService httpClientService = new HttpClientService();

  public List<ProductStockModel> getInventory(GetInventoryDto getDto) {
    httpClientService.endpoint = "/inventory";
    StringBuilder url = new StringBuilder("/find-inventory");
    boolean firstParam = true;

    if (getDto.getProductId() != null) {
      url.append("?productId=").append(getDto.getProductId());
      firstParam = false;
    }

    if (getDto.getCategoryId() != null) {
      url.append(firstParam ? "?" : "&").append("categoryId=").append(getDto.getCategoryId());
      firstParam = false;
    }

    if (getDto.getSupplierId() != null) {
      url.append(firstParam ? "?" : "&").append("supplierId=").append(getDto.getSupplierId());
      firstParam = false;
    }

    try {
      return httpClientService.getList(url.toString(), ProductStockModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<ProductStockModel> getAllInventory() {
    httpClientService.endpoint = "/inventory";
    StringBuilder url = new StringBuilder("/find-all-inventory");
    try {
      return httpClientService.getList(url.toString(), ProductStockModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<ProductCategoryModel> getAllProductCategories() {
    httpClientService.endpoint = "/index";
    StringBuilder url = new StringBuilder("/product-categories");
    try {
      return (List<ProductCategoryModel>) httpClientService.getList(url.toString(), ProductCategoryModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<SupplierModel> getAllSuppliers() {
    httpClientService.endpoint = "/suppliers";
    StringBuilder url = new StringBuilder("/find-supplier");
    try {
      return (List<SupplierModel>) httpClientService.getList(url.toString(), SupplierModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
