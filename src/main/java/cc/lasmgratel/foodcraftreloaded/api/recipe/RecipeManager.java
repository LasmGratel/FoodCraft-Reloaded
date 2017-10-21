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

package cc.lasmgratel.foodcraftreloaded.api.recipe;

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
}
