from .services.File_Service import FileService
from .FilesFrame_Component import FilesFrameComponent
from .components.FileLabel_Component import FileLabelComponent
from .components.FileEntry_Component import FileEntryComponent
from .components.ExploreButton_Component import ExploreButtonComponent
from .components.LoadButton_Component import LoadButtonComponent


class FilesFrameController:
  """
  FilesFrameController is responsible for managing the file-related components 
  and interactions within the application.
  Attributes:
    service (FileService): An instance of the FileService class to handle file operations.
    parent_controller: The parent controller that manages this controller.
    component: An instance of FilesFrameComponent.
    loadButtonComponent: An instance of LoadButtonComponent.
    exploreButtonComponent: An instance of ExploreButtonComponent.
    fileEntryComponent: An instance of FileEntryComponent.
    fileLabelComponent: An instance of FileLabelComponent.
  Methods:
    __init__(parent):
      Initializes the FilesFrameController with the given parent controller.
    create_component():
    init_components():
      Initializes the components for the FilesFrameController.
    get_file_path():
      Prompts the user to select a file and sets the selected file path to the text variable of the file entry component.
    set_image_path():
  """

  def __init__(self, parent):
    self.service = FileService()
    self.parent_controller = parent
    self.create_component()
    self.init_components()


  def create_component(self):
    """
    Creates an instance of FilesFrameComponent and assigns it to the component attribute.

    This method initializes the component attribute with a new instance of 
    FilesFrameComponent, passing the current instance as an argument.
    """
    self.component = FilesFrameComponent(self)


  def init_components(self):
    """
    Initializes the components for the FilesFrame_Controller.

    This method sets up the following components:
    - LoadButtonComponent: A button to load files.
    - ExploreButtonComponent: A button to explore files.
    - FileEntryComponent: An entry field for file input.
    - FileLabelComponent: A label to display file information.
    """
    self.loadButtonComponent = LoadButtonComponent(self)
    self.exploreButtonComponent = ExploreButtonComponent(self)
    self.fileEntryComponent = FileEntryComponent(self)
    self.fileLabelComponent = FileLabelComponent(self)


  def get_file_path(self):
    """
    Prompts the user to select a file and sets the selected file path 
    to the text variable of the file entry component.

    This method uses the service to open a file selection dialog, 
    retrieves the selected file path, and updates the file entry 
    component with the selected path.
    """
    self.fileEntryComponent.set_textvariable(self.service.select_file())


  def set_image_path(self):
    """
    Sets the image path by invoking the set_image method on the ImageFrameController.

    Returns:
      The result of the set_image method from the ImageFrameController.
    """
    return self.parent_controller.childrenControllers["ImageFrameController"].set_image()

