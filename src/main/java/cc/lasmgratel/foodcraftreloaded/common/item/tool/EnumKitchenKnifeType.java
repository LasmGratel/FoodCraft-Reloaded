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

package cc.lasmgratel.foodcraftreloaded.common.item.tool;

import cc.lasmgratel.foodcraftreloaded.client.util.masking.Colorable;

import java.awt.Color;

public enum EnumKitchenKnifeType implements Colorable {
    STONE(100, new Color(128, 128, 128)),
    IRON(300, new Color(234, 234, 234)),
    GOLD(500, new Color(254, 254, 5)),
    DIAMOND(1000, new Color(154, 255, 243)),
    EMERALD(1750, new Color(0, 254, 0));

    private int maxDamage;
    private Color color;

    EnumKitchenKnifeType(int maxDamage, Color color) {
        this.maxDamage = maxDamage;
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
