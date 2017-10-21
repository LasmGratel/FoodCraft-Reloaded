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

public class SafeRecipeOutput<T> extends RecipeOutput {
    @SafeVarargs
    public SafeRecipeOutput(T... value) {
        this.value = value;
    }

    @Override
    public T[] getValue() {
        return (T[]) value;
    }

    @Override
    public T first() {
        return (T) value[0];
    }

    @Override
    public T second() {
        return (T) value[1];
    }

    @Override
    public T third() {
        return (T) value[2];
    }
}
