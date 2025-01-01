package com.api.routes.inventory.usecases;

import java.util.List;
import java.util.stream.Collectors;
import com.api.routes.inventory.model.ProductMinimalStockModel;
import com.api.routes.inventory.model.ProductStockModel;

public class ProductMinimalStockUseCase {
  private final static int minStock = 10;
  private final static int maxStock = 100;

  public int getMaxStock() {
    return maxStock;
  }

  public static boolean isBelowMinimalStock(int stock) {
    return stock < minStock;
  }

  public static List<ProductMinimalStockModel> getMinimalProducts(List<ProductStockModel> products) {
    return products.stream()
        .filter(product -> isBelowMinimalStock(product.getStock()))
        .map(product -> new ProductMinimalStockModel()
            .setProductName(product.getProductName())
            .setProductCategory(product.getProductCategory())
            .setStock(product.getStock()))
        .collect(Collectors.toList());
  }
}
