/**
 * Singularity Mod for Minecraft.
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
package org.infinitystudio.foodcraftreloaded.block.machine;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.infinitystudio.foodcraftreloaded.tileentity.GlassCupTileEntity;

/**
 * Cup Block
 * 杯子
 */
public class BlockGlassCup extends Block {
    public BlockGlassCup() {
        super(Material.GLASS);
        setHardness(1.0f);
        setResistance(5.0f);
    }

    /**
     * Called throughout the code as a replacement for ITileEntityProvider.createNewTileEntity
     * Return the same thing you would from that function.
     * This will fall back to ITileEntityProvider.createNewTileEntity(World) if this block is a ITileEntityProvider
     *
     * @param world
     * @param state
     * @return A instance of a class extending TileEntity
     */
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new GlassCupTileEntity();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking())
            return false;
        ItemStack currentItemStack = playerIn.inventory.getCurrentItem();
        if (currentItemStack != null) {
            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(currentItemStack);
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof GlassCupTileEntity) {
                GlassCupTileEntity glassCup = (GlassCupTileEntity) tileEntity;
                if (fluid != null) {
                    if (currentItemStack.isItemEqual(new ItemStack(Items.POTIONITEM))) {
                        fluid = new FluidStack(fluid.getFluid(), (FluidContainerRegistry.BUCKET_VOLUME / 4), fluid.tag);
                        glassCup.drain(side, fluid, true);
                    } else if (currentItemStack.getUnlocalizedName().startsWith("itemJuice")) {
                        fluid = new FluidStack(fluid.getFluid(), (FluidContainerRegistry.BUCKET_VOLUME / 4), fluid.tag);
                        glassCup.drain(side, fluid, true);
                    }
                }
            }
        }
        return true;
    }
}
