from tkinter import ttk


class LabelScaleSpinboxFrameComponent(ttk.Frame):
  """
  LabelScaleSpinboxFrameComponent is a custom ttk.Frame component that represents a frame with a specific style and placement within a parent component. It is designed to be used within a larger application framework.
  Attributes:
    controller (object): The controller object that manages this component.
    relx (float): The relative x-coordinate for placing the frame.
    rely (float): The relative y-coordinate for placing the frame.
  Methods:
    __init__(controller, relx, rely):
      Initializes the LabelScaleSpinboxFrameComponent with the given controller and placement coordinates.
    _configure_frame():
      Configures the frame's style and placement within the parent component.
    _bind_events():
      Binds necessary events to the frame, such as resizing.
    _on_resize(event):
      Handles the resize event to adjust the frame's placement.
  """

  def __init__(self, controller, relx, rely):
    self.controller = controller
    self.relx = relx
    self.rely = rely
    self._configure_frame()
    self._bind_events()


  def _configure_frame(self):
    """
    Configures the frame for the LabelScaleSpinboxFrame component.

    This method initializes the frame by calling the superclass's __init__ method
    with the parent controller's component. It then sets the frame's style and 
    places it within the parent container using relative positioning.

    Attributes:
      None

    Returns:
      None
    """
    super().__init__(self.controller.parent_controller.component)
    self.configure(style="Custom.TFrame")
    self.place(relx=self.relx, rely=self.rely, relwidth=2, relheight=0.08, anchor="n")


  def _bind_events(self):
    """
    Binds the <Configure> event of the parent controller's component to the _on_resize method.

    This method sets up an event listener that triggers the _on_resize method whenever the 
    parent controller's component is resized.

    Returns:
      None
    """
    self.controller.parent_controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Handles the resize event for the component.

    This method is triggered when the component is resized. It reconfigures
    the placement of the component using the specified relative x and y 
    coordinates, width, height, and anchor.

    Args:
      _: The event object (not used).
    """
    self.place_configure(relx=self.relx, rely=self.rely, relwidth=2, relheight=0.08, anchor="n")

