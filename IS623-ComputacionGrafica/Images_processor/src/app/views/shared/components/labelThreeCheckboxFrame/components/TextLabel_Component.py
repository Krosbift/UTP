from tkinter import ttk


class TextLabelComponent(ttk.Label):
  """
  TextLabelComponent is a custom ttk.Label widget that displays a text label with specific styles and event bindings.
  Attributes:
    controller: The controller object that manages this component.
    text: The text to be displayed on the label.
    style_name: The name of the style to be applied to the label.
  Methods:
    __init__(controller, text, style_name):
      Initializes the TextLabelComponent with the given controller, text, and style name.
    _configure_label():
      Configures the label with the specified text and style, and places it within the parent component.
    _configure_styles():
      Configures the styles for the label, including foreground color, background color, and font.
    _bind_events():
      Binds the necessary events to the label, such as resizing.
    _on_resize(event):
      Handles the resize event to adjust the label's placement within the parent component.
  """


  def __init__(self, controller, text, style_name):
    self.controller = controller
    self.text = text
    self.style_name = style_name
    self._configure_label()
    self._configure_styles()
    self._bind_events()


  def _configure_label(self):
    """
    Configures the label component.

    This method initializes the label component with the specified text and style,
    and places it at the center of the parent component with a west anchor.

    Overrides:
      __init__ method of the parent class.

    Attributes:
      controller.component: The parent component to which the label belongs.
      text (str): The text to be displayed on the label.
      style_name (str): The style to be applied to the label.
    """
    super().__init__(self.controller.component, text=self.text, style=self.style_name)
    self.place(relx=0.25, rely=0.5, anchor="w")


  def _configure_styles(self):
    """
    Configures the styles for the TextLabel component.

    This method sets up the style for the TextLabel component using the 
    tkinter.ttk.Style class. It defines the foreground color, background 
    color, and font for the component.

    Attributes:
      style_name (str): The name of the style to be configured.
    """
    style = ttk.Style()
    style.configure(self.style_name, foreground="#000000", background="#FFFFFF", font=("Helvetica", 12))


  def _bind_events(self):
    """
    Binds the necessary events to the component.

    This method binds the '<Configure>' event to the component, which triggers
    the `_on_resize` method when the event occurs.

    Returns:
      None
    """
    self.controller.component.bind('<Configure>', self._on_resize)


  def _on_resize(self, _):
    """
    Adjusts the placement configuration of the widget when resized.

    This method is triggered on a resize event and repositions the widget
    to be centered horizontally with the anchor point set to the west.

    Args:
      _: The event object (not used).
    """
    self.place_configure(relx=0.25, rely=0.5, anchor="w")

