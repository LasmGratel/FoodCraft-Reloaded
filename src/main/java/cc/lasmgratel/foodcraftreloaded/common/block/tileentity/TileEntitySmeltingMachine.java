package cc.lasmgratel.foodcraftreloaded.common.block.tileentity;

import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 * Machine type that can use fuel to progress.
 */
public abstract class TileEntitySmeltingMachine extends TileEntityProgressiveMachine {
    private int fuel;
    private int currentFuelAmount;

    public TileEntitySmeltingMachine(int maxProgress) {
        super(maxProgress);
    }

    @Override
    public boolean canStart() {
        return fuel > 0;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void progress() {
        if (fuel <= 0)
            resetProgress();
        else
            --fuel;
    }

    public int getFuel() {
        return isStarted() ? fuel : 0;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public int getCurrentFuelAmount() {
        return isStarted() ? currentFuelAmount : 0;
    }

    public void setCurrentFuelAmount(int currentFuelAmount) {
        this.currentFuelAmount = currentFuelAmount;
    }
}
