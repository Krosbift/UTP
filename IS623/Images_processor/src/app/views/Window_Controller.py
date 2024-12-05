from .Window_Tk import WindowTk
from .home.HomeFrame_Controller import HomeFrameController

class WindowController:
  """
  WindowController is responsible for managing the main application window and its child controllers.
  Methods:
    __init__:
      Initializes the WindowController instance by creating the main window,
      initializing child controllers, and opening the window.
    create_window:
    init_childControllers:
    open_window:
  """
  
  def __init__(self):
    self.create_window()
    self.init_childControllers()
    self.open_window()


  def create_window(self):
    """
    Creates a new window using the WindowTk class.

    This method initializes a new instance of the WindowTk class and assigns it
    to the `windowTk` attribute of the current object.

    Returns:
      None
    """
    self.windowTk = WindowTk(self)


  def init_childControllers(self):
    """
    Initializes the child controllers for the window.

    This method sets up the `childrenControllers` dictionary with instances
    of child controllers. Currently, it initializes the `HomeFrameController`
    with the current instance of the window controller.

    Attributes:
      childrenControllers (dict): A dictionary containing instances of child
                    controllers keyed by their names.
    """
    self.childrenControllers = {
        "HomeFrameController": HomeFrameController(self)
      }


  def open_window(self):
    """
    Opens the main application window and starts the Tkinter main event loop.

    This method initializes and displays the main window of the application,
    keeping it open and responsive to user interactions until the window is closed.
    """
    self.windowTk.mainloop()

