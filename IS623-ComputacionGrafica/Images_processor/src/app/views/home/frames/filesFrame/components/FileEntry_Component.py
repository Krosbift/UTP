import tkinter as tk
from tkinter import ttk


class FileEntryComponent(ttk.Entry):
  """
  FileEntryComponent is a custom ttk.Entry widget designed to display file paths in a read-only state.
  Attributes:
    path_route (tk.StringVar): A Tkinter StringVar to hold the file path.
    controller: The controller object that manages this component.
  Methods:
    __init__(self, controller):
      Initializes the FileEntryComponent with the given controller.
    _configure_entry(self):
      Configures the ttk.Entry widget with specific styles and placement.
    _configure_styles(self):
      Sets up the styles for the ttk.Entry widget, including font, colors, and borders.
    _bind_events(self):
      Binds necessary events to the component, such as resizing.
    _on_resize(self, _):
      Adjusts the placement of the widget when the parent component is resized.
    set_textvariable(self, new_value):
      Updates the textvariable (file path) displayed in the entry widget.
  """


  def __init__(self, controller):
    self.path_route = tk.StringVar(value="")
    self.controller = controller
    self._configure_styles()
    self._configure_entry()
    self._bind_events()


  def _configure_entry(self):
    """
    Configures the entry widget with specific properties and places it within the parent component.

    This method initializes the entry widget with the following properties:
    - Sets the parent component.
    - Uses the "Helvetica" font with a size of 10.
    - Binds the entry widget to the `path_route` text variable.
    - Applies the "FileEntry.TEntry" style.
    - Sets the state of the entry widget to "readonly".

    The entry widget is placed at the center of the parent component with:
    - Relative x position (relx) of 0.5.
    - Relative y position (rely) of 0.5.
    - Relative width (relwidth) of 0.5.
    - Relative height (relheight) of 0.6.
    - Anchor point set to "center".
    """
    super().__init__(self.controller.component, font=("Helvetica", 10), textvariable=self.path_route, style="FileEntry.TEntry", state="readonly")
    self.place(relx=0.5, rely=0.5, relwidth=0.5, relheight=0.6, anchor="center")


  def _configure_styles(self):
    """
    Configures the styles for the FileEntry component using the ttk.Style class.

    This method sets up the following styles:
    - "FileEntry.TEntry": Configures the font, foreground color, background color, padding, border color, relief, and border width.
    - Maps the "readonly" state to specific field background and foreground colors.

    Styles configured:
    - Font: Helvetica, size 10
    - Foreground color: black
    - Background color: lightgray
    - Padding: 2
    - Border color: black
    - Relief: solid
    - Border width: 1
    - Readonly state:
      - Field background: lightgray
      - Foreground: black
    """
    style = ttk.Style()
    style.configure("FileEntry.TEntry", font=("Helvetica", 10), foreground="black", background="lightgray", padding=2, bordercolor="black", relief="solid", borderwidth=1)
    style.map("FileEntry.TEntry", fieldbackground=[('readonly', 'lightgray')], foreground=[('readonly', 'black')])


  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the <Configure> event of the component to the _on_resize method,
    allowing the component to handle resize events.

    Returns:
      None
    """
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Adjusts the placement and size of the component when the window is resized.

    Parameters:
    _ (Event): The resize event (unused).
    """
    self.place_configure(relx=0.5, rely=0.5, relwidth=0.5, relheight=0.6, anchor="center")


  def set_textvariable(self, new_value):
    """
    Updates the text variable associated with the file path.

    Args:
      new_value (str): The new value to set for the path_route text variable.
    """
    self.path_route.set(new_value)

