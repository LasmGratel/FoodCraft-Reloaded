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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.item.tool;

import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.OreDictated;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class ItemKitchenKnife extends Item implements CustomModelMasking, OreDictated {
    private KitchenKnifeWrapper type;

    public ItemKitchenKnife(KitchenKnifeWrapper type) {
        this.type = type;
        setMaxDamage(type.getMaterial().getMaxUses() * 10);
        setMaxStackSize(1);
        setRegistryName(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName("kitchen", "knife", type.getMaterial().name().toLowerCase()));
        setTranslationKey(NameBuilder.buildUnlocalizedName("kitchen", "knife", type.getMaterial().name().toLowerCase()));
        setCreativeTab(FCRCreativeTabs.BASE);
    }

    public KitchenKnifeWrapper getType() {
        return type;
    }

    public void setType(KitchenKnifeWrapper type) {
        this.type = type;
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloadedMod.MODID, "kitchen_knife"), "inventory");
    }

    @Override
    public int getTintIndex() {
        return 0;
    }

    @Override
    public String[] getOreDictNames() {
        return new String[]{"knifeKitchen"};
    }
}
