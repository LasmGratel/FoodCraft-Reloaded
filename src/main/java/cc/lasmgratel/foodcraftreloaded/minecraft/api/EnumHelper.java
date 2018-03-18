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

package cc.lasmgratel.foodcraftreloaded.minecraft.api;

import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.tool.KitchenKnifeType;
import net.minecraft.item.ItemStack;

import java.awt.*;

public class EnumHelper extends net.minecraftforge.common.util.EnumHelper {

    public static KitchenKnifeType addKitchenKnifeType(String enumName, int maxDamage, Color knifeColor, ItemStack repairMaterial) {
        return addEnum(KitchenKnifeType.class, enumName, new Class[]{int.class, Color.class, ItemStack.class}, maxDamage, knifeColor, repairMaterial);
    }
}
