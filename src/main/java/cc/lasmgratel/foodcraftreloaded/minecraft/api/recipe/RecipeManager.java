/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import javax.annotation.Nullable;
import java.util.*;

@SuppressWarnings("all")
public class RecipeManager {
    private Map<Class<? extends Recipe<?>>, Set<? extends Recipe<?>>> recipeMap = new HashMap<>();

    private static final RecipeManager INSTANCE = new RecipeManager();

    public static RecipeManager getInstance() {
        return INSTANCE;
    }

    public <T extends Recipe<?>> void addRecipe(T recipe) {
        Set<T> set;
        if (recipeMap.containsKey(recipe.getClass())) {
            set = (Set<T>) recipeMap.get(recipe.getClass());
        } else {
            set = new HashSet<>();
        }
        set.add(recipe);
        recipeMap.put((Class<? extends Recipe<?>>) recipe.getClass(), set);
    }

    public <T extends Recipe> Set<T> getRecipes(Class<T> recipeClass) {
        return (Set<T>) recipeMap.get(recipeClass);
    }

    public <T, R extends Recipe<T>> Optional<R> getRecipe(Class<R> recipeClass, T input) {
        return (Optional<R>) recipeMap.get(recipeClass).stream().filter(recipe -> recipe.getInput().equals(input)).findAny();
    }

    @Nullable
    public <T, R extends Recipe<T>> R getRecipeNullable(Class<R> recipeClass, T input) {
        return getRecipe(recipeClass, input).orElse(null);
    }

    @Nullable
    public <T, R extends Recipe<T>> R getIngredientRecipeNullable(Class<R> recipeClass, T input) {
        if (!(input instanceof RecipeInput) && !((((RecipeInput) input).value instanceof ItemStack[]) || ((RecipeInput) input).value instanceof Item[]))
            return null;
        return getRecipes(recipeClass).stream()
            .filter(recipe -> recipe.getInput() instanceof RecipeInput)
            .filter(recipe -> ((RecipeInput) recipe.getInput()).getValue() instanceof Ingredient[])
            .filter(recipe -> ((RecipeInput) recipe.getInput()).getValue().length == ((RecipeInput) input).getValue().length)
            .filter(recipe -> {
                Ingredient[] ingredients = (Ingredient[]) ((RecipeInput) recipe.getInput()).getValue();
                Object[] inputs = ((RecipeInput) input).getValue();
                for (int i = 0; i < inputs.length; i++) {
                    ItemStack stack;
                    if (inputs[i] instanceof Item)
                        stack = new ItemStack((Item) inputs[i]);
                    else
                        stack = (ItemStack) inputs[i];
                    if (!ingredients[i].apply(stack))
                        return false;
                }
                return true;
            })
            .findAny().orElse(null);
    }
}
