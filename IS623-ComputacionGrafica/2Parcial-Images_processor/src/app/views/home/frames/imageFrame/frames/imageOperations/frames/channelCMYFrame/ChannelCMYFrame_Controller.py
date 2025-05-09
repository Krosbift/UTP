import threading
from ........shared.components.labelThreeCheckboxFrame.LabelThreeCheckboxFrame_Component import LabelThreeCheckboxFrameComponent
from ........shared.components.labelThreeCheckboxFrame.components.TextLabel_Component import TextLabelComponent
from ........shared.components.labelThreeCheckboxFrame.components.Checkbox_Component import CheckboxbuttonComponent


class ChannelCMYFrameController:
  """
  ChannelCMYFrameController is responsible for managing the CMY channel operations in the image processing application.
  Attributes:
    resize_timer (threading.Timer): Timer to handle resize events.
    parent_controller (object): Reference to the parent controller.
    component (LabelThreeCheckboxFrameComponent): Component for the CMY channel frame.
    text_label (TextLabelComponent): Label for the CMY channel.
    cyan_checkbox (CheckboxbuttonComponent): Checkbox for the Cyan channel.
    magenta_checkbox (CheckboxbuttonComponent): Checkbox for the Magenta channel.
    yellow_checkbox (CheckboxbuttonComponent): Checkbox for the Yellow channel.
  Methods:
    __init__(parent):
      Initializes the ChannelCMYFrameController with the given parent controller.
    create_component():
      Creates the main component for the CMY channel frame.
    init_components():
      Initializes the components (label and checkboxes) for the CMY channel frame.
    detect_change():
      Detects changes in the checkbox states and starts a timer to apply the CMY filter.
    apply_image_channelCMY():
      Applies the CMY filter to the image based on the checkbox states and updates the parent controller.
  """

  def __init__(self, parent) -> None:
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()

  
  def create_component(self):
    self.component = LabelThreeCheckboxFrameComponent(self, relx=0.5, rely=0.40)


  def init_components(self):
    self.text_label = TextLabelComponent(self, text="Channel CMY: ", style_name="TitleChannel.TLabel")
    self.cyan_checkbox = CheckboxbuttonComponent(self, text="Cyan", relx=0.62, rely=0, command=self.detect_change)
    self.magenta_checkbox = CheckboxbuttonComponent(self, text="Magenta", relx=0.62, rely=0.32, command=self.detect_change)
    self.yellow_checkbox = CheckboxbuttonComponent(self, text="Yellow", relx=0.62, rely=0.64, command=self.detect_change)


  def detect_change(self):
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_channelCMY, [])
    self.resize_timer.start()


  def apply_image_channelCMY(self):
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_cmy_filter(self.cyan_checkbox.var.get(), self.magenta_checkbox.var.get(), self.yellow_checkbox.var.get())
      self.parent_controller.parent_controller.applied_image_operation(new_image)

