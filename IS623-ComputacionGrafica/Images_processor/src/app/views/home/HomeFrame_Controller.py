from .HomeFrame_Component import HomeFrameComponent
from .components.TitleLabel_Component import TitleLabelComponent
from .frames.filesFrame.FilesFrame_Controller import FilesFrameController
from .frames.imageFrame.ImageFrame_Controller import ImageFrameController


class HomeFrameController:
  """
  HomeFrameController is responsible for managing the home frame of the application.
  This controller initializes the main components and child controllers required for
  the home frame. It sets up the title label and manages file and image-related operations
  through its child controllers.
    parent_controller (object): The parent controller instance.
    component (HomeFrameComponent): The main component of the home frame.
  Methods:
    __init__(parent):
      Initializes the HomeFrameController with the given parent controller.
    create_component():
    init_childcontrollers():
  """

  def __init__(self, parent):
    self.parent_controller = parent
    self.create_component()
    self.init_childcontrollers()


  def create_component(self):
    """
    Creates an instance of HomeFrameComponent and assigns it to the component attribute.

    This method initializes the HomeFrameComponent with the current instance of the controller.
    """
    self.component = HomeFrameComponent(self)


  def init_childcontrollers(self):
    """
    Initializes child controllers for the HomeFrame.

    This method sets up the title label component and initializes the child controllers
    for managing files and images. The child controllers are stored in a dictionary
    with their respective names as keys.

    Attributes:
      titleLabel (TitleLabelComponent): The title label component for the HomeFrame.
      childrenControllers (dict): A dictionary containing instances of child controllers.
        - "FilesFrameController": Manages file-related operations.
        - "ImageFrameController": Manages image-related operations.
    """
    self.titleLabel = TitleLabelComponent(self)
    self.childrenControllers = {
        "FilesFrameController": FilesFrameController(self),
        "ImageFrameController": ImageFrameController(self),
      }

