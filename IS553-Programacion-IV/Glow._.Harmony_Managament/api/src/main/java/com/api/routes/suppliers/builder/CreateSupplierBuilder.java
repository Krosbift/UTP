package com.api.routes.suppliers.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.suppliers.dto.CreateSupplierDto;

public class CreateSupplierBuilder {
  public static Binds buildCreateSupplier(CreateSupplierDto createSupplierDto) {
    StringBuilder sql = new StringBuilder("INSERT INTO TB_IMS_SUPPLIERS ");
    StringBuilder columns = new StringBuilder("(");
    StringBuilder values = new StringBuilder("VALUES (");
    List<Object> params = new ArrayList<>();
    if (createSupplierDto.getName() != null) {
      columns.append("NAME, ");
      values.append("?, ");
      params.add(createSupplierDto.getName());
    }
    if (createSupplierDto.getAddress() != null) {
      columns.append("ADDRESS, ");
      values.append("?, ");
      params.add(createSupplierDto.getAddress());
    }
    if (createSupplierDto.getPhone() != null) {
      columns.append("PHONENUMBER, ");
      values.append("?, ");
      params.add(createSupplierDto.getPhone());
    }
    if (columns.charAt(columns.length() - 2) == ',') {
      columns.setLength(columns.length() - 2);
      values.setLength(values.length() - 2);
    }
    columns.append(") ");
    values.append(") ");
    sql.append(columns).append(values);
    return new Binds(sql.toString(), params.toArray());
  }
}
