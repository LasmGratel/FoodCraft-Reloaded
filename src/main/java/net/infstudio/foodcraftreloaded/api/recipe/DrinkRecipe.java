package net.infstudio.foodcraftreloaded.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class DrinkRecipe extends Recipe {
    public DrinkRecipe(FluidStack output, int xp, @Nonnull ItemStack rubbish) {
        outputFluids.add(output);
        setXp(xp);
        outputStacks.add(rubbish);
    }

    public DrinkRecipe(FluidStack output) {
        outputFluids.add(output);
        outputStacks.add(ItemStack.EMPTY);
    }

    public FluidStack getOutput() {
        return outputFluids.get(0);
    }

    public void setOutput(FluidStack output) {
        outputFluids.set(0, output);
    }

    @Nonnull
    public ItemStack getRubbish() {
        return outputStacks.get(0);
    }

    public void setRubbish(@Nonnull ItemStack rubbish) {
        outputStacks.set(0, rubbish);
    }
}
