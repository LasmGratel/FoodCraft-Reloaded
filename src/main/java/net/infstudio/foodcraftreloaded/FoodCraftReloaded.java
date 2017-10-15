package net.infstudio.foodcraftreloaded;

import net.infstudio.foodcraftreloaded.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = FoodCraftReloaded.MODID, name = FoodCraftReloaded.NAME, version = FoodCraftReloaded.VERSION,
acceptedMinecraftVersions = "[1.12,)", dependencies = "required-after:biomesoplenty",
updateJSON = "http://infinitystudio.github.io/FoodCraft/update.json")
public class FoodCraftReloaded {
    public static final String MODID = "foodcraftreloaded";
    public static final String NAME = "FoodCraft-Reloaded";
    public static final String VERSION = "@version@";
    public static final String GROUP = "net.infstudio.foodcraftreloaded";
    private static final Logger LOGGER = LogManager.getLogger(MODID);

    @Instance(MODID)
    public static FoodCraftReloaded INSTANCE;

    @SidedProxy(clientSide = GROUP + ".client.ClientProxy", serverSide = GROUP + ".common.CommonProxy")
    private static CommonProxy proxy;

    public static Logger getLogger() {
        return LOGGER;
    }

    public static CommonProxy getProxy() {
        return proxy;
    }

    @EventHandler
    public void construct(FMLConstructionEvent event) {
        proxy.construct(event);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void finish(FMLLoadCompleteEvent event) {
        proxy.loadComplete(event);
    }

}
