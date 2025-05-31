import { Orders } from "../data/orders.data";
import { primaryIngredients } from "../data/primary-ingredients.data";
import { recipes } from "../data/recipes.data";
import { secundaryIngredients } from "../data/secundary-ingredients.data";
import { transformationIngredients } from "../data/transformation.data";
import type { GetIngredientType } from "../types/get.ingredient.type";
import type { RecipeIngredient } from "../types/recipe-ingredients.type";
import type { WorkingTrayType } from "../types/working-tray.type";

export class LoveCoffe {
  private static instance: LoveCoffe;

  private constructor() { }

  public static getInstance(): LoveCoffe {
    if (!LoveCoffe.instance) {
      LoveCoffe.instance = new LoveCoffe();
    }
    return LoveCoffe.instance;
  }

  private currentGame!: RecipeIngredient;
  private currentIngredient!: GetIngredientType;
  private currentIngredients: WorkingTrayType[] = [];

  public initGameOrderList(): RecipeIngredient {
    const [order, random] = this.selectOrder();
    const orderComplete = recipes[random as number];
    const ingredients = orderComplete.ingredients;

    const ingredientUrls = ingredients.map(ingredient => {
      const ingredientName = ingredient.tier === 1
        ? primaryIngredients[ingredient.ingredient]
        : secundaryIngredients[ingredient.ingredient];
      return {
        url: `ingredients/${ingredientName}.png`,
        ingredient: ingredientName,
      };
    });

    const characterUrl = `characters/character${random as number + 1}.png`

    this.currentGame = { order, ingredientUrls, characterUrl }
    return this.currentGame;
  }

  public getIngredient(): GetIngredientType {
    const ramdonIngredient = Math.floor(Math.random() * 9);
    const firstIngredient = primaryIngredients[ramdonIngredient];
    const firstIngredientUrl = `ingredients/${firstIngredient}.png`
    this.currentIngredient = { firstIngredient, firstIngredientUrl, used: false, modify: false };
    return this.currentIngredient;
  }

  public getIngrediens(): WorkingTrayType[] {
    return this.currentIngredients
  }

  public setIngredient(): WorkingTrayType[] {
    if (this.currentIngredients.length < 4) {
      return this.addIngredients();
    }
    return this.replaceIngredients();
  }

  public addIngredients(): WorkingTrayType[] {
    const ingredient: WorkingTrayType = {
      num: this.currentIngredients.length,
      tier: 1,
      used: false,
      modified: false,
      ingredient: this.currentIngredient.firstIngredient,
      ingredientUrl: this.currentIngredient.firstIngredientUrl,
    }
    this.currentIngredients.push(ingredient);
    return this.currentIngredients;
  }

  public replaceIngredients(): WorkingTrayType[] {
    const exists = this.currentIngredients.findIndex(
      (item) => item.ingredient === this.currentIngredient.firstIngredient
    );
    if (exists !== -1) {
      return this.currentIngredients;
    }

    const items = this.currentIngredients.map((value: WorkingTrayType, index: number): { index: number, priority: 0 | 1 | 2 } => {
      const priority =
        value.used && value.modified ? 0 :
          value.used || value.modified ? 1 : 2;
      return { index, priority };
    })

    const selected = items.reduce((acc, value) => {
      return (value.priority > acc.priority ||
        (value.priority === acc.priority && value.index < acc.index))
        ? value
        : acc;
    });

    const replacementIndex = selected.index;
    this.currentIngredients[replacementIndex] = {
      num: replacementIndex,
      tier: 1,
      used: false,
      modified: false,
      ingredient: this.currentIngredient.firstIngredient,
      ingredientUrl: this.currentIngredient.firstIngredientUrl,
    };

    return this.currentIngredients;
  }

  public modifyIngredients(ingredient: string, position: number): WorkingTrayType {
    const ingredientIndex = primaryIngredients.indexOf(ingredient);
    const currentIngredient = this.currentIngredients[position]
    if (currentIngredient.tier === 1) {
      const { primaryIngredient, secundaryIngredinet } = transformationIngredients[ingredientIndex]
      if (secundaryIngredinet === "") {
        alert("Ingrediente no modificable");
        return currentIngredient
      }
      const ingredientModified: WorkingTrayType = {
        num: currentIngredient.num,
        tier: 2,
        used: currentIngredient.used,
        modified: true,
        ingredient: secundaryIngredinet,
        ingredientUrl: `ingredients/${secundaryIngredinet}.png`,
      }
      this.currentIngredients[position] = ingredientModified;
      return ingredientModified;
    }
    alert("Elemento ya modificado");
    return currentIngredient;
  }

  public validRecipe(): boolean {
    const [one, two, three] = this.currentGame.ingredientUrls;
    const firstIngredient = one.ingredient;
    const secondIngredient = two.ingredient;
    const thirdIngredient = three.ingredient;

    const ingredientsSet = new Set(this.currentIngredients.map(item => item.ingredient));

    const isValid = ingredientsSet.has(firstIngredient) &&
      ingredientsSet.has(secondIngredient) &&
      ingredientsSet.has(thirdIngredient);

    if (isValid) {
      this.currentIngredients = this.currentIngredients.map(item => {
        if (item.ingredient === firstIngredient ||
            item.ingredient === secondIngredient ||
            item.ingredient === thirdIngredient) {
          return { ...item, used: true };
        }
        return item;
      });
    }

    return isValid;
  }

  public selectOrder(): (string | number)[] {
    const ramdonOrder = Math.floor(Math.random() * 5);
    return [Orders[ramdonOrder], ramdonOrder];
  }
}
