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

import cc.lasmgratel.foodcraftreloaded.api.food.FoodProperty;
import cc.lasmgratel.foodcraftreloaded.api.util.builder.NamedBuilder;

import javax.annotation.Nonnull;

public class MaterialBuilder implements NamedBuilder<MaterialBase> {
    private MaterialBase materialBase = new MaterialBase();

    @Override
    public MaterialBuilder withName(@Nonnull String name) {
        materialBase.setName(name);
        return this;
    }

    public MaterialBuilder withProperty(FoodProperty property, int value) {
        materialBase.getPropertyMap().put(property, value);
        return this;
    }

    @Override
    public MaterialBase build() {
        return materialBase;
    }
}
