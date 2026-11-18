<div align="center">

<img src="public/characters/character1.png" width="72"/>
<img src="public/characters/character2.png" width="72"/>
<img src="public/characters/character3.png" width="72"/>
<img src="public/characters/character4.png" width="72"/>
<img src="public/characters/character5.png" width="72"/>

# ☕ Love Coffe

**Sistemas Operativos · Mini proyecto**

*Un pequeño juego web de cafetería*

![Astro](https://img.shields.io/badge/Astro%205-BC52EE?style=flat-square&logo=astro&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=flat-square&logo=typescript&logoColor=white)
![GitHub Pages](https://img.shields.io/badge/GitHub%20Pages-222222?style=flat-square&logo=githubpages&logoColor=white)

</div>

[🏠 Inicio](../../README.md) › [Sistemas Operativos](../README.md) › **Love Coffe**

---

## 🎮 Cómo se juega

1. 🧑 Llega un **cliente** con su pedido.
2. 📖 Consulta el **recetario** para saber qué ingredientes lleva.
3. 🥛 Recibe ingredientes y transfórmalos cuando haga falta (café → espresso, leche → espuma…).
4. 🧺 Combínalos en la **bandeja de trabajo** (máximo 4).
5. ⏭️ Entrega y pasa al **siguiente turno**.

### ☕ Recetas disponibles

`Iced Coffee` · `Affogato` · `Espresso Macchiato` · `Café Bombón` · `Moka`

<div align="center">
<img src="public/ingredients/Coffee.png" width="44"/>
<img src="public/ingredients/EspressoCoffe.png" width="44"/>
<img src="public/ingredients/Milk.png" width="44"/>
<img src="public/ingredients/FoamedMilk.png" width="44"/>
<img src="public/ingredients/CondensedMilk.png" width="44"/>
<img src="public/ingredients/Chocolate.png" width="44"/>
<img src="public/ingredients/IceCream.png" width="44"/>
<img src="public/ingredients/Ice.png" width="44"/>
<img src="public/ingredients/Sugar.png" width="44"/>
</div>

## 🗂️ Estructura

```text
love-coffe/
├── public/
│   ├── characters/          # Clientes
│   └── ingredients/         # Sprites de ingredientes
└── src/
    ├── components/          # OrderList · WorkingTray · Reciper · NextTurn
    ├── data/                # Pedidos, recetas, ingredientes y transformaciones
    ├── enums/               # Cafés e ingredientes (nivel 1 y 2)
    ├── service/             # Lógica del juego (singleton LoveCoffe)
    ├── types/
    ├── layouts/
    └── pages/index.astro
```

## ▶️ Cómo ejecutarlo

| Comando | Acción |
| :--- | :--- |
| `npm install` | Instala las dependencias |
| `npm run dev` | Servidor local en `http://localhost:4321` |
| `npm run build` | Genera el sitio en `./dist/` |
| `npm run preview` | Previsualiza la versión compilada |

El proyecto incluye un *workflow* de GitHub Actions ([`.github/workflows/deploy.yml`](.github/workflows/deploy.yml)) para publicarlo en **GitHub Pages**.

<div align="center"><sub><a href="../README.md">⬅ Volver a Sistemas Operativos</a></sub></div>
