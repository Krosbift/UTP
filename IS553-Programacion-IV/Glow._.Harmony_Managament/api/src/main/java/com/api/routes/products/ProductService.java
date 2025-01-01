package com.api.routes.products;

import java.util.List;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import com.api.routes.products.sql.ProducSql;
import com.api.routes.shared.mappers.product.ProductMapper;
import com.api.routes.shared.models.product.ProductModel;
import com.api.routes.shared.utils.query.Binds;
import com.api.routes.products.builder.*;
import com.api.routes.products.dto.*;

@Service
public class ProductService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  protected ProductModel findProductById(int productId) {
    return jdbcTemplate.query(ProducSql.FIND_PRODUCT_BY_ID.getQuery(), ProductMapper.productRowMapper, productId)
        .get(0);
  }

  public List<ProductModel> findAllProducts() {
    try {
      return jdbcTemplate.query(ProducSql.FIND_ALL_PRODUCTS.getQuery(), ProductMapper.productRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<ProductModel> findProduct(GetProductDto getProductDto) {
    Binds binds = FindProductBuilder.buildFindProduct(getProductDto);
    try {
      return jdbcTemplate.query(binds.getSql(), ProductMapper.productRowMapper, binds.getParams());
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public ProductModel createProduct(CreateProductDto createProductDto) {
    Binds binds = CreateProductBuilder.buildCreateProduct(createProductDto);
    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(connection -> {
        PreparedStatement statement = connection.prepareStatement(binds.getSql(), new String[] { "product_id" });
        for (int i = 0; i < binds.getParams().length; i++) {
          statement.setObject(i + 1, binds.getParams()[i]);
        }
        return statement;
      }, keyHolder);
      @SuppressWarnings("null")
      int generatedId = keyHolder.getKey().intValue();
      return findProductById(generatedId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public ProductModel updateProduct(UpdateProductDto updateProductDto, int productId) {
    Binds binds = UpdateProductBuilder.buildUpdateProduc(updateProductDto, productId);
    try {
      jdbcTemplate.update(binds.getSql(), binds.getParams());
      return findProductById(productId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int activeProduct(int productId) {
    try {
      jdbcTemplate.update(ProducSql.ACTIVE_PRODUCT.getQuery(), productId);
      return productId;
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int deleteProduct(int productId) {
    try {
      jdbcTemplate.update(ProducSql.DELETE_PRODUCT.getQuery(), productId);
      return productId;
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }
}
