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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.container;

import cc.lasmgratel.foodcraftreloaded.minecraft.common.container.slot.SlotDrinkMachineOutput;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.container.slot.SlotHandlerFuel;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.AutomatedGui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerSmeltingDrinkMachine extends ContainerFCR implements AutomatedGui {
    private InventoryPlayer inventoryPlayer;
    private IItemHandlerModifiable itemHandler;

    public ContainerSmeltingDrinkMachine(InventoryPlayer playerInventory, TileEntity tileEntity) {
        super(playerInventory);
        this.inventoryPlayer = playerInventory;
        this.itemHandler = (IItemHandlerModifiable) tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 68, 17));
        addSlotToContainer(new SlotHandlerFuel(itemHandler, 2, 68, 53));
        addSlotToContainer(new SlotDrinkMachineOutput(playerInventory.player, itemHandler, 1, 124 + 4, 31 + 4));
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

    @Override
    public int getPlayerSlotStart() {
        return 3;
    }

    @Override
    public int getPlayerInvX() {
        return 8;
    }

    @Override
    public int getPlayerInvY() {
        return 84;
    }

}
