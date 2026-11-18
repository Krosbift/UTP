<div align="center">

# 🧠 Programación III

**IS453 · Universidad Tecnológica de Pereira**

![Prolog](https://img.shields.io/badge/Prolog-E61B23?style=for-the-badge)
![MiniZinc](https://img.shields.io/badge/MiniZinc-2E5C8A?style=for-the-badge)

</div>

[🏠 Inicio](../README.md) › **Programación III**

---

## 📖 Sobre la asignatura

Paradigmas **declarativos**: programación lógica con **Prolog** y programación por restricciones con **MiniZinc**. En lugar de decir *cómo* resolver un problema, se describe *qué* condiciones debe cumplir la solución.

## 📂 Contenido

### 📝 Tareas

| | Ejercicio | Lenguaje | Descripción |
| :-: | :--- | :--- | :--- |
| 🗺️ | [Colorear regiones](Tareas/ColorearRegiones) | MiniZinc | Colorear un mapa de regiones sin que dos vecinas compartan color. |
| 🧮 | [Ejercicios en MiniZinc](Tareas/EjerciciosEnMinizinc) | MiniZinc | Acertijos lógicos (edades, órdenes, asignaciones) modelados como restricciones. |
| 📄 | [Procesador de texto](Tareas/ProcesadorDeTexto) | Prolog | Pide un nombre de archivo y añade el texto que escriba el usuario hasta `fin`. |

### 📋 Segundo parcial

| Parte | Contenido |
| :--- | :--- |
| [1.ª parte](2-Parcial/1-Parte) | Un ejercicio en Prolog (notebook SWISH `.swinb`) y uno en MiniZinc. |
| [2.ª parte](2-Parcial/2-Parte) | Siete modelos de restricciones en MiniZinc. |

## ▶️ Cómo ejecutar

**MiniZinc** — instala el [MiniZinc IDE](https://www.minizinc.org/software.html), abre el archivo `.mzn` y presiona **Run**, o desde consola:

```bash
minizinc archivo.mzn
```

**Prolog** — instala [SWI‑Prolog](https://www.swi-prolog.org/download/stable):

```bash
swipl Tareas/ProcesadorDeTexto/procesadorTextoOptimo.pl
```

Los archivos `.swinb` se abren en [SWISH](https://swish.swi-prolog.org/).

<div align="center"><sub><a href="../README.md">⬆ Volver al inicio</a></sub></div>
