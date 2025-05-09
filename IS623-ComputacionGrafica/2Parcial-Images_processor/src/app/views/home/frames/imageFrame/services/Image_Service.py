from PIL import Image, ImageTk

class ImageService:


  def __init__(self) -> None:
    pass


  def get_load_image(self, route, width, height):
    """
    Opens an image from the specified file path, resizes it to the given width and height, 
    and returns a PhotoImage object.

    Args:
      route (str): The file path to the image.
      width (int): The desired width of the image.
      height (int): The desired height of the image.

    Returns:
      ImageTk.PhotoImage: The resized image as a PhotoImage object.
    """
    image = Image.open(route)
    image = image.resize((int(width), int(height)), Image.LANCZOS)
    return ImageTk.PhotoImage(image)
  

  def get_new_image(self, image, width, height):
    """
    Resizes the given image to the specified width and height, and converts it to a PhotoImage object.

    Args:
      image (PIL.Image.Image): The image to be resized.
      width (int): The desired width of the resized image.
      height (int): The desired height of the resized image.

    Returns:
      ImageTk.PhotoImage: The resized image as a PhotoImage object.
    """
    image = image.resize((int(width), int(height)), Image.LANCZOS)
    return ImageTk.PhotoImage(image)

