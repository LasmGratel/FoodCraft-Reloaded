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
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;

public abstract class Recipe {
    protected final NonNullList<ItemStack> inputStacks = NonNullList.create();
    protected final NonNullList<ItemStack> outputStacks = NonNullList.create();
    protected final NonNullList<FluidStack> inputFluids = NonNullList.create();
    protected final NonNullList<FluidStack> outputFluids = NonNullList.create();
    private float xp = 0f;

    public float getXp() {
        return xp;
    }

    public void setXp(float xp) {
        this.xp = xp;
    }
}
