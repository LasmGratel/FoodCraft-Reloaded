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

package cc.lasmgratel.foodcraftreloaded.item.food.vegetable;

import cc.lasmgratel.foodcraftreloaded.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.item.food.ItemLiqueur;
import cc.lasmgratel.foodcraftreloaded.util.NameBuilder;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class ItemVegetableLiqueur extends ItemLiqueur implements VegetableTyped {
    private VegetableType type;

    public ItemVegetableLiqueur(VegetableType type) {
        super(5);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(type.toString(), "liqueur"));
        this.type = type;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return I18n.format("item.liqueur", I18n.format("item.vegetable" + StringUtils.capitalize(type.toString())));
    }

    @Override
    public VegetableType getVegetableType() {
        return type;
    }
}
