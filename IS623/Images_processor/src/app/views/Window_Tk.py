import tkinter as tk


class WindowTk(tk.Tk):
  """
  WindowTk is a subclass of tk.Tk that initializes and configures a Tkinter window.
  Attributes:
    controller: An instance of the controller that manages the window.
  Methods:
    __init__(controller):
      Initializes the WindowTk instance with the given controller and configures the window.
    _configure_tk():
      Configures the Tkinter window with specific properties, including title, geometry, state, and minimum size.
  """

  def __init__(self, controller):
    self.controller = controller
    self._configure_tk()


  def _configure_tk(self):
    """
    Configures the Tkinter window with specific properties.

    This method initializes the Tkinter window by setting its title, 
    geometry to match the screen size, state to 'zoomed', and minimum size.

    Inherited from:
      super().__init__() - Calls the initializer of the parent class.

    Sets:
      title: Sets the window title to "Visor de imágenes".
      geometry: Sets the window size to the full screen dimensions.
      state: Sets the window state to 'zoomed' to maximize it.
      minsize: Sets the minimum window size to 800x600 pixels.
    """
    super().__init__()
    self.title("Visor de imágenes")
    self.geometry(f"{self.winfo_screenwidth()}x{self.winfo_screenheight()}")
    self.state('zoomed')
    self.minsize(800, 600)

