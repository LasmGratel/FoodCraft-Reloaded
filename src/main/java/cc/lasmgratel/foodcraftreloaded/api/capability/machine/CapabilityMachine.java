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

package cc.lasmgratel.foodcraftreloaded.api.capability.machine;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

import javax.annotation.Nonnull;

public interface CapabilityMachine {
    @CapabilityInject(MachineInfo.class)
    Capability<MachineInfo> MACHINE_INFO_CAPABILITY = null;

    @CapabilityInject(SmeltingMachineInfo.class)
    Capability<SmeltingMachineInfo> SMELTING_MACHINE_INFO_CAPABILITY = null;

    class WildcardStorage<T extends MachineInfo> implements Capability.IStorage<T> {
        @Nonnull
        @Override
        public NBTTagCompound writeNBT(Capability<T> capability, T instance, EnumFacing side) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            tagCompound.setInteger("progress", instance.getProgress());
            return tagCompound;
        }

        @Override
        public void readNBT(Capability<T> capability, T instance, EnumFacing side, NBTBase nbt) {
            if (nbt instanceof NBTTagCompound) {
                instance.setProgress(((NBTTagCompound) nbt).getInteger("progress"));
            } else {
                FoodCraftReloaded.getLogger().warn(nbt, new ClassCastException("NBT type mismatch!"));
            }
        }
    }

    class BaseStorage extends WildcardStorage<MachineInfo> {}

    class SmeltingStorage extends WildcardStorage<SmeltingMachineInfo> {
        @Nonnull
        @Override
        public NBTTagCompound writeNBT(Capability<SmeltingMachineInfo> capability, SmeltingMachineInfo instance, EnumFacing side) {
            NBTTagCompound tagCompound = super.writeNBT(capability, instance, side);
            tagCompound.setInteger("fuel", instance.getFuel());
            return tagCompound;
        }

        @Override
        public void readNBT(Capability<SmeltingMachineInfo> capability, SmeltingMachineInfo instance, EnumFacing side, NBTBase nbt) {
            super.readNBT(capability, instance, side, nbt);
            instance.setFuel(((NBTTagCompound) nbt).getInteger("fuel"));
        }
    }

    class BaseInfo implements MachineInfo {
        private int progress;

        @Override
        public int getProgress() {
            return progress;
        }

        @Override
        public void setProgress(int progress) {
            this.progress = progress;
        }
    }

    class SmeltingInfo extends BaseInfo implements SmeltingMachineInfo {
        private int fuel;

        @Override
        public int getFuel() {
            return fuel;
        }

        @Override
        public void setFuel(int fuel) {
            this.fuel = fuel;
        }
    }
}
