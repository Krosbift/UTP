package com.api.routes.shared.models.inventory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.api.routes.shared.models.index.TransactionTypeModel;
import com.api.routes.shared.models.product.ProductModel;

public class ProductMovementModel {
  private Integer updateProductId;
  private String reason;
  private Date updateDate;
  private ProductModel productModel;
  private TransactionTypeModel transactionTypeModel;
  private Integer updateAmount;
  private Double productPrice;
  private Date expirationDate;
  private boolean active;

  public Integer getUpdateProductId() {
    return updateProductId;
  }

  public String getReason() {
    return reason;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public ProductModel getProductModel() {
    return productModel;
  }

  public TransactionTypeModel getTransactionTypeModel() {
    return transactionTypeModel;
  }

  public Integer getUpdateAmount() {
    return updateAmount;
  }

  public Double getProductPrice() {
    return productPrice;
  }

  public Date getExpirationDate() {
    return expirationDate;
  }

  public boolean getActive() {
    return active;
  }

  public ProductMovementModel setProductModel() {
    this.productModel = new ProductModel();
    return this;
  }

  public ProductMovementModel setTransactionTypeModel() {
    this.transactionTypeModel = new TransactionTypeModel();
    return this;
  }

  public ProductMovementModel setUpdateProductId(Integer updateProductId) {
    this.updateProductId = updateProductId;
    return this;
  }

  public ProductMovementModel setUpdateProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.updateProductId = rs.getInt("UPDATEPRODUCTID");
    }
    return this;
  }

  public ProductMovementModel setReason(String reason) {
    this.reason = reason;
    return this;
  }

  public ProductMovementModel setReason(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.reason = rs.getString("REASON");
    }
    return this;
  }

  public ProductMovementModel setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
    return this;
  }

  public ProductMovementModel setUpdateDate(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.updateDate = rs.getDate("UPDATEDATE");
    }
    return this;
  }

  public ProductMovementModel setProductId(Integer productId) {
    this.productModel.setProductId(productId);
    return this;
  }

  public ProductMovementModel setProductId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productModel.setProductId(rs.getInt("PRODUCTID"));
    }
    return this;
  }

  public ProductMovementModel setProductName(String productName) {
    this.productModel.setProductName(productName);
    return this;
  }

  public ProductMovementModel setProductName(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productModel.setProductName(rs.getString("PRODUCTNAME"));
    }
    return this;
  }

  public ProductMovementModel setTransactionTypeId(Integer productCategory) {
    this.transactionTypeModel.setTransactionTypeId(productCategory);
    return this;
  }

  public ProductMovementModel setTransactionTypeId(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.transactionTypeModel.setTransactionTypeId(rs.getInt("TRANSACTIONTYPEID"));
    }
    return this;
  }

  public ProductMovementModel setTransactionType(String transactionType) {
    this.transactionTypeModel.setTransactionType(transactionType);
    return this;
  }

  public ProductMovementModel setTransactionType(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.transactionTypeModel.setTransactionType(rs.getString("TRANSACTIONTYPE"));
    }
    return this;
  }

  public ProductMovementModel setUpdateAmount(Integer updateAmount) {
    this.updateAmount = updateAmount;
    return this;
  }

  public ProductMovementModel setUpdateAmount(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.updateAmount = rs.getInt("UPDATEAMOUNT");
    }
    return this;
  }

  public ProductMovementModel setProductPrice(Double productPrice) {
    this.productPrice = productPrice;
    return this;
  }

  public ProductMovementModel setProductPrice(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.productPrice = rs.getDouble("PRICE");
    }
    return this;
  }

  public ProductMovementModel setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  public ProductMovementModel setExpirationDate(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.expirationDate = rs.getDate("EXPIRATIONDATE");
    }
    return this;
  }

  public ProductMovementModel setActive(boolean active) {
    this.active = active;
    return this;
  }

  public ProductMovementModel setActive(ResultSet rs, boolean setValue) throws SQLException {
    if (setValue) {
      this.active = rs.getBoolean("ACTIVE");
    }
    return this;
  }

  public ProductMovementModel build() {
    return this;
  }
}
