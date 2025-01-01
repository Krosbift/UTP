from tkinter import ttk


class HomeFrameComponent(ttk.Frame):
  """
  HomeFrameComponent is a custom frame component for the home view of the application.
  This class extends `ttk.Frame` and provides custom styling, event binding, and
  resize handling for the frame.
  Attributes:
    controller: The controller object that manages this frame.
  Methods:
    __init__(self, controller):
      Initializes the HomeFrameComponent with the given controller.
    _configure_frame(self):
      Configures the frame for the HomeFrame component, setting it to expand
      and fill the available space within the parent window.
    _configure_style(self):
      Configures the style for the application, setting up a custom style
      named 'White.TFrame' with a white background.
    _bind_events(self):
      Binds the necessary events to the window, specifically the "<Configure>"
      event to the `_on_resize` method.
    _on_resize(self, event):
      Handles the resize event for the component, updating the component's
      width and height based on the new dimensions provided by the event.
  """

  def __init__(self, controller):
    self.controller = controller
    self._configure_style()
    self._configure_frame()
    self._bind_events()


  def _configure_frame(self):
    """
    Configures the frame for the HomeFrame component.

    This method initializes the frame with a specific style and sets it to 
    expand and fill the available space within the parent window.

    Inherited from:

    Actions:
      - Packs the frame to fill both horizontal and vertical space.
      - Sets the frame to expand with the window size.
    """
    super().__init__(self.controller.parent_controller.windowTk, style='White.TFrame')
    self.pack(fill='both', expand=True)


  def _configure_style(self):
    """
    Configures the style for the application.

    This method sets up a custom style for the application's frames using the
    tkinter.ttk.Style class. Specifically, it configures a style named 'White.TFrame'
    with a white background.
    """
    style = ttk.Style()
    style.configure('White.TFrame', background='white')


  def _bind_events(self):
    """
    Binds the necessary events to the window.

    This method binds the "<Configure>" event of the Tkinter window to the 
    `_on_resize` method, which is triggered whenever the window is resized.

    Events:
      <Configure>: Triggered when the window is resized.
    """
    self.controller.parent_controller.windowTk.bind("<Configure>", self._on_resize)


  def _on_resize(self, event):
    """
    Handles the resize event for the component.

    This method is called when the component is resized. It updates the
    component's width and height based on the new dimensions provided by
    the event.

    Args:
      event: An event object containing the new width and height of the component.
    """
    self.configure(width=event.width, height=event.height)

