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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.block.tileentity;

import cc.lasmgratel.foodcraftreloaded.api.machine.Machine;
import cc.lasmgratel.foodcraftreloaded.api.machine.Process;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.List;

/**
 * Machine type that can be progressed in tick.
 */
public abstract class TileEntityProgressiveMachine<T extends TileEntityProgressiveMachine<T>> extends TileEntity implements ITickable, Machine<T> {
    private List<Process<T>> processes = new ArrayList<>();

    public TileEntityProgressiveMachine(int maxProgress) {
        getProcesses().add(new Process.Builder<T>(maxProgress)
            .canProgress(ignored -> canProgress())
            .canStart(ignored -> canStart())
            .completed(ignored -> progressCompleted())
            .started(ignored -> startProgress())
            .build()
        );
    }

    public static int getProgress(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityProgressiveMachine && ((TileEntityProgressiveMachine) tileEntity).isStarted())
            return ((TileEntityProgressiveMachine) tileEntity).getProgress();
        return 0;
    }

    public static boolean isProgressing(TileEntity tileEntity) {
        return tileEntity instanceof TileEntityProgressiveMachine && ((TileEntityProgressiveMachine) tileEntity).isStarted();
    }

    @Override
    public List<Process<T>> getProcesses() {
        return processes;
    }

    @Override
    public void update() {
        processes.forEach(process -> process.update((T) this));
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList list = compound.getTagList("processes", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            Process<T> process = getProcesses().get(i);
            NBTTagCompound processNbt = list.getCompoundTagAt(i);
            process.setProgress(processNbt.getInteger("progress"));
            process.setMaxProgress(processNbt.getInteger("maxProgress"));
            process.setStarted(processNbt.getBoolean("started"));
            process.setCompleted(processNbt.getBoolean("completed"));
        }
    }

    @Nonnull
    @Override
    @OverridingMethodsMustInvokeSuper
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        NBTTagList list = new NBTTagList();
        for (Process<T> process : getProcesses()) {
            NBTTagCompound processNbt = new NBTTagCompound();
            processNbt.setInteger("progress", process.getProgress());
            processNbt.setInteger("maxProgress", process.getMaxProgress());
            processNbt.setBoolean("completed", process.isCompleted());
            processNbt.setBoolean("started", process.isStarted());
            list.appendTag(processNbt);
        }
        return compound;
    }

    public int getMaxProgress() {
        return getProcesses().stream().findAny().map(Process::getMaxProgress).orElse(0);
    }

    public int getProgress() {
        return getProcesses().stream().findAny().map(Process::getProgress).orElse(0);
    }

    public boolean isStarted() {
        return getProcesses().stream().findAny().map(Process::isStarted).orElse(false);
    }
}
