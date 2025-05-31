import type { Coffees } from "../enums/Coffees.enum";
import type { IngredientType } from "./ingredient.type";

export type OrderType = {
  name: Coffees;
  ingredients: IngredientType[];
}