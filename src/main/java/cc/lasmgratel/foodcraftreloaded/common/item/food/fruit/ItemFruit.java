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

import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.common.item.food.FCRItemFood;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.OreDictated;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.FruitTyped;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemFruit extends FCRItemFood implements FruitTyped, CustomModelMasking, OreDictated {
    private FruitType fruitType;

    public ItemFruit(FruitType fruitType) {
        super(1, 1.0f, false);
        setAlwaysEdible(true);
        setRegistryName(FoodCraftReloaded.MODID, fruitType.toString());
        setCreativeTab(FCRCreativeTabs.INGREDIENTS);

        MinecraftForge.addGrassSeed(new ItemStack(this), 6);

        this.fruitType = fruitType;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.fruit" + StringUtils.capitalize(fruitType.toString()));
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("fruit", fruitType.toString())), "inventory");
    }

    @Nullable
    @Override
    public String[] getOreDictNames() {
        return new String[]{NameBuilder.buildUnlocalizedName("crop", fruitType.toString()), "listAllfruit"};
    }

    @Override
    public FruitType getType() {
        return fruitType;
    }
}
