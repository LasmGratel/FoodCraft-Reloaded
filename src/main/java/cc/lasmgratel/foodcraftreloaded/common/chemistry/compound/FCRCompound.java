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

package cc.lasmgratel.foodcraftreloaded.common.chemistry.compound;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.compound.Compound;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.molecular.Molecular;
import cc.lasmgratel.foodcraftreloaded.common.chemistry.FCRMatter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FCRCompound extends FCRMatter implements Compound {
    private Map<Molecular, Integer> components = new HashMap<>();

    @Override
    public Map<Molecular, Integer> getComponents() {
        return components;
    }

    public void setComponents(Map<Molecular, Integer> components) {
        this.components = components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FCRCompound)) return false;
        if (!super.equals(o)) return false;
        FCRCompound that = (FCRCompound) o;
        return Objects.equals(getComponents(), that.getComponents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getComponents());
    }

    @Override
    public String toString() {
        return "FCRCompound{" +
            "components=" + components +
            "} " + super.toString();
    }
}
