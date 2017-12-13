/*
 * FoodCraft Mod - Add more food to your Minecraft.
 * Copyright (C) 2017 Lasm Gratel
 *
 * Builderhis file is part of FoodCraft Mod.
 *
 * FoodCraft Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FoodCraft Mod is distributed in the hope that it will be useful,
 * but WIBuilderHOUBuilder ANY WARRANBuilderY; without even the implied warranty of
 * MERCHANBuilderABILIBuilderY or FIBuilderNESS FOR A PARBuilderICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with FoodCraft Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package cc.lasmgratel.foodcraftreloaded.common.worldgen;

import cc.lasmgratel.foodcraftreloaded.common.util.world.BlockQueries;
import cc.lasmgratel.foodcraftreloaded.common.util.world.BlockQuery;
import cc.lasmgratel.foodcraftreloaded.common.util.world.IBlockPosQuery;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class BaseTreeGenerator extends WorldGenerator {
    private int maxHeight;
    private int minHeight;
    private IBlockPosQuery replace;
    private IBlockPosQuery placeOn;
    private IBlockPosQuery placeVinesOn;
    private int maxLeavesRadius = 1;
    private int leavesLayerHeight = 2;
    private int leafLayers;
    private IBlockState leaves;
    private IBlockState altLeaves;
    private IBlockState hanging;
    private IBlockState log;
    private boolean updateNeighbours;
    private IBlockState vine;
    private int leavesOffset;
    private float hangingChance = 0.0f;

    public BaseTreeGenerator(int maxHeight, int minHeight, IBlockPosQuery replace, IBlockPosQuery placeOn, IBlockPosQuery placeVinesOn, int maxLeavesRadius, int leavesLayerHeight, int leafLayers, IBlockState leaves, IBlockState altLeaves, IBlockState hanging, IBlockState log, boolean updateNeighbours, IBlockState vine, int leavesOffset, float hangingChance) {
        this.maxHeight = maxHeight;
        this.minHeight = minHeight;
        this.replace = replace;
        this.placeOn = placeOn;
        this.placeVinesOn = placeVinesOn;
        this.maxLeavesRadius = maxLeavesRadius;
        this.leavesLayerHeight = leavesLayerHeight;
        this.leafLayers = leafLayers;
        this.leaves = leaves;
        this.altLeaves = altLeaves;
        this.hanging = hanging;
        this.log = log;
        this.updateNeighbours = updateNeighbours;
        this.vine = vine;
        this.leavesOffset = leavesOffset;
        this.hangingChance = hangingChance;
    }

    public static class Builder {
        protected IBlockPosQuery placeOn = BlockQuery.anything;
        protected IBlockPosQuery replace = new BlockQuery.BlockQueryMaterial(Material.AIR, Material.LEAVES);
        protected IBlockState log = Blocks.LOG.getDefaultState();
        protected IBlockState leaves = Blocks.LOG.getDefaultState();
        protected IBlockState altLeaves = null;
        protected int minHeight = 4;
        protected int maxHeight = 7;
        protected boolean updateNeighbours = false;

        public Builder placeOn(IBlockPosQuery a) {
            this.placeOn = a;
            return this;
        }

        public Builder placeOn(String a) throws BlockQuery.BlockQueryParseException {
            this.placeOn = BlockQuery.parseQueryString(a);
            return this;
        }

        public Builder placeOn(Block a) {
            this.placeOn = new BlockQuery.BlockQueryBlock(a);
            return this;
        }

        public Builder placeOn(IBlockState a) {
            this.placeOn = new BlockQuery.BlockQueryState(a);
            return this;
        }

        public Builder placeOn(Material... a) {
            this.placeOn = new BlockQuery.BlockQueryMaterial(a);
            return this;
        }

        public Builder replace(IBlockPosQuery a) {
            this.replace = a;
            return this;
        }

        public Builder replace(String a) throws BlockQuery.BlockQueryParseException {
            this.replace = BlockQuery.parseQueryString(a);
            return this;
        }

        public Builder replace(Block a) {
            this.replace = new BlockQuery.BlockQueryBlock(a);
            return this;
        }

        public Builder replace(IBlockState a) {
            this.replace = new BlockQuery.BlockQueryState(a);
            return this;
        }

        public Builder replace(Material... a) {
            this.replace = new BlockQuery.BlockQueryMaterial(a);
            return this;
        }

        public Builder log(IBlockState a) {
            this.log = a;
            return this;
        }

        public Builder log(BlockPlanks.EnumType a) {
            if (a.getMetadata() < 4) {
                this.log = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, a);
            } else {
                this.log = Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, a);
            }
            return this;
        }

        public Builder leaves(IBlockState a) {
            this.leaves = a;
            return this;
        }

        public Builder leaves(BlockPlanks.EnumType a) {
            if (a.getMetadata() < 4) {
                this.leaves = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockOldLeaf.VARIANT, a);
            } else {
                this.leaves = Blocks.LEAVES2.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockNewLeaf.VARIANT, a);
            }
            return this;
        }

        public Builder altLeaves(IBlockState a) {
            this.altLeaves = a;
            return this;
        }

        public Builder altLeaves(BlockPlanks.EnumType a) {
            if (a.getMetadata() < 4) {
                this.altLeaves = Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockOldLeaf.VARIANT, a);
            } else {
                this.altLeaves = Blocks.LEAVES2.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockNewLeaf.VARIANT, a);
            }
            return this;
        }

        public Builder minHeight(int a) {
            this.minHeight = a;
            return this;
        }

        public Builder maxHeight(int a) {
            this.maxHeight = a;
            return this;
        }

        public Builder updateNeighbours(boolean a) {
            this.updateNeighbours = a;
            return this;
        }

        public BaseTreeGenerator build() {
            return new BaseTreeGenerator(maxHeight, minHeight, replace, placeOn, BlockQueries.air, 1, 2, 4, leaves, altLeaves, null, log, updateNeighbours, null ,1, 0.0f);
        }
    }

    @Override
    public boolean generate(@Nonnull World world, @Nonnull Random random, @Nonnull BlockPos pos) {
        int height = random.nextInt(this.maxHeight - this.minHeight) + this.minHeight;
        boolean hasSpace = true;

        //Generate only if we are above the lowest bedrock level (1) and reach less than the world height
        //There must be a gap of 1 between the top leaf block and the world height
        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256) {
            int radius;

            for (int y = pos.getY(); y <= pos.getY() + 1 + height; y++) {
                radius = 1;

                //Don't check for space on the first level, if we are a sapling then there will
                //already be a block here (the sapling itself)
                if (y == pos.getY()) {
                    radius = 0;
                }

                //At and above the top log block, require a radius of 2 to be empty
                if (y >= pos.getY() + 1 + height - 2) {
                    radius = 2;
                }

                for (int x = pos.getX() - radius; x <= pos.getX() + radius && hasSpace; ++x) {
                    for (int z = pos.getZ() - radius; z <= pos.getZ() + radius && hasSpace; ++z) {
                        if (y >= 0 && y < 256) {
                            if (!this.replace.matches(world, new BlockPos(x, y, z))) {
                                hasSpace = false;
                            }
                        } else {
                            hasSpace = false;
                        }
                    }
                }
            }

            if (!hasSpace) {
                return false;
            } else {
                BlockPos soilPos = pos.down();
                Block soil = world.getBlockState(soilPos).getBlock();
                boolean isSoil = soil.canSustainPlant(world.getBlockState(soilPos), world, soilPos, EnumFacing.UP, (BlockSapling) Blocks.SAPLING);

                if (this.placeOn.matches(world, soilPos) && isSoil && pos.getY() < 256 - height - 1) {
                    soil.onPlantGrow(world.getBlockState(soilPos), world, soilPos, pos);
                    int leavesLayers = (this.leafLayers - 1);

                    //Generates leaves at the top of the tree, going one block above the top log (<= rather than <)
                    for (int y = pos.getY() + height - leavesLayers; y <= pos.getY() + height; y++) {
                        //Determines the distance from the top of the tree as a negative number
                        int currentLayer = y - (pos.getY() + height);
                        //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                        //the radius closer to the top of the tree. (2, 2, 1, 1)
                        int leavesRadius = this.maxLeavesRadius - currentLayer / this.leavesLayerHeight;

                        for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++) {
                            int xDiff = x - pos.getX();

                            for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; ++z) {
                                int zDiff = z - pos.getZ();

                                //Randomly prevent the generation of leaves on the corners of each layer
                                //If the layer is the top layer, never generate the corners
                                if (Math.abs(xDiff) != leavesRadius || Math.abs(zDiff) != leavesRadius || random.nextInt(2) != 0 && currentLayer != 0) {
                                    BlockPos leavesPos = new BlockPos(x, y, z);
                                    if (this.replace.matches(world, leavesPos)) {
                                        if (this.altLeaves != null) {
                                            if (random.nextInt(4) == 0) {
                                                this.setBlockAndNotifyAdequately(world, leavesPos, this.altLeaves);
                                            } else {
                                                this.setBlockAndNotifyAdequately(world, leavesPos, this.leaves);
                                            }
                                        } else {
                                            this.setBlockAndNotifyAdequately(world, leavesPos, this.leaves);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    this.generateTrunk(world, pos, height);

                    if (this.vine != null) {
                        for (int y = pos.getY() - leavesLayers + height; y <= pos.getY() + height; y++) {
                            //Determines the distance from the top of the tree as a negative number
                            int currentLayer = y - (pos.getY() + height);
                            //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                            //the radius closer to the top of the tree. (3, 3, 2, 2)
                            int leavesRadius = (this.maxLeavesRadius + this.leavesOffset) - currentLayer / this.leavesLayerHeight;

                            for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++) {
                                for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; z++) {
                                    BlockPos blockpos3 = new BlockPos(x, y, z);

                                    //Surround leaves on the edge of the tree with vines and extend them downwards
                                    if (world.getBlockState(blockpos3).getBlock().isLeaves(world.getBlockState(blockpos3), world, blockpos3)) {
                                        BlockPos westPos = blockpos3.west();
                                        BlockPos eastPos = blockpos3.east();
                                        BlockPos northPos = blockpos3.north();
                                        BlockPos southPos = blockpos3.south();

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, westPos)) {
                                            this.extendVines(world, westPos, EnumFacing.EAST);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, eastPos)) {
                                            this.extendVines(world, eastPos, EnumFacing.WEST);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, northPos)) {
                                            this.extendVines(world, northPos, EnumFacing.SOUTH);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, southPos)) {
                                            this.extendVines(world, southPos, EnumFacing.NORTH);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //Generate fruit or any other blocks that may hang off of the tree
                    if (this.hanging != null) this.generateHanging(world, pos, height);

                    /*
                    if (this.trunkFruit != null)
                    {
                        if (random.nextInt(5) == 0 && height > 5)
                        {
                            for (int l3 = 0; l3 < 2; ++l3)
                            {
                                for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                                {
                                    if (random.nextInt(4 - l3) == 0)
                                    {
                                        EnumFacing enumfacing1 = enumfacing.getOpposite();
                                        this.generateTrunkFruit(world, random.nextInt(3), pos.add(enumfacing1.getFrontOffsetX(), height - 5 + l3, enumfacing1.getFrontOffsetZ()), enumfacing);
                                    }
                                }
                            }
                        }
                    }
                    */

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    protected void generateTrunk(World world, BlockPos start, int height) {
        //Create the trunk from the bottom up, using < to ensure it is covered with one layer of leaves
        for (int layer = 0; layer < height; ++layer) {
            BlockPos blockpos2 = start.up(layer);
            if (this.replace.matches(world, blockpos2)) {
                this.setBlockAndNotifyAdequately(world, start.up(layer), this.log);
            }
        }
    }

    protected void generateHanging(World world, BlockPos start, int height) {
        //Generate below the bottom layer of leaves
        int y = start.getY() + (height - this.leafLayers);

        for (int x = start.getX() - (maxLeavesRadius + 1); x <= start.getX() + (maxLeavesRadius + 1); x++) {
            for (int z = start.getZ() - (maxLeavesRadius + 1); z <= start.getZ() + (maxLeavesRadius + 1); z++) {
                BlockPos hangingPos = new BlockPos(x, y, z);

                if (!world.isAirBlock(hangingPos.up()) && (world.isAirBlock(hangingPos)) && world.rand.nextFloat() <= this.hangingChance) {
                    this.setHanging(world, hangingPos);
                }
            }
        }
    }

    public boolean setHanging(World world, BlockPos pos) {
        if (this.hanging == null) {
            return false;
        }
        if (this.replace.matches(world, pos)) {
            world.setBlockState(pos, this.hanging, 2);
        }
        return false;
    }

    private IBlockState getVineStateForSide(EnumFacing side) {
        return this.vine.getBlock() instanceof BlockVine ? this.vine.withProperty(BlockVine.getPropertyFor(side), Boolean.valueOf(true)) : this.vine;
    }

    private void extendVines(World world, BlockPos pos, EnumFacing side) {
        IBlockState vineState = this.getVineStateForSide(side);
        this.setBlockAndNotifyAdequately(world, pos, vineState);

        int length = 4;

        //Extend vine downwards for a maximum of 4 blocks
        for (pos = pos.down(); this.placeVinesOn.matches(world, pos) && length > 0; length--) {
            this.setBlockAndNotifyAdequately(world, pos, vineState);
            pos = pos.down();
        }
    }

    public void setBlockAndNotifyAdequately(World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
        if (this.updateNeighbours) {
            world.setBlockState(pos, state, 3);
        } else {
            world.setBlockState(pos, state, 2);
        }
    }
}
