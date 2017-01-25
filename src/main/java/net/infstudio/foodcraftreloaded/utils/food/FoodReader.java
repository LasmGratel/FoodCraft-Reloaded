/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.infstudio.foodcraftreloaded.utils.food;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.List;

/**
 * A reading helper to read food data.
 */
public class FoodReader {
    public static List<Food> readAllFoods(File configDir) {
        if(configDir.isFile()) {
            configDir.delete();
            configDir.mkdir();
        }
        File foodDir = new File(configDir, "foods");
        if(foodDir.isFile()) {
            foodDir.delete();
            foodDir.mkdir();
            // First run
            ResourceLocation location = new ResourceLocation("foodcraftreloaded", "foods/");

        }
        List<Food> foods = Lists.newArrayList();
        return foods;
    }

    private static Food readFood(ResourceLocation resource) {
        File file = new File(resource.getResourceDomain());
        return new Food();
    }
}
