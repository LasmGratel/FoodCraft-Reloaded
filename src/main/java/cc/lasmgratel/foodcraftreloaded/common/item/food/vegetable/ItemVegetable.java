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

import cc.lasmgratel.foodcraftreloaded.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.block.BlockVegetableCrop;
import cc.lasmgratel.foodcraftreloaded.common.item.food.FCRItemFood;
import cc.lasmgratel.foodcraftreloaded.common.loader.VegetableEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.OreDictated;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.VegetableTyped;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.MinecraftForge;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemVegetable extends FCRItemFood implements VegetableTyped, CustomModelMasking, IPlantable, OreDictated {
    private VegetableType vegetableType;

    public ItemVegetable(VegetableType vegetableType) {
        super(1, 1.0f, false);
        setAlwaysEdible(true);
        setRegistryName(FoodCraftReloaded.MODID, vegetableType.toString());
        setCreativeTab(FCRCreativeTabs.INGREDIENTS);

        MinecraftForge.addGrassSeed(new ItemStack(this), 8);

        this.vegetableType = vegetableType;
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.vegetable" + StringUtils.capitalize(NameBuilder.buildUnlocalizedName(vegetableType.toString())));
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName("vegetable", vegetableType.toString())), "inventory");
    }

    @Override
    public VegetableType getType() {
        return vegetableType;
    }

    @Nullable
    @Override
    public String[] getOreDictNames() {
        return new String[]{NameBuilder.buildUnlocalizedName("crop", vegetableType.toString()), "listAllveggie"};
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.getHeldItem(hand);
        IBlockState state = worldIn.getBlockState(pos);
        if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up())) {
            worldIn.setBlockState(pos.up(), getPlant(worldIn, pos), 11);
            itemstack.shrink(1);
            return EnumActionResult.SUCCESS;
        }
        else
            return EnumActionResult.FAIL;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return FoodCraftReloaded.getLoader(VegetableEnumLoader.class).get().getInstanceMap(BlockVegetableCrop.class).get(vegetableType).getDefaultState();
    }
}
