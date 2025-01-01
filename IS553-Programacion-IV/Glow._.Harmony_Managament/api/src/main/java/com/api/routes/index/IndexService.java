package com.api.routes.index;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.api.routes.index.sql.IndexSql;
import com.api.routes.shared.mappers.index.DocumentTypeMapper;
import com.api.routes.shared.mappers.index.ProductCategoryMapper;
import com.api.routes.shared.mappers.index.RoleTypeMapper;
import com.api.routes.shared.mappers.index.TransactionTypeMapper;
import com.api.routes.shared.mappers.index.ViewMapper;
import com.api.routes.shared.models.*;
import com.api.routes.shared.models.index.*;

@Service
public class IndexService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<ViewModel> findAllViews() {
    try {
      return jdbcTemplate.query(IndexSql.FIND_VIEWS.getSql(), ViewMapper.viewsRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<DocumentTypeModel> findAllDocumentTypes() {
    try {
      return jdbcTemplate.query(IndexSql.FIND_DOCUMENT_TYPES.getSql(), DocumentTypeMapper.documentTypesRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<ProductCategoryModel> findAllProductCategories() {
    try {
      return jdbcTemplate.query(IndexSql.FIND_PRODUCT_CATEGORIES.getSql(), ProductCategoryMapper.productCategoriesRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<RoleTypeModel> findAllRoleTypes() {
    try {
      return jdbcTemplate.query(IndexSql.FIND_ROLE_TYPES.getSql(), RoleTypeMapper.roleTypesRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<TransactionTypeModel> findAllTransactionTypes() {
    try {
      return jdbcTemplate.query(IndexSql.FIND_TRANSACTION_TYPES.getSql(), TransactionTypeMapper.transactionTypesRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }
}
