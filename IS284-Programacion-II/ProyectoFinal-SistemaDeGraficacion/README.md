<div align="center">

# 📈 Sistema de graficación

**Programación II · Proyecto final**

*Gestión de usuarios con almacenamiento en archivos y graficación de polinomios*

![C](https://img.shields.io/badge/C-A8B9CC?style=flat-square&logo=c&logoColor=black)
![SDL2](https://img.shields.io/badge/SDL2-1E3A8A?style=flat-square)
![Windows](https://img.shields.io/badge/Windows-0078D6?style=flat-square&logo=windows&logoColor=white)

</div>

[🏠 Inicio](../../README.md) › [Programación II](../README.md) › **Sistema de graficación**

---

## 📌 Enunciado

Programa basado en archivos con:

- 👤 **Registro de usuarios** con contraseña **cifrada**.
- 📊 **Graficación de funciones polinómicas**, donde cada gráfica pertenece a su usuario.
- 🛡️ Un usuario administrador llamado **`root`** con acceso a todas las gráficas.

## 🗂️ Estructura

| Archivo / carpeta | Contenido |
| :--- | :--- |
| [`main.c`](main.c) | Programa completo. |
| [`seguridad.gra`](seguridad.gra) | Palabra clave usada para el cifrado de contraseñas. |
| [`font/`](font) | Fuente usada por SDL2_ttf para el texto. |
| [`src/`](src) | Cabeceras y librerías de SDL2 / SDL2_ttf (MinGW). |
| `SDL2.dll`, `SDL2_ttf.dll` | Librerías dinámicas necesarias en tiempo de ejecución. |

## ▶️ Cómo ejecutarlo

> [!IMPORTANT]
> El programa usa `conio.h`, así que está pensado para **Windows** con **MinGW / GCC**.

```bash
gcc main.c -o graficacion -I src/include -L src/lib/lib -L src/lib -lmingw32 -lSDL2main -lSDL2 -lSDL2_ttf
./graficacion
```

Ejecuta el programa desde esta carpeta para que encuentre `font/`, `seguridad.gra` y las `.dll`.

<div align="center"><sub><a href="../README.md">⬅ Volver a Programación II</a></sub></div>
