package com.api.routes.suppliers.builder;

import java.util.ArrayList;
import java.util.List;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.suppliers.sql.SupplierSql;
import com.api.routes.suppliers.dto.GetSupplierDto;

public class FindSupplierBuilder {
  public static Binds buildFindSupplier(GetSupplierDto getSupplierDto) {
    StringBuilder sql = new StringBuilder(SupplierSql.FIND_SUPPLIER.getQuery());
    List<Object> params = new ArrayList<Object>();
    if (getSupplierDto.getName() != null) {
      sql.append(" AND ISL.NAME = ?");
      params.add(getSupplierDto.getName());
    }
    if (getSupplierDto.getAddress() != null) {
      sql.append(" AND ISL.ADDRESS = ?");
      params.add(getSupplierDto.getAddress());
    }
    if (getSupplierDto.getPhone() != null) {
      sql.append(" AND ISL.PHONENUMBER = ?");
      params.add(getSupplierDto.getPhone());
    }
    return new Binds(sql.toString(), params.toArray());
  }
}
