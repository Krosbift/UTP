from tkinter import ttk


class ScaleComponent(ttk.Scale):
  """
  ScaleComponent is a custom ttk.Scale widget that provides a horizontal scale with specific styles and behaviors.
  Attributes:
    controller (object): The controller object that manages this component.
    command (callable): The command to be executed when the scale value changes.
  Methods:
    __init__(controller, command):
      Initializes the ScaleComponent with the given controller and command.
    _configure_scale():
      Configures the scale widget with specific properties such as orientation, length, range, and command.
    _configure_styles():
      Configures the styles for the scale widget, including background color, trough color, slider thickness, and relief styles.
    _bind_events():
      Binds the necessary events to the scale widget, such as resizing.
    _on_resize(event):
      Adjusts the scale length and placement when the parent component is resized.
    set_value(value):
      Sets the scale to the specified value, formatted to two decimal places.
  """


  def __init__(self, controller, command, initial_value, min_value, max_value):
    self.controller = controller
    self.command = command
    self.min_value = min_value
    self.max_value = max_value
    self._configure_styles()
    self._configure_scale()
    self._bind_events()
    self.set(initial_value)


  def _configure_scale(self):
    """
    Configures the scale component.

    This method initializes the scale component with specific parameters and places it
    at a defined position within the parent widget.

    Parameters:
    None

    Returns:
    None
    """
    super().__init__(self.controller.component, orient="horizontal", length=100, from_=self.min_value, to=self.max_value, command=self.command)
    self.place(relx=0.1, rely=0.5, anchor="e")


  def _configure_styles(self):
    """
    Configures the styles for the TScale widget.

    This method sets up the visual appearance of the TScale widget using the 
    ttk.Style class. It customizes various style attributes such as background 
    color, trough color, slider thickness, trough relief, slider relief, and 
    slider length.

    Attributes configured:
    - background: Sets the background color of the TScale widget.
    - troughcolor: Sets the color of the trough (the track in which the slider moves).
    - sliderthickness: Sets the thickness of the slider.
    - troughrelief: Sets the relief style of the trough.
    - sliderrelief: Sets the relief style of the slider.
    - sliderlength: Sets the length of the slider.
    """
    style = ttk.Style()
    style.configure("TScale", background="#ffffff", troughcolor="#666666", sliderthickness=0, troughrelief="flat", sliderrelief="raised", sliderlength=1)


  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the "<Configure>" event of the component to the 
    `_on_resize` method, allowing the component to respond to resize events.

    Returns:
      None
    """
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, event):
    """
    Handles the resize event for the component.

    This method adjusts the length of the component based on the width of the event
    and repositions it within its parent container.

    Args:
      event: An event object that contains information about the resize event,
           including the new width of the component.
    """
    self.config(length=event.width * 0.15)
    self.place_configure(relx=0.97, rely=0.5, relheight=0.6, anchor="e")


  def set_value(self, value):
    """
    Sets the value of the component to the specified value formatted to two decimal places.

    Args:
      value (float or str): The value to set, which will be converted to a float and formatted to two decimal places.
    """
    self.set(f"{float(value):.2f}")

