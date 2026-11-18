<div align="center">

# 🌳 Estructura de Datos

**IS304 · Universidad Tecnológica de Pereira**

![C++](https://img.shields.io/badge/C++-00599C?style=for-the-badge&logo=cplusplus&logoColor=white)
![Estructuras](https://img.shields.io/badge/estructuras-9-38bdf8?style=for-the-badge)
![Algoritmos](https://img.shields.io/badge/algoritmos-1-818cf8?style=for-the-badge)

</div>

[🏠 Inicio](../README.md) › **Estructura de Datos**

---

## 📖 Sobre la asignatura

Implementaciones propias en **C++** de estructuras de datos clásicas, escritas como plantillas (`template`) en archivos de cabecera `.hh` y acompañadas de un `main.cpp` que las prueba.

## 📂 Contenido

### 🧱 Estructuras lineales

| Estructura | Descripción | Código |
| :--- | :--- | :---: |
| **Vector** | Secuencia dinámica de elementos con acceso aleatorio. | [📁](structures/vector) |
| **Lista doble** | Lista enlazada donde cada nodo apunta al anterior y al siguiente. | [📁](structures/double-list) |
| **Pila** | Estructura LIFO: el último en entrar es el primero en salir. | [📁](structures/stack) |
| **Cola** | Estructura FIFO: el primero en entrar es el primero en salir. | [📁](structures/queue) |
| **Skip List** | Lista ordenada probabilística con búsquedas rápidas por niveles. | [📁](structures/skip-list) |

### 🌲 Árboles y montículos

| Estructura | Descripción | Código |
| :--- | :--- | :---: |
| **Árbol binario de búsqueda** | Cada nodo tiene como máximo un hijo izquierdo y uno derecho. | [📁](structures/binary-tree) |
| **Árbol rojo‑negro** | BST auto‑balanceado: búsqueda, inserción y borrado en O(log n). | [📁](structures/red-black-tree) |
| **Treap** | Híbrido entre árbol binario de búsqueda y heap. | [📁](structures/treap) |
| **Heap** | Árbol donde cada padre es mayor (o menor) que sus hijos. | [📁](structures/heap) |

### 🔀 Algoritmos

| Algoritmo | Descripción | Código |
| :--- | :--- | :---: |
| **Karger** | Algoritmo probabilístico para encontrar el corte mínimo de un grafo. | [📁](algorithm/karger) |

## 🗂️ Estructura

```text
IS304-EstructuraDeDatos/
├── algorithm/karger/main.cpp
├── exceptions/my-exceptions.hh      # Excepciones compartidas
└── structures/
    └── <estructura>/
        ├── class/<estructura>.hh    # Implementación (template)
        └── main.cpp                 # Programa de prueba
```

## ▶️ Cómo ejecutar

Desde la carpeta de cualquier estructura:

```bash
g++ -std=c++17 main.cpp -o main
./main
```

<div align="center"><sub><a href="../README.md">⬆ Volver al inicio</a></sub></div>
