package com.desktop.views.IntsOuts.services;

import java.util.List;
import com.desktop.core.database.service.HttpClientService;
import com.desktop.views.IntsOuts.model.CreateUpdateProductDto;
import com.desktop.views.IntsOuts.model.GetUpdateProductDto;
import com.desktop.views.IntsOuts.model.UpdateProductUpdateDto;
import com.desktop.views.shared.models.index.TransactionTypeModel;
import com.desktop.views.shared.models.inventory.ProductMovementModel;
import com.desktop.views.shared.models.product.ProductModel;

public class IntsOutsService {
  private final HttpClientService httpClientService = new HttpClientService();

  public List<ProductMovementModel> getIntsOuts(GetUpdateProductDto getDto) {
    httpClientService.endpoint = "/inventory";
    StringBuilder url = new StringBuilder("/find-movement-product");
    boolean firstParam = true;

    if (getDto.getProductId() != null) {
      url.append("?productId=").append(getDto.getProductId());
      firstParam = false;
    }

    if (getDto.getTransactionTypeId() != null) {
      url.append(firstParam ? "?" : "&").append("transactionTypeId=").append(getDto.getTransactionTypeId());
    }

    try {
      return httpClientService.getList(url.toString(), ProductMovementModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<ProductMovementModel> findAllInstOuts() {
    httpClientService.endpoint = "/inventory";
    try {
      return httpClientService.getList("/find-all-movement-products", ProductMovementModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ProductMovementModel createIntsOuts(CreateUpdateProductDto intsOutsModel) {
    httpClientService.endpoint = "/inventory";
    try {
      return httpClientService.post("/create-movement-product", intsOutsModel, ProductMovementModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ProductMovementModel updateIntsOuts(UpdateProductUpdateDto intsOutsModel, int updateProductId) {
    httpClientService.endpoint = "/inventory";
    try {
      return httpClientService.patch("/update-movement-product/" + updateProductId, intsOutsModel,
          ProductMovementModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Integer activateIntsOuts(int updateProductId) {
    httpClientService.endpoint = "/inventory";
    try {
      return httpClientService.patch("/activate-movement-product/" + updateProductId, null, Integer.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Integer deleteIntsOuts(int updateProductId) {
    httpClientService.endpoint = "/inventory";
    try {
      return httpClientService.delete("/delete-movement-product/" + updateProductId, Integer.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<ProductModel> getProducts() {
    httpClientService.endpoint = "/products";
    StringBuilder url = new StringBuilder("/find-product");
    try {
      return (List<ProductModel>) httpClientService.getList(url.toString(), ProductModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<TransactionTypeModel> getTransactionTypes() {
    httpClientService.endpoint = "/index";
    try {
      return httpClientService.getList("/transaction-types", TransactionTypeModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
