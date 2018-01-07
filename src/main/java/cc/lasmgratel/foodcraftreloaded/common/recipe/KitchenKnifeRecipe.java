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

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.tool.ItemKitchenKnife;
import cc.lasmgratel.foodcraftreloaded.common.item.tool.KitchenKnifeType;
import cc.lasmgratel.foodcraftreloaded.common.loader.KitchenKnifeLoader;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.Arrays;

public class KitchenKnifeRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World worldIn) {
        return Arrays.stream(KitchenKnifeType.values())
            .filter(toolMaterial -> inv.getStackInRowAndColumn(0, 0).isItemEqualIgnoreDurability(toolMaterial.getRepairItemStack()))
            .findFirst()
            .filter(toolMaterial -> {
                ItemStack[] stacks = new ItemStack[]{inv.getStackInRowAndColumn(0, 0), inv.getStackInRowAndColumn(0, 1), inv.getStackInRowAndColumn(1, 0), inv.getStackInRowAndColumn(1, 1)};
                return Arrays.stream(stacks).allMatch(stack -> stack.isItemEqualIgnoreDurability(toolMaterial.getRepairItemStack())) && OreDictionary.getOres("stickWood").stream().anyMatch(stack -> stack.isItemEqualIgnoreDurability(inv.getStackInRowAndColumn(2, 0)));
            })
            .map(toolMaterial -> FoodCraftReloaded.getProxy().getLoaderManager().getLoader(KitchenKnifeLoader.class).get().getInstanceMap(ItemKitchenKnife.class).get(toolMaterial))
            .map(ItemStack::new)
            .isPresent();
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting inv) {
        return Arrays.stream(KitchenKnifeType.values())
            .filter(toolMaterial -> inv.getStackInRowAndColumn(0, 0).isItemEqualIgnoreDurability(toolMaterial.getRepairItemStack()))
            .findFirst()
            .filter(toolMaterial -> {
                ItemStack[] stacks = new ItemStack[]{inv.getStackInRowAndColumn(0, 0), inv.getStackInRowAndColumn(0, 1), inv.getStackInRowAndColumn(1, 0), inv.getStackInRowAndColumn(1, 1)};
                return Arrays.stream(stacks).allMatch(stack -> stack.isItemEqualIgnoreDurability(toolMaterial.getRepairItemStack())) && OreDictionary.getOres("stickWood").stream().anyMatch(stack -> stack.isItemEqualIgnoreDurability(inv.getStackInRowAndColumn(2, 0)));
            })
            .map(toolMaterial -> FoodCraftReloaded.getProxy().getLoaderManager().getLoader(KitchenKnifeLoader.class).get().getInstanceMap(ItemKitchenKnife.class).get(toolMaterial))
            .map(ItemStack::new)
            .orElse(ItemStack.EMPTY);
    }

    @Override
    public boolean canFit(int width, int height) {
        return width >= 2 && height >= 3;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}
