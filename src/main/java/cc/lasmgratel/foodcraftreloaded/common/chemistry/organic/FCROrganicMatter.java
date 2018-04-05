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

package cc.lasmgratel.foodcraftreloaded.common.chemistry.organic;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.organic.OrganicMatter;
import cc.lasmgratel.foodcraftreloaded.common.chemistry.FCRMatter;

import java.util.Objects;

public class FCROrganicMatter extends FCRMatter implements OrganicMatter {
    private double energyDensity;

    @Override
    public double getEnergyDensity() {
        return energyDensity;
    }

    public void setEnergyDensity(double energyDensity) {
        this.energyDensity = energyDensity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FCROrganicMatter)) return false;
        if (!super.equals(o)) return false;
        FCROrganicMatter that = (FCROrganicMatter) o;
        return Double.compare(that.getEnergyDensity(), getEnergyDensity()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEnergyDensity());
    }

    @Override
    public String toString() {
        return "FCROrganicMatter{" +
            "energyDensity=" + energyDensity +
            "} " + super.toString();
    }
}
