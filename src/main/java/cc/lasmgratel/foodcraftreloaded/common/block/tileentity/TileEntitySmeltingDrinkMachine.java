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

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.api.recipe.DrinkRecipe;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeInput;
import cc.lasmgratel.foodcraftreloaded.api.recipe.RecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

public class TileEntitySmeltingDrinkMachine extends TileFluidHandler implements ITickable {
    public static final int BURN_TIME = 200;

    private ItemStackHandler itemStackHandler = new ItemStackHandler(3);
    private int progress = 0;
    private int fuel = 0;
    private ItemStack output = ItemStack.EMPTY;
    private FluidStack fluidStack = null;

    public TileEntitySmeltingDrinkMachine() {
        tank.setCapacity(TileEntityDrinkMachine.FLUID_CAPACITY);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) itemStackHandler : super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemStackHandler, null, tag.getTag("Items"));
        progress = tag.getInteger("progress");
        fuel = tag.getInteger("fuel");
        output = new ItemStack(tag.getCompoundTag("output"));
        fluidStack = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluidOutput"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setTag("Items", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemStackHandler, null));
        tag.setInteger("progress", progress);
        tag.setInteger("fuel", fuel);
        tag.setTag("output", output.serializeNBT());
        if (fluidStack != null)
            tag.setTag("fluidOutput", fluidStack.writeToNBT(new NBTTagCompound()));
        return super.writeToNBT(tag);
    }

    @Override
    public void update() {
        if (progress < BURN_TIME && fluidStack != null) {
            ++progress;
            --fuel;
        } else if (progress >= BURN_TIME) {
            progress = 0;
            itemStackHandler.insertItem(1, output, false);
            tank.fill(fluidStack, false);
            output = ItemStack.EMPTY;
            fluidStack = null;
            return;
        }
        if (fuel <= 0) {
            int burnTime = TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(2));
            if (burnTime != 0) {
                fuel += burnTime;
            } else
                progress = 0;
        }
        if (progress == 0) {
            DrinkRecipe recipe = RecipeManager.getInstance().getRecipeNullable(DrinkRecipe.class, new RecipeInput(itemStackHandler.getStackInSlot(0)));
            if (recipe != null) {
                FoodCraftReloaded.getLogger().info("Smelting machine: Found recipe " + Arrays.toString(recipe.getOutput().getValue()));
                fluidStack = recipe.getOutput().first();
                output = recipe.getOutput().second();
            }
        }
    }
}
