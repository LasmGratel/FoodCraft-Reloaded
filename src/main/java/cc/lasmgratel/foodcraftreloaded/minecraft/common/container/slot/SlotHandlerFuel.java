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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.container.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

import static net.minecraft.inventory.SlotFurnaceFuel.isBucket;

public class SlotHandlerFuel extends SlotItemHandler implements ExtendedSlot {
    public SlotHandlerFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    public boolean isItemValid(@Nonnull ItemStack stack) {
        return TileEntityFurnace.isItemFuel(stack) || isBucket(stack);
    }

    public int getItemStackLimit(@Nonnull ItemStack stack) {
        return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
    }

    @Override
    public boolean isOutputSlot() {
        return false;
    }
}
