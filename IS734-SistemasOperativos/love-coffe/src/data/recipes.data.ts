import { Coffees } from "../enums/coffees.enum";
import { IngredientsTierOne } from "../enums/ingredients-tier-one.enum";
import { IngredientsTierTwo } from "../enums/ingredients-tier-two.enum";
import type { OrderType } from "../types/order.type";

export const recipes: OrderType[] = [
  {
    name: Coffees.IcedCoffee,
    ingredients: [
      {
        ingredient: IngredientsTierOne.Milk,
        tier: 1,
      },
      {
        ingredient: IngredientsTierOne.Ice,
        tier: 1,
      },
      {
        ingredient: IngredientsTierOne.InstantCoffee,
        tier: 1,
      },
    ],
  },
  {
    name: Coffees.AffogatoCoffee,
    ingredients: [
      {
        ingredient: IngredientsTierTwo.EspressoCoffe,
        tier: 2,
      },
      {
        ingredient: IngredientsTierTwo.VanillaIceCream,
        tier: 2,
      },
      {
        ingredient: IngredientsTierTwo.ChocolatePowder,
        tier: 2,
      },
    ],
  },
  {
    name: Coffees.EspressoMacchiato,
    ingredients: [
      {
        ingredient: IngredientsTierTwo.EspressoCoffe,
        tier: 2,
      },
      {
        ingredient: IngredientsTierTwo.FoamedMilk,
        tier: 2,
      },
      {
        ingredient: IngredientsTierOne.Sugar,
        tier: 1,
      },
    ],
  },
  {
    name: Coffees.BombonCoffee,
    ingredients: [
      {
        ingredient: IngredientsTierTwo.EspressoCoffe,
        tier: 2,
      },
      {
        ingredient: IngredientsTierOne.CondensedMilk,
        tier: 1,
      },
      {
        ingredient: IngredientsTierTwo.CinamorollPowder,
        tier: 2,
      },
    ],
  },
  {
    name: Coffees.MokaCoffe,
    ingredients: [
      {
        ingredient: IngredientsTierTwo.EspressoCoffe,
        tier: 2,
      },
      {
        ingredient: IngredientsTierOne.Milk,
        tier: 1,
      },
      {
        ingredient: IngredientsTierTwo.ChocolatePowder,
        tier: 2,
      },
    ],
  }
]
