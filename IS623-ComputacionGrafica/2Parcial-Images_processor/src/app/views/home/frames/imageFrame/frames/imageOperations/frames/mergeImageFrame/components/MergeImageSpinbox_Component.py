from tkinter import ttk


class MergeImageSpinboxComponent(ttk.Spinbox):


  def __init__(self, controller):
    self.controller = controller
    self._configure_spinbox()
    self._configure_styles()
    self._bind_events()
    self.set(0)


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
    super().__init__(self.controller.component, from_=0, to=1, increment=0.05, style="TSpinbox", state="readonly", command=None)
    self.place(relx=0.5, rely=0.6, relwidth=0.13, relheight=0.6, anchor="w")


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
    self.place_configure(relx=0.5, rely=0.6, relwidth=0.13, relheight=0.6, anchor="w")

