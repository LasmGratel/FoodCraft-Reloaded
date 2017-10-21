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

package cc.lasmgratel.foodcraftreloaded.api.recipe;

import java.util.Arrays;

public class RecipeInput {
    protected Object[] value;

    public RecipeInput(Object... value) {
        this.value = value;
    }

    public <T> T[] getValue() {
        return (T[]) value;
    }

    public <T> T first() {
        return (T) value[0];
    }

    public <T> T second() {
        return (T) value[1];
    }

    public <T> T third() {
        return (T) value[2];
    }

    public RecipeInput setValue(Object... value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeInput that = (RecipeInput) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(value);
    }
}
