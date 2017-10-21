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

package cc.lasmgratel.foodcraftreloaded.item.crafting;

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.VegetableLoader;
import cc.lasmgratel.foodcraftreloaded.common.FruitLoader;
import cc.lasmgratel.foodcraftreloaded.item.food.fruit.FruitType;
import cc.lasmgratel.foodcraftreloaded.item.food.fruit.ItemJuices;
import cc.lasmgratel.foodcraftreloaded.item.food.vegetable.ItemVegetableJuices;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class CakeRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
        boolean cakes = false;
        boolean juices = false;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && (stack.getItem() != Items.CAKE ||
                !(stack.getItem() instanceof ItemJuices) && !(stack.getItem() instanceof ItemVegetableJuices)))
                return false;
            if (stack.getItem() == Items.CAKE)
                if (cakes)
                    return false;
                else
                    cakes = true;
            if (stack.getItem() instanceof ItemJuices || stack.getItem() instanceof ItemVegetableJuices)
                if (juices)
                    return false;
                else
                    juices = true;
        }
        return cakes && juices;
    }

    @Nonnull
    @Override
    @SuppressWarnings("ConstantConditions")
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        ItemStack[] invArray = new ItemStack[inv.getSizeInventory()];
        for (int i = 0; i < inv.getSizeInventory(); i++)
            invArray[i] = inv.getStackInSlot(i);
        AtomicReference<ItemStack> ret = new AtomicReference<>(ItemStack.EMPTY);
        Arrays.stream(invArray).filter(itemStack -> itemStack.getItem() instanceof ItemJuices || itemStack.getItem() instanceof ItemVegetableJuices).findAny().ifPresent(juice -> {
            if (juice.getItem() instanceof ItemJuices)
                ret.set(new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(FruitLoader.class).get().getFruitCakeMap().get(FruitType.values()[juice.getMetadata()])));
            else if (juice.getItem() instanceof ItemVegetableJuices)
                ret.set(new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(VegetableLoader.class).get().getCakes(), 1, juice.getMetadata()));
        });
        return ret.get();
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 2 && height >= 1;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
