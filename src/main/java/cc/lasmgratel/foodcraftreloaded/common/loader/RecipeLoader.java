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

package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeManager;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.FruitType;
import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.ItemFruit;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetable;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.VegetableType;
import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.LoaderState;

@SuppressWarnings("OptionalGetWithoutIsPresent")
public class RecipeLoader {
    private FruitEnumLoader fruitLoader;
    private VegetableEnumLoader vegetableLoader;

    @Load(LoaderState.AVAILABLE)
    public void loadDrinkRecipes() {
        fruitLoader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitEnumLoader.class).get();
        vegetableLoader = FoodCraftReloaded.getProxy().getLoaderManager().getLoader(VegetableEnumLoader.class).get();
        for (FruitType fruitType : FruitType.values()) {
            RecipeManager.getInstance().addRecipe(new DrinkRecipe(new ItemStack[]{new ItemStack(fruitLoader.getInstanceMap(ItemFruit.class).get(fruitType))}, new FluidStack(fruitLoader.getFluidJuiceEnumMap().get(fruitType), 1000)));
        }
        for (VegetableType vegetableType : VegetableType.values()) {
            RecipeManager.getInstance().addRecipe(new DrinkRecipe(new ItemStack[]{new ItemStack(vegetableLoader.getInstanceMap(ItemVegetable.class).get(vegetableType))}, new FluidStack(vegetableLoader.getFluidJuiceEnumMap().get(vegetableType), 1000)));
        }
//        ForgeRegistries.RECIPES.register(new CakeRecipe().setRegistryName(FoodCraftReloaded.MODID, "cake_recipe"));
    }
}
