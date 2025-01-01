package com.desktop.views.minimals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.desktop.core.navigation.components.Content.ContentPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.minimals.components.MinimalTableComponent;
import com.desktop.views.minimals.model.ProductMinimalStockModel;
import com.desktop.views.minimals.service.MinimalService;
import com.desktop.views.shared.models.ViewModel;

public class MinimalPanelController implements ControllerInterface {
  public ViewModel view;
  public MinimalService minimalService = new MinimalService();
  public ContentPanelController parentController;
  public MinimalPanelComponent minimalPanelComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public MinimalPanelController(ContentPanelController controller, ViewModel view) {
    this.parentController = controller;
    this.view = view;
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    minimalPanelComponent = new MinimalPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
    childComponents.put("MinimalTableComponent", new MinimalTableComponent(this));
  }

  public void setDataTable() {
    try {
      List<ProductMinimalStockModel> minimals = minimalService.getProductMinimalStock();
      ((MinimalTableComponent) childComponents.get("MinimalTableComponent")).createTable(minimals);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
