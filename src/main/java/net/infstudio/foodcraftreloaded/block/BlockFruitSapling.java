package net.infstudio.foodcraftreloaded.block;

import net.infstudio.foodcraftreloaded.item.food.EnumFruitType;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockFruitSapling extends Block implements IGrowable {
    private EnumFruitType fruitType;

    public BlockFruitSapling(EnumFruitType fruitType) {
        super(Material.PLANTS);
        this.fruitType = fruitType;
    }

    @Override
    public boolean canGrow(@Nonnull World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        return (double)worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(@Nonnull World worldIn, @Nonnull Random rand, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        TerrainGen.saplingGrowTree(worldIn, rand, pos);
    }

    public EnumFruitType getFruitType() {
        return fruitType;
    }
}
