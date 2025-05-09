import threading
from ........shared.components.labelThreeCheckboxFrame.LabelThreeCheckboxFrame_Component import LabelThreeCheckboxFrameComponent
from ........shared.components.labelThreeCheckboxFrame.components.TextLabel_Component import TextLabelComponent
from ........shared.components.labelThreeCheckboxFrame.components.Checkbox_Component import CheckboxbuttonComponent


class ChannelRGBFrameController:
  """
  Controller class for managing the RGB channel frame in the application.
  Attributes:
    resize_timer (threading.Timer): Timer to handle resize events.
    parent_controller (object): Reference to the parent controller.
    component (LabelThreeCheckboxFrameComponent): Component for the RGB channel frame.
    text_label (TextLabelComponent): Label component for the RGB channel title.
    red_checkbox (CheckboxbuttonComponent): Checkbox component for the red channel.
    green_checkbox (CheckboxbuttonComponent): Checkbox component for the green channel.
    blue_checkbox (CheckboxbuttonComponent): Checkbox component for the blue channel.
  Methods:
    __init__(parent):
      Initializes the ChannelRGBFrameController with the given parent controller.
    create_component():
      Creates the main component for the RGB channel frame.
    init_components():
      Initializes the label and checkbox components for the RGB channels.
    detect_change():
      Detects changes in the checkbox states and starts a timer to apply the RGB filter.
    apply_image_channelRGB():
      Applies the RGB filter to the image based on the checkbox states.
  """

  def __init__(self, parent) -> None:
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()

  
  def create_component(self):
    self.component = LabelThreeCheckboxFrameComponent(self, relx=0.5, rely=0.28)


  def init_components(self):
    self.text_label = TextLabelComponent(self, text="Channel RGB: ", style_name="TitleChannel.TLabel")
    self.red_checkbox = CheckboxbuttonComponent(self, text="Red", relx=0.62, rely=0, command=self.detect_change)
    self.green_checkbox = CheckboxbuttonComponent(self, text="Green", relx=0.62, rely=0.32, command=self.detect_change)
    self.blue_checkbox = CheckboxbuttonComponent(self, text="Blue", relx=0.62, rely=0.64, command=self.detect_change)


  def detect_change(self):
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_channelRGB, [])
    self.resize_timer.start()


  def apply_image_channelRGB(self):
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_rgb_filter(self.red_checkbox.var.get(), self.green_checkbox.var.get(), self.blue_checkbox.var.get())
      self.parent_controller.parent_controller.applied_image_operation(new_image)


