package net.infstudio.foodcraftreloaded.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class DrinkRecipe {
    private FluidStack output;

    @Nonnull
    private ItemStack rubbish;

    private float xp = 1;

    public DrinkRecipe(FluidStack output, int xp, @Nonnull ItemStack rubbish) {
        this.output = output;
        this.xp = xp;
        this.rubbish = rubbish;
    }

    public DrinkRecipe(FluidStack output) {
        this.output = output;
        this.rubbish = ItemStack.EMPTY;
    }

    public float getXp() {
        return xp;
    }

    public void setXp(float xp) {
        this.xp = xp;
    }

    public FluidStack getOutput() {
        return output;
    }

    public void setOutput(FluidStack output) {
        this.output = output;
    }

    @Nonnull
    public ItemStack getRubbish() {
        return rubbish;
    }

    public void setRubbish(@Nonnull ItemStack rubbish) {
        this.rubbish = rubbish;
    }
}
