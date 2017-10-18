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

package cc.lasmgratel.foodcraftreloaded.util.world;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.EnumPlantType;

public interface BlockQueries {
    // Match block positions adjacent to water
    IBlockPosQuery hasWater = (world, pos) -> (world.getBlockState(pos.west()).getMaterial() == Material.WATER || world.getBlockState(pos.east()).getMaterial() == Material.WATER || world.getBlockState(pos.north()).getMaterial() == Material.WATER || world.getBlockState(pos.south()).getMaterial() == Material.WATER);

    // Match block positions with air above
    IBlockPosQuery airAbove = (world, pos) -> world.isAirBlock(pos.up());

    // Match block positions with air below
    IBlockPosQuery airBelow = (world, pos) -> world.isAirBlock(pos.down());

    // Match block positions with water above
    IBlockPosQuery waterCovered = (world, pos) -> world.getBlockState(pos).getMaterial() == Material.WATER && world.getBlockState(pos.up()).getMaterial() == Material.WATER;

    // Block.setBlockUnbreakable sets the hardness value to -1.0F
    // Match blocks which are not unbreakable - IE not bedrock, barrier, command blocks
    IBlockPosQuery breakable = (world, pos) -> world.getBlockState(pos).getBlockHardness(world, pos) >= 0.0F;

    // Block.setBlockUnbreakable sets the hardness value to -1.0F
    //Match blocks with a solid top
    IBlockPosQuery solid = (world, pos) -> world.getBlockState(pos).isSideSolid(world, pos, EnumFacing.UP);

    // Block.setBlockUnbreakable sets the hardness value to -1.0F
    //Match replacable blocks
    IBlockPosQuery replaceable = (world, pos) -> world.getBlockState(pos).getBlock().isReplaceable(world, pos);

    IBlockPosQuery air = new BlockQuery.BlockQueryMaterial(Material.AIR);
    IBlockPosQuery airOrLeaves = new BlockQuery.BlockQueryMaterial(Material.AIR, Material.LEAVES);

    // Match blocks which count as 'the surface' - useful for finding places to put plants, trees, lilypads etc - note plants, trees, snow all excluded because they sit or grow 'on' the surface
    IBlockPosQuery surfaceBlocks = new BlockQuery.BlockQueryMaterial(Material.BARRIER, Material.CLAY, Material.GRASS, Material.GROUND, Material.ICE, Material.LAVA, Material.PACKED_ICE, Material.ROCK, Material.SAND, Material.WATER);
    // As above but without the liquids - useful for placing stuff on the sea floor
    IBlockPosQuery groundBlocks = new BlockQuery.BlockQueryMaterial(Material.BARRIER, Material.CLAY, Material.GRASS, Material.GROUND, Material.PACKED_ICE, Material.ROCK, Material.SAND);

    IBlockPosQuery fertile = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).create();
//    IBlockPosQuery fertileOrNetherrack = BlockQuery.buildOr().sustainsPlant(EnumPlantType.Plains).blocks(Blocks.NETHERRACK).states(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK), BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.MYCELIAL_NETHERRACK)).create();
//    IBlockPosQuery fertileOrSand = BlockQuery.buildOr().sustainsPlant(EnumPlantType.Plains).blocks(Blocks.SAND, BOPBlocks.white_sand).create();
    IBlockPosQuery sustainsCave = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Cave).create();
    IBlockPosQuery sustainsNether = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Nether).create();
    IBlockPosQuery litBeach = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Beach).withLightAboveAtLeast(8).create();
    IBlockPosQuery litFertileWaterside = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).byWater().create();
    IBlockPosQuery litFertile = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Plains).withLightAboveAtLeast(8).create();
    IBlockPosQuery litSand = BlockQuery.buildAnd().materials(Material.SAND).withLightAboveAtLeast(8).create();
    IBlockPosQuery litDry = BlockQuery.buildAnd().sustainsPlant(EnumPlantType.Desert).withLightAboveAtLeast(8).create();
    IBlockPosQuery litFertileOrDry = BlockQuery.buildOr().add(litFertile).add(litDry).create();
//    IBlockPosQuery spectralMoss = new BlockQuery.BlockQueryState(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SPECTRAL_MOSS));
    IBlockPosQuery underwater = new BlockQuery.BlockQueryMaterial(Material.WATER);
    IBlockPosQuery fertileSeaBed = new BlockQuery.BlockQueryMaterial(Material.GROUND, Material.SAND, Material.CLAY, Material.SPONGE);
    // reed needs the ground block to be water, but the block below that to NOT be water
    IBlockPosQuery suitableForReed = BlockQuery.buildAnd().add((world, pos) -> {
        BlockPos groundPos = pos.down();
        return (world.getBlockState(pos).getMaterial() == Material.WATER && world.getBlockState(pos).getValue(BlockLiquid.LEVEL) == 0 || world.getBlockState(pos).getMaterial() == Material.ICE) &&
            (world.getBlockState(groundPos).getBlock() != Blocks.WATER && world.getBlockState(groundPos).isSideSolid(world, groundPos, EnumFacing.UP));
    }).withLightAboveAtLeast(8).create();
    IBlockPosQuery rootsCanDigThrough = new BlockQuery.BlockQueryMaterial(Material.AIR, Material.WATER, Material.GROUND, Material.GRASS, Material.SAND, Material.CLAY, Material.PLANTS, Material.LEAVES);

}
