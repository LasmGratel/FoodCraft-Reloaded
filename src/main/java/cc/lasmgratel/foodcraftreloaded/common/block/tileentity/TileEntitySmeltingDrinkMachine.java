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
import net.minecraft.tileentity.TileEntity;
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

public class TileEntitySmeltingDrinkMachine extends TileFluidHandler implements ITickable {
    public static final int BURN_TIME = 200;

    private ItemStackHandler itemStackHandler = new ItemStackHandler(3);
    private int progress = 0;
    private int fuel = 0;
    private ItemStack output = ItemStack.EMPTY;
    private FluidStack fluidStack = null;
    private int currentItemBurnTime = 0;

    public TileEntitySmeltingDrinkMachine() {
        tank.setCapacity(TileEntityDrinkMachine.FLUID_CAPACITY);
    }

    public static int getProgress(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntitySmeltingDrinkMachine)
            return BURN_TIME - ((TileEntitySmeltingDrinkMachine) tileEntity).progress;
        return 0;
    }

    public static int getFuelTime(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntitySmeltingDrinkMachine)
            return ((TileEntitySmeltingDrinkMachine) tileEntity).fuel;
        return 0;
    }

    public static int getCurrentItemBurnTime(TileEntity tileEntity) {
        if (tileEntity instanceof TileEntitySmeltingDrinkMachine)
            return ((TileEntitySmeltingDrinkMachine) tileEntity).currentItemBurnTime;
        return 0;
    }

    public static boolean isBurning(TileEntity tileEntity) {
        return tileEntity instanceof TileEntitySmeltingDrinkMachine && ((TileEntitySmeltingDrinkMachine) tileEntity).fluidStack != null;
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
        super.readFromNBT(tag.getCompoundTag("Tank"));
        CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().readNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemStackHandler, null, tag.getTag("Items"));
        progress = tag.getInteger("progress");
        fuel = tag.getInteger("fuel");
        output = new ItemStack(tag.getCompoundTag("output"));
        fluidStack = FluidStack.loadFluidStackFromNBT(tag.getCompoundTag("fluidOutput"));
        currentItemBurnTime = TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(2));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        tag.setTag("Items", CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, itemStackHandler, null));
        tag.setInteger("progress", progress);
        tag.setInteger("fuel", fuel);
        tag.setTag("output", output.serializeNBT());
        if (fluidStack != null)
            tag.setTag("fluidOutput", fluidStack.writeToNBT(new NBTTagCompound()));
        tag.setTag("Tank", super.writeToNBT(new NBTTagCompound()));
        return tag;
    }

    @Override
    public void update() {
        if (fluidStack != null) {
            if (fuel <= 0) {
                int burnTime = TileEntityFurnace.getItemBurnTime(itemStackHandler.getStackInSlot(2));
                if (burnTime != 0) {
                    FoodCraftReloaded.getLogger().info("Smelting drink machine gets fuel: " + burnTime);
                    fuel += burnTime;
                    itemStackHandler.getStackInSlot(2).splitStack(1);
                } else {
                    progress = 0;
                    return;
                }
            }
            if (progress > 0) {
                --progress;
                --fuel;
            } else {
                FoodCraftReloaded.getLogger().info("Progressed! " + fluidStack.getFluid().getName());
                progress = BURN_TIME;
                itemStackHandler.insertItem(1, output, false);
                tank.fill(fluidStack, true);
                output = ItemStack.EMPTY;
                fluidStack = null;
            }
        } else {
            DrinkRecipe recipe = RecipeManager.getInstance().getRecipeNullable(DrinkRecipe.class, new RecipeInput(itemStackHandler.getStackInSlot(0).getItem()));
            if (recipe != null) {
                FoodCraftReloaded.getLogger().info("Smelting machine: Found recipe " + recipe.getOutput().<FluidStack>first().getFluid().getName());
                fluidStack = recipe.getOutput().first();
                output = recipe.getOutput().second();
                itemStackHandler.getStackInSlot(0).splitStack(1);
                progress = BURN_TIME;
            }
        }
    }
}
