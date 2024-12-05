from tkinter import ttk


class FilesFrameComponent(ttk.Frame):
  """
  FilesFrameComponent is a custom frame component that inherits from ttk.Frame. 
  It is designed to be part of a larger GUI application and is responsible for 
  configuring its own layout and handling resize events.
  Attributes:
    controller: The parent controller that manages this component.
  Methods:
    __init__(controller):
      Initializes the FilesFrameComponent with the given controller.
    _configure_frame():
    _bind_events():
      Binds the necessary events to the component, specifically the "<Configure>" 
      event to the _on_resize method.
    _on_resize(_):
      Handles the resize event for the component, adjusting its placement configuration.
  """

  def __init__(self, controller):
    self.controller = controller
    self._configure_frame()
    self._bind_events()


  def _configure_frame(self):
    """
    Configures the frame by initializing it with the parent controller's component
    and setting its placement and dimensions.

    The frame is placed at the center horizontally (relx=0.5) and at a fixed vertical
    position (y=70). It spans 90% of the width (relwidth=0.9) and 7% of the height
    (relheight=0.07) of the parent container, with the anchor point set to the north ("n").
    """
    super().__init__(self.controller.parent_controller.component)
    self.place(relx=0.5, y=70, relwidth=0.9, relheight=0.07, anchor="n")


  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the "<Configure>" event of the parent controller's component
    to the _on_resize method, ensuring that the _on_resize method is called whenever
    the component is resized.

    Returns:
      None
    """
    self.controller.parent_controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Handles the resize event for the component.

    This method is triggered when the component is resized and adjusts the 
    placement configuration of the component.

    Args:
      _: The event object (not used).
    """
    self.place_configure(relx=0.5, y=70, relwidth=0.9, relheight=0.07)

