<div align="center">

# 🐈 Alkosto — API

![NestJS](https://img.shields.io/badge/NestJS%2011-E0234E?style=flat-square&logo=nestjs&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=flat-square&logo=typescript&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=flat-square&logo=postgresql&logoColor=white)
![TypeORM](https://img.shields.io/badge/TypeORM-FE0803?style=flat-square&logo=typeorm&logoColor=white)
![Jest](https://img.shields.io/badge/Jest-C21325?style=flat-square&logo=jest&logoColor=white)

</div>

[🏠 Inicio](../../../README.md) › [Ingeniería de Software I](../../README.md) › [Alkosto](../README.md) › **API**

---

Backend REST del proyecto, construido con **NestJS** y **TypeORM** sobre PostgreSQL.

## 🧩 Módulos

| Módulo | Responsabilidad |
| :--- | :--- |
| `auth` | Registro, inicio de sesión, verificación y cambio de contraseña. |
| `users` | Gestión de usuarios. |
| `products` | Catálogo y búsqueda de productos. |
| `shopping-cart` | Carrito de compras. |
| `pg-typeorm` | Conexión a PostgreSQL. |
| `process-env` | Carga y validación de variables de entorno. |

## ⚙️ Variables de entorno

Crea un archivo `.env` en esta carpeta:

```env
HOST=localhost
PORT=3000
DATABASE_CONNECTION_STRING=postgresql://<usuario>:<contraseña>@<host>:5432/<base_de_datos>
EMAIL_USER=<correo>
EMAIL_PASSWORD=<contraseña_de_aplicación>
TWILIO_SID=<sid>
TWILIO_TOKEN=<token>
TWILIO_SMS_NUMBER=<número>
TWILIO_WHATSAPP_NUMBER=<número>
```

## ▶️ Comandos

```bash
npm install

npm run start:dev    # desarrollo con recarga automática
npm run start:prod   # producción (requiere npm run build)

npm run test         # pruebas unitarias
npm run test:e2e     # pruebas end-to-end
npm run test:cov     # cobertura
```

<div align="center"><sub><a href="../README.md">⬅ Volver a Alkosto</a></sub></div>
