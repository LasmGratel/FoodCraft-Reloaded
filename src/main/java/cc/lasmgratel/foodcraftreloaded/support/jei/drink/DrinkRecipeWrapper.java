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

package cc.lasmgratel.foodcraftreloaded.support.jei.drink;

import cc.lasmgratel.foodcraftreloaded.api.recipe.AbstractRecipe;
import cc.lasmgratel.foodcraftreloaded.support.jei.FCRRecipeWrapper;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class DrinkRecipeWrapper extends FCRRecipeWrapper {
    public DrinkRecipeWrapper(AbstractRecipe recipe) {
        super(recipe);
    }

    @Override
    public void getIngredients(@Nonnull IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, getRecipe().getInput().first());
        ingredients.setOutput(FluidStack.class, getRecipe().getOutput().first());
        ingredients.setOutput(ItemStack.class, getRecipe().getOutput().second());
    }
}
