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

import cc.lasmgratel.foodcraftreloaded.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.block.BlockVegetableCake;
import cc.lasmgratel.foodcraftreloaded.common.item.food.ItemDrink;
import cc.lasmgratel.foodcraftreloaded.common.loader.VegetableEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.VegetableTyped;
import cc.lasmgratel.foodcraftreloaded.client.util.masking.CustomModelMasking;
import net.minecraft.block.BlockCake;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemVegetableJuice extends ItemDrink implements VegetableTyped, CustomModelMasking {
    private VegetableType vegetableType;

    public ItemVegetableJuice(VegetableType vegetableType) {
        super(4, 1.0f);
        setRegistryName(FoodCraftReloaded.MODID, vegetableType.toString() + "_juice");
        this.vegetableType = vegetableType;
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.getBlockState(pos).getBlock() instanceof BlockCake) {
            worldIn.setBlockState(pos, FoodCraftReloaded.getLoader(VegetableEnumLoader.class).get().getInstanceMap(BlockVegetableCake.class).get(vegetableType).getDefaultState().withProperty(BlockCake.BITES, worldIn.getBlockState(pos).getValue(BlockCake.BITES)));
            player.setHeldItem(hand, new ItemStack(FCRItems.GLASS_BOTTLE));
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.juice",  Translator.format("item.vegetable" + StringUtils.capitalize(NameBuilder.buildUnlocalizedName(vegetableType.toString()))));
    }

    @Nullable
    @Override
    public ModelResourceLocation getModelLocation() {
        return new ModelResourceLocation(new ResourceLocation(FoodCraftReloaded.MODID, "juice"), "inventory");
    }

    @Override
    public int getTintIndex() {
        return 1;
    }

    @Override
    public VegetableType getType() {
        return vegetableType;
    }
}
