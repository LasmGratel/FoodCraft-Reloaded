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

package cc.lasmgratel.foodcraftreloaded.block;

import cc.lasmgratel.foodcraftreloaded.item.food.vegetable.VegetableType;
import cc.lasmgratel.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nonnull;

public class BlockFluidVegetableJuice extends BlockFluidClassic {
    private VegetableType vegetableType;

    public BlockFluidVegetableJuice(VegetableType vegetableType) {
        super(FluidRegistry.getFluid(NameBuilder.buildRegistryName(vegetableType.toString(), "juice")), Material.WATER);
        this.vegetableType = vegetableType;
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(vegetableType.toString(), "juice"));
        setRegistryName(NameBuilder.buildRegistryName(vegetableType.toString(), "juice"));
    }

    public VegetableType getVegetableType() {
        return vegetableType;
    }

    @Nonnull
    @Override
    public String getLocalizedName() {
        return I18n.format("item.vegetableJuice", net.minecraft.client.resources.I18n.format(NameBuilder.buildRegistryName("item.vegetable", vegetableType.toString())));
    }
}
