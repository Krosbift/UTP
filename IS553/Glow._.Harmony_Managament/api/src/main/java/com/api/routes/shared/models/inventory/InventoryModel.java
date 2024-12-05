package com.api.routes.shared.models.inventory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.api.routes.shared.models.SupplierModel;
import com.api.routes.shared.models.index.ProductCategoryModel;
import com.api.routes.shared.models.index.TransactionTypeModel;
import com.api.routes.shared.models.product.ProductModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class InventoryModel {
  private ProductMovementModel productMovementModel;

  public ProductMovementModel getProductMovementModel() {
    return productMovementModel;
  }

  @JsonIgnore
  public ProductModel getProductModel() {
    return productMovementModel.getProductModel();
  }

  @JsonIgnore
  public ProductCategoryModel getProductCategoryModel() {
    return productMovementModel.getProductModel().getProductCategoryModel();
  }

  @JsonIgnore
  public SupplierModel getSupplierModel() {
    return productMovementModel.getProductModel().getSupplierModel();
  }

  @JsonIgnore
  public TransactionTypeModel getTransactionTypeModel() {
    return productMovementModel.getTransactionTypeModel();
  }

  public InventoryModel setProductMovementModel() {
    this.productMovementModel = new ProductMovementModel();
    return this;
  }

  public InventoryModel setProductModel() {
    this.productMovementModel.setProductModel();
    return this;
  }

  public InventoryModel setProductCategoryModel() {
    this.productMovementModel.getProductModel().setProductCategoryModel();
    return this;
  }

  public InventoryModel setSupplierModel() {
    this.productMovementModel.getProductModel().setSupplierModel();
    return this;
  }

  public InventoryModel setTransactionTypeModel() {
    this.productMovementModel.setTransactionTypeModel();
    return this;
  }

  public InventoryModel setUpdateProductId(Integer updateProductId) {
    this.productMovementModel.setUpdateProductId(updateProductId);
    return this;
  }

  public InventoryModel setUpdateProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setUpdateProductId(rs.getInt("UPDATEPRODUCTID"));
    }
    return this;
  }

  public InventoryModel setReason(String reason) {
    this.productMovementModel.setReason(reason);
    return this;
  }

  public InventoryModel setReason(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setReason(rs.getString("REASON"));
    }
    return this;
  }

  public InventoryModel setUpdateDate(Date updateDate) {
    this.productMovementModel.setUpdateDate(updateDate);
    return this;
  }

  public InventoryModel setUpdateDate(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setUpdateDate(rs.getDate("UPDATEDATE"));
    }
    return this;
  }

  public InventoryModel setProductId(Integer productId) {
    this.productMovementModel.setProductId(productId);
    return this;
  }

  public InventoryModel setProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setProductId(rs.getInt("PRODUCTID"));
    }
    return this;
  }

  public InventoryModel setProductName(String productName) {
    this.productMovementModel.setProductName(productName);
    return this;
  }

  public InventoryModel setProductName(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setProductName(rs.getString("PRODUCTNAME"));
    }
    return this;
  }

  public InventoryModel setProductCategoryId(Integer productCategoryId) {
    this.productMovementModel.getProductModel().setProductCategoryId(productCategoryId);
    return this;
  }

  public InventoryModel setProductCategoryId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.getProductModel().setProductCategoryId(rs.getInt("CATEGORYID"));
    }
    return this;
  }

  public InventoryModel setProductCategory(String productCategory) {
    this.productMovementModel.getProductModel().setProductCategory(productCategory);
    return this;
  }

  public InventoryModel setProductCategory(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.getProductModel().setProductCategory(rs.getString("CATEGORYNAME"));
    }
    return this;
  }

  public InventoryModel setSupplierId(Integer supplierId) {
    this.productMovementModel.getProductModel().setSupplierId(supplierId);
    return this;
  }

  public InventoryModel setSupplierId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.getProductModel().setSupplierId(rs.getInt("SUPPLIERID"));
    }
    return this;
  }

  public InventoryModel setSupplierName(String name) {
    this.productMovementModel.getProductModel().setSupplierName(name);
    return this;
  }

  public InventoryModel setSupplierName(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.getProductModel().setSupplierName(rs.getString("SUPPLIERNAME"));
    }
    return this;
  }

  public InventoryModel setTransactionTypeId(Integer transactionTypeId) {
    this.productMovementModel.setTransactionTypeId(transactionTypeId);
    return this;
  }

  public InventoryModel setTransactionTypeId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setTransactionTypeId(rs.getInt("TRANSACTIONTYPEID"));
    }
    return this;
  }

  public InventoryModel setTransactionType(String transactionType) {
    this.productMovementModel.setTransactionType(transactionType);
    return this;
  }

  public InventoryModel setTransactionType(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setTransactionType(rs.getString("TRANSACTIONTYPE"));
    }
    return this;
  }

  public InventoryModel setUpdateAmount(Integer updateAmount) {
    this.productMovementModel.setUpdateAmount(updateAmount);
    return this;
  }

  public InventoryModel setUpdateAmount(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setUpdateAmount(rs.getInt("UPDATEAMOUNT"));
    }
    return this;
  }

  public InventoryModel setProductPrice(Double productPrice) {
    this.productMovementModel.setProductPrice(productPrice);
    return this;
  }

  public InventoryModel setProductPrice(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setProductPrice(rs.getDouble("PRICE"));
    }
    return this;
  }

  public InventoryModel setActive(boolean active) {
    this.productMovementModel.setActive(active);
    return this;
  }

  public InventoryModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productMovementModel.setActive(rs.getBoolean("ACTIVE"));
    }
    return this;
  }

  public InventoryModel build() {
    return this;
  }
}
