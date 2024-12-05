from tkinter import ttk


class BinarizationFrameComponent(ttk.Frame):
  """
  BinarizationFrameComponent is a custom ttk.Frame that is part of the application's GUI.
  Attributes:
    controller (object): The controller that manages this frame.
  Methods:
    __init__(controller):
      Initializes the BinarizationFrameComponent with the given controller.
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

    This method initializes the frame with a parent component, sets its style to "Rotation.TFrame",
    and places it at the center top of the parent component with a relative width of 2 and a relative height of 0.1.

    Inherited from:

    Configuration:
      - Style: "Binarization.TFrame"
      - Placement: 
        - relx: 0.5 (relative x position)
        - rely: 0.3 (relative y position)
        - relwidth: 2 (relative width)
        - relheight: 0.1 (relative height)
        - anchor: "n" (north anchor)
    """
    super().__init__(self.controller.parent_controller.component)
    self.place(relx=0.5, rely=0.64, relwidth=2, relheight=0.08, anchor="n")

  
  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the "<Configure>" event of the parent controller's component
    to the `_on_resize` method, allowing the component to respond to resize events.
    """
    self.controller.parent_controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Handles the resize event to adjust the frame's placement.

    This method recalculates the frame's placement based on the parent component's size
    and repositions the frame to the center top of the parent component.
    """
    self.place(relx=0.5, rely=0.64, relwidth=2, relheight=0.08, anchor="n")

