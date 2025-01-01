from .....shared.components.buttonStandard.ButtonStandard_Component import ButtonStandardComponent


class LoadButtonComponent(ButtonStandardComponent):
  """
  LoadButtonComponent is a custom button component that inherits from ButtonStandardComponent.
  Attributes:
    controller (object): The controller object that manages the button's behavior.
  Methods:
    __init__(controller): Initializes the LoadButtonComponent with the given controller, sets the button text to "Cargar", applies the "Load.TButton" style, and binds the button's command to the controller's set_image_path method.
  """
  
  def __init__(self, controller):
    self.controller = controller
    super().__init__(self.controller, text="Cargar", style="Load.TButton", command=self.controller.set_image_path, relx=0.9)

