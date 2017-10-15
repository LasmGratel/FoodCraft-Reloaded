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

package net.infstudio.foodcraftreloaded.client;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.client.gui.*;
import net.infstudio.foodcraftreloaded.common.container.*;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(FoodCraftReloaded.INSTANCE, this);
    }

    @Nullable
    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiID.DRINK_MACHINE:
                return new ContainerDrinkMachine(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
            case GuiID.PRESSURE_COOKER:
                return new ContainerPressureCooker(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Gui getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GuiID.DRINK_MACHINE:
                return new GuiContainerDrinkMachine(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
            case GuiID.PRESSURE_COOKER:
                return new GuiContainerPressureCooker(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
        }
        return null;
    }
}
