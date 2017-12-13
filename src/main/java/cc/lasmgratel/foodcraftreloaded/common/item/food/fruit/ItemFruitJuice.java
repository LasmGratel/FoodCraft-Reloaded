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

package cc.lasmgratel.foodcraftreloaded.common.item.food.fruit;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.ItemDrink;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.FruitTyped;
import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import net.minecraft.item.ItemStack;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class ItemFruitJuice extends ItemDrink implements FruitTyped, CustomModelMasking {
    private FruitType fruitType;

    public ItemFruitJuice(FruitType fruitType) {
        super(4, 1.0f);
        setRegistryName(FoodCraftReloaded.MODID, fruitType.toString() + "_juice");
        this.fruitType = fruitType;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.juice", Translator.format("item.fruit" + StringUtils.capitalize(fruitType.toString())));
    }

    @Override
    public FruitType getType() {
        return fruitType;
    }

    @Override
    public int getTintIndex() {
        return 1;
    }
}
