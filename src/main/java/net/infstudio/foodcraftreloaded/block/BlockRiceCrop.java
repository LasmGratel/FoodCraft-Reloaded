package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.init.FCRItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockRiceCrop extends BlockCrops {
    public BlockRiceCrop() {
        super();
    }

    @Override
    protected Item getSeed() {
        return FCRItems.RICE;
    }

    @Override
    protected Item getCrop() {
        return FCRItems.RICE;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos) {
        return getDefaultState();
    }
}
