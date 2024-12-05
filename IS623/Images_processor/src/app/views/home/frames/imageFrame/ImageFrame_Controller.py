from .services.Image_Service import ImageService
from .services.ImageChange_Service import ImageChangeService
from .ImageFrame_Component import ImageFrameComponent
from .components.ImageLabel_Component import ImageLabelComponent
from .frames.imageOperations.ImageOperationFrame_Controller import ImageOperationFrameController


class ImageFrameController:
  """
  ImageFrameController is responsible for managing the image frame component and its interactions.
  Attributes:
    service (ImageService): Service for handling image-related operations.
    imageChangeService (ImageChangeService): Service for handling image change operations.
    parent_controller: Reference to the parent controller.
    component (ImageFrameComponent): The image frame component managed by this controller.
    imageLabelComponent (ImageLabelComponent): Component for displaying and managing the image label.
    childrenControllers (dict): Dictionary of child controllers.
  Methods:
    __init__(parent): Initializes the ImageFrameController with a parent controller.
    create_component(): Creates the image frame component.
    init_components(): Initializes the components used by the controller.
    init_childControllers(): Initializes the child controllers.
    set_image(): Loads the image into the image label component.
    applied_image_operation(image): Updates the image label component with the given image.
  """

  def __init__(self, parent):
    self.service = ImageService()
    self.imageChangeService = ImageChangeService()
    self.parent_controller = parent
    self.create_component()
    self.init_components()


  def create_component(self):
    """
    Creates an instance of ImageFrameComponent and assigns it to the component attribute.

    This method initializes the ImageFrameComponent with the current instance of the controller.
    """
    self.component = ImageFrameComponent(self)


  def init_components(self):
    """
    Initializes the components for the ImageFrame_Controller.

    This method sets up the imageLabelComponent by creating an instance of 
    ImageLabelComponent and assigning it to the imageLabelComponent attribute.
    """
    self.imageLabelComponent = ImageLabelComponent(self)


  def init_childControllers(self):
    """
    Initializes child controllers for the ImageFrame_Controller.

    This method sets up the `childrenControllers` dictionary with instances
    of child controllers. Currently, it initializes the 
    `ImageOperationFrameController` with the current instance of 
    `ImageFrame_Controller`.

    Attributes:
      childrenControllers (dict): A dictionary containing instances of 
      child controllers.
    """
    self.childrenControllers = {
        "ImageOperationFrameController": ImageOperationFrameController(self)
    }


  def set_image(self):
    """
    Loads an image into the image label component.

    This method calls the `load_image` method of the `imageLabelComponent`
    to load and display an image.
    """
    self.imageLabelComponent.load_image()


  def applied_image_operation(self, image):
    """
    Applies an image operation and updates the image label component.

    Args:
      image: The image to be processed and displayed.
    """
    self.imageLabelComponent.update_image(image)

