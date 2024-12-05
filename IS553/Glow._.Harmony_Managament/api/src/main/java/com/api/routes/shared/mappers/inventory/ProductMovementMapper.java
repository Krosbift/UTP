package com.api.routes.shared.mappers.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.api.routes.shared.utils.methods.HasColumns;
import com.api.routes.shared.models.inventory.ProductMovementModel;

public class ProductMovementMapper {
  public static RowMapper<ProductMovementModel> updateProductRowMapper = new RowMapper<ProductMovementModel>() {
    @Override
    public ProductMovementModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      ProductMovementModel updateProduct = new ProductMovementModel()
          .setUpdateProductId(rs, HasColumns.verify(rs, "UPDATEPRODUCTID"))
          .setReason(rs, HasColumns.verify(rs, "REASON"))
          .setUpdateDate(rs, HasColumns.verify(rs, "UPDATEDATE"))
          .setProductModel()
          .setProductId(rs, HasColumns.verify(rs, "PRODUCTID"))
          .setProductName(rs, HasColumns.verify(rs, "PRODUCTNAME"))
          .setTransactionTypeModel()
          .setTransactionTypeId(rs, HasColumns.verify(rs, "TRANSACTIONTYPEID"))
          .setTransactionType(rs, HasColumns.verify(rs, "TRANSACTIONTYPE"))
          .setUpdateAmount(rs, HasColumns.verify(rs, "UPDATEAMOUNT"))
          .setProductPrice(rs, HasColumns.verify(rs, "PRICE"))
          .setExpirationDate(rs, HasColumns.verify(rs, "EXPIRATIONDATE"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return updateProduct;
    }
  };
}
