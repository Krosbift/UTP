import type { IngredientsTierOne } from "../enums/ingredients-tier-one.enum";
import type { IngredientsTierTwo } from "../enums/ingredients-tier-two.enum";

export type IngredientType = {
  ingredient: (IngredientsTierOne | IngredientsTierTwo),
  tier: 1 | 2,
}