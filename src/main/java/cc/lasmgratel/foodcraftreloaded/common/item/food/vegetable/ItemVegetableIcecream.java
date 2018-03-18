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

package cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable;

import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.common.item.food.FCRItemFood;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.VegetableTyped;
import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemVegetableIcecream extends FCRItemFood implements VegetableTyped, CustomModelMasking {
    private VegetableType vegetableType;

    public ItemVegetableIcecream(VegetableType vegetableType) {
        super(5, 1.0f, false);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(vegetableType.toString(), "icecream"));
        setCreativeTab(FCRCreativeTabs.SNACK);
        this.vegetableType = vegetableType;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.iceCream", Translator.format("item.vegetable" + StringUtils.capitalize(NameBuilder.buildUnlocalizedName(vegetableType.toString()))));
    }

    @Override
    public VegetableType getType() {
        return vegetableType;
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "ice_cream"), "inventory");
    }

    @Override
    public int getTintIndex() {
        return 1;
    }
}
