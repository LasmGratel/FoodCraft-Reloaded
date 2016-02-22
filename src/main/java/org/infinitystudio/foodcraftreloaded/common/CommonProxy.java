/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
package org.infinitystudio.foodcraftreloaded.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.infinitystudio.foodcraftreloaded.FoodCraftReloaded;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.ModManager;
import org.infinitystudio.foodcraftreloaded.utils.modmanagent.common.CommonModManagement;

public class CommonProxy {
    protected static final ModManager manager = new ModManager(
            FoodCraftReloaded.MODID, FoodCraftRegistration.class,
            CommonModManagement.BLOCK, CommonModManagement.BLOCKFLUID,
            CommonModManagement.EVENT, CommonModManagement.EVENTBUS,
            CommonModManagement.FLUID,
            CommonModManagement.ITEM, CommonModManagement.FOOD,
            CommonModManagement.VEGETABLE, CommonModManagement.MEAT,
            CommonModManagement.OBJPREINIT, CommonModManagement.OBJINIT, CommonModManagement.OBJPOSTINIT,
            CommonModManagement.TILEENTITY
    );

    /**
     * FML Pre Initialization Event Handler.
     *
     * @param event FMLPreInitializationEvent
     */
    public void preInit(FMLPreInitializationEvent event) {
        manager.preInit();
    }

    /**
     * FML Initialization Event Handler.
     *
     * @param event FMLInitializationEvent
     */
    public void init(FMLInitializationEvent event) {
        manager.init();
    }

    /**
     * FML Post Initialization Event Handler.
     *
     * @param event FMLPostInitializationEvent
     */
    public void postInit(FMLPostInitializationEvent event) {
        manager.postInit();
    }

    public void loadComplete(FMLLoadCompleteEvent event) {
    }
}
