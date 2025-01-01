from tkinter import ttk


class ZonesLightRadiobuttonComponent(ttk.Radiobutton):

  def __init__(self, controller):
    self.controller = controller
    self._configure_radiobutton()
    self._bind_events()


  def _configure_radiobutton(self):
    super().__init__(self.controller.component, text="Z.claras", variable=self.controller.value, value=1, command=self.controller.toggle_state)
    self.place(relx=0.435, rely=0.02)


  def _bind_events(self):
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    self.place(relx=0.435, rely=0.02)

