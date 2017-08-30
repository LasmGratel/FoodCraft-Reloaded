package net.infstudio.foodcraftreloaded.block.tileentity;

import net.infstudio.foodcraftreloaded.api.recipe.DrinkRecipe;
import net.infstudio.foodcraftreloaded.api.recipe.DrinkRecipeManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.TileFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityDrinkMachine extends TileFluidHandler implements ITickable {
    private static final int DRINK_ENERGY_NEED = 500;

    private IItemHandlerModifiable itemHandler = new ItemStackHandler(2);
    private EnergyStorage energyStorage = new EnergyStorage(2000);
    private FluidTank fluidTank = new FluidTank(1000);

    private int energyTaken = 0;

    @Nullable
    private DrinkRecipe currentRecipe = null;

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
        else if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) fluidTank;
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
        if (currentRecipe != null) {
            if (energyTaken == DRINK_ENERGY_NEED) {
                itemHandler.extractItem(0, 1, false);
                itemHandler.insertItem(1, currentRecipe.getRubbish(), false);
                fluidTank.fill(currentRecipe.getOutput(), true);
                currentRecipe = null;
            } else if (energyStorage.getEnergyStored() > 0) {
                energyStorage.extractEnergy(1, false);
                ++energyTaken;
            } else {
                currentRecipe = null;
            }
        } else {
            currentRecipe = DrinkRecipeManager.getInstance().getRecipeNullable(itemHandler.getStackInSlot(0));
        }
    }
}
