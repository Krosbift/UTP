from tkinter import ttk

class ButtonStandardComponent(ttk.Button):
  """
  ButtonStandardComponent is a custom ttk.Button component with additional configuration for styles, events, and placement.
  Attributes:
    controller (object): The controller object that manages this component.
    text (str): The text to display on the button.
    style (str): The style name to apply to the button.
    command (callable): The function to call when the button is clicked.
    relx (float): The relative x-coordinate for button placement.
  Methods:
    __init__(controller, text, style, command, relx):
      Initializes the ButtonStandardComponent with the given parameters and configures styles, button, and events.
    _configure_button():
      Configures the button properties and places it within the parent component.
    _configure_styles():
      Configures the styles for the button, including font, background, foreground, and borderwidth.
    _bind_events():
      Binds necessary events to the button, such as resizing.
    _on_resize(event):
      Adjusts the button placement when the parent component is resized.
  """

  def __init__(self, controller, text, style, command, relx):
    self.controller = controller
    self.text = text
    self.style = style
    self.command = command
    self.relx = relx
    self._configure_styles()
    self._configure_button()
    self._bind_events()


  def _configure_button(self):
    """
    Configures the button component with the specified properties.

    This method initializes the button with the given text, style, and command,
    and places it at the specified relative position within its parent container.
    """
    super().__init__(self.controller.component, text=self.text, style=self.style, cursor="hand2", command=self.command)
    self.place(relx=self.relx, rely=0.5, relwidth=0.09, relheight=0.6, anchor="w")


  def _configure_styles(self):
    """
    Configures the styles for the button component using the ttk.Style class.

    This method sets the font, background color, foreground color, and border width for the button's style.
    It also maps the background and foreground colors for the 'active' state of the button.
    """
    style = ttk.Style()
    style.configure(self.style, font=("Helvetica", 10), background="#E5E5E5", foreground="#111111", borderwidth=1)
    style.map(self.style, background=[('active', '#D5D5D5')], foreground=[('active', '#000000')])


  def _bind_events(self):
    """
    Binds the <Configure> event to the _on_resize method for the component.

    This method sets up an event listener on the component to trigger the 
    _on_resize method whenever the component is resized.
    """
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Adjusts the placement configuration of the button when the window is resized.

    Parameters:
    _ (Event): The resize event object (unused).
    """
    self.place_configure(relx=self.relx, rely=0.5, relwidth=0.09, relheight=0.6, anchor="w")
