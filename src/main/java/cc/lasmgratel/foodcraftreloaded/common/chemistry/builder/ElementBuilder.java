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
import cc.lasmgratel.foodcraftreloaded.common.chemistry.FCRElement;

import javax.annotation.Nonnull;

public class ElementBuilder implements NamedBuilder<FCRElement> {
    private FCRElement element = new FCRElement();

    @Nonnull
    @Override
    public FCRElement build() {
        return element;
    }

    @Override
    public ElementBuilder withName(@Nonnull String name) {
        element.setName(name);
        return this;
    }
}
