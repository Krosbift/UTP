from tkinter import ttk


class NegativeLabelComponent(ttk.Label):
  
  def __init__(self, controller):
    self.controller = controller 
    self._configure_label()
    self._bind_events()

  
  def _configure_label(self):
    super().__init__(self.controller.component, text="Negative: ", font=("Helvetica", 12))
    self.place(relx=0.25, rely=0.4)


  def _bind_events(self):
    self.controller.component.bind("<Configure>", self._on_resize)

  
  def _on_resize(self, _):
    self.place_configure(relx=0.25, rely=0.4)

