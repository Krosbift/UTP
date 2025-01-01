from tkinter import ttk

class ZoomFrameComponent(ttk.Frame):
    
  def __init__(self, controller):
    self.controller = controller
    self._configure_frame()
    self._bind_events()
  
  def _configure_frame(self):
    """
    Configures the frame for the component.

    This method initializes the frame by calling the superclass's __init__ method
    with the parent controller's component. It then places the frame at a specified
    location and size within the parent component.

    Attributes:
      relx (float): The relative x-coordinate for placing the frame.
      rely (float): The relative y-coordinate for placing the frame.
    """
    super().__init__(self.controller.parent_controller.component)
    self.place(relx=0.5, rely=0.52, relwidth=2, relheight=0.12, anchor="n")


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
    self.place_configure(relx=0.5, rely=0.52, relwidth=2, relheight=0.12, anchor="n")

