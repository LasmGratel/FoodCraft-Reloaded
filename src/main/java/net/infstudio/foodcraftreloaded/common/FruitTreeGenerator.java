package net.infstudio.foodcraftreloaded.common;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FruitTreeGenerator {
    public FruitTreeGenerator() {
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
    }

    @SubscribeEvent
    public void onSaplingGrowEvent(SaplingGrowTreeEvent event) {

    }

}
