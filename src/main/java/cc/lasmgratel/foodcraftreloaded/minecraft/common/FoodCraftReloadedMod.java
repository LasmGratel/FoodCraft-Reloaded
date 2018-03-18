/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * This file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.minecraft.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

import java.util.Optional;

@Mod(modid = FoodCraftReloadedMod.MODID, name = FoodCraftReloadedMod.NAME, version = FoodCraftReloadedMod.VERSION,
acceptedMinecraftVersions = "[1.12.2,)",
updateJSON = "http://lasmgratel.github.io/FoodCraft-Reloaded/update.json")
public class FoodCraftReloadedMod {
    public static final String MODID = "foodcraftreloaded";
    public static final String NAME = "FoodCraft Reloaded";
    public static final String VERSION = "@version@";
    public static final String GROUP = "cc.lasmgratel.foodcraftreloaded.minecraft";

    @Instance(MODID)
    public static FoodCraftReloadedMod INSTANCE;

    @SidedProxy(clientSide = GROUP + ".client.ClientProxy", serverSide = GROUP + ".common.CommonProxy")
    private static CommonProxy proxy;

    public static CommonProxy getProxy() {
        return proxy;
    }

    public static <T> Optional<T> getLoader(Class<T> loaderClass) {
        return getProxy().getLoaderManager().getLoader(loaderClass);
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
