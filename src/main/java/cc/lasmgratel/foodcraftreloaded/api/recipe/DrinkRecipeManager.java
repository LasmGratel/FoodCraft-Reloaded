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

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class DrinkRecipeManager {
    private Map<ItemStack, DrinkRecipe> recipeMap = new HashMap<>();

    private static final DrinkRecipeManager INSTANCE = new DrinkRecipeManager();

    public static DrinkRecipeManager getInstance() {
        return INSTANCE;
    }

    public Optional<DrinkRecipe> getRecipe(@Nonnull ItemStack stack) {
        return Optional.ofNullable(recipeMap.get(stack));
    }

    public List<DrinkRecipe> getRecipes(String oreDict) {
        return OreDictionary.getOres(oreDict).stream().map(recipeMap::get).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Nullable
    public DrinkRecipe getRecipeNullable(@Nonnull ItemStack stack) {
        return recipeMap.get(stack);
    }

    public boolean hasRecipe(@Nonnull ItemStack stack) {
        return recipeMap.containsKey(stack);
    }

    public boolean hasRecipes(String oreDict) {
        return OreDictionary.getOres(oreDict).stream().map(recipeMap::get).anyMatch(Objects::nonNull);
    }

    public void addRecipe(@Nonnull ItemStack input, FluidStack output) {
        recipeMap.put(input, new DrinkRecipe(output));
    }

    public void addRecipes(String oreDictInput, FluidStack output) {
        OreDictionary.getOres(oreDictInput).forEach(stack -> recipeMap.put(stack, new DrinkRecipe(output)));
    }

    public void removeRecipe(@Nonnull ItemStack input) {
        recipeMap.remove(input);
    }

    public void removeRecipes(String oreDictInput) {
        OreDictionary.getOres(oreDictInput).forEach(recipeMap::remove);
    }

    public float getXp(@Nonnull ItemStack input) {
        return Optional.ofNullable(recipeMap.get(input)).map(DrinkRecipe::getXp).orElse(0f);
    }
}
