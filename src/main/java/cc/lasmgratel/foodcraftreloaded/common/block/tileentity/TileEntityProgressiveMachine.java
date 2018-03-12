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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Machine type that can be progressed in tick.
 */
public abstract class TileEntityProgressiveMachine extends TileEntity implements ITickable, Machine {
    private int maxProgress;
    private boolean started = false;
    private int progress = 0;

    public TileEntityProgressiveMachine(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public static int getProgress(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityProgressiveMachine && ((TileEntityProgressiveMachine) tileEntity).isStarted())
            return ((TileEntityProgressiveMachine) tileEntity).getMaxProgress() - ((TileEntityProgressiveMachine) tileEntity).getProgress();
        return 0;
    }

    public static boolean isProgressing(TileEntity tileEntity) {
        return tileEntity instanceof TileEntityProgressiveMachine && ((TileEntityProgressiveMachine) tileEntity).isStarted();
    }

    @Override
    public void update() {
        if (isStarted()) {
            if (getProgress() > 0) { // Progress is not completed
                if (canProgress()) {
                    progress();
                    setProgress(getProgress() - 1);
                }
            } else {
                setProgress(0);
                progressCompleted();
                setStarted(false);
            }
        } else {
            if (canStart()) { // If machine can be started
                setProgress(getMaxProgress());
                setStarted(true);
                startProgress();
            }
        }
    }

    @Override
    public void resetProgress() {
        setStarted(false);
        setProgress(0);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        setProgress(compound.getInteger("progress"));
        setStarted(compound.getBoolean("started"));
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("progress", getProgress());
        compound.setBoolean("started", isStarted());
        return compound;
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}
