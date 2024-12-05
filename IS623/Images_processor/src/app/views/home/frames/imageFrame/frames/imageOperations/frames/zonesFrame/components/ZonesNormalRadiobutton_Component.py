from tkinter import ttk


class ZonesNormalRadiobuttonComponent(ttk.Radiobutton):

  def __init__(self, controller):
    self.controller = controller
    self._configure_radiobutton()
    self._bind_events()


  def _configure_radiobutton(self):
    super().__init__(self.controller.component, text="Z.normales", variable=self.controller.value, value=0, command=self.controller.toggle_state)
    self.place(relx=0.25, rely=0.02)


  def _bind_events(self):
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    self.place(relx=0.25, rely=0.02)

