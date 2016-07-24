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
import org.infinitystudio.foodcraftreloaded.tileentity.GlassCupTileEntity;

public class GlassCupRecipe implements IFcRecipe {
    private ItemStack input;
    private ItemStack output;

    public GlassCupRecipe(){}

    public GlassCupRecipe(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public ItemStack getInput() {
        return input;
    }

    public void setInput(ItemStack input) {
        this.input = input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public void setOutput(ItemStack output) {
        this.output = output;
    }

    /**
     * Used to check if a recipe matches current crafting inventory
     *
     * @param inv
     */
    @Override
    public boolean matches(ItemStack[] inv) {
        return input.isItemEqual(output);
    }

    /**
     * Get the result of current crafting inventory
     *
     * @param inv
     */
    @Override
    public ItemStack[] getCraftingResult(ItemStack[] inv) {
        if (inv[0].isItemEqual(getInput())) {
            return new ItemStack[]{
                    getOutput()
            };
        } else {
            return null;
        }
    }

    @Override
    public ItemStack[] getRecipeOutput() {
        return new ItemStack[]{
                getOutput()
        };
    }

    /**
     * Get the TileEntity class.
     * Used to tag the recipe type.
     */
    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return GlassCupTileEntity.class;
    }
}
