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

package cc.lasmgratel.foodcraftreloaded.common.chemistry.molecular;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.Element;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.molecular.Molecular;
import cc.lasmgratel.foodcraftreloaded.common.chemistry.FCRMatter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FCRMolecular extends FCRMatter implements Molecular {
    private Set<Element> elements = new HashSet<>();

    @Override
    public Set<Element> getElements() {
        return elements;
    }

    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FCRMolecular)) return false;
        if (!super.equals(o)) return false;
        FCRMolecular that = (FCRMolecular) o;
        return Objects.equals(getElements(), that.getElements());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getElements());
    }

    @Override
    public String toString() {
        return "FCRMolecular{" +
            "elements=" + elements +
            "} " + super.toString();
    }
}
