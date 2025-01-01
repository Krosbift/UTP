package com.desktop.views.suppliers.model;

public class GetSupplierDto {
  private String name;
  private String address;
  private String phone;

  public String getName() {
    return name;
  }

  public GetSupplierDto setName(String name) {
    this.name = name;
    return this;
  }

  public GetSupplierDto setName(String name, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.name = name;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public GetSupplierDto setAddress(String address) {
    this.address = address;
    return this;
  }

  public GetSupplierDto setAddress(String address, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.address = address;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public GetSupplierDto setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public GetSupplierDto setPhone(String phone, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.phone = phone;
    return this;
  }

  /**
   * Builds and returns the current instance of GetSupplierDto.
   *
   * @return the current instance of GetSupplierDto
   */
  public GetSupplierDto build() {
    return this;
  }
}
