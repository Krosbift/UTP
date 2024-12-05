from tkinter import ttk


class BinarizationComboxComponent(ttk.Combobox):
  
  def __init__(self, controller):
    self.controller = controller
    self._configure_combobox()
    self._bind_events()
    self.set('Original')


  def _configure_combobox(self):
    super().__init__(self.controller.component, values=['Original', 'Binarization'], state='readonly')
    self.pack(side='left', fill='x', expand=True)


  def _bind_events(self):
    self.bind('<<ComboboxSelected>>', self.on_select)
    self.controller.component.bind("<Configure>", self._on_resize)


  def _on_resize(self, _):
    self.place_configure(relx=0.5, rely=0.35, relwidth=0.2)


  def on_select(self, _):
    self.controller.change_value()

