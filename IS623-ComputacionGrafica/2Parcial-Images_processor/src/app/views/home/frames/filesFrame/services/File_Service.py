import tkinter as tk
from tkinter import filedialog

class FileService:


  def __init__(self) -> None:
    pass


  def select_file(self):
    entry_var = tk.StringVar()
    file_path = filedialog.askopenfilename()

    if file_path:
      entry_var.set(file_path)

    if entry_var.get():
      return entry_var.get()

