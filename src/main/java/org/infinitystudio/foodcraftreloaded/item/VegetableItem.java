/**
 * FoodCraft Mod for Minecraft.
 * Copyright (C) 2016 Infinity Studio.
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @license GPLv3
 */
package org.infinitystudio.foodcraftreloaded.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import org.infinitystudio.foodcraftreloaded.common.FoodCraftRegistration;

public class VegetableItem extends FoodItem implements IPlantable {
    public VegetableItem(String name, float saturation, boolean hasEffect, String[] oredicts) {
        super(name, saturation, hasEffect, oredicts);
    }

    protected boolean canPlant = false;

    private Block seedblock;

    public void setCanPlant(boolean canPlant) {
        this.canPlant = canPlant;
    }

    public void setSeedblock(Block seedblock) {
        this.seedblock = seedblock;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (side != EnumFacing.UP) {
            return false;
        } else if (!playerIn.canPlayerEdit(pos.offset(side), side, stack)) {
            return false;
        } else if (worldIn.getBlockState(pos).getBlock().canSustainPlant(worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up())) {
            worldIn.setBlockState(pos.up(), this.seedblock.getDefaultState());
            --stack.stackSize;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return FoodCraftRegistration.blockRice.getDefaultState();
    }
}
