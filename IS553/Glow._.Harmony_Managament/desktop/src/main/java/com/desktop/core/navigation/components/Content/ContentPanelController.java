package com.desktop.core.navigation.components.Content;

import java.util.Map;
import java.util.HashMap;
import java.awt.Component;
import java.awt.Container;
import com.desktop.core.navigation.NavigationPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.core.utils.interfaces.ControllerInterface;
import com.desktop.views.IntsOuts.IntsOutsPanelController;
import com.desktop.views.inventory.InventoryPanelController;
import com.desktop.views.minimals.MinimalPanelController;
import com.desktop.views.products.ProductsPanelController;
import com.desktop.views.shared.models.ViewModel;
import com.desktop.views.shared.models.user.UserModel;
import com.desktop.views.suppliers.SuppliersPanelController;
import com.desktop.views.users.UsersPanelController;

public class ContentPanelController implements ControllerInterface {
  public String oldView = "0";
  public UserModel user;
  public NavigationPanelController parentController;
  public ContentPanelComponent contentPanelComponent;
  public Map<String, ControllerInterface> childControllers;
  public Map<String, ComponentInterface> childComponents;

  public ContentPanelController(NavigationPanelController controller) {
    this.parentController = controller;
    _initComponent();
    _initChildControllers();
    _initChildComponents();
  }

  @Override
  public void _initComponent() {
    contentPanelComponent = new ContentPanelComponent(this);
  }

  @Override
  public void _initChildControllers() {
    childControllers = new HashMap<>();
  }

  public void _initChildComponents() {
    childComponents = new HashMap<>();
  }

  /**
   * Loads a view based on the provided view ID and updates the controller.
   *
   * @param viewId     The ID of the view to load. Valid IDs are:
   *                   1 - ProductsPanelController
   *                   2 - InventoryPanelController
   *                   3 - IntsOutsPanelController
   *                   4 - SuppliersPanelController
   * @param controller The container controller to update. If not null, all
   *                   components
   *                   will be removed, and the container will be revalidated and
   *                   repainted.
   */
  public void loadView(ViewModel view) {
    if ("1" == oldView) {
      ProductsPanelController controller = ((ProductsPanelController) childControllers.get(oldView));
      removeAllComponents(controller.productsComponent);
    }
    if ("2" == oldView) {
      InventoryPanelController controller = ((InventoryPanelController) childControllers.get(oldView));
      removeAllComponents(controller.inventoryComponent);
    }
    if ("3" == oldView) {
      IntsOutsPanelController controller = ((IntsOutsPanelController) childControllers.get(oldView));
      removeAllComponents(controller.intsOutsComponent);
    }
    if ("4" == oldView) {
      SuppliersPanelController controller = ((SuppliersPanelController) childControllers.get(oldView));
      removeAllComponents(controller.suppliersComponent);
    }
    if ("6" == oldView) {
      MinimalPanelController controller = ((MinimalPanelController) childControllers.get(oldView));
      removeAllComponents(controller.minimalPanelComponent);
    }
    if ("7" == oldView) {
      UsersPanelController controller = ((UsersPanelController) childControllers.get(oldView));
      removeAllComponents(controller.usersPanelComponent);
    }
    childControllers.remove(oldView);
    contentPanelComponent.removeAll();

    oldView = view.getViewId() + "";

    if (1 == view.getViewId()) {
      childControllers.put(oldView, new ProductsPanelController(this, view));
      contentPanelComponent.revalidate();
      contentPanelComponent.repaint();
    }
    if (2 == view.getViewId()) {
      childControllers.put(oldView, new InventoryPanelController(this, view));
      contentPanelComponent.revalidate();
      contentPanelComponent.repaint();
    }
    if (3 == view.getViewId()) {
      childControllers.put(oldView, new IntsOutsPanelController(this, view));
      contentPanelComponent.revalidate();
      contentPanelComponent.repaint();
    }
    if (4 == view.getViewId()) {
      childControllers.put(oldView, new SuppliersPanelController(this, view));
      contentPanelComponent.revalidate();
      contentPanelComponent.repaint();
    }
    if (6 == view.getViewId()) {
      childControllers.put(oldView, new MinimalPanelController(this, view));
      contentPanelComponent.revalidate();
      contentPanelComponent.repaint();
    }
    if (7 == view.getViewId()) {
      childControllers.put(oldView, new UsersPanelController(this, view));
      contentPanelComponent.revalidate();
      contentPanelComponent.repaint();
    }
  }

  /**
   * Recursively removes all components from the specified component and its
   * children.
   * If the component is a container, it will remove all its child components
   * first.
   * Finally, it removes the specified component from the app frame.
   *
   * @param component the component from which all child components will be
   *                  removed
   */
  private void removeAllComponents(Component component) {
    if (component instanceof Container) {
      Container container = (Container) component;
      for (Component child : container.getComponents()) {
        removeAllComponents(child);
      }
      container.removeAll();
    }
    contentPanelComponent.remove(component);
  }
}
