package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.common.util.loader.annotation.Load;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityLoader {
    public CapabilityLoader() {
//        CapabilityLiqueur.register();
    }

    @Load(value = LoaderState.INITIALIZATION)
    public void load() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void attachItemStack(AttachCapabilitiesEvent<ItemStack> event) {
//        if (event.getObject().getItem() instanceof ItemLiqueur)
//            event.addCapability(new ResourceLocation(FoodCraftReloaded.MODID, "liqueur"), new CapabilityLiqueur.Provider());
    }
}
