package com.api.routes.inventory;

import java.util.List;
import java.sql.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Service;
import com.api.routes.inventory.builder.*;
import com.api.routes.inventory.dto.*;
import com.api.routes.inventory.model.*;
import com.api.routes.inventory.usecases.*;
import com.api.routes.shared.mappers.inventory.*;
import com.api.routes.shared.models.inventory.*;
import com.api.routes.inventory.sql.InventorySql;
import com.api.routes.shared.utils.query.Binds;

@Service
public class InventoryService {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  private ProductMovementModel findProductMovementById(int updateProductId) {
    return jdbcTemplate
        .query(InventorySql.FIND_UPDATEPRODUCT_BY_ID.getQuery(), ProductMovementMapper.updateProductRowMapper,
            updateProductId)
        .get(0);
  }

  public List<ProductMovementModel> findAllUpdateProducts() {
    try {
      return jdbcTemplate.query(InventorySql.FIND_ALL_UPDATEPRODUCT.getQuery(),
          ProductMovementMapper.updateProductRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<ProductMovementModel> findUpdateProduct(GetProductMovementDto getProductMovementDto) {
    Binds binds = FindMovementBuilder.buildFindUpdateProduct(getProductMovementDto);
    try {
      return jdbcTemplate.query(binds.getSql(), ProductMovementMapper.updateProductRowMapper, binds.getParams());
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public ProductMovementModel createUpdateProduct(CreateProductMovementDto updateProductMovementDto) {
    try {
      Binds binds = CreateMovementBuilder.buildCreateUpdateProduct(updateProductMovementDto);
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
      return findProductMovementById(generatedId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public ProductMovementModel updateUpdateProduct(UpdateProductMovementDto updateProductMovementDto,
      int updateProductId) {
    try {
      ProductMovementModel updateProductModel = findProductMovementById(updateProductId);
      if (ValidateModifyUseCase.validateStockInModify(
          updateProductMovementDto,
          updateProductModel,
          findInventory(new GetInventoryDto().setProductId(updateProductModel.getProductModel().getProductId())).get(0)
              .getStock())) {
        throw new RuntimeException("The product stock is insufficient for the sale.");
      }
      Binds binds = UpdateMovementBuilder.buildUpdateUpdateProducts(updateProductMovementDto, updateProductId);
      jdbcTemplate.update(binds.getSql(), binds.getParams());
      return findProductMovementById(updateProductId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int activeUpdateProduct(int updateProductId) {
    try {
      return jdbcTemplate.update(InventorySql.ACTIVE_UPDATEPRODUCT.getQuery(), updateProductId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public int deleteUpdateProduct(int updateProductId) {
    try {
      return jdbcTemplate.update(InventorySql.DELETE_UPDATEPRODUCT.getQuery(), updateProductId);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<ProductStockModel> findInventory(GetInventoryDto getInventoryDto) {
    Binds binds = FindInventoryBuilder.buildFindInventory(getInventoryDto);
    try {
      List<InventoryModel> result = jdbcTemplate.query(binds.getSql(), InventoryMapper.inventoryRowMapper,
          binds.getParams());
      return InvetoryManagmentUseCase.calculateStock(result);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<InventoryModel> findAllInventory() {
    try {
      return jdbcTemplate.query(InventorySql.FIND_ALL_INVENTORY.getQuery(),
          InventoryMapper.inventoryRowMapper);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public List<ProductMinimalStockModel> findProductsMinimals(GetInventoryDto getInventoryDto) {
    try {
      List<ProductStockModel> inventory = findInventory(getInventoryDto);
      return ProductMinimalStockUseCase.getMinimalProducts(inventory);
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }

  public ProductMovementModel orderProduct(String productName) {
    try {
      List<ProductStockModel> inventory = findInventory(new GetInventoryDto());
      int productId = 0;
      for (ProductStockModel product : inventory) {
        if (product.getProductName().equals(productName)) {
          productId = product.getProductId();
        }
      }
      return createUpdateProduct(new CreateProductMovementDto()
          .setReason("Compra proveedor")
          .setProductId(productId)
          .setTransactionTypeId(1)
          .setUpdateAmount(100));
    } catch (Exception error) {
      throw new RuntimeException("An unexpected error occurred: " + error.getMessage());
    }
  }
}
