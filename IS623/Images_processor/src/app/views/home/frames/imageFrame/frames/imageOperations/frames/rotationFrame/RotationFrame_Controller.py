import threading
from ........shared.components.labelScaleSpinboxFrame.LabelScaleSpinboxFrame_Component import LabelScaleSpinboxFrameComponent
from ........shared.components.labelScaleSpinboxFrame.components.Scale_Component import ScaleComponent
from ........shared.components.labelScaleSpinboxFrame.components.Spinbox_Component import SpinBoxComponent
from ........shared.components.labelScaleSpinboxFrame.components.TextLabel_Component import TextLabelComponent


class RotationFrameController:
  """
  Controller class for handling the rotation frame operations in the image processing application.
  Attributes:
    parent_controller (object): The parent controller object.
    resize_timer (threading.Timer): Timer for handling delayed image rotation application.
    component (LabelScaleSpinboxFrameComponent): Component for the rotation frame.
  Methods:
    __init__(parent):
      Initializes the RotationFrameController with the given parent controller.
    create_component():
      Creates the component for the rotation frame.
    init_components():
      Initializes the components for rotation control, including labels, spinbox, and scale.
    on_scale_move(value):
      Handles the event when the rotation scale is moved. Updates the spinbox value and applies the rotation after a delay.
    on_spinbox_change(value):
      Handles the event when the rotation spinbox value is changed. Updates the scale value and applies the rotation after a delay.
    apply_image_rotation(value):
      Applies the image rotation using the parent controller's image change service and updates the displayed image.
  """

  def __init__(self, parent) -> None:
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()

  
  def create_component(self):
    self.component = LabelScaleSpinboxFrameComponent(self, relx=0.01, rely=0.16)


  def init_components(self):
    self.rotationTextLabel = TextLabelComponent(self, text="Rotación: ", style_name="RotationLabel.TLabel")
    self.rotationSpinbox = SpinBoxComponent(self, command=self.on_spinbox_change, initial_value=0, min_value=0, max_value=360, increment=1, notation="integer_with_degree")
    self.rotationScale = ScaleComponent(self, command=self.on_scale_move, initial_value=0, min_value=0, max_value=360)


  def on_scale_move(self, value):
    self.rotationSpinbox.set_value(value)
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_rotation, [value])
    self.resize_timer.start()


  def on_spinbox_change(self, value):
    self.rotationScale.set_value(value)
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_rotation, [value])
    self.resize_timer.start()


  def apply_image_rotation(self, value):
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_rotation(value)
      self.parent_controller.parent_controller.applied_image_operation(new_image)

