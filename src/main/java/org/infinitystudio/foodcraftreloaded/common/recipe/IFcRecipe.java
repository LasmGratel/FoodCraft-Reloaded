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

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface IFcRecipe {

    /**
     * Used to check if a recipe matches current crafting inventory
     *
     * @param inv
     */
    boolean matches(ItemStack[] inv);

    /**
     * Get the result of current crafting inventory
     *
     * @param inv
     */
    ItemStack[] getCraftingResult(ItemStack[] inv);

    /**
     * Get the output of this recipe
     */
    ItemStack[] getRecipeOutput();

    /**
     * Get the TileEntity class.
     * Used to tag the recipe type.
     */
    Class<? extends TileEntity> getTileEntityClass();
}
