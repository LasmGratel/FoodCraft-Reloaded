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

import net.minecraft.nbt.NBTTagCompound;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Machine type that can use fuel to progress.
 */
public abstract class TileEntitySmeltingMachine extends TileEntityProgressiveMachine implements SmeltingMachine {
    private int fuel;
    private int currentFuelAmount;

    public TileEntitySmeltingMachine(int maxProgress) {
        super(maxProgress);
    }

    @Override
    public boolean canStart() {
        if (fuel <= 0) {
            int progressed = progressFuel();
            if (progressed == 0)
                return false;
            else
                fuel = progressed;
        }
        return fuel > 0;
    }

    @Override
    public boolean canProgress() {
        return fuel > 0;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void progress() {
        if (fuel <= 0) {
            int progressed = progressFuel();
            if (progressed == 0)
                resetProgress();
            else
                fuel = progressed;
        } else
            --fuel;
    }

    public int getFuel() {
        return isStarted() ? fuel : 0;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getCurrentFuelAmount() {
        return isStarted() ? currentFuelAmount : 0;
    }

    public void setCurrentFuelAmount(int currentFuelAmount) {
        this.currentFuelAmount = currentFuelAmount;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        setFuel(compound.getInteger("fuel"));
        setCurrentFuelAmount(compound.getInteger("currentFuelAmount"));
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("fuel", getFuel());
        compound.setInteger("currentFuelAmount", getCurrentFuelAmount());
        return compound;
    }
}
