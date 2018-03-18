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

import cc.lasmgratel.foodcraftreloaded.api.init.FCRItems;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.block.BlockFruitCake;
import cc.lasmgratel.foodcraftreloaded.common.item.food.ItemDrink;
import cc.lasmgratel.foodcraftreloaded.common.item.food.Juice;
import cc.lasmgratel.foodcraftreloaded.common.loader.FruitEnumLoader;
import cc.lasmgratel.foodcraftreloaded.common.util.NameBuilder;
import cc.lasmgratel.foodcraftreloaded.common.util.Translator;
import cc.lasmgratel.foodcraftreloaded.common.util.enumeration.FruitTyped;
import net.minecraft.block.BlockCake;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

public class ItemFruitJuice extends ItemDrink implements FruitTyped, Juice {
    private FruitType fruitType;

    public ItemFruitJuice(FruitType fruitType) {
        super(4, 1.0f);
        setRegistryName(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "juice"));
        this.fruitType = fruitType;
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (worldIn.getBlockState(pos).getBlock() instanceof BlockCake) {
            worldIn.setBlockState(pos, FoodCraftReloaded.getLoader(FruitEnumLoader.class).get().getInstanceMap(BlockFruitCake.class).get(fruitType).getDefaultState().withProperty(BlockCake.BITES, worldIn.getBlockState(pos).getValue(BlockCake.BITES)));
            player.setHeldItem(hand, new ItemStack(FCRItems.GLASS_BOTTLE));
            return EnumActionResult.SUCCESS;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return Translator.format("item.juice", Translator.format("item.fruit" + StringUtils.capitalize(fruitType.toString())));
    }

    @Override
    public String[] getOreDictNames() {
        return ArrayUtils.addAll(super.getOreDictNames(), "listAlljuice");
    }

    @Override
    public FruitType getType() {
        return fruitType;
    }
}
