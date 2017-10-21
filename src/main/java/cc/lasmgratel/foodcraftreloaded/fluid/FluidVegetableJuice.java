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

package cc.lasmgratel.foodcraftreloaded.fluid;

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.item.food.vegetable.VegetableType;
import cc.lasmgratel.foodcraftreloaded.util.NameBuilder;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidVegetableJuice extends Fluid {
    private VegetableType fruitType;

    public FluidVegetableJuice(VegetableType vegetableType) {
        super(NameBuilder.buildRegistryName(vegetableType.toString(), "juice"), new ResourceLocation(FoodCraftReloaded.MODID, "fluids/juice_still"),  new ResourceLocation(FoodCraftReloaded.MODID, "fluids/juice_flow"));
        this.fruitType = vegetableType;
        setViscosity(2000);

    }

    @Override
    public int getColor() {
        return fruitType.getColor().getRGB();
    }
}
