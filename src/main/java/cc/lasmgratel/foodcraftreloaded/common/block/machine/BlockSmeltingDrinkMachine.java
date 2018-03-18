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

package cc.lasmgratel.foodcraftreloaded.common.block.machine;

import cc.lasmgratel.foodcraftreloaded.api.init.FCRBlocks;
import cc.lasmgratel.foodcraftreloaded.api.init.FCRCreativeTabs;
import cc.lasmgratel.foodcraftreloaded.client.EnumGui;
import cc.lasmgratel.foodcraftreloaded.common.FoodCraftReloaded;
import cc.lasmgratel.foodcraftreloaded.common.block.tileentity.TileEntitySmeltingDrinkMachine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockSmeltingDrinkMachine extends BlockMachine {
    public BlockSmeltingDrinkMachine() {
        setCreativeTab(FCRCreativeTabs.BASE);
        setHardness(2.5f);
        setHarvestLevel("pickaxe", 0);
        setLightLevel(1.0f);
    }

    @Override
    public void breakBlock(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        if (worldIn.getTileEntity(pos).hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
            IItemHandler itemHandler = worldIn.getTileEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            for (int i = 0; i < itemHandler.getSlots(); i++)
                spawnAsEntity(worldIn, pos, itemHandler.getStackInSlot(i));
        }
        worldIn.removeTileEntity(pos);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity == null)
            createNewTileEntity(worldIn, getMetaFromState(state));
        // Is player handling a fluid container?
        ItemStack stack = playerIn.getHeldItem(hand).copy();
        stack.setCount(1);
        if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
            // Gets handler capability from container
            IFluidHandlerItem bucket = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
            if (tileEntity.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing)) {
                FluidTank tank = (FluidTank) tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, facing);
                int filled = bucket.fill(tank.getFluid(), true);
                if (filled != 0) {
                    playerIn.getHeldItem(hand).splitStack(1);
                    playerIn.inventory.addItemStackToInventory(bucket.getContainer());
                    tank.drain(filled, true);
                    return true;
                }
                return false;
            }
            return false;
        }
        playerIn.openGui(FoodCraftReloaded.INSTANCE, EnumGui.SMELTING_PRESSURE_COOKER.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, @Nonnull IBlockState state) {
        return new ItemStack(FCRBlocks.SMELTING_DRINK_MACHINE);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nonnull World worldIn, int meta) {
        return new TileEntitySmeltingDrinkMachine();
    }
}
