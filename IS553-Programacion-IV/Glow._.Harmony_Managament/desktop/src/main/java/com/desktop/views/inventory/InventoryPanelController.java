package com.desktop.views.inventory;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.inventory.components.BottomPanelComponent;
import com.desktop.views.inventory.components.TopPanelComponent;
import com.desktop.views.inventory.model.GetInventoryDto;
import com.desktop.views.inventory.model.ProductStockModel;
import com.desktop.views.inventory.services.InventoryService;
import com.desktop.views.shared.models.SupplierModel;
import com.desktop.views.shared.models.ViewModel;
import com.desktop.views.shared.models.index.ProductCategoryModel;

public class InventoryPanelController implements ControllerInterface {
  public ViewModel view;
  public InventoryService inventoryService = new InventoryService();
  public ContentPanelController parentController;
  public InventoryPanelComponent inventoryComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;
  public List<ProductStockModel> products;
  public List<ProductCategoryModel> productCategories;
  public List<SupplierModel> suppliers;

  public InventoryPanelController(ContentPanelController controller, ViewModel view) {
    this.parentController = controller;
    this.view = view;
    products = inventoryService.getInventory(new GetInventoryDto());
    productCategories = findCategories();
    suppliers = findSuppliers();
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    inventoryComponent = new InventoryPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("TopPanelComponent", new TopPanelComponent(this, view.getViewName()));
    childComponents.put("BottomPanelComponent", new BottomPanelComponent(this));
  }

  public void setDataTable(GetInventoryDto getProductDto) {
    try {
      List<ProductStockModel> products = inventoryService.getInventory(getProductDto);
      ((BottomPanelComponent) childComponents.get("BottomPanelComponent")).createTable(products);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<ProductCategoryModel> findCategories() {
    return inventoryService.getAllProductCategories();
  }

  public List<SupplierModel> findSuppliers() {
    return inventoryService.getAllSuppliers();
  }
}
