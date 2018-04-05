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

package cc.lasmgratel.foodcraftreloaded.api.chemistry.snapshot;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.EnergyDensely;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.EnergyReleasable;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.Matter;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.organic.OrganicMatter;

public class OrganicMatterSnapshot extends MatterSnapshot implements EnergyReleasable {
    @Override
    public void setMatter(Matter matter) {
        if (!(matter instanceof OrganicMatter))
            throw new IllegalArgumentException("Matter " + matter + " is not a organic matter!");
        super.setMatter(matter);
    }

    @Override
    public double getEnergy() {
        return ((EnergyDensely) getMatter()).getEnergyDensity() * getWeight();
    }
}
