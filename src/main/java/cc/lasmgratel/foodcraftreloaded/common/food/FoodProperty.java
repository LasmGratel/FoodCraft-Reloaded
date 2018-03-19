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

package cc.lasmgratel.foodcraftreloaded.common.food;

import cc.lasmgratel.foodcraftreloaded.common.material.Material;

/**
 * How food effect the player and the amplifier.
 * For instance, spice is a category of property of food.
 */
public interface FoodProperty {
    /**
     * Multiplier between 0.0~1.0.
     * Usually represents the multiplier of this property effected,
     * but it is possible to treat it for other usages.
     * The amount of property is given by {@link Material#getPropertyMap()}.
     */
    double getMultiplier();
}
