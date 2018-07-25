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

package cc.lasmgratel.foodcraftreloaded.minecraft.common.block;

import cc.lasmgratel.foodcraftreloaded.minecraft.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.FoodCraftReloadedMod;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.FruitType;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration.FruitTyped;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlockFruitSapling extends BlockSapling implements FruitTyped, CustomModelMasking {
    private FruitType fruitType;

    public BlockFruitSapling(FruitType fruitType) {
        this.fruitType = fruitType;
        setRegistryName(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "sapling"));
        setTranslationKey(NameBuilder.buildUnlocalizedName(fruitType.toString(), "sapling"));
    }

    @Override
    public FruitType getType() {
        return fruitType;
    }

    @Nonnull
    @Override
    public Map<IBlockState, ModelResourceLocation> getStateModelLocations() {
        Map<IBlockState, ModelResourceLocation> locationMap = new HashMap<>();
        for (int i : BlockSapling.STAGE.getAllowedValues())
            locationMap.put(getDefaultState().withProperty(BlockSapling.STAGE, i),
                new ModelResourceLocation(new ResourceLocation(FoodCraftReloadedMod.MODID, "fruit_sapling"), "normal"));
        return locationMap;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, @Nonnull NonNullList<ItemStack> items) {
        items.add(new ItemStack(this).setStackDisplayName(Translator.format("item.foodcraftreloaded.sapling", Translator.format(NameBuilder.buildUnlocalizedName("item.fruit", fruitType.toString())))));
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloadedMod.MODID, "fruit_sapling"), "normal");
    }

    @Override
    public int getTintIndex() {
        return 0;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return worldIn.rand.nextDouble() < 0.7;
    }
}
