package cc.lasmgratel.foodcraftreloaded.common.chemistry;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.EnergyReleasable;
import cc.lasmgratel.foodcraftreloaded.common.util.AbstractNamedProperty;

public abstract class AbstractEnergyReleasable extends AbstractNamedProperty implements EnergyReleasable {
    private double energy;

    @Override
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }
}
