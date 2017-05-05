package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.init.FCRBlocks;
import net.infstudio.foodcraftreloaded.utils.loader.annotation.Load;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventLoader {

    @Load(value = LoaderState.INITIALIZATION)
    public void register() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent event) {
        if (event.getWorld().getBlockState(event.getPos().up()).getBlock() == FCRBlocks.RICE_PLANT &&
            event.getState().getBlock() != Blocks.FARMLAND)
            event.getWorld().destroyBlock(event.getPos().up(), true);
    }
}
