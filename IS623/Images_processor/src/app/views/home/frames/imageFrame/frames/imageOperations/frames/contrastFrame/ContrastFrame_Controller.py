import threading
from ........shared.components.labelScaleSpinboxFrame.LabelScaleSpinboxFrame_Component import LabelScaleSpinboxFrameComponent
from ........shared.components.labelScaleSpinboxFrame.components.Scale_Component import ScaleComponent
from ........shared.components.labelScaleSpinboxFrame.components.Spinbox_Component import SpinBoxComponent
from ........shared.components.labelScaleSpinboxFrame.components.TextLabel_Component import TextLabelComponent

class ContrastFrameController:
  """
  ContrastFrameController is responsible for managing the contrast adjustment components and their interactions.
  Attributes:
    resize_timer (threading.Timer): Timer to handle delayed application of contrast changes.
    parent_controller (object): Reference to the parent controller.
    component (LabelScaleSpinboxFrameComponent): UI component for contrast adjustment.
    contrastTextLabel (TextLabelComponent): Label for the contrast adjustment.
    contrastSpinbox (SpinBoxComponent): Spinbox for precise contrast value input.
    contrastScale (ScaleComponent): Scale for adjusting contrast value.
  Methods:
    __init__(parent): Initializes the ContrastFrameController with the given parent.
    create_component(): Creates the UI component for contrast adjustment.
    init_components(): Initializes the UI components for contrast adjustment.
    on_scale_move(value): Handles the event when the contrast scale is moved.
    on_spinbox_change(value): Handles the event when the contrast spinbox value is changed.
    apply_image_contrast(value): Applies the contrast adjustment to the image.
  """


  def __init__(self, parent):
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()


  def create_component(self):
    """
    Initializes and creates the LabelScaleSpinboxFrameComponent for the ContrastFrame.

    This method sets up the component with specific relative x and y positions
    within the parent frame.

    Attributes:
      component (LabelScaleSpinboxFrameComponent): The component created and initialized
      with the specified relative positions.
    """
    self.component = LabelScaleSpinboxFrameComponent(self, relx=0.01, rely=0.08)


  def init_components(self):
    """
    Initializes the components for the contrast adjustment frame.

    This method sets up the following components:
    - contrastTextLabel: A label displaying the text "Contraste: ".
    - contrastSpinbox: A spinbox for adjusting the contrast value, with a range from 0 to 2, 
      an initial value of 1, and increments of 0.01. It uses a float notation with 2 decimal places.
    - contrastScale: A scale for adjusting the contrast value, with a range from 0 to 2 
      and an initial value of 1.

    The spinbox and scale components are linked to their respective callback methods 
    (`on_spinbox_change` and `on_scale_move`) to handle user interactions.
    """
    self.contrastTextLabel = TextLabelComponent(self, text="Contraste: ", style_name="ContrastLabel.TLabel")
    self.contrastSpinbox = SpinBoxComponent(self, command=self.on_spinbox_change, initial_value=1, min_value=0, max_value=2, increment=0.01, notation="float_2_decimals")
    self.contrastScale = ScaleComponent(self, command=self.on_scale_move, initial_value=1, min_value=0, max_value=2)


  def on_scale_move(self, value):
    """
    Handles the event when the scale is moved.

    This method updates the contrast spinbox with the new value from the scale,
    cancels any existing resize timer, and starts a new timer to apply the image
    contrast after a short delay.

    Args:
      value (float): The new value from the scale.
    """
    self.contrastSpinbox.set_value(value)
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_contrast, [value])
    self.resize_timer.start()


  def on_spinbox_change(self, value):
    """
    Handles the event when the spinbox value changes.

    This method updates the contrast scale with the new value from the spinbox,
    cancels any existing resize timer, and starts a new timer to apply the image
    contrast after a short delay.

    Args:
      value (int): The new value from the spinbox.
    """
    self.contrastScale.set_value(value)
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_contrast, [value])
    self.resize_timer.start()


  def apply_image_contrast(self, value):
    """
    Adjusts the contrast of the current image.

    This method retrieves the original image from the parent controller's 
    imageLabelComponent and applies a contrast adjustment using the 
    imageChangeService. The adjusted image is then passed to the 
    applied_image_operation method of the parent controller.

    Args:
      value (float): The contrast value to be applied to the image. 
               Typically, values > 1 increase contrast, 
               values < 1 decrease contrast, and a value of 1 
               leaves the contrast unchanged.
    """
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_contrast(value)
      self.parent_controller.parent_controller.applied_image_operation(new_image)

