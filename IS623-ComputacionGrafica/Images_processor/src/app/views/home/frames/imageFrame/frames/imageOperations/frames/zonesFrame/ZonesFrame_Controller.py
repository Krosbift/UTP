import threading
from tkinter import IntVar
from .ZonesFrame_Component import ZonesFrameComponent
from .components.ZonesNormalRadiobutton_Component import ZonesNormalRadiobuttonComponent
from .components.ZonesLightRadiobutton_Component import ZonesLightRadiobuttonComponent
from .components.ZonesDarkRadiobutton_Component import ZonesDarkRadiobuttonComponent


class ZonesFrameController:
  """
  ZonesFrameController is responsible for managing the state and behavior of the zones frame in the application.
  Attributes:
    resize_timer (threading.Timer): Timer to handle delayed execution of image zone application.
    value (IntVar): Integer variable to store the current value of the zones.
    parent_controller: Reference to the parent controller.
    component: Instance of ZonesFrameComponent.
    zones_normal_radiobutton: Instance of ZonesNormalRadiobuttonComponent.
    zones_light_radiobutton: Instance of ZonesLightRadiobuttonComponent.
    zones_dark_radiobutton: Instance of ZonesDarkRadiobuttonComponent.
  Methods:
    __init__(parent):
      Initializes the ZonesFrameController with the given parent controller.
    create_component():
      Creates the ZonesFrameComponent instance.
    init_components():
      Initializes the radiobutton components for different zones.
    toggle_state():
      Toggles the state of the zones frame and applies the image zones after a delay.
    apply_image_zones(value):
      Applies the image zones based on the given value and updates the image in the parent controller.
  """

  def __init__(self, parent):
    self.resize_timer = None
    self.value = IntVar(value=0)
    self.parent_controller = parent
    self.create_component()
    self.init_components()

  
  def create_component(self):
    self.component = ZonesFrameComponent(self)
  

  def init_components(self):
    self.zones_normal_radiobutton = ZonesNormalRadiobuttonComponent(self)
    self.zones_light_radiobutton = ZonesLightRadiobuttonComponent(self)
    self.zones_dark_radiobutton = ZonesDarkRadiobuttonComponent(self)


  def toggle_state(self):
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.2, self.apply_image_zones, [self.value.get()])
    self.resize_timer.start()


  def apply_image_zones(self, value):
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_zones(value)
      self.parent_controller.parent_controller.applied_image_operation(new_image)

