package net.infstudio.foodcraftreloaded.client;

import net.infstudio.foodcraftreloaded.common.CommonProxy;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {
    @SideOnly(Side.CLIENT)
    public void construct(FMLConstructionEvent event) {
        super.construct(event);
        getLoaderManager().invoke(event, LoaderState.CONSTRUCTING, Side.CLIENT);
    }

    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        getLoaderManager().invoke(event, LoaderState.PREINITIALIZATION, Side.CLIENT);
        new GuiHandler();
    }

    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {
        super.init(event);
        getLoaderManager().invoke(event, LoaderState.INITIALIZATION, Side.CLIENT);
    }

    @SideOnly(Side.CLIENT)
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        getLoaderManager().invoke(event, LoaderState.POSTINITIALIZATION, Side.CLIENT);
    }

    @SideOnly(Side.CLIENT)
    public void loadComplete(FMLLoadCompleteEvent event) {
        super.loadComplete(event);
        getLoaderManager().invoke(event, LoaderState.AVAILABLE, Side.CLIENT);
    }
}
