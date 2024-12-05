import numpy as np
from PIL import Image
from scipy.ndimage import rotate

class ImageProcessing:

  def __init__(self):
    pass

  def adjust_brightness(self, image, factor):
    """
    Adjust the brightness of an image.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    factor (float): The factor by which to adjust the brightness. 
      Values > 1 will increase brightness, values < 1 will decrease it.
    
    Returns:
    PIL.Image.Image: The brightness-adjusted image.
    """

    # Convert PIL Image to NumPy array
    image = np.array(image)

    # Ensure the factor is a float
    factor = float(factor)
    
    # Adjust brightness
    adjusted_image = np.clip(image * factor, 0, 255).astype(np.uint8)
    
    # Convert back to PIL Image
    adjusted_image = Image.fromarray(adjusted_image)
    
    return adjusted_image


  def adjust_contrast(self, image, factor):
    """
    Adjust the contrast of an image.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    factor (float): The factor by which to adjust the contrast. 
      Values > 1 will increase contrast, values < 1 will decrease it.
    
    Returns:
    PIL.Image.Image: The contrast-adjusted image.
    """
    
    # Convert PIL Image to NumPy array
    image = np.array(image)
    
    # Ensure the factor is a float
    factor = float(factor)
    
    # Calculate the mean of the image
    mean = np.mean(image)
    
    # Adjust contrast
    adjusted_image = np.clip((image - mean) * factor + mean, 0, 255).astype(np.uint8)
    
    # Convert back to PIL Image
    adjusted_image = Image.fromarray(adjusted_image)
    
    return adjusted_image
  

  def imrotate(self, image, x):
    """
    Rota una imagen en escala de grises o en color por un ángulo especificado.

    Parámetros:
    image (PIL.Image.Image): Imagen representada como un objeto PIL.Image.
    grados (float): Ángulo de rotación en grados.

    Retorna:
    PIL.Image.Image: Imagen rotada.

    Ejemplo de uso:
    >>> from PIL import Image
    >>> from ImageProcessing import ImageProcessing
    >>> img = Image.open('path_to_image.jpg')
    >>> processor = ImageProcessing()
    >>> rotated_img = processor.imrotate(img, 45)
    >>> rotated_img.show()
    """
    # Convertir la imagen PIL a un arreglo de NumPy
    grados = float(x)
    I = np.array(image)
    
    # Convertir los grados a radianes
    theta = -grados * np.pi / 180
    
    # Obtener las dimensiones de la imagen original
    M, N, C = I.shape  # C es el número de canales (3 para RGB)
    
    # Centro de la imagen original
    pc = np.array([N, M, 1]) / 2
    
    # Matriz de rotación inversa
    R = np.array([
      [np.cos(theta), -np.sin(theta), 0],
      [np.sin(theta),  np.cos(theta), 0],
      [0,              0,             1]
    ])
    
    R_inv = np.linalg.inv(R)
    
    # Calcular las nuevas dimensiones de la imagen rotada
    D = np.abs(R)
    z = np.array([N, M, 1])
    zp = np.dot(D, z)  # Nuevas dimensiones sin el término homogéneo
    
    # Dimensiones de la imagen rotada
    Np = int(np.ceil(zp[0]))  # Nueva anchura
    Mp = int(np.ceil(zp[1]))  # Nueva altura
    
    # Centro de la imagen rotada
    pc_p = np.array([Np, Mp, 1]) / 2
    
    # Inicializar la nueva imagen rotada
    I_rotada = np.zeros((Mp, Np, C), dtype=np.uint8)
    
    # Ciclos for para recorrer la imagen rotada
    for xp in range(Np):
      for yp in range(Mp):  
        # Coordenadas homogéneas del píxel en la imagen rotada
        p_p = np.array([xp, yp, 1])
        
        # Calcular la posición relativa respecto al centro de la imagen rotada
        p_p_rel = p_p - pc_p
        
        # Aplicar la matriz de rotación inversa a las coordenadas relativas
        p_rel = np.dot(R_inv, p_p_rel)
        
        # Ajustar las coordenadas al centro de la imagen original
        p = p_rel + pc
        
        # Redondear las coordenadas al píxel más cercano
        x = int(np.round(p[0]))
        y = int(np.round(p[1]))
        
        # Verificar si las coordenadas están dentro de los límites de la imagen original
        if 0 <= x < N and 0 <= y < M:
          # Asignar los valores de los píxeles de la imagen original a la imagen rotada
          I_rotada[yp, xp, :] = I[y, x, :]
    
    # Convertir la imagen rotada de vuelta a un objeto PIL.Image
    return Image.fromarray(I_rotada)



  def rotate_image(self, image, angle):
    """
    Rotate the image by a specified angle.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    angle (float): The angle by which to rotate the image, in degrees.
    
    Returns:
    PIL.Image.Image: The rotated image.
    """
    
    # Convert PIL Image to NumPy array
    image = np.array(image)
    
    # Ensure the angle is a float
    angle = float(angle)
    
    # Rotate the image
    rotated_image = rotate(image, angle, reshape=True, mode='nearest')
    
    # Convert back to PIL Image
    rotated_image = Image.fromarray(rotated_image.astype(np.uint8))
    
    return rotated_image


  def highlight_light_zones(self, image, threshold=200):
    """
    Highlight the light zones of an image.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    threshold (int): The threshold value to separate light zones.
    
    Returns:
    PIL.Image.Image: The image with light zones highlighted.
    """
    
    # Convert PIL Image to NumPy array
    image = np.array(image)
    
    # Create mask for light zones
    light_mask = image > threshold
    
    # Highlight light zones
    highlighted_image = np.zeros_like(image)
    highlighted_image[light_mask] = image[light_mask]
    
    # Convert back to PIL Image
    highlighted_image = Image.fromarray(highlighted_image)
    
    return highlighted_image


  def highlight_dark_zones(self, image, threshold=50):
    """
    Highlight the dark zones of an image.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    threshold (int): The threshold value to separate dark zones.
    
    Returns:
    PIL.Image.Image: The image with dark zones highlighted.
    """
    
    # Convert PIL Image to NumPy array
    image = np.array(image)
    
    # Create mask for dark zones
    dark_mask = image < threshold
    
    # Highlight dark zones
    highlighted_image = np.zeros_like(image)
    highlighted_image[dark_mask] = image[dark_mask]
    
    # Convert back to PIL Image
    highlighted_image = Image.fromarray(highlighted_image)
    
    return highlighted_image


  def apply_rgb_filter(self, image, show_red, show_green, show_blue):
    """
    Apply an RGB filter to an image by showing or hiding specific channels.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    show_red (bool): Whether to show the red channel.
    show_green (bool): Whether to show the green channel.
    show_blue (bool): Whether to show the blue channel.
    
    Returns:
    PIL.Image.Image: The RGB-filtered image.
    """
    image = np.array(image)
    
    red_mask = np.zeros_like(image)
    green_mask = np.zeros_like(image)
    blue_mask = np.zeros_like(image)
    
    if show_red:
        red_mask[:, :, 0] = image[:, :, 0]
    if show_green:
        green_mask[:, :, 1] = image[:, :, 1]
    if show_blue:
        blue_mask[:, :, 2] = image[:, :, 2]
    
    filtered_rgb_image = red_mask + green_mask + blue_mask
    
    if show_red and not show_green and not show_blue:
        filtered_rgb_image[:, :, 1] = 0
        filtered_rgb_image[:, :, 2] = 0
    elif show_green and not show_red and not show_blue:
        filtered_rgb_image[:, :, 0] = 0
        filtered_rgb_image[:, :, 2] = 0
    elif show_blue and not show_red and not show_green:
        filtered_rgb_image[:, :, 0] = 0
        filtered_rgb_image[:, :, 1] = 0
    
    filtered_image = Image.fromarray(filtered_rgb_image.astype(np.uint8))
    
    return filtered_image


  def apply_cmy_filter(self, image, show_cyan, show_magenta, show_yellow):
    """
    Apply a CMY filter to an image by showing or hiding specific channels.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    show_cyan (bool): Whether to show the cyan channel.
    show_magenta (bool): Whether to show the magenta channel.
    show_yellow (bool): Whether to show the yellow channel.
    
    Returns:
    PIL.Image.Image: The CMY-filtered image.
    """
    image = np.array(image)
    
    cmy_image = 255 - image
    
    cyan_mask = np.zeros_like(cmy_image)
    magenta_mask = np.zeros_like(cmy_image)
    yellow_mask = np.zeros_like(cmy_image)
    
    if show_cyan:
        cyan_mask[:, :, 0] = cmy_image[:, :, 0]
    if show_magenta:
        magenta_mask[:, :, 1] = cmy_image[:, :, 1]
    if show_yellow:
        yellow_mask[:, :, 2] = cmy_image[:, :, 2]
    
    filtered_cmy_image = cyan_mask + magenta_mask + yellow_mask
    
    if show_cyan and not show_magenta and not show_yellow:
        filtered_cmy_image[:, :, 1] = 0
        filtered_cmy_image[:, :, 2] = 0
    elif show_magenta and not show_cyan and not show_yellow:
        filtered_cmy_image[:, :, 0] = 0
        filtered_cmy_image[:, :, 2] = 0
    elif show_yellow and not show_cyan and not show_magenta:
        filtered_cmy_image[:, :, 0] = 0
        filtered_cmy_image[:, :, 1] = 0
    
    rgb_image = 255 - filtered_cmy_image
    
    filtered_image = Image.fromarray(rgb_image.astype(np.uint8))
    
    return filtered_image


  def zoom_image(self, image, x, y, scale_factor):
    """
    Zoom into an image from a specified point (x, y) with a given scale factor.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    x (int): The x-coordinate of the initial point.
    y (int): The y-coordinate of the initial point.
    scale_factor (float): The scale factor for zooming.
    
    Returns:
    PIL.Image.Image: The zoomed image.
    """
    if scale_factor == 0 or scale_factor == 0.0:
        return image

    image = np.array(image)
    height, width = image.shape[:2]
    new_height, new_width = int(height / scale_factor), int(width / scale_factor)
    
    # Calculate the cropping box
    left = int(max(0, x - new_width // 2))
    right = int(min(width, x + new_width // 2))
    top = int(max(0, y - new_height // 2))
    bottom = int(min(height, y + new_height // 2))
    
    # Crop the image
    cropped_image = image[top:bottom, left:right]
    
    # Resize the cropped image to the original dimensions
    zoomed_image = np.array(Image.fromarray(cropped_image).resize((width, height), Image.LANCZOS))
    
    return Image.fromarray(zoomed_image)


  def binarize_image(self, image, threshold=128):
    """
    Binarize the image by applying a threshold to convert it to black and white.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    threshold (int): The threshold value to binarize the image.
    
    Returns:
    PIL.Image.Image: The binarized image.
    """
    # Convert PIL Image to NumPy array
    image = np.array(image)
    
    # Apply threshold to binarize the image
    binarized_image = np.where(image > threshold, 255, 0)

    # binarized_image = binarized_image[:, :, 0]

    # Convert back to PIL Image
    binarized_image = Image.fromarray(binarized_image.astype(np.uint8))
    
    return binarized_image


  def negative_image(self, image):
    """
    Get the negative of an image.
    
    Parameters:
    image (PIL.Image.Image): The input image.
    
    Returns:
    PIL.Image.Image: The negative image.
    """
    # Convert PIL Image to NumPy array
    image = np.array(image)
    
    # Calculate the negative of the image
    negative_image = 255 - image
    
    # Convert back to PIL Image
    negative_image = Image.fromarray(negative_image.astype(np.uint8))
    
    return negative_image


  def merge_images(self, image1, image2, transparencia):
    """
    Combina dos imágenes aplicando un factor de transparencia a la segunda imagen.
    
    Parameters:
    image1 (PIL.Image.Image): La primera imagen.
    image2 (PIL.Image.Image): La segunda imagen.
    transparencia (float): El factor de transparencia para la segunda imagen (0 a 1).
    
    Returns:
    PIL.Image.Image: La imagen combinada.
    """
    # Convertir las imágenes a arrays de NumPy
    image1_np = np.array(image1)
    
    # Redimensionar la segunda imagen para que tenga las mismas dimensiones que la primera
    image2_resized = image2.resize(image1.size, Image.LANCZOS)
    image2_np = np.array(image2_resized)
    
    # Asegurarse de que ambas imágenes tengan el mismo número de canales
    if image1_np.shape[2] != image2_np.shape[2]:
        if image1_np.shape[2] == 4:
            image1_np = image1_np[:, :, :3]
        if image2_np.shape[2] == 4:
            image2_np = image2_np[:, :, :3]
    
    # Aplicar el factor de transparencia a la segunda imagen
    image2_np = image2_np * (1 - transparencia)
    
    # Combinar las dos imágenes
    imagen_combinada_np = np.clip(image1_np * transparencia + image2_np, 0, 255).astype(np.uint8)
    
    # Convertir la imagen combinada de vuelta a un objeto PIL.Image
    imagen_combinada = Image.fromarray(imagen_combinada_np)
    
    return imagen_combinada

