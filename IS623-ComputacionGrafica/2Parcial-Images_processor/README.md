<div align="center">

# 🖼️ Images Processor

**Computación Gráfica · Segundo parcial**

*Procesador de imágenes de escritorio hecho en Python*

![Python](https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=python&logoColor=white)
![Tkinter](https://img.shields.io/badge/Tkinter-GUI-38bdf8?style=flat-square)
![OpenCV](https://img.shields.io/badge/OpenCV-5C3EE8?style=flat-square&logo=opencv&logoColor=white)
![NumPy](https://img.shields.io/badge/NumPy-013243?style=flat-square&logo=numpy&logoColor=white)
![Matplotlib](https://img.shields.io/badge/Matplotlib-11557C?style=flat-square)

</div>

[🏠 Inicio](../../README.md) › [Computación Gráfica](../README.md) › **Images Processor**

---

## ✨ Funcionalidades

| Ajustes | Color | Transformaciones | Otros |
| :--- | :--- | :--- | :--- |
| ☀️ Brillo | 🟥 Filtros RGB | 🔄 Rotación | ⚫ Binarización |
| 🌓 Contraste | 🟦 Filtros CMY | 🔍 Zoom | 🎞️ Negativo |
| 💡 Resaltar zonas claras | 📊 Histograma | | 🧩 Combinar imágenes con transparencia |
| 🌑 Resaltar zonas oscuras | | | 💾 Guardar resultado |

## 🗂️ Estructura

La interfaz está organizada en **componentes** y **controladores**, separando la vista de la lógica de procesamiento:

```text
src/
├── __main__.py                    # Punto de entrada
└── app/
    ├── core/ImageProcessing.py    # Algoritmos de procesamiento
    └── views/
        ├── home/frames/
        │   ├── filesFrame/        # Selección y carga de archivos
        │   └── imageFrame/        # Vista de la imagen y operaciones
        │       └── frames/imageOperations/frames/
        │           ├── brightnessFrame/  contrastFrame/  rotationFrame/
        │           ├── channelRGBFrame/  channelCMYFrame/ zonesFrame/
        │           └── zoomFrame/  binarizationFrame/  negativeFrame/  mergeImageFrame/
        └── shared/components/     # Botones y controles reutilizables
```

## ▶️ Cómo ejecutarlo

```bash
pip install -r requirements.txt
python src
```

<div align="center"><sub><a href="../README.md">⬅ Volver a Computación Gráfica</a></sub></div>
