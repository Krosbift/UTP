<div align="center">

# 🅰️ gRPC Bank — Cliente web

![Angular](https://img.shields.io/badge/Angular%2020-DD0031?style=flat-square&logo=angular&logoColor=white)
![gRPC-Web](https://img.shields.io/badge/gRPC--Web-244C5A?style=flat-square)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=flat-square&logo=typescript&logoColor=white)

</div>

[🏠 Inicio](../../../../README.md) › [Sistemas Distribuidos](../../../README.md) › [gRPC Bank](../../README.md) › **Cliente web**

---

Interfaz en **Angular** que consume los servicios del banco mediante **gRPC‑Web**.

## 📄 Páginas

| Página | Descripción |
| :--- | :--- |
| 🔐 `login` | Inicio de sesión. |
| 📝 `register` | Registro de usuarios. |
| 📊 `dashboard` | Resumen y saldo total del banco en tiempo real. |
| 💸 `transactions` | Movimientos y nuevas transacciones de la cuenta. |
| 👤 `user-account` | Datos de la cuenta del usuario. |

## ▶️ Comandos

```bash
npm install
npm start                # ng serve → http://localhost:4200
npm run build            # compila en dist/
npm test                 # pruebas unitarias
npm run generate:grpc    # regenera src/app/grpc a partir de proto/bank.proto (requiere protoc y protoc-gen-grpc-web)
```

> [!IMPORTANT]
> Los servicios apuntan a un proxy gRPC‑Web en `http://localhost:8081`. Consulta el [README del proyecto](../../README.md) para levantar el servidor y el proxy.

<div align="center"><sub><a href="../../README.md">⬅ Volver a gRPC Bank</a></sub></div>
