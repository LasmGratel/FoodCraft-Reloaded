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

package cc.lasmgratel.foodcraftreloaded.common.item;

import cc.lasmgratel.foodcraftreloaded.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.fluid.FluidJuice;
import cc.lasmgratel.foodcraftreloaded.common.fluid.FluidVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.ItemFruitJuice;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.common.loader.FruitEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.loader.VegetableEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.FruitTyped;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.VegetableTyped;
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
        return fluid.getFluid() instanceof FluidVegetableJuice || fluid.getFluid() instanceof FluidJuice;
    }

    @Nullable
    @Override
    public FluidStack getFluid() {
        Item item = container.getItem();
        if (item instanceof FruitTyped)
            return new FluidStack(FoodCraftReloaded.getLoader(FruitEnumLoader.class).get().getFluidJuiceEnumMap().get(((FruitTyped) item).getType()), Fluid.BUCKET_VOLUME);
        else if (item instanceof VegetableTyped)
            return new FluidStack(FoodCraftReloaded.getLoader(VegetableEnumLoader.class).get().getFluidJuiceEnumMap().get(((VegetableTyped) item).getType()), Fluid.BUCKET_VOLUME);
        else
            return null;
    }

    @Override
    protected void setFluid(@Nullable FluidStack fluidStack) {
        if (fluidStack == null)
            container = new ItemStack(FCRItems.GLASS_BOTTLE);
        else if (fluidStack.getFluid() instanceof FluidJuice)
            container = new ItemStack(FoodCraftReloaded.getLoader(FruitEnumLoader.class).get()
                .getInstanceMap(ItemFruitJuice.class).get(((FluidJuice) fluidStack.getFluid()).getType()));
        else if (fluidStack.getFluid() instanceof FluidVegetableJuice)
            container = new ItemStack(FoodCraftReloaded.getLoader(VegetableEnumLoader.class).get()
                .getInstanceMap(ItemVegetableJuice.class).get(((FluidVegetableJuice) fluidStack.getFluid()).getType()));
    }
}
