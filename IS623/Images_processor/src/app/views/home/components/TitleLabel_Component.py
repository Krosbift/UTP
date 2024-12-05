from tkinter import ttk


class TitleLabelComponent(ttk.Label):
  """
  TitleLabelComponent is a custom ttk.Label component for displaying a title label.
  This class extends the ttk.Label widget and provides additional configuration
  and event handling for a title label within a GUI application.
    controller: The controller object that manages this component.
  Methods:
    __init__(controller):
      Initializes the TitleLabelComponent with the given controller.
    _configure_label():
      Configures the label with specific properties such as text, font, and color.
    _bind_events():
    _on_resize(_):
      Handles the resize event for the component, repositioning it as needed.
  """

  def __init__(self, controller):
    self.controller = controller
    self._configure_label()
    self._bind_events()


  def _configure_label(self):
    """
    Configures the label with specific properties.

    This method initializes the label with the specified text, font, and foreground color.
    It also places the label at a specific position within its parent component.

    Attributes:
      text (str): The text to be displayed on the label.
      font (tuple): The font type and size for the label text.
      foreground (str): The color of the label text.
      relx (float): The relative x-coordinate for placing the label.
      y (int): The y-coordinate for placing the label.
      anchor (str): The anchor point for positioning the label.
    """
    super().__init__(self.controller.component, text="Visor de imágenes", font=("Helvetica", 20), foreground="#8B0000")
    self.place(relx=0.5, y=20, anchor='n')


  def _bind_events(self):
    """
    Binds the '<Configure>' event to the component's resize handler.

    This method attaches the '_on_resize' method to the 'component' of the
    controller, so that it is called whenever the component is resized.

    Returns:
      None
    """
    self.controller.component.bind('<Configure>', self._on_resize)


  def _on_resize(self, _):
    """
    Handles the resize event for the component.

    This method is triggered when the component is resized and repositions
    the component to be centered horizontally with a fixed vertical position.

    Args:
      _: The event object (not used).
    """
    self.place_configure(relx=0.5, y=20)

