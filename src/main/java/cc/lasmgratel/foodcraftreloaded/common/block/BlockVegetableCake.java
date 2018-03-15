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

import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.ItemVegetableCake;
import cc.lasmgratel.foodcraftreloaded.common.item.food.vegetable.VegetableType;
import cc.lasmgratel.foodcraftreloaded.common.loader.VegetableEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import net.minecraft.block.BlockCake;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class BlockVegetableCake extends BlockCake implements CustomModelMasking {
    private VegetableType vegetableType;

    public BlockVegetableCake(VegetableType vegetableType) {
        super();
        setDefaultState(getDefaultState().withProperty(BlockCake.BITES, 0));
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(vegetableType.toString(), "cake"));
        this.vegetableType = vegetableType;
    }

    @Nonnull
    @Override
    public Map<IBlockState, ModelResourceLocation> getStateModelLocations() {
        Map<IBlockState, ModelResourceLocation> map = new HashMap<>();
        for (int j = 0; j <= 6; j++)
            map.put(getDefaultState().withProperty(BlockCake.BITES, j), new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "fruit_cake"), "bites=" + j));
        return map;
    }

    @Nonnull
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(FoodCraftReloaded.getProxy().getLoaderManager().getLoader(VegetableEnumLoader.class).get().getInstanceMap(ItemVegetableCake.class).get(vegetableType));
    }

    @Override
    public int getTintIndex() {
        return 0;
    }

    public VegetableType getVegetableType() {
        return vegetableType;
    }

    public void setVegetableType(VegetableType fruitType) {
        this.vegetableType = fruitType;
    }
}
