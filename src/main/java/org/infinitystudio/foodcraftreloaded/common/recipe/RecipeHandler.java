/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
package org.infinitystudio.foodcraftreloaded.common.recipe;

import com.google.common.collect.Maps;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.HashMap;

public class RecipeHandler {
    protected HashMap<ItemStack[], IFcRecipe> recipes = Maps.newHashMap();

    /**
     * Get a recipe.
     * @param tileEntityClass
     * @param inv
     */
    public IFcRecipe getRecipe(Class<? extends TileEntity> tileEntityClass, ItemStack[] inv) {
        if(recipes.get(inv).getTileEntityClass().equals(tileEntityClass))
            return recipes.get(inv);
        return null;
    }

    /**
     * Get the output of a recipe.
     * @param tileEntityClass
     * @param inv
     */
    public ItemStack[] getRecipeOutput(Class<? extends TileEntity> tileEntityClass, ItemStack[] inv) {
        if(recipes.get(inv).getTileEntityClass().equals(tileEntityClass))
            return recipes.get(inv).getRecipeOutput();
        return null;
    }

    /**
     * Add a recipe.
     * @param recipe
     */
    public void addRecipe(IFcRecipe recipe) {
        recipes.put(recipe.getRecipeOutput(), recipe);
    }

}
