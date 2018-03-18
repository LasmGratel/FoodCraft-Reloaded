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

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.RecipeInput;
import cc.lasmgratel.foodcraftreloaded.minecraft.api.recipe.RecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntitySmeltingDrinkMachine extends TileEntitySmeltingMachine implements ITickable {
    public static final int BURN_TIME = 200;

    private FluidTank tank = new FluidTank(TileEntityDrinkMachine.FLUID_CAPACITY);
    private ItemStackHandler itemStackHandler = new ItemStackHandler(3);
    private ItemStack output = ItemStack.EMPTY;
    private FluidStack fluidOutput = null;

    public TileEntitySmeltingDrinkMachine() {
        super(BURN_TIME);
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
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(itemStackHandler, null, tag.getTag("items"));
        output = new ItemStack(tag.getCompoundTag("output"));
        fluidOutput = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluidOutput"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setTag("items", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.writeNBT(itemStackHandler, null));
        tag.setTag("output", output.serializeNBT());
        if (fluidOutput != null)
            tag.setTag("fluidOutput", fluidOutput.writeToNBT(new NBTTagCompound()));
        tag.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        return tag;
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
            FoodCraftReloaded.getLogger().debug("Smelting drink machine: Found recipe " + recipe.getOutput().<FluidStack>first().getFluid().getName());
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
    public int progressFuel() {
        int burnTime = TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(2));
        if (burnTime != 0)
            itemStackHandler.extractItem(2, 1, false);
        setCurrentFuelAmount(TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(2)));
        return TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(2));
    }
}
