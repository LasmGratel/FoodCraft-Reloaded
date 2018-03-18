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
import cc.lasmgratel.foodcraftreloaded.minecraft.common.item.food.fruit.ItemFruit;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.loader.FruitEnumLoader;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.minecraft.common.util.enumeration.FruitTyped;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.common.IShearable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BlockFruitLeaves extends Block implements FruitTyped, IShearable, CustomModelMasking {
    private FruitType fruitType;

    public BlockFruitLeaves(FruitType fruitType) {
        super(Material.LEAVES);
        this.setTickRandomly(true);
        this.fruitType = fruitType;
        this.setHardness(0.2F);
        this.setLightOpacity(1);
        setRegistryName(FoodCraftReloadedMod.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "leaves"));
    }

    @Override
    public FruitType getType() {
        return fruitType;
    }

    public int quantityDroppedWithBonus(int fortune, @Nonnull Random random) {
        return MathHelper.clamp(this.quantityDropped(random) + random.nextInt(fortune + 1), 1, 4);
    }

    @Override
    public int quantityDropped(Random random) {
        return 2 + random.nextInt(3);
    }

    @Override
    @Nonnull
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return FoodCraftReloadedMod.getProxy().getLoaderManager().getLoader(FruitEnumLoader.class).get().getInstanceMap(ItemFruit.class).get(fruitType);
    }

    @Override
    @Nonnull
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return fruitType.ordinal();
    }

    @Override
    public boolean isLeaves(IBlockState state, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public boolean isShearable(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    @Override
    public List<ItemStack> onSheared(@Nonnull ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        return Collections.singletonList(new ItemStack(FoodCraftReloadedMod.getProxy().getLoaderManager().getLoader(FruitEnumLoader.class).get().getInstanceMap(ItemFruit.class).get(fruitType), MathHelper.ceil(quantityDroppedWithBonus(fortune, ((World) world).rand) * 1.5)));
    }

    @Nonnull
    @Override
    public Map<IBlockState, ModelResourceLocation> getStateModelLocations() {
        return Collections.singletonMap(getDefaultState(),
            new ModelResourceLocation(new ResourceLocation(FoodCraftReloadedMod.MODID, "fruit_leaves"), "normal"));
    }

    @Nullable
    @Override
    public IBlockColor getBlockColorMultiplier() {
        return (IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> {
            if (tintIndex == 0)
                return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
            else if (tintIndex == 1)
                return fruitType.getColor().getRGB();
            else
                return -1;
        };
    }
}
