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

package cc.lasmgratel.foodcraftreloaded.client;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import javax.annotation.Nullable;

import static cc.lasmgratel.foodcraftreloaded.client.EnumGui.values;

public class GuiHandler implements IGuiHandler {
    public GuiHandler() {
        NetworkRegistry.INSTANCE.registerGuiHandler(FoodCraftReloaded.INSTANCE, this);
    }

    @Nullable
    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (values().length >= ID) {
            try {
                return (Container) EnumGui.values()[ID].getContainerClass().getConstructor(InventoryPlayer.class, TileEntity.class).newInstance(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().error("Cannot create container instance!", e);
            }
        }
        return null;
    }

    @Nullable
    @Override
    public Gui getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (values().length >= ID) {
            try {
                return (Gui) EnumGui.values()[ID].getGuiClass().getConstructor(InventoryPlayer.class, TileEntity.class).newInstance(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
            } catch (Exception e) {
                FoodCraftReloaded.getLogger().error("Cannot create container instance!", e);
            }
        }
        return null;
    }
}
