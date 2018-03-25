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

package cc.lasmgratel.foodcraftreloaded.minecraft.extra.item;

import cc.lasmgratel.foodcraftreloaded.minecraft.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.FCRItemFood;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.FruitType;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.ItemFruitIcecream;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.loader.FruitEnumLoader;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * For Cyl18~
 */
public class ItemCY extends FCRItemFood implements CustomModelMasking {
    public ItemCY() {
        super(4, false);
        setRegistryName(FoodCraftReloadedMod.MODID, "cyl18");
        setUnlocalizedName("cyl18");
        setCreativeTab(FCRCreativeTabs.EXTRA);
        setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
        NBTTagCompound compound = stack.getOrCreateSubCompound("cyl");
        if (compound.getInteger("time") == 29) {
            if (compound.getInteger("value") == FruitType.values().length - 1)
                compound.setInteger("value", 0);
            else
                compound.setInteger("value", compound.getInteger("value") + 1);
            compound.setInteger("time", 0);
        } else {
            compound.setInteger("time", compound.getInteger("time") + 1);
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Translator.format("item.cyl18.lore"));
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloadedMod.MODID, "ice_cream"), "inventory");
    }

    @Override
    public ItemMeshDefinition getItemMeshDefinition() {
        return stack -> {
            if (stack.getSubCompound("cyl") != null)
                return FoodCraftReloadedMod.getLoader(FruitEnumLoader.class).get().getInstanceMap(ItemFruitIcecream.class)
                    .get(FruitType.values()[stack.getSubCompound("cyl").getInteger("value")]).getModelLocation();
            return new ModelResourceLocation("");
        };
    }

    @Nullable
    @Override
    public IItemColor getItemColorMultiplier() {
        return (stack, tintIndex) -> {
            if (stack.getSubCompound("cyl") == null)
                stack.getOrCreateSubCompound("cyl");
            return tintIndex == 1 ? FoodCraftReloadedMod.getLoader(FruitEnumLoader.class).get().getInstanceMap(ItemFruitIcecream.class)
            .get(FruitType.values()[stack.getSubCompound("cyl").getInteger("value")]).getType().getColor().getRGB() : -1; };
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
