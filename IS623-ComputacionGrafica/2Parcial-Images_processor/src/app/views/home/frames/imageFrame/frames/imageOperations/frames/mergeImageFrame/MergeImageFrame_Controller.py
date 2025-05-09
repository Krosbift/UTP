import tkinter as tk
from tkinter import filedialog
from .MergeImageFrame_Component import MergeImageFrameComponent
from .components.MergeImageButton_Component import MergeImageButtonComponent
from .components.MergeImageSpinbox_Component import MergeImageSpinboxComponent


class MergeImageFrameController:
  """
  MergeImageFrameController is responsible for managing the merge image frame operations.
  Attributes:
    resize_timer (None): Timer for resizing operations.
    parent_controller (object): Reference to the parent controller.
    component (MergeImageFrameComponent): Component for the merge image frame.
    merge_button_component (MergeImageButtonComponent): Component for the merge button.
    merge_spinbox_component (MergeImageSpinboxComponent): Component for the merge spinbox.
  Methods:
    __init__(parent):
      Initializes the MergeImageFrameController with the given parent.
    create_component():
      Creates the merge image frame component.
    init_components():
      Initializes the merge button and spinbox components.
    toggle_state():
      Toggles the state by applying the combine image operation with the value from the spinbox component.
    apply_combine_image(value):
      Applies the combine image operation with the given value.
    select_file():
      Opens a file dialog to select a file and returns the selected file path.
  """

  def __init__(self, parent):
    self.resize_timer = None
    self.parent_controller = parent
    self.create_component()
    self.init_components()

  
  def create_component(self):
    self.component = MergeImageFrameComponent(self)
  

  def init_components(self):
    self.merge_button_component = MergeImageButtonComponent(self)
    self.merge_spinbox_component = MergeImageSpinboxComponent(self)


  def toggle_state(self):
    self.apply_combine_image(self.merge_spinbox_component.get())


  def apply_combine_image(self, value):
    route = self.select_file()
    if self.parent_controller.parent_controller.imageLabelComponent.original_image is not None:
      new_image = self.parent_controller.parent_controller.imageChangeService.set_transparence(route, float(value))
      self.parent_controller.parent_controller.applied_image_operation(new_image)


  def select_file(self):
    entry_var = tk.StringVar()
    file_path = filedialog.askopenfilename()

    if file_path:
      entry_var.set(file_path)

    if entry_var.get():
      return entry_var.get()