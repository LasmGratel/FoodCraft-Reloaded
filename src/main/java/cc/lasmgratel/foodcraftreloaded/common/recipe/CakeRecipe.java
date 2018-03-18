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

package cc.lasmgratel.foodcraftreloaded.common.recipe;

import cc.lasmgratel.foodcraftreloaded.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.ItemFruitCake;
import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.ItemFruitJuice;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetableCake;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetableJuice;
import cc.lasmgratel.foodcraftreloaded.common.loader.FruitEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.loader.VegetableEnumLoader;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

public class CakeRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
        boolean flagCake = false, flagJuice = false;
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i).getItem() == Item.getItemFromBlock(Blocks.CAKE))
                flagCake = !flagCake;
            else if (inv.getStackInSlot(i).getItem() instanceof ItemFruitJuice || inv.getStackInSlot(i).getItem() instanceof ItemVegetableJuice)
                flagJuice = !flagJuice;
        }
        return flagCake && flagJuice;
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (inv.getStackInSlot(i).getItem() instanceof ItemFruitJuice) {
                ItemStack ret = new ItemStack(FoodCraftReloaded.getLoader(FruitEnumLoader.class).get().getInstanceMap(ItemFruitCake.class).get(((ItemFruitJuice) inv.getStackInSlot(i).getItem()).getType()));
                inv.setInventorySlotContents(i, new ItemStack(FCRItems.GLASS_BOTTLE));
                return ret;
            } else if (inv.getStackInSlot(i).getItem() instanceof ItemVegetableJuice) {
                ItemStack ret = new ItemStack(FoodCraftReloaded.getLoader(VegetableEnumLoader.class).get().getInstanceMap(ItemVegetableCake.class).get(((ItemVegetableJuice) inv.getStackInSlot(i).getItem()).getType()));
                inv.setInventorySlotContents(i, new ItemStack(FCRItems.GLASS_BOTTLE));
                return ret;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public boolean isDynamic()
    {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
