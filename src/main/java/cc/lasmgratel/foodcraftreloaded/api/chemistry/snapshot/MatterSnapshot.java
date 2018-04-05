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

package cc.lasmgratel.foodcraftreloaded.api.chemistry.snapshot;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.Matter;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.Volumetric;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.Weighable;
import cc.lasmgratel.foodcraftreloaded.api.chemistry.state.MatterState;

import java.util.Objects;

/**
 * Contains a snapshot of matter, includes its current state and other properties.
 */
public class MatterSnapshot implements Volumetric, Weighable {
    private Matter matter;
    private MatterState state;
    private double weight, volume;

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public MatterState getState() {
        return state;
    }

    public void setState(MatterState state) {
        this.state = state;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
        if (this.weight != 0)
            setVolume(matter.getDensity() / weight);
    }

    @Override
    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
        setWeight(volume * matter.getDensity());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatterSnapshot)) return false;
        MatterSnapshot that = (MatterSnapshot) o;
        return Double.compare(that.getWeight(), getWeight()) == 0 &&
            Double.compare(that.getVolume(), getVolume()) == 0 &&
            Objects.equals(getMatter(), that.getMatter()) &&
            getState() == that.getState();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatter(), getState(), getWeight(), getVolume());
    }
}
