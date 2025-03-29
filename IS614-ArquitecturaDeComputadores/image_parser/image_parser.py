from PIL import Image

image = Image.open("./power_girls.jpg")
pixels = image.load()

with open("power_girls.bat", "w") as file:
    for y in range(image.height):
        for x in range(image.width):
            r, g, b = pixels[x, y]
            file.write(f"{r:08b}{g:08b}{b:08b}")
