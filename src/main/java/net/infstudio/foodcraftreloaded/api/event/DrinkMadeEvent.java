package net.infstudio.foodcraftreloaded.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nonnull;

public class DrinkMadeEvent extends PlayerEvent {
    @Nonnull
    private final ItemStack making;

    public DrinkMadeEvent(EntityPlayer player, @Nonnull ItemStack making) {
        super(player);
        this.making = making;
    }

    @Nonnull
    public ItemStack getMaking() {
        return making;
    }
}
