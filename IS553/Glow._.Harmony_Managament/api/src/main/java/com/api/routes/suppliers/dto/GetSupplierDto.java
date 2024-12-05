package com.api.routes.suppliers.dto;

public class GetSupplierDto {
  private String name;
  private String address;
  private String phone;

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public GetSupplierDto setName(String name) {
    this.name = name;
    return this;
  }

  public GetSupplierDto setAddress(String address) {
    this.address = address;
    return this;
  }

  public GetSupplierDto setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public GetSupplierDto build() {
    return this;
  }
}
