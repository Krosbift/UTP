<div align="center">

# 🔐 Desencriptadora

**Programación II · Tercer parcial**

![C](https://img.shields.io/badge/C-A8B9CC?style=flat-square&logo=c&logoColor=black)

</div>

[🏠 Inicio](../../README.md) › [Programación II](../README.md) › **Desencriptadora**

---

## 📌 Enunciado

Crear un programa que sirva de apoyo para desencriptar el archivo `confidencial09` (ubicado en [`decrypt/specialfiles`](decrypt/specialfiles)) y dejar el resultado en un archivo llamado `decrypt09`.

El archivo fue cifrado combinando dos métodos básicos:

1. 🔁 **Cifrado por desplazamiento.**
2. 🔑 **Cifrado por palabra clave.**

## 🗂️ Estructura

```text
3Parcial-Desencriptadora/
├── encrypt/
│   └── encriptador.c          # Cifrador entregado por el docente
└── decrypt/
    ├── decrypt.c              # Mi solución
    └── specialfiles/
        ├── confidencial09     # Archivo cifrado (entrada)
        └── decrypt09          # Archivo descifrado (salida)
```

| Carpeta | Autor | Descripción |
| :--- | :--- | :--- |
| [`encrypt/`](encrypt) | PhD. Ricardo Moreno Laverde | Código usado para generar el archivo cifrado. |
| [`decrypt/`](decrypt) | Santiago Torifa Manso | Programa que resuelve el reto. |

## ▶️ Cómo ejecutarlo

```bash
cd decrypt
gcc decrypt.c -o decrypt
./decrypt
```

<div align="center"><sub><a href="../README.md">⬅ Volver a Programación II</a></sub></div>
