import tkinter as tk
from tkinter import filedialog
from PIL import Image
from ......core.ImageProcessing import ImageProcessing
from .Image_histograma import show_histogram


class ImageChangeService:
  """
  ImageChangeService class provides various methods to apply image processing filters and transformations.
  Methods:
    __init__():
      Initializes the ImageChangeService with default values for image processing parameters.
    apply_filter():
      Applies the current set of filters and transformations to the image and returns the modified image.
    set_image(route: str):
      Sets the image from the given file path and applies the current filters.
    set_brightness(value: float):
      Sets the brightness level and applies the current filters.
    set_contrast(value: float):
      Sets the contrast level and applies the current filters.
    set_rotation(value: float):
      Sets the rotation angle and applies the current filters.
    set_zones(value: int):
      Sets the zone highlighting mode and applies the current filters.
    set_rgb_filter(valueR: float, valueG: float, valueB: float):
      Sets the RGB filter values and applies the current filters.
    set_cmy_filter(valueC: float, valueM: float, valueY: float):
      Sets the CMY filter values and applies the current filters.
    set_zoom(valueX: float, valueY: float, factor: float):
      Sets the zoom parameters and applies the current filters.
    get_widthxheight() -> tuple:
      Returns the width and height of the current image.
    set_binarization(value: str):
      Enables or disables binarization based on the given value and applies the current filters.
    set_negative(value: str):
      Enables or disables the negative filter based on the given value and applies the current filters.
    set_transparence(route_new_image: str, value: float):
      Merges the current image with a new image with the given transparency value and returns the modified image.
    view_histogram():
      Displays the histogram of the current image.
  """

  def __init__(self):
    self.imageProcessing = ImageProcessing()
    self.image = None
    self.modified_image = None
    self.brightness = 1
    self.contrast = 1
    self.rotation = 0
    self.zones = 0
    self.channelR = 1
    self.channelG = 1
    self.channelB = 1
    self.channelC = 1
    self.channelN = 1
    self.channelY = 1
    self.zoomX = 0
    self.zoomY = 0
    self.zoomFactor = 0
    self.binarity = False
    self.negative = False
    self.transparence = 0.5


  def apply_filter(self):
    """
    Applies a series of image processing filters to the current image.

    The following filters are applied in sequence:
    1. Adjust brightness based on the `brightness` attribute.
    2. Adjust contrast based on the `contrast` attribute.
    3. Rotate the image based on the `rotation` attribute.
    4. Highlight light or dark zones based on the `zones` attribute.
    5. Apply an RGB filter based on the `channelR`, `channelG`, and `channelB` attributes.
    6. Apply a CMY filter based on the `channelC`, `channelN`, and `channelY` attributes.
    7. Zoom the image based on the `zoomX`, `zoomY`, and `zoomFactor` attributes.
    8. Binarize the image if the `binarity` attribute is True.
    9. Apply a negative filter if the `negative` attribute is True.

    The modified image is stored in the `modified_image` attribute and also returned.

    Returns:
      The modified image after applying all the filters.
    """
    if self.image is not None:
      image = self.image
      image = self.imageProcessing.adjust_brightness(image, self.brightness)
      image = self.imageProcessing.adjust_contrast(image, self.contrast)
      image = self.imageProcessing.rotate_image(image, self.rotation)
      image = (
        image if self.zones == 0 else self.imageProcessing.highlight_light_zones(image)
        if self.zones == 1 else self.imageProcessing.highlight_dark_zones(image)
      )
      image = self.imageProcessing.apply_rgb_filter(image, self.channelR, self.channelG, self.channelB)
      image = self.imageProcessing.apply_cmy_filter(image, self.channelC, self.channelN, self.channelY)
      image = self.imageProcessing.zoom_image(image, self.zoomX, self.zoomY, self.zoomFactor)
      image = self.imageProcessing.binarize_image(image) if self.binarity else image
      image = self.imageProcessing.negative_image(image) if self.negative else image
      self.modified_image = image
      return image


  def set_image(self, route):
    """
    Sets the image from the given file path and applies a filter to it.

    Args:
      route (str): The file path to the image.

    Returns:
      Image: The filtered image.
    """
    self.image = Image.open(route)
    return self.apply_filter()


  def set_brightness(self, value):
    """
    Sets the brightness level for the image and applies the filter.

    Args:
      value (int or float): The brightness level to set.

    Returns:
      Image: The filtered image with the new brightness level applied.
    """
    self.brightness = value
    return self.apply_filter()


  def set_contrast(self, value):
    """
    Sets the contrast value for the image and applies the filter.

    Args:
      value (float): The contrast value to be set.

    Returns:
      Image: The image with the applied contrast filter.
    """
    self.contrast = value
    return self.apply_filter()


  def set_rotation(self, value):
    """
    Sets the rotation value and applies the filter.

    Args:
      value (int or float): The rotation value to be set.

    Returns:
      The result of the apply_filter() method after setting the rotation value.
    """
    self.rotation = value
    return self.apply_filter()


  def set_zones(self, value):
    """
    Sets the zones attribute and applies a filter.

    Args:
      value: The value to set for the zones attribute.

    Returns:
      The result of the apply_filter method.
    """
    self.zones = value
    return self.apply_filter()


  def set_rgb_filter(self, valueR, valueG, valueB):
    """
    Sets the RGB filter values and applies the filter to the image.

    Args:
      valueR (int): The value for the red channel.
      valueG (int): The value for the green channel.
      valueB (int): The value for the blue channel.

    Returns:
      Image: The filtered image after applying the RGB filter.
    """
    self.channelR = valueR
    self.channelG = valueG
    self.channelB = valueB
    return self.apply_filter()


  def set_cmy_filter(self, valueC, valueM, valueY):
    """
    Sets the CMY (Cyan, Magenta, Yellow) filter values and applies the filter.

    Args:
      valueC (float): The value for the Cyan channel.
      valueM (float): The value for the Magenta channel.
      valueY (float): The value for the Yellow channel.

    Returns:
      Image: The image with the applied CMY filter.
    """
    self.channelC = valueC
    self.channelN = valueM
    self.channelY = valueY
    return self.apply_filter()


  def set_zoom(self, valueX, valueY, factor):
    """
    Sets the zoom parameters for the image and applies the filter.

    Args:
      valueX (float): The zoom level along the X-axis.
      valueY (float): The zoom level along the Y-axis.
      factor (float): The zoom factor to be applied.

    Returns:
      The result of the apply_filter() method.
    """
    self.zoomX = valueX
    self.zoomY = valueY
    self.zoomFactor = factor
    return self.apply_filter()


  def get_widthxheight(self):
    """
    Retrieve the width and height of the image.

    Returns:
      tuple: A tuple containing the width and height of the image.
           If the image is None, returns (0, 0).
    """
    if self.image is not None:
      return self.image.size
    return 0, 0


  def set_binarization(self, value):
    """
    Sets the binarity attribute based on the provided value and applies the filter.

    Args:
      value (str): The value to determine if binarity should be set to True or False.
             If the value is "Binarization", binarity is set to True. Otherwise, it is set to False.

    Returns:
      The result of the apply_filter() method.
    """
    self.binarity = True if value == "Binarization" else False
    return self.apply_filter()
  

  def set_negative(self, value):
    """
    Sets the negative attribute based on the provided value and applies the filter.

    Args:
      value (str): The value to determine if the negative attribute should be set to True. 
             If the value is "Negative", the negative attribute is set to True; otherwise, it is set to False.

    Returns:
      The result of the apply_filter() method.
    """
    self.negative = True if value == "Negative" else False
    return self.apply_filter()


  def set_transparence(self, route_new_image, value):
    """
    Sets the transparency of an image by merging it with a new image.

    Args:
      route_new_image (str): The file path to the new image.
      value (float): The transparency value to be applied during the merge.

    Returns:
      Image: The modified image with the applied transparency.
    """
    new_image = Image.open(route_new_image)
    old_image = self.apply_filter()
    self.modified_image = self.imageProcessing.merge_images(old_image, new_image, value)
    return self.modified_image


  def view_histogram(self):
    """
    Displays the histogram of the current image after applying a filter.

    This method checks if there is an image loaded. If an image is loaded,
    it applies a filter to the image and then displays the histogram of the
    filtered image.

    Returns:
      None
    """
    if self.image is not None:
      show_histogram(self.apply_filter())
  

  def save_image(self):
    """
    Saves the modified image to the specified file path.
    If no file path is provided, a file dialog will prompt the user to select a location and filename.
    Args:
      file_path (str, optional): The path where the image will be saved. Defaults to None.
    Raises:
      ValueError: If no modified image is available to save or if no file path is provided by the user.
    """

    if self.modified_image is not None:
      root = tk.Tk()
      root.withdraw()
      file_path = filedialog.asksaveasfilename(defaultextension=".jpg", filetypes=[("JPEG files", "*.jpg"), ("All files", "*.*")])
      if file_path:
        self.modified_image.save(file_path, format='JPEG')
      else:
        raise ValueError("No file path provided to save the image.")
    else:
      raise ValueError("No modified image to save.")


  def reset_all_values(self):
    """
    Resets all the attributes to their default values.

    Returns:
      None
    """
    self.modified_image = None
    self.brightness = 1
    self.contrast = 1
    self.rotation = 0
    self.zones = 0
    self.channelR = 1
    self.channelG = 1
    self.channelB = 1
    self.channelC = 1
    self.channelN = 1
    self.channelY = 1
    self.zoomX = 0
    self.zoomY = 0
    self.zoomFactor = 0
    self.binarity = False
    self.negative = False
    self.transparence = 0.5