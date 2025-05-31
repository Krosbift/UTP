export type RecipeIngredient = {
  order: string | number;
  ingredientUrls: {
    url: string;
    ingredient: string;
  }[];
  characterUrl: string;
};