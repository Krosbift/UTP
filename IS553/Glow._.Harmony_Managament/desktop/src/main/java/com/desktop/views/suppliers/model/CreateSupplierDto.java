package com.desktop.views.suppliers.model;

public class CreateSupplierDto {
  private String name;
  private String address;
  private String phone;

  public String getName() {
    return name;
  }

  public CreateSupplierDto setName(String name) {
    this.name = name;
    return this;
  }

  public CreateSupplierDto setName(String name, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.name = name;
    return this;
  }

  public String getAddress() {
    return address;
  }

  public CreateSupplierDto setAddress(String address) {
    this.address = address;
    return this;
  }

  public CreateSupplierDto setAddress(String address, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.address = address;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public CreateSupplierDto setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public CreateSupplierDto setPhone(String phone, boolean setValue) {
    if (!setValue) {
      return this;
    }
    this.phone = phone;
    return this;
  }

  /**
   * Builds and returns the current instance of CreateSupplierDto.
   *
   * @return the current instance of CreateSupplierDto
   */
  public CreateSupplierDto build() {
    return this;
  }
}
