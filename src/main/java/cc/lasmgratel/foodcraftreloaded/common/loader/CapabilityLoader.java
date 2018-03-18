package cc.lasmgratel.foodcraftreloaded.common.loader;

import cc.lasmgratel.foodcraftreloaded.api.capability.machine.CapabilityMachine;
import cc.lasmgratel.foodcraftreloaded.api.capability.machine.MachineInfo;
import cc.lasmgratel.foodcraftreloaded.api.capability.machine.SmeltingMachineInfo;
import cc.lasmgratel.foodcraftreloaded.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.FluidGlassBottleWrapper;
import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.ItemFruitJuice;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetableJuice;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CapabilityLoader {
    public CapabilityLoader() {
//        CapabilityLiqueur.register();
        CapabilityManager.INSTANCE.register(MachineInfo.class, new CapabilityMachine.BaseStorage(), CapabilityMachine.BaseInfo::new);
        CapabilityManager.INSTANCE.register(SmeltingMachineInfo.class, new CapabilityMachine.SmeltingStorage(), CapabilityMachine.SmeltingInfo::new);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void attachItemStack(AttachCapabilitiesEvent<ItemStack> event) {
//        if (event.getObject().getItem() instanceof ItemLiqueur)
//            event.addCapability(new ResourceLocation(FoodCraftReloaded.MODID, "liqueur"), new CapabilityLiqueur.Provider());
        if (event.getObject().getItem() instanceof ItemFruitJuice || event.getObject().getItem() instanceof ItemVegetableJuice || event.getObject().getItem() == FCRItems.GLASS_BOTTLE) {
            event.addCapability(new ResourceLocation(FoodCraftReloaded.MODID, "glass_bottle_wrapper"), new FluidGlassBottleWrapper(event.getObject()));
        }
    }
}
