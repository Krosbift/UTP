<div align="center">

# 📈 Alkosto — Pruebas de rendimiento

![k6](https://img.shields.io/badge/k6-7D64FF?style=flat-square&logo=k6&logoColor=white)
![JMeter](https://img.shields.io/badge/JMeter-D22128?style=flat-square&logo=apachejmeter&logoColor=white)

</div>

[🏠 Inicio](../../../README.md) › [Ingeniería de Software I](../../README.md) › [Alkosto](../README.md) › **Perf**

---

Plantillas de **k6** y **JMeter** que simulan a un usuario **escribiendo** en el buscador del sitio: llaman repetidamente al endpoint de búsqueda con prefijos incrementales (`i`, `ip`, `iph`, …) para imitar la búsqueda en vivo.

## 🟣 k6

| Archivo | Contenido |
| :--- | :--- |
| [`k6/search_typing.js`](k6/search_typing.js) | Elige un término y llama a `/products/search` con cada prefijo. |
| [`k6/env.json`](k6/env.json) | Variables de ejemplo (`BASE_URL`, `SEARCH_TERMS`). |

1. Instala k6: <https://k6.io/docs/get-started/installation/>
2. Desde la carpeta `alkosto/` ejecuta:

```bash
BASE_URL=http://localhost:3000 k6 run perf/k6/search_typing.js

# con términos propios
BASE_URL=http://localhost:3000 SEARCH_TERMS="iphone,samsung,xiaomi" k6 run perf/k6/search_typing.js
```

## 🔴 JMeter

| Archivo | Contenido |
| :--- | :--- |
| [`jmeter/search_typing.jmx`](jmeter/search_typing.jmx) | Plan de prueba base. Espera un archivo `search_terms.csv` en la misma carpeta (una columna de términos, sin encabezado o con encabezado `term`). |

Abre el `.jmx` en la interfaz de JMeter, apunta el *CSV Data Set* a `perf/jmeter/search_terms.csv`, define `BASE_URL` como variable si hace falta y ejecútalo.

## 💡 Recomendaciones

- Protege o limita los endpoints antes de lanzar carga pesada en entornos compartidos.
- Para pruebas realistas, usa prefijos de productos comunes y añade tiempos de espera entre peticiones.
- Métricas clave de k6: `http_req_duration` (p95), `http_req_failed` (tasa) y `http_reqs` (rendimiento).

<div align="center"><sub><a href="../README.md">⬅ Volver a Alkosto</a></sub></div>
