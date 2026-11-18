<div align="center">

# ⏱️ Reloj digital para FPGA

**Electrónica Digital · Proyecto final**

![SystemVerilog](https://img.shields.io/badge/SystemVerilog-4B5563?style=flat-square)
![FPGA](https://img.shields.io/badge/FPGA-0071C5?style=flat-square&logo=intel&logoColor=white)
![Modos](https://img.shields.io/badge/modos-4-38bdf8?style=flat-square)
![Testbenches](https://img.shields.io/badge/testbenches-7-34d399?style=flat-square)

</div>

[🏠 Inicio](../../README.md) › [Electrónica Digital](../README.md) › **Reloj digital**

---

## 📌 Descripción funcional

El dispositivo tiene **4 modos de operación** seleccionados con **2 switches**, y muestra la información en los displays de 7 segmentos de la tarjeta.

| Modo | Nombre | Qué hace |
| :-: | :--- | :--- |
| 1️⃣ | 🕐 **Operación normal** | Muestra la hora actual y su avance. |
| 2️⃣ | ✏️ **Cambio de hora** | Permite ajustar horas y minutos con los botones. |
| 3️⃣ | 🔔 **Alarma** | Configura la hora de la alarma (por defecto **08:00**). |
| 4️⃣ | ⏳ **Temporizador** | Cuenta regresiva en formato **MM:SS** con inicio/pausa. |

<details>
<summary><b>📖 Ver la especificación completa de cada modo</b></summary>

### Modo 1 — Operación normal
En los displays se observa el funcionamiento del reloj, permitiendo ver la hora actual y su avance.

### Modo 2 — Cambio de hora
Se observa la hora actual detenida, con los dos dígitos de los minutos parpadeando para indicar que pueden **incrementarse o decrementarse** con dos botones. Un tercer botón alterna entre modificar minutos u horas (cuyos dígitos pasan a parpadear). Un cuarto botón **guarda** la hora configurada como hora del sistema.

### Modo 3 — Alarma
Se observa la hora de la alarma actual (por defecto **08:00** si nunca se ha configurado). La configuración funciona igual que en el Modo 2, pero el cuarto botón guarda la hora como **hora de la alarma** en lugar de hora del sistema.

### Modo 4 — Temporizador
Se observa `00:00` en formato **MM:SS** (minutos hasta 99, segundos hasta 59). La configuración es similar a los modos 2 y 3 (el tercer botón alterna entre MM y SS). El cuarto botón **inicia** la cuenta regresiva; si se presiona de nuevo durante la cuenta, la **pausa** (botón de inicio/pausa). Al llegar a `00:00` la cuenta se detiene, los minutos vuelven a parpadear (modo configuración) y se activa la alarma, que se detiene al presionar cualquiera de los cuatro botones o al cambiar de modo.

</details>

### 🔔 Habilitación de la alarma

Un **tercer switch** habilita la alarma. Si está activo y la hora de la alarma coincide con la hora del sistema, un **LED comienza a parpadear**. La alarma se detiene al presionar cualquiera de los cuatro botones de los modos 2, 3 y 4.

> [!NOTE]
> La hora del sistema **nunca se detiene**: sigue avanzando en cualquier modo, aunque no se muestre en los displays.

## 🗂️ Estructura

```text
ProyectoFinal-digital-clock/
├── project/
│   ├── controlModos.sv              # Módulo tope: selección de modos
│   └── modules/
│       ├── mode1/relojHHMM.sv       # Reloj HH:MM
│       ├── mode2/cambioHora.sv      # Ajuste de hora
│       ├── mode3/controlAlarma.sv   # Alarma
│       ├── mode4/                   # Temporizador MM:SS
│       └── shared/                  # Display 7 segmentos y divisores
└── test/                            # Testbenches de módulos y de cada modo
```

## ▶️ Cómo ejecutarlo

- **Simulación:** compila los archivos de `project/` junto con un testbench de `test/` en tu simulador de SystemVerilog (por ejemplo ModelSim/Questa).
- **FPGA:** crea un proyecto en **Intel Quartus Prime**, agrega `project/`, define `controlModos` como entidad tope y asigna switches, botones, displays y LED según tu tarjeta.

<div align="center"><sub><a href="../README.md">⬅ Volver a Electrónica Digital</a></sub></div>
