import threading
from .BinarizationFrame_Component import BinarizationFrameComponent
from .components.BinarizationLabel_Component import BinarizationLabelComponent
from .components.BinarizationCombox_Component import BinarizationComboxComponent


class BinarizationFrameController:
  """
  BinarizationFrameController is responsible for managing the binarization frame component and its operations.
  Attributes:
    resize_timer (threading.Timer): Timer to handle resize events.
    parent_controller (object): Reference to the parent controller.
    component (BinarizationFrameComponent): Instance of the binarization frame component.
    binarization_label_component (BinarizationLabelComponent): Instance of the binarization label component.
    binarization_combobox_component (BinarizationComboxComponent): Instance of the binarization combobox component.
  Methods:
    __init__(parent):
      Initializes the BinarizationFrameController with the given parent controller.
    create_component():
      Creates the binarization frame component.
    init_components():
      Initializes the binarization label and combobox components.
    change_value():
      Changes the binarization value and updates the image after a delay.
    update_binarization_image():
      Updates the binarization image using the selected binarization method.
  """

  def __init__(self, parent):
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()

  
  def create_component(self):
    self.component = BinarizationFrameComponent(self)

  
  def init_components(self):
    self.binarization_label_component = BinarizationLabelComponent(self)
    self.binarization_combobox_component = BinarizationComboxComponent(self)


  def change_value(self):
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.update_binarization_image)
    self.resize_timer.start()


  def update_binarization_image(self):
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_binarization(self.binarization_combobox_component.get())
      self.parent_controller.parent_controller.applied_image_operation(new_image)

