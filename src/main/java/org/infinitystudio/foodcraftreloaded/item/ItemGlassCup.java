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
package org.infinitystudio.foodcraftreloaded.item;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class ItemGlassCup extends BaseItem implements IFluidContainerItem {

    private static final int CAPACITY = 1000;

    /**
     * @param container ItemStack which is the fluid container.
     * @return FluidStack representing the fluid in the container, null if the container is empty.
     */
    @Override
    public FluidStack getFluid(ItemStack container) {
        if (!container.hasTagCompound() || !container.getTagCompound().hasKey("Fluid")) {
            return null;
        }
        return FluidStack.loadFluidStackFromNBT(container.getTagCompound().getCompoundTag("Fluid"));
    }

    /**
     * @param container ItemStack which is the fluid container.
     * @return Capacity of this fluid container.
     */
    @Override
    public int getCapacity(ItemStack container) {
        return CAPACITY;
    }

    /**
     * @param container ItemStack which is the fluid container.
     * @param resource  FluidStack attempting to fill the container.
     * @param doFill    If false, the fill will only be simulated.
     * @return Amount of fluid that was (or would have been, if simulated) filled into the
     * container.
     */
    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill) {
        if (resource == null)
            return 0;

        if (!doFill) {
            if (!container.hasTagCompound() || !container.getTagCompound().hasKey("Fluid"))
                return Math.min(CAPACITY, resource.amount);

            FluidStack stack = FluidStack.loadFluidStackFromNBT(container.getTagCompound().getCompoundTag("Fluid"));

            if (stack == null)
                return Math.min(CAPACITY, resource.amount);

            if (!stack.isFluidEqual(resource))
                return 0;

            return Math.min(CAPACITY - stack.amount, resource.amount);
        }

        if (!container.hasTagCompound())
            container.setTagCompound(new NBTTagCompound());

        if (!container.getTagCompound().hasKey("Fluid")) {
            NBTTagCompound fluidTag = resource.writeToNBT(new NBTTagCompound());

            if (CAPACITY < resource.amount) {
                fluidTag.setInteger("Amount", CAPACITY);
                container.getTagCompound().setTag("Fluid", fluidTag);
                return CAPACITY;
            }

            container.getTagCompound().setTag("Fluid", fluidTag);
            return resource.amount;
        }

        NBTTagCompound fluidTag = container.getTagCompound().getCompoundTag("Fluid");
        FluidStack stack = FluidStack.loadFluidStackFromNBT(fluidTag);

        if (!stack.isFluidEqual(resource)) {
            return 0;
        }

        int filled = CAPACITY - stack.amount;
        if (resource.amount < filled) {
            stack.amount += resource.amount;
            filled = resource.amount;
        } else {
            stack.amount = CAPACITY;
        }

        container.getTagCompound().setTag("Fluid", stack.writeToNBT(fluidTag));
        return filled;
    }

    /**
     * @param container ItemStack which is the fluid container.
     * @param maxDrain  Maximum amount of fluid to be removed from the container.
     * @param doDrain
     * @return Amount of fluid that was (or would have been, if simulated) drained from the
     * container.
     */
    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain) {
        if (!container.hasTagCompound() || !container.getTagCompound().hasKey("Fluid"))
            return null;

        FluidStack stack = FluidStack.loadFluidStackFromNBT(container.getTagCompound().getCompoundTag("Fluid"));
        if (stack == null)
            return null;

        int currentAmount = stack.amount;
        stack.amount = Math.min(stack.amount, maxDrain);
        if (doDrain) {
            if (currentAmount == stack.amount) {
                container.getTagCompound().removeTag("Fluid");

                if (container.getTagCompound().hasNoTags())
                    container.setTagCompound(null);
                return stack;
            }

            NBTTagCompound fluidTag = container.getTagCompound().getCompoundTag("Fluid");
            fluidTag.setInteger("storageAmount", currentAmount - stack.amount);
            container.getTagCompound().setTag("Fluid", fluidTag);
        }
        return stack;
    }
}
