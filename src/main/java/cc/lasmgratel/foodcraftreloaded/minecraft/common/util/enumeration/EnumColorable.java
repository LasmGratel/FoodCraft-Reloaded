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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration;

import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.Colorable;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import net.minecraft.client.renderer.color.IItemColor;

import javax.annotation.Nullable;
import java.awt.*;

public interface EnumColorable<T extends Enum<T> & Colorable> extends CustomModelMasking, EnumTyped<T> {
    default Color getColor(int tintIndex) {
        if (getType() != null)
            return getType().getColor();
        else
            return Color.black;
    }

    @Nullable
    @Override
    default IItemColor getItemColorMultiplier() {
        return (stack, tintIndex) -> getColor(tintIndex).getRGB();
    }
}
