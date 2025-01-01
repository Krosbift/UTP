package com.api.routes.shared.mappers.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.api.routes.shared.utils.methods.HasColumns;
import com.api.routes.shared.models.inventory.InventoryModel;

public class InventoryMapper {
  public static RowMapper<InventoryModel> inventoryRowMapper = new RowMapper<InventoryModel>() {
    @Override
    public InventoryModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      InventoryModel inventory = new InventoryModel()
          .setProductMovementModel()
          .setUpdateProductId(rs, HasColumns.verify(rs, "UPDATEPRODUCTID"))
          .setReason(rs, HasColumns.verify(rs, "REASON"))
          .setUpdateDate(rs, HasColumns.verify(rs, "UPDATEDATE"))
          .setProductModel()
          .setProductId(rs, HasColumns.verify(rs, "PRODUCTID"))
          .setProductName(rs, HasColumns.verify(rs, "PRODUCTNAME"))
          .setProductCategoryModel()
          .setProductCategoryId(rs, HasColumns.verify(rs, "CATEGORYID"))
          .setProductCategory(rs, HasColumns.verify(rs, "CATEGORYNAME"))
          .setSupplierModel()
          .setSupplierId(rs, HasColumns.verify(rs, "SUPPLIERID"))
          .setSupplierName(rs, HasColumns.verify(rs, "SUPPLIERNAME"))
          .setTransactionTypeModel()
          .setTransactionTypeId(rs, HasColumns.verify(rs, "TRANSACTIONTYPEID"))
          .setTransactionType(rs, HasColumns.verify(rs, "TRANSACTIONTYPE"))
          .setUpdateAmount(rs, HasColumns.verify(rs, "UPDATEAMOUNT"))
          .setProductPrice(rs, HasColumns.verify(rs, "PRICE"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return inventory;
    }
  };
}
