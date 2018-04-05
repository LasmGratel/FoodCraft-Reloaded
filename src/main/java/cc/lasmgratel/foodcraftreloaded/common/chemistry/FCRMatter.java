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

package cc.lasmgratel.foodcraftreloaded.common.chemistry;

import cc.lasmgratel.foodcraftreloaded.api.chemistry.Matter;
import cc.lasmgratel.foodcraftreloaded.common.chemistry.state.AbstractMeltable;

import javax.annotation.Nonnull;
import java.util.Objects;

public class FCRMatter extends AbstractMeltable implements Matter {
    private double density;
    private boolean chemicalFormulated;
    private String formula;

    @Override
    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    @Override
    public boolean isChemicalFormulated() {
        return chemicalFormulated;
    }

    public void setChemicalFormulated(boolean chemicalFormulated) {
        this.chemicalFormulated = chemicalFormulated;
    }

    @Nonnull
    @Override
    public String getChemicalFormula() {
        return formula;
    }

    public void setChemicalFormula(@Nonnull String chemicalFormula) {
        this.formula = chemicalFormula;
        if (!chemicalFormula.isEmpty())
            setChemicalFormulated(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FCRMatter)) return false;
        if (!super.equals(o)) return false;
        FCRMatter fcrMatter = (FCRMatter) o;
        return Double.compare(fcrMatter.getDensity(), getDensity()) == 0 &&
            isChemicalFormulated() == fcrMatter.isChemicalFormulated() &&
            Objects.equals(getChemicalFormula(), fcrMatter.getChemicalFormula());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDensity(), isChemicalFormulated(), getChemicalFormula());
    }

    @Override
    public String toString() {
        return "FCRMatter{" +
            "density=" + density +
            ", chemicalFormulated=" + chemicalFormulated +
            ", formula='" + formula + '\'' +
            "} " + super.toString();
    }
}
