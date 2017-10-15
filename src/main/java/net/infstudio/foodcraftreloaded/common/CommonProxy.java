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

package net.infstudio.foodcraftreloaded.common;

import net.infstudio.foodcraftreloaded.block.BlockLoader;
import net.infstudio.foodcraftreloaded.item.ItemLoader;
import net.infstudio.foodcraftreloaded.item.food.PropertiedFoodLoader;
import net.infstudio.foodcraftreloaded.util.loader.LoaderManager;
import net.infstudio.foodcraftreloaded.worldgen.FruitTreeGenerator;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.relauncher.Side;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.Arrays;

public class CommonProxy {
    private final LoaderManager loaderManager = new LoaderManager();

    public CommonProxy() {
        Arrays.asList(
            BlockLoader.class, ItemLoader.class,
            FruitLoader.class, VegetableLoader.class,
            KitchenKnifeLoader.class, PropertiedFoodLoader.class,
            LiqueurLoader.class,
            AdvancementLoader.class, RecipeLoader.class,
            EventLoader.class
        ).forEach(loaderManager::addLoader);
    }

    @OverridingMethodsMustInvokeSuper
    public void construct(FMLConstructionEvent event) {
        FluidRegistry.enableUniversalBucket();
        loaderManager.invoke(event, LoaderState.CONSTRUCTING, Side.SERVER);
    }

    @OverridingMethodsMustInvokeSuper
    public void preInit(FMLPreInitializationEvent event) {
        loaderManager.invoke(event, LoaderState.PREINITIALIZATION, Side.SERVER);
    }

    @OverridingMethodsMustInvokeSuper
    public void init(FMLInitializationEvent event) {
        loaderManager.invoke(event, LoaderState.INITIALIZATION, Side.SERVER);
    }

    @OverridingMethodsMustInvokeSuper
    public void postInit(FMLPostInitializationEvent event) {
        loaderManager.invoke(event, LoaderState.POSTINITIALIZATION, Side.SERVER);
    }

    @OverridingMethodsMustInvokeSuper
    public void loadComplete(FMLLoadCompleteEvent event) {
        loaderManager.invoke(event, LoaderState.AVAILABLE, Side.SERVER);
        new FruitTreeGenerator();
    }

    public LoaderManager getLoaderManager() {
        return loaderManager;
    }
}
