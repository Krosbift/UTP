import cv2
import numpy as np
import matplotlib.pyplot as plt


def show_histogram(pil_image):
  """
  Generates and displays a histogram showing the distribution of color channels (R, G, B) and luminosity intensity.
  
  Args:
    pil_image (PIL.Image.Image): The input image in PIL format.
  """
  np_image = np.array(pil_image)
  
  bgr_image = cv2.cvtColor(np_image, cv2.COLOR_RGB2BGR)
  
  gray_image = cv2.cvtColor(bgr_image, cv2.COLOR_BGR2GRAY)
  
  hist_b = cv2.calcHist([bgr_image], [0], None, [256], [0, 256])
  hist_g = cv2.calcHist([bgr_image], [1], None, [256], [0, 256])
  hist_r = cv2.calcHist([bgr_image], [2], None, [256], [0, 256])
  
  hist_lum = cv2.calcHist([gray_image], [0], None, [256], [0, 256])
  
  plt.figure(figsize=(10, 8))
  
  plt.subplot(2, 2, 1)
  plt.plot(hist_b, color='blue')
  plt.title('Blue Channel Histogram')
  plt.xlim([0, 256])
  
  plt.subplot(2, 2, 2)
  plt.plot(hist_g, color='green')
  plt.title('Green Channel Histogram')
  plt.xlim([0, 256])
  
  plt.subplot(2, 2, 3)
  plt.plot(hist_r, color='red')
  plt.title('Red Channel Histogram')
  plt.xlim([0, 256])
  
  plt.subplot(2, 2, 4)
  plt.plot(hist_lum, color='black')
  plt.title('Luminosity Intensity Histogram')
  plt.xlim([0, 256])
  
  plt.tight_layout()
  plt.show()
