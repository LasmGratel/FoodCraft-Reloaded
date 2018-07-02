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

package cc.lasmgratel.foodcraftreloaded.common.material;

import cc.lasmgratel.foodcraftreloaded.api.food.material.Material;
import cc.lasmgratel.foodcraftreloaded.common.util.AbstractNamedProperty;

import java.util.Objects;

public class MaterialBase extends AbstractNamedProperty implements Material {
    private double energy;

    @Override
    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaterialBase)) return false;
        if (!super.equals(o)) return false;
        MaterialBase that = (MaterialBase) o;
        return Double.compare(that.getEnergy(), getEnergy()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEnergy());
    }

    @Override
    public String toString() {
        return "MaterialBase{" +
            "energy=" + energy +
            "} " + super.toString();
    }
}
