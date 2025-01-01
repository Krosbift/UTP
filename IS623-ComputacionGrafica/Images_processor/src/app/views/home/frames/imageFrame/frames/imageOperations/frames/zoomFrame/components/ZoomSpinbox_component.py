from tkinter import ttk


class ZoomSpinboxComponent(ttk.Spinbox):
  
  def __init__(self, controller, relx, rely, min_value, max_value, command, initial_value, increment) -> None:
    self.controller = controller 
    self.relx = relx
    self.rely = rely
    self.min_value = min_value
    self.max_value = max_value
    self.command = command
    self.increment = increment
    self._configure_label()
    self._bind_events()
    self.set(initial_value)

  
  def _configure_label(self):
    super().__init__(self.controller.component, from_=self.min_value , to=self.max_value, increment=self.increment, style="TSpinbox", validate='key', validatecommand=(self.controller.component.register(self.validate_numeric_input), '%P'), command=self.command)
    self.place(relx=self.relx, rely=self.rely, relwidth=0.15, relheight=0.3)


  def _bind_events(self):
    self.controller.component.bind("<Configure>", self._on_resize)

  
  def _on_resize(self, _):
    self.place_configure(relx=self.relx, rely=self.rely, relwidth=0.15, relheight=0.3)


  def validate_numeric_input(self, P):
    if str.isdigit(P) or P == "":
      return True
    else:
      return False

