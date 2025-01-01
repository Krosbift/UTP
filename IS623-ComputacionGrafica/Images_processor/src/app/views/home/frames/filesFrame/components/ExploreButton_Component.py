from .....shared.components.buttonStandard.ButtonStandard_Component import ButtonStandardComponent


class ExploreButtonComponent(ButtonStandardComponent):
  """
  ExploreButtonComponent is a custom button component that inherits from ButtonStandardComponent.
  It is designed to trigger the file exploration functionality.
  Attributes:
    controller (object): The controller object that manages the button's behavior.
  Methods:
    __init__(controller): Initializes the ExploreButtonComponent with the specified controller.
  """
  
  def __init__(self, controller):
    super().__init__(controller, text="Explorar", style="Explore.TButton", command=controller.get_file_path, relx=0.8)

