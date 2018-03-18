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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.item;

import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.fluid.FluidFruitJuice;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.fluid.FluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.ItemFruitJuice;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.vegetable.ItemVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.loader.FruitEnumLoader;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.loader.VegetableEnumLoader;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration.FruitTyped;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration.VegetableTyped;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidGlassBottleWrapper extends FluidBucketWrapper {
    public FluidGlassBottleWrapper(@Nonnull ItemStack container) {
        super(container);
    }

    @Override
    public boolean canFillFluidType(FluidStack fluid) {
        return fluid.getFluid() instanceof FluidVegetableJuice || fluid.getFluid() instanceof FluidFruitJuice;
    }

    @Nullable
    @Override
    public FluidStack getFluid() {
        Item item = container.getItem();
        if (item instanceof FruitTyped)
            return new FluidStack(FoodCraftReloadedMod.getLoader(FruitEnumLoader.class).get().getInstance(FluidFruitJuice.class, ((FruitTyped) item).getType()), Fluid.BUCKET_VOLUME);
        else if (item instanceof VegetableTyped)
            return new FluidStack(FoodCraftReloadedMod.getLoader(VegetableEnumLoader.class).get().getInstance(FluidVegetableJuice.class, ((VegetableTyped) item).getType()), Fluid.BUCKET_VOLUME);
        else
            return null;
    }

    @Override
    protected void setFluid(@Nullable FluidStack fluidStack) {
        if (fluidStack == null)
            container = new ItemStack(FCRItems.GLASS_BOTTLE);
        else if (fluidStack.getFluid() instanceof FluidFruitJuice)
            container = new ItemStack(FoodCraftReloadedMod.getLoader(FruitEnumLoader.class).get()
                .getInstanceMap(ItemFruitJuice.class).get(((FluidFruitJuice) fluidStack.getFluid()).getType()));
        else if (fluidStack.getFluid() instanceof FluidVegetableJuice)
            container = new ItemStack(FoodCraftReloadedMod.getLoader(VegetableEnumLoader.class).get()
                .getInstanceMap(ItemVegetableJuice.class).get(((FluidVegetableJuice) fluidStack.getFluid()).getType()));
    }
}
