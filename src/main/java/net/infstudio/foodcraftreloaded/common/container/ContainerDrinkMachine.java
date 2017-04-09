package net.infstudio.foodcraftreloaded.common.container;

import net.infstudio.foodcraftreloaded.common.container.slot.SlotDrinkMachineOutput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerDrinkMachine extends Container {
    private InventoryPlayer inventoryPlayer;
    private IItemHandlerModifiable itemHandler;

    public ContainerDrinkMachine(InventoryPlayer playerInventory, IItemHandlerModifiable itemHandler) {
        this.inventoryPlayer = playerInventory;
        this.itemHandler = itemHandler;
        addSlotToContainer(new SlotItemHandler(itemHandler, 0, 68, 34));
        addSlotToContainer(new SlotDrinkMachineOutput(playerInventory.player, itemHandler, 1, 130, 38));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }
}
