/**
 * Singularity Mod for Minecraft.
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
package org.infinitystudio.foodcraftreloaded.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;

public class GlassCupTileEntity extends TileEntity implements IFluidHandler {
    /**
     * Capacity of this cup.
     * Default 1000(1 bucket)
     */
    private static final int CAPACITY = 1000;

    private static Fluid storageFluid = null;

    private static int storageFluidAmount = 0;

    private static NBTTagCompound storageFluidNBT = new NBTTagCompound();

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(storageFluid != null)
            compound.setString("storageFluid", FluidRegistry.getFluidName(storageFluid));
        compound.setInteger("storageFluidAmount", storageFluidAmount);
        compound.merge(storageFluidNBT);
		return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        storageFluid = FluidRegistry.getFluid(compound.getString("storageFluid"));
        storageFluidAmount = compound.getInteger("storageFluidAmount");
        storageFluidNBT.merge(compound);
    }

    /**
     * Fills fluid into internal tanks, distribution is left entirely to the IFluidHandler.
     *
     * @param from     Orientation the Fluid is pumped in from.
     * @param resource FluidStack representing the Fluid and maximum amount of fluid to be filled.
     * @param doFill   If false, fill will only be simulated.
     * @return Amount of resource that was (or would have been, if simulated) filled.
     */
    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        if(doFill) {
            if(storageFluidAmount < CAPACITY) {
                if(storageFluid != null && storageFluid.equals(resource.getFluid())) {
                    storageFluidAmount += resource.amount;
                    if(storageFluidAmount > CAPACITY) {
                        int ret = storageFluidAmount - CAPACITY;
                        storageFluidAmount -= CAPACITY;
                        return resource.amount - ret;
                    }
                    return resource.amount;
                } else if(storageFluid != null) {
                    return 0;
                } else {
                    storageFluid = resource.getFluid();
                }
            } else {
                storageFluidAmount = CAPACITY;
                return 0;
            }
            storageFluidNBT.setTag("Fluid", resource.tag);
        }
        return 0;
    }

    /**
     * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
     *
     * @param from     Orientation the Fluid is drained to.
     * @param resource FluidStack representing the Fluid and maximum amount of fluid to be drained.
     * @param doDrain  If false, drain will only be simulated.
     * @return FluidStack representing the Fluid and amount that was (or would have been, if
     * simulated) drained.
     */
    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        if(doDrain) {
            if(resource.amount <= storageFluidAmount) {
                FluidStack ret = new FluidStack(resource.getFluid(), resource.amount);
                storageFluidAmount -= resource.amount;
                return ret;
            } else {
                FluidStack ret = new FluidStack(resource.getFluid(), storageFluidAmount);
                storageFluidAmount = 0;
                return ret;
            }
        }
        return null;
    }

    /**
     * Drains fluid out of internal tanks, distribution is left entirely to the IFluidHandler.
     * <p>
     * This method is not Fluid-sensitive.
     *
     * @param from     Orientation the fluid is drained to.
     * @param maxDrain Maximum amount of fluid to drain.
     * @param doDrain  If false, drain will only be simulated.
     * @return FluidStack representing the Fluid and amount that was (or would have been, if
     * simulated) drained.
     */
    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        if(doDrain) {
            if(maxDrain <= storageFluidAmount) {
                FluidStack ret = new FluidStack(storageFluid, maxDrain);
                storageFluidAmount -= maxDrain;
                return ret;
            } else {
                FluidStack ret = new FluidStack(storageFluid, storageFluidAmount);
                storageFluidAmount = 0;
                return ret;
            }
        }
        return null;
    }

    /**
     * Returns true if the given fluid can be inserted into the given direction.
     * <p>
     * More formally, this should return true if fluid is able to enter from the given direction.
     *
     * @param from
     * @param fluid
     */
    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return storageFluid.equals(fluid);
    }

    /**
     * Returns true if the given fluid can be extracted from the given direction.
     * <p>
     * More formally, this should return true if fluid is able to leave from the given direction.
     *
     * @param from
     * @param fluid
     */
    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return storageFluid.equals(fluid);
    }

    /**
     * Returns an array of objects which represent the internal tanks. These objects cannot be used
     * to manipulate the internal tanks. See {@link FluidTankInfo}.
     *
     * @param from Orientation determining which tanks should be queried.
     * @return Info for the relevant internal tanks.
     */
    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[0];
    }
}
