package com.api.routes.shared.mappers.index;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.api.routes.shared.models.index.TransactionTypeModel;
import com.api.routes.shared.utils.methods.HasColumns;

public class TransactionTypeMapper {
  public static RowMapper<TransactionTypeModel> transactionTypesRowMapper = new RowMapper<TransactionTypeModel>() {
    @Override
    public TransactionTypeModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      TransactionTypeModel transactionType = new TransactionTypeModel()
          .setTransactionTypeId(rs, HasColumns.verify(rs, "TRANSACTIONTYPEID"))
          .setTransactionType(rs, HasColumns.verify(rs, "TRANSACTIONTYPE"))
          .setDescription(rs, HasColumns.verify(rs, "DESCRIPTION"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return transactionType;
    }
  };
}
