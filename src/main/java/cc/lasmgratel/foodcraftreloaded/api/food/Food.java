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

package cc.lasmgratel.foodcraftreloaded.api.food;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.EnergyReleasable;
import cc.lasmgratel.foodcraftreloaded.api.food.material.Material;
import cc.lasmgratel.foodcraftreloaded.api.util.NamedProperty;
import com.google.common.util.concurrent.AtomicDouble;

import javax.annotation.Nonnegative;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * A food which can be eaten by players.
 */
public interface Food extends NamedProperty, EnergyReleasable {
    /**
     * Unit in Joules(J)
     */
    double ENERGY_PER_HEAL_AMOUNT = 39451790.8512;

    /**
     * The amount food heals player.
     * In Minecraft, this is represented by "chicken leg" located in the right side of HUD.
     * Calculated from material and weight by default.
     */
    default int getHealAmount() {
        return (int) (getEnergy() / ENERGY_PER_HEAL_AMOUNT);
    }

    /**
     * Total energy released from this food.
     * Material energy are used as Joules per gram(J/g).
     */
    @Override
    default double getEnergy() {
        AtomicDouble totalEnergy = new AtomicDouble();
        getMaterialMap().forEach((material, weight) -> totalEnergy.addAndGet(material.getEnergy() * weight));
        return totalEnergy.get();
    }

    /**
     * How many time to eat this food.
     */
    @Nonnegative
    default long getDuration(TimeUnit unit) {
        return unit.convert(1600L * getWeight(), TimeUnit.MILLISECONDS);
    }

    /**
     * Materials and their weight.
     */
    Map<Material, Integer> getMaterialMap();

    /**
     * The weight of the food (in grams).
     * WARNING: Should be non-zero as it is used to calculate eating duration.
     */
    @Nonnegative
    default int getWeight() {
        return getMaterialMap().values().stream().mapToInt(Integer::intValue).sum();
    }
}
