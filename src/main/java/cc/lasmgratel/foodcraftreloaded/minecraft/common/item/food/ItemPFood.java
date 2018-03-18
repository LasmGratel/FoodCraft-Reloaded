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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.Validate;

import static net.minecraft.util.math.MathHelper.ceil;

public class ItemPFood extends FCRItemFood {
    private float[] properties;

    public ItemPFood() {
        super(1, 1.0f, true);
    }

    public float[] getProperties() {
        return properties;
    }

    public void setProperties(float... properties) {
        Validate.validIndex(ArrayUtils.toObject(properties), 4);
        this.properties = properties;
    }

    public void calcHealAmount() {
        float sour = properties[0] * -10f;
        float sweet = properties[1] * 30f;
        float bitter = properties[2] * -15f;
        float spice = properties[3] * 10f;
        float salty = properties[4] * 5f;
        setHealAmount(ceil(sour) + ceil(sweet) + ceil(bitter) + ceil(spice) + ceil(salty));
    }
}
