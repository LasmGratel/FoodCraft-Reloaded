package cc.lasmgratel.foodcraftreloaded.minecraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public abstract class ContainerFCR extends Container {
    public ContainerFCR(InventoryPlayer playerInventory) {
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < 9; ++j)
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, getPlayerInvX() + j * 18, getPlayerInvY() + i * 18));

        for (int k = 0; k < 9; ++k)
            this.addSlotToContainer(new Slot(playerInventory, k, getPlayerInvX() + k * 18, 58 + getPlayerInvY()));
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer playerIn) {
        return true;
    }

    /**
     * Handles Shift+Click, as you see that player inventory were also added as slot,
     * so you may detect whether a slot is player slot or normal slot.
     * @param player Player
     * @param index Slot index
     * @return
     */
    @Nonnull
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = this.inventorySlots.get(index);
        if (slot == null)
            return ItemStack.EMPTY;
        ItemStack stack = slot.getStack();
        ItemStack copied = stack.copy();

        if (this.mergeItemStack(stack, 0, getPlayerSlotStart() - 1, false))
            return stack;

        // Normal player logic
        if (index >= getPlayerSlotStart() && index < getPlayerSlotStart() + 27)
            if (!this.mergeItemStack(stack, getPlayerSlotStart(), getPlayerSlotStart() + 36, false))
                return ItemStack.EMPTY;

        if (index >= getPlayerSlotStart() + 27 && index < getPlayerSlotStart() + 36 && !this.mergeItemStack
            (stack, getPlayerSlotStart(), getPlayerSlotStart() + 27, false)) {
            return ItemStack.EMPTY;
        }

        if (stack.isEmpty())
            slot.putStack(ItemStack.EMPTY);
        else
            slot.onSlotChanged();

        // Handles player -> container logic
        if (slot.inventory instanceof InventoryPlayer) {

        } else { // Handles container -> player logic

        }

        return stack;
    }

    public abstract int getPlayerSlotStart();
    public abstract int getPlayerInvX();
    public abstract int getPlayerInvY();
}
