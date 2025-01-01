package com.desktop.views.shared.models;

public class SupplierModel {
  private Integer supplierId;
  private String name;
  private String address;
  private String phone;
  private boolean active;

  public Integer getSupplierId() {
    return supplierId;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public boolean getActive() {
    return active;
  }

  public SupplierModel setSupplierId(Integer supplierId) {
    this.supplierId = supplierId;
    return this;
  }

  public SupplierModel setName(String name) {
    this.name = name;
    return this;
  }

  public SupplierModel setAddress(String address) {
    this.address = address;
    return this;
  }

  public SupplierModel setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public SupplierModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public SupplierModel build() {
    return this;
  }
}
