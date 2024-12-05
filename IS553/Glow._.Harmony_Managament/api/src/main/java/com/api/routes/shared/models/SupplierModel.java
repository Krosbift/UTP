package com.api.routes.shared.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierModel {
  private Integer supplierId;
  private String supplierName;
  private String address;
  private String phone;
  private boolean active;

  public Integer getSupplierId() {
    return supplierId;
  }

  public String getName() {
    return supplierName;
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

  public SupplierModel setSupplierId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.supplierId = rs.getInt("SUPPLIERID");
    }
    return this;
  }

  public SupplierModel setName(String supplierName) {
    this.supplierName = supplierName;
    return this;
  }

  public SupplierModel setName(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.supplierName = rs.getString("NAME");
    }
    return this;
  }

  public SupplierModel setAddress(String address) {
    this.address = address;
    return this;
  }

  public SupplierModel setAddress(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.address = rs.getString("ADDRESS");
    }
    return this;
  }

  public SupplierModel setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public SupplierModel setPhone(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.phone = rs.getString("PHONENUMBER");
    }
    return this;
  }

  public SupplierModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public SupplierModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public SupplierModel build() {
    return this;
  }
}
