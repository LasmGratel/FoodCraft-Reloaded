package net.infstudio.foodcraftreloaded.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fluids.FluidStack;

public abstract class Recipe {
    protected final NonNullList<ItemStack> inputStacks = NonNullList.create();
    protected final NonNullList<ItemStack> outputStacks = NonNullList.create();
    protected final NonNullList<FluidStack> inputFluids = NonNullList.create();
    protected final NonNullList<FluidStack> outputFluids = NonNullList.create();
    private float xp = 0f;

    public float getXp() {
        return xp;
    }

    public void setXp(float xp) {
        this.xp = xp;
    }
}
