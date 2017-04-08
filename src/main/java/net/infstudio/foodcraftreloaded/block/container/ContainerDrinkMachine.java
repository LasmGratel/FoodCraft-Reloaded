package net.infstudio.foodcraftreloaded.block.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nonnull;

public class ContainerDrinkMachine extends Container {
    private InventoryPlayer inventoryPlayer;
    private IItemHandlerModifiable itemHandler;

    public ContainerDrinkMachine(InventoryPlayer inventoryPlayer, IItemHandlerModifiable itemHandler) {
        this.inventoryPlayer = inventoryPlayer;
        this.itemHandler = itemHandler;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }
}
