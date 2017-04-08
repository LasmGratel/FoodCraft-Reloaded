package net.infstudio.foodcraftreloaded.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.BiConsumer;

public class WorldGenTree {
    private IBlockState treeLeaves;
    private IBlockState treeLog;
    private int minHeight;
    private int maxHeight;
    private IBlockState soil;
    private Map<BlockPos, IBlockState> generatedTreeMap = new HashMap<>();

    public Map<BlockPos, IBlockState> getGeneratedTreeMap() {
        return generatedTreeMap;
    }

    public void setGeneratedTreeMap(Map<BlockPos, IBlockState> generatedTreeMap) {
        this.generatedTreeMap = generatedTreeMap;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public IBlockState getSoil() {
        return soil;
    }

    public void setSoil(IBlockState soil) {
        this.soil = soil;
    }

    public IBlockState getTreeLeaves() {
        return treeLeaves;
    }

    public void setTreeLeaves(IBlockState treeLeaves) {
        this.treeLeaves = treeLeaves;
    }

    public IBlockState getTreeLog() {
        return treeLog;
    }

    public void setTreeLog(IBlockState treeLog) {
        this.treeLog = treeLog;
    }

    public void generate(World world, Random random, BlockPos pos, @Nullable BiConsumer<BlockPos, IBlockState> consumer) {
        generateMap(world, random, pos);
        generatedTreeMap.forEach(consumer == null ? (pos1, state) -> setBlockStateWithCheck(world, pos1, state) : consumer);
    }

    public static void setBlockStateWithCheck(World world, BlockPos pos, IBlockState state) {
        if (world.isAirBlock(pos))
            world.setBlockState(pos, state);
    }

    public void generateMap(World world, Random random, BlockPos pos) {
        int height = MathHelper.getInt(random, minHeight, maxHeight);
        if (world.getBlockState(new BlockPos(pos).down()).equals(soil)) {
            // Generate tree log
            for (int i = 0; i <= height - 2; i++) {
                BlockPos logPos = new BlockPos(pos).up(i);
                if (world.isAirBlock(logPos))
                    generatedTreeMap.put(logPos, treeLog);
                else {
                    generatedTreeMap.clear();
                    // Un-able to generate tree
                    return;
                }
            }
            BlockPos height1 = new BlockPos(pos).up(height - 1);
            generatedTreeMap.put(new BlockPos(height1), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).west(), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).east(), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).north(), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).south(), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).west().north(), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).east().north(), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).west().south(), treeLeaves);
            generatedTreeMap.put(new BlockPos(height1).east().south(), treeLeaves);

            BlockPos heightPos = new BlockPos(pos).up(height);
            generatedTreeMap.put(new BlockPos(heightPos), treeLeaves);
            generatedTreeMap.put(new BlockPos(heightPos).west(), treeLeaves);
            generatedTreeMap.put(new BlockPos(heightPos).east(), treeLeaves);
            generatedTreeMap.put(new BlockPos(heightPos).north(), treeLeaves);
            generatedTreeMap.put(new BlockPos(heightPos).south(), treeLeaves);
        }
    }
}
