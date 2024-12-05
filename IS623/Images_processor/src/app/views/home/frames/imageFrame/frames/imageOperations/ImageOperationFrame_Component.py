from tkinter import ttk

class ImageOperationFrameComponent(ttk.Frame):
  
  def __init__(self, controller):
    self.controller = controller
    self._configure_frame()
    self._bind_events()

  def _configure_frame(self):
    super().__init__(self.controller.parent_controller.component)
    self.configure(style="Custom.TFrame")
    self.place(relx=0.7, y=0, relwidth=0.3, relheight=1, anchor="nw")

  def _bind_events(self):
    self.controller.parent_controller.component.bind("<Configure>", self._on_resize)

  def _on_resize(self, _):
    self.place_configure(relx=0.7, y=0, relwidth=0.3, relheight=1, anchor="nw")
