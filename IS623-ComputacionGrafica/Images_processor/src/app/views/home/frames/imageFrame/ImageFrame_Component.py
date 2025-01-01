from tkinter import ttk

class ImageFrameComponent(ttk.Frame):
  """
  ImageFrameComponent is a custom ttk.Frame component designed to be part of a larger GUI application.
  Attributes:
    controller (object): The controller object that manages this frame.
  Methods:
    __init__(controller):
      Initializes the ImageFrameComponent with the given controller.
    _configure_frame():
      Configures the frame's style and placement within the parent component.
    _bind_events():
      Binds necessary events to the frame, such as resizing.
    _on_resize(event):
      Handles the resize event to adjust the frame's placement.
  """
  
  def __init__(self, controller):
    self.controller = controller
    self._configure_frame()
    self._bind_events()


  def _configure_frame(self):
    """
    Configures the frame with specific style and placement.

    This method initializes the frame with a custom style and places it
    relative to its parent container. It sets the frame's style to "Custom.TFrame"
    and positions it at the center horizontally (relx=0.5) and at a fixed vertical
    position (y=135). The frame's width and height are set relative to its parent
    container (relwidth=0.9, relheight=0.7), and it is anchored at the top ("n").

    Returns:
      None
    """
    super().__init__(self.controller.parent_controller.component)
    self.configure(style="Custom.TFrame")
    self.place(relx=0.5, y=135, relwidth=0.9, relheight=0.7, anchor="n")


  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the "<Configure>" event of the parent controller's component
    to the `_on_resize` method, ensuring that the `_on_resize` method is called
    whenever the component is resized.
    """
    self.controller.parent_controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Handles the resize event for the image frame component.

    This method is triggered when the image frame is resized. It adjusts the 
    placement configuration of the frame to maintain its relative position 
    and size within the parent container.

    Args:
      _: The event object (not used in this method).
    """
    self.place_configure(relx=0.5, y=135, relwidth=0.9, relheight=0.7)

