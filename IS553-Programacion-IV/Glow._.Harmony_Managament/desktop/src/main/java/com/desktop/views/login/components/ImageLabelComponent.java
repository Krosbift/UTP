package com.desktop.views.login.components;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import com.desktop.views.login.LoginPanelController;
import com.desktop.core.utils.interfaces.ComponentInterface;

public class ImageLabelComponent extends JLabel implements ComponentInterface {
  public LoginPanelController controller;

  public ImageLabelComponent(LoginPanelController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerSizing();
  }

  @Override
  public void _configureComponent() {
    this.setLayout(new BorderLayout());
    ((RightPanelComponent) controller.childComponents.get("RightPanelComponent")).add(this);
  }

  /**
   * Adds a component listener to the "ImageLabelComponent" that triggers the
   * resizeImage method
   * whenever the component is resized. Also calls resizeImage initially to ensure
   * the image is
   * correctly sized when this method is invoked.
   */
  private void _listenerSizing() {
    ((RightPanelComponent) controller.childComponents.get("RightPanelComponent"))
        .addComponentListener(new ComponentAdapter() {
          public void componentResized(ComponentEvent evt) {
            resizeImage();
          }
        });
    resizeImage();
  }

  /**
   * Resizes the image to fit the current dimensions of the component.
   * <p>
   * This method retrieves the current width and height of the component. If both
   * dimensions are greater than zero,
   * it loads the original image from the specified resource path and scales it
   * smoothly to fit the component's size.
   * The scaled image is then set as the icon of the component.
   * </p>
   */
  private void resizeImage() {
    Dimension size = ((RightPanelComponent) controller.childComponents.get("RightPanelComponent")).getSize();
    if (size.width > 0 && size.height > 0) {
      ImageIcon originalImage = new ImageIcon(getClass().getResource("/images/glow._.harmony.png"));
      Image scaledImage = originalImage.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH);
      this.setIcon(new ImageIcon(scaledImage));
      this.repaint();
      this.revalidate();
    }
  }
}
