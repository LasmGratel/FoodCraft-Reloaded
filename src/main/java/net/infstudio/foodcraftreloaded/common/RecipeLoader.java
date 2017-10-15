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

package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.api.recipe.DrinkRecipeManager;
import net.infstudio.foodcraftreloaded.item.crafting.CakeRecipe;
import net.infstudio.foodcraftreloaded.item.food.FruitType;
import net.infstudio.foodcraftreloaded.util.loader.annotation.Load;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class RecipeLoader {
    private FruitLoader loader;

    @Load(LoaderState.AVAILABLE)
    public void loadDrinkRecipes() {
        loader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get();
        for (FruitType fruitType : FruitType.values())
            DrinkRecipeManager.getInstance().addRecipes("fruit" + StringUtils.capitalize(fruitType.toString()), new FluidStack(loader.getJuiceMap().get(fruitType), 500));
        ForgeRegistries.RECIPES.register(new CakeRecipe().setRegistryName(FoodCraftReloaded.MODID, "cake_recipe"));
    }
}
