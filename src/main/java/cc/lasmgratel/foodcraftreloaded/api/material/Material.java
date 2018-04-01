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

package cc.lasmgratel.foodcraftreloaded.api.material;

import cc.lasmgratel.foodcraftreloaded.api.food.FoodProperty;
import cc.lasmgratel.foodcraftreloaded.api.util.NamedProperty;
import com.google.common.util.concurrent.AtomicDouble;

import java.util.Map;

public interface Material extends NamedProperty {
    /**
     * Includes key and amount of a property.
     */
    Map<FoodProperty, Integer> getPropertyMap();

    default double calcMultiplier() {
        AtomicDouble multiplier = new AtomicDouble();
        getPropertyMap().forEach((foodProperty, integer) -> multiplier.getAndAdd(foodProperty.getMultiplier() * integer));
        return multiplier.get();
    }
}
