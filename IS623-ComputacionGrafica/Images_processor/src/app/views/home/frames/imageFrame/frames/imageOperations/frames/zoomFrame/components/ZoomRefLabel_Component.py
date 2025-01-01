from tkinter import ttk


class ZoomRefLabelComponent(ttk.Label):
  
  def __init__(self, controller, relx, rely, text):
    self.controller = controller 
    self.relx = relx
    self.rely = rely
    self.text = text
    self._configure_label()
    self._bind_events()

  
  def _configure_label(self):
    super().__init__(self.controller.component, text=self.text, font=("Helvetica", 9))
    self.place(relx=self.relx, rely=self.rely)


  def _bind_events(self):
    self.controller.component.bind("<Configure>", self._on_resize)

  
  def _on_resize(self, _):
    self.place_configure(relx=self.relx, rely=self.rely)