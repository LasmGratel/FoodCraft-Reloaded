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

package net.infstudio.foodcraftreloaded.item.food;

import net.infstudio.foodcraftreloaded.FoodCraftReloaded;
import net.infstudio.foodcraftreloaded.init.FCRCreativeTabs;
import net.infstudio.foodcraftreloaded.util.NameBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemCakes extends FCRItemFood {
    public ItemCakes() {
        super(4, 1.0f, false);
        setRegistryName(FoodCraftReloaded.MODID, "cake");
        setCreativeTab(FCRCreativeTabs.INGREDIENTS);
        setMaxDamage(0);
        setHasSubtypes(true);
    }

    @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        FruitType fruitType = FruitType.values()[player.getHeldItem(hand).getMetadata()];
        Block cake = Block.REGISTRY.getObject(new ResourceLocation(FoodCraftReloaded.MODID, NameBuilder.buildRegistryName(fruitType.toString(), "cake")));

        if (block == Blocks.SNOW_LAYER && iblockstate.getValue(BlockSnow.LAYERS) < 1)
            facing = EnumFacing.UP;
        else if (!block.isReplaceable(worldIn, pos))
            pos = pos.offset(facing);

        ItemStack itemstack = player.getHeldItem(hand);

        if (!itemstack.isEmpty() && player.canPlayerEdit(pos, facing, itemstack) && worldIn.mayPlace(cake, pos, false, facing, null)) {
            IBlockState iblockstate1 = cake.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, player, hand);

            if (!worldIn.setBlockState(pos, iblockstate1, 11))
                return EnumActionResult.FAIL;
            else {
                iblockstate1 = worldIn.getBlockState(pos);

                if (iblockstate1.getBlock() == cake) {
                    ItemBlock.setTileEntityNBT(worldIn, player, pos, itemstack);
                    iblockstate1.getBlock().onBlockPlacedBy(worldIn, pos, iblockstate1, player, itemstack);
                }

                SoundType soundtype = iblockstate1.getBlock().getSoundType(iblockstate1, worldIn, pos, player);
                worldIn.playSound(player, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                itemstack.shrink(1);
                return EnumActionResult.SUCCESS;
            }
        }
        else
            return EnumActionResult.FAIL;
    }

    @Nonnull
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, @Nullable World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer)entityLiving;
            entityplayer.getFoodStats().addStats(this, stack);
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onFoodEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }
        return ItemStack.EMPTY;
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems) {
        for (FruitType fruitType : FruitType.values())
            subItems.add(new ItemStack(this, 1, fruitType.ordinal()));
    }

    @Nonnull
    @Override
    public String getItemStackDisplayName(@Nonnull ItemStack stack) {
        return I18n.format("item.cake", I18n.format("item.fruit" + StringUtils.capitalize(FruitType.values()[stack.getMetadata()].toString())));
    }
}
