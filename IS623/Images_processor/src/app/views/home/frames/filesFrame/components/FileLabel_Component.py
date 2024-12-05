from tkinter import ttk


class FileLabelComponent(ttk.Label):
  """
  FileLabelComponent is a custom ttk.Label widget designed to display a label for an image file.
  Attributes:
    controller (object): The controller object that manages this component.
  Methods:
    __init__(controller):
      Initializes the FileLabelComponent with the given controller.
    _configure_label():
      Configures the label with initial properties such as text and style.
    _configure_styles():
      Configures the styles for the label, including foreground color, background color, and font.
    _bind_events():
      Binds necessary events to the label, such as resizing.
    _on_resize(event):
      Handles the resize event to adjust the font size and reposition the label.
  """

  def __init__(self, controller):
    self.controller = controller
    self._configure_label()
    self._bind_events()


  def _configure_label(self):
    """
    Configures the label component.

    This method initializes the label with specific text and style, and places it at a defined position within the parent component.

    Overrides:
      __init__ method of the superclass.

    Attributes:
      controller.component: The parent component to which this label belongs.
      text (str): The text displayed on the label.
      style (str): The style applied to the label.
      relx (float): The relative x-coordinate for the label's placement.
      rely (float): The relative y-coordinate for the label's placement.
      anchor (str): The anchor point for the label's placement.
    """
    super().__init__(self.controller.component, text="Archivo de imagen: ", style="FileLabel.TLabel")
    self.place(relx=0.1, rely=0.5, anchor="e")


  def _configure_styles(self):
    """
    Configures the styles for the FileLabel component.

    This method sets up the visual appearance of the FileLabel component
    using the ttk.Style class. It defines the foreground color, background
    color, and font for the "FileLabel.TLabel" style.

    Styles configured:
    - FileLabel.TLabel: Black text on a white background with Helvetica font, size 12.
    """
    style = ttk.Style()
    style.configure("FileLabel.TLabel", foreground="#000000", background="#FFFFFF", font=("Helvetica", 12))
  

  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the '<Configure>' event to the component, which triggers
    the `_on_resize` method when the event occurs.

    Returns:
      None
    """
    self.controller.component.bind('<Configure>', self._on_resize)


  def _on_resize(self, event):
    """
    Handles the resize event for the widget.

    This method adjusts the font size of the label based on the new height of the widget.
    It also repositions the label to maintain its alignment.

    Args:
      event (tkinter.Event): The resize event containing the new dimensions of the widget.
    """
    style = ttk.Style()
    new_font_size = min(12, int(event.height / 2))
    style.configure("FileLabel.TLabel", font=("Helvetica", new_font_size))
    self.place_configure(x=self.winfo_width() / 2 + 10, rely=0.5, anchor="e")
