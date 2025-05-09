import tkinter as tk
from tkinter import ttk


class CheckboxbuttonComponent(ttk.Checkbutton):
  """
  CheckboxbuttonComponent is a custom ttk.Checkbutton component that integrates with a controller and supports
  automatic resizing and event binding.
  Attributes:
    var (tk.IntVar): Variable to store the state of the checkbutton (1 for selected, 0 for not selected).
    controller: The controller object that manages this component.
    text (str): The text label for the checkbutton.
    relx (float): The relative x-coordinate for placing the checkbutton.
    rely (float): The relative y-coordinate for placing the checkbutton.
    command (callable): The command to be executed when the checkbutton is toggled.
  Methods:
    __init__(self, controller, text, relx, rely, command):
      Initializes the CheckboxbuttonComponent with the given parameters and configures the frame.
    _configure_frame(self):
      Configures the checkbutton and places it within the parent component.
    _bind_events(self):
      Binds the necessary events to the component.
    _on_resize(self, _):
      Adjusts the placement of the checkbutton when the parent component is resized.
    get_value(self):
      Returns the current value of the checkbutton (1 if selected, 0 if not selected).
  """

  def __init__(self, controller, text, relx, rely, command):
    self.var = tk.IntVar(value=1)
    self.controller = controller
    self.text = text
    self.relx = relx
    self.rely = rely
    self.command = command
    self._configure_frame()
    self._bind_events()


  def _configure_frame(self):
    """
    Configures the frame for the checkbox component.

    This method initializes the frame with the specified text, variable, and command.
    It also places the frame at the specified relative position and size within its parent.

    Attributes:
      controller.component: The parent component to which this frame belongs.
      text (str): The text to display on the frame.
      var: The variable associated with the checkbox.
      command: The command to execute when the checkbox state changes.
      relx (float): The relative x-coordinate for placing the frame.
      rely (float): The relative y-coordinate for placing the frame.
    """
    super().__init__(self.controller.component, text=self.text, variable=self.var, command=self.command)
    self.place(relx=self.relx, rely=self.rely, relwidth=0.2, relheight=0.3, anchor="n")


  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the "<Configure>" event to the component, which triggers
    the `_on_resize` method whenever the component is resized.

    Returns:
      None
    """
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Adjusts the placement configuration of the widget when the window is resized.

    Parameters:
    _ (Event): The resize event object (unused).
    """
    self.place_configure(relx=self.relx, rely=self.rely, relwidth=0.2, relheight=0.3, anchor="n")


  def get_value(self):
    """
    Retrieve the current value of the checkbox.

    Returns:
      bool: The current state of the checkbox (True if checked, False if unchecked).
    """
    return self.var.get()

