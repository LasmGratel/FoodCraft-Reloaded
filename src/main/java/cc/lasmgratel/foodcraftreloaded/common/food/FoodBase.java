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

import cc.lasmgratel.foodcraftreloaded.api.food.Food;
import cc.lasmgratel.foodcraftreloaded.api.food.material.Material;
import cc.lasmgratel.foodcraftreloaded.common.util.AbstractNamedProperty;

import java.util.HashMap;
import java.util.Map;

public class FoodBase extends AbstractNamedProperty implements Food {
    private Map<Material, Integer> materialMap = new HashMap<>();
    private int healAmount = -1;

    @Override
    public int getHealAmount() {
        return healAmount == -1 ? (int) (getEnergy() / ENERGY_PER_HEAL_AMOUNT) : healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public Map<Material, Integer> getMaterialMap() {
        return materialMap;
    }
}
