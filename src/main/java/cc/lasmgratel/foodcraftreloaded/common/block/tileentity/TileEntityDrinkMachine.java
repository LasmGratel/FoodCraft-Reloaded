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

import cc.lasmgratel.foodcraftreloaded.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeInput;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeManager;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityDrinkMachine extends TileEntityEnergizedMachine {
    public static final int ENERGY_PER_TICK = 20; // 20 RF/t
    public static final int ENERGY_CAPACITY = 2000;
    public static final int FLUID_CAPACITY = 8000;

    private FluidTank tank = new FluidTank(TileEntityDrinkMachine.FLUID_CAPACITY);
    private ItemStackHandler itemStackHandler = new ItemStackHandler(3);
    private ItemStack output = ItemStack.EMPTY;
    private FluidStack fluidOutput = null;

    public TileEntityDrinkMachine() {
        super(TileEntitySmeltingDrinkMachine.BURN_TIME, ENERGY_CAPACITY, ENERGY_PER_TICK);
    }

    @Override
    public boolean canStart() {
        DrinkRecipe recipe = RecipeManager.getInstance().getRecipeNullable(DrinkRecipe.class, new RecipeInput(itemStackHandler.getStackInSlot(0).getItem()));
        return super.canStart() && recipe != null && tank.getFluidAmount() < TileEntityDrinkMachine.FLUID_CAPACITY;
    }

    @Override
    public void startProgress() {
        DrinkRecipe recipe = RecipeManager.getInstance().getRecipeNullable(DrinkRecipe.class, new RecipeInput(itemStackHandler.getStackInSlot(0).getItem()));
        if (recipe != null) {
            FoodCraftReloaded.getLogger().debug("Drink machine: Found recipe " + recipe.getOutput().<FluidStack>first().getFluid().getName());
            fluidOutput = recipe.getOutput().first();
            output = recipe.getOutput().second();
            itemStackHandler.getStackInSlot(0).splitStack(1);
        }
    }

    @Override
    public void progressCompleted() {
        itemStackHandler.insertItem(1, output, false);
        tank.fill(fluidOutput, true);
        output = ItemStack.EMPTY;
        fluidOutput = null;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
            || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY
            || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) itemStackHandler;
        else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) tank;
        else return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        tank.readFromNBT(tag.getCompoundTag("tank"));
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemStackHandler, null, tag.getTag("items"));
        output = new ItemStack(tag.getCompoundTag("output"));
        fluidOutput = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluidOutput"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setTag("items", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemStackHandler, null));
        tag.setTag("output", output.serializeNBT());
        if (fluidOutput != null)
            tag.setTag("fluidOutput", fluidOutput.writeToNBT(new NBTTagCompound()));
        tag.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        return tag;
    }
}
