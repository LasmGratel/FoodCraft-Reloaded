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

package cc.lasmgratel.foodcraftreloaded.minecraft.api.init;

import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nonnull;

public interface FCRCreativeTabs {
    CreativeTabs BASE = new CreativeTabs(CreativeTabs.getNextID(), FoodCraftReloadedMod.MODID + ".base") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.GOLDEN_APPLE);
        }
    };

    CreativeTabs INGREDIENTS = new CreativeTabs(CreativeTabs.getNextID(), FoodCraftReloadedMod.MODID + ".ingredient") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.POTATO);
        }
    };

    CreativeTabs DRINK = new CreativeTabs(CreativeTabs.getNextID(), FoodCraftReloadedMod.MODID + ".drink") {
        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(FoodCraftReloadedMod.MODID, "lemon_juice")));
        }
    };

    CreativeTabs SNACK = new CreativeTabs(CreativeTabs.getNextID(), FoodCraftReloadedMod.MODID + ".snack") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(FoodCraftReloadedMod.MODID, "banana_cake")));
        }
    };

    CreativeTabs MEAL = new CreativeTabs(CreativeTabs.getNextID(), FoodCraftReloadedMod.MODID + ".meal") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(FCRFoods.WHITE_RICE);
        }
    };

    CreativeTabs EXTRA = new CreativeTabs(CreativeTabs.getNextID(), FoodCraftReloadedMod.MODID + ".extra") {
        @Override
        public ItemStack createIcon() {
            return ItemStack.EMPTY;
        }
    };
}
