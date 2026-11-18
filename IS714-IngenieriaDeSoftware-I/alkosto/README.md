<div align="center">

# 🛍️ Alkosto — HamKov Engineers

**Ingeniería de Software I · Proyecto de semestre**

*E‑commerce full‑stack inspirado en la tienda Alkosto*

![Angular](https://img.shields.io/badge/Angular%2020-DD0031?style=flat-square&logo=angular&logoColor=white)
![NestJS](https://img.shields.io/badge/NestJS%2011-E0234E?style=flat-square&logo=nestjs&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=flat-square&logo=typescript&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![TypeORM](https://img.shields.io/badge/TypeORM-FE0803?style=flat-square&logo=typeorm&logoColor=white)
![Netlify](https://img.shields.io/badge/Netlify-00C7B7?style=flat-square&logo=netlify&logoColor=white)
![k6](https://img.shields.io/badge/k6-7D64FF?style=flat-square&logo=k6&logoColor=white)

</div>

[🏠 Inicio](../../README.md) › [Ingeniería de Software I](../README.md) › **Alkosto**

---

## 📌 Descripción

Proyecto realizado en equipo (**HamKov Engineers**) para la asignatura de Ingeniería de Software I: una tienda en línea con frontend en **Angular**, backend en **NestJS** sobre **PostgreSQL** y pruebas de rendimiento con **k6** y **JMeter**.

## ✨ Funcionalidades

| | Funcionalidad |
| :-: | :--- |
| 🏠 | Página de inicio y catálogo de productos |
| 🔎 | Búsqueda en vivo mientras el usuario escribe |
| 📄 | Vista de detalle de producto |
| 🛒 | Carrito de compras |
| 🔐 | Registro, inicio de sesión y cambio de contraseña |
| ✉️ | Verificación por correo y códigos OTP por SMS / WhatsApp (Twilio) |
| 👤 | Perfil de usuario |
| 📍 | Nuestras tiendas y página de ayuda |

## 🏗️ Arquitectura

```mermaid
flowchart LR
    U["👤 Usuario"] --> APP["🅰️ app<br/>Angular"]
    APP -- "REST" --> API["🐈 api<br/>NestJS"]
    API -- "TypeORM" --> DB[("🐘 PostgreSQL")]
    API --> EXT["✉️ Email · 📱 Twilio"]
    PERF["📈 perf<br/>k6 · JMeter"] -. "carga" .-> API
```

## 🗂️ Estructura

| Carpeta | Contenido |
| :--- | :--- |
| [`app/`](app) | Frontend en Angular (páginas, componentes y servicios). |
| [`api/`](api) | Backend en NestJS: módulos `auth`, `users`, `products` y `shopping-cart`. |
| [`perf/`](perf) | Pruebas de carga que simulan a un usuario escribiendo en el buscador. |

## ▶️ Cómo ejecutarlo

Consulta el README de cada parte:

1. [⚙️ API](api/README.md) — configurar variables de entorno y levantar el servidor.
2. [🅰️ App](app/README.md) — levantar el frontend.
3. [📈 Perf](perf/README.md) — ejecutar las pruebas de rendimiento.

<div align="center"><sub><a href="../README.md">⬅ Volver a Ingeniería de Software I</a></sub></div>
