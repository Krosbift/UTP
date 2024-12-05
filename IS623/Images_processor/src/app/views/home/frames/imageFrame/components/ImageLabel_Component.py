import tkinter as tk
import threading


class ImageLabelComponent(tk.Label):
  """
  ImageLabelComponent is a custom Tkinter Label widget designed to display and manage images within a GUI application.
  Attributes:
    original_image: The original image loaded into the label.
    temporal_image: A temporary image used for display purposes.
    resize_timer: A timer used to handle image resizing events.
    controller: The controller managing this component.
  Methods:
    __init__(controller):
      Initializes the ImageLabelComponent with the given controller.
    _configure_label():
      Configures the label's initial properties and placement.
    _bind_events():
      Binds necessary events to the label.
    _on_resize(event):
      Handles the resize event for the label.
    _resize_image():
      Resizes the image based on the current dimensions of the label.
    load_image():
      Loads an image into the label and initializes related services.
    update_image(image=None):
      Updates the displayed image, optionally using a provided image.
    set_image_service(route):
      Sets the image service with the provided image route.
  """

  def __init__(self, controller):
    self.original_image = None
    self.temporal_image = None
    self.resize_timer = None
    self.controller = controller
    self._configure_label()
    self._bind_events()


  def _configure_label(self):
    """
    Configures the label component.

    This method initializes the label component with the specified controller component,
    sets the highlight thickness, and places the label at the specified position within
    its parent container.

    Attributes:
      self (object): The instance of the class containing this method.
    """
    super().__init__(self.controller.component, highlightthickness=1)
    self.place(x=0, y=0, relwidth=0.7, relheight=1, anchor="nw")


  def _bind_events(self):
    """
    Binds the <Configure> event of the parent controller's component to the _on_resize method.

    This method sets up an event listener that triggers the _on_resize method whenever the 
    parent controller's component is resized.

    Args:
      None

    Returns:
      None
    """
    self.controller.parent_controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    """
    Handles the resize event for the image label component.

    This method is triggered when the component is resized. It adjusts the 
    placement configuration of the component and sets a timer to resize the 
    image after a short delay. If a previous resize timer is active, it is 
    canceled before starting a new one.

    Args:
      _: The event object (not used in this method).
    """
    self.place_configure(x=0, y=0, relwidth=0.7, relheight=1, anchor="nw")
    if self.resize_timer is not None:
      self.resize_timer.cancel()
    self.resize_timer = threading.Timer(0.5, self._resize_image)
    self.resize_timer.start()


  def _resize_image(self):
    """
    Resizes the image if a file path is provided.

    This method checks if the file path in the `fileEntryComponent` of the 
    `FilesFrameController` is not empty. If a file path is present, it calls 
    the `update_image` method to resize the image accordingly.
    """
    if self.controller.parent_controller.childrenControllers["FilesFrameController"].fileEntryComponent.path_route.get() != "":
      self.update_image()


  def load_image(self):
    """
    Loads an image using the path provided by the FilesFrameController's fileEntryComponent.
    
    This method retrieves the image from the specified path, resizes it according to the 
    dimensions of the current component, and sets it as the current image. It also initializes 
    child controllers and updates the component.

    Returns:
      None
    """
    self.original_image = self.controller.service.get_load_image(
        self.controller.parent_controller.childrenControllers["FilesFrameController"].fileEntryComponent.path_route.get(),
        self.controller.component.winfo_width() * 0.7,
        self.controller.component.winfo_height()
      )
    self.temporal_image = self.original_image
    self.config(image=self.temporal_image)
    self.set_image_service(self.controller.parent_controller.childrenControllers["FilesFrameController"].fileEntryComponent.path_route.get())
    self.controller.init_childControllers()
    self.controller.imageChangeService.reset_all_values()
    return self.update()


  def update_image(self, image=None):
    """
    Updates the image displayed in the component.

    Parameters:
    image (optional): The new image to be displayed. If not provided, a filtered image will be used.

    Returns:
    None
    """
    self.temporal_image = self.controller.service.get_new_image(
        image if image is not None else self.controller.imageChangeService.apply_filter(),
        self.controller.component.winfo_width() * 0.7,
        self.controller.component.winfo_height()
      )
    self.config(image=self.temporal_image)
    return self.update()


  def set_image_service(self, route):
    """
    Sets the image using the provided route.

    Args:
      route (str): The path to the image file.

    Returns:
      bool: True if the image was successfully set, False otherwise.
    """
    return self.controller.imageChangeService.set_image(route)

