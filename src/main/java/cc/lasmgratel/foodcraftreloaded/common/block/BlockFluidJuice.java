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

package cc.lasmgratel.foodcraftreloaded.common.block;

import cc.lasmgratel.foodcraftreloaded.common.item.food.fruit.FruitType;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import net.minecraft.block.material.Material;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class BlockFluidJuice extends BlockFluidClassic {
    private FruitType fruitType;

    public BlockFluidJuice(FruitType fruitType) {
        super(FluidRegistry.getFluid(NameBuilder.buildRegistryName(fruitType.toString(), "juice")), Material.WATER);
        this.fruitType = fruitType;
        setUnlocalizedName(NameBuilder.buildUnlocalizedName(fruitType.toString(), "juice"));
        setRegistryName(NameBuilder.buildRegistryName(fruitType.toString(), "juice"));
    }

    public FruitType getFruitType() {
        return fruitType;
    }

    @Nonnull
    @Override
    public String getLocalizedName() {
        return I18n.format("item.juice", Translator.format("item.fruit" + StringUtils.capitalize(fruitType.toString())));
    }
}
