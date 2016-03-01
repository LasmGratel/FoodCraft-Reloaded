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
package org.infinitystudio.foodcraftreloaded.block;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class FruitTreeSaplingBlock extends BaseBlock implements IGrowable {
    private Block fruit;

    public FruitTreeSaplingBlock(Block fruitBlock) {
        fruit = fruitBlock;
    }

    private boolean isBlockAir(World w, int x, int y, int z) {
        if(w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.air
                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.leaves
                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.leaves2
//                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == FoodCraftRegistration.blockFruitTreeLeaves
          ) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean canGrow(World w, BlockPos pos, IBlockState state, boolean isClient) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        if(
            //A
            isBlockAir(w, x, y + 1, z) &&
            isBlockAir(w, x, y + 2, z) &&
            isBlockAir(w, x, y + 3, z) &&
            isBlockAir(w, x, y + 4, z) &&
            isBlockAir(w, x, y + 5, z) &&
            isBlockAir(w, x, y + 6, z) &&
            isBlockAir(w, x, y + 7, z) &&
            isBlockAir(w, x, y + 8, z) &&
            isBlockAir(w, x, y + 9, z) &&
            isBlockAir(w, x, y + 10, z) &&
            //-x
            isBlockAir(w, x - 1, y + 6, z) &&
            isBlockAir(w, x - 1, y + 7, z) &&
            //+x
            isBlockAir(w, x + 1, y + 6, z) &&
            isBlockAir(w, x + 1, y + 7, z) &&
            //-z
            isBlockAir(w, x, y + 6, z - 1) &&
            isBlockAir(w, x, y + 7, z - 1) &&
            //+z
            isBlockAir(w, x, y + 6, z + 1) &&
            isBlockAir(w, x, y + 7, z + 1) &&
            //-x leaves
            isBlockAir(w, x - 2, y + 6, z) &&
            isBlockAir(w, x - 3, y + 7, z) &&
            isBlockAir(w, x - 3, y + 5, z) &&
            isBlockAir(w, x - 4, y + 8, z) &&
            //+x leaves
            isBlockAir(w, x + 2, y + 6, z) &&
            isBlockAir(w, x + 3, y + 7, z) &&
            isBlockAir(w, x + 3, y + 5, z) &&
            isBlockAir(w, x + 4, y + 8, z) &&
            //-z leaves
            isBlockAir(w, x, y + 6, z - 2) &&
            isBlockAir(w, x, y + 7, z - 3) &&
            isBlockAir(w, x, y + 5, z - 3) &&
            isBlockAir(w, x, y + 8, z - 4) &&
            //+z leaves
            isBlockAir(w, x, y + 6, z + 2) &&
            isBlockAir(w, x, y + 7, z + 3) &&
            isBlockAir(w, x, y + 5, z + 3) &&
            isBlockAir(w, x, y + 8, z + 4) &&
            //fruit
            isBlockAir(w, x, y + 5, z + 1) &&
            isBlockAir(w, x, y + 5, z - 1) &&
            isBlockAir(w, x + 1, y + 5, z) &&
            isBlockAir(w, x - 1, y + 5, z) &&

            isBlockAir(w, x, y + 4, z + 1) &&
            isBlockAir(w, x, y + 4, z - 1) &&
            isBlockAir(w, x + 1, y + 4, z) &&
            isBlockAir(w, x - 1, y + 4, z) &&

            isBlockAir(w, x, y + 3, z + 1) &&
            isBlockAir(w, x, y + 3, z - 1) &&
            isBlockAir(w, x + 1, y + 3, z) &&
            isBlockAir(w, x - 1, y + 3, z)) {
            return true;
        } else {
            return false;
        }
    }

    private void setBlockToTree(World w, int x, int y, int z, Block leaves) {
//        if(w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.air
//                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.leaves
//                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.leaves2
//                || w.getBlockState(new BlockPos(x, y, z)).getBlock() ==
//                FoodCraftRegistration.blockFruitTreeLeaves) {
//            w.setBlockState(new BlockPos(x, y, z),leaves.getDefaultState());
//        }
    }

    private void setBlockToFruit(World w, int x, int y, int z, Block leaves) {
//        if(w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.air
//                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.leaves
//                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.leaves2
//                || w.getBlockState(new BlockPos(x, y, z)).getBlock() == FoodCraftRegistration.blockFruitTreeLeaves) {
//            w.setBlockState(new BlockPos(x, y, z),leaves.getDefaultState());
//        }
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }


    @Override
    public void grow(World w, Random rand, BlockPos pos, IBlockState state) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        w.setBlockToAir(new BlockPos(x, y, z));
        //A
        setBlockToTree(w, x, y, z,Blocks.log);
        setBlockToTree(w, x, y + 1, z,Blocks.log);
        setBlockToTree(w, x, y + 2, z,Blocks.log);
        setBlockToTree(w, x, y + 3, z,Blocks.log);
        setBlockToTree(w, x, y + 4, z,Blocks.log);
        setBlockToTree(w, x, y + 5, z,Blocks.log);
        setBlockToTree(w, x, y + 6, z,Blocks.leaves);
        setBlockToTree(w, x, y + 7, z,Blocks.leaves);
        setBlockToTree(w, x, y + 8, z,Blocks.leaves);
        setBlockToTree(w, x, y + 9, z,Blocks.leaves);
        setBlockToTree(w, x, y + 10, z,Blocks.leaves);
        //-x
        setBlockToTree(w, x - 1, y + 6, z,Blocks.leaves);
        setBlockToTree(w, x - 1, y + 7, z,Blocks.leaves);
        //+x
        setBlockToTree(w, x + 1, y + 6, z,Blocks.leaves);
        setBlockToTree(w, x + 1, y + 7, z,Blocks.leaves);
        //-z
        setBlockToTree(w, x, y + 6, z - 1,Blocks.leaves);
        setBlockToTree(w, x, y + 7, z - 1,Blocks.leaves);
        //+z
        setBlockToTree(w, x, y + 6, z + 1,Blocks.leaves);
        setBlockToTree(w, x, y + 7, z + 1,Blocks.leaves);

        //-x leaves
        setBlockToTree(w, x - 2, y + 6, z,Blocks.leaves);
        setBlockToTree(w, x - 3, y + 7, z,Blocks.leaves);
        setBlockToTree(w, x - 3, y + 5, z,Blocks.leaves);
        setBlockToTree(w, x - 4, y + 8, z,Blocks.leaves);
        //+x leaves
        setBlockToTree(w, x + 2, y + 6, z,Blocks.leaves);
        setBlockToTree(w, x + 3, y + 7, z,Blocks.leaves);
        setBlockToTree(w, x + 3, y + 5, z,Blocks.leaves);
        setBlockToTree(w, x + 4, y + 8, z,Blocks.leaves);

        //-z leaves
        setBlockToTree(w, x, y + 6, z - 2,Blocks.leaves);
        setBlockToTree(w, x, y + 7, z - 3,Blocks.leaves);
        setBlockToTree(w, x, y + 5, z - 3,Blocks.leaves);
        setBlockToTree(w, x, y + 8, z - 4,Blocks.leaves);
        //+z leaves
        setBlockToTree(w, x, y + 6, z + 2,Blocks.leaves);
        setBlockToTree(w, x, y + 7, z + 3,Blocks.leaves);
        setBlockToTree(w, x, y + 5, z + 3,Blocks.leaves);
        setBlockToTree(w, x, y + 8, z + 4,Blocks.leaves);

        //fruit
        setBlockToFruit(w, x, y + 5, z + 1,fruit);
        setBlockToFruit(w, x, y + 5, z - 1,fruit);
        setBlockToFruit(w, x + 1, y + 5, z,fruit);
        setBlockToFruit(w, x - 1, y + 5, z,fruit);

        setBlockToFruit(w, x, y + 4, z + 1,fruit);
        setBlockToFruit(w, x, y + 4, z - 1,fruit);
        setBlockToFruit(w, x + 1, y + 4, z,fruit);
        setBlockToFruit(w, x - 1, y + 4, z,fruit);

        setBlockToFruit(w, x, y + 3, z + 1,fruit);
        setBlockToFruit(w, x, y + 3, z - 1,fruit);
        setBlockToFruit(w, x + 1, y + 3, z,fruit);
        setBlockToFruit(w, x - 1, y + 3, z,fruit);
    }
}
