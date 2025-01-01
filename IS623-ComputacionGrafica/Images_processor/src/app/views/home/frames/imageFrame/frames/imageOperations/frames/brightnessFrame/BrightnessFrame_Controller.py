import threading
from ........shared.components.labelScaleSpinboxFrame.LabelScaleSpinboxFrame_Component import LabelScaleSpinboxFrameComponent
from ........shared.components.labelScaleSpinboxFrame.components.Scale_Component import ScaleComponent
from ........shared.components.labelScaleSpinboxFrame.components.Spinbox_Component import SpinBoxComponent
from ........shared.components.labelScaleSpinboxFrame.components.TextLabel_Component import TextLabelComponent


class BrightnessFrameController:
  """
  Controller class for managing the brightness adjustment frame in the application.
  Attributes:
    resize_timer (threading.Timer): Timer to handle delayed application of brightness changes.
    parent_controller (object): Reference to the parent controller.
    component (LabelScaleSpinboxFrameComponent): UI component for brightness adjustment.
    brightnessTextLabel (TextLabelComponent): Label for the brightness adjustment.
    brightnessSpinbox (SpinBoxComponent): Spinbox for numeric brightness adjustment.
    brightnessScale (ScaleComponent): Scale for graphical brightness adjustment.
  Methods:
    __init__(parent):
      Initializes the BrightnessFrameController with the given parent controller.
    create_component():
      Creates the UI component for brightness adjustment.
    init_components():
      Initializes the UI components for brightness adjustment.
    on_scale_move(value):
      Handles the event when the brightness scale is moved.
    on_spinbox_change(value):
      Handles the event when the brightness spinbox value is changed.
    apply_image_brightness(value):
      Applies the brightness adjustment to the image.
  """

  def __init__(self, parent):
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()


  def create_component(self):
    """
    Creates and initializes the LabelScaleSpinboxFrameComponent for the BrightnessFrame_Controller.

    This method sets up the component with specific relative positioning within the parent frame.

    Attributes:
      component (LabelScaleSpinboxFrameComponent): The component initialized with the specified relative x and y positions.
    """
    self.component = LabelScaleSpinboxFrameComponent(self, relx=0.01, rely=0)


  def init_components(self):
    """
    Initializes the components for the BrightnessFrame_Controller.

    This method sets up the following components:
    - brightnessTextLabel: A label displaying the text "Brillo: " with a specific style.
    - brightnessSpinbox: A spinbox for adjusting brightness with a range from 0 to 2, an initial value of 1, 
      and increments of 0.01. It uses a float notation with 2 decimal places.
    - brightnessScale: A scale for adjusting brightness with a range from 0 to 2 and an initial value of 1.

    Each component is associated with a command that handles changes in their values.
    """
    self.brightnessTextLabel = TextLabelComponent(self, text="Brillo: ", style_name="BrightnessLabel.TLabel")
    self.brightnessSpinbox = SpinBoxComponent(self, command=self.on_spinbox_change, initial_value=1, min_value=0, max_value=2, increment=0.01, notation="float_2_decimals")
    self.brightnessScale = ScaleComponent(self, command=self.on_scale_move, initial_value=1, min_value=0, max_value=2)


  def on_scale_move(self, value):
    """
    Handles the event when the scale (slider) is moved.

    This method updates the brightness spinbox with the new value from the scale,
    cancels any existing timer for applying brightness, and starts a new timer
    to apply the brightness after a short delay.

    Args:
      value (float): The new value from the scale (slider) indicating the brightness level.
    """
    self.brightnessSpinbox.set_value(value)
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_brightness, [value])
    self.resize_timer.start()


  def on_spinbox_change(self, value):
    """
    Handles the event when the spinbox value changes.

    This method updates the brightness scale with the new value and sets a timer to apply the image brightness adjustment after a short delay. If a previous timer is still running, it will be canceled before starting a new one.

    Args:
      value (int or float): The new value from the spinbox to set the brightness scale.
    """
    self.brightnessScale.set_value(value)
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_brightness, [value])
    self.resize_timer.start()


  def apply_image_brightness(self, value):
    """
    Adjusts the brightness of the current image.

    This method retrieves the original image from the parent controller's image label component,
    applies the brightness adjustment using the image change service, and then updates the image
    with the new brightness level.

    Args:
      value (int or float): The brightness level to be applied to the image. The exact range and
                  effect of this value depend on the implementation of the imageChangeService.
    """
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_brightness(value)
      self.parent_controller.parent_controller.applied_image_operation(new_image)

