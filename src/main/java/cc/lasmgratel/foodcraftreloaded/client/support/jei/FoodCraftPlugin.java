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

package cc.lasmgratel.foodcraftreloaded.client.support.jei;

import cc.lasmgratel.foodcraftreloaded.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeManager;
import cc.lasmgratel.foodcraftreloaded.client.gui.GuiContainerDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.client.gui.GuiContainerSmeltingDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.client.support.jei.drink.DrinkRecipeCategory;
import cc.lasmgratel.foodcraftreloaded.client.support.jei.drink.DrinkRecipeWrapper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import java.util.stream.Collectors;

@JEIPlugin
public class FoodCraftPlugin implements IModPlugin {
    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(RecipeManager.getInstance().getRecipes(DrinkRecipe.class).stream().map(DrinkRecipeWrapper::new).collect(Collectors.toList()), RecipeUIDs.DRINK);
        registry.handleRecipes(DrinkRecipeWrapper.class, recipe -> recipe, RecipeUIDs.DRINK);
        registry.addRecipeClickArea(GuiContainerDrinkMachine.class, 87, 27, 27, 25, RecipeUIDs.DRINK);
        registry.addRecipeClickArea(GuiContainerSmeltingDrinkMachine.class, 87, 27, 27, 25, RecipeUIDs.DRINK);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new DrinkRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
    }
}
