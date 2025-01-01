from tkinter import ttk


class SpinBoxComponent(ttk.Spinbox):
  """
  SpinBoxComponent is a custom ttk.Spinbox widget with additional configuration and event handling.
  Attributes:
    controller (object): The controller object that manages this component.
    command (callable): The command to be executed when the spinbox value changes.
  Methods:
    __init__(controller, command):
      Initializes the SpinBoxComponent with the given controller and command.
    _configure_spinbox():
      Configures the spinbox widget with specific properties and places it within the parent component.
    _configure_styles():
      Configures the styles for the spinbox widget, including arrow size and layout.
    _bind_events():
      Binds necessary events to the spinbox widget, such as resizing.
    _on_resize(event):
      Adjusts the placement of the spinbox widget when the parent component is resized.
    _on_spinbox_change():
      Executes the command with the current value of the spinbox when the value changes.
    set_value(value):
      Sets the value of the spinbox to the specified value, formatted to two decimal places.
  """


  def __init__(self, controller, command, initial_value, min_value, max_value, increment, notation):
    self.controller = controller
    self.command = command
    self.min_value = min_value
    self.max_value = max_value
    self.increment = increment
    self.notation = notation
    self._configure_spinbox()
    self._configure_styles()
    self._bind_events()
    self.set(initial_value)


  def _configure_spinbox(self):
    """
    Configures the spinbox widget with specific parameters and places it within the parent widget.

    This method initializes the spinbox with a range from 0 to 2, an increment of 0.01, 
    a specific style, and sets it to be readonly. It also binds a command to handle 
    changes in the spinbox value. The spinbox is then placed at a specific location 
    within the parent widget using relative positioning.

    Parameters:
    None

    Returns:
    None
    """
    super().__init__(self.controller.component, from_=self.min_value, to=self.max_value, increment=self.increment, style="TSpinbox", state="readonly", command=self._on_spinbox_change)
    self.place(relx=0.68, rely=0.5, relwidth=0.13, relheight=0.6, anchor="w")


  def _configure_styles(self):
    """
    Configures the styles for the TSpinbox widget.

    This method sets up the appearance and layout of the TSpinbox widget using the ttk.Style class.
    It customizes the arrowsize, layout, and color mappings for the TSpinbox.

    Styles configured:
    - TSpinbox: Sets the arrowsize to 20.
    - Layout: Defines the structure of the Spinbox with padding, textarea, uparrow, and downarrow.
    - Map: Sets the field background, select background, and select foreground colors for the readonly state.
    """
    style = ttk.Style()
    style.configure("TSpinbox", arrowsize=20)
    style.layout("TSpinbox", [
      ("Spinbox.field", {"children": [
      ("Spinbox.padding", {"children": [
        ("Spinbox.textarea", {"sticky": "nswe"}),
        ("Spinbox.uparrow", {"side": "right", "sticky": "ns"}),
        ("Spinbox.downarrow", {"side": "right", "sticky": "ns"})
      ], "sticky": "nswe"})
      ], "sticky": "nswe"})
    ])
    style.map("TSpinbox", fieldbackground=[('readonly', 'white')], selectbackground=[('readonly', 'white')], selectforeground=[('readonly', 'black')])


  def _bind_events(self):
    """
    Binds the <Configure> event of the component to the _on_resize method.

    This method sets up an event listener on the component managed by the controller.
    When the component is resized, the _on_resize method is triggered to handle the event.
    """
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Adjusts the placement configuration of the widget when the resize event occurs.

    Parameters:
    _ (Event): The resize event object (unused).
    """
    self.place_configure(relx=0.68, rely=0.5, relwidth=0.13, relheight=0.6, anchor="w")


  def _on_spinbox_change(self):
    """
    Handles the event when the spinbox value changes.

    This method is called whenever the value in the spinbox is changed by the user.
    It retrieves the current value from the spinbox and passes it to the command
    associated with this spinbox.

    Returns:
      None
    """
    self.command(self.get())

  def set_value(self, value):
    """
    Sets the value of the spinbox to the specified value formatted according to the notation.

    Args:
      value (float or int): The value to set in the spinbox.
    """
    format_map = {
        "float_2_decimals": lambda v: f"{float(v):.2f}",
        "integer_with_degree": lambda v: f"{int(float(v))}°"
    }

    formatted_value = format_map.get(self.notation, lambda v: str(v))(value)
    self.set(formatted_value)
