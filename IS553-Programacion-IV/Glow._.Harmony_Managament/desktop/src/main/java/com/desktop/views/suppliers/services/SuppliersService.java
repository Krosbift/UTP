package com.desktop.views.suppliers.services;

import java.util.List;
import com.desktop.core.database.service.HttpClientService;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.suppliers.model.CreateSupplierDto;
import com.desktop.views.suppliers.model.GetSupplierDto;
import com.desktop.views.suppliers.model.UpdateSupplierDto;

public class SuppliersService {
  private final HttpClientService httpClientService = new HttpClientService();

  public List<SupplierModel> getSuppliers(GetSupplierDto getDto) {
    httpClientService.endpoint = "/suppliers";
    StringBuilder url = new StringBuilder("/find-supplier");
    boolean firstParam = true;

    if (getDto.getName() != null) {
      url.append("?name=").append(getDto.getName());
      firstParam = false;
    }

    if (getDto.getAddress() != null) {
      url.append(firstParam ? "?" : "&").append("address=").append(getDto.getAddress());
    }

    if (getDto.getPhone() != null) {
      url.append(firstParam ? "?" : "&").append("phone=").append(getDto.getPhone());
    }

    try {
      return httpClientService.getList(url.toString(), SupplierModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<SupplierModel> getAllSuppliers() {
    httpClientService.endpoint = "/suppliers";
    StringBuilder url = new StringBuilder("/find-all-suppliers");
    try {
      return httpClientService.getList(url.toString(), SupplierModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public SupplierModel createSupplier(CreateSupplierDto createDto) {
    httpClientService.endpoint = "/suppliers";
    try {
      return httpClientService.post("/create-supplier", createDto, SupplierModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public SupplierModel updateSupplier(UpdateSupplierDto updateDto, int supplierId) {
    httpClientService.endpoint = "/suppliers";
    try {
      return httpClientService.patch("/update-supplier/" + supplierId, updateDto, SupplierModel.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Integer activateSupplier(int supplierId) {
    httpClientService.endpoint = "/suppliers";
    try {
      return httpClientService.patch("/activate-supplier/" + supplierId, supplierId, Integer.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Integer deactivateSupplier(int supplierId) {
    httpClientService.endpoint = "/suppliers";
    try {
      return httpClientService.delete("/delete-supplier/" + supplierId, supplierId, Integer.class);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
