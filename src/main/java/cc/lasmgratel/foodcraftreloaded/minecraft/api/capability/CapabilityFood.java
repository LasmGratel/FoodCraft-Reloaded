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

package cc.lasmgratel.foodcraftreloaded.minecraft.api.capability;

import cc.lasmgratel.foodcraftreloaded.api.food.Food;
import cc.lasmgratel.foodcraftreloaded.api.food.material.Material;
import cc.lasmgratel.foodcraftreloaded.common.material.MaterialBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public interface CapabilityFood {
    @CapabilityInject(Material.class)
    Capability<Material> MATERIAL_CAPABILITY = null;

    @CapabilityInject(Food.class)
    Capability<Food> FOOD_CAPABILITY = null;

    class MaterialStorage implements Capability.IStorage<Material> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<Material> capability, Material instance, EnumFacing side) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setString("name", instance.getName());
            tagCompound.setDouble("energy", instance.getEnergy());
            return tagCompound;
        }

        @Override
        public void readNBT(Capability<Material> capability, Material instance, EnumFacing side, NBTBase nbt) {
            NBTTagCompound tagCompound = (NBTTagCompound) nbt;
            if (instance instanceof MaterialBase) {
                ((MaterialBase) instance).setName(tagCompound.getString("name"));
                ((MaterialBase) instance).setEnergy(tagCompound.getDouble("energy"));
            }
        }
    }

    class FoodStorage implements Capability.IStorage<Food> {
        @Nullable
        @Override
        public NBTBase writeNBT(Capability<Food> capability, Food instance, EnumFacing side) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setString("name", instance.getName());
            tagCompound.setDouble("energy", instance.getEnergy());
            tagCompound.setInteger("healAmount", instance.getHealAmount());
            tagCompound.setInteger("weight", instance.getWeight());
            tagCompound.setInteger("duration", (int) (instance.getDuration(TimeUnit.MILLISECONDS) / 50));
            NBTTagList materials = new NBTTagList();
            instance.getMaterialMap().forEach((material, weight) -> {
                NBTTagCompound compound = (NBTTagCompound) MATERIAL_CAPABILITY.writeNBT(material, null);
                compound.setInteger("weight", weight);
                materials.appendTag(compound);
            });
            tagCompound.setTag("materials", materials);
            return tagCompound;
        }

        @Override
        public void readNBT(Capability<Food> capability, Food instance, EnumFacing side, NBTBase nbt) {

        }
    }
}
