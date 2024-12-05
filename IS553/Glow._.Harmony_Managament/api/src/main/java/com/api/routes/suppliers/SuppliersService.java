package com.api.routes.suppliers;

import java.util.List;
import java.sql.PreparedStatement;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.suppliers.sql.SupplierSql;
import com.api.routes.shared.models.SupplierModel;
import com.api.routes.suppliers.dto.GetSupplierDto;
import com.api.routes.suppliers.dto.UpdateSupplierDto;
import com.api.routes.suppliers.dto.CreateSupplierDto;
import com.api.routes.suppliers.builder.CreateSupplierBuilder;
import com.api.routes.suppliers.builder.FindSupplierBuilder;
import com.api.routes.suppliers.builder.UpdateSupplierBuilder;
import com.api.routes.shared.mappers.supplier.SupplierMapper;

@Service
public class SuppliersService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private SupplierModel findSupplierById(int supplierId) {
    return jdbcTemplate.query(SupplierSql.FIND_SUPPLIER_BY_ID.getQuery(), SupplierMapper.supplierRowMapper, supplierId).get(0);
  }

  public List<SupplierModel> findAllSuppliers() {
    try {
      return jdbcTemplate.query(SupplierSql.FIND_ALL_SUPPLIERS.getQuery(), SupplierMapper.supplierRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<SupplierModel> findSupplier(GetSupplierDto getSupplierDto) {
    Binds binds = FindSupplierBuilder.buildFindSupplier(getSupplierDto);
    try {
      return jdbcTemplate.query(binds.getSql(), SupplierMapper.supplierRowMapper, binds.getParams());
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public SupplierModel createSupplier(CreateSupplierDto createSupplierDto) {
    Binds binds = CreateSupplierBuilder.buildCreateSupplier(createSupplierDto);
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(connection -> {
        PreparedStatement statement = connection.prepareStatement(binds.getSql(), new String[] { "id" });
        for (int i = 0; i < binds.getParams().length; i++) {
          statement.setObject(i + 1, binds.getParams()[i]);
        }
        return statement;
      }, keyHolder);
      @SuppressWarnings("null")
      int generatedId = keyHolder.getKey().intValue();
      return findSupplierById(generatedId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public SupplierModel updateSupplier(UpdateSupplierDto updateSupplierDto, int supplierId) {
    Binds binds = UpdateSupplierBuilder.buildUpdateSupplier(updateSupplierDto, supplierId);
    try {
      jdbcTemplate.update(binds.getSql(), binds.getParams());
      return findSupplierById(supplierId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int activateSupplier(int supplierId) {
    try {
      jdbcTemplate.update(SupplierSql.ACTIVE_SUPPLIER.getQuery(), supplierId);
      return supplierId;
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int deleteSupplier(int supplierId) {
    try {
      jdbcTemplate.update(SupplierSql.DELETE_SUPPLIER.getQuery(), supplierId);
      return supplierId;
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }
}
