package com.desktop.views.components;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JPanel;

import com.desktop.core.utils.interfaces.ComponentInterface;
import com.desktop.views.AppFrameController;

public class TopBarPanelComponent extends JPanel implements ComponentInterface {
  public AppFrameController controller;
  public Map<String, Integer> coords = new HashMap<>();

  public TopBarPanelComponent(AppFrameController controller) {
    this.controller = controller;
    _configureComponent();
    _listenerClicking();
  }

  public void _configureComponent() {
    this.setBackground(Color.decode("#121212"));
    this.setPreferredSize(new Dimension(this.getWidth(), 30));
    this.setLayout(new BorderLayout());
    controller.appFrame.add(this, BorderLayout.NORTH);
  }

  /**
   * Adds mouse listeners to the component to handle click and drag events.
   * <p>
   * This method sets up two listeners:
   * <ul>
   *   <li>A mouse listener that captures the initial click coordinates when the mouse is pressed.</li>
   *   <li>A mouse motion listener that updates the location of the application frame as the mouse is dragged.</li>
   * </ul>
   * The coordinates of the initial click are stored in a map with keys "pX" and "pY".
   * The application frame's location is updated based on the difference between the current mouse position on the screen and the initial click coordinates.
   */
  private void _listenerClicking() {
    this.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent e) {
        coords.put("pX", e.getX());
        coords.put("pY", e.getY());
      }
    });

    this.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent e) {
        controller.appFrame.setLocation(e.getXOnScreen() - coords.get("pX"), e.getYOnScreen() - coords.get("pY"));
      }
    });
  }
}
