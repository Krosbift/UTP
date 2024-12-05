from tkinter import ttk

class MergeImageButtonComponent(ttk.Button):

  def __init__(self, controller):
    self.controller = controller
    self._configure_styles()
    self._configure_button()
    self._bind_events()


  def _configure_button(self):
    """
    Configures the button component with the specified properties.

    This method initializes the button with the given text, style, and command,
    and places it at the specified relative position within its parent container.
    """
    super().__init__(self.controller.component, text="Merge Image", style="Merge.TButton", cursor="hand2", command=self.controller.toggle_state)
    self.place(relx=0.34, rely=0.4, height=25, anchor="n")


  def _configure_styles(self):
    """
    Configures the styles for the button component using the ttk.Style class.

    This method sets the font, background color, foreground color, and border width for the button's style.
    It also maps the background and foreground colors for the 'active' state of the button.
    """
    style = ttk.Style()
    style.configure("Merge.TButton", font=("Helvetica", 10), background="#E5E5E5", foreground="#111111", borderwidth=1)
    style.map("Merge.TButton", background=[('active', '#D5D5D5')], foreground=[('active', '#000000')])


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
    self.place_configure(relx=0.34, rely=0.4, height=25, anchor="n")

