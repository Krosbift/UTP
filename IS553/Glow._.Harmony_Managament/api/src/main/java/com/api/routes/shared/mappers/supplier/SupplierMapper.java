package com.api.routes.shared.mappers.supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.api.routes.shared.models.SupplierModel;
import com.api.routes.shared.utils.methods.HasColumns;

public class SupplierMapper {
  public static RowMapper<SupplierModel> supplierRowMapper = new RowMapper<SupplierModel>() {
    @Override
    public SupplierModel mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {
      SupplierModel supplier = new SupplierModel()
          .setSupplierId(rs, HasColumns.verify(rs, "SUPPLIERID"))
          .setName(rs, HasColumns.verify(rs, "NAME"))
          .setAddress(rs, HasColumns.verify(rs, "ADDRESS"))
          .setPhone(rs, HasColumns.verify(rs, "PHONENUMBER"))
          .setActive(rs, HasColumns.verify(rs, "ACTIVE"))
          .build();
      return supplier;
    }
  };
}
