package com.desktop.views.shared.models.inventory;

import java.sql.Date;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.product.ProductModel;
import com.desktop.views.shared.models.index.ProductCategoryModel;
import com.desktop.views.shared.models.index.TransactionTypeModel;

public class InventoryModel {
  private ProductMovementModel productMovementModel;

  public ProductMovementModel getProductMovementModel() {
    return productMovementModel;
  }

  public ProductModel getProductModel() {
    return productMovementModel.getProductModel();
  }

  public ProductCategoryModel getProductCategoryModel() {
    return this.getProductModel().getProductCategoryModel();
  }

  public SupplierModel getSupplierModel() {
    return productMovementModel.getProductModel().getSupplierModel();
  }

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

  public InventoryModel setReason(String reason) {
    this.productMovementModel.setReason(reason);
    return this;
  }

  public InventoryModel setUpdateDate(Date updateDate) {
    this.productMovementModel.setUpdateDate(updateDate);
    return this;
  }

  public InventoryModel setProductId(Integer productId) {
    this.productMovementModel.setProductId(productId);
    return this;
  }

  public InventoryModel setProductName(String productName) {
    this.productMovementModel.setProductName(productName);
    return this;
  }

  public InventoryModel setProductCategoryId(Integer productCategoryId) {
    this.productMovementModel.getProductModel().setProductCategoryId(productCategoryId);
    return this;
  }

  public InventoryModel setProductCategory(String productCategory) {
    this.productMovementModel.getProductModel().setProductCategory(productCategory);
    return this;
  }

  public InventoryModel setUnitPrice(Integer unitPrice) {
    this.productMovementModel.setProductPrice(unitPrice);
    return this;
  }

  public InventoryModel setSupplierId(Integer supplierId) {
    this.productMovementModel.getProductModel().setSupplierId(supplierId);
    return this;
  }

  public InventoryModel setSupplierName(String name) {
    this.productMovementModel.getProductModel().setSupplierName(name);
    return this;
  }

  public InventoryModel setTransactionTypeId(Integer transactionTypeId) {
    this.productMovementModel.setTransactionTypeId(transactionTypeId);
    return this;
  }

  public InventoryModel setTransactionType(String transactionType) {
    this.productMovementModel.setTransactionType(transactionType);
    return this;
  }

  public InventoryModel setUpdateAmount(Integer updateAmount) {
    this.productMovementModel.setUpdateAmount(updateAmount);
    return this;
  }

  public InventoryModel setActive(boolean active) {
    this.productMovementModel.setActive(active);
    return this;
  }

  public InventoryModel build() {
    return this;
  }
}
