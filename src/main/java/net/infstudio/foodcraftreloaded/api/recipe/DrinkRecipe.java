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

package net.infstudio.foodcraftreloaded.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class DrinkRecipe extends Recipe {
    public DrinkRecipe(FluidStack output, int xp, @Nonnull ItemStack rubbish) {
        outputFluids.add(output);
        setXp(xp);
        outputStacks.add(rubbish);
    }

    public DrinkRecipe(FluidStack output) {
        outputFluids.add(output);
        outputStacks.add(ItemStack.EMPTY);
    }

    public FluidStack getOutput() {
        return outputFluids.get(0);
    }

    public void setOutput(FluidStack output) {
        outputFluids.set(0, output);
    }

    @Nonnull
    public ItemStack getRubbish() {
        return outputStacks.get(0);
    }

    public void setRubbish(@Nonnull ItemStack rubbish) {
        outputStacks.set(0, rubbish);
    }
}
