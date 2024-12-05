package com.desktop;

import com.desktop.views.AppFrameController;
/**
 * The Main class serves as the entry point for the desktop application.
 * It initializes the application by invoking the AppController's start method
 * on the Event Dispatch Thread (EDT) using SwingUtilities.invokeLater.
 */
public class Main {
  /**
   * The main entry point for the desktop application.
   * This method schedules a job for the event-dispatching thread:
   * creating and showing the application's GUI.
   *
   * @param args Command line arguments passed to the application.
   */
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new AppFrameController().start();
      }
    });
  }
}
