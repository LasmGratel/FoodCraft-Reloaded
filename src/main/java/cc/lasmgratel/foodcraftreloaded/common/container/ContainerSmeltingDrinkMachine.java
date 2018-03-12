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

package cc.lasmgratel.foodcraftreloaded.common.container;

import cc.lasmgratel.foodcraftreloaded.common.container.slot.SlotDrinkMachineOutput;
import cc.lasmgratel.foodcraftreloaded.common.container.slot.SlotHandlerFuel;
import cc.lasmgratel.foodcraftreloaded.common.util.AutomatedGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerSmeltingDrinkMachine extends Container implements AutomatedGui {
    private InventoryPlayer inventoryPlayer;
    private IItemHandlerModifiable itemHandler;

    public ContainerSmeltingDrinkMachine(InventoryPlayer playerInventory, TileEntity tileEntity) {
        this.inventoryPlayer = playerInventory;
        this.itemHandler = (IItemHandlerModifiable) tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 68, 17));
        addSlotToContainer(new SlotHandlerFuel(itemHandler, 2, 68, 53));
        addSlotToContainer(new SlotDrinkMachineOutput(playerInventory.player, itemHandler, 1, 124 + 4, 31 + 4));
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

        for (int k = 0; k < 9; ++k)
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return super.transferStackInSlot(playerIn, index);
    }
}
