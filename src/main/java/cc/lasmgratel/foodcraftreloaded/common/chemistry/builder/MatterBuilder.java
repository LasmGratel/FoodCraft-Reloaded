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

package cc.lasmgratel.foodcraftreloaded.common.chemistry.builder;

import cc.lasmgratel.foodcraftreloaded.api.util.builder.NamedBuilder;
import cc.lasmgratel.foodcraftreloaded.common.chemistry.FCRMatter;

import javax.annotation.Nonnull;

public abstract class MatterBuilder<T extends FCRMatter, V extends MatterBuilder<T, V>> implements NamedBuilder<T> {
    protected T matter = newInstance();

    protected abstract T newInstance();

    public V withDensity(double density) {
        matter.setDensity(density);
        return (V) this;
    }

    public V withFormula(@Nonnull String formula) {
        matter.setChemicalFormula(formula);
        return (V) this;
    }

    public V withMeltingPoint(double meltingPoint) {
        matter.setMeltingPoint(meltingPoint);
        return (V) this;
    }

    @Override
    public V withName(@Nonnull String name) {
        matter.setName(name);
        return (V) this;
    }

    @Nonnull
    @Override
    public T build() {
        return matter;
    }
}
