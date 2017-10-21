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

package cc.lasmgratel.foodcraftreloaded.init;

import cc.lasmgratel.foodcraftreloaded.block.BlockRiceCrop;
import cc.lasmgratel.foodcraftreloaded.block.machine.BlockDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.block.machine.BlockPressureCooker;
import cc.lasmgratel.foodcraftreloaded.block.machine.BlockSmeltingDrinkMachine;
import cc.lasmgratel.foodcraftreloaded.util.loader.annotation.RegBlock;

public interface FCRBlocks {
    @RegBlock({"rice", "plant"})
    BlockRiceCrop RICE_PLANT = new BlockRiceCrop();

    @RegBlock({"drink", "machine"})
    BlockDrinkMachine DRINK_MACHINE = new BlockDrinkMachine();

    @RegBlock({"pressure", "cooker"})
    BlockPressureCooker PRESSURE_COOKER = new BlockPressureCooker();

    @RegBlock({"smelting", "drink", "machine"})
    BlockSmeltingDrinkMachine SMELTING_DRINK_MACHINE = new BlockSmeltingDrinkMachine();
}
