import threading
from .NegativeFrame_Component import NegativeFrameComponent
from .components.NegativeLabel_Component import NegativeLabelComponent
from .components.NegativeCombox_Component import NegativeComboxComponent


class NegativeFrameController:
  """
  NegativeFrameController is responsible for managing the negative image processing operations
  within the application. It initializes components, handles user interactions, and updates the
  negative image accordingly.
  Attributes:
    resize_timer (threading.Timer): Timer to handle delayed operations for resizing.
    parent_controller (object): Reference to the parent controller.
    component (NegativeFrameComponent): Component for negative frame operations.
    negative_label_component (NegativeLabelComponent): Component for the negative label.
    negative_combobox_component (NegativeComboxComponent): Component for the negative combobox.
  Methods:
    __init__(parent):
      Initializes the NegativeFrameController with the given parent controller.
    create_component():
      Creates the NegativeFrameComponent.
    init_components():
      Initializes the negative label and combobox components.
    change_value():
      Handles the change in value and updates the negative image after a delay.
    update_negative_image():
      Updates the negative image using the selected value from the combobox component.
  """

  def __init__(self, parent):
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()

  
  def create_component(self):
    self.component = NegativeFrameComponent(self)

  
  def init_components(self):
    self.negative_label_component = NegativeLabelComponent(self)
    self.negative_combobox_component = NegativeComboxComponent(self)


  def change_value(self):
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.update_negative_image)
    self.resize_timer.start()


  def update_negative_image(self):
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_negative(self.negative_combobox_component.get())
      self.parent_controller.parent_controller.applied_image_operation(new_image)

