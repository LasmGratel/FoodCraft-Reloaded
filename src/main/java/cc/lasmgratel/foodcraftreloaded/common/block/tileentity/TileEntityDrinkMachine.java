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
import cc.lasmgratel.foodcraftreloaded.api.recipe.Recipe;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeInput;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityDrinkMachine extends TileFluidHandler implements ITickable {
    public static final int DRINK_ENERGY_NEED = 500;
    public static final int ENERGY_CAPACITY = 2000;
    public static final int FLUID_CAPACITY = 8000;

    private IItemHandlerModifiable itemHandler = new ItemStackHandler(2);
    private EnergyStorage energyStorage = new EnergyStorage(ENERGY_CAPACITY);

    private int energyTaken = 0;

    @Nullable
    private Recipe currentRecipe = null;

    public TileEntityDrinkMachine() {
        tank.setCapacity(FLUID_CAPACITY);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        CapabilityEnergy.ENERGY.getStorage().readNBT(CapabilityEnergy.ENERGY, energyStorage, null, tag.getTag("energy"));
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler, null, tag.getTag("inv"));
        energyTaken = tag.getInteger("energyTaken");
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setTag("energy", CapabilityEnergy.ENERGY.getStorage().writeNBT(CapabilityEnergy.ENERGY, energyStorage, null));
        tag.setTag("inv", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemHandler, null));
        tag.setInteger("energyTaken", energyTaken);
        return super.writeToNBT(tag);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityEnergy.ENERGY || capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) return (T) energyStorage;
        else if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing == null)
                return (T) itemHandler;
            switch (facing) {
                case UP:
                    return (T) new ItemStackHandler(NonNullList.withSize(1, itemHandler.getStackInSlot(0)));
                case DOWN:
                    return (T) new ItemStackHandler(NonNullList.withSize(1, itemHandler.getStackInSlot(1)));
                default:
                    return (T) itemHandler;
            }
        }
        else return super.getCapability(capability, facing);
    }

    @Override
    public void update() {
        // Update Crafting
        if (currentRecipe != null && tank.canFillFluidType(currentRecipe.getOutput().first())) {
            if (energyTaken == DRINK_ENERGY_NEED) {
                itemHandler.extractItem(0, 1, false);
                itemHandler.insertItem(1, currentRecipe.getOutput().second(), false);
                tank.fill(currentRecipe.getOutput().first(), true);
                currentRecipe = null;
            } else if (energyStorage.getEnergyStored() > 0) {
                energyStorage.extractEnergy(1, false);
                ++energyTaken;
            } else {
                currentRecipe = null;
            }
        } else {
            currentRecipe = RecipeManager.getInstance().getRecipeNullable(DrinkRecipe.class, new RecipeInput(itemHandler.getStackInSlot(0)));
        }
    }
}
