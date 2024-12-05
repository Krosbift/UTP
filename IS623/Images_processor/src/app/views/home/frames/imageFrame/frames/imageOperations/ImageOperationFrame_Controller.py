from .ImageOperationFrame_Component import ImageOperationFrameComponent
from .components.ShowHistrogramButton_Component import ShowHistogramButtonComponent
from .frames.brightnessFrame.BrightnessFrame_Controller import BrightnessFrameController
from .frames.contrastFrame.ContrastFrame_Controller import ContrastFrameController
from .frames.rotationFrame.RotationFrame_Controller import RotationFrameController
from .frames.zonesFrame.ZonesFrame_Controller import ZonesFrameController
from .frames.channelRGBFrame.ChannelRGBFrame_Controller import ChannelRGBFrameController
from .frames.channelCMYFrame.ChannelCMYFrame_Controller import ChannelCMYFrameController
from .frames.zoomFrame.ZoomFrame_Controller import ZoomFrameController
from .frames.binarizationFrame.BinarizationFrame_Controller import BinarizationFrameController
from .frames.negativeFrame.NegativeFrame_Controller import NegativeFrameController
from .frames.mergeImageFrame.MergeImageFrame_Controller import MergeImageFrameController
from .components.SaveImageButton_Component import SaveImageButtonComponent


class ImageOperationFrameController:
  """
  ImageOperationFrameController is responsible for managing the image operation frame and its components.
  Attributes:
    parent_controller (object): The parent controller object.
    component (ImageOperationFrameComponent): The component associated with this controller.
    childrenControllers (dict): A dictionary containing instances of various child controllers.
    show_histogram_button_component (ShowHistogramButtonComponent): The component for the show histogram button.
  Methods:
    __init__(parent): Initializes the ImageOperationFrameController with the given parent.
    create_component(): Creates the main component for the image operation frame.
    init_components(): Initializes the child controllers and other components.
    view_histogram(): Triggers the view histogram functionality in the parent controller.
  """

  def __init__(self, parent):
    self.parent_controller = parent
    self.component = None
    self.create_component()
    self.init_components()


  def create_component(self):
    """
    Creates an instance of ImageOperationFrameComponent and assigns it to the component attribute.

    This method initializes the component attribute with a new instance of 
    ImageOperationFrameComponent, passing the current instance as an argument.
    """
    self.component = ImageOperationFrameComponent(self)


  def init_components(self):
    """
    Initializes the components for the ImageOperationFrame_Controller.

    This method sets up the child controllers for various image operations such as 
    brightness adjustment, contrast adjustment, rotation, zone selection, RGB and CMY 
    channel manipulation, zooming, binarization, negative effect, and image merging. 
    Additionally, it initializes the component for displaying the histogram.

    Attributes:
      childrenControllers (dict): A dictionary mapping controller names to their instances.
        - "BrightnessFrameController": Manages brightness adjustments.
        - "ContrastFrameController": Manages contrast adjustments.
        - "RotationFrameController": Manages image rotation.
        - "ZonesFrameController": Manages zone selections within the image.
        - "ChannelRGBFrameController": Manages RGB channel manipulations.
        - "ChannelCMYFrameController": Manages CMY channel manipulations.
        - "ZoomFrameController": Manages image zooming.
        - "BinarizationFrameController": Manages image binarization.
        - "NegativeFrameController": Manages negative effect application.
        - "MergeImageFrameController": Manages image merging operations.
      show_histogram_button_component (ShowHistogramButtonComponent): Component for displaying the histogram.
    """
    self.childrenControllers = {
        "BrightnessFrameController": BrightnessFrameController(self),
        "ContrastFrameController": ContrastFrameController(self),
        "RotationFrameController": RotationFrameController(self),
        "ZonesFrameController": ZonesFrameController(self),
        "ChannelRGBFrameController": ChannelRGBFrameController(self),
        "ChannelCMYFrameController": ChannelCMYFrameController(self),
        "ZoomFrameController": ZoomFrameController(self),
        "BinarizationFrameController": BinarizationFrameController(self),
        "NegativeFrameController": NegativeFrameController(self),
        "MergeImageFrameController": MergeImageFrameController(self)
      }
    self.show_histogram_button_component = ShowHistogramButtonComponent(self)
    self.save_image_button_component = SaveImageButtonComponent(self)


  def view_histogram(self):
    """
    Displays the histogram of the current image.

    This method calls the `view_histogram` method of the `imageChangeService`
    from the parent controller to display the histogram of the currently 
    selected image.

    Returns:
      None
    """
    self.parent_controller.imageChangeService.view_histogram()


  def download_image(self):
    """
    Downloads the current image.

    This method calls the `download_image` method of the `imageChangeService`
    from the parent controller to download the currently selected image.

    Returns:
      None
    """
    self.parent_controller.imageChangeService.save_image()