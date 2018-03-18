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

package cc.lasmgratel.foodcraftreloaded.common.block.tileentity;

import cc.lasmgratel.foodcraftreloaded.common.util.machine.EnergizedMachine;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class TileEntityEnergizedMachine extends TileEntityProgressiveMachine implements EnergizedMachine {
    protected EnergyStorage energyStorage;
    private int energyPerTick;

    /**
     * @param maxProgress Ticks to produce
     * @param capacity Energy capacity
     * @param energyPerTick Energy costs per tick (XXX RF/t)
     */
    public TileEntityEnergizedMachine(int maxProgress, int capacity, int energyPerTick) {
        super(maxProgress);
        energyStorage = new EnergyStorage(capacity);
        this.energyPerTick = energyPerTick;
    }

    public static int getEnergyCapacity(TileEntity tileEntity) {
        return tileEntity instanceof TileEntityEnergizedMachine ? ((TileEntityEnergizedMachine) tileEntity).energyStorage.getMaxEnergyStored() : -1;
    }

    public static int getEnergyStored(TileEntity tileEntity) {
        return tileEntity instanceof TileEntityEnergizedMachine ? ((TileEntityEnergizedMachine) tileEntity).energyStorage.getEnergyStored() : -1;
    }

    @Override
    public boolean canStart() {
        return energyStorage.canExtract() && energyStorage.getEnergyStored() >= energyPerTick;
    }

    @Override
    public boolean canProgress() {
        return energyStorage.canExtract() && energyStorage.getEnergyStored() >= energyPerTick;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void progress() {
        energyStorage.extractEnergy(energyPerTick, false);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return super.hasCapability(capability, facing) || capability == CapabilityEnergy.ENERGY;
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    @OverridingMethodsMustInvokeSuper
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY ? (T) energyStorage : super.getCapability(capability, facing);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        CapabilityEnergy.ENERGY.readNBT(energyStorage, null, compound.getTag("energyStorage"));
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag("energyStorage", CapabilityEnergy.ENERGY.writeNBT(energyStorage, null));
        return compound;
    }
}
