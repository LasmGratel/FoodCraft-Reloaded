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

package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.block.BlockFluidJuice;
import cc.lasmgratel.foodcraftreloaded.block.BlockFruitCake;
import cc.lasmgratel.foodcraftreloaded.block.BlockFruitLeaves;
import cc.lasmgratel.foodcraftreloaded.block.BlockFruitSapling;
import cc.lasmgratel.foodcraftreloaded.fluid.FluidJuice;
import cc.lasmgratel.foodcraftreloaded.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.item.food.fruit.FruitType;
import cc.lasmgratel.foodcraftreloaded.item.food.fruit.ItemFruitCake;
import cc.lasmgratel.foodcraftreloaded.item.food.fruit.ItemFruitLiqueur;
import cc.lasmgratel.foodcraftreloaded.item.food.fruit.ItemFruitYogurt;
import cc.lasmgratel.foodcraftreloaded.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.util.loader.annotation.Load;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;

public class FruitEnumLoader extends EnumLoader<FruitType> {
    @Load
    public void loadFruits() {
        Class[] values = new Class[] {
            BlockFruitLeaves.class, BlockFruitSapling.class, FluidJuice.class, BlockFluidJuice.class,
            BlockFruitCake.class, ItemFruitCake.class, ItemFruitLiqueur.class, ItemFruitYogurt.class
        };
        Arrays.stream(values).forEach(this::putValue);
        this.<BlockFruitSapling>getValue().forEach(sapling -> ForgeRegistries.ITEMS.register(new ItemBlock(sapling).setUnlocalizedName(NameBuilder.buildUnlocalizedName(sapling.getFruitType().toString(), "sapling")).setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(sapling.getFruitType().toString(), "sapling")).setCreativeTab(FCRCreativeTabs.BASE)));
    }
}
