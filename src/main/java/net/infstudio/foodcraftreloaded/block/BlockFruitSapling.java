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

package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.item.food.FruitType;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.BlockSapling;

public class BlockFruitSapling extends BlockSapling {
    private FruitType fruitType;

    public BlockFruitSapling(FruitType fruitType) {
        this.fruitType = fruitType;
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling"));
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(fruitType.toString(), "sapling"));
    }

    public FruitType getFruitType() {
        return fruitType;
    }
}
