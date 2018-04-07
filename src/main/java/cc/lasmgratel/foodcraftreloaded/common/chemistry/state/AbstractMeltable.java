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

package cc.lasmgratel.foodcraftreloaded.common.chemistry.state;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.state.Meltable;
import cc.lasmgratel.foodcraftreloaded.common.util.AbstractNamedProperty;

import javax.annotation.Nonnegative;
import java.util.Objects;

public abstract class AbstractMeltable extends AbstractNamedProperty implements Meltable {
    private boolean meltingPointFixed = false;
    private double meltingPoint = -1;

    @Override
    public boolean isMeltingPointFixed() {
        return meltingPointFixed;
    }

    public void setMeltingPointFixed(boolean meltingPointFixed) {
        this.meltingPointFixed = meltingPointFixed;
    }

    @Override
    public double getMeltingPoint() {
        return meltingPoint;
    }

    public void setMeltingPoint(@Nonnegative double meltingPoint) {
        this.meltingPoint = meltingPoint;
        if (meltingPoint > 0)
            setMeltingPointFixed(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractMeltable)) return false;
        AbstractMeltable that = (AbstractMeltable) o;
        return isMeltingPointFixed() == that.isMeltingPointFixed() &&
            Double.compare(that.getMeltingPoint(), getMeltingPoint()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMeltingPointFixed(), getMeltingPoint());
    }

    @Override
    public String toString() {
        return "AbstractMeltable{" +
            "meltingPointFixed=" + meltingPointFixed +
            ", meltingPoint=" + meltingPoint +
            '}';
    }
}
