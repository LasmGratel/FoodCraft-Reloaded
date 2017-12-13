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

package cc.lasmgratel.foodcraftreloaded.common.util.item;

import cc.lasmgratel.foodcraftreloaded.common.item.food.ItemLiqueur;
import cc.lasmgratel.foodcraftreloaded.common.item.food.LiqueurType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface LiqueurUtils {
    static <T extends ItemLiqueur> List<T> generateLiqueurs(T liqueur) {
        List<T> liqueurs = new ArrayList<>(LiqueurType.values().length - 1);
        for (LiqueurType type : LiqueurType.values())
            if (liqueur.getLiqueurType() != type) {
                T generated = (T) liqueur.clone();
                generated.setLiqueurType(type);
                try {
                    generated.getClass().getField("registryName").setAccessible(true);
                    generated.getClass().getField("registryName").set(generated, new ResourceLocation(liqueur.getRegistryName().getResourceDomain(), type.name().toLowerCase() + "_" + liqueur.getRegistryName().getResourcePath()));
                } catch (Exception e) {
                    throw new RuntimeException("This shouldn't be happened", e);
                }
                generated.setUnlocalizedName(liqueur.getUnlocalizedName());
                liqueurs.add(generated);
            }
        return liqueurs;
    }

    static <K, V extends ItemLiqueur> Map<K, V> generateLiqueurMap(Map<K, V> sourceMap) {
        Map<K, V> liqueurs = new HashMap<>((LiqueurType.values().length - 1) * sourceMap.size());
        sourceMap.forEach((k, v) -> generateLiqueurs(v).forEach(liqueur -> liqueurs.put(k, liqueur)));
        return liqueurs;
    }
}
