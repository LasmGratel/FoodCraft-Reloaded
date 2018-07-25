package cc.lasmgratel.foodcraftreloaded.minecraft.common.loader;

import cc.lasmgratel.foodcraftreloaded.minecraft.api.capability.machine.CapabilityMachine;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.capability.machine.MachineInfo;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.capability.machine.SmeltingMachineInfo;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.FluidGlassBottleWrapper;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.ItemFruitJuice;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.vegetable.ItemVegetableJuice;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = FoodCraftReloadedMod.MODID)
public class CapabilityLoader {
    public CapabilityLoader() {
//        CapabilityLiqueur.register();
        CapabilityManager.INSTANCE.register(MachineInfo.class, new CapabilityMachine.BaseStorage(), CapabilityMachine.BaseInfo::new);
        CapabilityManager.INSTANCE.register(SmeltingMachineInfo.class, new CapabilityMachine.SmeltingStorage(), CapabilityMachine.SmeltingInfo::new);
    }

    @SubscribeEvent
    public void attachItemStack(AttachCapabilitiesEvent<ItemStack> event) {
//        if (event.getObject().getItem() instanceof ItemLiqueur)
//            event.addCapability(new ResourceLocation(FoodCraftReloadedMod.MODID, "liqueur"), new CapabilityLiqueur.Provider());
        if (event.getObject().getItem() instanceof ItemFruitJuice || event.getObject().getItem() instanceof ItemVegetableJuice || event.getObject().getItem() == FCRItems.GLASS_BOTTLE) {
            event.addCapability(new ResourceLocation(FoodCraftReloadedMod.MODID, "glass_bottle_wrapper"), new FluidGlassBottleWrapper(event.getObject()));
        }
    }
}
