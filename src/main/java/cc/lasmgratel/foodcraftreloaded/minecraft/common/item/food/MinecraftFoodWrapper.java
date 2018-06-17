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

import cc.lasmgratel.foodcraftreloaded.api.food.Food;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Wraps a Food API to Minecraft.
 */
public class MinecraftFoodWrapper extends FCRItemFood {
    private Food food;

    /**
     * Creates a food item from the Food API.
     * @param food the Food API
     */
    public MinecraftFoodWrapper(Food food) {
        this.food = food;
        setRegistryName(food.getName());
        setUnlocalizedName(food.getName());
        setHealAmount(food.getHealAmount());
        setItemUseDuration((int) (food.getDuration(TimeUnit.MILLISECONDS) / 50));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        // TODO I18n Material Map
        tooltip.add(food.getWeight() + "g");
    }
}
