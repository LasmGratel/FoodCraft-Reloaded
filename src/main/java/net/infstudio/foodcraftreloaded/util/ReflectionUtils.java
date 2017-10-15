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

package net.infstudio.foodcraftreloaded.util;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;

import javax.annotation.Nullable;

public interface ReflectionUtils {
    @SuppressWarnings("unchecked")
    @Nullable
    static <K, V> K getFieldIn(Class<V> clz, String fieldName, @Nullable V clzObj) {
        try {
            return (K) clz.getDeclaredField(fieldName).get(clzObj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            FoodCraftReloaded.getLogger().error("[FoodCraft]Cannot access field " + clz + "#" + fieldName + "!", e);
        }
        return null;
    }

    static <T> void setField(Class<T> clz, String fieldName, Object objToSet, @Nullable T clzObj) {
        try {
            clz.getDeclaredField(fieldName).set(clzObj, objToSet);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            FoodCraftReloaded.getLogger().error("[FoodCraft]Cannot access field " + clz + "#" + fieldName + "!", e);
        }
    }
}
