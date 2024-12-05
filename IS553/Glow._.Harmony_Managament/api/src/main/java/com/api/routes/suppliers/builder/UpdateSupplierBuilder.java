package com.api.routes.suppliers.builder;

import java.util.List;
import java.util.ArrayList;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.suppliers.dto.UpdateSupplierDto;

public class UpdateSupplierBuilder {
  public static Binds buildUpdateSupplier(UpdateSupplierDto updateSupplierDto, int supplierId) {
    StringBuilder sql = new StringBuilder("UPDATE TB_IMS_SUPPLIERS SET ");
    List<Object> params = new ArrayList<>();
    if (updateSupplierDto.getName() != null) {
      sql.append("NAME = ?, ");
      params.add(updateSupplierDto.getName());
    }
    if (updateSupplierDto.getAddress() != null) {
      sql.append("ADDRESS = ?, ");
      params.add(updateSupplierDto.getAddress());
    }
    if (updateSupplierDto.getPhone() != null) {
      sql.append("PHONENUMBER = ?, ");
      params.add(updateSupplierDto.getPhone());
    }
    if (sql.charAt(sql.length() - 2) == ',') {
      sql.setLength(sql.length() - 2);
    }
    sql.append(" WHERE SUPPLIERID = ?");
    params.add(supplierId);
    return new Binds(sql.toString(), params.toArray());
  }
}
