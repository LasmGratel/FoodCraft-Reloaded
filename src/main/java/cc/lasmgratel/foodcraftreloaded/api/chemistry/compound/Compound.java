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

package cc.lasmgratel.foodcraftreloaded.api.chemistry.compound;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.Matter;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.molecule.Molecule;

import java.util.List;

public interface Compound extends Matter {
    /**
     * Molecules contained in this compound.
     */
    List<Molecule> getMolecules();

    @Override
    default String getFormula() {
        StringBuilder stringBuilder = new StringBuilder();
        getMolecules().stream().map(Matter::getFormula).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
